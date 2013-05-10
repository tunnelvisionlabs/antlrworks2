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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.works.editor.grammar.codemodel.PackageModel;
import org.antlr.works.editor.grammar.codemodel.TokenVocabModel;
import org.netbeans.api.project.Project;

/**
 *
 * @author Sam Harwell
 */
public class PackageModelImpl extends AbstractCodeElementModel implements PackageModel {
    private final Map<String, FileModelImpl> files = new HashMap<>();

    public PackageModelImpl(String name, Project project, String path) {
        super(name, project, path);
    }

    @Override
    public PackageModelImpl getPackage() {
        return this;
    }

    @Override
    public Collection<FileModelImpl> getFiles() {
        return files.values();
    }

    public void updateFile(FileModelImpl fileModel) {
        files.put(fileModel.getName(), fileModel);
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        List<AbstractCodeElementModel> members = new ArrayList<>();
        for (FileModelImpl file : getFiles()) {
            members.addAll(file.getMembers());
        }

        return members;
    }

    @Override
    public Collection<? extends TokenVocabModel> getVocabularies() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
