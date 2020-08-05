package Game.Run;

import Map.Map;
import Service.Command;
import Service.Player;
import Service.Server.MainServer;
//import com.company.Server.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class RunGameServer extends Run {


    private int port;
    private ServerSocket serverSocket;
    private Socket connectionSocket;

    protected ArrayList<Socket> connectionSockets;

    //all players info
    protected ArrayList<Player> players;
    protected int countOfOnlinePlayers;


    static int playingWithComputer;
    static int grouhi;
    int countOfPlayers;
    static int league;
    int livesOfTank;
    int powerOfTir;
//    int livesOfWall;
    String nameOfGame = "";
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;
    Player player;
//    static Map map;
//    static ArrayList<Player> group1 = null;
//    static ArrayList<Player> group2 = null;
//    static ArrayList<Player> allPlayers = null;
//    static int countOfWinGroup1 = 0;
//    static int countOfWinGroup2 = 0;

    public RunGameServer(int port) throws IOException {
        //TODO check port from server and add here
        this.port = port;
        //TODO Check Setting
        startServer();
    }

    public void startServer() throws IOException {

        try {
            serverSocket = new ServerSocket(port);
            while(countOfOnlinePlayers < countOfPlayers) {
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

//
//        player = new Player(connectionSocket,);
//        Thread t = new Thread(player);
//        try
//        {
//            outputStream.writeObject("you are first player");
//
//
//            String user = (String) inputStream.readObject();
//            String pass = (String) inputStream.readObject();
//            player.setUserName(user);
//            player.setPassword(pass);
//
//
//            outputStream.writeObject("name bazi ra vared konid");
//
//
//            nameOfGame = (String) inputStream.readObject();
//
//
//            outputStream.writeObject("tedad jan tankHa ra vared konid.");
//
//
//            String livesOfTankString = (String) inputStream.readObject();
//            livesOfTank = Integer.parseInt(livesOfTankString);
//
//
//            outputStream.writeObject("ghodrat tirHa ravared konid.");
//
//
//            String powerOfTirString = (String) inputStream.readObject();
//            powerOfTir = Integer.parseInt(powerOfTirString);
//
//
//            outputStream.writeObject("tedad jan divarHa ra vared konid.");
//
//
//            String playingWithComputerString = (String) inputStream.readObject();
//            playingWithComputer = Integer.parseInt(playingWithComputerString);
//
//
//            outputStream.writeObject("bazi ba digaran:Input 1  bazi ba computer:Input 2");
//
//
//            playingWithComputerString = (String) inputStream.readObject();
//            playingWithComputer = Integer.parseInt(playingWithComputerString);
//
//
//            outputStream.writeObject("tak be tak:Input 1  grouhi:Input 2");
//
//
//            String grouhiString = (String) inputStream.readObject();
//            grouhi = Integer.parseInt(grouhiString);
//
//
//            outputStream.writeObject("tedade bazikonan ra vared konid");
//
//
//            String countOfPlayersString = (String) inputStream.readObject();
//            countOfPlayers = Integer.parseInt(countOfPlayersString);
//
//
//            outputStream.writeObject("tedade bazihaye league ra vared konid");
//
//
//            String leagueString = (String) inputStream.readObject();
//            league = Integer.parseInt(leagueString);
//        }
//        catch (IOException | ClassNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//        t.start();
    }


    public class ClientCommand extends Command implements Runnable {

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
                int command = Command.MainMenu.UNKNOWN;
                while (true) {
                    //a player connect
                    ;
                    //get Command
                    try {
                        System.out.println("BEFORE IN GAME");
                        command = inputStream.readInt();
                        System.out.println("AFTER IN GAME");
                        commandMenu(command);
                    }
                    catch (java.io.EOFException | java.net.SocketException eofException) {
                        System.out.println("Someone Left The Game");
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
                case PlayGame.SEND_GAME_STATE:
                    break;
                case PlayGame.GET_GAME_STATE:
                    break;
                case MainMenu.LOGGING_IN:
                    break;
                //TODO MAKE A COMMAND MENU

            }
        }

    }
}
