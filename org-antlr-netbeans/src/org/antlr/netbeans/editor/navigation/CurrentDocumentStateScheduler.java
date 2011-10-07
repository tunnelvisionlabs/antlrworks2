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

package org.antlr.netbeans.editor.navigation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.api.lexer.TokenHierarchyEventType;
import org.netbeans.modules.parsing.api.Source;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;
import org.netbeans.spi.lexer.MutableTextInput;
import org.netbeans.spi.lexer.TokenHierarchyControl;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service=Scheduler.class)
public final class CurrentDocumentStateScheduler extends Scheduler {

    private final DocumentListener documentListener;
    private JTextComponent currentEditor;
    private Document currentDocument;
    private Source source;

    public CurrentDocumentStateScheduler() {
        setEditor(EditorRegistry.focusedComponent());
        EditorRegistry.addPropertyChangeListener(new AListener());
        documentListener = new ADocumentListener();
    }

    @Override
    public String toString() {
        return "CurrentDocumentStateScheduler";
    }

    protected void setEditor(JTextComponent editor) {
        if (editor != null) {
            Document document = editor.getDocument();
            if (currentDocument == document) {
                return;
            }

            if (currentDocument != null) {
                currentDocument.removeDocumentListener(documentListener);
            }

            currentDocument = document;
            currentDocument.addDocumentListener(documentListener);
            source = Source.create(currentDocument);
            schedule(source, new SchedulerEvent(this) {});
        } else {
            currentDocument = null;
            source = null;
            schedule(null, null);
        }
    }

    @Override
    protected SchedulerEvent createSchedulerEvent(SourceModificationEvent event) {
        if (event.getModifiedSource() == source) {
            return new SchedulerEvent(this) {};
        }

        return null;
    }

    private class AListener implements PropertyChangeListener {
        @Override
        public void propertyChange (PropertyChangeEvent evt) {
            if (evt.getPropertyName () == null ||
                evt.getPropertyName ().equals (EditorRegistry.FOCUSED_DOCUMENT_PROPERTY) ||
                evt.getPropertyName ().equals (EditorRegistry.FOCUS_GAINED_PROPERTY)
            ) {
                JTextComponent editor = EditorRegistry.focusedComponent ();
                if (editor == currentEditor) return;
                currentEditor = editor;
                if (currentEditor != null) {
                    Document document = currentEditor.getDocument ();
                    FileObject fileObject = getFileObject (document);
                    if (fileObject == null) {
//                        System.out.println("no file object for " + document);
                        return;
                    }
                }
                setEditor (currentEditor);
            }
            else if (evt.getPropertyName().equals(EditorRegistry.LAST_FOCUSED_REMOVED_PROPERTY)) {
                currentEditor = null;
                setEditor(null);
            }
        }

        private FileObject getFileObject(Document doc) {
            Object sdp = doc.getProperty(Document.StreamDescriptionProperty);
            if (sdp instanceof FileObject) {
                return (FileObject)sdp;
            }

            if (sdp instanceof DataObject) {
                return ((DataObject)sdp).getPrimaryFile();
            }

            return null;
        }
    }

    private class ADocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            invalidateDocument(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            invalidateDocument(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            invalidateDocument(e);
        }

        //XXX This is a serious hack to get around a bug
        @SuppressWarnings("unchecked")
        private void invalidateDocument(DocumentEvent e) {
            MutableTextInput input = (MutableTextInput)e.getDocument().getProperty(MutableTextInput.class);
            if (input == null) {
                return;
            }

            TokenHierarchyControl thc = input.tokenHierarchyControl();
            try {
                Field field = TokenHierarchyControl.class.getDeclaredField("operation");
                field.setAccessible(true);
                Object operation = field.get(thc);
                Method[] methods = operation.getClass().getDeclaredMethods();
                Method fireTokenHierarchyChanged = null;
                for (Method method : methods) {
                    if (method.getName().equals("fireTokenHierarchyChanged")) {
                        fireTokenHierarchyChanged = method;
                        break;
                    }
                }

                Class tokenHierarchyEventInfoClass = fireTokenHierarchyChanged.getParameterTypes()[0];
                Constructor tokenHierarchyEventInfoCtor = tokenHierarchyEventInfoClass.getConstructor(
                    operation.getClass(),
                    TokenHierarchyEventType.class,
                    Integer.TYPE,
                    Integer.TYPE,
                    CharSequence.class,
                    Integer.TYPE
                    );
                int modificationOffset = e.getOffset();
                int removedLength = 0;
                String removedText = "";
                int insertedLength = 0;
                Object tokenHierarchyEventInfo = tokenHierarchyEventInfoCtor.newInstance(operation, TokenHierarchyEventType.MODIFICATION, modificationOffset, removedLength, "", insertedLength);
                fireTokenHierarchyChanged.invoke(operation, tokenHierarchyEventInfo);
            } catch (IllegalAccessException ex) {
                Exceptions.printStackTrace(ex);
            } catch (InstantiationException ex) {
                Exceptions.printStackTrace(ex);
            } catch (InvocationTargetException ex) {
                Exceptions.printStackTrace(ex);
            } catch (NoSuchFieldException ex) {
                Exceptions.printStackTrace(ex);
            } catch (NoSuchMethodException ex) {
                Exceptions.printStackTrace(ex);
            } catch (SecurityException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

    }
}
