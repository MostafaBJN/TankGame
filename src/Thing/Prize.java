package Thing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Prize
{
    /**
     * TYPE
     */
    private static final int SHIELD = 0;
    private static final int HEALTH_INCREASE = 1;
    private static final int LASER = 2;
    private static final int POWER_SHOT_TWICE = 3;
    private static final int POWER_SHOT_TRIPLE = 4;

    /**
     * IMAGE OF TYPE
     */
    private static BufferedImage SHIELD_IMAGE;
    private static BufferedImage HEALTH_INCREASE_IMAGE;
    private static BufferedImage LASER_IMAGE;
    private static BufferedImage POWER_SHOT_TWICE_IMAGE;
    private static BufferedImage POWER_SHOT_TRIPLE_IMAGE;

    /**
     * FILE STRING
     */
    private static final String FOLDER_PATH = "data\\prize\\";
    private static final String TYPE_OF_FILE = ".png";

    /* INSERTING IMAGES */
    static {
        try {
            SHIELD_IMAGE = ImageIO.read(new File(FOLDER_PATH + "shield" + TYPE_OF_FILE));
            HEALTH_INCREASE_IMAGE = ImageIO.read(new File(FOLDER_PATH + "health" + TYPE_OF_FILE));
            LASER_IMAGE = ImageIO.read(new File(FOLDER_PATH + "laser" + TYPE_OF_FILE));
            POWER_SHOT_TWICE_IMAGE = ImageIO.read(new File(FOLDER_PATH + "double" + TYPE_OF_FILE));
            POWER_SHOT_TRIPLE_IMAGE = ImageIO.read(new File(FOLDER_PATH + "triple" + TYPE_OF_FILE));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Prize Images Not Loaded!", "Loading Images", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private int style;
    private BufferedImage styleImage;

    int x;
    int y;



    char typeOfPrize;

    public Prize(char typeOfPrize2)
    {
        typeOfPrize = typeOfPrize2;
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

}
