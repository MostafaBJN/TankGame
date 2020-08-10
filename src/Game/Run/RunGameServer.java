package Game.Run;

import Game.Play.*;
import Service.Client.MainClient;
import Service.Command;
import Service.Player;
//import com.company.Server.Player;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class RunGameServer implements Runnable {


    protected ArrayList<Player> players;
    private GameInfo gameInfo;
    Player gameMakerPlayer;

    private ServerSocket serverSocket;
    protected ArrayList<Socket> connectionSockets;
    private GameMap gameMap;

    //all players info
    protected int countOfOnlinePlayers;


    public RunGameServer(GameInfo gameInfo) throws IOException {
        gameMap = null;
        players = new ArrayList<>();
        this.gameInfo = gameInfo;
        gameMakerPlayer = MainClient.getLoggedPlayer();
    }

    @Override
    public void run() {
        try {
            countOfOnlinePlayers = 0;
            connectionSockets = new ArrayList<>();
            serverSocket = new ServerSocket(gameInfo.getPort());
            while (countOfOnlinePlayers < gameInfo.getNumberOfPlayers()) {
                System.out.println("Online Players: " + countOfOnlinePlayers);
                Socket connectionSocket = serverSocket.accept();
                connectionSockets.add(connectionSocket);
                countOfOnlinePlayers++;
                gameInfo.playerJoin();
                Thread t = new Thread(new ClientCommand(connectionSocket, countOfOnlinePlayers, gameInfo));
                t.start();
            }
            runAGame();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public class ClientCommand extends Command implements Runnable {

        protected final Socket connectionSocket;
        protected final int clientNum;
        protected final ObjectInputStream inputStream;
        protected final ObjectOutputStream outputStream;
        protected final GameInfo firstGameInfo;

        public ClientCommand(Socket connectionSocket, int clientNum, GameInfo gameInfo) throws IOException {
            this.connectionSocket = connectionSocket;
            this.clientNum = clientNum;
            outputStream = new ObjectOutputStream(connectionSocket.getOutputStream());
            inputStream = new ObjectInputStream(connectionSocket.getInputStream());
            firstGameInfo = gameInfo;
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
                        System.out.println("BEFORE IN GAME COMMAND");
                        command = inputStream.readInt();
                        System.out.println("AFTER IN GAME COMMAND");
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
                case PlayGame.GET_GAME_INFO_UPDATE:
                    outputStream.writeObject(gameInfo);
                    outputStream.flush();
                    break;
                case PlayGame.GET_START_OF_GAME:
                    outputStream.writeObject(gameMap);
                    outputStream.flush();
                case PlayGame.GET_GAME_MAP_FROM_SERVER:

                case PlayGame.SEND_GAME_MAP_TO_SERVER:
                    outputStream.writeObject(gameMap);
                    outputStream.flush();
                //TODO MAKE A COMMAND MENU

            }
        }

    }

    public void runAGame() {
        if(gameMap == null){
            gameMap = new GameMap(gameInfo, players);
        }
    }

    public void runTheGame() {
        ThreadPool.init();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameStart();
            }
        });
    }

    private void gameStart() {
        GameLoop game = new GameLoop();
        game.init(gameMap);
        ThreadPool.execute(game);
    }
}
