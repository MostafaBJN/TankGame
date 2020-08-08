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



    public Run(GameInfo gameInfo, ArrayList<Player> players) {
        // Show the game menu ...
        this.gameInfo = gameInfo;
        this.players = players;
        ArrayList<Tank> tanks = new ArrayList<>();
        for (Player player:players) {
            tanks.add(player.getTank());
        }
        gameMap = new GameMap(gameInfo.getMap(),tanks);
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
        game.init(this, MainClient.getLoggedPlayer());
        ThreadPool.execute(game);
        // and the game starts ...

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }
}
