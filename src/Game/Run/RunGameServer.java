package Game.Run;

import Game.Play.GameInfo;
import Service.Client.MainClient;
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


    private ServerSocket serverSocket;
    private Socket connectionSocket;
    Player gameMakerPlayer;

    protected ArrayList<Socket> connectionSockets;

    //all players info
    protected int countOfOnlinePlayers;


    public RunGameServer(GameInfo gameInfo) throws IOException {
        super(gameInfo);
        gameMakerPlayer = MainClient.getLoggedPlayer();
        startServer();
    }

    public void startServer() throws IOException {
        try {
            countOfOnlinePlayers = 1;
            connectionSockets = new ArrayList<>();
            serverSocket = new ServerSocket(gameInfo.getPort());
            while (countOfOnlinePlayers < gameInfo.getNumberOfPlayers()) {
                System.out.println("Online Players: " + countOfOnlinePlayers);
                Socket connectionSocket = serverSocket.accept();
                connectionSockets.add(connectionSocket);
                countOfOnlinePlayers++;
                Thread t = new Thread(new ClientCommand(connectionSocket, countOfOnlinePlayers));
                t.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
            } catch (IOException | ClassNotFoundException exception) {
                exception.printStackTrace();
            } finally {
                try {
                    connectionSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        private void commandMenu(int command) throws IOException, ClassNotFoundException {
            switch (command){
                case PlayGame.SEND_PLAYER_INFO_TO_GAME_SERVER:
                    Object object = inputStream.readObject();
                    if(object instanceof Player) {
                        Player player = (Player) object;
                        players.add(player);
                    }
                    break;

                //TODO MAKE A COMMAND MENU

            }
        }

    }
}
