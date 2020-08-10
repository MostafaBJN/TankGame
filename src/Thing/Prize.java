package Thing;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import static Thing.Image.Prize.*;
import static Thing.Image.Tank.BLACK_TANK_IMAGE;

public class Prize implements Serializable, Thing
{

    public static final int SHIELD = 0;
    public static final int HEALTH_INCREASE = 1;
    public static final int LASER = 2;
    public static final int POWER_SHOT_TWICE = 3;
    public static final int POWER_SHOT_TRIPLE = 4;

    public static final int LASER_TIME = 3000;
    public static final int BULLET_POWER_TIME = 9000;
    public static final int SHIELD_TIME = 15000;
    public static final int HEALTH_INCREASE_TIME = 1;

    public static final int height = SHIELD_IMAGE.getHeight();
    public static final int width = SHIELD_IMAGE.getWidth();

    protected static final Area DEFAULT_AREA = new Area(width, height);
    protected Area area;


    private int style;
    private transient BufferedImage styleImage;

    private Timer usingTimer;
    private long pickingTime;
    private boolean picked;
    private PlayingTank playingTank;
    private int keepingTime;

    int x;
    int y;


    public Prize(int style, int x, int y) {
        setStyle(style);
        this.x = x;
        this.y = y;
        picked = false;
        pickingTime = 0;
        usingTimer = null;
        playingTank = null;
        picked = false;
    }

    public void pickPrize(PlayingTank playingTank){
        playingTank.pickPrize(this);
        this.playingTank = playingTank;
        picked = true;
        pickingTime = System.currentTimeMillis();
        usingTimer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keepingTimeFinder();
                if(System.currentTimeMillis() >= pickingTime + keepingTime){
                    playingTank.setCurrentPrize(null);
                }
            }
        });
        usingTimer.start();
    }

    private void keepingTimeFinder() {
        switch (style) {
            case SHIELD -> keepingTime = SHIELD_TIME;
            case HEALTH_INCREASE -> keepingTime = HEALTH_INCREASE_TIME;
            case LASER -> keepingTime = LASER_TIME;
            case POWER_SHOT_TWICE, POWER_SHOT_TRIPLE -> keepingTime = BULLET_POWER_TIME;
        }
    }

    /**
     * Find the Image Of This Ground
     */
    public void styleFinder() {
        switch (style) {
            case SHIELD -> styleImage = SHIELD_IMAGE;
            case HEALTH_INCREASE -> styleImage = HEALTH_INCREASE_IMAGE;
            case LASER -> styleImage = LASER_IMAGE;
            case POWER_SHOT_TWICE -> styleImage = POWER_SHOT_TWICE_IMAGE;
            case POWER_SHOT_TRIPLE -> styleImage = POWER_SHOT_TRIPLE_IMAGE;
        }
    }

    @Override
    public void setStyle(int style) {
        this.style = style;
        styleFinder();
    }

}
