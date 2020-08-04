package Thing;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bullet {

    private static final long LIFE_TIME = 4000;
    private static final long DEFAULT_VELOCITY = 20;

    public static final int DEFAULT_DAMAGE_POWER = 50;
    public static final int MAX_DAMAGE_POWER = 1000;
    public static final int MIN_DAMAGE_POWER = 1;

    /**
     * STYLE
     */
    private static final int STANDARD = 0;
    private static final int DOUBLE = 1;
    private static final int TRIPLE = 2;
    private static final int LASER = 3;


    /**
     * IMAGES OF STYLES
     */
    private static BufferedImage STANDARD_IMAGE;
    private static BufferedImage DOUBLE_IMAGE;
    private static BufferedImage TRIPLE_IMAGE;
    private static BufferedImage LASER_IMAGE;

    /**
     * FILE STRING
     */
    private static final String FOLDER_PATH = "data\\bullet";
    private static final String TYPE_OF_FILE = ".png";


    /* INSERTING IMAGES */
    static {
        try {
            STANDARD_IMAGE = ImageIO.read(new File(FOLDER_PATH + "shield" + TYPE_OF_FILE));
            DOUBLE_IMAGE = ImageIO.read(new File(FOLDER_PATH + "health" + TYPE_OF_FILE));
            TRIPLE_IMAGE = ImageIO.read(new File(FOLDER_PATH + "laser" + TYPE_OF_FILE));
            LASER_IMAGE = ImageIO.read(new File(FOLDER_PATH + "double" + TYPE_OF_FILE));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Bullit Images Not Loaded!", "Loading Images", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private int style;
    private BufferedImage styleImage;

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

    public int getDefaultPower() {
        return defaultPower;
    }
}
