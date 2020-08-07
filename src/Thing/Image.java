package Thing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {

    public static class Tank {
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
        public static BufferedImage BLACK_TANK_IMAGE;
        public static BufferedImage SAND_TANK_IMAGE;
        public static BufferedImage RED_TANK_IMAGE;
        public static BufferedImage BLUE_TANK_IMAGE;
        public static BufferedImage GREEN_TANK_IMAGE;
        public static BufferedImage BLACK_INVINCIBLE_TANK_IMAGE;
        public static BufferedImage SAND_INVINCIBLE_TANK_IMAGE;
        public static BufferedImage RED_INVINCIBLE_TANK_IMAGE;
        public static BufferedImage BLUE_INVINCIBLE_TANK_IMAGE;
        public static BufferedImage GREEN_INVINCIBLE_TANK_IMAGE;


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
    }

    public static class Prize {
        /**
         * TYPE
         */
        public static final int SHIELD = 0;
        public static final int HEALTH_INCREASE = 1;
        public static final int LASER = 2;
        public static final int POWER_SHOT_TWICE = 3;
        public static final int POWER_SHOT_TRIPLE = 4;

        /**
         * IMAGE OF TYPE
         */
        public static BufferedImage SHIELD_IMAGE;
        public static BufferedImage HEALTH_INCREASE_IMAGE;
        public static BufferedImage LASER_IMAGE;
        public static BufferedImage POWER_SHOT_TWICE_IMAGE;
        public static BufferedImage POWER_SHOT_TRIPLE_IMAGE;

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
    }

    public static class Bullet {
        /**
         * STYLE
         */
        public static final int STANDARD = 0;
        public static final int DOUBLE = 1;
        public static final int TRIPLE = 2;
        public static final int LASER = 3;


        /**
         * IMAGES OF STYLES
         */
        public static BufferedImage STANDARD_IMAGE;
        public static BufferedImage DOUBLE_IMAGE;
        public static BufferedImage TRIPLE_IMAGE;
        public static BufferedImage LASER_IMAGE;

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
    }

    public static class Ground {
        //TYPES
        public static final int UNKNOWN = -1;
        public static final int ROAD = 0;
        public static final int WALL = 1;
        public static final int BREAKABLE_WALL = 2;



        //PLACES
        public static final int OUT_OF_MAP = -1;
        public static final int SQUARE = 0;
        public static final int HORIZON_LINE = 1;
        public static final int VERTICAL_LINE = 2;
        public static final int CORNER = 3;

        //STYLES
        public static final int EMPTY_FIELD = 0;
        public static final int FULL_WALL = 1;
        public static final int FULL_BREAKABLE_WALL = 2;
        public static final int HORIZONTAL_WALL = 3;
        public static final int VERTICAL_WALL = 4;
        public static final int HORIZONTAL_BREAKABLE_WALL = 5;
        public static final int VERTICAL_BREAKABLE_WALL = 6;
        public static final int HORIZONTAL_ROAD = 7;
        public static final int VERTICAL_ROAD = 8;
        public static final int HORIZONTAL_EMPTY_ROAD = 9;
        public static final int VERTICAL_EMPTY_ROAD = 10;
        public static final int CORNER_WALL = 11;
        public static final int CORNER_ROAD = 12;
        public static final int UP = 13;
        public static final int RIGHT = 14;
        public static final int DOWN = 15;
        public static final int LEFT = 16;
        public static final int UP_RIGHT = 17;
        public static final int UP_DOWN = 18;
        public static final int UP_LEFT = 19;
        public static final int RIGHT_DOWN = 20;
        public static final int RIGHT_LEFT = 21;
        public static final int DOWN_LEFT = 22;
        public static final int UP_RIGHT_DOWN = 23;
        public static final int UP_RIGHT_LEFT = 24;
        public static final int UP_DOWN_LEFT = 25;
        public static final int RIGHT_DOWN_LEFT = 26;
        public static final int UP_RIGHT_DOWN_LEFT = 27;


        //IMAGES OF STYLES
        public static BufferedImage EMPTY_FIELD_IMAGE;
        public static BufferedImage FULL_WALL_IMAGE;
        public static BufferedImage FULL_BREAKABLE_WALL_IMAGE;
        public static BufferedImage HORIZONTAL_WALL_IMAGE;
        public static BufferedImage VERTICAL_WALL_IMAGE;
        public static BufferedImage HORIZONTAL_BREAKABLE_WALL_IMAGE;
        public static BufferedImage VERTICAL_BREAKABLE_WALL_IMAGE;
        public static BufferedImage HORIZONTAL_ROAD_IMAGE;
        public static BufferedImage VERTICAL_ROAD_IMAGE;
        public static BufferedImage HORIZONTAL_EMPTY_ROAD_IMAGE;
        public static BufferedImage VERTICAL_EMPTY_ROAD_IMAGE;
        public static BufferedImage CORNER_WALL_IMAGE;
        public static BufferedImage CORNER_ROAD_IMAGE;
        public static BufferedImage UP_IMAGE;
        public static BufferedImage RIGHT_IMAGE;
        public static BufferedImage DOWN_IMAGE;
        public static BufferedImage LEFT_IMAGE;
        public static BufferedImage UP_RIGHT_IMAGE;
        public static BufferedImage UP_DOWN_IMAGE;
        public static BufferedImage UP_LEFT_IMAGE;
        public static BufferedImage RIGHT_DOWN_IMAGE;
        public static BufferedImage RIGHT_LEFT_IMAGE;
        public static BufferedImage DOWN_LEFT_IMAGE;
        public static BufferedImage UP_RIGHT_DOWN_IMAGE;
        public static BufferedImage UP_RIGHT_LEFT_IMAGE;
        public static BufferedImage UP_DOWN_LEFT_IMAGE;
        public static BufferedImage RIGHT_DOWN_LEFT_IMAGE;
        public static BufferedImage UP_RIGHT_DOWN_LEFT_IMAGE;

        //FILE
        private static final String FOLDER_PATH = "data\\map\\";
        private static final String TYPE_OF_FILE = ".png";


        /* INSERTING IMAGES */
        static {
            try {
                EMPTY_FIELD_IMAGE = ImageIO.read(new File(FOLDER_PATH + "emptyField" + TYPE_OF_FILE));
                FULL_WALL_IMAGE = ImageIO.read(new File(FOLDER_PATH + "Wall" + TYPE_OF_FILE));
                FULL_BREAKABLE_WALL_IMAGE = ImageIO.read(new File(FOLDER_PATH + "breakableWall" + TYPE_OF_FILE));
                HORIZONTAL_WALL_IMAGE = ImageIO.read(new File(FOLDER_PATH + "upDownWall" + TYPE_OF_FILE));
                VERTICAL_WALL_IMAGE = ImageIO.read(new File(FOLDER_PATH + "rightLeftWall" + TYPE_OF_FILE));
                HORIZONTAL_BREAKABLE_WALL_IMAGE = ImageIO.read(new File(FOLDER_PATH + "upDownBreakableWall" + TYPE_OF_FILE));
                VERTICAL_BREAKABLE_WALL_IMAGE = ImageIO.read(new File(FOLDER_PATH + "rightLeftBreakableWall" + TYPE_OF_FILE));
                HORIZONTAL_ROAD_IMAGE = ImageIO.read(new File(FOLDER_PATH + "upDownRoad" + TYPE_OF_FILE));
                VERTICAL_ROAD_IMAGE = ImageIO.read(new File(FOLDER_PATH + "rightLeftRoad" + TYPE_OF_FILE));
                HORIZONTAL_EMPTY_ROAD_IMAGE = ImageIO.read(new File(FOLDER_PATH + "upDownEmptyRoad" + TYPE_OF_FILE));
                VERTICAL_EMPTY_ROAD_IMAGE = ImageIO.read(new File(FOLDER_PATH + "rightLeftEmptyRoad" + TYPE_OF_FILE));
                CORNER_WALL_IMAGE = ImageIO.read(new File(FOLDER_PATH + "cornerWall" + TYPE_OF_FILE));
                CORNER_ROAD_IMAGE = ImageIO.read(new File(FOLDER_PATH + "cornerRoad" + TYPE_OF_FILE));
                UP_IMAGE = ImageIO.read(new File(FOLDER_PATH + "up" + TYPE_OF_FILE));
                RIGHT_IMAGE = ImageIO.read(new File(FOLDER_PATH + "right" + TYPE_OF_FILE));
                DOWN_IMAGE = ImageIO.read(new File(FOLDER_PATH + "down" + TYPE_OF_FILE));
                LEFT_IMAGE = ImageIO.read(new File(FOLDER_PATH + "left" + TYPE_OF_FILE));
                UP_RIGHT_IMAGE = ImageIO.read(new File(FOLDER_PATH + "upRight" + TYPE_OF_FILE));
                UP_DOWN_IMAGE = ImageIO.read(new File(FOLDER_PATH + "upDown" + TYPE_OF_FILE));
                UP_LEFT_IMAGE = ImageIO.read(new File(FOLDER_PATH + "upLeft" + TYPE_OF_FILE));
                RIGHT_DOWN_IMAGE = ImageIO.read(new File(FOLDER_PATH + "rightDown" + TYPE_OF_FILE));
                RIGHT_LEFT_IMAGE = ImageIO.read(new File(FOLDER_PATH + "rightLeft" + TYPE_OF_FILE));
                DOWN_LEFT_IMAGE = ImageIO.read(new File(FOLDER_PATH + "downLeft" + TYPE_OF_FILE));
                UP_RIGHT_DOWN_IMAGE = ImageIO.read(new File(FOLDER_PATH + "upRightDown" + TYPE_OF_FILE));
                UP_RIGHT_LEFT_IMAGE = ImageIO.read(new File(FOLDER_PATH + "upRightLeft" + TYPE_OF_FILE));
                UP_DOWN_LEFT_IMAGE = ImageIO.read(new File(FOLDER_PATH + "upDownLeft" + TYPE_OF_FILE));
                RIGHT_DOWN_LEFT_IMAGE = ImageIO.read(new File(FOLDER_PATH + "rightDownLeft" + TYPE_OF_FILE));
                UP_RIGHT_DOWN_LEFT_IMAGE = ImageIO.read(new File(FOLDER_PATH + "upRightDownLeft" + TYPE_OF_FILE));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Map Images Not Loaded!", "Loading Images", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
