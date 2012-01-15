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
package org.antlr.netbeans.editor.navigation;

import java.util.Collection;
import java.util.Comparator;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Sam Harwell
 */
public class Description {

    public static final Comparator<Description> ALPHA_COMPARATOR =
        new DescriptionComparator(true);
    public static final Comparator<Description> POSITION_COMPARATOR =
        new DescriptionComparator(false);

    public static final String INHERITED_COLOR = "#7D694A";

    private FileObject fileObject;
    private boolean inherited;
    private String name;
    private String htmlHeader;
    private Collection<Description> children;
    private int offset;
    private SnapshotPosition position;

    public Description() {
    }

    public Description(String name) {
        this.name = name;
    }

    public Collection<Description> getChildren() {
        return children;
    }

    public void setChildren(Collection<Description> children) {
        this.children = children;
    }

    public int getOffset() {
        if (position != null) {
            return position.getOffset();
        }

        return offset;
    }

    public String getName() {
        return name;
    }

    public String getSortText() {
        return getName();
    }

    public String getHtmlHeader() {
        if (htmlHeader != null) {
            return htmlHeader;
        }

        if (isInherited()) {
            return String.format("<font color='%s'>%s</font>", INHERITED_COLOR, getName());
        }

        return null;
    }

    public void setHtmlHeader(String htmlHeader) {
        this.htmlHeader = htmlHeader;
    }

    public void setOffset(DocumentSnapshot snapshot, FileObject fileObject, int snapshotOffset) {
        if (snapshot == null) {
            assert fileObject != null;
            this.fileObject = fileObject;
            offset = snapshotOffset;
            position = null;
        } else {
            this.fileObject = snapshot.getVersionedDocument().getFileObject();
            this.offset = snapshotOffset;
            this.position = null;
            if (offset >= 0) {
                position = new SnapshotPosition(snapshot, offset);
            }
        }
    }

    public FileObject getFileObject() {
        return fileObject;
    }

    public void setFileObject(FileObject fileObject) {
        this.fileObject = fileObject;
    }

    public boolean isInherited() {
        return inherited;
    }

    public void setInherited(boolean value) {
        inherited = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Description)) {
            return false;
        }

        Description other = (Description)obj;
        if (this.name != other.name && (this.name == null || !this.name.equals(other.name))) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    private static class DescriptionComparator implements Comparator<Description> {
        private final boolean alpha;

        DescriptionComparator(boolean alpha) {
            this.alpha = alpha;
        }

        @Override
        public int compare(Description d1, Description d2) {

            if (alpha) {
                return alphaCompare(d1, d2);
            } else {
                if (d1.isInherited() && !d2.isInherited()) {
                    return 1;
                }
                if (!d1.isInherited() && d2.isInherited()) {
                    return -1;
                }
                if (d1.isInherited() && d2.isInherited()) {
                    return alphaCompare(d1, d2);
                }

                return d1.getOffset() - d2.getOffset();
            }
        }

        private int alphaCompare(Description d1, Description d2) {
            return d1.getSortText().compareTo(d2.getSortText());
        }
    }
}
