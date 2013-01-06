/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

import com.tvl.spi.editor.completion.CompletionController;
import com.tvl.spi.editor.completion.CompletionControllerProvider;
import com.tvl.spi.editor.completion.CompletionTask;
import java.util.List;
import javax.swing.text.JTextComponent;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=CompletionControllerProvider.class)
public class GrammarCompletionControllerProvider implements CompletionControllerProvider {

    @Override
    public CompletionController createController(JTextComponent component, List<? extends CompletionTask> tasks, List<Integer> queryTypes) {
        return new GrammarCompletionController(component, tasks, queryTypes);
    }

}
