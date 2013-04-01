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
    private static BufferedReader inputLine = null;

    public static void main(String[] args) {
        GameClient client = new GameClient();
        try {
            client.initializeConnection();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (socket != null && os != null && is != null) {
            Thread thread = new Thread(new GameClient());
            thread.start();
            while (true) {
                try {
                    os.writeObject(inputLine.readLine());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void run() {
        acu = new ApplicationContextUtils();
        acu.setApplicationContext(new ClassPathXmlApplicationContext("Beans.xml"));
        ctx = ApplicationContextUtils.getApplicationContext();
        mainFrame = (MainFrame) ctx.getBean("MainFrame");
        hexGrid = (HexGrid) ctx.getBean("HexGrid");
        Server server = new Server(socket,is,os);
        GameEngine chatEngine = new GameEngine(server);
        //BackupEngine gameEngine = new BackupEngine(server);
        mapController = new MapController(hexGrid, mainFrame, chatEngine);
        /**/
        chatEngine.addObserver(mainFrame);
        chatEngine.addObserver(hexGrid);
        /**/
        chatEngine.read();
    }

    private void initializeConnection()
            throws UnknownHostException, IOException {
        socket = new Socket(HOST, PORT);
        inputLine = new BufferedReader(new InputStreamReader(System.in));
        os = new ObjectOutputStream(socket.getOutputStream());
        is = new ObjectInputStream(socket.getInputStream());
    }

    public void closeConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.exit(0);
    }
}
