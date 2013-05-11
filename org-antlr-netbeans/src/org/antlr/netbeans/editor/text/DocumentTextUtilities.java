/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.text;

import javax.swing.text.Document;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.editor.BaseDocument;

/**
 *
 * @author Sam Harwell
 */
public final class DocumentTextUtilities {

    private DocumentTextUtilities() {
    }

    /** Get the identifier around the given position or null if there's no identifier
    * around the given position. The identifier must be
    * accepted by SyntaxSupport.isIdnetifier() otherwise null is returned.
    * @param position position in document - usually the caret.getDot()
    * @return the block (starting and ending position) enclosing the identifier
    *   or null if no identifier was found
    */
    @SuppressWarnings("deprecation")
    public static @CheckForNull SnapshotPositionRegion getIdentifierBlock(@NonNull SnapshotPosition position) {
        DocumentSnapshot snapshot = position.getSnapshot();
        Document document = snapshot.getVersionedDocument().getDocument();
        if (!(document instanceof BaseDocument)) {
            return null;
        }

        BaseDocument baseDocument = (BaseDocument)document;

        SnapshotPosition idStart = getWordStart(position);
        if (idStart != null) {
            SnapshotPosition idEnd = getWordEnd(idStart);
            if (idEnd != null) {
                SnapshotPositionRegion region = new SnapshotPositionRegion(idStart, idEnd);
                String id = region.getText();
                if (baseDocument.getSyntaxSupport().isIdentifier(id)) {
                    return region;
                } else { // not identifier by syntax support
                    id = getWord(position); // try right at offset
                    if (baseDocument.getSyntaxSupport().isIdentifier(id)) {
                        assert id != null;
                        return new SnapshotPositionRegion(position, id.length());
                    }
                }
            }
        }

        return null;
    }

    public static @CheckForNull SnapshotPosition getWordStart(@NonNull SnapshotPosition position) {
        Document document = position.getSnapshot().getVersionedDocument().getDocument();
        if (!(document instanceof BaseDocument)) {
            return null;
        }

        return find(new SnapshotFinderFactory.PreviousWordBwdFinder((BaseDocument)document, false, true),
                        position, SearchDirection.BACKWARD);
    }

    public static @CheckForNull SnapshotPosition getWordEnd(@NonNull SnapshotPosition position) {
        Document document = position.getSnapshot().getVersionedDocument().getDocument();
        if (!(document instanceof BaseDocument)) {
            return null;
        }

        SnapshotPosition ret = find(new SnapshotFinderFactory.NextWordFwdFinder((BaseDocument)document, false, true),
                        position, SearchDirection.FORWARD);
        return (ret != null) ? ret : new SnapshotPosition(position.getSnapshot(), position.getSnapshot().length());
    }

    /** Get the word at given position.
     * @param position
     * @return
    */
    public static @CheckForNull String getWord(@NonNull SnapshotPosition position) {
        SnapshotPosition wordEnd = getWordEnd(position);
        if (wordEnd != null) {
            DocumentSnapshot snapshot = position.getSnapshot();
            return snapshot.subSequence(position.getOffset(), wordEnd.getOffset()).toString();
        }

        return null;
    }

    public static @CheckForNull SnapshotPosition find(@NonNull SnapshotFinder finder, @NonNull SnapshotPosition position) {
        return find(finder, position, SearchDirection.FORWARD);
    }

    public static @CheckForNull SnapshotPosition find(@NonNull SnapshotFinder finder, @NonNull SnapshotPosition position, @NonNull SearchDirection direction) {
        if (direction == SearchDirection.FORWARD) {
            int snapshotLength = position.getSnapshot().length();
            return find(finder, new SnapshotPositionRegion(position, snapshotLength - position.getOffset()), direction);
        } else {
            return find(finder, new SnapshotPositionRegion(position.getSnapshot(), 0, position.getOffset()), direction);
        }
    }

