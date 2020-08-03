package Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ground {

    //TYPES
    public static final int UNKNOWN = -1;
    public static final int ROAD = 0;
    public static final int WALL = 1;
    public static final int BREAKABLE_WALL = 2;
//    private static final int



    //PLACES
    public static final int OUT_OF_MAP = -1;
    public static final int SQUARE = 0;
    public static final int HORIZON_LINE = 1;
    public static final int VERTICAL_LINE = 2;
    public static final int CORNER = 3;

    //STYLES
    private static final int EMPTY_FIELD = 0;
    private static final int FULL_WALL = 1;
    private static final int FULL_BREAKABLE_WALL = 2;
    private static final int HORIZONTAL_WALL = 3;
    private static final int VERTICAL_WALL = 4;
    private static final int HORIZONTAL_BREAKABLE_WALL = 5;
    private static final int VERTICAL_BREAKABLE_WALL = 6;
    private static final int HORIZONTAL_ROAD = 7;
    private static final int VERTICAL_ROAD = 8;
    private static final int HORIZONTAL_EMPTY_ROAD = 9;
    private static final int VERTICAL_EMPTY_ROAD = 10;
    private static final int CORNER_WALL = 11;
    private static final int CORNER_ROAD = 12;
    private static final int UP = 13;
    private static final int RIGHT = 14;
    private static final int DOWN = 15;
    private static final int LEFT = 16;
    private static final int UP_RIGHT = 17;
    private static final int UP_DOWN = 18;
    private static final int UP_LEFT = 19;
    private static final int RIGHT_DOWN = 20;
    private static final int RIGHT_LEFT = 21;
    private static final int DOWN_LEFT = 22;
    private static final int UP_RIGHT_DOWN = 23;
    private static final int UP_RIGHT_LEFT = 24;
    private static final int UP_DOWN_LEFT = 25;
    private static final int RIGHT_DOWN_LEFT = 26;
    private static final int UP_RIGHT_DOWN_LEFT = 27;


    //IMAGES OF STYLES
    private static BufferedImage EMPTY_FIELD_IMAGE;
    private static BufferedImage FULL_WALL_IMAGE;
    private static BufferedImage FULL_BREAKABLE_WALL_IMAGE;
    private static BufferedImage HORIZONTAL_WALL_IMAGE;
    private static BufferedImage VERTICAL_WALL_IMAGE;
    private static BufferedImage HORIZONTAL_BREAKABLE_WALL_IMAGE;
    private static BufferedImage VERTICAL_BREAKABLE_WALL_IMAGE;
    private static BufferedImage HORIZONTAL_ROAD_IMAGE;
    private static BufferedImage VERTICAL_ROAD_IMAGE;
    private static BufferedImage HORIZONTAL_EMPTY_ROAD_IMAGE;
    private static BufferedImage VERTICAL_EMPTY_ROAD_IMAGE;
    private static BufferedImage CORNER_WALL_IMAGE;
    private static BufferedImage CORNER_ROAD_IMAGE;
    private static BufferedImage UP_IMAGE;
    private static BufferedImage RIGHT_IMAGE;
    private static BufferedImage DOWN_IMAGE;
    private static BufferedImage LEFT_IMAGE;
    private static BufferedImage UP_RIGHT_IMAGE;
    private static BufferedImage UP_DOWN_IMAGE;
    private static BufferedImage UP_LEFT_IMAGE;
    private static BufferedImage RIGHT_DOWN_IMAGE;
    private static BufferedImage RIGHT_LEFT_IMAGE;
    private static BufferedImage DOWN_LEFT_IMAGE;
    private static BufferedImage UP_RIGHT_DOWN_IMAGE;
    private static BufferedImage UP_RIGHT_LEFT_IMAGE;
    private static BufferedImage UP_DOWN_LEFT_IMAGE;
    private static BufferedImage RIGHT_DOWN_LEFT_IMAGE;
    private static BufferedImage UP_RIGHT_DOWN_LEFT_IMAGE;

    //FILE
    private static final String FOLDER_PATH = "data\\map\\";
    private static final String TYPE_OF_FILE = ".png";

    //Horizontal == up/down
    //Vertical == right/left

    private int VerticalPlaceInMap;
    private int HorizontalPlaceInMap;

    private int startVerticalVisualPointInMap;
    private int startHorizontalVisualPointInMap;
    private int endVerticalVisualPointInMap;
    private int endHorizontalVisualPointInMap;

    private int type;
    private int place;
    private Area area;

    private Ground up;
    private Ground right;
    private Ground down;
    private Ground left;

    private int style;
    private BufferedImage styleImage;


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



    //PIXELS
    public static final int smallSize = HORIZONTAL_WALL_IMAGE.getHeight();
    public static final int bigSize = HORIZONTAL_WALL_IMAGE.getWidth();

    //AREAS
    public static final Area OUT_OF_MAP_AREA = new Area(0, 0);
    public static final Area SQUARE_AREA = new Area(bigSize, bigSize);
    public static final Area HORIZON_LINE_AREA = new Area(bigSize, smallSize);
    public static final Area VERTICAL_LINE_AREA = new Area(smallSize, bigSize);
    public static final Area DOT_AREA = new Area(smallSize, smallSize);

    /**
     * ALWAYS Calling With settingAroundGrounds & styleFinder
     *
     * @param type
     * @param place
     * @param VerticalPlaceInMap
     * @param HorizontalPlaceInMap
     */
    public Ground(int type, int place, int VerticalPlaceInMap, int HorizontalPlaceInMap) {

        this.VerticalPlaceInMap = VerticalPlaceInMap;
        this.HorizontalPlaceInMap = HorizontalPlaceInMap;

        if(type > BREAKABLE_WALL || type < ROAD)
            type = UNKNOWN;
        this.type = type;

        if(place > CORNER || place < SQUARE)
            place = OUT_OF_MAP;
        this.place = place;

        switch (place) {
            case SQUARE -> area = SQUARE_AREA;
            case HORIZON_LINE -> area = HORIZON_LINE_AREA;
            case VERTICAL_LINE -> area = VERTICAL_LINE_AREA;
            case CORNER -> area = DOT_AREA;
            default -> area = OUT_OF_MAP_AREA;
        }

        if(VerticalPlaceInMap % 2 == 0) {
            startVerticalVisualPointInMap = (VerticalPlaceInMap / 2) * (bigSize + smallSize);
        } else {
            startVerticalVisualPointInMap = ((VerticalPlaceInMap - 1) / 2) * (bigSize + smallSize) + smallSize;
        }
        endVerticalVisualPointInMap = startVerticalVisualPointInMap + area.getY();

        if(HorizontalPlaceInMap % 2 == 0) {
            startHorizontalVisualPointInMap = (HorizontalPlaceInMap / 2) * (bigSize + smallSize);
        } else {
            startHorizontalVisualPointInMap = ((HorizontalPlaceInMap - 1) / 2) * (bigSize + smallSize) + smallSize;
        }
        endHorizontalVisualPointInMap = startVerticalVisualPointInMap + area.getX();
    }

    /**
     * Change a Breakable wall to a Normal Road
     *
     * Change Style
     * Changing Around Grounds This Ground ou side of them,
     * Changing Style of Around Grounds
     *
     * @param breakableWall Broke Wall
     */
    public Ground(BreakableWall breakableWall) {
        this(ROAD, breakableWall.getPlace(), breakableWall.getVerticalPlaceInMap(), breakableWall.getHorizontalPlaceInMap());
        settingAroundGrounds(breakableWall.getUp(), breakableWall.getRight(), breakableWall.getDown(), breakableWall.getLeft());
        Ground up = breakableWall.getUp();
        up.setDown(this);
        up.styleFinder();
        Ground right = breakableWall.getRight();
        right.setLeft(this);
        right.styleFinder();
        Ground down = breakableWall.getDown();
        down.setUp(this);
        down.styleFinder();
        Ground left = breakableWall.getLeft();
        left.setRight(this);
        left.styleFinder();
    }

    /**
     * Setting Around Grounds
     */
    public void settingAroundGrounds(Ground up, Ground right, Ground down, Ground left) {
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
        styleFinder();
    }

    /**
     * Find the Image Of This Ground
     */
    public void styleFinder() {
        if (place == SQUARE) {
            if (type == ROAD) {//16
                if(up.getType() == ROAD && right.getType() == ROAD && down.getType() == ROAD && left.getType() == ROAD) {
                    style = UP_RIGHT_DOWN_LEFT;
                    styleImage = UP_RIGHT_DOWN_LEFT_IMAGE;
                }

                else if (up.getType() == ROAD && right.getType() == ROAD && down.getType() == ROAD && (left.getType() == WALL || left.getType() == BREAKABLE_WALL)) {
                    style = UP_RIGHT_DOWN;
                    styleImage = UP_RIGHT_DOWN_IMAGE;
                } else if (up.getType() == ROAD && right.getType() == ROAD && (down.getType() == WALL || down.getType() == BREAKABLE_WALL) && left.getType() == ROAD) {
                    style = UP_RIGHT_LEFT;
                    styleImage = UP_RIGHT_LEFT_IMAGE;
                } else if (up.getType() == ROAD && (right.getType() == WALL || right.getType() == BREAKABLE_WALL) &&  down.getType() == ROAD && left.getType() == ROAD) {
                    style = UP_DOWN_LEFT;
                    styleImage = UP_DOWN_LEFT_IMAGE;
                } else if ((up.getType() == WALL || up.getType() == BREAKABLE_WALL) && right.getType() == ROAD &&  down.getType() == ROAD && left.getType() == ROAD) {
                    style = RIGHT_DOWN_LEFT;
                    styleImage = RIGHT_DOWN_LEFT_IMAGE;
                }

                else if (up.getType() == ROAD && right.getType() == ROAD &&  (down.getType() == WALL || down.getType() == BREAKABLE_WALL) && (left.getType() == WALL || left.getType() == BREAKABLE_WALL)) {
                    style = UP_RIGHT;
                    styleImage = UP_RIGHT_IMAGE;
                } else if (up.getType() == ROAD && (right.getType() == WALL || right.getType() == BREAKABLE_WALL) &&  down.getType() == ROAD && (left.getType() == WALL || left.getType() == BREAKABLE_WALL)) {
                    style = UP_DOWN;
                    styleImage = UP_DOWN_IMAGE;
                } else if (up.getType() == ROAD && (right.getType() == WALL || right.getType() == BREAKABLE_WALL) &&  (down.getType() == WALL || down.getType() == BREAKABLE_WALL) && left.getType() == ROAD) {
                    style = UP_LEFT;
                    styleImage = UP_LEFT_IMAGE;
                } else if ((up.getType() == WALL || up.getType() == BREAKABLE_WALL) && right.getType() == ROAD &&  down.getType() == ROAD && (left.getType() == WALL || left.getType() == BREAKABLE_WALL)) {
                    style = RIGHT_DOWN;
                    styleImage = RIGHT_DOWN_IMAGE;
                } else if ((up.getType() == WALL || up.getType() == BREAKABLE_WALL) && right.getType() == ROAD &&  (down.getType() == WALL || down.getType() == BREAKABLE_WALL) && left.getType() == ROAD) {
                    style = RIGHT_LEFT;
                    styleImage = RIGHT_LEFT_IMAGE;
                } else if ((up.getType() == WALL || up.getType() == BREAKABLE_WALL) && (right.getType() == WALL || right.getType() == BREAKABLE_WALL) &&  down.getType() == ROAD && left.getType() == ROAD) {
                    style = DOWN_LEFT;
                    styleImage = DOWN_LEFT_IMAGE;
                }

                else if (up.getType() == ROAD && (right.getType() == WALL || right.getType() == BREAKABLE_WALL) &&  (down.getType() == WALL || down.getType() == BREAKABLE_WALL) && (left.getType() == WALL || left.getType() == BREAKABLE_WALL)) {
                    style = UP;
                    styleImage = UP_IMAGE;
                } else if ((up.getType() == WALL || up.getType() == BREAKABLE_WALL) && right.getType() == ROAD &&  (down.getType() == WALL || down.getType() == BREAKABLE_WALL) && (left.getType() == WALL || left.getType() == BREAKABLE_WALL)) {
                    style = RIGHT;
                    styleImage = RIGHT_IMAGE;
                } else if ((up.getType() == WALL || up.getType() == BREAKABLE_WALL) && (right.getType() == WALL || right.getType() == BREAKABLE_WALL) &&  down.getType() == ROAD && (left.getType() == WALL || left.getType() == BREAKABLE_WALL)) {
                    style = DOWN;
                    styleImage = DOWN_IMAGE;
                } else if ((up.getType() == WALL || up.getType() == BREAKABLE_WALL) && (right.getType() == WALL || right.getType() == BREAKABLE_WALL) &&  (down.getType() == WALL || down.getType() == BREAKABLE_WALL) && left.getType() == ROAD) {
                    style = LEFT;
                    styleImage = LEFT_IMAGE;
                }

                else if ((up.getType() == WALL || up.getType() == BREAKABLE_WALL) && (right.getType() == WALL || right.getType() == BREAKABLE_WALL) &&  (down.getType() == WALL || down.getType() == BREAKABLE_WALL) && (left.getType() == WALL || left.getType() == BREAKABLE_WALL)) {
                    style = EMPTY_FIELD;
                    styleImage = EMPTY_FIELD_IMAGE;
                }

            } else if (type == WALL) {
                style = FULL_WALL;
                styleImage = FULL_WALL_IMAGE;
            } else if (type == BREAKABLE_WALL) {
                style = FULL_BREAKABLE_WALL;
                styleImage = FULL_BREAKABLE_WALL_IMAGE;
            }

        }
        else if (place == HORIZON_LINE) {
            if (type == ROAD) {
                if(up.getType() == ROAD && down.getType() == ROAD){
                    style = HORIZONTAL_ROAD;
                    styleImage = HORIZONTAL_ROAD_IMAGE;
                } else {
                    style = HORIZONTAL_EMPTY_ROAD;
                    styleImage = HORIZONTAL_EMPTY_ROAD_IMAGE;
                }
            } else if (type == WALL) {
                style = HORIZONTAL_WALL;
                styleImage = HORIZONTAL_WALL_IMAGE;
            } else if (type == BREAKABLE_WALL) {
                style = HORIZONTAL_BREAKABLE_WALL;
                styleImage = HORIZONTAL_BREAKABLE_WALL_IMAGE;
            }

        }
        else if (place == VERTICAL_LINE) {
            if (type == ROAD) {
                if(right.getType() == ROAD && left.getType() == ROAD){
                    style = VERTICAL_ROAD;
                    styleImage = VERTICAL_ROAD_IMAGE;
                } else {
                    style = VERTICAL_EMPTY_ROAD;
                    styleImage = VERTICAL_EMPTY_ROAD_IMAGE;
                }
            } else if (type == WALL) {
                style = VERTICAL_WALL;
                styleImage = VERTICAL_WALL_IMAGE;
            } else if (type == BREAKABLE_WALL) {
                style = VERTICAL_BREAKABLE_WALL;
                styleImage = VERTICAL_BREAKABLE_WALL_IMAGE;
            }

        }
        else if (place == CORNER) {
            if (type == ROAD) {
                style = CORNER_ROAD;
                styleImage = CORNER_ROAD_IMAGE;
            } else if (type == WALL || type == BREAKABLE_WALL) {
                style = CORNER_WALL;
                styleImage = CORNER_WALL_IMAGE;
            }
        }
    }



    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Area getArea() {
        return area;
    }

    public Ground getUp() {
        return up;
    }

    public Ground getDown() {
        return down;
    }

    public Ground getLeft() {
        return left;
    }

    public Ground getRight() {
        return right;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setUp(Ground up) {
        this.up = up;
    }

    public void setRight(Ground right) {
        this.right = right;
    }

    public void setDown(Ground down) {
        this.down = down;
    }

    public void setLeft(Ground left) {
        this.left = left;
    }

    public int getVerticalPlaceInMap() {
        return VerticalPlaceInMap;
    }

    public void setVerticalPlaceInMap(int verticalPlaceInMap) {
        VerticalPlaceInMap = verticalPlaceInMap;
    }

    public int getHorizontalPlaceInMap() {
        return HorizontalPlaceInMap;
    }

    public void setHorizontalPlaceInMap(int horizontalPlaceInMap) {
        HorizontalPlaceInMap = horizontalPlaceInMap;
    }

    public int getStartVerticalVisualPointInMap() {
        return startVerticalVisualPointInMap;
    }

    public int getStartHorizontalVisualPointInMap() {
        return startHorizontalVisualPointInMap;
    }

    public int getEndVerticalVisualPointInMap() {
        return endVerticalVisualPointInMap;
    }

    public int getEndHorizontalVisualPointInMap() {
        return endHorizontalVisualPointInMap;
    }

    public int getStyle() {
        return style;
    }

    public BufferedImage getStyleImage() {
        return styleImage;
    }
}
