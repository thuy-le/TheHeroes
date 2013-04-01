package vn.edu.rmit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import vn.edu.rmit.Controller.MapController;
import vn.edu.rmit.GUI.MainFrame;
import vn.edu.rmit.Model.GameEngine;
import vn.edu.rmit.Server.Server;
import vn.edu.rmit.Utilities.ApplicationContextUtils;
import vn.edu.rmit.Utilities.HexGrid;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: ${KATE.LE}
 * Date: 3/9/13
 * Time: 8:23 PM
 */

public class GameClient implements Runnable {
    private final String HOST = "localhost";
    private final int PORT = 13000;
    private static Socket socket;
    private ApplicationContextUtils acu;
    private ApplicationContext ctx;
    private MainFrame mainFrame;
    private HexGrid hexGrid;
    private MapController mapController;
    private static ObjectOutputStream os = null;
    private static ObjectInputStream is = null;

    public static void main(String[] args) {
        GameClient client = new GameClient();
        client.initializeConnection();
        if (socket != null && os != null && is != null) {
            Thread thread = new Thread(new GameClient());
            thread.start();
        }
    }

    @Override
    public void run() {
        acu = new ApplicationContextUtils();
        acu.setApplicationContext(new ClassPathXmlApplicationContext("Beans.xml"));
        ctx = ApplicationContextUtils.getApplicationContext();
        mainFrame = (MainFrame) ctx.getBean("MainFrame");
        hexGrid = (HexGrid) ctx.getBean("HexGrid");
        Server server = new Server(socket, is, os);
        GameEngine gameEngine = new GameEngine(server);
        mapController = new MapController(hexGrid, mainFrame, gameEngine);
        gameEngine.addObserver(mainFrame);
        gameEngine.addObserver(hexGrid);
        gameEngine.read();
    }

    private void initializeConnection() {
        try {
            socket = new Socket(HOST, PORT);
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
