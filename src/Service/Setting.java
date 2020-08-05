package Service;

import Map.BreakableWall;
import Thing.Bullet;
import Thing.Tank;

import java.awt.image.BufferedImage;

/**
 * Setting Data of a Player
 */
public class Setting {

    /*
    Access with MainClient.getLoggedPlayer.....
     */

    private int defaultTankHealth;
    private int defaultBreakableWallHealth;
    private int defaultBulletPower;

    private long totalPlayedTime;

    public static final int WIN_GAME = 1;
    public static final int LOSE_GAME = -1;

    private int numberOfWinsOnline;
    private int numberOfLosesOnline;
    private int numberOfWinsOffline;
    private int numberOfLosesOffline;

    //TODO ? List of URLs ?
    //change and see

    public Setting() {
        defaultTankHealth = Tank.DEFAULT_HEALTH_VALUE;
        defaultBulletPower = Bullet.DEFAULT_DAMAGE_POWER;
        defaultBreakableWallHealth = BreakableWall.DEFAULT_HEALTH_VALUE;
        totalPlayedTime = 0;
        numberOfWinsOnline = 0;
        numberOfLosesOnline = 0;
        numberOfWinsOffline = 0;
        numberOfLosesOffline = 0;
    }

    public Setting(int defaultTankHealth,
                   int defaultBreakableWallHealth,
                   int defaultBulletPower,
                   long totalPlayedTime,
                   int numberOfWinsOnline,
                   int numberOfLosesOnline,
                   int numberOfWinsOffline,
                   int numberOfLosesOffline) {
        this.defaultTankHealth = defaultTankHealth;
        this.defaultBreakableWallHealth = defaultBreakableWallHealth;
        this.defaultBulletPower = defaultBulletPower;
        this.totalPlayedTime = totalPlayedTime;
        this.numberOfWinsOnline = numberOfWinsOnline;
        this.numberOfLosesOnline = numberOfLosesOnline;
        this.numberOfWinsOffline = numberOfWinsOffline;
        this.numberOfLosesOffline = numberOfLosesOffline;
    }

    /**
     * change the model of tank
     */
    public void changeTankModel() {

    }

    /**
     * add result of an offline game to setting data
     *
     * @param result result of a played game
     */
    public void offlineAddResult(int result) {
        if(result != WIN_GAME && result != LOSE_GAME){
            System.out.println("WRONG RESULT SENT");
            return;
        }
        if (result > 0) {
            numberOfWinsOffline++;
        }
        else {
            numberOfLosesOffline++;
        }
    }

    /**
     * add result of an online game to setting data
     *
     * @param result result of a played game
     */
    public void onlineAddResult(int result) {
        if(result != WIN_GAME && result != LOSE_GAME){
            System.out.println("WRONG RESULT SENT");
            return;
        }
        if (result > 0) {
            numberOfWinsOnline++;
        }
        else {
            numberOfLosesOnline++;
        }
    }
}
