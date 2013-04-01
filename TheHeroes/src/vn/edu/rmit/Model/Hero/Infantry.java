package vn.edu.rmit.Model.Hero;

import vn.edu.rmit.Utilities.ImagePath;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 4/1/13
 * Time: 9:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class Infantry extends Hero {
    public Infantry() {
        setName("Infantry");
        setHealth(27);
        setAttack(6);
        setDefense(2);
        setView(4);
        setMove(4);
        setRange(1);
        setMana(25);
        setImagePath(ImagePath.INFANTRY);
    }
}