    public static @CheckForNull SnapshotPosition find(@NonNull SnapshotFinder finder, @NonNull SnapshotPositionRegion region, @NonNull SearchDirection direction) {
        DocumentSnapshot snapshot = region.getSnapshot();

        if (finder instanceof SnapshotAdjustFinder) {
            if (region.isEmpty()) { // stop immediately
                finder.reset(); // reset() should be called in all the cases
                return null; // must stop here because wouldn't know if fwd/bwd search?
            }

            region = ((SnapshotAdjustFinder)finder).adjustPositionRegion(region);
            if (region == null) {
                finder.reset();
                return null;
            }
        }

        finder.reset();
        if (region.getLength() == 0) {
            return null;
        }

//        CharSequence text = new javax.swing.text.Segment();
        int gapStart = 0;
//        int gapStart = ((EditorDocumentContent)getContent()).getCharContentGapStart();
//        if (gapStart == -1) {
//            throw new IllegalStateException("Cannot get gapStart"); // NOI18N
//        }

        int startPos = region.getStart().getOffset();
        int limitPos = region.getEnd().getOffset();
        int pos = startPos; // pos at which the search starts (continues)
        boolean fwdSearch = direction == SearchDirection.FORWARD; // forward search
        if (fwdSearch) {
            while (pos >= startPos && pos < limitPos) {
                int p0; // low bound
                int p1; // upper bound
                if (pos < gapStart) { // part below gap
                    p0 = startPos;
                    p1 = Math.min(gapStart, limitPos);
                } else { // part above gap
                    p0 = Math.max(gapStart, startPos);
                    p1 = limitPos;
                }

                //getText(p0, p1 - p0, text);
                CharSequence text = snapshot.subSequence(p0, p1);
                int textOffset = p0;
                pos = finder.find(p0 - textOffset, text,
                        textOffset, textOffset + text.length(), pos, limitPos);

                if (finder.isFound()) {
                    return new SnapshotPosition(snapshot, pos);
                }
            }

        } else { // backward search limitPos < startPos
            pos--; // start one char below the upper bound
            while (limitPos <= pos && pos <= startPos) {
                int p0; // low bound
                int p1; // upper bound
                if (pos < gapStart) { // part below gap
                    p0 = limitPos;
                    p1 = Math.min(gapStart, startPos);
                } else { // part above gap
                    p0 = Math.max(gapStart, limitPos);
                    p1 = startPos;
                }

                //getText(p0, p1 - p0, text);
                CharSequence text = snapshot.subSequence(p0, p1);
                int textOffset = p0;
                pos = finder.find(p0 - textOffset, text,
                        textOffset, textOffset + text.length(), pos, limitPos);

                if (finder.isFound()) {
                    return new SnapshotPosition(snapshot, pos);
                }
            }
        }

        return null; // position outside bounds => not found
    }

    public enum SearchDirection {
        FORWARD,
        BACKWARD,
    }

    public interface SnapshotAdjustFinder {
        SnapshotPositionRegion adjustPositionRegion(SnapshotPositionRegion region);
    }

    public interface SnapshotFinder {
        void reset();

        int find(int bufferStartPos, CharSequence buffer, int offset1, int offset2, int reqPos, int limitPos);

        boolean isFound();
    }

    private static class SnapshotFinderFactory {

        /** Abstract finder implementation. The only <CODE>find()</CODE>
        * method must be redefined.
        */
        public static abstract class AbstractFinder implements SnapshotFinder {

            /** Was the string found? */
            protected boolean found;

            /** Was the string found? */
            @Override
            public final boolean isFound() {
                return found;
            }

            /** Reset the finder */
            @Override
            public void reset() {
                found = false;
            }

            }

        /** Generic forward finder that simplifies the search process. */
        public static abstract class GenericFwdFinder extends AbstractFinder {

            @Override
            public final int find(int bufferStartPos, CharSequence buffer,
                                int offset1, int offset2, int reqPos, int limitPos) {
                int offset = reqPos - bufferStartPos;
                int limitOffset = limitPos - bufferStartPos - 1;
                while (offset >= offset1 && offset < offset2) {
                    offset += scan(buffer.charAt(offset), (offset == limitOffset));
                    if (found) {
                        break;
                    }
                }
                return bufferStartPos + offset;
            }

            /** This function decides if it found a desired string or not.
            * The function receives currently searched character and flag if it's
            * the last one that is searched or not.
            * @return if the function decides that
            * it found a desired string it sets <CODE>found = true</CODE> and returns
            * how many characters back the searched string begins in forward
            * direction (0 stands for current character).
            * For example if the function looks for word 'yes' and it gets
            * 's' as parameter it sets found = true and returns -2.
            * If the string is not yet found it returns how many characters it should go
            * in forward direction (in this case it would usually be 1).
            * The next searched character will be that one requested.
            */
            protected abstract int scan(char ch, boolean lastChar);

        }

        /** Generic backward finder that simplifies the search process. */
        public static abstract class GenericBwdFinder extends AbstractFinder {

            @Override
            public final int find(int bufferStartPos, CharSequence buffer,
                                int offset1, int offset2, int reqPos, int limitPos) {
                int offset = reqPos - bufferStartPos;
                int limitOffset = limitPos - bufferStartPos;
                while (offset >= offset1 && offset < offset2) {
                    offset += scan(buffer.charAt(offset), (offset == limitOffset));
                    if (found) {
                        break;
                    }
                }
                return bufferStartPos + offset;
            }

