package vn.edu.rmit.Model.Skill;

import vn.edu.rmit.Model.Hero.Hero;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/16/13
 * Time: 12:15 PM
 */
public abstract class Skill {
    private String name;
    private SkillType type;
    private int consume;

    public abstract void takeEffect(Hero hero, List<Hero> targets);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SkillType getType() {
        return type;
    }

    public void setType(SkillType type) {
        this.type = type;
    }

    public int getConsume() {
        return consume;
    }

    public void setConsume(int consume) {
        this.consume = consume;
    }
}

