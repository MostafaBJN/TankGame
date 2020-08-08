package Game.Play;

import Game.Run.Run;
import Service.Command;
import Thing.Map.*;
import Service.Player;
import Thing.PlayingTank;
import Thing.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameState {

    private Run run;
    public boolean gameOver;
    private Player activePlayer;
    private PlayingTank playingTank;

    private boolean mousePress;
    private int mouseX, mouseY;
    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT, keySpace;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;

    public GameState(Run run, Player activePlayer) {
        this.activePlayer = activePlayer;
        this.run = run;

        for(PlayingTank playingTank:run.getGameMap().getPlayingTanks()){
            if(playingTank.getOwner().equals(activePlayer)){
                this.playingTank = playingTank;
            }
        }
        gameOver = false;

        keySpace = false;
        keyUP = false;
        keyDOWN = false;
        keyRIGHT = false;
        keyLEFT = false;

        mousePress = false;
        mouseX = 0;
        mouseY = 0;
        //
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
    }

    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {

        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads));
        double cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.setColor(Color.RED);
        g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
        g2d.dispose();

        return rotated;
    }

    /**
     * The method which updates the game state.
     */
    public void update() {
//        if (mousePress) {
//            playingTank.setY(mouseY - diam / 2);
//            locX = mouseX - diam / 2;
//        }


        if (keyUP) {
            playingTank.setX((int) (playingTank.getX() + Math.round(playingTank.DEFAULT_SPEED * Math.cos(Math.toRadians(playingTank.getDirection().getAngle())))));
            playingTank.setY((int) (playingTank.getY() + Math.round(playingTank.DEFAULT_SPEED * Math.sin(Math.toRadians(playingTank.getDirection().getAngle())))));
        }
//        if (keyDOWN)
//            locY += 4;


        if (keyLEFT) {
            playingTank.getDirection().clockwiseTurning();
            rotateImageByDegrees(playingTank.getStyleImage(), playingTank.getDirection().getAngle());
        }
        if (keyRIGHT) {
            playingTank.getDirection().anticlockwiseTurning();
            rotateImageByDegrees(playingTank.getStyleImage(), playingTank.getDirection().getAngle());
        }
        if(keySpace)
            playingTank.shootBullet();
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
                case KeyEvent.VK_SPACE:
                    keySpace = true;
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
                case KeyEvent.VK_SPACE:
                    keySpace = false;
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

    public Run getRun() {
        return run;
    }

    public void setRun(Run run) {
        this.run = run;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public PlayingTank getPlayingTank() {
        return playingTank;
    }

    public void setPlayingTank(PlayingTank playingTank) {
        this.playingTank = playingTank;
    }

    public boolean isMousePress() {
        return mousePress;
    }

    public void setMousePress(boolean mousePress) {
        this.mousePress = mousePress;
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public boolean isKeyUP() {
        return keyUP;
    }

    public void setKeyUP(boolean keyUP) {
        this.keyUP = keyUP;
    }

    public boolean isKeyDOWN() {
        return keyDOWN;
    }

    public void setKeyDOWN(boolean keyDOWN) {
        this.keyDOWN = keyDOWN;
    }

    public boolean isKeyRIGHT() {
        return keyRIGHT;
    }

    public void setKeyRIGHT(boolean keyRIGHT) {
        this.keyRIGHT = keyRIGHT;
    }

    public boolean isKeyLEFT() {
        return keyLEFT;
    }

    public void setKeyLEFT(boolean keyLEFT) {
        this.keyLEFT = keyLEFT;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }

    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }

    public void setMouseHandler(MouseHandler mouseHandler) {
        this.mouseHandler = mouseHandler;
    }
}
