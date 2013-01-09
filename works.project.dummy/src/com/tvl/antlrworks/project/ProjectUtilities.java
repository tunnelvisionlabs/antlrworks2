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

import java.io.CharConversionException;
import java.io.File;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle.Messages;
import org.openide.xml.XMLUtil;

/** The util methods for projectui module.
 *
 * @author  Jiri Rechtacek
 */
public class ProjectUtilities {
    
    /** Checks if the given file name can be created in the target folder.
     *
     * @param targetFolder target folder (e.g. source group)
     * @param folderName name of the folder relative to target folder (null or /-separated)
     * @param newObjectName name of created file
     * @param extension extension of created file
     * @param allowFileSeparator if '/' (and possibly other file separator, see {@link FileUtil#createFolder FileUtil#createFolder})
     *                           is allowed in the newObjectName
     * @return localized error message (HTML-safe) or null if all right
     */    
    @Messages({
        "# {0} - name of the file", "# {1} - an integer representing the invalid characters:", "#       0: both '/' and '\\' are invalid", "#       1: '\\' is invalid", "MSG_not_valid_filename=The filename {0} is not permitted as it contains {1,choice,0#a slash (/) or a backslash (\\)|1#a backslash (\\)}.",
        "# {0} - name of the file", "# {1} - an integer representing the invalid characters:", "#       0: both '/' and '\\' are invalid", "#       1: '\\' is invalid", "MSG_not_valid_folder=The folder name {0} is not permitted as it contains {1,choice,0#a slash (/) or a backslash (\\)|1#a backslash (\\)}.",
        "MSG_fs_or_folder_does_not_exist=The target folder does not exist.",
        "MSG_fs_is_readonly=The target folder is read-only.",
        "# {0} - name of the existing file", "MSG_file_already_exist=The file {0} already exists."
    })
    public static String canUseFileName (FileObject targetFolder, String folderName, String newObjectName,
            String extension, boolean allowFileSeparator, boolean freeFileExtension) {
        assert newObjectName != null; // SimpleTargetChooserPanel.isValid returns false if it is... XXX should it use an error label instead?

        boolean allowSlash = false;
        boolean allowBackslash = false;
        int errorVariant = 0;
        
        if (allowFileSeparator) {
            if (File.separatorChar == '\\') {
                errorVariant = 3;
                allowSlash = allowBackslash = true;
            } else {
                errorVariant = 1;
                allowSlash = true;
            }
        }
        
        if ((!allowSlash && newObjectName.indexOf('/') != -1) || (!allowBackslash && newObjectName.indexOf('\\') != -1)) {
            //if errorVariant == 3, the test above should never be true:
            assert errorVariant == 0 || errorVariant == 1 : "Invalid error variant: " + errorVariant;
            
            return Bundle.MSG_not_valid_filename(safeEncode(newObjectName), errorVariant);
        }
        
        // test whether the selected folder on selected filesystem already exists
        if (targetFolder == null) {
            if (folderName == null || folderName.isEmpty()) {
                return Bundle.MSG_fs_or_folder_does_not_exist();
            }

            targetFolder = FileUtil.toFileObject(new File(folderName));
            if (targetFolder == null) {
                return Bundle.MSG_fs_or_folder_does_not_exist();
            }

            folderName = "";
        }
        
        // target directory should be writable
        File targetDir = folderName != null ? new File (FileUtil.toFile (targetFolder), folderName) : FileUtil.toFile (targetFolder);
        if (targetDir != null) {
            if (targetDir.exists () && ! targetDir.canWrite ()) {
                return Bundle.MSG_fs_is_readonly();
            }
        } else if (! targetFolder.canWrite ()) {
            return Bundle.MSG_fs_is_readonly();
        }

        // file should not already exist
        StringBuilder relFileName = new StringBuilder();
        if (folderName != null) {
            if (!allowBackslash && folderName.indexOf('\\') != -1) {
                return Bundle.MSG_not_valid_folder(safeEncode(folderName), 1);
            }
            relFileName.append(folderName);
            relFileName.append('/');
        }
        relFileName.append(newObjectName);
        String ext = "";
        if (extension != null && extension.length() != 0 && (!freeFileExtension || newObjectName.indexOf('.') == -1)) {
            ext = "." + extension;
            relFileName.append(ext);
        }
        if (targetFolder.getFileObject(relFileName.toString()) != null) {
            return Bundle.MSG_file_already_exist(safeEncode(newObjectName + ext));
        }
        
        // all ok
        return null;
    }
    private static String safeEncode(String text) { // #208432
        if (text.length() > 30) {
            text = text.substring(0, 30) + '…';
        }
        try {
            return XMLUtil.toElementContent(text.replaceAll("\\s+", " "));
        } catch (CharConversionException ex) {
            return text;
        }
    }
}
