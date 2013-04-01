package vn.edu.rmit.Model;

import vn.edu.rmit.Model.Hero.Hero;
import vn.edu.rmit.Utilities.Hexagon;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/16/13
 * Time: 12:16 PM
 */
public class Player {
    private String name;
    private Team team;
    private Room room;
    private boolean isRoomKey;
    private List<Hero> heroes;
    private Hexagon currentPlace;
    private static final int MAX_HEROES = 3;

    public Player(String name) {
        this.name = name;
        this.isRoomKey = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public void addHero (Hero hero) {
        if (heroes.size() < MAX_HEROES) {
            heroes.add(hero);
        }
    }

    public boolean isRoomKey() {
        return  isRoomKey;
    }

    public void setRoomKey() {
        this.isRoomKey = true;
    }

    public Hexagon getCurrentPlace() {
        return currentPlace;
    }

    public void setCurrentPlace(Hexagon currentPlace) {
        this.currentPlace = currentPlace;
    }
}
