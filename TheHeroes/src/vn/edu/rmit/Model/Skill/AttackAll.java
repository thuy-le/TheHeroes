package vn.edu.rmit.Model.Skill;

import vn.edu.rmit.Model.Hero.Hero;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/16/13
 * Time: 1:54 PM
 */
public class AttackAll extends Skill {

    public AttackAll() {
        setName("Attack All");
        setType(SkillType.ATTACK);
        setConsume(100);
    }

    @Override
    public void takeEffect(Hero hero, List<Hero> targets) {
        if (targets != null) {
            for (int i = 0; i < targets.size(); i++) {
                Hero target = targets.get(i);
                hero.attack(target);
            }
        }
    }
}
