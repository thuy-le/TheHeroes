package vn.edu.rmit.Utilities;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/16/13
 * Time: 8:53 PM
 */
public class Avatar extends CustomPanel {

    private String imagePath;

    public Avatar(){
        super();
        setPosition(0,0);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        BufferedImage img = Utilities.getBackgroundImage(imagePath);
        g2d.setComposite(AlphaComposite.SrcAtop);
        g2d.drawImage(img, getPosition().x + 3, getPosition().y + 3, getPanelSize().width - getPosition().x,
                getPanelSize().height - getPosition().y,null);
    }


}
