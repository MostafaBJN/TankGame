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


    protected ArrayList<Player> players;
    protected GameInfo gameInfo;
    protected GameMap gameMap;



    public Run(GameInfo gameInfo) {
        ThreadPool.init();

        // Show the game menu ...
        this.gameInfo = gameInfo;
        players = new ArrayList<>();

        //SERVER
        //CLIENT


        // After the player clicks 'PLAY' ...

    }


    public void runTheGame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameStart();
            }
        });
    }

    private void gameStart() {
        ArrayList<Tank> tanks = new ArrayList<>();
        for (Player player:players) {
            tanks.add(player.getTank());
        }
        gameMap = new GameMap(gameInfo.getMap(),tanks);

        GameFrame gameFrame = new GameFrame("Tank Trouble");
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
        gameFrame.initBufferStrategy();
        // Create and execute the game-loop
        GameLoop game = new GameLoop(gameFrame);
        game.init();
        ThreadPool.execute(game);
        // and the game starts ...

    }


}
