package vn.edu.rmit.Model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/16/13
 * Time: 12:15 PM
 */
public class Team {
    private String name;
    private Room room;
    private List<Player> players;
    private int maxMembers;

    public Team(String name, Room room) {
        this.name = name;
        this.room = room;
        if (room.getType() == RoomType.ONE_VS_ONE) {
            this.maxMembers = 1;
        }
        if (room.getType() == RoomType.THREE_VS_THREE) {
            this.maxMembers = 3;
        }
        if (room.getType() == RoomType.FIVE_VS_FIVE) {
            this.maxMembers = 5;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(int maxMembers) {
        this.maxMembers = maxMembers;
    }

    public void addPlayer (Player player) {
        if (players.size() < maxMembers) {
            players.add(player);
        }
    }
}
