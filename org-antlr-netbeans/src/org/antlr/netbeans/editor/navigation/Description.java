/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.antlr.netbeans.editor.navigation;

import java.util.Collection;
import java.util.Comparator;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Position;
import org.netbeans.modules.parsing.api.Snapshot;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;

/**
 *
 * @author sam
 */
public class Description {

    public static final Comparator<Description> ALPHA_COMPARATOR =
        new DescriptionComparator(true);
    public static final Comparator<Description> POSITION_COMPARATOR =
        new DescriptionComparator(false);

    public static final String INHERITED_COLOR = "#7D694A";

    private final NavigatorPanelUI ui;
    private FileObject fileObject;
    private boolean inherited;
    private String name;
    private String htmlHeader;
    private Collection<Description> children;
    private int offset;
    private Position position;

    public Description(NavigatorPanelUI ui) {
        this.ui = ui;
    }

    public Description(NavigatorPanelUI ui, String name) {
        this.ui = ui;
        this.name = name;
    }

    public NavigatorPanelUI getUI() {
        return ui;
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

    public void setOffset(Snapshot snapshot, FileObject fileObject, int snapshotOffset) {
        if (snapshot == null) {
            this.fileObject = fileObject;
            offset = snapshotOffset;
            position = null;
        } else {
            this.fileObject = snapshot.getSource().getFileObject();
            offset = snapshot.getOriginalOffset(snapshotOffset);
            position = null;
            if (offset >= 0) {
                Document document = snapshot.getSource().getDocument(false);
                if (document != null) {
                    try {
                        position = document.createPosition(offset);
                    } catch (BadLocationException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
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

        int alphaCompare(Description d1, Description d2) {
            return d1.name.compareTo(d2.name);
        }
    }
}
