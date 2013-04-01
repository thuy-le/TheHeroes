package vn.edu.rmit.Model;

import vn.edu.rmit.Model.Hero.Hero;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/16/13
 * Time: 1:40 PM
 */
public class Tower extends Base {
    public Tower() {

    }

    public void takeEffect(List<Hero> allies, List<Hero> enemies) {
        for (int i = 0; i < allies.size(); i++) {
            Hero ally = allies.get(i);
            Double diff = ally.getAttack() * 0.1;
            ally.setCurrentAttack(ally.getCurrentAttack() + diff.intValue());
            diff = ally.getDefense() * 0.1;
            ally.setCurrentDefense(ally.getCurrentDefense() + diff.intValue());
        }
        for (int i = 0; i < enemies.size(); i++) {
            Hero enemy = enemies.get(i);
            Double diff = enemy.getAttack() * 0.1;
            enemy.setCurrentAttack(enemy.getCurrentAttack() - diff.intValue());
            diff = enemy.getDefense() * 0.1;
            enemy.setCurrentDefense(enemy.getCurrentDefense() - diff.intValue());
        }
    }

    public void autoAttack(List<Hero> enemies) {
        for (int i = 0; i < enemies.size(); i++) {
            Hero enemy = enemies.get(i);
            attack(enemy);
        }
    }

}
