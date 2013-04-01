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
    private CustomPanel row1, row2, row3;
    private JTextField unTxt, pwTxt;
    public LoginForm(){
    }

    private void init(){
        /*Init variables*/
        formTitle = new JLabel("The Heroes");
        unLabel = new JLabel("Username: ");
        pwLabel = new JLabel("Password: ");
        unTxt = new JTextField();
        pwTxt = new JTextField();
        loginBtn = new CustomButton("Login");
        exitBtn = new CustomButton("Exit");
        row1 = new CustomPanel();
        row2 = new CustomPanel();
        row3 = new CustomPanel();

        row1.add(unLabel, "0,0");
        row1.add(unTxt, "1,0");

        row2.add(pwLabel, "0,0");
        row2.add(pwTxt, "1,0");

        row3.add(loginBtn, "0,0");
        row3.add(exitBtn, "1,0");

        add(formTitle, "0,0");
        add(row1, "0,1");
        add(row2, "0,2");
        add(row3, "0,3");
    }

    private void uiConfig(){
        formTitle.setForeground(Colour.PINK_LIGHT);
        formTitle.setFont(new Font("Century Gothic", Font.BOLD, 17));
    }

    private void setLayout(){
        double layout[][] = {{TableLayout.FILL}, //cols
                {50,30,30,40}}; //rows
        setLayout(new TableLayout(layout));
        double rowLayout[][] = {{150,300},{TableLayout.FILL}};
        row1.setLayout(new TableLayout(rowLayout));
        row2.setLayout(new TableLayout(rowLayout));
        row3.setLayout(new TableLayout(rowLayout));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }
}
