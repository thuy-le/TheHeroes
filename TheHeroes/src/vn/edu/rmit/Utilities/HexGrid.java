package vn.edu.rmit.Utilities;

import org.springframework.context.ApplicationContext;
import vn.edu.rmit.Model.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/11/13
 * Time: 10:50 PM
 */

public class HexGrid extends JPanel implements Observer {

    private static final int HEXES = 30;
    private List<Hexagon> hexagons;
    private HexagonType type;
    private ApplicationContext ctx;
    private double minDistance = 0;
    private Point2D.Double center;
    private double side;
    private Rectangle2D bounds;
    private double h, w;
    private List<Point2D.Double> listPoints;
    private Hexagon myPosition;
    private Hexagon opponentPosition;
    private String initID;
    private boolean isInit;
    private GameEngine model;

    public HexGrid(HexagonType type) {
        this.hexagons = new ArrayList<Hexagon>();
        this.type = type;
        ctx = ApplicationContextUtils.getApplicationContext();
        this.setOpaque(false);
        isInit = false;
    }

    @Override
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .0f));
        super.paintComponent(g2d);
        w = getWidth();
        h = getHeight() + 30;
        side = h / 30;
        center = new Point2D.Double(w / 2, h / 2);
        bounds = getBounds();
        minDistance = side / 4;
        if (hexagons.size() == 0)
            init();
        g2d.setColor(Colour.BLUE_DARK);
        Iterator it = hexagons.listIterator();
        for (; it.hasNext(); ) {
            Hexagon h = (Hexagon) it.next();
            if(h.getGroundType() == GroundType.MOUNTAIN || h.getGroundType() == GroundType.TREE) {
                h.drawHexagon(g2d);
                continue;
            }
            if (initID != null && initID.equals(h.getID())) {
                h.setGroundType(GroundType.WARRIOR1);
                h.drawHexagon(g2d);
                myPosition = h;
                model.writeOpponentPosition(h);
                initID = null;
                continue;
            }
            if (opponentPosition != null && opponentPosition.getID().equals(h.getID())) {
                h.setGroundType(GroundType.WARRIOR2);
                h.drawHexagon(g2d);
                continue;
            }
            if (myPosition != null && myPosition.getID().equals(h.getID())) {
                h.setGroundType(GroundType.WARRIOR1);
                h.drawHexagon(g2d);
                continue;
            }
            if (h.getGroundType() == GroundType.MOVE_AVAIL) ;
            else
                h.setGroundType(GroundType.GRASS);
            h.drawHexagon(g2d);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getParent().getWidth(), getParent().getHeight() + 30);
    }

    private void init() {
        /*
        * Create the first hexagon at the center of hex grid
        * */
        Hexagon hexagonD = (Hexagon) ctx.getBean("Hexagon");
        hexagonD.initHexagon("0", type, 0, 0, center, side, center);
        /*
        * get bounds of the created hexagon to calculate positions of next hexagons
        * */
        Rectangle2D hexBounds = hexagonD.getBounds();
        Point2D.Double c = new Point2D.Double(bounds.getCenterX(), bounds.getCenterY());
        double s = Math.min(hexBounds.getHeight(), hexBounds.getWidth());
        /*
        * calculate available central points
        * draw a corresponding hexagon for each point
        */
        listPoints = getCenters(c, s);
        for (int i = 0; i < listPoints.size(); i++) {
            Point2D.Double point = listPoints.get(i);
            double xPixel = point.x - center.x;
            double yPixel = point.y - center.y;
            Hexagon hexagon = (Hexagon) ctx.getBean("Hexagon");
            hexagon.initHexagon(String.valueOf(i + 1), type, xPixel, yPixel, center, side, listPoints.get(i));
            hexagons.add(hexagon);
        }
    }

    private List<Point2D.Double> getCenters(Point2D.Double c, double s) {
        List<Point2D.Double> allPoints = new ArrayList<Point2D.Double>();
        allPoints.add(c);
        List<Point2D.Double> temp = new ArrayList<Point2D.Double>();
        temp.add(c);
        for (; temp.size() > 0; ) {
            List<Point2D.Double> nextPoints = new ArrayList<Point2D.Double>();
            for (int i = 0; i < temp.size(); i++) {
                List<Point2D.Double> next = getNext(c, s, temp.get(i), allPoints);
                for (int j = 0; j < next.size(); j++) {
                    Point2D.Double p = next.get(j);
                    allPoints.add(p);
                    nextPoints.add(p);
                }
            }
            temp.clear();
            for (int i = 0; i < nextPoints.size(); i++) {
                temp.add(nextPoints.get(i));
            }
        }
        return allPoints;
    }

    private List<Point2D.Double> getNext(Point2D.Double c, double s,
                                         Point2D.Double current,
                                         List<Point2D.Double> listPoints) {
        List<Point2D.Double> nextPoints = new ArrayList<Point2D.Double>();
        /*get the shortest distance from center of the panel to current hexagon's central point*/
        double minD = current.distance(c);
        /* get all the (central) points near by current (central) point
        *  in order to be added to the list, these points must not be closer to center than the current
        */
        for (int i = 0; i < 6; i++) {
            double radian = Math.toRadians(60) * i;
            if (type != HexagonType.POINTY) radian += Math.toRadians(30);
            Double x = current.getX() + s * Math.cos(radian);
            Double y = current.getY() + s * Math.sin(radian);
            Point2D.Double point = new Point2D.Double(x, y);
            double distance = point.distance(c);
            if (bounds.contains(point)
                    && distance > minD
                    && point.getX() + s / 2 < center.getX() * 2
                    && point.getY() + s / 2 < center.getY() * 2 - s
                    && point.getX() - s / 2 > 0
                    && point.getY() - s / 2 > 0) {
                boolean flag = false;
                for (int j = 0; j < listPoints.size(); j++) {
                    double d = point.distance(listPoints.get(j));
                    if (d < minDistance) flag = true;
                }
                if (!flag)
                    nextPoints.add(point);
            }
        }
        return nextPoints;
    }

    public List<Hexagon> rangeCalculator(Hexagon hex, double steps) {
        if (hexagons.size() == 0) return null;
        Point2D.Double current = hex.getCenter();
        double s = hex.getSide();
        List<Hexagon> hexes = new ArrayList<Hexagon>();
        for (int i = 0; i < 6; i++) {
            double radian = Math.toRadians(60) * i;
            if (type != HexagonType.POINTY) radian += Math.toRadians(30);
            Double x = current.getX() + s * Math.cos(radian);
            Double y = current.getY() + s * Math.sin(radian);
            Point2D.Double point = new Point2D.Double(x, y);
            double distance = 0;
            for (int k = 0; k < hexagons.size(); k++) {
                if (hexagons.get(k).getHexagon().contains(point)) {
                    distance = hexagons.get(k).getCenter().distance(current);
                }
            }
            for (int j = 0; j < hexagons.size(); j++) {
                if (hexagons.get(j).getCenter().distance(current) <= distance * steps) {
                    hexes.add(hexagons.get(j));
                }
            }
        }
        return hexes;
    }

    /*
    Getter - Setter
    */

    public HexagonType getHexagonType(){
        return type;
    }

    public List<Hexagon> getHexagons() {
        return hexagons;
    }

    public void setMyPosition(Hexagon myPosition) {
        this.myPosition = myPosition;
    }

    public Hexagon getMyPosition() {
        return myPosition;
    }

    public void setOpponentPosition(Hexagon opponentPosition) {
        this.opponentPosition = opponentPosition;
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GameEngine) {
            this.model = (GameEngine) o;
            if (!isInit) {
                initID = ((GameEngine) o).getInitID();
                repaint();
                isInit = true;
            }
            if(((GameEngine) o).isNewCreated()){
                model.writeOpponentPosition(myPosition);
                ((GameEngine) o).setNewCreated(false);
            }
        }
    }
}
