/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.semantics;

import java.util.WeakHashMap;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.editor.errorstripe.privatespi.MarkProviderCreator;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=MarkProviderCreator.class)
public class MarkOccurrencesMarkProviderCreator implements MarkProviderCreator {

    private final Object lock = new Object();

    @Override
    public MarkOccurrencesMarkProvider createMarkProvider(JTextComponent component) {
        synchronized (lock) {
            Document document = component.getDocument();
            ProviderMap providerMap = (ProviderMap)document.getProperty(MarkOccurrencesMarkProvider.class);
            if (providerMap == null) {
                providerMap = new ProviderMap();
                document.putProperty(MarkOccurrencesMarkProvider.class, providerMap);
            }

            MarkOccurrencesMarkProvider provider = providerMap.get(component);
            if (provider == null) {
                provider = new MarkOccurrencesMarkProvider();
                providerMap.put(component, provider);
            }

            return provider;
        }
    }

    private static class ProviderMap extends WeakHashMap<JTextComponent, MarkOccurrencesMarkProvider> {
    }

}
