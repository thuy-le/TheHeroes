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
public class GamePane extends CustomPanel{
    private final int WIDTH = 1280;
    private JLabel heroName;
    private java.util.List<Avatar> buttons;
    private JTextArea textArea;
    private JTextField textField;
    private CustomPanel heroDetailPanel;
    private Avatar heroAvatar;
    private CustomPanel actionPanel;
    private CustomPanel chatPanel;
    private String playerName;
    private CustomPanel textHolder;
    private JScrollPane sp;
    private JLabel status;

    public GamePane(){
        init();
    }
    private void init(){
        heroDetailPanel = new CustomPanel();
        actionPanel = new CustomPanel();
        chatPanel = new CustomPanel();
        setLayout();
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
        chatPanel.add(sp);
        textHolder.add(textField);
        chatPanel.add(textHolder);
        heroDetailPanel.add(heroName, "0, 0");
        heroDetailPanel.add(actionPanel, "0, 1");
        heroDetailPanel.add(actionPanel, "0, 2");
        add(heroAvatar, "0, 0");
        add(heroDetailPanel, "1, 0");
        add(chatPanel, "2,0");
        uiConfig();
    }

    private void uiConfig(){
        /*Bottom Panel*/
        setPanelSize(WIDTH - 40, 120);
        setPosition(WIDTH / 2 - getPanelSize().width / 2, 3);
        setBackground(Colour.BLUE_DARK);
        setStroke(new BasicStroke(1f), Colour.BLUE_MIDDLE);
        setARC(10, 10);
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

    private void setLayout(){
        double bottomPanelLayout[][] = {{120, 250, TableLayout.FILL}, //cols
                {TableLayout.FILL}}; //rows
        setLayout(new TableLayout(bottomPanelLayout));
        double heroDetailLayout[][] = {{TableLayout.FILL}, //cols
                {30, TableLayout.FILL, 20}}; //rows
        heroDetailPanel.setLayout(new TableLayout(heroDetailLayout));
        actionPanel.setLayout(new FlowLayout());
        chatPanel.setLayout(new FlowLayout());
    }

    public JLabel getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName.setText(heroName);
    }

    public List<Avatar> getButtons() {
        return buttons;
    }

    public void setButtons(List<Avatar> buttons) {
        this.buttons = buttons;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public CustomPanel getHeroDetailPanel() {
        return heroDetailPanel;
    }

    public void setHeroDetailPanel(CustomPanel heroDetailPanel) {
        this.heroDetailPanel = heroDetailPanel;
    }

    public Avatar getHeroAvatar() {
        return heroAvatar;
    }

    public void setHeroAvatar(String heroAvatar) {
        this.heroAvatar.setImagePath(heroAvatar);
    }

    public CustomPanel getActionPanel() {
        return actionPanel;
    }

    public void setActionPanel(CustomPanel actionPanel) {
        this.actionPanel = actionPanel;
    }

    public CustomPanel getChatPanel() {
        return chatPanel;
    }

    public void setChatPanel(CustomPanel chatPanel) {
        this.chatPanel = chatPanel;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public CustomPanel getTextHolder() {
        return textHolder;
    }

    public void setTextHolder(CustomPanel textHolder) {
        this.textHolder = textHolder;
    }

    public JScrollPane getSp() {
        return sp;
    }

    public void setSp(JScrollPane sp) {
        this.sp = sp;
    }

    public JLabel getStatus() {
        return status;
    }

    public void setStatus(JLabel status) {
        this.status = status;
    }

    public void appendText(String text){
        this.textArea.append(text);
    }

    public void setTextAreaCaretPosition(int length){
        this.textArea.setCaretPosition(length);
    }
}
