package Map;

import Map.*;

public class Ground {

    //TYPES
    public static final int UNKNOWN = -1;
    public static final int ROAD = 0;
    public static final int WALL = 1;
    public static final int BREAKABLE_WALL = 2;
//    private static final int

    //PIXELS
    public static final int smallSize = 5;
    public static final int bigSize = smallSize * 6;

    //AREAS
    public static final Area OUT_OF_MAP_AREA = new Area(0, 0);
    public static final Area SQUARE_AREA = new Area(bigSize, bigSize);
    public static final Area HORIZON_LINE_AREA = new Area(bigSize, smallSize);
    public static final Area VERTICAL_LINE_AREA = new Area(smallSize, bigSize);
    public static final Area DOT_AREA = new Area(smallSize, smallSize);

    //PLACES
    public static final int OUT_OF_MAP = -1;
    public static final int SQUARE = 0;
    public static final int HORIZON_LINE = 1;
    public static final int VERTICAL_LINE = 2;
    public static final int DOT = 3;

    //Horizontal == up/down
    //Vertical == right/left

    private int VerticalPlaceInMap;
    private int HorizontalPlaceInMap;

    private int type;
    private int place;
    private Area area;

    private Ground up;
    private Ground right;
    private Ground down;
    private Ground left;

    public Ground(int type, int place, int VerticalPlaceInMap, int HorizontalPlaceInMap) {

        this.VerticalPlaceInMap = VerticalPlaceInMap;
        this.HorizontalPlaceInMap = HorizontalPlaceInMap;

        if(type > BREAKABLE_WALL || type < ROAD)
            type = UNKNOWN;
        this.type = type;

        if(place > DOT || place < SQUARE)
            place = OUT_OF_MAP;
        this.place = place;

        switch (place) {
            case SQUARE -> area = SQUARE_AREA;
            case HORIZON_LINE -> area = HORIZON_LINE_AREA;
            case VERTICAL_LINE -> area = VERTICAL_LINE_AREA;
            case DOT -> area = DOT_AREA;
            default -> area = OUT_OF_MAP_AREA;
        }
    }

    public void settingAroundGrounds(Ground up, Ground right, Ground down, Ground left) {
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
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
}
