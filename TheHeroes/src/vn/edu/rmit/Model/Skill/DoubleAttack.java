package vn.edu.rmit.Model.Skill;

import vn.edu.rmit.Model.Hero.Hero;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/16/13
 * Time: 1:54 PM
 */
public class DoubleAttack extends Skill {

    public DoubleAttack() {
        setName("Double Attack");
        setType(SkillType.ATTACK);
        setConsume(50);
    }

    @Override
    public void takeEffect(Hero hero, List<Hero> targets) {
        if (hero != null) {
            hero.setCurrentAttack(hero.getCurrentAttack() + hero.getAttack());
            if (targets != null) {
                Hero target = targets.get(0);
                hero.attack(target);
            }
            hero.setCurrentAttack(hero.getCurrentAttack() - hero.getAttack());
        }
    }
}
