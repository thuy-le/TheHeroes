package vn.edu.rmit.Controller;

import vn.edu.rmit.GUI.MainFrame;
import vn.edu.rmit.Model.GameEngine;
import vn.edu.rmit.Model.Hero.Hero;
import vn.edu.rmit.Utilities.GroundType;
import vn.edu.rmit.Utilities.HexGrid;
import vn.edu.rmit.Utilities.Hexagon;
import vn.edu.rmit.Utilities.HexagonType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/13/13
 * Time: 6:56 PM
 */
public class MapController {

    private HexGrid hexGrid;
    private MainFrame mainFrame;
    private GameEngine gameEngine;
    private List<Hexagon> hexesRanges;

    public MapController(HexGrid hexGrid, MainFrame mainFrame, GameEngine gameEngine) {
        this.hexGrid = hexGrid;
        this.mainFrame = mainFrame;
        this.gameEngine = gameEngine;
        init();
    }

    private void init() {
        hexGrid.addMouseListener(new HexOnClick());
        mainFrame.getButtons().get(0).addMouseListener(new MoveOnClick());
        JTextField txt = mainFrame.getTextField();
        txt.addActionListener(new ChatAction());
        mainFrame.addWindowListener(new WindowOnClose());
        mainFrame.getHeroAvatar().addMouseListener(new HeroOnClick());
    }

    private class HexOnClick extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            /*
            for (int i = 0; i < hexGrid.getHexagons().size(); i++) {
                Shape s = hexGrid.getHexagons().get(i).getHexagon();
                if (s.contains(e.getX(), e.getY())) {
                    Hexagon h = hexGrid.getHexagons().get(i);
                    if (h.getGroundType() != GroundType.MOVE_AVAIL) return;
                    hexGrid.setMyPosition(h);
                    gameEngine.writeOpponentPosition(h);
                    toggleRange();
                    hexGrid.repaint();
                }
            }
            */
            JOptionPane.showMessageDialog(null, "Clicked");
            for (int i = 0; i < hexGrid.getHexagons().size(); i++) {
                Hexagon h = hexGrid.getHexagons().get(i);
                if (h.getHexagon().contains(e.getPoint().x, e.getPoint().y)) {
                    JOptionPane.showMessageDialog(null, h.getID());
                }
            }
        }
    }

    private List<Hexagon> findPath(List<Hexagon> hexes, Hexagon prev, Hexagon next) {
        List<Hexagon> path = new ArrayList<Hexagon>();
        Point2D.Double current = prev.getCenter();
        for (int i = 0; i < 6; i++) {
            double radian = Math.toRadians(60) * i;
            if (hexGrid.getHexagonType() != HexagonType.POINTY) radian += Math.toRadians(30);
            Double x = current.getX() + prev.getSide() * Math.cos(radian);
            Double y = current.getY() + prev.getSide() * Math.sin(radian);
            Point2D.Double point = new Point2D.Double(x, y);
            double distance = 0;

        }
        return path;
    }

    private class MoveOnClick extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            Hexagon hex = hexGrid.getMyPosition();
            if (hex != null) {
                hexesRanges = hexGrid.rangeCalculator(hex, 3);
                toggleRange();
                hexGrid.repaint();
            }
            else JOptionPane.showMessageDialog(null, "Hex null");
        }
    }

    private class ChatAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JTextField) {
                JTextField txt = (JTextField) e.getSource();
                String s = txt.getText();
                gameEngine.writeChatMSG(s);
                txt.setText("");
            }
        }
    }

    private class HeroOnClick extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            List<Hero> heroes = mainFrame.getHeroes();
            if (heroes == null) return;
            String s = "";
            for (int i = 0; i < heroes.size(); i++) {
                s += heroes.get(i).getName();
                s += "\n";
            }
            JOptionPane.showMessageDialog(null, s);
        }
    }

    private class WindowOnClose extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            gameEngine.terminate();
        }
    }

    private void toggleRange() {
        if (hexesRanges == null) return;
        for (int i = 0; i < hexGrid.getHexagons().size(); i++) {
            Hexagon h = hexGrid.getHexagons().get(i);
            for (int j = 0; j < hexesRanges.size(); j++) {
                if (!h.getID().equals(hexesRanges.get(j).getID())) continue;
                if (h.getGroundType() == GroundType.GRASS)
                    h.setGroundType(GroundType.MOVE_AVAIL);
                else
                    h.setGroundType(GroundType.GRASS);
                break;
            }
        }
    }
}
