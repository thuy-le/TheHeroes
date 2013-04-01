package vn.edu.rmit.Utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/15/13
 * Time: 2:44 PM
 */
public class Utilities {
    public static BufferedImage getBackgroundImage(String imgPath){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return img;
    }
}
