package Game.Run;

import Game.GUIMenu.GUIManager;
import Game.Play.GameInfo;
import Game.Play.GameMap;
import Service.Command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RunGameClient implements Runnable {

    private static GameInfo gameInfo;
    public static Socket socket;
    public static ObjectInputStream inputStream;
    public static ObjectOutputStream outputStream;
    public static GameMap gameMap;

    public RunGameClient(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public static boolean connectToGame(int port) {
        try {
            socket = new Socket("127.0.0.1", port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connected To game server");
            return true;
        } catch (IOException e) {
            new Thread(new GUIManager.ShowMessage("This Game Server Is Not Available", "Connection to Game", GUIManager.ShowMessage.ERROR)).start();
            return false;
        }
    }

    @Override
    public void run() {
        while (!gameInfo.gameStart()) {
            System.out.println("NOT READY");
        }
        try {
            outputStream.writeInt(Command.PlayGame.GET_START_OF_GAME);
            outputStream.flush();
            gameMap = (GameMap) inputStream.readObject();
            new Run(gameMap).runTheGame();
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public static GameInfo getGameInfo() {
        return gameInfo;
    }

    public static void setGameInfo(GameInfo gameInfo1) {
        gameInfo = gameInfo1;
    }
}
