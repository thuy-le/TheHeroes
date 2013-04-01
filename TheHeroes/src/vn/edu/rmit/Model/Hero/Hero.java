package vn.edu.rmit.Model.Hero;

import vn.edu.rmit.Model.GameObject;
import vn.edu.rmit.Model.Player;
import vn.edu.rmit.Model.Skill.Skill;
import vn.edu.rmit.Model.Team;
import vn.edu.rmit.Utilities.Hexagon;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/16/13
 * Time: 11:38 AM
 */
public class Hero extends GameObject {
    private int move;
    private int mana;
    private int currentMana;
    private List<Skill> skills;
    private String imagePath;

    public Hero() {

    }

    public Hero(String name, int health, int attack, int defense, int view, int range, int move, int mana, List<Skill> skills, Team team, Player player, String imagePath) {
        super(name, health, attack, defense, view, range, team, player);
        this.move = move;
        this.mana = mana;
        this.currentMana = this.mana;
        this.skills = skills;
        this.imagePath = imagePath;
    }

    public void move(Hexagon hex) {
        /*if (hex.getMoveAvail() == true) {
            this.setPosition(hex);
        } */
    }

    public void useSkill(Skill skill, List<Hero> targets) {
        skill.takeEffect(this, targets);
    }

    public void skip() {

    }

    public void generateMana() {
        currentMana += mana * 0.05;
        if (currentMana > mana) currentMana = mana;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    protected void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    public String getImagePath(){
        return imagePath;
    }
}
