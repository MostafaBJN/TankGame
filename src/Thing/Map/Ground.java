package Thing.Map;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import static Thing.Image.Ground.*;
import Thing.Thing;

public class Ground implements Thing, Serializable {

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
    private transient BufferedImage styleImage;




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
        endHorizontalVisualPointInMap = startHorizontalVisualPointInMap + area.getX();
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

    @Override
    public void setStyle(int style) {
        this.style = style;
        styleFinder();
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