            /** This function decides if it found a desired string or not.
            * The function receives currently searched character and flag if it's
            * the last one that is searched or not.
            * @return if the function decides that
            * it found a desired string it sets <CODE>found = true</CODE> and returns
            * how many characters back the searched string begins in backward
            * direction (0 stands for current character). It is usually 0 as the
            * finder usually decides after the last required character but it's
            * not always the case e.g. for whole-words-only search it can be 1 or so.
            * If the string is not yet found it returns how many characters it should go
            * in backward direction (in this case it would usually be -1).
            * The next searched character will be that one requested.
            */
            protected abstract int scan(char ch, boolean lastChar);

        }

        /** Next word forward finder */
        public static class NextWordFwdFinder extends GenericFwdFinder {

            /** Document used to recognize the character types */
            BaseDocument doc;

            /** Currently inside whitespace */
            boolean inWhitespace;

            /** Currently inside identifier */
            boolean inIdentifier;

            /** Currently inside not in word and not in whitespace */
            boolean inPunct;

            /** Whether scanning the first character */
            boolean firstChar;

            /** Whether stop on EOL */
            boolean stopOnEOL;

            /** Stop with successful find on the first white character */
            boolean stopOnWhitespace;

            public NextWordFwdFinder(BaseDocument doc, boolean stopOnEOL, boolean stopOnWhitespace) {
                this.doc = doc;
                this.stopOnEOL = stopOnEOL;
                this.stopOnWhitespace = stopOnWhitespace;
            }

            public @Override void reset() {
                super.reset();
                inWhitespace = false;
                inIdentifier = false;
                inPunct = false;
                firstChar = true;
            }

            @Override
            protected int scan(char ch, boolean lastChar) {
                if (stopOnEOL) {
                    if (ch == '\n') {
                        found = true;
                        return firstChar ? 1 : 0;
                    }
                    firstChar = false;
                }

                if (doc.isWhitespace(ch)) { // whitespace char found
                    if (stopOnWhitespace) {
                        found = true;
                        return 0;
                    } else {
                        inWhitespace = true;
                        return 1;
                    }
                }

                if (inWhitespace) {
                    found = true;
                    return 0;
                }
                if (inIdentifier) { // inside word
                    if (doc.isIdentifierPart(ch)) { // still in word
                        return 1;
                    }
                    found = true;
                    return 0; // found punct
                }
                if (inPunct) { // inside punctuation
                    if (doc.isIdentifierPart(ch)) { // a word starts after punct
                        found = true;
                        return 0;
                    }
                    return 1; // still in punct
                }

                // just starting - no state assigned yet
                if (doc.isIdentifierPart(ch)) {
                    inIdentifier = true;
                    return 1;
                } else {
                    inPunct = true;
                    return 1;
                }
            }

        }

        /** Find start of the word. This finder can be used to go to previous
        * word or to the start of the current word.
        */
        public static class PreviousWordBwdFinder extends GenericBwdFinder {

            BaseDocument doc;

            /** Currently inside identifier */
            boolean inIdentifier;

            /** Currently inside not in word and not in whitespace */
            boolean inPunct;

            /** Stop on EOL */
            boolean stopOnEOL;

            /** Stop with successful find on the first white character */
            boolean stopOnWhitespace;

            boolean firstChar;

            public PreviousWordBwdFinder(BaseDocument doc, boolean stopOnEOL, boolean stopOnWhitespace) {
                this.doc = doc;
                this.stopOnEOL = stopOnEOL;
                this.stopOnWhitespace = stopOnWhitespace;
            }

            @Override
            public void reset() {
                super.reset();
                inIdentifier = false;
                inPunct = false;
                firstChar = true;
            }

            @Override
            protected int scan(char ch, boolean lastChar) {
                if (stopOnEOL) {
                    if (ch == '\n') {
                        found = true;
                        return firstChar ? 0 : 1;
                    }
                    firstChar = false;
                }

                if (inIdentifier) { // inside word
                    if (doc.isIdentifierPart(ch)) {
                        if (lastChar) {
                            found = true;
                            return 0;
                        }
                        return -1;
                    }
                    found = true;
                    return 1; // found punct or whitespace
                }
                if (inPunct) { // inside punctuation
                    if (doc.isIdentifierPart(ch) || doc.isWhitespace(ch) || lastChar) {
                        found = true;
                        return 1;
                    }
                    return -1; // still in punct
                }
                if (doc.isWhitespace(ch)) {
                    if (stopOnWhitespace) {
                        found = true;
                        return 1;
                    }
                    return -1;
                }
                if (doc.isIdentifierPart(ch)) {
                    inIdentifier = true;
                    if (lastChar) {
                        found = true;
                        return 0;
                    }
                    return -1;
                }
                inPunct = true;
                return -1;
            }

        }

    }

}
