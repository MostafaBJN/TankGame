package Service.Client;

import Service.Command;
import Service.Player;
import game.MenuGUI.GUIManager;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientMain extends Command {

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
        inputStream = null;
        outputStream = null;
        loggedPlayer = null;
        int tries = 0;
        //wait till connect
        while (true) {
            try {
                tries++;
                socket = new Socket("127.0.0.1", MAIN_SERVER_PORT);
                if(socket.isConnected()){
                    //new Thread(new GUIManager.ShowMessage("Connected Successfully\nWELCOME", "Connection to Server", GUIManager.ShowMessage.INFORMATION)).start();
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

        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e){
            e.printStackTrace();
            new GUIManager.ShowMessage("Game Server is not Available!\nTry Again Later", "Connection to Server", GUIManager.ShowMessage.ERROR).run();
            System.exit(-1);
        }
        GUIManager.openLogin();
    }

    /**
     * sign up a player to server
     */
    public static void signUp(String username, String password) throws IOException {
        //TODO send a command to server which i want to SIGN UP with player info
        outputStream.writeInt(0);
        outputStream.writeObject(new Player(username, password));
    }

    /**
     * RETURN OF SERVER ABOUT LOGIN
     */
    public static final int ERROR = -1;
    public static final int WRONG_PASSWORD = 2;
    public static final int NO_USERNAME = 1;
    public static final int SUCCESSFUL = 0;

    /**
     * get info from login GUI and check with server and give result
     *
     * @return result of trying to login
     */
    public static int login(String username, String password, boolean keepLogin) throws IOException {
        //TODO send a command to server which i want to LOGIN with player info
        outputStream.writeInt(0);
        outputStream.writeObject(new Player(username, password, keepLogin));
        return inputStream.readInt();
    }

    /**
     * get Player from server list and add to loggedPlayer
     *
     * @return result of logging in
     */
    public static boolean loggingIn(String username, String password, boolean keepLogin) throws IOException {
        //TODO send a command to server which i want to get Player Info
        outputStream.writeInt(0);
        outputStream.writeObject(new Player(username, password, keepLogin));
        Object object = null;
        try {
            object = inputStream.readObject();
            if (object instanceof Player){
                //TODO server side
//            Player player = (Player) object;
//            player.setKeepLogin(keepLogin);
                loggedPlayer = (Player) object;
                return true;
            }
            else {
                loggedPlayer = null;
                return false;
            }
        } catch (ClassNotFoundException e) {
            loggedPlayer = null;
            return false;
        }
    }

    public static Socket getSocket() {
        return socket;
    }

    public static Player getLoggedPlayer() {
        return loggedPlayer;
    }
}
