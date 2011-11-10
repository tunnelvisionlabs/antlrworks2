/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.antlr.works.editor.grammar;

import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.completion.CompletionController;
import org.netbeans.spi.editor.completion.CompletionControllerProvider;
import org.netbeans.spi.editor.completion.CompletionTask;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=CompletionControllerProvider.class)
public class GrammarCompletionControllerProvider implements CompletionControllerProvider {

    @Override
    public CompletionController createController(JTextComponent component, CompletionTask task, int queryType) {
        return new GrammarCompletionController(component, task, queryType);
    }

}
