/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
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
package org.antlr.netbeans.parsing.spi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.parsing.spi.impl.CurrentDocumentParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.impl.CursorSensitiveParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.impl.DocumentContentParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.impl.SelectedNodesParserTaskScheduler;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.openide.util.Lookup;

/**
 *
 * @author Sam Harwell
 */
public abstract class ParserTaskScheduler {

    public static final Class<? extends ParserTaskScheduler> CONTENT_SENSITIVE_TASK_SCHEDULER =
        DocumentContentParserTaskScheduler.class;

    public static final Class<? extends ParserTaskScheduler> EDITOR_SENSITIVE_TASK_SCHEDULER =
        CurrentDocumentParserTaskScheduler.class;

    public static final Class<? extends ParserTaskScheduler> CURSOR_SENSITIVE_TASK_SCHEDULER =
        CursorSensitiveParserTaskScheduler.class;

    public static final Class<? extends ParserTaskScheduler> SELECTED_NODES_SENSITIVE_TASK_SCHEDULER =
        SelectedNodesParserTaskScheduler.class;

    protected void schedule(VersionedDocument document) {
        @SuppressWarnings("unchecked")
        Collection<? extends ParserDataDefinition<?>> mimeData = (Collection<? extends ParserDataDefinition<?>>)MimeLookup.getLookup(document.getMimeType()).lookupAll(ParserDataDefinition.class);
        List<ParserDataDefinition<?>> currentScheduledData = new ArrayList<ParserDataDefinition<?>>();
        for (ParserDataDefinition<?> data : mimeData) {
            if (getClass().isAssignableFrom(data.getScheduler())) {
                currentScheduledData.add(data);
            }
        }

        Collection<ScheduledFuture<ParserData<?>>> future = getTaskManager().schedule(document, currentScheduledData, getParseDelayMilliseconds(), TimeUnit.MILLISECONDS);
    }
    
    protected int getParseDelayMilliseconds() {
        return 500;
    }

    protected ParserTaskManager getTaskManager() {
        return Lookup.getDefault().lookup(ParserTaskManager.class);
    }
}
