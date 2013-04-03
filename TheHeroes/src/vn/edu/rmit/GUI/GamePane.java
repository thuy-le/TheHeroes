package vn.edu.rmit.GUI;

import layout.TableLayout;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import vn.edu.rmit.Utilities.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 4/1/13
 * Time: 8:27 PM
 */
public class GamePane extends CustomPanel {
    private final int WIDTH = 1280;
    private JLabel heroName;
    private java.util.List<ActionButton> buttons;
    private JTextArea textArea;
    private JTextField textField;
    private CustomPanel heroDetailPanel;
    private Avatar heroAvatar;
    private CustomPanel actionPanel;
    private CustomPanel chatPanel;
    private CustomPanel textHolder;
    private JScrollPane sp;
    private JLabel status;
    private JPanel heroNameHolder;
    private JPanel statusHolder;
    private Status health;
    private Status attack;
    private Status defense;
    private JLabel healthLabel;
    private JLabel attackLabel;
    private JLabel defenseLabel;

    public GamePane() {
        init();
    }

    private void init() {
        heroDetailPanel = new CustomPanel();
        actionPanel = new CustomPanel();
        chatPanel = new CustomPanel();
        heroNameHolder = new CustomPanel();
        statusHolder = new CustomPanel();
        textHolder = new CustomPanel();
        setLayout();
        health = new Status(Colour.RED_MIDDLE);
        attack = new Status(Colour.YELLOW);
        defense = new Status(Colour.BLUE_MIDDLE);
        healthLabel = new JLabel("Health");
        attackLabel = new JLabel("Attack");
        defenseLabel = new JLabel("Defense");
        heroAvatar = new Avatar();
        heroName = new JLabel(" Hero's name");
        String actionIMGs[] = {ImagePath.MOVE, ImagePath.ATTACK, ImagePath.DEFENSE, ImagePath.SKIP};
        String actions[] = {"Move", "Attack", "Defense", "Skip"};
        buttons = new ArrayList<ActionButton>();
        textArea = new JTextArea(5, 69);
        textField = new JTextField();
        sp = new JScrollPane(textArea);
        for (int i = 0; i < actionIMGs.length && i < actions.length; i++) {
            ActionButton a = new ActionButton();
            a.setToolTipText(actions[i]);
            a.setImagePath(actionIMGs[i]);
            buttons.add(a);
            actionPanel.add(a);
        }
        chatPanel.add(sp, "0, 1");
        textHolder.add(textField, "1,0");
        chatPanel.add(textHolder, "0, 2");
        heroNameHolder.add(heroName, "1,0");
        heroDetailPanel.add(heroNameHolder, "0, 0");
        heroDetailPanel.add(actionPanel, "0, 1");

        statusHolder.add(healthLabel, "1,0");
        statusHolder.add(health, "2, 0");
        statusHolder.add(attackLabel, "1,1");
        statusHolder.add(attack, "2, 1");
        statusHolder.add(defenseLabel, "1,2");
        statusHolder.add(defense, "2, 2");

        heroDetailPanel.add(statusHolder, "0, 2");
        add(heroAvatar, "1, 0");
        add(heroDetailPanel, "2, 0");
        add(chatPanel, "3,0");
        uiConfig();
    }

    private void uiConfig() {
        /*Bottom Panel*/
        setPanelSize(WIDTH - 40, 150);
        setPanelPosition(WIDTH / 2 - getPanelSize().width / 2 - 10, 3);
        setBackground(Colour.BLUE_DARK);
        setStroke(1, Colour.BLUE_MIDDLE);
        setARC(10, 10);
        /*Hero Represent*/
        heroAvatar.setPanelSize(120, 120);
        heroAvatar.setPosition(5, 5);
        heroAvatar.setStroke(1, Colour.BLUE_MIDDLE);
        heroAvatar.setImagePath(ImagePath.WARRIOR1);
        heroAvatar.setBackground(Colour.BLUE_LIGHT);
        heroAvatar.setARC(10, 10);
        heroAvatar.setAlpha(.8f);
        /*Hero Name Text Field*/
        heroName.setFont(new Font("Century Gothic", Font.BOLD, 19));
        heroName.setForeground(Colour.GREEN_LIGHT);
        /*Navigators*/
        for (ActionButton a : buttons) {
            a.setPanelSize(40, 40);
            a.setARC(17, 17);
            a.setBackground(Colour.BLUE_MIDDLE);
            a.setStroke(0, Colour.BLUE_MIDDLE);
        }
        /*Chat Text Field + Text Area*/
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setForeground(Colour.BLUE_LIGHT);
        textArea.setText("Chat pane");
        textHolder.setPanelSize(770, 30);
        textHolder.setStroke(1, Colour.BLUE_LIGHT);
        textHolder.setARC(10, 10);
        textHolder.setAlpha(.5f);
        textHolder.setBackground(Colour.BLUE_LIGHT);
        textField.setOpaque(false);
        textField.setBorder(null);
        textField.setForeground(Colour.BLUE_DARK.darker());
        textField.setFont(new Font("Century Gothic", Font.BOLD, 13));
        JScrollBar sb = sp.getVerticalScrollBar();
        sb.setUI(new MyScrollBarUI(12, 120));
        sp.getViewport().setOpaque(false);
        sp.getViewport().setBorder(null);
        sp.setBorder(null);
        sp.setHorizontalScrollBar(null);
        sp.setOpaque(false);
        /*Status*/
        healthLabel.setForeground(Colour.BLUE_LIGHT);
        healthLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        attackLabel.setForeground(Colour.BLUE_LIGHT);
        attackLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        defenseLabel.setForeground(Colour.BLUE_LIGHT);
        defenseLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
    }

    private void setLayout() {
        double bottomPanelLayout[][] = {{7, 140, 300, TableLayout.FILL}, //cols
                {TableLayout.FILL}}; //rows
        setLayout(new TableLayout(bottomPanelLayout));
        double heroDetailLayout[][] = {{TableLayout.FILL}, //cols
                {30, TableLayout.FILL, 55}}; //rows
        heroDetailPanel.setLayout(new TableLayout(heroDetailLayout));
        double rowLayout1[][] = {{5, TableLayout.FILL},
                {TableLayout.FILL}};
        heroNameHolder.setLayout(new TableLayout(rowLayout1));
        double rowLayout2[][] = {{5, 60, TableLayout.FILL},
                {15,15,15}};
        statusHolder.setLayout(new TableLayout(rowLayout2));
        actionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        double chatLayout[][] = {{TableLayout.FILL},
                {10, TableLayout.FILL, 45}};
        chatPanel.setLayout(new TableLayout(chatLayout));
        double txtLayout[][] = {{10, TableLayout.FILL, 80},
                {TableLayout.FILL,13}};
        textHolder.setLayout(new TableLayout(txtLayout));
    }

    public void setHeroName(String heroName) {
        this.heroName.setText(heroName);
    }

    public List<ActionButton> getButtons() {
        return buttons;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JTextField getTextField() {
        return textField;
    }

    public Avatar getHeroAvatar() {
        return heroAvatar;
    }

    public void setHeroAvatar(String heroAvatar) {
        this.heroAvatar.setImagePath(heroAvatar);
    }

    public void appendText(String text) {
        this.textArea.append(text);
    }

    public void setTextAreaCaretPosition(int length) {
        this.textArea.setCaretPosition(length);
    }

}
