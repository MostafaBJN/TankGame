package Game.Play;

import Thing.Bullet;
import Thing.Map.*;
import Thing.Tank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import static Game.Run.Run.selectRandomMap;

/**
 * Main Info of a game
 */
public class GameInfo implements Serializable
{

    public static final int MAX_NUMBER_OF_PORT = 32000;

    private int port;

    private String name;
    private boolean playWithComputer;
    private boolean isPCAllowed;
    private boolean teamGame;
    private boolean leaguePlay;
    private int numberOfGames;
    private int numberOfPlayers;
    private int numberOfPCPlayers;
    private int tankHealth;
    private int bulletPower;
    private int bWallHealth;
    private int playerNeedToJoin;
    private Map map;

    public static final int NUMBER_OF_PLAYERS_SHOULD_BE_EVEN = 1;
    public static final int NUMBER_OF_GAMES_IS_OUT_OF_RANGE = 2;
    public static final int HEALTH_OF_TANK_IS_OUT_OF_RANGE = 3;
    public static final int POWER_OF_BULLET_IS_OUT_OF_RANGE = 4;
    public static final int HEALTH_OF_B_WALL_IS_OUT_OF_RANGE = 5;
    public static final int WRONG_INPUT = 6;
    public static final int MAX_OF_NUMBER_OF_GAMES = 100;
    public static final int MIN_OF_NUMBER_OF_GAMES = 2;

    public GameInfo(int port,
                    String name,
                    boolean playWithComputer,
                    boolean isPCAllowed,
                    boolean teamGame,
                    boolean leaguePlay,
                    int numberOfGames,
                    int numberOfPlayers,
                    int numberOfPCPlayers,
                    int tankHealth,
                    int bulletPower,
                    int bWallHealth
                    ) {
        this.port = port;
        this.name = name;
        this.playWithComputer = playWithComputer;
        this.isPCAllowed = isPCAllowed;
        this.teamGame = teamGame;
        this.leaguePlay = leaguePlay;
        this.numberOfGames = numberOfGames;
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfPCPlayers = numberOfPCPlayers;
        this.tankHealth = tankHealth;
        this.bulletPower = bulletPower;
        this.bWallHealth = bWallHealth;
        playerNeedToJoin = numberOfPlayers - numberOfPCPlayers - 1;
        map = selectRandomMap();
        MapManager.selectMap(map.getName());
    }

    /**
     * get info from GUI add games and check is ok
     *
     * @return list of errors
     */
    public static ArrayList<Integer> checkPCGameInfo(
                                          boolean teamGame,
                                          boolean leaguePlay,
                                          String numberOfGamesSt,
                                          String personLimitSt,
                                          String tankHealthSt,
                                          String bulletPowerSt,
                                          String bWallHealthSt) {
        ArrayList<Integer> errors = new ArrayList<>();
        int numberOfGames;
        int personLimit;
        int tankHealth;
        int bulletPower;
        int bWallHealth;

        if(leaguePlay) {
            try {
                numberOfGames = Integer.parseInt(numberOfGamesSt);
            } catch (NumberFormatException e) {
                errors.add(WRONG_INPUT);
                return errors;
            }
        }
        else {
            numberOfGames = 1;
        }
        try {
            personLimit = Integer.parseInt(personLimitSt);
        } catch (NumberFormatException e){
            errors.add(WRONG_INPUT);
            return errors;
        }
        try {
            tankHealth = Integer.parseInt(tankHealthSt);
        } catch (NumberFormatException e){
            errors.add(WRONG_INPUT);
            return errors;
        }
        try {
            bulletPower = Integer.parseInt(bulletPowerSt);
        } catch (NumberFormatException e){
            errors.add(WRONG_INPUT);
            return errors;
        }
        try {
            bWallHealth = Integer.parseInt(bWallHealthSt);
        } catch (NumberFormatException e){
            errors.add(WRONG_INPUT);
            return errors;
        }


        if(teamGame){
            if(personLimit % 2 == 1){
                errors.add(NUMBER_OF_PLAYERS_SHOULD_BE_EVEN);
            }
        }
        if(leaguePlay) {
            if (numberOfGames < MIN_OF_NUMBER_OF_GAMES || numberOfGames > MAX_OF_NUMBER_OF_GAMES) {
                errors.add(NUMBER_OF_GAMES_IS_OUT_OF_RANGE);
            }
        }
        if(tankHealth > Tank.MAX_HEALTH_VALUE || tankHealth < Tank.MIN_HEALTH_VALUE){
            errors.add(HEALTH_OF_TANK_IS_OUT_OF_RANGE);
        }
        if(bulletPower > Bullet.MAX_DAMAGE_POWER || bulletPower < Bullet.MIN_DAMAGE_POWER){
            errors.add(POWER_OF_BULLET_IS_OUT_OF_RANGE);
        }
        if(bWallHealth > BreakableWall.MAX_HEALTH_VALUE || bWallHealth < BreakableWall.MIN_HEALTH_VALUE){
            errors.add(HEALTH_OF_B_WALL_IS_OUT_OF_RANGE);
        }
        return errors;
    }

    /**
     * a player join to game
     */
    public void playerJoin() {
        playerNeedToJoin--;
    }

    /**
     * condition of start of game check
     * @return is game can start
     */
    public boolean gameStart() {
        return playerNeedToJoin == 0;
    }

    /**
     * @return a random number for port
     */
    public static int selectRandomPort(){
        return new Random().nextInt(MAX_NUMBER_OF_PORT);
    }

    public int getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    public boolean isPlayWithComputer() {
        return playWithComputer;
    }

    public boolean isPCAllowed() {
        return isPCAllowed;
    }

    public boolean isTeamGame() {
        return teamGame;
    }

    public boolean isLeaguePlay() {
        return leaguePlay;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getNumberOfPCPlayers() {
        return numberOfPCPlayers;
    }

    public int getTankHealth() {
        return tankHealth;
    }

    public int getBulletPower() {
        return bulletPower;
    }

    public int getBWallHealth() {
        return bWallHealth;
    }

    public int getPlayerNeedToJoin() {
        return playerNeedToJoin;
    }

    public Map getMap() {
        return map;
    }
}
