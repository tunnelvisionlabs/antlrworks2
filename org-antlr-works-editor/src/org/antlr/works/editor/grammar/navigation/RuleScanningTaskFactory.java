/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */

package org.antlr.works.editor.grammar.navigation;

import java.util.Collection;
import java.util.Collections;
import org.antlr.works.editor.grammar.parser.GrammarParser;
import org.antlr.works.editor.grammar.parser.GrammarParser.GrammarParserResult;
import org.antlr.works.editor.grammar.parser.GrammarParserV4;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.ParserResultTask;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.netbeans.modules.parsing.spi.SchedulerTask;
import org.netbeans.modules.parsing.spi.TaskFactory;

@MimeRegistration(mimeType="text/x-antlr3", service=TaskFactory.class)
public class RuleScanningTaskFactory extends TaskFactory {

    @Override
    public Collection<? extends SchedulerTask> create(Snapshot snapshot) {
        return Collections.singleton(new TaskSelector());
    }

    private static final class TaskSelector extends ParserResultTask<GrammarParser.GrammarParserResult> {

        private final RuleScanningTaskV3 v3 = new RuleScanningTaskV3();
        private final RuleScanningTaskV4 v4 = new RuleScanningTaskV4();

        @Override
        public void run(GrammarParserResult result, SchedulerEvent event) {
            if (result instanceof GrammarParserV4.GrammarParserResultV4) {
                v4.run(result, event);
            } else {
                v3.run(result, event);
            }
        }

        @Override
        public int getPriority() {
            return v4.getPriority();
        }

        @Override
        public Class<? extends Scheduler> getSchedulerClass() {
            return v4.getSchedulerClass();
        }

        @Override
        public void cancel() {
            v3.cancel();
            v4.cancel();
        }
    }
}
