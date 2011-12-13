/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.antlr.netbeans.editor;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.Position;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Sam Harwell
 */
public final class SelectionHelper {

    private SelectionHelper() {
        // static class
    }

    public static List<DocumentSpan> getSelection(JTextComponent target) {
        List<DocumentSpan> spans = new ArrayList<DocumentSpan>();
        Document document = target.getDocument();
        if (!(document instanceof StyledDocument)) {
            return null;
        }

        if (isRectangularSelection(target)) {
            List<Position> positions = regionsCopy(target);
            for (int i = 0; i < positions.size(); i += 2) {
                Position start = positions.get(i);
                Position end = positions.get(i + 1);
                DocumentSpan selection = new DocumentSpan((StyledDocument)document, start, end);
                spans.add(selection);
            }
        } else {
            try {
                DocumentSpan selection = new DocumentSpan((StyledDocument)document, target.getSelectionStart(), target.getSelectionEnd());
                spans.add(selection);
            } catch (BadLocationException ex) {
                throw new IllegalStateException("Shouldn't be reachable: the selection should be within valid bounds.", ex);
            }
        }

        return spans;
    }

    public static void setSelection(JTextComponent target, List<DocumentSpan> updatedSelection) throws BadLocationException {
        Document document = target.getDocument();

        if (updatedSelection.size() > 1 && !isRectangularSelection(target)) {
            setRectangularSelection(target, true);
        }

        if (updatedSelection.size() > 1) {
            List<Position> positions = new ArrayList<Position>();
            for (int i = 0; i < updatedSelection.size(); i++) {
                positions.add(document.createPosition(updatedSelection.get(i).getStart().getOffset()));
                positions.add(document.createPosition(updatedSelection.get(i).getEnd().getOffset()));
            }

            target.putClientProperty(RECTANGULAR_SELECTION_REGIONS_PROPERTY, positions);
        } else {
            target.select(updatedSelection.get(0).getStart().getOffset(), updatedSelection.get(0).getEnd().getOffset());
        }
    }

    /** Boolean property defining whether selection is being rectangular in a particular text component. */
    private static final String RECTANGULAR_SELECTION_PROPERTY = "rectangular-selection"; // NOI18N

    /** List of positions (with even size) defining regions of rectangular selection. Maintained by BaseCaret. */
    private static final String RECTANGULAR_SELECTION_REGIONS_PROPERTY = "rectangular-selection-regions"; // NOI18N

    public static boolean isRectangularSelection(JComponent c) {
        return Boolean.TRUE.equals(c.getClientProperty(RECTANGULAR_SELECTION_PROPERTY));
    }

    public static void setRectangularSelection(JComponent c, boolean value) {
        c.putClientProperty(RECTANGULAR_SELECTION_PROPERTY, value);
    }

    public static String getRectangularSelectionProperty() {
        return RECTANGULAR_SELECTION_PROPERTY;
    }

    public static List<Position> regionsCopy(JComponent c) {
        @SuppressWarnings("unchecked") List<Position> regions =
                (List<Position>) c.getClientProperty(RECTANGULAR_SELECTION_REGIONS_PROPERTY);
        List<Position> lRegions;
        if (regions != null) {
            synchronized (regions) {
                lRegions = new ArrayList<Position>(regions);
            }
        } else {
            lRegions = null;
        }
        return lRegions;
    }

}
