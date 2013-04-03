package vn.edu.rmit.Utilities;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;


/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/9/13
 * Time: 8:23 PM
 */

public class CustomButton extends JPanel{
    private static final int ARCW = 5;
    private static final int ARCH = 5;
    private static final double BTN_WIDTH = 150;
    private static final double BTN_HEIGHT = 35;
    private Double x, y, width, height;
    private int arcH, arcW;
    private float alpha;
    private Color backgroundColor;
    private Color foregroundColor;
    private Color hoverBackground;
    private Color strokeColor;
    private String text;
    private boolean isActive;
    private Font font;
    private Stroke s;
    private float strokeSize;
    private Color temp;

    /**
     * Constructor
     * @param text
     */
    public CustomButton(String text) {
        this.x = 0.0;
        this.y = 0.0;
        this.width = BTN_WIDTH;
        this.height = BTN_HEIGHT;
        this.backgroundColor = Colour.BLUE_DARK;
        this.text = text;
        this.foregroundColor = Colour.BLUE_LIGHT;
        this.hoverBackground = Colour.BLUE_MIDDLE;
        this.strokeColor = Colour.BLUE_LIGHT;
        this.strokeSize = 2.5f;
        this.setOpaque(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.isActive = true;
        this.alpha = .6f;
        this.arcH = ARCH;
        this.arcW = ARCW;
        this.font = new Font("Century Gothic", Font.BOLD, 17);
        this.setFocusable(true);
        addMouseListener(new CustomButtonEvent(backgroundColor, hoverBackground));
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                temp = strokeColor;
                strokeColor = Colour.BLUE_MIDDLE.brighter();
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                strokeColor = temp;
                repaint();
            }
        });
    }

    /**
     * Set text
     * @param text
     */
    public void setText(String text) {
        this.text = text;
        repaint();
    }

    /**
     * Set button's border radius
     * @param arc
     */
    public void setArc(int arc){
        this.arcH = arc;
        repaint();
    }

    /**
     * Set size of the button.
     * @param width
     * @param height
     */
    public void setButtonSize(final double width, final double height) {
        this.width = width;
        this.height = height;
        repaint();
    }

    public double getButtonWidth(){
        return width;
    }

    public double getButtonHeight(){
        return height;
    }

    /**
     * Set position of the button.
     * @param x
     * @param y
     */
    public void setPosition(final double x, final double y){
        this.x = x;
        this.y = y;
        repaint();
    }

    /**
     * Disable or enable the button
     * @param isActive
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
        if(this.isActive)
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        else
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        repaint();
    }

    /**
     * Get button's status
     * @return true if the button is active
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Set button's transparency
     * @param a
     */
    public void setAlpha(float a){
        this.alpha = a;
        repaint();
    }

    /**
     * Set button text's font
     * @param f
     */
    public void setCustomFont(Font f){
        this.font = f;
        repaint();
    }

    /**
     * Set button's background color
     * @param backgroundColor
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }

    /**
     * Set button's hover background color
     * @param hoverBackground
     */
    public void setHoverBackground(Color hoverBackground) {
        addMouseListener(new CustomButtonEvent(backgroundColor, hoverBackground));
        this.hoverBackground = hoverBackground;
        repaint();
    }

    /**
     * Set button's foreground
     * @param c
     */
    public void setForegroundColor(Color c) {
        this.foregroundColor = c;
        repaint();
    }

    /**
     * Set stroke size
     * @param f
     */
    public void setStrokeSize(float f){
        this.strokeSize = f;
    }

    /**
     * Set button's stroke
     * @param s
     */
    public void setStroke(Stroke s, Color c){
        this.s = s;
        this.strokeColor = c;
    }

    /**
     * Paint the button
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(s==null){
            s = new BasicStroke(strokeSize);
        }
        g2d.setColor(strokeColor);
        g2d.setStroke(s);
        RoundRectangle2D roundRectangle2D = new RoundRectangle2D.Double();
        roundRectangle2D.setRoundRect(x, y, width, height, arcW, arcH);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        g2d.draw(roundRectangle2D);
        if (backgroundColor != null) {
            if (isActive) {
                g2d.setColor(backgroundColor);
            } else {
                g2d.setColor(Colour.GREY_MIDDLE);
            }
        }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.fill(roundRectangle2D);
        g2d.setFont(font);
        if (isActive) {
            g2d.setColor(foregroundColor);
        } else {
            g2d.setColor(Colour.GREY_LIGHT);
        }
        FontMetrics fm = g.getFontMetrics();
        Double fx = (width + x*2 - fm.stringWidth(text)) / 2;
        Double fy = (fm.getAscent() + (height + y - (fm.getAscent() + fm.getDescent())) / 2);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        g2d.drawString(text, fx.floatValue(), fy.floatValue());
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    public Dimension getSize(){
        return getPreferredSize();
    }

    /**
     * Get button's size
     * @return the real size of button
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width.intValue(), height.intValue()+1);
    }

    /**
     * CustomButtonEvent only contains button effects
     */
    private class CustomButtonEvent extends MouseAdapter {

        private Color colour;
        private Color onMouseColour;
        private CustomButton btn;

        public CustomButtonEvent(Color oldColour, Color newColour) {
            this.onMouseColour = oldColour;
            this.colour = newColour;
            this.btn = CustomButton.this;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (btn.isActive()) {
                btn.setBackgroundColor(colour);
                btn.repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (btn.isActive()) {
                btn.setBackgroundColor(onMouseColour);
                btn.repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (btn.isActive()) {
                btn.setBackgroundColor(onMouseColour);
                btn.repaint();
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }
    }
}
