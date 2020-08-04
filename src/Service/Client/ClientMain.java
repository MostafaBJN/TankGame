package Service.Client;

import Service.Player;
import game.MenuGUI.GUIManager;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientMain {

    public static final int MAIN_SERVER_PORT = 9999;

    private static Socket socket;
    private static ObjectInputStream inputStream;
    private static ObjectOutputStream outputStream;

    private static Player loggedPlayer;

    public static void main(String[] args) throws InterruptedException, IOException {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        Socket socket = null;
        inputStream = null;
        outputStream = null;
        int tries = 0;
        //wait til connect
        while (true) {
            try {
                tries++;
                socket = new Socket("127.0.0.1", MAIN_SERVER_PORT);
                if(socket.isConnected()){
                    new Thread(new GUIManager.ShowMessage("Connected Successfully\nWELCOME", "Connection to Server", GUIManager.ShowMessage.INFORMATION)).start();
                    break;
                }
            } catch (IOException e) {
                if(tries > 30){
                    new GUIManager.ShowMessage("Game Server is not Available!\nTry Again Later", "Connection to Server", GUIManager.ShowMessage.ERROR).run();
                    System.exit(-1);
                }
                else {
                    if(tries % 5 == 1)
                        new Thread(new GUIManager.ShowMessage("Can't Connect to the Game Server\nTrying Again", "Connection to Server", GUIManager.ShowMessage.ERROR)).start();
                }
                Thread.sleep(1000);
            }
        }

        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());

        //TODO Loading Files From Server
        GUIManager.openLogin();
    }

    public static void signUp(String username, String password) {
        username;
        password;
        outputStream.writeInt(0);
        outputStream.writeObject(new Player(username, password));
    }

    public static final int WRONG_PASSWORD = 2;
    public static final int NO_USERNAME = 1;
    public static final int SUCCESSFUL = 0;

    public static int login(String username, String password, boolean keepLogin) throws IOException {
        //TODO send user info and get result
        outputStream.writeInt(0);
        outputStream.writeObject(new Player(username, password, keepLogin));
        return inputStream.readInt();
    }
}
