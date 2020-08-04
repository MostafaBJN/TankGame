package Service.Server;

import Service.Command;
import Service.Player;
import game.MenuGUI.GUIManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class MainServer extends Command {

    private static ServerSocket serverSocket;
    private static ArrayList<Socket> connectionSockets;

    //all players info
    private static ArrayList<Player> players;
    private static int countOfPlayers;
//    private static ArrayList<GameServer> gameServers;
    //private static ArrayList<Thread> playerThreads;

    private static ObjectInputStream inputStream;
    private static ObjectOutputStream outputStream;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        countOfPlayers = 0;
        connectionSockets = new ArrayList<>();
        new Thread(new LoadPlayer()).start();
        //new Thread(new CheckPlayersConnection()).start();
        try {
            serverSocket = new ServerSocket(MAIN_SERVER_PORT);
            while(true) {
                Socket connectionSocket = serverSocket.accept();
                connectionSockets.add(connectionSocket);
                System.out.println(connectionSocket.getRemoteSocketAddress());
                countOfPlayers++;
                Thread t = new Thread(new ClientCommand(connectionSocket, countOfPlayers));
                t.start();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static class LoadPlayer implements Runnable {

        @Override
        public void run() {
            try {
                players = SaveAndLoad.<Player>loadListData(SaveAndLoad.PLAYER_FOLDER_PATH);
            } catch (IOException | ClassNotFoundException exception) {
                new GUIManager.ShowMessage("Can't Load Players", "Load", GUIManager.ShowMessage.ERROR);
                players = new ArrayList<>();
                exception.printStackTrace();
            }
        }
    }

//    private static class CheckPlayersConnection implements Runnable {
//
//        @Override
//        public void run() {
//            while (true) {
//                Iterator<Socket> it = connectionSockets.iterator();
//                while (it.hasNext()){
//                    System.out.println(it.next());
//                    try {
//                        command = inputStream.readInt();
//                    }
//                    catch (java.io.EOFException eofException) {
//                        System.out.println("FUCK");
//                        break;
//                    }
//                }
//            }
//        }
//    }

    static class ClientCommand implements Runnable {

        private Socket connectionSocket;
        private int clientNum;

        public ClientCommand(Socket connectionSocket, int clientNum) {
            this.connectionSocket = connectionSocket;
            this.clientNum = clientNum;
        }

        @Override
        public void run() {
            try {
                outputStream = new ObjectOutputStream(connectionSocket.getOutputStream());
                inputStream = new ObjectInputStream(connectionSocket.getInputStream());
                //TODO first login info send
                int command = UNKNOWN;
                while (true) {
                    //a player connect
                    ;
                    //get Command
                    try {
                        command = inputStream.readInt();
                    }
                    catch (java.io.EOFException eofException) {
                        System.out.println("FUCK");
                        break;
                    }
                    switch (command){
                        case SIGN_UP:
                            try {
                                Object object = inputStream.readObject();
                                if(object instanceof Player){
                                    Player player = (Player) object;
                                    players.add(player);
                                    //SAVE Player
                                    SaveAndLoad.<Player>saveObjectData(player, SaveAndLoad.PLAYER_FOLDER_PATH, "", SaveAndLoad.PLAYER_FILE_TYPE);
                                    //TODO save
                                }
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            System.out.println("hello");
                            break;
                        case LOGGING_IN:
                            break;
                        case TRY_TO_LOGIN:
                            break;
                            //TODO MAKE A COMMAND MENU

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


//        public void logIn()
//        {
//            try
//            {
//                serverSocket = new ServerSocket(7000);
//                try
//                {
//                    connectionSocket = serverSocket.accept();//waiting for connection.
//                    outputStream = new ObjectOutputStream(connectionSocket.getOutputStream());
//                    inputStream = new ObjectInputStream(connectionSocket.getInputStream());
//                }
//                catch(IOException e)
//                {
//                    e.printStackTrace();
//                }
//                player = new Player(connectionSocket, false);
//                Thread t = new Thread(player);
//                try
//                {
//                    outputStream.writeObject("you are first player");
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    String user = (String) inputStream.readObject();
//                    String pass = (String) inputStream.readObject();
//                    player.setUserName(user);
//                    player.setPassword(pass);
//                }
//                catch (IOException | ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    outputStream.writeObject("name bazi ra vared konid");
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    nameOfGame = (String) inputStream.readObject();
//                }
//                catch (IOException | ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    outputStream.writeObject("tedad jan tankHa ra vared konid.");
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    String livesOfTankString = (String) inputStream.readObject();
//                    livesOfTank = Integer.parseInt(livesOfTankString);
//                }
//                catch (IOException | ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    outputStream.writeObject("ghodrat tirHa ravared konid.");
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    String powerOfTirString = (String) inputStream.readObject();
//                    powerOfTir = Integer.parseInt(powerOfTirString);
//                }
//                catch (IOException | ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    outputStream.writeObject("tedad jan divarHa ra vared konid.");
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    String playingWithComputerString = (String) inputStream.readObject();
//                    playingWithComputer = Integer.parseInt(playingWithComputerString);
//                }
//                catch (IOException | ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    outputStream.writeObject("bazi ba digaran:Input 1  bazi ba computer:Input 2");
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    String playingWithComputerString = (String) inputStream.readObject();
//                    playingWithComputer = Integer.parseInt(playingWithComputerString);
//                }
//                catch (IOException | ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    outputStream.writeObject("tak be tak:Input 1  grouhi:Input 2");
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    String grouhiString = (String) inputStream.readObject();
//                    grouhi = Integer.parseInt(grouhiString);
//                }
//                catch (IOException | ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    outputStream.writeObject("tedade bazikonan ra vared konid");
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    String countOfPlayersString = (String) inputStream.readObject();
//                    countOfPlayers = Integer.parseInt(countOfPlayersString);
//                }
//                catch (IOException | ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    outputStream.writeObject("tedade bazihaye league ra vared konid");
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    String leagueString = (String) inputStream.readObject();
//                    league = Integer.parseInt(leagueString);
//                }
//                catch (IOException | ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                t.start();
//            }
//            catch (java.net.SocketException e)
//            {
//                e.printStackTrace();
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }
    }

}
