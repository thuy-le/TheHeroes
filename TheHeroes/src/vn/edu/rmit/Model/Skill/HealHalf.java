package vn.edu.rmit.Model.Skill;

import vn.edu.rmit.Model.Hero.Hero;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/16/13
 * Time: 1:54 PM
 */
public class HealHalf extends Skill {

    public HealHalf(){
        setName("Heal Half");
        setType(SkillType.HEAL);
        setConsume(25);
    }

    @Override
    public void takeEffect(Hero hero, List<Hero> targets) {
        if (targets != null) {
            Hero target = targets.get(0);
            target.setCurrentHealth(target.getCurrentHealth() + target.getHealth() / 2);
            if (target.getCurrentHealth() > target.getHealth())
                target.setCurrentHealth(target.getHealth());
        }

    }


}
