package Game.Play;

import Map.*;
import Thing.PlayingTank;
import Thing.Tank;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

public class GameState {

    private Map map;
    private GameMap gameMap;
    public boolean gameOver;
    private PlayingTank playingTank;

    private boolean mousePress;
    private int mouseX, mouseY;
    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;

    public GameState() {

        map = MapManager.getSelectedMap();

        makeGameMap();
        gameOver = false;

        //map = MapManager.getSelectedMap();

        //
        keyUP = false;
        keyDOWN = false;
        keyRIGHT = false;
        keyLEFT = false;
        //
        mousePress = false;
        mouseX = 0;
        mouseY = 0;
        //
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
    }

    public void makeGameMap() {
        ArrayList<Tank> tanks = new ArrayList<>();
//        for (Player player:players){
//            Tank tank = player.getTank();
//            ///
//
//        }
        tanks.add(new Tank("Mostafa"));
        tanks.add(new Tank("1"));
        tanks.add(new Tank("2"));
        tanks.add(new Tank("3"));
        tanks.add(new Tank("4"));
        tanks.add(new Tank("5"));
        gameMap = new GameMap(map, tanks);
    }


    private void ss() {
//        if (dastur.equals("turnSaeat")) {
//            tank.getDirection().clockwiseTurning();
//        } else if (dastur.equals("turnPadSaeat")) {
//            tank.getDirection().anticlockwiseTurning();
//        } else if (dastur.equals("go")) {//birun nazanad-az divar rad nashe.
//            tank.setX((int) (tank.getX() + Math.round(4 * Math.cos(Math.toRadians(tank.getDirection().getAngle())))));
//            tank.setY((int) (tank.getY() + Math.round(4 * Math.sin(Math.toRadians(tank.getDirection().getAngle())))));
//        } else if (dastur.equals("fire")) {
//            //TODO:tir zadan
//        }
    }
    /**
     * The method which updates the game state.
     */
    public void update() {
//        if (mousePress) {
//            locY = mouseY - diam / 2;
//            locX = mouseX - diam / 2;
//        }
//        if (keyUP)
//            locY -= 4;
//        if (keyDOWN)
//            locY += 4;
//        if (keyLEFT)
//            locX -= 4;
//        if (keyRIGHT)
//            locX += 4;
//
//        locX = Math.max(locX, 0);
//        locX = Math.min(locX, GameFrame.GAME_WIDTH - diam);
//        locY = Math.max(locY, 0);
//        locY = Math.min(locY, GameFrame.GAME_HEIGHT - diam);
    }


    public KeyListener getKeyListener() {
        return keyHandler;
    }
    public MouseListener getMouseListener() {
        return mouseHandler;
    }
    public MouseMotionListener getMouseMotionListener() {
        return mouseHandler;
    }


    /**
     * The keyboard handler.
     */
    class KeyHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    keyUP = true;
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN = true;
                    break;
                case KeyEvent.VK_LEFT:
                    keyLEFT = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT = true;
                    break;
                case KeyEvent.VK_ESCAPE:
                    gameOver = true;
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    keyUP = false;
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN = false;
                    break;
                case KeyEvent.VK_LEFT:
                    keyLEFT = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT = false;
                    break;
            }
        }

    }

    /**
     * The mouse handler.
     */
    class MouseHandler extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            mousePress = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mousePress = false;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
        }
    }

    public GameMap getGameMap() {
        return gameMap;
    }


}
