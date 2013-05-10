/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codemodel.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.project.Project;
import org.openide.util.Exceptions;

/**
 *
 * @author Sam Harwell
 */
public class CodeModelProjectCache {
    @NullAllowed
    private final Project project;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Map<String, FileModelImpl> files = new HashMap<>();
    private final Set<PackageModelImpl> packages = new HashSet<>();
    private final Map<String, PackageModelImpl> packagesByPath = new HashMap<>();
    private final Map<String, Collection<PackageModelImpl>> packagesByName = new HashMap<>();

    public CodeModelProjectCache(@NullAllowed Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    @NonNull
    public Collection<PackageModelImpl> getPackages() {
        return lockedRead(new Callable<Collection<PackageModelImpl>>() {

            @Override
            public Collection<PackageModelImpl> call() throws Exception {
                return new ArrayList<>(packages);
            }

        });
    }

    @NonNull
    public Collection<PackageModelImpl> getPackages(final String name) {
        return lockedRead(new Callable<Collection<PackageModelImpl>>() {

            @Override
            public Collection<PackageModelImpl> call() throws Exception {
                Collection<? extends PackageModelImpl> model = packagesByName.get(name);
                if (model == null) {
                    return Collections.emptyList();
                }

                return new ArrayList<>(model);
            }

        });
    }

    @CheckForNull
    public PackageModelImpl getUniquePackage(final String path) {
        return lockedRead(new Callable<PackageModelImpl>() {

            @Override
            public PackageModelImpl call() throws Exception {
                return packagesByPath.get(path);
            }

        });
    }

    public void updateFile(@NonNull final FileModelImpl fileModel) {
        assert fileModel.isFrozen();
        assert fileModel.getProject() == getProject();

        lockedWrite(new Runnable() {

            @Override
            public void run() {
                files.put(fileModel.getName(), fileModel);

                String packagePath = fileModel.getPackagePath();
                PackageModelImpl packageModel = getUniquePackage(packagePath);
                if (packageModel == null) {
                    String packageName = packagePath.substring(packagePath.lastIndexOf('/') + 1);
                    packageModel = new PackageModelImpl(packageName, project, packagePath);
                    packages.add(packageModel);
                    packagesByPath.put(packagePath, packageModel);

                    Collection<PackageModelImpl> set = packagesByName.get(packageName);
                    if (set == null) {
                        set = new HashSet<>();
                        packagesByName.put(packageName, set);
                    }

                    set.add(packageModel);
                }

                packageModel.updateFile(fileModel);
            }

        });
    }

    protected <T> T lockedRead(Callable<T> runnable) {
        Lock readLock = lock.readLock();
        boolean locked = false;
        try {
            readLock.lock();
            locked = true;
            return runnable.call();
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
            throw new RuntimeException(ex);
        } finally {
            if (locked) {
                readLock.unlock();
            }
        }
    }

    protected void lockedWrite(Runnable runnable) {
        Lock writeLock = lock.writeLock();
        boolean locked = false;
        try {
            writeLock.lock();
            locked = true;
            runnable.run();
        } finally {
            if (locked) {
                writeLock.unlock();
            }
        }
    }

}
