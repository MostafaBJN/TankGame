package Thing;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import static Thing.Image.Bullet.*;

public class Bullet implements Serializable, Thing {

    public static final int STANDARD = 0;
    public static final int DOUBLE = 1;
    public static final int TRIPLE = 2;
    public static final int LASER = 3;

    public static final long LIFE_TIME = 4000;
    public static final long DEFAULT_VELOCITY = 2;

    public static final int DEFAULT_DAMAGE_POWER = 50;
    public static final int MAX_DAMAGE_POWER = 1000;
    public static final int MIN_DAMAGE_POWER = 1;

    private String name;

    private Area area;

    private int style;
    private transient BufferedImage styleImage;

    private PlayingTank shooterTank;

    private int defaultPower;
    private int power;
    private double velocity;
    private double xVelocity;
    private double yVelocity;
    private Degree direction;

    private int x;
    private int y;

    private Timer shootTimer;
    private long shootTime;

    public Bullet(PlayingTank bulletShooterTank) {
        this(bulletShooterTank.getCurrentBullet());
        name = shooterTank.getName();
        direction = new Degree(bulletShooterTank.getDirection().getAngle());
        power = defaultPower;
        this.shooterTank = bulletShooterTank;
        shootTimer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(System.currentTimeMillis() >= shootTime + LIFE_TIME){
                    shooterTank.killBullet(Bullet.this);
                    shootTimer.stop();
                }
            }
        });
        shootTimer.start();
        shootTime = System.currentTimeMillis();
        area = new Area(styleImage.getWidth(), styleImage.getHeight());
    }

    public Bullet(int style, PlayingTank tank) {
        name = tank.getName();
        shooterTank = tank;
        setStyle(style);
        defaultPower = DEFAULT_DAMAGE_POWER;
        power = defaultPower;
        velocity = DEFAULT_VELOCITY;
        area = new Area(styleImage.getWidth(), styleImage.getHeight());
    }

    public Bullet(Bullet bullet) {
        power = bullet.power;
        velocity = bullet.velocity;
    }


    public void killBullet() {
        shooterTank.killBullet(this);
    }
    /**
     * Find the Image Of This Bullet
     */
    public void styleFinder() {
        switch (style) {
            case STANDARD -> styleImage = STANDARD_IMAGE;
            case DOUBLE -> styleImage = DOUBLE_IMAGE;
            case TRIPLE -> styleImage = TRIPLE_IMAGE;
            case LASER -> styleImage = LASER_IMAGE;
        }
    }

    @Override
    public void setStyle(int style) {
        this.style = style;
        if(style != LASER) {
            power = (style+1) * DEFAULT_DAMAGE_POWER;
            velocity = DEFAULT_VELOCITY;
        }
        else {
            power = DEFAULT_DAMAGE_POWER;
            velocity = 3 * DEFAULT_VELOCITY;
        }

        styleFinder();
    }


    public String getName() {
        return name;
    }

    public int getStyle() {
        return style;
    }

    public BufferedImage getStyleImage() {
        return styleImage;
    }

    public PlayingTank getShooterTank() {
        return shooterTank;
    }

    public int getDefaultPower() {
        return defaultPower;
    }

    public int getPower() {
        return power;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public Degree getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Timer getShootTimer() {
        return shootTimer;
    }

    public long getShootTime() {
        return shootTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStyleImage(BufferedImage styleImage) {
        this.styleImage = styleImage;
    }

    public void setShooterTank(PlayingTank shooterTank) {
        this.shooterTank = shooterTank;
    }

    public void setDefaultPower(int defaultPower) {
        this.defaultPower = defaultPower;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void setDirection(Degree direction) {
        this.direction = direction;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setShootTimer(Timer shootTimer) {
        this.shootTimer = shootTimer;
    }

    public void setShootTime(long shootTime) {
        this.shootTime = shootTime;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}

