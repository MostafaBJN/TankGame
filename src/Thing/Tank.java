/**
 * int lives:counter of lives tank.
 * int x and int y:location of tank.
 * Prize prize:a prize of tank.
 *
 */
package Thing;


import java.util.Random;

public class Tank
{
    public static final int DEFAULT_HEALTH_VALUE = 200;//should discus
    private static final int MAX_HEALTH_VALUE = 1000;//should discus
    private static final int MIN_HEALTH_VALUE = 1;//should discus

    private static int defaultHealth;

    private int health;

    private int x;
    private int y;

    private Bullet shootedBullet;

    private Area area;

    private Prize currentPrize;

    private double velocity;
    private Degree direction;

    private boolean damageable;
    int powerOfTir;

    public Tank(int health, int x, int y) {
        this.health = health;
        this.x = x;
        this.y = y;
        direction = new Degree();
    }



    public void takingPrize () {

    }


    public static int getDefaultHealth() {
        return defaultHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Bullet getShootedBullet() {
        return shootedBullet;
    }

    public void setShootedBullet(Bullet shootedBullet) {
        this.shootedBullet = shootedBullet;
    }

    public Area getArea() {
        return area;
    }

    public Prize getCurrentPrize() {
        return currentPrize;
    }

    public double getVelocity() {
        return velocity;
    }

    public Degree getDirection() {
        return direction;
    }

    public boolean isDamageable() {
        return damageable;
    }

    public int getPowerOfTir() {
        return powerOfTir;
    }

    public void setCurrentPrize(Prize currentPrize) {
        this.currentPrize = currentPrize;
    }


}
