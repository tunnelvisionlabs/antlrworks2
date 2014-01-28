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
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public interface RuleModel extends CodeElementModel {

    @NonNull
    RuleKind getRuleKind();

    @CheckForNull
    ModeModel getMode();

    @NonNull
    Collection<? extends ParameterModel> getParameters();

    @NonNull
    Collection<? extends ParameterModel> getParameters(String name);

    @NonNull
    Collection<? extends ParameterModel> getReturnValues();

    @NonNull
    Collection<? extends ParameterModel> getReturnValues(String name);

    @NonNull
    Collection<? extends ParameterModel> getLocals();

    @NonNull
    Collection<? extends ParameterModel> getLocals(String name);

    @NonNull
    Collection<? extends LabelModel> getLabels();

    @NonNull
    Collection<? extends LabelModel> getLabels(String name);

    public boolean hasExplicitEof();

}
