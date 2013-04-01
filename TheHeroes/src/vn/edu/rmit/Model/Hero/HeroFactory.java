package vn.edu.rmit.Model.Hero;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 4/1/13
 * Time: 8:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class HeroFactory {
    public Hero createHero (HeroType type) {
        Hero hero;
        if (type == HeroType.SOLDIER) {
            hero = new Soldier();
        } else if (type == HeroType.INFANTRY) {
            hero = new Infantry();
        } else if (type == HeroType.MAIDEN) {
            hero = new Maiden();
        } else if (type == HeroType.THIEF) {
            hero = new Thief();
        } else if (type == HeroType.SPELLCASTER) {
            hero = new Spellcaster();
        } else if (type == HeroType.MAGE) {
            hero = new Mage();
        } else if (type == HeroType.FIGHTER) {
            hero = new Fighter();
        } else if (type == HeroType.HEALER) {
            hero = new Healer();
        } else return null;
        return hero;
    }
}
