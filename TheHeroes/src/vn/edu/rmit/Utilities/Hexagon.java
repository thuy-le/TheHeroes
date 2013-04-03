package vn.edu.rmit.Utilities;

import vn.edu.rmit.Model.Hero.Hero;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;


/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/9/13
 * Time: 7:42 PM
 * References:
 * http://www.codeproject.com/Articles/14948/Hexagonal-grid-for-games-and-other-projects-Part-1
 */

public class Hexagon implements Serializable {
    //Constants
    private static final Color BORDER_COLOR = Colour.AQUA;
    private static final Color BACKGROUND_COLOR = Colour.BLUE_DARK;
    private static final Color DISABLE_COLOR = Colour.GREY_MIDDLE;
    private static final float OPACITY = .7f;
    //Variables
    private String id;
    //private boolean available;
    //private boolean active;
    //private boolean selected;
    private GroundType groundType;
    private Hero hero;
    private HexagonType type;
    private double side;
    private double xPixel;
    private double yPixel;
    private Point2D.Double centerPoint;
    private Point2D.Double center;
    private Shape hexagon;
    private Rectangle2D bounds;
    private String trees[] = {
            "367","328","287","243","198","155","156","157","666","634","604","572",
            "462","428","392","354","340","300","299","257","213","168","126","244",
            "72","73","106","638","608","639","107","108","109","542","511","480",
            "105","104","71","69","45","99","97","609","577","576","546","545","513",
            "544","145","146","189","246","200","201","248","596","564","626","470",
            "628","598"
    };
    private String mountains[] = {
            "405","369","329","289","245","199","154","115","116","635","605","573",
            "543","252","206","161","162","163","209","253","254","575","607","638",
            "608","639", "368", "288","4","3","9","8","22","40","66","68","100","136",
            "330","331","81","82","117"
    };
    private String water[] = {
            "315","273","314","353","313","426","427","460",
            "492","269","267","309","347","305","303","345","383","574","606","637","636","391",
            "461","390","272","307","265","263","343","352","381","416","450","482","451","380",
            "417","512","5","1","2","10","24","42","44","70","669","627","597","565","534","502",
            "533","501"
    };
    //private boolean moveAvail;
    //private boolean opponentPosition;
    //private boolean myPosition;

    public Hexagon() {
        this.id = "";
        this.xPixel = 0;
        this.yPixel = 0;
        this.side = 0;
        this.centerPoint = new Point2D.Double(0, 0);
        this.type = HexagonType.FLAT;
        this.hexagon = hexagonPath(centerPoint, side, type);
        this.bounds = hexagon.getBounds();
        this.groundType = GroundType.GRASS;
    }

    private TexturePaint getTexturePaint() {
        TexturePaint texture = null;
        BufferedImage img = Utilities.getBackgroundImage(groundType.getImagePath());
        if (img != null && hexagon != null) {
            texture = new TexturePaint(img, hexagon.getBounds());
        }
        return texture;
    }

    public void drawHexagon(Graphics2D g2d) {
        for(int i = 0; i < trees.length; i++){
            if(id.equalsIgnoreCase(trees[i]))
                groundType = GroundType.TREE;
        }
        for(int i = 0; i < mountains.length; i++){
            if(id.equalsIgnoreCase(mountains[i]))
                groundType = GroundType.MOUNTAIN;
        }
        for(int i = 0; i < water.length; i++){
            if(id.equalsIgnoreCase(water[i]))
                groundType = GroundType.WATER;
        }
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2d.setColor(Colour.GREEN_DARK);
        g2d.setStroke(new BasicStroke(1));
        //g2d.draw(hexagon);
        g2d.setColor(Colour.BROWN);
        //g2d.drawString(id, hexagon.getBounds().x +15, hexagon.getBounds().y + 20);
        if(groundType == GroundType.GRASS) return;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .8f));
        g2d.setBackground(BACKGROUND_COLOR);
        //g2d.fill(hexagon);
        TexturePaint texture = getTexturePaint();
        g2d.setPaint(texture);
        g2d.fill(hexagon);
    }

    public Point2D.Double getCenter(){
        return this.center;
    }

    public void setGroundType(GroundType type) {
        this.groundType = type;
    }

    public GroundType getGroundType(){
        return this.groundType;
    }

    public Rectangle2D getBounds() {
        return bounds;
    }

    private void setBounds(Rectangle2D r) {
        this.bounds = r;
    }

    /**
     * Initialize hexagon
     * Takes parameters from hex grid (which will determine side, position, type and id of the hexagon)
     *
     * @param id     auto-generated in HexGrid.java
     * @param type   pointy or flat
     * @param xPixel xPixel and yPixel determine the position of hexagon
     * @param yPixel
     * @param center the central point of the hexagon
     * @param side   hexagon side is calculated by the height of hex grid panel
     */
    public void initHexagon(String id, HexagonType type, double xPixel, double yPixel,
                            Point2D.Double center, double side, Point2D.Double c) {
        this.id = id;
        AffineTransform at = AffineTransform.getTranslateInstance(xPixel, yPixel);
        this.hexagon = at.createTransformedShape(hexagonPath(center, side, type));
        setBounds(hexagon.getBounds2D());
        this.center = c;
        this.side = side;
        this.xPixel = xPixel;
        this.yPixel = yPixel;
    }

    public double getSide(){
        return side;
    }

    public String getID() {
        return id;
    }

    public Shape getHexagon() {
        return hexagon;
    }

    /**
     * Creating a hexagon path
     *
     * @param center is the central point of the hexagon
     * @param side   hexagon's side
     * @param type   pointy or flat
     * @return hexagon
     */
    private Shape hexagonPath(Point2D center, double side, HexagonType type) {
        Path2D.Double hexPath = new Path2D.Double();
        /* Calculating the radian base on HexagonType
        *  HexagonType type is passed as a constructor parameter to HexGrid
        *  Radian is used later to calculate the position of points (for creating a hex path)
        * */
        double radian = (type == HexagonType.POINTY) ? Math.toRadians(90) : Math.toRadians(60);
        /* Calculating the starting point of our hex path
        *  The first point of the first hexagon will be calculated by:
        *  For pointy hexagon:
        *       x = the side of the hexagon multiply by cos(90 degree) plus xCoordinate of the center
        *       y = the side of the hexagon multiply by sin(90 degree) plus yCoordinate of the center
        *  For flat hexagon:
        *       The algorithm is the same, but the starting point will be calculated by cos(60 degree) and
        *       sin(60 degree) instead.
        * */
        double x = center.getX() + side * Math.cos(radian);
        double y = center.getY() + side * Math.sin(radian);
        hexPath.moveTo(x, y);
        /* For each next point, the radian is added by 60 degree
        * */
        for (int i = 0; i < 6; i++) {
            radian += Math.toRadians(60);
            x = center.getX() + side * Math.cos(radian);
            y = center.getY() + side * Math.sin(radian);
            hexPath.lineTo(x, y);
        }
        return hexPath;
    }
}
