package Game.Run;

import Game.Play.*;
import Service.Client.MainClient;
import Service.Player;

import java.awt.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class RunGameOffline {

    protected ArrayList<Player> players;
    private GameInfo gameInfo;
    Player gameMakerPlayer;
    private GameMap gameMap;

    public RunGameOffline(ArrayList<Player> players, GameInfo gameInfo, Player gameMakerPlayer) {
        this.players = players;
        this.gameInfo = gameInfo;
        this.gameMakerPlayer = gameMakerPlayer;
        gameMap = new GameMap(gameInfo,players);
        runTheGame();
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

        GameFrame gameFrame = new GameFrame("Tank Trouble");
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
        gameFrame.initBufferStrategy();
        // Create and execute the game-loop
        GameLoop game = new GameLoop(gameFrame);
        game.init(gameMap, gameMakerPlayer, true);
        ThreadPool.execute(game);
        // and the game starts ...

    }
}
