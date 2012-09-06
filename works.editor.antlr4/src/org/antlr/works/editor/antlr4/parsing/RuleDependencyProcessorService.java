/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.parsing;

import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import org.antlr.v4.runtime.misc.RuleDependencyProcessor;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Sam Harwell
 */

@ServiceProvider(service=Processor.class)
@SupportedAnnotationTypes({"org.antlr.v4.runtime.RuleDependency", "org.antlr.v4.runtime.RuleDependencies"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class RuleDependencyProcessorService extends RuleDependencyProcessor {

}
