package vn.edu.rmit.Model.Hero;

import vn.edu.rmit.Model.Skill.AttackAll;
import vn.edu.rmit.Model.Skill.HealAll;
import vn.edu.rmit.Model.Skill.Skill;
import vn.edu.rmit.Utilities.ImagePath;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 4/1/13
 * Time: 9:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class Maiden extends Hero {
    public Maiden() {
        setName("Maiden");
        setHealth(24);
        setAttack(4);
        setDefense(3);
        setView(5);
        setMove(2);
        setRange(5);
        setMana(50);
        List<Skill> skills = new ArrayList<Skill>();
        skills.add(new HealAll());
        setSkills(skills);
        setImagePath(ImagePath.MAIDEN);
    }
}
