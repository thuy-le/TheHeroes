package vn.edu.rmit.Model.Hero;

import vn.edu.rmit.Model.Skill.HealAll;
import vn.edu.rmit.Model.Skill.Skill;
import vn.edu.rmit.Utilities.ImagePath;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 4/1/13
 * Time: 9:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class Healer extends Hero {
    public Healer() {
        setName("Healer");
        setHealth(251);
        setAttack(38);
        setDefense(8);
        setView(5);
        setMove(2);
        setRange(5);
        setMana(100);
        List<Skill> skills = new ArrayList<Skill>();
        skills.add(new HealAll());
        setSkills(skills);
        setImagePath(ImagePath.HEALER);
    }
}
