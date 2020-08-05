package Service.Server;

import Service.Command;
import Service.Player;
import Game.GUIMenu.GUIManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServer extends Command {

    protected static ServerSocket serverSocket;
    protected static ArrayList<Socket> connectionSockets;

    //all players info
    protected static ArrayList<Player> players;
    protected static int countOfOnlinePlayers;
//    private static ArrayList<GameServer> gameServers;
    //private static ArrayList<Thread> playerThreads;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        countOfOnlinePlayers = 0;
        connectionSockets = new ArrayList<>();
        new Thread(new LoadPlayer()).start();
        //new Thread(new CheckPlayersConnection()).start();
        try {
            serverSocket = new ServerSocket(MAIN_SERVER_PORT);
            while(true) {
                System.out.println("Online Players: " + countOfOnlinePlayers);
                Socket connectionSocket = serverSocket.accept();
                connectionSockets.add(connectionSocket);
                System.out.println(connectionSocket.getRemoteSocketAddress());
                countOfOnlinePlayers++;
                Thread t = new Thread(new ClientCommand(connectionSocket, countOfOnlinePlayers));
                t.start();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Load Data Of Player in A Thread
     */
    private static class LoadPlayer implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("LOADING");
                players = SaveAndLoad.<Player>loadListData(SaveAndLoad.PLAYER_FOLDER_PATH);
            } catch (IOException | ClassNotFoundException exception) {
                new GUIManager.ShowMessage("Can't Load Players", "Load", GUIManager.ShowMessage.ERROR);
                players = new ArrayList<>();
                exception.printStackTrace();
            }
            System.out.println("DONE");

        }
    }

    /**
     * Save Data Of Player in A Thread
     */
    private static class SavePlayer implements Runnable {

        Player player;

        public SavePlayer(Player player) {
            this.player = player;
        }

        @Override
        public void run() {
            try {
                System.out.println("SAVING");
                SaveAndLoad.<Player>saveObjectData(player, SaveAndLoad.PLAYER_FOLDER_PATH, "", SaveAndLoad.PLAYER_FILE_TYPE);
            } catch (IOException exception) {
                new Thread(new GUIManager.ShowMessage("Can't save Players", "Load", GUIManager.ShowMessage.ERROR)).start();
                players = new ArrayList<>();
                exception.printStackTrace();
            }
            System.out.println("DONE");
        }
    }

    public static class ClientCommand implements Runnable {

        protected final Socket connectionSocket;
        protected final int clientNum;
        protected final ObjectInputStream inputStream;
        protected final ObjectOutputStream outputStream;

        public ClientCommand(Socket connectionSocket, int clientNum) throws IOException {
            this.connectionSocket = connectionSocket;
            this.clientNum = clientNum;
            outputStream = new ObjectOutputStream(connectionSocket.getOutputStream());
            inputStream = new ObjectInputStream(connectionSocket.getInputStream());
        }

        @Override
        public void run() {
            try {
                //TODO first login info send
                int command = MainMenu.UNKNOWN;
                while (true) {
                    //a player connect
                    ;
                    //get Command
                    try {
                        System.out.println("BEFORE");
                        command = inputStream.readInt();
                        System.out.println("AFTER");
                        commandMenu(command);
                    }
                    catch (java.io.EOFException | java.net.SocketException eofException) {
                        System.out.println("Someone Left");
                        connectionSockets.remove(connectionSocket);
                        countOfOnlinePlayers--;
                        break;
                    }

                }
            } catch (IOException exception) {
                exception.printStackTrace();
            } finally {
                try {
                    connectionSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        private void commandMenu(int command) throws IOException {
            switch (command){
                case MainMenu.SIGN_UP:
                    try {
                        Object object = inputStream.readObject();
                        System.out.println("Reading");
                        if(object instanceof Player) {
                            Player player = (Player) object;
                            System.out.println(player.getUsername() + " " + player.getPassword());
                            players.add(player);
                            System.out.println("Added");
                            outputStream.writeInt(Login.SUCCESSFUL);
                            outputStream.flush();
                            System.out.println("Sent");
                            //SAVE Player
                            new Thread(new SavePlayer(player)).start();
                        }
                        else {
                            outputStream.writeInt(Login.ERROR);
                            outputStream.flush();
                        }
                    } catch (ClassNotFoundException | IOException e) {

                        e.printStackTrace();
                    }
                    break;
                case MainMenu.TRY_TO_LOGIN:
                    try {
                        Object object = inputStream.readObject();
                        System.out.println("Reading");
                        if (object instanceof Player) {
                            Player player = (Player) object;
                            for (Player playerOfList : players) {
                                if (playerOfList.equals(player)) {
                                    if(playerOfList.getPassword().equals(player.getPassword())) {
                                        System.out.println("OK FOR LOGIN");
                                        outputStream.writeInt(Login.SUCCESSFUL);
                                        outputStream.flush();
                                        return;
                                    }
                                    else {
                                        System.out.println("WRONG PASS");
                                        outputStream.writeInt(Login.WRONG_PASSWORD);
                                        outputStream.flush();
                                        return;
                                    }
                                }
                            }
                            System.out.println("NO USERNAME");
                            outputStream.writeInt(Login.NO_USERNAME);
                            outputStream.flush();
                        }
                        outputStream.writeInt(Login.ERROR);
                        outputStream.flush();
                    } catch (IOException | ClassNotFoundException exception) {
                        exception.printStackTrace();
                    }
                    break;
                case MainMenu.LOGGING_IN:
                    try {
                        Object object = inputStream.readObject();
                        System.out.println("Reading");
                        if (object instanceof Player) {
                            Player player = (Player) object;
                            for (Player playerOfList : players) {
                                if (playerOfList.equals(player)) {
                                    System.out.println("LOGGED");
                                    outputStream.writeObject(playerOfList);
                                    outputStream.flush();
                                    return;
                                }
                            }
                        }
                        outputStream.writeObject(null);
                        outputStream.flush();
                    } catch (IOException | ClassNotFoundException exception) {
                        exception.printStackTrace();
                    }
                    break;
                //TODO MAKE A COMMAND MENU

            }
        }

    }


}
