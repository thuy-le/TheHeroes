package vn.edu.rmit;

import vn.edu.rmit.Model.Player;
import vn.edu.rmit.Server.ClientThread;
import vn.edu.rmit.Server.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameServer {

    private static final int PORT = 13000;
    private static final int MAX_CLIENT = 2;
    private static Socket clientSocket = null;
    private static ServerSocket serverSocket = null;
    private static ClientThread threads[] = new ClientThread[MAX_CLIENT];

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                clientSocket = serverSocket.accept();

                int i;
                for (i = 0; i < MAX_CLIENT; i++) {
                    if(threads[i] == null){
                        Player player = new Player("Player " + (i+1));
                        ObjectOutputStream os = new ObjectOutputStream(clientSocket.getOutputStream());
                        ObjectInputStream is = new ObjectInputStream(clientSocket.getInputStream());
                        Server server = new Server(clientSocket, is, os);
                        threads[i] = new ClientThread(threads, server, player);
                        threads[i].start();
                        break;
                    }
                    if(i == MAX_CLIENT){
                        ObjectOutputStream os = new ObjectOutputStream(clientSocket.getOutputStream());
                        os.writeObject("Game server is too busy, sorry");
                        os.close();
                        clientSocket.close();
                    }
                }
            }
        }catch(UnknownHostException ex){
            System.out.println(ex.getMessage());
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
