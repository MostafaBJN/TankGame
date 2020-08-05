package Thing;

public class VisualArea extends Area {

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
