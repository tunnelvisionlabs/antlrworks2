/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
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
 * This class provides utility methods for working with rectangular selections.
 *
 * @author Sam Harwell
 */
public final class SelectionHelper {

    /** Boolean property defining whether selection is being rectangular in a particular text component. */
    private static final String RECTANGULAR_SELECTION_PROPERTY = "rectangular-selection"; // NOI18N

    /** List of positions (with even size) defining regions of rectangular selection. Maintained by BaseCaret. */
    private static final String RECTANGULAR_SELECTION_REGIONS_PROPERTY = "rectangular-selection-regions"; // NOI18N

    private SelectionHelper() {
        // static class
    }

    public static List<DocumentSpan> getSelection(JTextComponent target) {
        List<DocumentSpan> spans = new ArrayList<>();
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
            List<Position> positions = new ArrayList<>();
            for (int i = 0; i < updatedSelection.size(); i++) {
                positions.add(document.createPosition(updatedSelection.get(i).getStart().getOffset()));
                positions.add(document.createPosition(updatedSelection.get(i).getEnd().getOffset()));
            }

            target.putClientProperty(RECTANGULAR_SELECTION_REGIONS_PROPERTY, positions);
        } else {
            target.select(updatedSelection.get(0).getStart().getOffset(), updatedSelection.get(0).getEnd().getOffset());
        }
    }

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
                lRegions = new ArrayList<>(regions);
            }
        } else {
            lRegions = null;
        }
        return lRegions;
    }

}
