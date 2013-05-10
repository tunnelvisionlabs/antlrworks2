/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.text.impl;

import java.util.ArrayList;
import java.util.List;
import org.antlr.netbeans.editor.text.DocumentChange;
import org.antlr.netbeans.editor.text.NormalizedDocumentChangeCollection;
import org.antlr.v4.runtime.misc.IntegerList;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class LineTextCache {

    private static int MaximumBlockLength = 64;

    private final int _length;
    private final int _lineCount;

    private final ArrayList<ArrayList<String>> _lineData = new ArrayList<>();
    private final IntegerList _blockOffsets = new IntegerList();
    private final IntegerList _blockLineOffsets = new IntegerList();
    private final ArrayList<IntegerList> _lineOffsets = new ArrayList<>();

    public LineTextCache(@NonNull String data) {
        Parameters.notNull("data", data);

        _length = data.length();

        ArrayList<String> currentBlock = new ArrayList<>();
        IntegerList currentOffsets = new IntegerList();
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        int lineCount = 0;
        int blockStart = 0;
        int blockLineStart = 0;
        int lineStart = 0;
        for (int i = 0; i <= data.length(); i++) {
            if (i == data.length() || data.charAt(i) == '\n') {
                int lineEnd = Math.min(i + 1, data.length());
                currentBlock.add(data.substring(lineStart, lineEnd));
                currentOffsets.add(lineStart - blockStart);
                lineCount++;
                lineStart = i + 1;
                if (i == data.length() || currentBlock.size() == MaximumBlockLength) {
                    _lineData.add(currentBlock);
                    _lineOffsets.add(currentOffsets);
                    _blockOffsets.add(blockStart);
                    _blockLineOffsets.add(blockLineStart);
                    currentBlock = new ArrayList<>();
                    currentOffsets = new IntegerList();
                    blockStart = lineStart;
                    blockLineStart = lineCount;
                }
            }
        }

        this._lineCount = lineCount;

        trimToSize();
    }

    private LineTextCache(int length, int lineCount) {
        this._length = length;
        this._lineCount = lineCount;
    }

    public int getLength() {
        return _length;
    }

    public int getLineCount() {
        return _lineCount;
    }

    public @NonNull ArrayList<ArrayList<String>> getLineData() {
        return _lineData;
    }

    public @NonNull IntegerList getBlockOffsets() {
        return _blockOffsets;
    }

    public @NonNull IntegerList getBlockLineOffsets() {
        return _blockLineOffsets;
    }

    public @NonNull ArrayList<IntegerList> getLineOffsets() {
        return _lineOffsets;
    }

    public int getBlockFromLineNumber(int lineNumber) {
        int block = _blockLineOffsets.binarySearch(lineNumber);
        if (block < 0) {
            block = -(block + 1) - 1;
        }

        return block;
    }

    public int getBlockFromPosition(int position) {
        int block = _blockOffsets.binarySearch(position);
        if (block < 0) {
            block = -(block + 1) - 1;
        }

        return block;
    }

    public int getBlockLineFromPosition(int block, int position) {
        int blockLine = _lineOffsets.get(block).binarySearch(position - _blockOffsets.get(block));
        if (blockLine < 0) {
            blockLine = -(blockLine + 1) - 1;
        }

        return blockLine;
    }

    public int getLineNumberFromPosition(int position) {
        int block = getBlockFromPosition(position);
        int blockLine = getBlockLineFromPosition(block, position);
        return blockLine + _blockLineOffsets.get(block);
    }

    public @NonNull LineTextCache applyChanges(@NonNull NormalizedDocumentChangeCollection changes) {
        int delta = 0;
        int lineDelta = 0;
        for (DocumentChange change : changes) {
            delta += change.getDelta();
            lineDelta += change.getLineCountDelta();
        }

        LineTextCache next = new LineTextCache(this._length + delta, this._lineCount + lineDelta);

        int oldBlock = 0;
        int oldLine = 0;
        int oldColumn = 0;
        int oldBlockLine = 0; // index of oldLine within oldBlock
        int newPosition = 0;
        ArrayList<String> modifiedBlock = null;
        for (int i = 0; i <= changes.size(); i++) {
            DocumentChange currentChange = i < changes.size() ? changes.get(i) : null;

            /*
             * move forward to this change
             */

            // 1. finish off the current line if necessary
            boolean previousLineEnded = oldColumn == 0;
            if (previousLineEnded && modifiedBlock != null && !modifiedBlock.isEmpty()) {
                String lastLine = modifiedBlock.get(modifiedBlock.size() - 1);
                previousLineEnded = lastLine.charAt(lastLine.length() - 1) == '\n';
            }

            if (!previousLineEnded && (currentChange == null || lineEndsBeforeChange(oldBlock, oldLine, currentChange))) {
                if (modifiedBlock == null) {
                    modifiedBlock = new ArrayList<>();
                }

                int index = modifiedBlock.size() - 1;
                String text = index >= 0 ? modifiedBlock.get(index) : null;
                String oldLineText = getLineText(oldBlock, oldLine);
                String appendText = oldLineText.substring(oldColumn);
                if (text == null || text.charAt(text.length() - 1) == '\n') {
                    modifiedBlock.add(appendText);
                } else {
                    modifiedBlock.set(index, text + appendText);
                }

                newPosition += appendText.length();
                oldLine++;
                oldColumn = 0;
                oldBlockLine++;
                if (oldBlockLine == _lineData.get(oldBlock).size()) {
                    oldBlock++;
                    oldBlockLine = 0;
                }
            }

            // 2. finish off the current block if necessary
            if (modifiedBlock != null && (currentChange == null || blockEndsBeforeChange(oldBlock, currentChange))) {
                assert oldColumn == 0 : "Should be at the beginning of a line.";

                if (oldBlock == _lineData.size()) {
                    // special handling for updates at the end of the last block
                    if (!modifiedBlock.isEmpty()) {
                        next._lineData.add(modifiedBlock);
                    }

                    modifiedBlock = null;
                } else {
                    List<String> remainingLines = _lineData.get(oldBlock).subList(oldBlockLine, _lineData.get(oldBlock).size());
                    if (modifiedBlock.size() + _lineData.get(oldBlock).size() - oldBlockLine < MaximumBlockLength) {
                        modifiedBlock.addAll(remainingLines);
                        for (String text : remainingLines) {
                            newPosition += text.length();
                        }

                        if (!modifiedBlock.isEmpty()) {
                            next._lineData.add(modifiedBlock);
                        }
                    } else {
                        if (!modifiedBlock.isEmpty()) {
                            next._lineData.add(modifiedBlock);
                        }

                        if (oldBlockLine > 0) {
                            next._lineData.add(new ArrayList<>(remainingLines));
                        } else {
                            next._lineData.add(_lineData.get(oldBlock));
                        }

                        for (String text : remainingLines) {
                            newPosition += text.length();
                        }
                    }

                    modifiedBlock = null;
                    oldLine = _blockLineOffsets.get(oldBlock) + _lineData.get(oldBlock).size();
                    oldBlock++;
                    oldBlockLine = 0;
                }
            }

            // 3. move any whole blocks we can
            while (oldBlock < _lineData.size() && (currentChange == null || blockEndsBeforeChange(oldBlock, currentChange))) {
                if (modifiedBlock != null) {
                    if (!modifiedBlock.isEmpty()) {
                        next._lineData.add(modifiedBlock);
                    }
                    modifiedBlock = null;
                }

                newPosition += getBlockEnd(oldBlock) - _blockOffsets.get(oldBlock);
                next._lineData.add(_lineData.get(oldBlock));
                oldLine = _blockLineOffsets.get(oldBlock) + _lineData.get(oldBlock).size();
                oldBlock++;
                oldBlockLine = 0;
            }

            // 4. now move any whole lines we can
            while (oldLine < getLineCount() && (currentChange == null || lineEndsBeforeChange(oldBlock, oldLine, currentChange))) {
                if (modifiedBlock != null && modifiedBlock.size() == MaximumBlockLength) {
                    next._lineData.add(modifiedBlock);
                    modifiedBlock = null;
                }

                if (modifiedBlock == null) {
                    modifiedBlock = new ArrayList<>();
                }

                modifiedBlock.add(_lineData.get(oldBlock).get(oldBlockLine));
                newPosition += modifiedBlock.get(modifiedBlock.size() - 1).length();
                oldLine++;
                oldBlockLine++;
                assert oldBlockLine < _lineData.get(oldBlock).size() : "Should have replaced the rest of the block in stop 2 or 3.";
            }

            if (currentChange != null) {
                // 5. move a partial line if necessary
                int oldPosition = getPosition(oldBlock, oldLine, oldColumn);
                if (oldPosition < currentChange.getOldOffset()) {
                    if (modifiedBlock == null) {
                        modifiedBlock = new ArrayList<>();
                    }

                    int index = modifiedBlock.size() - 1;
                    String text = index >= 0 ? modifiedBlock.get(index) : null;
                    String oldLineText = getLineText(oldBlock, oldLine);
                    String appendText = oldLineText.substring(oldColumn, oldColumn + currentChange.getOldOffset() - oldPosition);
                    if (text == null || text.charAt(text.length() - 1) == '\n') {
                        modifiedBlock.add(appendText);
                    } else {
                        modifiedBlock.set(index, text + appendText);
                    }

                    oldColumn += currentChange.getOldOffset() - oldPosition;
                    newPosition += appendText.length();
                    assert !modifiedBlock.get(modifiedBlock.size() - 1).endsWith("\n") : "Should have replaced the end of the line in step 1 or 4.";
                }

                /*
                 * apply the change
                 */

                assert newPosition == currentChange.getNewOffset() : "Moves are not supported... should be synchronized at this point.";

                int lineStart = 0;
                String newText = currentChange.getNewText();
                for (int j = 0; j <= newText.length(); j++) {
                    if (j == newText.length() || newText.charAt(j) == '\n') {
                        if (modifiedBlock == null) {
                            modifiedBlock = new ArrayList<>();
                        }

                        int index = modifiedBlock.size() - 1;
                        String text = index >= 0 ? modifiedBlock.get(index) : null;
                        int lineEnd = Math.min(j + 1, newText.length());
                        if (lineStart < lineEnd) {
                            String appendText = newText.substring(lineStart, lineEnd);
                            if (text == null || text.charAt(text.length() - 1) == '\n') {
                                modifiedBlock.add(appendText);
                            } else {
                                modifiedBlock.set(index, text + appendText);
                            }

                            lineStart = lineEnd;
                            if (j == newText.length() - 1) {
                                break;
                            }
                        }
                    }
                }

                // update the current position
                oldPosition = getPosition(oldBlock, oldLine, oldColumn) + currentChange.getOldLength();
                oldBlock = getBlockFromPosition(oldPosition);
                oldLine = getLineNumberFromPosition(oldPosition);
                oldColumn = oldPosition - getLineStart(oldBlock, oldLine);
                oldBlockLine = oldLine - _blockLineOffsets.get(oldBlock);
                newPosition += newText.length();
            }
        }

        if (modifiedBlock != null) {
            if (!modifiedBlock.isEmpty()) {
                next._lineData.add(modifiedBlock);
            }
            modifiedBlock = null;
        }

        // now set all the indexes in the new cache
        int newBlockOffset = 0; // offset of the start of the current block
        int newLine = 0; // absolute line number
        for (int newBlock = 0; newBlock < next._lineData.size(); newBlock++) {
            ArrayList<String> block = next._lineData.get(newBlock);
            next._blockOffsets.add(newBlockOffset);
            next._blockLineOffsets.add(newLine);
            next._lineOffsets.add(new IntegerList(MaximumBlockLength));
            int blockLineOffset = 0;
            for (int newBlockLine = 0; newBlockLine < block.size(); newBlockLine++) {
                next._lineOffsets.get(newBlock).add(blockLineOffset);
                blockLineOffset += block.get(newBlockLine).length();
                newLine++;
                // all lines before last end with \n
                assert (newBlock == next._lineData.size() - 1 && newBlockLine == block.size() - 1) || block.get(newBlockLine).endsWith("\n");
                // last line does not end with \n
                assert newBlock < next._lineData.size() - 1 || newBlockLine < block.size() - 1 || !block.get(newBlockLine).endsWith("\n");
            }

            assert next._lineOffsets.get(newBlock).size() == block.size();
            newBlockOffset += blockLineOffset;
        }

        trimToSize();

        assert next._blockOffsets.size() == next._lineData.size();
        assert next._blockLineOffsets.size() == next._lineData.size();
        assert next._lineOffsets.size() == next._lineData.size();
        assert newBlockOffset == next._length;
        assert newLine == next._lineCount;
//        assert next.getLineCount() == next.blockOffsets.get(next.lineData.size() - 1) + next.lineData.get(next.lineData.size() - 1).size() : "Line counts should match";
//        assert next.getLength() == next.getBlockEnd(next.lineData.size() - 1) : "Lengths should match";

        return next;
    }

    public boolean lineEndsBeforeChange(int block, int line, DocumentChange change) {
        if (getLineEnd(block, line) < change.getOldOffset()) {
            return true;
        }

        return getLineEnd(block, line) == change.getOldOffset()
            && line < getLineCount() - 1;
    }

    public boolean blockEndsBeforeChange(int block, DocumentChange change) {
        if (getBlockEnd(block) < change.getOldOffset()) {
            return true;
        }

        return getBlockEnd(block) == change.getOldOffset()
            && block < _lineData.size() - 1;
    }

    public int getBlockEnd(int block) {
        if (block == _lineData.size() - 1) {
            return getLength();
        }

        return _blockOffsets.get(block + 1);
    }

    public int getPosition(int block, int line, int column) {
        int blockLine = line - _blockLineOffsets.get(block);
        return _blockOffsets.get(block) + _lineOffsets.get(block).get(blockLine) + column;
    }

    public int getLineStart(int block, int line) {
        int blockLine = line - _blockLineOffsets.get(block);
        return _blockOffsets.get(block) + _lineOffsets.get(block).get(blockLine);
    }

    public int getLineEnd(int block, int line) {
        int blockLine = line - _blockLineOffsets.get(block);
        if (blockLine == _lineData.get(block).size() - 1) {
            return getBlockEnd(block);
        }

        return _blockOffsets.get(block) + _lineOffsets.get(block).get(blockLine + 1);
    }

    public @NonNull String getLineText(int block, int line) {
        int blockLine = line - _blockLineOffsets.get(block);
        return _lineData.get(block).get(blockLine);
    }

    private void trimToSize() {
        _lineData.trimToSize();
        _blockOffsets.trimToSize();
        _blockLineOffsets.trimToSize();
        _lineOffsets.trimToSize();

        for (ArrayList<?> list : _lineData) {
            list.trimToSize();
        }

        for (IntegerList list : _lineOffsets) {
            list.trimToSize();
        }
    }

}
