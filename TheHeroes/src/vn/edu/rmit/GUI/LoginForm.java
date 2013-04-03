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
    private JLabel formTitle, unLabel, pwLabel;
    private CustomButton loginBtn, exitBtn;
    private CustomPanel unTxtHolder, pwTxtHolder;
    private CustomPanel row1, row2, row3;
    private JTextField unTxt, pwTxt;
    public LoginForm(){
        init();
    }

    private void init(){
        /*Init variables*/
        formTitle = new JLabel("The Heroes");
        unLabel = new JLabel("Username: ");
        pwLabel = new JLabel("Password: ");
        unTxt = new JTextField(20);
        pwTxt = new JTextField(20);
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
        row1.add(unLabel, "0,0");
        row1.add(unTxtHolder, "1,0");
        row2.add(pwLabel, "0,0");
        row2.add(pwTxtHolder, "1,0");
        row3.add(loginBtn, "0,0");
        row3.add(exitBtn, "1,0");
        add(formTitle, "1,0");
        add(row1, "1,1");
        add(row2, "1,2");
        add(row3, "1,3");
        uiConfig();
    }

    private void uiConfig(){
        formTitle.setForeground(Colour.BLUE_LIGHT);
        formTitle.setFont(new Font("Century Gothic", Font.BOLD, 30));
        unTxtHolder.setPanelSize(350, 30);
        unTxtHolder.setStroke(new BasicStroke(1), Colour.BLUE_LIGHT);
        unTxtHolder.setARC(10, 10);
        unTxtHolder.setAlpha(.5f);
        unTxtHolder.setBackground(Colour.BLUE_LIGHT);
        pwTxtHolder.setPanelSize(350, 30);
        pwTxtHolder.setStroke(new BasicStroke(1), Colour.BLUE_LIGHT);
        pwTxtHolder.setARC(10, 10);
        pwTxtHolder.setAlpha(.5f);
        pwTxtHolder.setBackground(Colour.BLUE_LIGHT);
        unTxt.setOpaque(false);
        pwTxt.setOpaque(false);
        unLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        pwLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        unTxt.setBorder(null);
        pwTxt.setBorder(null);
        unTxt.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        pwTxt.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        unLabel.setForeground(Colour.BLUE_MIDDLE.brighter());
        pwLabel.setForeground(Colour.BLUE_MIDDLE.brighter());
    }

    private void setLayout(){
        double layout[][] = {{20, TableLayout.FILL}, //cols
                {75,40,40,40}}; //rows
        setLayout(new TableLayout(layout));
        double rowLayout[][] = {{110,360},{TableLayout.FILL}};
        row1.setLayout(new TableLayout(rowLayout));
        row2.setLayout(new TableLayout(rowLayout));
        row3.setLayout(new TableLayout(rowLayout));
        double txtLayout[][] = {{10,TableLayout.FILL,20},{TableLayout.FILL,10}};
        unTxtHolder.setLayout(new TableLayout(txtLayout));
        pwTxtHolder.setLayout(new TableLayout(txtLayout));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }
}
