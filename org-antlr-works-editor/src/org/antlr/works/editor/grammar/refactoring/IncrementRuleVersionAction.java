/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.refactoring;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.DocumentSnapshotLine;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.TrackingPosition;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.Tuple;
import org.antlr.v4.runtime.misc.Tuple3;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.tool.Grammar;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.antlr.works.editor.grammar.GoToSupport;
import org.antlr.works.editor.grammar.GrammarDataObject;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.completion.GrammarCompletionProvider;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ActionBlockContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.GrammarSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ParserRuleSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.RuleActionContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.RulePrequelsContext;
import org.antlr.works.editor.grammar.experimental.generated.GrammarParserBaseVisitor;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.editor.BaseDocument;
import org.netbeans.editor.GuardedDocument;
import org.netbeans.modules.editor.indent.api.Indent;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.cookies.EditorCookie;
import org.openide.text.NbDocument;
import org.openide.util.ContextAwareAction;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;

@ActionID(
    category = "Refactoring",
    id = "org.antlr.works.editor.grammar.refactoring.IncrementRuleVersionAction")
@ActionRegistration(
    displayName = "#CTL_IncrementRuleVersionAction", lazy = false)
@ActionReference(path = "Editors/" + GrammarEditorKit.GRAMMAR_MIME_TYPE + "/Popup/refactor", position = 100)
@Messages("CTL_IncrementRuleVersionAction=Increment Rule Version")
public final class IncrementRuleVersionAction extends AbstractAction implements ContextAwareAction {
    // -J-Dorg.antlr.works.editor.grammar.refactoring.IncrementRuleVersionAction.level=FINE
    private static final Logger LOGGER = Logger.getLogger(IncrementRuleVersionAction.class.getName());

    @NullAllowed
    private final Lookup _context;
    private final GrammarDataObject _dataObject;
    private final EditorCookie _editorCookie;

    private final DocumentSnapshot _snapshot;
    private final Description _description;

    public IncrementRuleVersionAction() {
        this(null);
    }

    public IncrementRuleVersionAction(@NullAllowed Lookup context) {
        super(Bundle.CTL_IncrementRuleVersionAction());
        this._context = context;
        this._dataObject = context != null ? context.lookup(GrammarDataObject.class) : null;
        this._editorCookie = context != null ? context.lookup(EditorCookie.class) : null;

        if (_editorCookie != null) {
            JTextComponent focused = EditorRegistry.focusedComponent();
            if (!_editorCookie.getDocument().equals(focused.getDocument())) {
                focused = _editorCookie.getOpenedPanes()[0];
            }

            Token token = GoToSupport.getContext(_editorCookie.getDocument(), focused.getCaretPosition());
            if (token != null && token.getType() == GrammarParser.RULE_REF) {
                ParserTaskManager parserTaskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
                VersionedDocument versionedDocument = VersionedDocumentUtilities.getVersionedDocument(_editorCookie.getDocument());
                _snapshot = versionedDocument.getCurrentSnapshot();
                Collection<Description> rules = GrammarCompletionProvider.getRulesFromGrammar(parserTaskManager, _snapshot, false);
                SnapshotPosition caretPosition = new SnapshotPosition(_snapshot, focused.getCaretPosition());
                Description currentDescription = null;
                for (Description description : rules) {
                    if (Grammar.isTokenName(description.getName())) {
                        continue;
                    }

                    if (!_dataObject.getPrimaryFile().equals(description.getFileObject())) {
                        continue;
                    }

                    SnapshotPositionRegion namePosition = new SnapshotPositionRegion(_snapshot, description.getOffset(), description.getName().length());
                    if (caretPosition.compareTo(namePosition.getStart()) >= 0 && caretPosition.compareTo(namePosition.getEnd()) <= 0) {
                        currentDescription = description;
                        break;
                    }
                }

                _description = currentDescription;
            } else {
                _snapshot = null;
                _description = null;
            }
        } else {
            _snapshot = null;
            _description = null;
        }
    }

    @Override
    public boolean isEnabled() {
        return _description != null;
    }

