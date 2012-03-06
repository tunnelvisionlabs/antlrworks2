/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codemodel.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.antlr.works.editor.grammar.codemodel.RuleKind;
import org.antlr.works.editor.grammar.codemodel.TokenData;
import org.antlr.works.editor.grammar.codemodel.TokenVocabModel;

/**
 *
 * @author Sam Harwell
 */
public class FileVocabModelImpl extends AbstractTokenVocabModel {

    public FileVocabModelImpl(FileModelImpl file) {
        super(file.getName(), file);
    }

    @Override
    public Collection<? extends TokenData> getTokens() {
        Map<String, TokenData> data = new HashMap<String, TokenData>();

        // imports
        for (ImportDeclarationModelImpl importDecl : getFile().getImportDeclarations()) {
            for (FileModelImpl fileModel : CodeModelCacheImpl.getInstance().resolvePackages(importDecl)) {
                for (TokenData tokenData : fileModel.getVocabulary().getTokens()) {
                    data.put(tokenData.getName(), tokenData);
                }
            }
        }

        // tokenVocab option
        for (TokenVocabDeclarationModelImpl tokenVocabDecl : getFile().getTokenVocabDeclaration()) {
            for (TokenVocabModel tokenVocab : tokenVocabDecl.resolve()) {
                for (TokenData tokenData : tokenVocab.getTokens()) {
                    data.put(tokenData.getName(), tokenData);
                }
            }
        }

        // rules in the current grammar
        for (RuleModelImpl rule : getFile().getRules()) {
            if (rule instanceof TokenData) {
                TokenData tokenData = (TokenData)rule;
                data.put(tokenData.getName(), tokenData);
                continue;
            }

            if (rule.getRuleKind() == RuleKind.LEXER) {
                data.put(rule.getName(), new TokenDataImpl(rule.getName(), null, rule.getFile()));
            }
        }

        return data.values();
    }

}
