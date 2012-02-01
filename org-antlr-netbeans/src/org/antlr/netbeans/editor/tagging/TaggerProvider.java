/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.tagging;

import org.antlr.netbeans.editor.text.VersionedDocument;

/**
 *
 * @author Sam Harwell
 */
public interface TaggerProvider {

    <T extends Tag> Tagger<T> createTagger(Class<T> tagType, VersionedDocument document);

}
