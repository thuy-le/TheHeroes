package vn.edu.rmit.Server;

import vn.edu.rmit.Model.Player;
import vn.edu.rmit.Utilities.Hexagon;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/30/13
 * Time: 5:04 PM
 */

public class ClientThread extends Thread {

    private final ClientThread threads[];
    private int maxClient;
    private Server server;
    private Player player;

    public ClientThread(ClientThread[] threads, Server server, Player player) {
        this.threads = threads;
        maxClient = threads.length;
        this.server = server;
        this.player = player;
    }

    public void run() {
        int maxClient = this.maxClient;
        ClientThread[] threads = this.threads;
        String name = "";
        server.write("Start");
        synchronized (this) {
            for (int i = 0; i < maxClient; i++) {
                if (threads[i] != null && threads[i] == this) {
                    name = threads[i].player.getName();
                    server.write("_" + name);
                    break;
                }
            }
        }
        synchronized (this) {
            for (int i = 0; i < maxClient; i++) {
                if (threads[i] != null && threads[i] != this) {
                    threads[i].server.write("newcreated");
                }
            }
        }
        while (true) {

            Object msg = server.read();
            if (msg == null) {
                server.write(null);
                break;
            }
            if (msg instanceof String) {
                String line = msg.toString();
                if(line.equalsIgnoreCase("start")){

                }
                else{
                synchronized (this) {
                    for (int i = 0; i < maxClient; i++) {
                        if (threads[i] != null ) {
                            threads[i].server.write("<" + name + "> " + line);
                        }
                    }
                }
                }
            }

            else if(msg instanceof Hexagon){
                Hexagon h = (Hexagon) msg;
                synchronized (this) {
                    for (int i = 0; i < maxClient; i++) {
                        if (threads[i] != null && threads[i] != this) {
                            threads[i].server.write(h);
                        }
                    }
                }
            }
        }
        synchronized (this) {
            for (int i = 0; i < maxClient; i++) {
                if (threads[i] != null && threads[i] != this
                        ) {
                    threads[i].server.write(name + " left");
                }
            }
        }
        synchronized (this) {
            for (int i = 0; i < maxClient; i++) {
                if (threads[i] == this) {
                    threads[i] = null;
                }
            }
        }
        server.close();
    }
}
