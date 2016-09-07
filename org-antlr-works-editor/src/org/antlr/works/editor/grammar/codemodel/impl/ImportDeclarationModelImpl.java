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
import java.util.Collections;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.grammar.codemodel.CodeElementPositionRegion;
import org.antlr.works.editor.grammar.codemodel.ImportDeclarationModel;
import org.netbeans.api.annotations.common.CheckForNull;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Sam Harwell
 */
public class ImportDeclarationModelImpl extends AbstractCodeElementModel implements ImportDeclarationModel {
    private final String target;

    private final OffsetRegion seek;
    private final OffsetRegion span;

    public ImportDeclarationModelImpl(String name, String target, FileModelImpl file, TerminalNode seek, ParserRuleContext span) {
        super(name, file);
        this.target = target;
        this.seek = getOffsetRegion(seek);
        this.span = getOffsetRegion(span);
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return Collections.emptyList();
    }

    @CheckForNull
    @Override
    public String getPath() {
        FileModelImpl fileModelImpl = getFile();
        if (fileModelImpl != null) {
            FileObject fileObject = fileModelImpl.getFileObject();
            if (fileObject != null) {
                FileObject importedFile = fileObject.getParent().getFileObject(getName() + ".g4");
                if (importedFile != null && importedFile.isData()) {
                    return importedFile.getPath();
                }
            }
        }

        return null;
    }

    @Override
    public CodeElementPositionRegion getSeek() {
        if (this.seek == null) {
            return super.getSeek();
        }

        return new CodeElementPositionRegionImpl(this, seek);
    }

    @Override
    public CodeElementPositionRegion getSpan() {
        if (this.span == null) {
            return super.getSpan();
        }

        return new CodeElementPositionRegionImpl(this, span);
    }
}
