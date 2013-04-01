package vn.edu.rmit.Model.Skill;

import vn.edu.rmit.Model.Hero.Hero;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/16/13
 * Time: 1:54 PM
 */
public class HealAll extends Skill {

    public HealAll() {
        setName("Heal All");
        setType(SkillType.HEAL);
        setConsume(100);
    }

    @Override
    public void takeEffect(Hero hero, List<Hero> targets) {
        if (targets != null) {
            for (int i = 0; i < targets.size(); i++) {
                Hero target = targets.get(i);
                target.setCurrentHealth(target.getCurrentHealth() + target.getHealth() / 2);
                if (target.getCurrentHealth() > target.getHealth()) target.setCurrentHealth(target.getHealth());
            }
        }

    }
}
