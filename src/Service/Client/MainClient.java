package Service.Client;

import Game.Play.GameInfo;
import Game.Run.Run;
import Thing.Map.MapManager;
import Service.Command;
import Service.Player;
import Game.GUIMenu.*;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MainClient extends Command {

    private static Socket socket;
    private static ObjectInputStream inputStream;
    private static ObjectOutputStream outputStream;

    private static Player loggedPlayer;

    public static void main(String[] args) throws IOException, InterruptedException {

        MapManager.loadAllMaps();

        //extra
        MapManager.selectMap(Run.selectRandomMap().getName());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        //wait till connect
        tryToConnectToServer();

        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Game Server is not Available!\nTry Again Later", "Connection to Server", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }


        GUIManager.openLogin();
    }

    /**
     * try to coonect to server
     */
    public static void tryToConnectToServer() throws InterruptedException {
        loggedPlayer = null;
        int tries = 0;
        while (true) {
            try {
                tries++;
                socket = new Socket("127.0.0.1", MAIN_SERVER_PORT);
                if(socket.isConnected()){
                    break;
                }
            } catch (IOException e) {
                if(tries > 50){
                    JOptionPane.showMessageDialog(null,"Game Server is not Available!\nTry Again Later", "Connection to Server", JOptionPane.ERROR_MESSAGE);
                    System.exit(-1);
                }
                else {
                    if(tries % 5 == 1)
                        new Thread(new GUIManager.ShowMessage("Can't Connect to the Game Server\nTrying Again", "Connection to Server", GUIManager.ShowMessage.ERROR)).start();
                }
                //Thread.sleep(1000);
            }
        }
    }

    /**
     * sign up a player to server
     */
    public static int signUp(String username, String password) throws IOException {
        outputStream.writeInt(0);
        outputStream.flush();
        outputStream.writeObject(new Player(username, password));
        outputStream.flush();
        return inputStream.readInt();
    }

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

    /*
     * get available servers from main server
     *
     * @return games servers available
     */
    public static ArrayList<GameInfo> playMultiPlayer() throws IOException, ClassNotFoundException {
        outputStream.writeInt(MainMenu.GET_LIST_OF_MULTIPLAYER_GAMES);
        outputStream.flush();
        int numberOfGames = inputStream.readInt();
        ArrayList<GameInfo> gameInfos = new ArrayList<>();
        for (int i = 0; i < numberOfGames; i++) {
            Object object = inputStream.readObject();
            GameInfo gameInfo = (GameInfo) object;
            gameInfo.getMap().settingImagesOfGrounds();
            gameInfos.add(gameInfo);
        }
        return gameInfos;
    }

    /**
     * Add A game to server list
     */
    public static void addGame(GameInfo gameInfo) throws IOException {
        outputStream.writeInt(MainMenu.ADD_GAME);
        outputStream.flush();
        outputStream.writeObject(gameInfo);
        outputStream.flush();
    }

    /**
     * get Player from server list and add to loggedPlayer
     *
     * @return result of logging in
     */
    public static boolean loggingIn(Player player) throws IOException {
        outputStream.writeInt(MainMenu.LOGGING_IN);
        outputStream.flush();
        outputStream.writeObject(player);
        outputStream.flush();
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

    public static void sendPlayerInfoToServer(Player player) throws IOException {
        outputStream.writeInt(MainMenu.SEND_PLAYER_INFO_TO_SERVER);
        outputStream.flush();
        outputStream.writeObject(player);
        outputStream.flush();
    }

    public static Player receivePlayerInfo(String username) throws IOException {
        outputStream.writeInt(MainMenu.GET_PLAYER_INFO_FROM_SERVER);
        outputStream.flush();
        outputStream.writeObject(new Player(username, ""));
        outputStream.flush();
        Object object;
        Player player;
        try {
            object = inputStream.readObject();
            if (object instanceof Player){
                return (Player) object;
            }
            else {
                return null;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Socket getSocket() {
        return socket;
    }

    public static Player getLoggedPlayer() {
        return loggedPlayer;
    }

    public static void runGame(GameInfo gameInfo) {

    }
}
