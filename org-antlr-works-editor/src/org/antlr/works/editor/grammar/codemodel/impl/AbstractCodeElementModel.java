/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codemodel.impl;

import java.util.Collection;
import org.antlr.works.editor.grammar.codemodel.CodeElementModel;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.project.Project;
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
