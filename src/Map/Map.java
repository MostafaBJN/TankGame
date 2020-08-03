package Map;

import java.util.ArrayList;

public class Map {

    private String name;
    private ArrayList<ArrayList<Integer>> intsOfMap;
    private ArrayList<ArrayList<Ground>> grounds;
    private ArrayList<Ground> groundsOneLineList;
    //vertical
    private int height;
    private int visualHeight;
    //horizontal
    private int width;
    private int visualWidth;
    /*
      012345678
    0 *-*-*-*-*
    1 |x|x|x|x|
    2 *-*-*-*-*
    3 |x|x|x|x|
    4 *-*-*-*-*

    w == -
    h == |
    c == *
    g == x

        a * b
    width * height

    w = 2a+1 , 2b
    h = 2a , 2b+1
    c = 2a , 2b
    g = 2a+1 , 2b+1

     */

    public Map(String name, ArrayList<ArrayList<Integer>> intsOfMap, int height, int width) {
        this.name = name;
        this.intsOfMap = intsOfMap;
        this.height = height;
        this.width = width;
        grounds = new ArrayList<>();
        groundsOneLineList = new ArrayList<>();
        visualHeight = ((height - 1) / 2) * (Ground.bigSize + Ground.smallSize) + (Ground.smallSize);
        visualWidth = ((width - 1) / 2) * (Ground.bigSize + Ground.smallSize) + (Ground.smallSize);
        makingNewMap();
        readyingGrounds();
    }

    private void makingNewMap() {
        for (int b = 0; b < height; b++) {
            ArrayList<Ground> horizonGrounds = new ArrayList<>();
            grounds.add(horizonGrounds);
            for (int a = 0; a < width; a++) {
                int type = intsOfMap.get(b).get(a);

                //roads
                if (a % 2 == 1 && b % 2 == 1) {
                    if(type == Ground.BREAKABLE_WALL)
                        horizonGrounds.add(new BreakableWall(type, Ground.SQUARE, b, a));
                    else
                        horizonGrounds.add(new Ground(type, Ground.SQUARE, b, a));
                }
                //horizontal Wall
                else if (a % 2 == 1) {
                    if(type == Ground.BREAKABLE_WALL)
                        horizonGrounds.add(new BreakableWall(type, Ground.HORIZON_LINE, b, a));
                    else
                        horizonGrounds.add(new Ground(type, Ground.HORIZON_LINE, b, a));
                }
                //Vertical Wall
                else if (b % 2 == 1) {
                    if(type == Ground.BREAKABLE_WALL)
                        horizonGrounds.add(new BreakableWall(type, Ground.VERTICAL_LINE, b, a));
                    else
                        horizonGrounds.add(new Ground(type, Ground.VERTICAL_LINE, b, a));
                }
                //Corners
                else {
                    if(type == Ground.BREAKABLE_WALL)
                        horizonGrounds.add(new BreakableWall(type, Ground.CORNER, b, a));
                    else
                        horizonGrounds.add(new Ground(type, Ground.CORNER, b, a));
                }

                groundsOneLineList.add(horizonGrounds.get(a));
            }
        }
    }

    private void readyingGrounds() {
        for (int b = 0; b < height; b++) {
            for (int a = 0; a < width; a++) {
                Ground ground = grounds.get(b).get(a);
                int vertical;
                int horizontal;

                Ground up;
                horizontal = a;
                vertical = b - 1;
                if(vertical < 0)
                    up = new Ground(Ground.UNKNOWN, Ground.OUT_OF_MAP, vertical, horizontal);
                else
                    up = grounds.get(vertical).get(horizontal);

                Ground right;
                horizontal = a + 1;
                vertical = b;
                if(horizontal >= width)
                    right = new Ground(Ground.UNKNOWN, Ground.OUT_OF_MAP, vertical, horizontal);
                else
                    right = grounds.get(vertical).get(horizontal);

                Ground down;
                horizontal = a;
                vertical = b + 1;
                if(vertical >= height)
                    down = new Ground(Ground.UNKNOWN, Ground.OUT_OF_MAP, vertical, horizontal);
                else
                    down = grounds.get(vertical).get(horizontal);

                Ground left;
                horizontal = a - 1;
                vertical = b ;
                if(horizontal < 0)
                    left = new Ground(Ground.UNKNOWN, Ground.OUT_OF_MAP, vertical, horizontal);
                else
                    left = grounds.get(vertical).get(horizontal);

                ground.settingAroundGrounds(up, right, down, left);
            }
        }
    }

    /**
     * replacing this wall with road in grounds List
     * changing a broke wall to a road
     *
     * @param breakableWallGround Broke Wall
     */
    public void breakableWallBreaks(Ground breakableWallGround) {
        BreakableWall breakableWall;
        if(breakableWallGround instanceof BreakableWall){
            breakableWall = (BreakableWall) breakableWallGround;
        }
        else {
            System.out.println("NOT A BREAKABLE WALL");
            return;
        }
        grounds.get(breakableWall.getVerticalPlaceInMap()).set(breakableWall.getHorizontalPlaceInMap(), new Ground(breakableWall));
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ArrayList<Integer>> getIntsOfMap() {
        return intsOfMap;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<ArrayList<Ground>> getGrounds() {
        return grounds;
    }

    public int getVisualHeight() {
        return visualHeight;
    }

    public int getVisualWidth() {
        return visualWidth;
    }

    public ArrayList<Ground> getGroundsOneLineList() {
        return groundsOneLineList;
    }
}
