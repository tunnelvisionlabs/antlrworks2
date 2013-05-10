/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.navigation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.netbeans.api.annotations.common.NonNull;
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

    private final Collection<Description> children = new ArrayList<>(0);

    private FileObject fileObject;
    private boolean inherited;
    private String name;
    private String htmlHeader;
    private int offset;
    private SnapshotPosition position;
    private SnapshotPositionRegion span;

    public Description() {
    }

    public Description(String name) {
        this.name = name;
    }

    public static String htmlEscape(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    @NonNull
    public Collection<Description> getChildren() {
        return children;
    }

    public int getOffset() {
        if (position != null) {
            return position.getOffset();
        }

        return offset;
    }

    public SnapshotPositionRegion getSpan() {
        return span;
    }

    public String getName() {
        return name;
    }

    public String getSortText() {
        return getName().toUpperCase();
    }

    public String getHtmlHeader() {
        if (htmlHeader != null) {
            return htmlHeader;
        }

        if (isInherited()) {
            return String.format("<font color='%s'>%s</font>", INHERITED_COLOR, htmlEscape(getName()));
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

    public void setSpan(SnapshotPositionRegion span) {
        this.span = span;
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
