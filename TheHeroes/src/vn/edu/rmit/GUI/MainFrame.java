package vn.edu.rmit.GUI;

import layout.TableLayout;
import org.springframework.context.ApplicationContext;
import vn.edu.rmit.Model.*;
import vn.edu.rmit.Model.Hero.Hero;
import vn.edu.rmit.Model.Hero.HeroFactory;
import vn.edu.rmit.Utilities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/9/13
 * Time: 8:13 PM
 */
public class MainFrame extends JFrame implements Observer {
    /*
    Variables declaration
     */
    private int width;
    private int height;
    private JPanel contentPane;
    private HexGrid map;
    private CustomButton exit;
    private Hero currentHero;
    private List<Hero> heroes;
    private JLabel status;
    private ApplicationContext ctx;
    private GamePane gamePane;
    private LoginForm loginForm;

    /**
     * Constructor
     *
     * @param title
     * @param width
     * @param height
     */
    public MainFrame(String title, int width, int height) {
        super(title);
        this.width = width;
        this.height = height;
        this.ctx = ApplicationContextUtils.getApplicationContext();
        this.contentPane = new CustomJPanel();
        setContentPane(contentPane);
        initializeUI();
    }

    /**
     * Initialization
     */
    private void initializeUI() {
        /*
        Initialize components
         */
        map = (HexGrid) ctx.getBean("HexGrid");
        exit = (CustomButton) ctx.getBean("Button");
        gamePane = (GamePane) ctx.getBean("GamePane");
        JScrollPane sp = new JScrollPane(map);
        sp.setOpaque(false);
        sp.getViewport().setBorder(null);
        sp.setBorder(null);
        sp.getViewport().setOpaque(false);
        map.setAutoscrolls(true);
        loginForm = new LoginForm();
        setLayout();
        /*
        Adding components
         */
        add(exit, "0, 0"); //col,row
        add(sp, "0, 1");
        add(gamePane, "0, 2");
        sp.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        //add(loginForm, "0, 1");
        configAppearance();
        /*
        Frame configuration
         */
        setSize(new Dimension(width, height));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /**
     * Get notification(s) from model(s)
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GameEngine) {
            if (((GameEngine) o).getChatMSG() != null) {
                gamePane.appendText(((GameEngine) o).getChatMSG());
                gamePane.appendText("\n");
                gamePane.setTextAreaCaretPosition(gamePane.getTextArea().getDocument().getLength());
                ((GameEngine) o).setChatMSG(null);
            }
            String playerName = ((GameEngine) o).getPlayerName();
            setTitle(playerName);
            map.setOpponentPosition(((GameEngine) o).getOpponentPosition());
            map.repaint();
            if (playerName != null && currentHero == null) {
                heroes = ((GameEngine) o).initRandomHeroes();
                currentHero = heroes.get(0);
                gamePane.setHeroName(currentHero.getName());
                gamePane.setHeroAvatar(currentHero.getImagePath());
            }
            repaint();
        }
    }

    /**
     * Creating a background for JFrame
     */
    private class CustomJPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (ImagePath.BACKGROUND != null) {
                BufferedImage img = Utilities.getBackgroundImage(ImagePath.BACKGROUND);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
            }
        }

        public Dimension getPreferredSize() {
            return new Dimension(width, height);
        }
    }

    /**
     * Getter - Setter
     */
    private int getFrameWidth() {
        return this.width;
    }

    public JTextField getTextField() {
        return gamePane.getTextField();
    }

    public Avatar getHeroAvatar(){
        return gamePane.getHeroAvatar();
    }

    public List<Hero> getHeroes(){
        return heroes;
    }

    public List<ActionButton> getButtons(){
        return gamePane.getButtons();
    }

    /**
     * Look and feel adjustment
     */
    private void configAppearance() {
        /*Exit button*/
        exit.setPosition(getFrameWidth() / 2 - exit.getButtonWidth() / 2, 0);
        exit.setText("Exit");
        exit.setStroke(new BasicStroke(1), Colour.GREY_LIGHT.brighter());
    }

    /**
     * Set layout
     */
    private void setLayout() {
        double size[][] = {{TableLayout.FILL}, //cols
                {36, TableLayout.FILL, 140}}; //rows
        setLayout(new TableLayout(size));
    }
}

