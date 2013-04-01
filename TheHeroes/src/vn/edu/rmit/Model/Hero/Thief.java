package vn.edu.rmit.Model.Hero;

import vn.edu.rmit.Utilities.ImagePath;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 4/1/13
 * Time: 9:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class Thief extends Hero {
    public Thief() {
        setName("Thief");
        setHealth(38);
        setAttack(26);
        setDefense(5);
        setView(5);
        setMove(2);
        setRange(5);
        setMana(25);
        setImagePath(ImagePath.THIEF);
    }
}
