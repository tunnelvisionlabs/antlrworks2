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

package com.tvl.antlrworks.project;

import java.util.prefs.Preferences;
import org.netbeans.modules.project.ui.groups.Group;
import org.openide.util.NbPreferences;

/** SystemOption to store the list of open projects
 */
public class OpenProjectListSettings {

    private static OpenProjectListSettings INSTANCE = new OpenProjectListSettings();
    
    private static final String PROP_PROJECT_CATEGORY = "lastSelectedProjectCategory"; //NOI18N - String
    
    private OpenProjectListSettings() {
    }
    
    public static OpenProjectListSettings getInstance() {
        return INSTANCE;
    }

    private String getGroupedProperty(String key) {
        return getPreferences(true).get(key, null);
    }

    private String putGroupedProperty(String key, String value, boolean notify) {
        Preferences prefs = getPreferences(true);
        String retval = prefs.get(key, null);
        if (value != null) {
            prefs.put(key, value);
        } else {
            prefs.remove(key);
        }
        return retval;
    }

    protected final Preferences getPreferences(boolean allowGrouped) {
        if (allowGrouped) {
            Group act = Group.getActiveGroup();
            if (act != null) {
                //TODO replace with NbPreferences.forModule()
                return act.prefs().node(OpenProjectListSettings.class.getPackage().getName().replace(".", "/"));
            }
        }
        return NbPreferences.forModule(OpenProjectListSettings.class);
    }

    public String getLastSelectedProjectCategory () {
        return getGroupedProperty(PROP_PROJECT_CATEGORY);
    }

    public void setLastSelectedProjectCategory (String category) {
        putGroupedProperty(PROP_PROJECT_CATEGORY,category,true);
    }
}
