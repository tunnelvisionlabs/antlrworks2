/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codemodel.impl;

import java.awt.event.ActionEvent;
import java.util.Collection;
import javax.swing.Action;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.navigation.actions.OpenAction;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.antlr.works.editor.grammar.codemodel.CodeElementModel;
import org.antlr.works.editor.grammar.codemodel.CodeElementPositionRegion;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractCodeElementModel implements CodeElementModel {
    @NonNull
    private final String name;
    @NullAllowed
    private final Project project;
    @NonNull
    private final String packagePath;
    @NullAllowed
    private final FileModelImpl file;

    private boolean frozen;

    public AbstractCodeElementModel(@NonNull String name, @NullAllowed Project project, @NonNull String packagePath) {
        this(name, project, packagePath, null);
    }

    public AbstractCodeElementModel(@NonNull String name, @NonNull FileModelImpl file) {
        this(name, file.getProject(), file.getPackagePath(), file);
    }

    private AbstractCodeElementModel(@NonNull String name, @NullAllowed Project project, @NonNull String packagePath, @NullAllowed FileModelImpl file) {
        Parameters.notNull("name", name);
        Parameters.notNull("packagePath", packagePath);

        this.name = name;
        this.project = project;
        this.packagePath = packagePath;
        this.file = file;
    }

    @CheckForNull
    protected static OffsetRegion getOffsetRegion(@NullAllowed ParseTree node) {
        if (node == null) {
            return null;
        }

        Interval sourceInterval = ParseTrees.getSourceInterval(node);
        return new OffsetRegion(sourceInterval.a, sourceInterval.length());
    }

    public boolean isFrozen() {
        return frozen;
    }

    public final void freeze() {
        if (isFrozen()) {
            return;
        }

        freezeImpl();
        frozen = true;
    }

    @Override
    public PackageModelImpl getPackage() {
        return getCodeModelCache().getUniquePackage(project, packagePath);
    }

    @Override
    public String getName() {
        return name;
    }

    @CheckForNull
    public FileModelImpl getFile() {
        return file;
    }

    @Override
    public abstract Collection<? extends AbstractCodeElementModel> getMembers();

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers(@NonNull String name) {
        Parameters.notNull("name", name);
        return CodeModelCacheImpl.findElementsByName(getMembers(), name);
    }

    public Project getProject() {
        return project;
    }

    public String getPackagePath() {
        return packagePath;
    }

    @Override
    public CodeElementPositionRegion getSeek() {
        final CodeElementPositionRegion span = getSpan();
        if (span == null) {
            return null;
        }

        return new CodeElementPositionRegion() {
            @Override
            public FileObject getFileObject() {
                return span.getFileObject();
            }

            @Override
            public OffsetRegion getOffsetRegion() {
                return new OffsetRegion(span.getOffsetRegion().getStart(), 0);
            }

            @Override
            public SnapshotPositionRegion getSnapshotPositionRegion(boolean translateToOpenDocument) {
                return new SnapshotPositionRegion(span.getSnapshotPositionRegion(translateToOpenDocument).getStart(), 0);
            }
        };
    }

    @Override
    public CodeElementPositionRegion getSpan() {
        return null;
    }

    @Override
    public JTextComponent navigateTo() {
        CodeElementPositionRegion position = getSeek();
        if (position == null) {
            return null;
        }

        OpenAction openAction = new OpenAction(position.getFileObject(), position.getOffsetRegion().getStart());
        openAction.actionPerformed(new ActionEvent(this, 0, openAction.getValue(Action.NAME).toString()));
        return null;
    }

    protected final CodeModelCacheImpl getCodeModelCache() {
        return CodeModelCacheImpl.getInstance();
    }

    protected void freezeImpl() {
    }

    protected void ensureModifiable() {
        if (isFrozen()) {
            throw new IllegalStateException("The object is frozen.");
        }
    }
}
