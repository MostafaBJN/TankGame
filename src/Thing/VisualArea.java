package Thing;

import java.io.Serializable;

public class VisualArea extends Area implements Serializable {

    private int upLeftCorner;
    private int corner1;
    private int corner2;
    private int corner3;
    private int corner4;
    private Degree direction;

    public VisualArea(int width, int height, Degree direction, int x, int y) {
        super(width, height);
    }
}
