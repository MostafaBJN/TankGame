package Service.Client;

import Service.Command;
import Service.Player;
import Game.GUIMenu.*;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainClient extends Command {

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
    public static int signUp(String username, String password) throws IOException {
        outputStream.writeInt(0);
        outputStream.flush();
        outputStream.writeObject(new Player(username, password));
        outputStream.flush();
        int result = inputStream.readInt();
        return result;
    }

    /**
     * RETURN OF SERVER ABOUT LOGIN
     */

    /**
     * get info from login GUI and check with server and give result
     *
     * @return result of trying to login
     */
    public static int login(String username, String password, boolean keepLogin) throws IOException {
        outputStream.writeInt(MainMenu.TRY_TO_LOGIN);
        outputStream.flush();
        Player player = new Player(username, password, keepLogin);
        outputStream.writeObject(player);
        outputStream.flush();
        int result = inputStream.readInt();
        if(result == Login.SUCCESSFUL) {
            if(!loggingIn(player)){
                JOptionPane.showMessageDialog(null, "NULL!", "Login", JOptionPane.ERROR_MESSAGE);
            }
        }
        return result;
    }

    /**
     * get Player from server list and add to loggedPlayer
     *
     * @return result of logging in
     */
    public static boolean loggingIn(Player player) throws IOException {
        //TODO send a command to server which i want to get Player Info
        outputStream.writeInt(MainMenu.LOGGING_IN);
        outputStream.writeObject(player);
        Object object;
        try {
            object = inputStream.readObject();
            if (object instanceof Player){
                Player playerInList = (Player) object;
                player.setKeepLogin(player.isKeepLogin());
                loggedPlayer = playerInList;
                return true;
            }
            else {
                loggedPlayer = null;
                return false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            loggedPlayer = null;
            return false;
        }
    }

    public static void logout() {
        loggedPlayer = null;
    }

    public static Socket getSocket() {
        return socket;
    }

    public static Player getLoggedPlayer() {
        return loggedPlayer;
    }
}
