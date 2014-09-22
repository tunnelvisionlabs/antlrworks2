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
import org.antlr.works.editor.grammar.codemodel.LexerRuleModel;
import org.antlr.works.editor.grammar.codemodel.ModeModel;
import org.antlr.works.editor.grammar.codemodel.RuleModel;
import org.antlr.works.editor.grammar.codemodel.TokenData;
import org.antlr.works.editor.grammar.codemodel.TokenVocabModel;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public class FileVocabModelImpl extends AbstractTokenVocabModel {

    public FileVocabModelImpl(@NonNull FileModelImpl file) {
        super(file.getName(), file);
    }

    @Override
    @NonNull
    public FileModelImpl getFile() {
        FileModelImpl fileModel = super.getFile();
        assert fileModel != null;
        return fileModel;
    }

    @Override
    public Collection<? extends TokenData> getTokens() {
        Map<String, TokenData> data = new HashMap<>();

        // imports
        for (ImportDeclarationModelImpl importDecl : getFile().getImportDeclarations()) {
            for (PackageModelImpl packageModel : CodeModelCacheImpl.getInstance().resolvePackages(importDecl)) {
                for (FileModelImpl fileModel : packageModel.getFiles()) {
                    for (TokenData tokenData : fileModel.getVocabulary().getTokens()) {
                        data.put(tokenData.getName(), tokenData);
                    }
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
        for (RuleModel rule : getFile().getRules()) {
            if (!(rule instanceof LexerRuleModel)) {
                continue;
            }

            TokenData tokenData = ((LexerRuleModel)rule).getTokenData();
            if (tokenData != null) {
                data.put(tokenData.getName(), tokenData);
            }
        }

        // rules in modes in the current grammar
        for (ModeModel mode : getFile().getModes()) {
            for (RuleModel rule : mode.getRules()) {
                if (!(rule instanceof LexerRuleModel)) {
                    continue;
                }

                TokenData tokenData = ((LexerRuleModel)rule).getTokenData();
                if (tokenData != null) {
                    data.put(tokenData.getName(), tokenData);
                }
            }
        }

        return data.values();
    }

}
