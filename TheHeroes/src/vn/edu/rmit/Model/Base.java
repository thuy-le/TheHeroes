package vn.edu.rmit.Model;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/16/13
 * Time: 1:35 PM
 */
public class Base extends GameObject {
    public Base(){

    }

    public void generateHealth(){
        setCurrentHealth(getCurrentHealth() + 5);
        if(getCurrentHealth() > getHealth())
            setCurrentHealth(getHealth());
    }
}


