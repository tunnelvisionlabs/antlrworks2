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
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.EnumSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.grammar.GoToSupport;
import org.antlr.works.editor.grammar.GrammarDataObject;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.completion.GrammarCompletionProvider;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.GrammarSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ParserRuleSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.RuleActionContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.RulePrequelsContext;
import org.antlr.works.editor.grammar.experimental.generated.GrammarParserBaseVisitor;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.editor.EditorRegistry;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.awt.NotificationDisplayer;
import org.openide.awt.NotificationDisplayer.Priority;
import org.openide.cookies.EditorCookie;
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
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarSpec, version=0, dependents=Dependents.SELF)
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

            int maximumVersion = MaximumVersionNumberVisitor.INSTANCE.visit(grammarSpecContext);
            String message = String.format("Need to update rule '%s' to version %d...", _description.getName(), maximumVersion + 1);
            NotificationDisplayer.getDefault().notify("Feature Not Implemented", EmptyIcon.INSTANCE, message, (ActionListener)null, Priority.NORMAL);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        } catch (ExecutionException ex) {
            Exceptions.printStackTrace(ex);
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

    private static class MaximumVersionNumberVisitor extends GrammarParserBaseVisitor<Integer> {
        public static final MaximumVersionNumberVisitor INSTANCE = new MaximumVersionNumberVisitor();

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleAction, version=0, dependents=Dependents.SELF),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1, dependents=Dependents.DESCENDANTS),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_actionBlock, version=0, dependents=Dependents.DESCENDANTS),
        })
        public Integer visitRuleAction(RuleActionContext ctx) {
            if (ctx.id() == null || ctx.actionBlock() == null) {
                return 0;
            }

            if ("version".equals(ctx.id().getText())) {
                String actionBlockText = ctx.actionBlock().getText();

                int beginIndex = 0;
                int endIndex = actionBlockText.length();
                if (actionBlockText.startsWith("{")) {
                    beginIndex++;
                }

                if (actionBlockText.endsWith("}")) {
                    endIndex--;
                }

                actionBlockText = actionBlockText.substring(beginIndex, endIndex).trim();
                try {
                    return Integer.parseInt(actionBlockText);
                } catch (NumberFormatException ex) {
                    return 0;
                }
            }

            return 0;
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0, dependents=Dependents.SELF),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_rulePrequels, version=0, dependents=Dependents.SELF),
        })
        public Integer visitParserRuleSpec(ParserRuleSpecContext ctx) {
            RulePrequelsContext rulePrequelsContext = ctx.rulePrequels();
            if (rulePrequelsContext == null) {
                return 0;
            }

            return visit(rulePrequelsContext);
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0, dependents=Dependents.SELF)
        public Integer visitLexerRule(LexerRuleContext ctx) {
            // lexer rules aren't versioned
            return 0;
        }

        @Override
        protected Integer aggregateResult(Integer aggregate, Integer nextResult) {
            return Math.max(aggregate, nextResult);
        }

        @Override
        protected Integer defaultResult() {
            return 0;
        }
    }
}
