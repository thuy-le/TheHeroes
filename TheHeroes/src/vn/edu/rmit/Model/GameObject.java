package vn.edu.rmit.Model;

import vn.edu.rmit.Utilities.Hexagon;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/16/13
 * Time: 1:26 PM
 */
public class GameObject {
    private String name;
    private int health;
    private int currentHealth;
    private int attack;
    private int currentAttack;
    private int defense;
    private int currentDefense;
    private int view;
    private int range;
    private Team team;
    private Player player;
    private Hexagon position;

    public GameObject(){

    }

    public GameObject(String name, int health, int attack, int defense, int view, int range, Team team, Player player) {
        this.name = name;
        this.health = health;
        this.currentHealth = this.health;
        this.attack = attack;
        this.currentAttack = this.attack;
        this.defense = defense;
        this.currentDefense = this.defense;
        this.view = view;
        this.range = range;
        this.team = team;
        this.player = player;
    }

    public void attack(GameObject target){
        if (!checkAlly(target)){
            target.setCurrentHealth(target.getCurrentHealth() - this.getCurrentAttack());
            if (target.getCurrentHealth() < 0){
                target.setCurrentHealth(0);
            }
        }
    }
    public void defense(){

    }
    public void expandView(){

    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getCurrentHealth(){
        return currentHealth;
    }

    public void setCurrentHealth(int health){
        this.currentHealth = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getCurrentAttack() {
        return currentAttack;
    }

    public void setCurrentAttack(int currentAttack) {
        this.currentAttack = currentAttack;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getCurrentDefense() {
        return currentDefense;
    }

    public void setCurrentDefense(int currentDefense) {
        this.currentDefense = currentDefense;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Hexagon getPosition() {
        return position;
    }

    public void setPosition(Hexagon position) {
        this.position = position;
    }

    public boolean checkAlly(GameObject target){
        if (target.getTeam() == this.getTeam()){
            return true;
        } else {
            return false;
        }
    }
}
