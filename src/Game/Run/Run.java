package Game.Run;

import Thing.Map.Map;
import Thing.Map.MapManager;
import Service.Player;

import Game.Play.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Run {


    private ArrayList<Player> players;
    private GameMap gameMap;



    public Run() {
        // Initialize the global thread-pool
        ThreadPool.init();

        // Show the game menu ...


        //SERVER

        //CLIENT


        // After the player clicks 'PLAY' ...
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameStart();
            }
        });
    }

///////////////////////////////////
    public static Map selectRandomMap() {
        ArrayList<Map> maps = MapManager.getMaps();
        int mapNumber = new Random().nextInt(maps.size());
        MapManager.selectMap(maps.get(mapNumber).getName());
        return MapManager.getSelectedMap();
    }


    private void gameStart() {
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
