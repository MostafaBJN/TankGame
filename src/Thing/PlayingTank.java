package Thing;


import Service.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayingTank extends Tank implements Serializable {

    private int health;

    private Player owner;

    private int x;
    private int y;

    VisualArea visualArea;

    private boolean prizePicked;
    private Prize currentPrize;

    public static final int DEFAULT_SPEED = 1;
    public static final int DEFAULT_ANGLE = -90;

    private double xVelocity;
    private double yVelocity;
    private Degree direction;

    private boolean invincible;
    private int powerOfTir;

    private int kills;
    private int deaths;


    private Bullet currentBullet;
    private ArrayList<Bullet> bullets;


    public PlayingTank(Tank tank, int x, int y, Player owner){
        super(tank);
        health = defaultHealth;
        this.x = x;
        this.y = y;
        this.owner = owner;
        currentBullet = new Bullet(Bullet.STANDARD, this);
        kills = 0;
        deaths = 0;
        direction = new Degree(DEFAULT_ANGLE);
        invincible = false;
        powerOfTir = Bullet.DEFAULT_DAMAGE_POWER;
        visualArea = new VisualArea(widthOfTank, heightOfTank, direction, x, y);
        bullets = new ArrayList<>();
    }

    public void shootBullet() {
        bullets.add(new Bullet(currentBullet));
    }

    public void destroyTank() {
        kills ++;
    }

    public void killBullet(Bullet bulletToKill) {
        for (Bullet bullet:bullets) {
            if(bulletToKill.equals(bullet)){
                bullets.remove(bullet);
                break;
            }
        }
    }

    ////
    public void pickPrize(Prize prize) {
        prizePicked = true;
        currentPrize = prize;
    }

    public void getShoot(Bullet bullet) {
        health -= bullet.getPower();
    }


    public boolean death() {
        deaths++;
        return health <= 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayingTank)) return false;
        if (!super.equals(o)) return false;

        PlayingTank that = (PlayingTank) o;

        return owner != null ? owner.equals(that.owner) : that.owner == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
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

    public Prize getCurrentPrize() {
        return currentPrize;
    }

    public Degree getDirection() {
        return direction;
    }

    public int getPowerOfTir() {
        return powerOfTir;
    }


    public VisualArea getVisualArea() {
        return visualArea;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }


    public boolean isPrizePicked() {
        return prizePicked;
    }

    public void setPrizePicked(boolean prizePicked) {
        this.prizePicked = prizePicked;
    }



    public static int getDefaultSpeed() {
        return DEFAULT_SPEED;
    }

    public static int getDefaultAngle() {
        return DEFAULT_ANGLE;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }



    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public void setPowerOfTir(int powerOfTir) {
        this.powerOfTir = powerOfTir;
    }

    public void setVisualArea(VisualArea visualArea) {
        this.visualArea = visualArea;
    }

    public void setCurrentPrize(Prize currentPrize) {
        this.currentPrize = currentPrize;
    }

    public void setDirection(Degree direction) {
        this.direction = direction;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public Bullet getCurrentBullet() {
        return currentBullet;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
