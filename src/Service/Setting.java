package Service;

import Thing.Map.BreakableWall;
import Thing.Bullet;
import Thing.Tank;

/**
 * Setting Data of a Player
 */
public class Setting {

    /*
    Access with MainClient.getLoggedPlayer.....
     */

    protected int defaultTankHealth;
    protected int defaultBreakableWallHealth;
    protected int defaultBulletPower;

    protected long totalPlayedTime;

    public static final int WIN_GAME = 1;
    public static final int LOSE_GAME = -1;

    protected int numberOfWinsOnline;
    protected int numberOfLosesOnline;
    protected int numberOfWinsOffline;
    protected int numberOfLosesOffline;

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

    public int getDefaultTankHealth() {
        return defaultTankHealth;
    }

    public int getDefaultBreakableWallHealth() {
        return defaultBreakableWallHealth;
    }

    public int getDefaultBulletPower() {
        return defaultBulletPower;
    }

    public long getTotalPlayedTime() {
        return totalPlayedTime;
    }

    public static int getWinGame() {
        return WIN_GAME;
    }

    public static int getLoseGame() {
        return LOSE_GAME;
    }

    public int getNumberOfWinsOnline() {
        return numberOfWinsOnline;
    }

    public int getNumberOfLosesOnline() {
        return numberOfLosesOnline;
    }

    public int getNumberOfWinsOffline() {
        return numberOfWinsOffline;
    }

    public int getNumberOfLosesOffline() {
        return numberOfLosesOffline;
    }

    public void setDefaultTankHealth(int defaultTankHealth) {
        this.defaultTankHealth = defaultTankHealth;
    }

    public void setDefaultBreakableWallHealth(int defaultBreakableWallHealth) {
        this.defaultBreakableWallHealth = defaultBreakableWallHealth;
    }

    public void setDefaultBulletPower(int defaultBulletPower) {
        this.defaultBulletPower = defaultBulletPower;
    }

    public void setTotalPlayedTime(long totalPlayedTime) {
        this.totalPlayedTime = totalPlayedTime;
    }

    public void setNumberOfWinsOnline(int numberOfWinsOnline) {
        this.numberOfWinsOnline = numberOfWinsOnline;
    }

    public void setNumberOfLosesOnline(int numberOfLosesOnline) {
        this.numberOfLosesOnline = numberOfLosesOnline;
    }

    public void setNumberOfWinsOffline(int numberOfWinsOffline) {
        this.numberOfWinsOffline = numberOfWinsOffline;
    }

    public void setNumberOfLosesOffline(int numberOfLosesOffline) {
        this.numberOfLosesOffline = numberOfLosesOffline;
    }
}
