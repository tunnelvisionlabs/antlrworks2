/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.navigation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.ref.WeakReference;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.UIManager;

public final class CollapsiblePanel extends JPanel {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final String EXPANDED_PROPERTY = "CollapsiblePanel.expanded";
    public static final String PROP_ORIENTATION = "orientation";

    private static WeakReference<MouseAdapterImpl> mouseAdapter;

    private int orientation;
    private boolean armed;
    private boolean expanded;
    private final int minimumHeight = 8;

    private final Icon up = new UpIcon ();
    private final Icon down = new DownIcon ();

    private final int ICON_SIZE = 8;

    public CollapsiblePanel() {
        setLayout(new TrivialLayout());
    }

    private static MouseAdapterImpl getMouseAdapter() {
        MouseAdapterImpl result = null;
        if (mouseAdapter != null) {
            result = mouseAdapter.get();
        }

        if (result == null) {
            result = new MouseAdapterImpl();
            mouseAdapter = new WeakReference<>(result);
        }

        return result;
    }

    private void setSecondaryMouseListener(MouseListener listener) {
        getMouseAdapter().setSecondaryListener(listener);
    }

    @Override
    public void addNotify() {
        addMouseMotionListener(getMouseAdapter());
        addMouseListener(getMouseAdapter());
        super.addNotify();
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        removeMouseMotionListener(getMouseAdapter());
        removeMouseListener(getMouseAdapter());
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int value) {
        if (orientation == value) {
            return;
        }

        int oldValue = orientation;
        orientation = value;
        firePropertyChange(PROP_ORIENTATION, oldValue, value);
    }

    private void setArmed(boolean value) {
        if (armed == value)
            return;

        armed = value;
        repaint();
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean value) {
        if (expanded == value) {
            return;
        }

        Dimension dimension = getPreferredSize();
        expanded = value;
        Dimension dimension2 = getPreferredSize();
        if (isDisplayable()) {
            revalidate();
        }

        firePropertyChange(EXPANDED_PROPERTY, !expanded, expanded);
    }

    @Override
    public Dimension getPreferredSize() {
        return getLayout().preferredLayoutSize(this);
    }

    @Override
    public Dimension getMinimumSize() {
        Dimension d = getPreferredSize();
        d.width = 20;
        return d;
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }


    private boolean isArmPoint(Point p) {
        if ( !expanded ) {
            return p.y > 0 && p.y < getHeight ();
        } else {
            if ( orientation == UP ) {
                return p.y > getHeight () - minimumHeight;
            } else {
                return p.y < minimumHeight;
            }
        }
    }

    public void updateBorder () {
        if ( orientation == UP ) {
            super.setBorder ( BorderFactory.createEmptyBorder ( 0, 0, minimumHeight, 0 ) );
        } else {
            super.setBorder ( BorderFactory.createEmptyBorder ( minimumHeight, 0, 0, 0 ) );
        }
    }

    public int getMinimumHeight () {
        return minimumHeight;
    }

    public void setBorder () {
        //do nothing
    }

    @Override
    public void paintBorder (Graphics g) {
        Color c = armed ? UIManager.getColor ( "List.selectionBackground" ) : getBackground (); //NOI18N
        if (c==null) {
            c=getBackground();
        }
        int x = 0;
        int y = orientation == UP ? 1 + ( getHeight () - minimumHeight ) : 0;
        int w = getWidth ();
        int h = minimumHeight - 1;
        g.setColor ( c );
        g.fillRect ( x, y, w, h );

        int pos = orientation == UP ? getHeight () - 1 : 0;
        int dir = orientation == UP ? -1 : 1;
        g.setColor ( armed ? c.darker () : UIManager.getColor ( "controlShadow" ) ); //NOI18N
        g.drawLine ( 0, pos, w, pos );
        pos += dir;

        if ( ( orientation == UP ) == expanded ) {
            up.paintIcon ( this, g, ( getWidth () / 2 ) - ( up.getIconWidth () / 2 ),
                    getHeight () - ( minimumHeight + ( expanded ? 0 : -1 ) ) );
        } else {
            down.paintIcon ( this, g, ( getWidth () / 2 ) - ( up.getIconWidth () / 2 ), expanded ? 2 : 1 );
        }
    }

    @Override
    public void paintChildren (Graphics g) {
        if ( !expanded ) return;
        super.paintChildren(g);
    }

    private static class MouseAdapterImpl extends MouseAdapter implements MouseMotionListener {
        private MouseListener secondaryListener;

        public void setSecondaryListener(MouseListener value) {
            secondaryListener = value;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ((CollapsiblePanel)e.getSource()).setArmed(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ((CollapsiblePanel)e.getSource()).setArmed(false);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            ((CollapsiblePanel)e.getSource()).setArmed(((CollapsiblePanel)e.getSource()).isArmPoint(e.getPoint()));
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (((CollapsiblePanel)e.getSource()).isArmPoint(e.getPoint())) {
                ((CollapsiblePanel)e.getSource()).setExpanded(!((CollapsiblePanel)e.getSource()).isExpanded());
                e.consume();
            } else if (secondaryListener != null) {
                secondaryListener.mousePressed(e);
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // do nothing
        }
    }

    private class UpIcon implements Icon {
        @Override
        public int getIconHeight () {
            return ICON_SIZE - 2;
        }

        @Override
        public int getIconWidth () {
            return ICON_SIZE + 2;
        }

        @Override
        public void paintIcon (java.awt.Component c, Graphics g, int x, int y) {

            g.setColor ( armed ?
                    UIManager.getColor ( "List.selectionForeground" ) : //NOI18N
                    UIManager.getColor ( "controlShadow" ) ); //NOI18N
/*            int[] xPoints = new int[] {x+getIconWidth()/2, x+getIconWidth(), x};
            int[] yPoints = new int[] {y, y+getIconHeight()-1, y+getIconHeight()-1};
 */
            int[] xPoints = new int[]{x, x + 8, x + 4};
            int[] yPoints = new int[]{y + 5, y + 5, y};

            g.fillPolygon ( xPoints, yPoints, 3 );
        }
    }

    private class DownIcon implements Icon {

        @Override
        public int getIconHeight () {
            return ICON_SIZE - 3;
        }

        @Override
        public int getIconWidth () {
            return ICON_SIZE + 2;
        }

        @Override
        public void paintIcon (java.awt.Component c, Graphics g, int x, int y) {
            x++;
            g.setColor ( armed ?
                    UIManager.getColor ( "List.selectionForeground" ) : //NOI18N
                    UIManager.getColor ( "controlShadow" ) ); //NOI18N
            /*
            int[] xPoints = new int[] {(x+getIconWidth()/2), x+getIconWidth()-1, x};
            int[] yPoints = new int[] {y+getIconHeight()-2, y, y};
             */
            int[] xPoints = new int[]{x, x + 8, x + 4};
            int[] yPoints = new int[]{y, y, y + 4};

            g.fillPolygon ( xPoints, yPoints, 3 );
        }
    }
}
