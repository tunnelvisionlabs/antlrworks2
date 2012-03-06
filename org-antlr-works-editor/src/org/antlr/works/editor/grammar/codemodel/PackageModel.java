/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codemodel;

import java.util.Collection;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.project.Project;

/**
 *
 * @author Sam Harwell
 */
public interface PackageModel extends CodeElementModel {

    Project getProject();

    @NonNull
    Collection<? extends FileModel> getFiles();

    @NonNull
    Collection<? extends TokenVocabModel> getVocabularies();

}
