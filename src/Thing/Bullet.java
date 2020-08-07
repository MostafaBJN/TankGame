package Thing;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import static Thing.Image.Bullet.*;

public class Bullet implements Serializable, Thing {

    public static final long LIFE_TIME = 4000;
    public static final long DEFAULT_VELOCITY = 20;

    public static final int DEFAULT_DAMAGE_POWER = 50;
    public static final int MAX_DAMAGE_POWER = 1000;
    public static final int MIN_DAMAGE_POWER = 1;

    private int style;
    private transient BufferedImage styleImage;

    private Tank shooterTank;

    private int defaultPower;
    private double velocity;
    private Degree direction;
    private Timer lifeTimer;
    private long startTime;

    public Bullet(Tank bulletShooterTank) {
        this.shooterTank = bulletShooterTank;
        lifeTimer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(System.currentTimeMillis() - startTime >= LIFE_TIME){
                    //Kill Bullet
                    lifeTimer.stop();
                    //TODO kill bullet
                }
            }
        });
        lifeTimer.start();
        startTime = System.currentTimeMillis();
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

    }

    public int getDefaultPower() {
        return defaultPower;
    }
}
