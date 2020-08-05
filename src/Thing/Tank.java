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
    public static final int NUMBER_OF_TANK_STYLES = 5;
    public static final int BLACK_TANK = 0;
    public static final int SAND_TANK = 1;
    public static final int RED_TANK = 2;
    public static final int BLUE_TANK = 3;
    public static final int GREEN_TANK = 4;
    public static final int BLACK_INVINCIBLE_TANK = 5;
    public static final int SAND_INVINCIBLE_TANK = 6;
    public static final int RED_INVINCIBLE_TANK = 7;
    public static final int BLUE_INVINCIBLE_TANK = 8;
    public static final int GREEN_INVINCIBLE_TANK = 9;

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
    private static final String INVINCIBLE_STRING = "Invincible";

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
            JOptionPane.showMessageDialog(null, "Tank Images Not Loaded!", "Loading Images", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static final int heightOfTank = BLACK_TANK_IMAGE.getHeight();
    public static final int widthOfTank = BLACK_TANK_IMAGE.getWidth();

    protected String name;

    protected int defaultHealth;

    protected int model;
    protected BufferedImage modelImage;

    protected static final Area DEFAULT_AREA = new Area(widthOfTank, heightOfTank);
    protected Area area;



    public Tank(String name) {
        this.name = name;
        defaultHealth = DEFAULT_HEALTH_VALUE;
        model = selectRandomModel();
        styleFinder();
        area = DEFAULT_AREA;
    }

    public Tank(String name, int model) {
        this.name = name;
        defaultHealth = DEFAULT_HEALTH_VALUE;
        this.model = model;
        styleFinder();
        area = DEFAULT_AREA;
    }

    public Tank(int defaultHealth, String name) {
        this.name = name;
        this.defaultHealth = defaultHealth;
        model = selectRandomModel();
        styleFinder();
        area = DEFAULT_AREA;
    }

    public Tank(String name, int defaultHealth, int model) {
        this.name = name;
        this.defaultHealth = defaultHealth;
        this.model = model;
        styleFinder();
        area = DEFAULT_AREA;
    }

    public Tank(Tank tank) {
        this(tank.name, tank.defaultHealth, tank.model);
    }


    private int selectRandomModel() {
        return new Random().nextInt(NUMBER_OF_TANK_STYLES);
    }

    public void shootABullet() {

    }


    public void takingPrize () {

    }

    /**
     * Find the Image Of This Ground
     */
    public void styleFinder() {
        switch (model) {
            case BLACK_TANK -> modelImage = BLACK_TANK_IMAGE;
            case SAND_TANK -> modelImage = SAND_TANK_IMAGE;
            case RED_TANK -> modelImage = RED_TANK_IMAGE;
            case BLUE_TANK -> modelImage = BLUE_TANK_IMAGE;
            case GREEN_TANK -> modelImage = GREEN_TANK_IMAGE;
            case BLACK_INVINCIBLE_TANK -> modelImage = BLACK_INVINCIBLE_TANK_IMAGE;
            case SAND_INVINCIBLE_TANK -> modelImage = SAND_INVINCIBLE_TANK_IMAGE;
            case RED_INVINCIBLE_TANK -> modelImage = RED_INVINCIBLE_TANK_IMAGE;
            case BLUE_INVINCIBLE_TANK -> modelImage = BLUE_INVINCIBLE_TANK_IMAGE;
            case GREEN_INVINCIBLE_TANK -> modelImage = GREEN_INVINCIBLE_TANK_IMAGE;
        }
    }



    public BufferedImage getModelImage() {
        return modelImage;
    }

    public int getDefaultHealth() {
        return defaultHealth;
    }

    public String getName() {
        return name;
    }

    public int getModel() {
        return model;
    }

    public Area getArea() {
        return area;
    }
}
