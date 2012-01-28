/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
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
package org.antlr.works.editor.st4.fold;

import java.util.ArrayList;
import java.util.List;
import org.antlr.netbeans.editor.fold.AbstractFoldScanner;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.works.editor.st4.parser.CompiledFileModel;
import org.antlr.works.editor.st4.parser.CompiledModel;
import org.antlr.works.editor.st4.parser.TemplateGroupRuleReturnScope;
import org.antlr.works.editor.st4.parser.TemplateGroupWrapper;
import org.stringtemplate.v4.ST.RegionType;
import org.stringtemplate.v4.compiler.CompiledST;
import org.stringtemplate.v4.misc.Interval;

/**
 *
 * @author Sam Harwell
 */
public class TemplateFoldScanner extends AbstractFoldScanner<CompiledModel> {

    @Override
    protected List<FoldInfo> calculateFolds(ParserData<CompiledModel> result) {
        DocumentSnapshot snapshot = result.getSnapshot();

        final List<FoldInfo> folds = new ArrayList<FoldInfo>();
        CompiledFileModel fileModel = result.getData().getResult();
        TemplateGroupRuleReturnScope parseResult = fileModel.getResult();
        if (parseResult == null) {
            return folds;
        }

        for (TemplateGroupWrapper.TemplateInformation templateInfo : parseResult.getGroup().getTemplateInformation()) {
            CompiledST template = templateInfo.getTemplate();
            if (template.isAnonSubtemplate) {
                continue;
            }

            if (template.isRegion && template.regionDefType != RegionType.EXPLICIT) {
                continue;
            }

            Interval sourceInterval = templateInfo.getGroupInterval();
            int startLine = snapshot.findLineNumber(sourceInterval.a);
            int stopLine = snapshot.findLineNumber(sourceInterval.b);
            if (startLine >= stopLine) {
                continue;
            }

            SnapshotPositionRegion region = new SnapshotPositionRegion(snapshot, OffsetRegion.fromBounds(sourceInterval.a, sourceInterval.b + 1));
            FoldInfo info = new FoldInfo(region, "...");
            folds.add(info);
        }

        return folds;
    }

    private static FoldInfo createFold(CommonTree child, String blockHint, DocumentSnapshot snapshot, CommonToken[] tokens) {
        CommonToken startToken = tokens[child.getTokenStartIndex()];
        CommonToken stopToken = tokens[child.getTokenStopIndex()];

        int startLine = snapshot.findLineNumber(startToken.getStartIndex());
        int stopLine = snapshot.findLineNumber(stopToken.getStopIndex());
        if (startLine >= stopLine) {
            return null;
        }

        SnapshotPositionRegion region = new SnapshotPositionRegion(snapshot, OffsetRegion.fromBounds(startToken.getStartIndex(), stopToken.getStopIndex() + 1));
        FoldInfo fold = new FoldInfo(region, blockHint);
        return fold;
    }

}
