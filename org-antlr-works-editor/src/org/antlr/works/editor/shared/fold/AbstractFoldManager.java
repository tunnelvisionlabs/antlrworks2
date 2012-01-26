/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
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
package org.antlr.works.editor.shared.fold;

import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;
import org.netbeans.api.editor.fold.Fold;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.netbeans.spi.editor.fold.FoldHierarchyTransaction;
import org.netbeans.spi.editor.fold.FoldManager;
import org.netbeans.spi.editor.fold.FoldOperation;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;

/**
 *
 * @author Sam Harwell
 */
public class AbstractFoldManager implements FoldManager {
    private static final Map<DataObject, AbstractFoldManager> managers =
        new WeakHashMap<DataObject, AbstractFoldManager>();

    private FoldOperation operation;
    final ArrayList<Fold> currentFolds = new ArrayList<Fold>();

    public static AbstractFoldManager getFoldManager(FileObject file) {
        try {
            DataObject dataObject = DataObject.find(file);
            synchronized (managers) {
                AbstractFoldManager manager = managers.get(dataObject);
                return manager;
            }
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        }
    }

    public FoldOperation getOperation() {
        return operation;
    }

    @Override
    public void init(FoldOperation operation) {
        this.operation = operation;

        DataObject dataObject = NbEditorUtilities.getDataObject(operation.getHierarchy().getComponent().getDocument());
        if (dataObject != null) {
            synchronized (managers) {
                managers.put(dataObject, this);
            }
        }

    }

    @Override
    public void initFolds(FoldHierarchyTransaction transaction) {
    }

    @Override
    public void insertUpdate(DocumentEvent evt, FoldHierarchyTransaction transaction) {
    }

    @Override
    public void removeUpdate(DocumentEvent evt, FoldHierarchyTransaction transaction) {
    }

    @Override
    public void changedUpdate(DocumentEvent evt, FoldHierarchyTransaction transaction) {
    }

    @Override
    public void removeEmptyNotify(Fold emptyFold) {
        removeDamagedNotify(emptyFold);
    }

    @Override
    public void removeDamagedNotify(Fold damagedFold) {
        currentFolds.remove(damagedFold);
    }

    @Override
    public void expandNotify(Fold expandedFold) {
    }

    @Override
    public void release() {
        operation = null;
        currentFolds.clear();
    }
}
