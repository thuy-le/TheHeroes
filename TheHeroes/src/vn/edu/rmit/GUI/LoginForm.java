package vn.edu.rmit.GUI;

import layout.TableLayout;
import vn.edu.rmit.Utilities.Colour;
import vn.edu.rmit.Utilities.CustomButton;
import vn.edu.rmit.Utilities.CustomPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 4/1/13
 * Time: 8:03 PM
 */
public class LoginForm extends CustomPanel{
    private JLabel formTitle;
    private JLabel unLabel;
    private JLabel pwLabel;
    private CustomButton loginBtn;
    private CustomButton exitBtn;
    private CustomPanel unTxtHolder;
    private CustomPanel pwTxtHolder;
    private CustomPanel row1;
    private CustomPanel row2;
    private CustomPanel row3;
    private CustomPanel loginForm;
    private JTextField unTxt;
    private JPasswordField pwTxt;

    public LoginForm(){
        init();
    }

    private void init(){
        /*Init variables*/
        loginForm = new CustomPanel();
        formTitle = new JLabel("The Heroes");
        unLabel = new JLabel("Username: ");
        pwLabel = new JLabel("Password: ");
        unTxt = new JTextField(20);
        pwTxt = new JPasswordField(20);
        loginBtn = new CustomButton("Login");
        exitBtn = new CustomButton("Exit");
        row1 = new CustomPanel();
        row2 = new CustomPanel();
        row3 = new CustomPanel();
        unTxtHolder = new CustomPanel();
        pwTxtHolder = new CustomPanel();
        setLayout();
        unTxtHolder.add(unTxt, "1,0");
        pwTxtHolder.add(pwTxt, "1,0");
        row1.add(unLabel, "0,1");
        row1.add(unTxtHolder, "1,1");
        row2.add(pwLabel, "0,1");
        row2.add(pwTxtHolder, "1,1");
        row3.add(loginBtn, "1,1");
        row3.add(exitBtn, "2,1");
        loginForm.add(formTitle, "1,1");
        loginForm.add(row1, "1,2");
        loginForm.add(row2, "1,3");
        loginForm.add(row3, "1,4");
        add(loginForm, "1,1");
        uiConfig();
    }

    private void uiConfig(){
        loginForm.setAlpha(.8f);
        loginForm.setStroke(1, Colour.BLUE_LIGHT);
        loginForm.setPanelSize(550, 330);
        loginForm.setPanelPosition(3, 3);
        formTitle.setForeground(Colour.BLUE_LIGHT.brighter());
        formTitle.setFont(new Font("Century Gothic", Font.PLAIN, 30));
        unTxtHolder.setPanelSize(350, 30);
        unTxtHolder.setStroke(1, Colour.BLUE_LIGHT);
        unTxtHolder.setARC(10, 10);
        unTxtHolder.setAlpha(.5f);
        unTxtHolder.setBackground(Colour.BLUE_LIGHT);
        pwTxtHolder.setPanelSize(350, 30);
        pwTxtHolder.setStroke(1, Colour.BLUE_LIGHT);
        pwTxtHolder.setARC(10, 10);
        pwTxtHolder.setAlpha(.5f);
        pwTxtHolder.setBackground(Colour.BLUE_LIGHT);
        unTxt.setOpaque(false);
        pwTxt.setOpaque(false);
        unLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        pwLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        unTxt.setBorder(null);
        pwTxt.setBorder(null);
        unTxt.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        pwTxt.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        unLabel.setForeground(Colour.BLUE_LIGHT.brighter());
        pwLabel.setForeground(Colour.BLUE_LIGHT.brighter());

        loginForm.setARC(20, 20);
        loginForm.setBackground(Colour.BLUE_DARK);
        loginForm.setStroke(1, Colour.GREY_LIGHT);
        loginForm.setPanelPosition(1,1);

        loginBtn.setPosition(1.5, 3.5);
        loginBtn.setStroke(new BasicStroke(1.5f), Colour.GREY_LIGHT.brighter());
        loginBtn.setButtonSize(loginBtn.getButtonWidth() + 18, loginBtn.getButtonHeight());

        exitBtn.setPosition(1.5, 3.5);
        exitBtn.setStroke(new BasicStroke(1.5f), Colour.GREY_LIGHT.brighter());
        exitBtn.setButtonSize(exitBtn.getButtonWidth() + 18, exitBtn.getButtonHeight());
    }

    private void setLayout(){
        double layout[][] = {{50, TableLayout.FILL},
                {20,TableLayout.FILL}};
        setLayout(new TableLayout(layout));
        double loginFormLayout[][] = {{40, TableLayout.FILL}, //cols
                {20, 75,60,60,70}}; //rows
        loginForm.setLayout(new TableLayout(loginFormLayout));
        double rowLayout[][] = {{110,360},{10,TableLayout.FILL, 10}};
        row1.setLayout(new TableLayout(rowLayout));
        row2.setLayout(new TableLayout(rowLayout));
        double rowLayout2[][] = {{110, 180, TableLayout.FILL},{10,TableLayout.FILL, 10}};
        row3.setLayout(new TableLayout(rowLayout2));
        double txtLayout[][] = {{10,TableLayout.FILL,20},{TableLayout.FILL,10}};
        unTxtHolder.setLayout(new TableLayout(txtLayout));
        pwTxtHolder.setLayout(new TableLayout(txtLayout));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(550, 550);
    }
}
