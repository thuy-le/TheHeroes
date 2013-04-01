package vn.edu.rmit.Model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/16/13
 * Time: 1:53 PM
 */
public class Room {
    private String name;
    private RoomType type;
    private List<Team> teams;
    private List<Player> players;
    private static final int MAX_TEAMS = 2;
    private int maxPlayers;

    public Room(String name, RoomType type) {
        this.name = name;
        this.type = type;
        if (type == RoomType.ONE_VS_ONE) {
            this.maxPlayers = 1;
        }
        if (type == RoomType.THREE_VS_THREE) {
            this.maxPlayers = 3;
        }
        if (type == RoomType.FIVE_VS_FIVE) {
            this.maxPlayers = 5;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void addTeam(Team team) {
        if (teams.size() < MAX_TEAMS) {
            teams.add(team);
        }
    }

    public void addPlayer(Player player) {
        if (players.size() < maxPlayers) {
            players.add(player);
        }
    }
}
