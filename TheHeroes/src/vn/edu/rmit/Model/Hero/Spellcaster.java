package vn.edu.rmit.Model.Hero;

import vn.edu.rmit.Model.Skill.DoubleAttack;
import vn.edu.rmit.Model.Skill.Skill;
import vn.edu.rmit.Utilities.ImagePath;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 4/1/13
 * Time: 9:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class Spellcaster extends Hero {
    public Spellcaster() {
        setName("Spellcaster");
        setHealth(68);
        setAttack(27);
        setDefense(5);
        setView(5);
        setMove(2);
        setRange(5);
        setMana(100);
        setSkills((List<Skill>) new DoubleAttack());
        setImagePath(ImagePath.SPELLCASTER);
    }
}
