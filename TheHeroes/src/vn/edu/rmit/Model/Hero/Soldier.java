package vn.edu.rmit.Model.Hero;

import vn.edu.rmit.Model.Skill.DoubleAttack;
import vn.edu.rmit.Model.Skill.Skill;
import vn.edu.rmit.Utilities.ImagePath;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 4/1/13
 * Time: 8:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class Soldier extends Hero {
    public Soldier() {
        setName("Soldier");
        setHealth(49);
        setAttack(11);
        setDefense(3);
        setView(5);
        setMove(5);
        setRange(1);
        setMana(25);
        setSkills((List<Skill>) new DoubleAttack());
        setImagePath(ImagePath.SOLDIER);
    }
}
