/**
 * int lives:counter of lives tank.
 * int x and int y:location of tank.
 * Prize prize:a prize of tank.
 *
 */
package Thing;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Tank
{
    /**
     * HEALTH DEFAULTS
     */
    public static final int DEFAULT_HEALTH_VALUE = 200;//should discus
    private static final int MAX_HEALTH_VALUE = 1000;//should discus
    private static final int MIN_HEALTH_VALUE = 1;//should discus

    /**
     * STYLES
     */
    private static final int BLACK_TANK = 0;
    private static final int SAND_TANK = 1;
    private static final int RED_TANK = 2;
    private static final int BLUE_TANK = 3;
    private static final int GREEN_TANK = 4;
    private static final int BLACK_INVINCIBLE_TANK = 5;
    private static final int SAND_INVINCIBLE_TANK = 6;
    private static final int RED_INVINCIBLE_TANK = 7;
    private static final int BLUE_INVINCIBLE_TANK = 8;
    private static final int GREEN_INVINCIBLE_TANK = 9;

    /**
     * IMAGES OF STYLES
     */
    private static BufferedImage BLACK_TANK_IMAGE;
    private static BufferedImage SAND_TANK_IMAGE;
    private static BufferedImage RED_TANK_IMAGE;
    private static BufferedImage BLUE_TANK_IMAGE;
    private static BufferedImage GREEN_TANK_IMAGE;
    private static BufferedImage BLACK_INVINCIBLE_TANK_IMAGE;
    private static BufferedImage SAND_INVINCIBLE_TANK_IMAGE;
    private static BufferedImage RED_INVINCIBLE_TANK_IMAGE;
    private static BufferedImage BLUE_INVINCIBLE_TANK_IMAGE;
    private static BufferedImage GREEN_INVINCIBLE_TANK_IMAGE;

    /**
     * FILE STRING
     */
    private static final String FOLDER_PATH = "data\\tanks\\standard\\";
    private static final String TYPE_OF_FILE = ".png";
    public static final String INVINCIBLE_STRING = "Invincible";

    /* INSERTING IMAGES */
    static {
        try {
            BLACK_TANK_IMAGE = ImageIO.read(new File(FOLDER_PATH + "black" + TYPE_OF_FILE));
            SAND_TANK_IMAGE = ImageIO.read(new File(FOLDER_PATH + "sand" + TYPE_OF_FILE));
            RED_TANK_IMAGE = ImageIO.read(new File(FOLDER_PATH + "red" + TYPE_OF_FILE));
            BLUE_TANK_IMAGE = ImageIO.read(new File(FOLDER_PATH + "blue" + TYPE_OF_FILE));
            GREEN_TANK_IMAGE = ImageIO.read(new File(FOLDER_PATH + "green" + TYPE_OF_FILE));
            BLACK_INVINCIBLE_TANK_IMAGE = ImageIO.read(new File(FOLDER_PATH + "black" + INVINCIBLE_STRING + TYPE_OF_FILE));
            SAND_INVINCIBLE_TANK_IMAGE = ImageIO.read(new File(FOLDER_PATH + "sand" + INVINCIBLE_STRING + TYPE_OF_FILE));
            RED_INVINCIBLE_TANK_IMAGE = ImageIO.read(new File(FOLDER_PATH + "red" + INVINCIBLE_STRING + TYPE_OF_FILE));
            BLUE_INVINCIBLE_TANK_IMAGE = ImageIO.read(new File(FOLDER_PATH + "blue" + INVINCIBLE_STRING + TYPE_OF_FILE));
            GREEN_INVINCIBLE_TANK_IMAGE = ImageIO.read(new File(FOLDER_PATH + "green" + INVINCIBLE_STRING + TYPE_OF_FILE));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Map Images Not Loaded!", "Loading Images", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static final int heightOfTank = BLACK_TANK_IMAGE.getHeight();
    public static final int widthOfTank = BLACK_TANK_IMAGE.getWidth();
    public static final double diameter = Math.sqrt(heightOfTank*2 + widthOfTank*2);

    private static int defaultHealth;

    private int model;
    private BufferedImage modelImage;

    //private Bullet shootedBullet;

    private Area area;



    public Tank() {

    }

    public Tank(Tank tank) {

    }


    public void shootABullet() {

    }


    public void takingPrize () {

    }


    public static int getDefaultHealth() {
        return defaultHealth;
    }




}
