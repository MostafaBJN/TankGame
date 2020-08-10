package Game.Play;

import Service.Client.MainClient;
import Service.Player;
import Thing.*;
import Thing.Area;
import Thing.Map.*;

import java.util.ArrayList;
import java.util.Random;

public class GameMap {

    private Map map;
    private GameInfo gameInfo;
    private ArrayList<Player> players;
    private ArrayList<Tank> tanks;
    private ArrayList<PlayingTank> playingTanks;
    private ArrayList<Prize> prizes;
    private ArrayList<Bullet> bullets;


    public static final int TANK = 3;
    public static final int BREAKABLE_WALL = 4;
    public static final int WALL = 5;
    public static final int PRIZE = 6;
    public static final int BULLET = 7;
    public static final int FREE = 0;

    public int visualHeight;
    public int visualWidth;

    private ArrayList<ArrayList<Integer>> visualGameMapDetail;

    public GameMap(GameInfo gameInfo, ArrayList<Player> players) {
        this.map = gameInfo.getMap();
        this.gameInfo = gameInfo;
        this.players = players;
        tanks = new ArrayList<>();
        for(Player player:players){
            Tank tank = player.getTank();
            tank.setDefaultHealth(gameInfo.getTankHealth());
            tanks.add(tank);
        }
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
                            if (type == Ground.ROAD) {
                                visualGameMapDetail.get(y).add(FREE);
                            } else if (type == Ground.WALL){
                                visualGameMapDetail.get(y).add(WALL);
                            } else if (type == Ground.BREAKABLE_WALL){
                                visualGameMapDetail.get(y).add(BREAKABLE_WALL);
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
            PlayingTank playingTank = new PlayingTank(tank, xMiddle, yMiddle, players.get(players.indexOf(new Player(tank.getName()))));
            playingTanks.add(playingTank);
        }
    }

    public boolean check(PlayingTank tank){
        int xStart;
        int xEnd;
        int yStart;
        int yEnd;
        Area area = tank.getArea();
        int height = Tank.heightOfTank;
        int width = Tank.widthOfTank;

        Area middlePoint;
        int xMiddle = tank.getX();
        int yMiddle = tank.getY();

            xStart = xMiddle - (height/2);
            xEnd = xMiddle + (height/2);
            yStart = yMiddle - (width/2);
            yEnd = yMiddle + (width/2);
            return checkOutLinePointOfTank(xStart, xEnd, yStart, yEnd);
    }

    private void fillTankPlace(int xStart, int xEnd, int yStart, int yEnd){
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x ++){
                    visualGameMapDetail.get(y).set(x, TANK);
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
                    else if(state == WALL || state == BREAKABLE_WALL){
                        return false;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkOutLinePointOfTank(int xStart, int xEnd, int yStart, int yEnd) {
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

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
