/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.fold;

import java.util.ArrayList;
import java.util.List;
import org.antlr.netbeans.editor.fold.AbstractFoldScanner;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.parsing.spi.ParserData;
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

        final List<FoldInfo> folds = new ArrayList<>();
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

}
