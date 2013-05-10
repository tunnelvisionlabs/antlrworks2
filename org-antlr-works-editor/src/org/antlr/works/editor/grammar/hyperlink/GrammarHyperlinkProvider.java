/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.hyperlink;

import java.lang.ref.WeakReference;
import java.util.EnumSet;
import java.util.Set;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.DocumentSpan;
import org.antlr.works.editor.grammar.GoToSupport;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.overridden.GoToImplementation;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.lib.editor.hyperlink.spi.HyperlinkProviderExt;
import org.netbeans.lib.editor.hyperlink.spi.HyperlinkType;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=HyperlinkProviderExt.class)
public class GrammarHyperlinkProvider implements HyperlinkProviderExt {

    private WeakReference<Document> _lastDocument;
    private int _lastOffset;
    private HyperlinkType _lastHyperlinkType;
    private int[] _lastResult;

    @Override
    public Set<HyperlinkType> getSupportedHyperlinkTypes() {
        return EnumSet.of(HyperlinkType.GO_TO_DECLARATION, HyperlinkType.ALT_HYPERLINK);
    }

    @Override
    public boolean isHyperlinkPoint(Document doc, int offset, HyperlinkType type) {
        return getHyperlinkSpan(doc, offset, type) != null;
    }

    @Override
    public int[] getHyperlinkSpan(Document doc, int offset, HyperlinkType type) {
        // The hyperlink operation calls isHyperlinkPoint before calling getHyperlinkSpan (both in the UI thread). Cache the result.
        if (_lastDocument != null && _lastDocument.get() == doc) {
            if (_lastOffset == offset && _lastHyperlinkType == type) {
                if (_lastResult == null) {
                    return null;
                } else {
                    return _lastResult.clone();
                }
            }
        } else {
            _lastDocument = new WeakReference<>(doc);
        }

        _lastOffset = offset;
        _lastHyperlinkType = type;
        _lastResult = null;

        if (!(doc instanceof StyledDocument)) {
            return null;
        }

        DocumentSpan span = GoToSupport.getIdentifierSpan((StyledDocument)doc, offset);
        if (span == null) {
            return null;
        }

        _lastResult = new int[] { span.getStart().getOffset(), span.getEnd().getOffset() };
        return _lastResult.clone();
    }

    @Override
    public void performClickAction(Document doc, int offset, HyperlinkType type) {
        if (!(doc instanceof StyledDocument)) {
            return;
        }

        switch (type) {
        case GO_TO_DECLARATION:
            GoToSupport.goTo((StyledDocument)doc, offset, false);
            break;

        case ALT_HYPERLINK:
            JTextComponent focused = EditorRegistry.focusedComponent();
            if (focused != null && focused.getDocument() == doc) {
                focused.setCaretPosition(offset);
                GoToImplementation.goToImplementation(focused);
            }
            break;
        }
    }

    @Override
    public String getTooltipText(Document doc, int offset, HyperlinkType type) {
        if (!(doc instanceof StyledDocument)) {
            return "";
        }

        return GoToSupport.getGoToElementToolTip((StyledDocument)doc, offset, false, type == HyperlinkType.GO_TO_DECLARATION ? "TP_OverriddenTooltipSugg" : "TP_GoToOverriddenTooltipSugg");
    }

}
