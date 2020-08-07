package Game.Run;

import Game.GUIMenu.GUIManager;
import Game.Play.GameInfo;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RunGameClient extends Run {

    private static Socket socket;
    private static ObjectInputStream inputStream;
    private static ObjectOutputStream outputStream;

    public RunGameClient(GameInfo gameInfo) {
        super(gameInfo);
    }

    public static boolean connectToGame(int port) {
        try {
            socket = new Socket("127.0.0.1", port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            return true;
        } catch (IOException e) {
            new Thread(new GUIManager.ShowMessage("This Game Server Is Not Available", "Connection to Game", GUIManager.ShowMessage.ERROR)).start();
            return false;
        }
    }
}
