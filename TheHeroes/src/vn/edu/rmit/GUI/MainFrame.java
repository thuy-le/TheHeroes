package vn.edu.rmit.GUI;

import layout.TableLayout;
import org.springframework.context.ApplicationContext;
import vn.edu.rmit.Model.*;
import vn.edu.rmit.Model.Hero.Hero;
import vn.edu.rmit.Model.Hero.HeroFactory;
import vn.edu.rmit.Model.Hero.HeroType;
import vn.edu.rmit.Utilities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
    private ApplicationContext ctx;
    private JPanel contentPane;
    private JLabel heroName;
    private List<Avatar> buttons;
    private JTextArea textArea;
    private JTextField textField;
    private HexGrid map;
    private CustomButton exit;
    private CustomPanel bottomPanel;
    private CustomPanel heroDetailPanel;
    private Avatar heroAvatar;
    private CustomPanel actionPanel;
    private CustomPanel chatPanel;
    private String playerName;
    private CustomPanel textHolder;
    private JScrollPane sp;
    public Hero hero;
    public HeroFactory heroFactory;
    private JLabel status;

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
        heroFactory = new HeroFactory();
    }

    /**
     * Initialization
     */
    private void initializeUI() {
        bottomPanel = new CustomPanel();
        heroDetailPanel = new CustomPanel();
        actionPanel = new CustomPanel();
        chatPanel = new CustomPanel();
        setLayout();
        /*
        Initialize components
         */
        map = (HexGrid) ctx.getBean("HexGrid");
        exit = (CustomButton) ctx.getBean("Button");

        heroAvatar = new Avatar();
        heroName = new JLabel(" Hero's name");

        String actionIMGs[] = {ImagePath.MOVE, ImagePath.ATTACK, ImagePath.DEFENSE, ImagePath.SKIP};
        String actions[] = {"Move", "Attack", "Defense", "Skip"};
        buttons = new ArrayList<Avatar>();
        textArea = new JTextArea(4, 69);
        textField = new JTextField(63);
        textHolder = new CustomPanel();
        sp = new JScrollPane(textArea);

        for (int i = 0; i < actionIMGs.length && i < actions.length; i++) {
            Avatar a = new Avatar();
            a.setToolTipText(actions[i]);
            a.setImagePath(actionIMGs[i]);
            buttons.add(a);
            actionPanel.add(a);
        }
        /*
        Adding components
         */

        chatPanel.add(sp);
        textHolder.add(textField);
        chatPanel.add(textHolder);
        heroDetailPanel.add(heroName, "0, 0");
        heroDetailPanel.add(actionPanel, "0, 1");
        heroDetailPanel.add(actionPanel, "0, 2");
        bottomPanel.add(heroAvatar, "0, 0");
        bottomPanel.add(heroDetailPanel, "1, 0");
        bottomPanel.add(chatPanel, "2,0");
        add(exit, "0, 0"); //col,row
        add(map, "0, 1");
        add(bottomPanel, "0, 2");
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

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GameEngine) {
            if (((GameEngine) o).getChatMSG().trim().length() > 0) {
                textArea.append("\n");
                textArea.append(((GameEngine) o).getChatMSG());
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
            playerName = ((GameEngine) o).getPlayerName();
            setTitle(playerName);
            map.setOpponentPosition(((GameEngine) o).getOpponentPosition());
            map.repaint();
            if(playerName!=null && hero == null){
                if(playerName.equalsIgnoreCase("Player 1")){
                    hero = heroFactory.createHero(HeroType.FIGHTER);
                }
                else {
                    hero = heroFactory.createHero(HeroType.HEALER);
                }
                heroName.setText(hero.getName());
                //heroAvatar.setImagePath("warrior1.png");
            }
            repaint();
        }
    }

    @Override
    public void repaint(long time, int x, int y, int width, int height) {
        super.repaint(time, x, y, width, height);
        if(hero != null){
            heroAvatar.setAlpha(.3f);
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

    ;

    /**
     * Getter - Setter
     */
    private int getFrameWidth() {
        return this.width;
    }

    public void setHeroName(String name) {
        this.heroName.setText(name);
    }

    public void setMoveAction(MouseAdapter m) {
        buttons.get(0).addMouseListener(m);
    }

    public JTextField getTextField() {
        return textField;
    }

    public String getPlayerName() {
        return playerName;
    }

    /**
     * Look and feel adjustment
     */
    private void configAppearance() {
        /*Bottom Panel*/
        bottomPanel.setPanelSize(getFrameWidth() - 40, 120);
        bottomPanel.setPosition(getFrameWidth() / 2 - bottomPanel.getPanelSize().width / 2, 3);
        bottomPanel.setBackground(Colour.BLUE_DARK);
        bottomPanel.setStroke(new BasicStroke(1f), Colour.BLUE_MIDDLE);
        bottomPanel.setARC(10, 10);
        /*Exit button*/
        exit.setPosition(getFrameWidth() / 2 - exit.getButtonWidth() / 2, 0);
        exit.setText("Exit");
        /*Hero Represent*/
        heroAvatar.setPanelSize(110, 110);
        heroAvatar.setPosition(5, 5);
        heroAvatar.setStroke(new BasicStroke(2f), Colour.BLUE_MIDDLE);
        heroAvatar.setImagePath(ImagePath.WARRIOR1);
        heroAvatar.setBackground(Colour.BLUE_LIGHT);
        heroAvatar.setARC(10, 10);
        heroAvatar.setAlpha(.8f);
        /*Hero Name Text Field*/
        heroName.setFont(new Font("Century Gothic", Font.BOLD, 19));
        heroName.setForeground(Colour.GREEN_LIGHT);
        /*Navigators*/
        for (Avatar a : buttons) {
            a.setPanelSize(55, 55);
            a.setARC(17, 17);
            a.setBackground(Colour.BLUE_MIDDLE);
            a.setStroke(null, Colour.BLUE_MIDDLE);
        }
        /*Chat Text Field + Text Area*/
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setForeground(Colour.BLUE_LIGHT);
        textArea.setText("Chat pane");
        textHolder.setPanelSize(770, 30);
        textHolder.setStroke(new BasicStroke(1), Colour.BLUE_LIGHT);
        textHolder.setARC(10, 10);
        textHolder.setAlpha(.5f);
        textHolder.setBackground(Colour.BLUE_LIGHT);
        textField.setOpaque(false);
        textField.setBorder(null);
        textField.setForeground(Colour.BLUE_DARK.darker());
        textField.setFont(new Font("Century Gothic", Font.BOLD, 13));
        JScrollBar sb = sp.getVerticalScrollBar();
        sb.setUI(new MyScrollBarUI());
        sp.getViewport().setOpaque(false);
        sp.getViewport().setBorder(null);
        sp.setBorder(null);
        sp.setHorizontalScrollBar(null);
        sp.setOpaque(false);
    }

    private void setLayout() {
        /*
        Set layout
         */
        double size[][] = {{TableLayout.FILL}, //cols
                {36, TableLayout.FILL, 120}}; //rows
        setLayout(new TableLayout(size));
        double bottomPanelLayout[][] = {{120, 250, TableLayout.FILL}, //cols
                {TableLayout.FILL}}; //rows
        bottomPanel.setLayout(new TableLayout(bottomPanelLayout));
        double heroDetailLayout[][] = {{TableLayout.FILL}, //cols
                {30, TableLayout.FILL, 20}}; //rows
        heroDetailPanel.setLayout(new TableLayout(heroDetailLayout));
        actionPanel.setLayout(new FlowLayout());
        chatPanel.setLayout(new FlowLayout());
    }
}

