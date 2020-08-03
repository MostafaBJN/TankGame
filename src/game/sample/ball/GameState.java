package game.sample.ball;

import Map.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameState {

    private Map map;
    public boolean gameOver;

    public GameState() {
        gameOver = false;

        map = MapManager.getSelectedMap();
    }

    public void update() {
    }

    public Map getMap() {
        return map;
    }
}
