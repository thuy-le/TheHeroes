package vn.edu.rmit.Utilities;

import javax.swing.*;
import javax.swing.plaf.metal.MetalScrollBarUI;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/30/13
 * Time: 9:04 PM
 */
public class MyScrollBarUI extends MetalScrollBarUI {

    private Image thumb, track;
    private int width;
    private int height;

    private JButton b = new JButton() {

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(0, 0);
        }

    };

    public MyScrollBarUI(int w, int h) {
        thumb = create(10, 10, Colour.BLUE_MIDDLE);
        track = create(10, 10, Colour.GREY_MIDDLE);
        this.width = w;
        this.height = h;
    }

    private Image create(int w, int h, Color c) {
        BufferedImage bi = new BufferedImage(
                w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setPaint(c);
        g2d.fillRect(0, 0, w, h);
        g2d.dispose();
        return bi;
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
        g.setColor(Colour.BLUE_MIDDLE);
        g.drawImage(thumb,
                r.x, r.y, r.width, r.height, null);
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
        g.drawImage(track,
                r.x, r.y, r.width, r.height, null);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return b;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return b;
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(width, height);
    }
}


