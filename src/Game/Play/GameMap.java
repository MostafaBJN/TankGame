package Game.Play;

import Map.*;
import Thing.Area;
import Thing.Bullet;
import Thing.PlayingTank;
import Thing.Prize;
import Thing.Tank;

import java.util.ArrayList;
import java.util.Random;

public class GameMap {

    private Map map;
    private ArrayList<Tank> tanks;
    private ArrayList<PlayingTank> playingTanks;
    private ArrayList<Prize> prizes;
    private ArrayList<Bullet> bullets;

    public static final int CANT_GO = 1;
    public static final int CAN_GO = 2;
    public static final int FREE = 0;

    public int visualHeight;
    public int visualWidth;

    private ArrayList<ArrayList<Integer>> visualGameMapDetail;

    public GameMap(Map map, ArrayList<Tank> tanks) {
        this.map = map;
        this.tanks = tanks;
        visualGameMapDetail = new ArrayList<>();
        visualHeight = map.getVisualHeight();
        visualWidth = map.getVisualWidth();
        for (int j = 0; j < visualHeight; j++) {
            visualGameMapDetail.add(new ArrayList<>());
        }
        makeEmptyVisualMap();
        placingTanks();
    }

    public void makeEmptyVisualMap() {
        boolean find = false;

        for (int y = 0; y < visualHeight; y++) {
            for (int x = 0; x < visualWidth; x++) {
                find = false;
                for (ArrayList<Ground> hGroundList : map.getGrounds()) {
                    for (Ground ground : hGroundList) {
                        if (checkPointInArea(x, y,
                                ground.getStartHorizontalVisualPointInMap(),
                                ground.getEndHorizontalVisualPointInMap(),
                                ground.getStartVerticalVisualPointInMap(),
                                ground.getEndVerticalVisualPointInMap())) {
                            int type = ground.getType();
                            if (type == 0) {
                                visualGameMapDetail.get(y).add(FREE);
                            } else {
                                visualGameMapDetail.get(y).add(CANT_GO);
                            }
                            find = true;
                        }
                    }
                    if (find = false) {
                        System.out.println("BAD BAKT SHODIM");
                    }
                }
            }
        }
    }

    public void placingTanks() {
        playingTanks = new ArrayList<>();
        for (Tank tank:tanks){
            int xStart;
            int xEnd;
            int yStart;
            int yEnd;
            Area area = tank.getArea();
            int height = Tank.heightOfTank;
            int width = Tank.widthOfTank;

            Area middlePoint;
            int xMiddle;
            int yMiddle;

            do {
                middlePoint = generatePoint();
                xMiddle = middlePoint.getWidth();
                yMiddle = middlePoint.getHeight();
                xStart = xMiddle - (height/2);
                xEnd = xMiddle + (height/2);
                yStart = yMiddle - (width/2);
                yEnd = yMiddle + (width/2);
            } while (!checkOutLinePointOfTank(xStart, xEnd, yStart, yEnd));
            System.out.println(tank.getName() + " " + tank.getModel() + " : y = " + yMiddle + " , x = " + xMiddle);
            fillTankPlace(xStart, xEnd, yStart, yEnd);
            PlayingTank playingTank = new PlayingTank(tank, xMiddle, yMiddle);
            playingTanks.add(playingTank);
        }
    }

    private void fillTankPlace(int xStart, int xEnd, int yStart, int yEnd){
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x ++){
                    visualGameMapDetail.get(y).set(x, CANT_GO);
            }
        }
    }

    private boolean checkPointInArea(int x, int y, int xStart, int xEnd, int yStart, int yEnd) {
        if(x >= xStart && x < xEnd){
            return y >= yStart && y < yEnd;
        }
        else return false;
    }

    private boolean checkAllOfMap(int xPoint, int yPoint) {
        boolean find = false;

        for (int y = 0; y < visualHeight; y++) {
            for (int x = 0; x < visualWidth; x++) {
                if(x == xPoint && y == yPoint) {
                    int state = visualGameMapDetail.get(y).get(x);
                    if(state == FREE){
                        return true;
                    }
                    else if(state == CANT_GO){
                        return false;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkOutLinePointOfTank(int xStart, int xEnd, int yStart, int yEnd) {
//        for (int y = yStart; y < yEnd; y++) {
//            for (int x = xStart; x < xEnd; x += (xEnd - xStart)-1){
//                if(!checkAllOfMap(x, y)){
//                    return false;
//                }
//            }
//        }
//        for (int x = xStart; x < xEnd; x++) {
//            for (int y = yStart; y < xEnd; y += (yEnd - xStart)-1){
//                if(!checkAllOfMap(x, y)){
//                    return false;
//                }
//            }
//        }
        for (int y = yStart; y <= yEnd; y++) {
            for (int x = xStart; x <= xEnd; x++){
                if(!checkAllOfMap(x, y)){
                    return false;
                }
            }
        }
        return true;
    }


    public Area generatePoint() {
        Random random = new Random();
        return new Area(random.nextInt(visualWidth), random.nextInt(visualHeight));
    }

    public Map getMap() {
        return map;
    }

    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    public ArrayList<PlayingTank> getPlayingTanks() {
        return playingTanks;
    }

    public ArrayList<Prize> getPrizes() {
        return prizes;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public int getVisualHeight() {
        return visualHeight;
    }

    public int getVisualWidth() {
        return visualWidth;
    }

    public ArrayList<ArrayList<Integer>> getVisualGameMapDetail() {
        return visualGameMapDetail;
    }
}
