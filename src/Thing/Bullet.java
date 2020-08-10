package Thing;


import javax.swing.*;
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
    public static final long DEFAULT_VELOCITY = 20;

    public static final int DEFAULT_DAMAGE_POWER = 50;
    public static final int MAX_DAMAGE_POWER = 1000;
    public static final int MIN_DAMAGE_POWER = 1;

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
    }

    public Bullet(int style) {
        setStyle(style);
        defaultPower = DEFAULT_DAMAGE_POWER;
        power = defaultPower;
        velocity = DEFAULT_VELOCITY;
    }

    public Bullet(Bullet bullet) {

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
        styleFinder();
    }

}
