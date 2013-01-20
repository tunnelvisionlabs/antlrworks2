/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.util;

import javax.swing.Icon;
import org.netbeans.api.annotations.common.StaticResource;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Sam Harwell
 */
public abstract class NotificationIcons {
    public static final Icon ERROR;
    public static final Icon WARNING;
    public static final Icon INFO;

    @StaticResource
    private static final String ERROR_IMAGE = "org/antlr/netbeans/util/resources/errorIcon.png";
    @StaticResource
    private static final String WARNING_IMAGE = "org/antlr/netbeans/util/resources/warningIcon.png";
    @StaticResource
    private static final String INFO_IMAGE = "org/antlr/netbeans/util/resources/infoIcon.png";

    static {
        ERROR = ImageUtilities.image2Icon(ImageUtilities.loadImage(ERROR_IMAGE));
        WARNING = ImageUtilities.image2Icon(ImageUtilities.loadImage(WARNING_IMAGE));
        INFO = ImageUtilities.image2Icon(ImageUtilities.loadImage(INFO_IMAGE));
    }

    private NotificationIcons() {
    }
}
