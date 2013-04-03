package vn.edu.rmit.Model;

import vn.edu.rmit.Model.Hero.Hero;
import vn.edu.rmit.Model.Hero.HeroFactory;
import vn.edu.rmit.Model.Hero.HeroType;
import vn.edu.rmit.Server.Server;
import vn.edu.rmit.Utilities.Hexagon;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: s3372751
 * Date: 31/03/13
 * Time: 10:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameEngine extends Observable {
    private Server server;
    private String chatMSG;
    private String playerName;
    private Hexagon opponentPosition;
    private String initID;
    private boolean newCreated;
    private List<Hero> listHeroes;

    public GameEngine(Server server) {
        this.server = server;
        chatMSG = "";
        newCreated = false;
    }

    public void read() {
        Object message = server.read();
        while (message != null) {
            if (message instanceof String) {
                if (((String) message).equalsIgnoreCase("Start")) ;
                else if (((String) message).charAt(0) == '_') {
                    playerName = message.toString().substring(1, ((String) message).length());
                    if (playerName.trim().equalsIgnoreCase("Player 1")) {
                        initID = "337";
                    } else initID = "336";
                } else if (((String) message).equalsIgnoreCase("newcreated")) {
                    newCreated = true;
                } else {
                    chatMSG = message.toString();
                }
                notifyObservers();
                setChanged();
            }
            if (message instanceof Hexagon) {
                opponentPosition = (Hexagon) message;
                notifyObservers();
                setChanged();
            }
            message = server.read();
        }
    }

    public List<Hero> initRandomHeroes() {
        List<Hero> heroes = new ArrayList<Hero>();
        List<HeroType> heroTypes = Arrays.asList(HeroType.values());
        Random r = new Random();
        List<Integer> integers = new ArrayList<Integer>();
        HeroFactory factory = new HeroFactory();
        for (int i = 0; i < 3; i++) {
            while (true) {
                int random = r.nextInt(heroTypes.size() - 1);
                Hero h = factory.createHero(heroTypes.get(random));
                if(integers.contains(random)) continue;
                integers.add(random);
                heroes.add(h);
                break;
            }
        }
        return heroes;
    }

    public List<Hero> getListHeroes() {
        return listHeroes;
    }

    public void writeChatMSG(String msg) {
        server.write(msg);
    }

    public void writeOpponentPosition(Hexagon hexagon) {
        server.write(hexagon);
    }

    public String getChatMSG() {
        return chatMSG;
    }

    public void setChatMSG(String chatMSG) {
        this.chatMSG = chatMSG;
    }

    public Hexagon getOpponentPosition() {
        return opponentPosition;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getInitID() {
        return initID;
    }

    public boolean isNewCreated() {
        return newCreated;
    }

    public void setNewCreated(boolean newCreated) {
        this.newCreated = newCreated;
    }

    public void terminate() {
        server.write(null);
    }

}
