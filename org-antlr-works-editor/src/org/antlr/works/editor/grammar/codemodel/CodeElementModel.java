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
import javax.swing.text.JTextComponent;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public interface CodeElementModel {

    @NonNull
    PackageModel getPackage();

    @NonNull
    String getName();

    @NonNull
    Collection<? extends CodeElementModel> getMembers();

    @NonNull
    Collection<? extends CodeElementModel> getMembers(String name);

    @CheckForNull
    CodeElementPositionRegion getSeek();

    @CheckForNull
    CodeElementPositionRegion getSpan();

    @CheckForNull
    JTextComponent navigateTo();

}
