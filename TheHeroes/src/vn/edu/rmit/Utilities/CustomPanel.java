package vn.edu.rmit.Utilities;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/15/13
 * Time: 2:19 PM
 */

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

public class CustomPanel extends JPanel {
    //declare variables
    private Color background, strokecolor;
    private int x;
    private int y;
    private int width;
    private int height;
    private float alpha;
    private int arcW;
    private int arcH;
    private Stroke stroke;
    private int strokeWidth;

    public CustomPanel() {
        this.x = 0;
        this.y = 0;
        this.width = super.getWidth();
        this.height = super.getHeight();
        this.alpha = .5f;
        this.background = Colour.GREEN_DARK;
        this.arcW = 0;
        this.arcH = 0;
        this.strokeWidth = 1;
        setOpaque(false);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        repaint();
    }

    public Point getPosition(){
        return new Point(x,y);
    }

    public void setPanelSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Dimension getPanelSize() {
        return new Dimension(width, height);
    }

    public void setARC(int arcW, int arcH) {
        this.arcW = arcW;
        this.arcH = arcH;
        repaint();
    }

    public int getARCW() {
        return this.arcW;
    }

    public int getARCH() {
        return this.arcH;
    }

    public void setAlpha(float a) {
        this.alpha = a;
        repaint();
    }

    public void setBackground(Color c) {
        this.background = c;
        repaint();
    }

    public void setStroke(int width, Color c) {
        strokeWidth = width;
        this.strokecolor = c;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(x, y);
        stroke = new BasicStroke(strokeWidth);
        g2d.setStroke(stroke);
        g2d.setColor(strokecolor);
        g2d.drawRoundRect(x, y, width + strokeWidth, height + strokeWidth, arcW, arcH);
        if (background != null) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.setColor(background);
            g2d.fillRoundRect(x + strokeWidth, y + strokeWidth, width, height, arcW, arcH);
        }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }

    public void setPanelPosition(final int x, final int y) {
        this.x = x;
        this.y = y;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width + x + strokeWidth +1, height + y + strokeWidth +1);
    }
}
