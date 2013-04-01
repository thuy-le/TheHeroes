package vn.edu.rmit.Model.Hero;

import vn.edu.rmit.Model.Skill.AttackAll;
import vn.edu.rmit.Model.Skill.Skill;
import vn.edu.rmit.Utilities.ImagePath;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 4/1/13
 * Time: 9:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class Mage extends Hero {
    public Mage() {
        setName("Mage");
        setHealth(84);
        setAttack(43);
        setDefense(7);
        setView(5);
        setMove(2);
        setRange(5);
        setMana(100);
        List<Skill> skills = new ArrayList<Skill>();
        skills.add(new AttackAll());
        setSkills(skills);
        setImagePath(ImagePath.MAGE);
    }
}
