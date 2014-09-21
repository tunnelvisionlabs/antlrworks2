/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codemodel.impl;

import java.util.Arrays;
import java.util.Collection;
import org.antlr.works.editor.grammar.codemodel.ChannelModel;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.codemodel.ModeModel;
import org.antlr.works.editor.grammar.codemodel.RuleModel;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Sam Harwell
 */
public class FileModelImpl extends AbstractCodeElementModel implements FileModel {
    @NullAllowed
    private final FileObject fileObject;
    @NonNull
    private final FreezableArrayList<ImportDeclarationModelImpl> importDeclarations = new FreezableArrayList<>();
    @NonNull
    private final FreezableArrayList<TokenVocabDeclarationModelImpl> tokenVocabDeclarations = new FreezableArrayList<>();
    @NonNull
    private final FreezableArrayList<ChannelModelImpl> channels = new FreezableArrayList<>();
    @NonNull
    private final FreezableArrayList<ModeModelImpl> modes = new FreezableArrayList<>();
    @NonNull
    private final FreezableArrayList<RuleModelImpl> rules = new FreezableArrayList<>();
    @NonNull
    private final ProxyCollection<AbstractCodeElementModel> codeElements = new ProxyCollection<>(Arrays.asList(importDeclarations, tokenVocabDeclarations, channels, modes, rules));

    public FileModelImpl(@NonNull FileObject fileObject, @NullAllowed Project project, @NonNull String packagePath) {
        super(fileObject.getNameExt(), project, packagePath);
        this.fileObject = fileObject;
    }

    public FileModelImpl(String name, Project project, String packagePath) {
        super(name, project, packagePath);
        this.fileObject = null;
    }

    public FileObject getFileObject() {
        return fileObject;
    }

    @Override
    public Collection<ImportDeclarationModelImpl> getImportDeclarations() {
        return importDeclarations;
    }

    @Override
    public Collection<TokenVocabDeclarationModelImpl> getTokenVocabDeclaration() {
        return tokenVocabDeclarations;
    }

    @NonNull
    @Override
    public Collection<ChannelModelImpl> getChannels() {
        return channels;
    }

    @NonNull
    @Override
    public Collection<? extends ChannelModel> getChannels(String name) {
        return CodeModelCacheImpl.findElementsByName(getChannels(), name);
    }

    @NonNull
    @Override
    public Collection<ModeModelImpl> getModes() {
        return modes;
    }

    @NonNull
    @Override
    public Collection<? extends ModeModel> getModes(String name) {
        return CodeModelCacheImpl.findElementsByName(getModes(), name);
    }

    @NonNull
    @Override
    public Collection<RuleModelImpl> getRules() {
        return rules;
    }

    @NonNull
    @Override
    public Collection<? extends RuleModel> getRules(String name) {
        return CodeModelCacheImpl.findElementsByName(getRules(), name);
    }

    @NonNull
    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return codeElements;
    }

    @NonNull
    @Override
    public FileVocabModelImpl getVocabulary() {
        return new FileVocabModelImpl(this);
    }

    @Override
    protected void freezeImpl() {
        importDeclarations.freeze();
        tokenVocabDeclarations.freeze();
        channels.freeze();
        modes.freeze();
        rules.freeze();
        super.freezeImpl();
    }

}
