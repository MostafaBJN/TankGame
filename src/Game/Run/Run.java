package Game.Run;

import Service.Client.MainClient;
import Thing.Map.Map;
import Thing.Map.MapManager;
import Service.Player;

import Game.Play.*;
import Thing.PlayingTank;
import Thing.Tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Run {


    protected GameMap gameMap;



    public Run(GameMap gameMap) {
        this.gameMap = gameMap;
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
        game.init(gameMap, MainClient.getLoggedPlayer());
        ThreadPool.execute(game);
        // and the game starts ...

    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }
}
