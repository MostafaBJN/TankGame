package Game.Run;

import Thing.Map.Map;
import Thing.Map.MapManager;
import Service.Player;

import Game.Play.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Run {

    private String name;
    private boolean teamGame;
    private boolean leagueGame;
    private int numberOfPlayer;

    private int defaultTankHealth;
    private int defaultBreakableWallHealth;
    private int defaultBulletPower;

    private boolean playWithComputer;

    private Map map;
    private ArrayList<Player> players;
    private GameMap gameMap;


    public Run(String name,
               boolean teamGame,
               boolean leagueGame,
               int numberOfPlayer,
               int defaultTankHealth,
               int defaultBreakableWallHealth,
               int defaultBulletPower,
               boolean playWithComputer) {
        this.name = name;
        this.teamGame = teamGame;
        this.leagueGame = leagueGame;
        this.numberOfPlayer = numberOfPlayer;
        this.defaultTankHealth = defaultTankHealth;
        this.defaultBreakableWallHealth = defaultBreakableWallHealth;
        this.defaultBulletPower = defaultBulletPower;
        this.playWithComputer = playWithComputer;
    }

    public Run() {
        // Initialize the global thread-pool
        ThreadPool.init();

        // Show the game menu ...
        MapManager.loadAllMaps();

        //SERVER
        map = selectRandomMap();
        MapManager.selectMap(map.getName());
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
