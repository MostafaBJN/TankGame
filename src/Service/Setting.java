package Service;

/**
 * Setting Data of a Player
 */
public class Setting {

    /*
    Access with ClientMain.getLoggedPlayer.....
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

    //picture of tank player

    //user name player

    //TODO ? List of URLs ?
    //change and see

    public Setting() {

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