    @Override
    public Action createContextAwareInstance(Lookup actionContext) {
        if (actionContext == null) {
            return null;
        }

        if (actionContext.lookup(GrammarDataObject.class) == null) {
            return null;
        }

        if (actionContext.lookup(EditorCookie.class) == null) {
            return null;
        }

        return new IncrementRuleVersionAction(actionContext);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarSpec, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleAction, version=0, dependents=Dependents.SELF),
    })
    public void actionPerformed(ActionEvent ev) {
        try {
            ParserTaskManager parserTaskManager = Lookup.getDefault().lookup(ParserTaskManager.class);

            // check for a rule at the caret position
            Future<ParserData<GrammarSpecContext>> futureData = parserTaskManager.getData(_snapshot, GrammarParserDataDefinitions.REFERENCE_PARSE_TREE, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
            ParserData<GrammarSpecContext> data = futureData != null ? futureData.get() : null;
            GrammarSpecContext grammarSpecContext = data != null ? data.getData() : null;
            if (grammarSpecContext == null) {
                return;
            }

            Map<ParserRuleSpecContext, String> rules = getRules(grammarSpecContext);
            Map<ParserRuleSpecContext, Tuple3<RuleActionContext, TerminalNode, Integer>> versionedRules =
                getVersionedRules(rules);
            int maximumVersion = getMaximumVersionNumber(versionedRules);

            ParserRuleSpecContext currentRule = findRuleForDescription(rules, _description);
            if (currentRule == null) {
                throw new UnsupportedOperationException("Could not locate the current rule in the parse tree.");
//                NotificationDisplayer.getDefault().notify("Could Not Apply 'Increment Rule Version'", EmptyIcon.INSTANCE, message, (ActionListener)null, Priority.NORMAL);
//                return;
            }

            Tuple3<RuleActionContext, TerminalNode, Integer> currentVersion = versionedRules.get(currentRule);
            if (currentVersion == null) {
                addVersionNumber(currentRule, maximumVersion + 1);
            } else if (currentVersion.getItem2() == null) {
                addVersionNumberToAction(currentVersion.getItem1(), maximumVersion + 1);
            } else {
                updateVersionNumber(currentVersion.getItem2(), maximumVersion + 1);
            }
        } catch (InterruptedException | ExecutionException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private void updateVersionNumber(TerminalNode currentVersionToken, final int newVersion) {
        Interval sourceInterval = ParseTrees.getSourceInterval(currentVersionToken);
        OffsetRegion region = OffsetRegion.fromBounds(sourceInterval.a, sourceInterval.b + 1);
        TrackingPositionRegion trackingRegion = _snapshot.createTrackingRegion(region, TrackingPositionRegion.Bias.Forward);
        final SnapshotPositionRegion currentRegion = trackingRegion.getRegion(_snapshot.getVersionedDocument().getCurrentSnapshot());
        final BaseDocument baseDocument = (BaseDocument)_snapshot.getVersionedDocument().getDocument();
        if (baseDocument == null) {
            throw new UnsupportedOperationException("No document available");
        }

        baseDocument.runAtomicAsUser(new Runnable() {

            @Override
            public void run() {
                try {
                    baseDocument.remove(currentRegion.getStart().getOffset(), currentRegion.getLength());
                    baseDocument.insertString(currentRegion.getStart().getOffset(), Integer.toString(newVersion), null);
                } catch (BadLocationException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        });
    }

    private void addVersionNumberToAction(RuleActionContext versionAction, int version) {
        ActionBlockContext actionBlockContext = versionAction.actionBlock();
        if (actionBlockContext == null || actionBlockContext.BEGIN_ACTION() == null || actionBlockContext.END_ACTION() == null) {
            throw new UnsupportedOperationException("Incomplete or invalid action block");
        }

        final String text = Integer.toString(version) + (actionBlockContext.getChildCount() == 2 ? "" : " ");

        int offset = ParseTrees.getSourceInterval(actionBlockContext.BEGIN_ACTION()).b + 1;
        TrackingPosition trackingPosition = _snapshot.createTrackingPosition(offset, TrackingPosition.Bias.Forward);
        final SnapshotPosition currentPosition = trackingPosition.getPosition(_snapshot.getVersionedDocument().getCurrentSnapshot());
        final BaseDocument baseDocument = (BaseDocument)_snapshot.getVersionedDocument().getDocument();
        if (baseDocument == null) {
            throw new UnsupportedOperationException("No document available");
        }

        baseDocument.runAtomicAsUser(new Runnable() {

            @Override
            public void run() {
                try {
                    baseDocument.insertString(currentPosition.getOffset(), text, null);
                } catch (BadLocationException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        });
    }

    private void addVersionNumber(@NonNull ParserRuleSpecContext ruleContext, int version) {
        TerminalNode colon = ruleContext.COLON();
        if (colon == null) {
            throw new UnsupportedOperationException("Incomplete rule");
        }

        DocumentSnapshotLine line = _snapshot.findLineFromLineNumber(colon.getSymbol().getLine() - 1);
        final boolean hasLeadingNewline = line.getText().substring(0, colon.getSymbol().getCharPositionInLine()).trim().isEmpty();

        final String text = String.format("%s@version{%d}\n", hasLeadingNewline ? "" : "\n", version);

        int offset = ParseTrees.getSourceInterval(colon).a;
        TrackingPosition trackingPosition = _snapshot.createTrackingPosition(offset, TrackingPosition.Bias.Forward);
        final SnapshotPosition currentPosition = trackingPosition.getPosition(_snapshot.getVersionedDocument().getCurrentSnapshot());
        final GuardedDocument document = (GuardedDocument)_snapshot.getVersionedDocument().getDocument();
        if (document == null) {
            throw new UnsupportedOperationException("No " + GuardedDocument.class.getName() + " available");
        }

        Indent indent = Indent.get(document);
        indent.lock();
        try {
            document.runAtomicAsUser(new Runnable() {

                @Override
                public void run() {
                    try {
                        document.insertString(currentPosition.getOffset(), text, null);

                        // reindent the line containing the @version{} action and the (following) line containing the ':'
                        int startLine = currentPosition.getContainingLine().getLineNumber() + (hasLeadingNewline ? 0 : 1);
                        int endLine = startLine + 1;

                        // currently the reindent algorithm only supports operating on one line at a time
                        for (int i = startLine; i <= endLine; i++) {
                            int lineOffset = NbDocument.findLineOffset(document, i);
                            Indent.get(document).reindent(lineOffset);
                        }
                    } catch (BadLocationException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            });
        } finally {
            indent.unlock();
        }
    }

    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarSpec, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=3, dependents=Dependents.ANCESTORS),
    })
    private Map<ParserRuleSpecContext, String> getRules(GrammarSpecContext grammarSpec) {
        return RulesVisitor.INSTANCE.visit(grammarSpec);
    }

    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleAction, version=0, dependents=Dependents.SELF),
    })
    private Map<ParserRuleSpecContext, Tuple3<RuleActionContext, TerminalNode, Integer>> getVersionedRules(Map<ParserRuleSpecContext, String> rules) {
        Map<ParserRuleSpecContext, Tuple3<RuleActionContext, TerminalNode, Integer>> result =
            new HashMap<>();
        for (Map.Entry<ParserRuleSpecContext, String> entry : rules.entrySet()) {
            RuleActionContext versionAction = VersionActionVisitor.INSTANCE.visit(entry.getKey());
            if (versionAction == null) {
                continue;
            }

            TerminalNode word = null;
            Integer version = null;

            List<TerminalNode> words = findActionWords(versionAction.actionBlock());
            if (words.size() != 1) {
                LOGGER.log(Level.WARNING, "{0}", String.format("The '@version{}' block for rule '%s' should only contain a single non-negative integer.", entry.getValue()));
            }

            for (TerminalNode node : words) {
                version = parseVersion(node);
                if (version != null) {
                    word = node;
                    break;
                }
            }

            result.put(entry.getKey(), Tuple.create(versionAction, word, version));
        }

        return result;
    }

    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_actionBlock, version=5, dependents={Dependents.PARENTS, Dependents.DESCENDANTS}),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleAction, version=0, dependents=Dependents.SELF),
    })
    private List<TerminalNode> findActionWords(ActionBlockContext ctx) {
        if (!(ctx.getParent() instanceof RuleActionContext)) {
            LOGGER.log(Level.WARNING, "Only expected to analyze 'actionBlock' nodes in a 'ruleAction' context.");
            return Collections.emptyList();
        }

        RuleActionContext ruleActionContext = (RuleActionContext)ctx.getParent();
        if (ruleActionContext.id() == null || !"version".equals(ruleActionContext.id().getText())) {
            LOGGER.log(Level.WARNING, "Only expected to analyze 'actionBlock' nodes for a @version{} action.");
            return Collections.emptyList();
        }

        boolean reportedProblem = false;
        List<TerminalNode> result = new ArrayList<>();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            String problem = null;
            ParseTree child = ctx.getChild(i);
            if (!(child instanceof TerminalNode)) {
                problem = "The '@version{}' block for a rule should only contain a single non-negative integer.";
            }

            if (problem == null) {
                int symbolType = ((TerminalNode)child).getSymbol().getType();

                if (i == 0) {
                    if (symbolType != GrammarParser.BEGIN_ACTION) {
                        problem = "The 'actionBlock' rule should start with a 'BEGIN_ACTION' terminal.";
                    }
                } else if (i == ctx.getChildCount() - 1) {
                    if (symbolType != GrammarParser.END_ACTION) {
                        problem = "The 'actionBlock' rule should end with an 'END_ACTION' terminal.";
                    }
                } else {
                    switch (symbolType) {
                    case GrammarParser.BEGIN_ACTION:
                        problem = "Unexpected 'BEGIN_ACTION' terminal after start of 'actionBlock' rule.";
                        break;

                    case GrammarParser.END_ACTION:
                        problem = "Unexpected 'END_ACTION' terminal before end of 'actionBlock' rule.";
                        break;

                    case GrammarParser.ACTION_WS:
                    case GrammarParser.ACTION_NEWLINE:
                    case GrammarParser.ACTION_COMMENT:
                        // ignore these tokens
                        continue;

                    case GrammarParser.ACTION_WORD:
                        // this is the token we're interested in
                        result.add((TerminalNode)child);
                        continue;

                    default:
                        // anything is a problem
                        problem = "The '@version{}' block for a rule should only contain a single non-negative integer.";
                        break;
                    }
                }
            }

            if (!reportedProblem && problem != null) {
                String file = _description.getFileObject().getNameExt();
                int line = 0;
                int column = 0;
                LOGGER.log(Level.WARNING, "{0}:{1}:{2}: {3}", new Object[] { file, line, column, problem });
                reportedProblem = true;
            }
        }

        return result;
    }

    @CheckForNull
    private static Integer parseVersion(@NonNull TerminalNode node) {
        try {
            int result = Integer.parseInt(node.getText());
            if (result >= 0) {
                // success
                return result;
            }

            return null;
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    @RuleDependencies({
        // doesn't use any of the rule context inputs
    })
    private static int getMaximumVersionNumber(@NonNull Map<ParserRuleSpecContext, Tuple3<RuleActionContext, TerminalNode, Integer>> versionedRules) {
        int maxVersion = 0;
        for (Map.Entry<ParserRuleSpecContext, Tuple3<RuleActionContext, TerminalNode, Integer>> entry : versionedRules.entrySet()) {
            Integer ruleVersion = entry.getValue().getItem3();
            if (ruleVersion != null) {
                maxVersion = Math.max(maxVersion, ruleVersion);
            }
        }

        return maxVersion;
    }

    @CheckForNull
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0, dependents=Dependents.SELF)
    private static ParserRuleSpecContext findRuleForDescription(@NonNull Map<ParserRuleSpecContext, String> rules, @NonNull Description description) {
        for (Map.Entry<ParserRuleSpecContext, String> entry : rules.entrySet()) {
            if (!description.getName().equals(entry.getValue())) {
                continue;
            }

            Interval sourceInterval = ParseTrees.getSourceInterval(entry.getKey());
            if (sourceInterval.a <= description.getOffset() && sourceInterval.b >= description.getOffset()) {
                return entry.getKey();
            }
        }

        return null;
    }

    private static final class EmptyIcon implements Icon {
        public static final EmptyIcon INSTANCE = new EmptyIcon();

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
        }

        @Override
        public int getIconWidth() {
            return 0;
        }

        @Override
        public int getIconHeight() {
            return 0;
        }
    }

    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0, dependents=Dependents.SELF)
    private static final class RulesVisitor extends GrammarParserBaseVisitor<Map<ParserRuleSpecContext, String>> {
        public static final RulesVisitor INSTANCE = new RulesVisitor();

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarSpec, version=0, dependents=Dependents.SELF),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0, dependents=Dependents.SELF),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleAction, version=0, dependents=Dependents.SELF),
        })
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0, dependents=Dependents.SELF)
        public Map<ParserRuleSpecContext, String> visitParserRuleSpec(ParserRuleSpecContext ctx) {
            TerminalNode nameToken = ctx.RULE_REF();
            String name = nameToken != null ? nameToken.getText() : "";
            return Collections.singletonMap(ctx, name);
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0, dependents=Dependents.SELF),
            // for the assumption about which contexts can contain parserRuleSpec:
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=3, dependents=Dependents.ANCESTORS),
        })
        public Map<ParserRuleSpecContext, String> visitLexerRule(LexerRuleContext ctx) {
            // optimization: lexer rules cannot contain parser rules
            return defaultResult();
        }

        @Override
        @RuleDependencies({
            // no dependencies
        })
        protected Map<ParserRuleSpecContext, String> aggregateResult(Map<ParserRuleSpecContext, String> aggregate, Map<ParserRuleSpecContext, String> nextResult) {
            if (aggregate.isEmpty()) {
                return nextResult;
            }

            if (nextResult.isEmpty()) {
                return aggregate;
            }

            Map<ParserRuleSpecContext, String> result = new HashMap<>(aggregate);
            result.putAll(nextResult);
            return result;
        }

        @Override
        @RuleDependencies({
            // no dependencies
        })
        protected Map<ParserRuleSpecContext, String> defaultResult() {
            return Collections.emptyMap();
        }
    }

    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleAction, version=0, dependents=Dependents.SELF)
    private static final class VersionActionVisitor extends GrammarParserBaseVisitor<RuleActionContext> {
        public static final VersionActionVisitor INSTANCE = new VersionActionVisitor();

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0, dependents=Dependents.SELF),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_rulePrequels, version=0, dependents=Dependents.SELF),
        })
        public RuleActionContext visitParserRuleSpec(ParserRuleSpecContext ctx) {
            RulePrequelsContext rulePrequelsContext = ctx.rulePrequels();
            if (rulePrequelsContext == null) {
                return null;
            }

            return visit(rulePrequelsContext);
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0, dependents=Dependents.SELF),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=3, dependents=Dependents.ANCESTORS),
        })
        public RuleActionContext visitLexerRule(LexerRuleContext ctx) {
            // lexer rules aren't versioned (optimization)
            return null;
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleAction, version=0, dependents=Dependents.SELF)
        public RuleActionContext visitRuleAction(RuleActionContext ctx) {
            if (ctx.id() == null || !"version".equals(ctx.id().getText())) {
                return null;
            }

            return ctx;
        }

        @Override
        @RuleDependencies({
            // no dependencies
        })
        protected RuleActionContext aggregateResult(RuleActionContext aggregate, RuleActionContext nextResult) {
            if (aggregate != null && nextResult != null) {
                LOGGER.log(Level.WARNING, "Found multiple @version{} actions for rule.");
            }

            if (aggregate != null) {
                return aggregate;
            }
            
            return nextResult;
        }

    }
}
