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

    private static final int FULL_CIRCLE_DEGREE = 360;

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

    public class Degree {

        private static final int TURNING_DEGREE_VALUE = 18;
        private static final int DEFAULT_ANGLE = 0;

        private int angle;

        public Degree() {
            angle = randomAngle();
        }

        public Degree(int angle) {
            this.angle = angle;
        }

        public int randomAngle() {
            return new Random().nextInt(FULL_CIRCLE_DEGREE);
        }

        public void clockwiseTurning()
        {
            if(angle - TURNING_DEGREE_VALUE < 0)
            {
                angle = angle + FULL_CIRCLE_DEGREE;
            }
            angle = angle - TURNING_DEGREE_VALUE;
        }

        public void anticlockwiseTurning()
        {

            if(angle + TURNING_DEGREE_VALUE >= FULL_CIRCLE_DEGREE)
            {
                angle = angle + TURNING_DEGREE_VALUE - FULL_CIRCLE_DEGREE;
            }
            angle = angle + TURNING_DEGREE_VALUE;
        }

    }
}
