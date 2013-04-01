package vn.edu.rmit.Model;

import vn.edu.rmit.Server.Server;
import vn.edu.rmit.Utilities.Hexagon;

import javax.swing.*;
import java.io.IOException;
import java.util.Observable;

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

    public GameEngine(Server server) {
        this.server = server;
        chatMSG = "";
        newCreated = false;
    }

    public void read() {
        Object message = server.read();
        while (message != null) {
            if (message instanceof String) {
                if(((String) message).equalsIgnoreCase("Start"));
                else if(((String) message).charAt(0) == '_')
                {
                    playerName = message.toString().substring(1, ((String) message).length());
                    if(playerName.trim().equalsIgnoreCase("Player 1")){
                        initID = "337";
                    }
                    else initID = "336";
                }
                else if(((String) message).equalsIgnoreCase("newcreated")){
                    System.out.println("DC DUC");
                     newCreated = true;
                }
                else {
                    System.out.println(message.toString().length());
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

    public void writeChatMSG(String msg){
        server.write(msg);
    }

    public void writeOpponentPosition(Hexagon hexagon){
        server.write(hexagon);
    }

    public String getChatMSG(){
        return chatMSG;
    }

    public Hexagon getOpponentPosition(){
        return opponentPosition;
    }

    public String getPlayerName(){
        return playerName;
    }

    public String getInitID(){
        return initID;
    }

    public boolean isNewCreated(){
        return newCreated;
    }

    public void setNewCreated(boolean  newCreated){
        this.newCreated = newCreated;
    }

    public void terminate() {
        server.write(null);
    }
}
