package Thing;

public class VisualArea {

    private int upLeftPoint;
    private int downRightPoint;
    private double diameter;
    private int height;
    private int weight;

    public VisualArea(int upLeftPoint, int downRightPoint, int height, int weight) {
        this.upLeftPoint = upLeftPoint;
        this.downRightPoint = downRightPoint;
        this.height = height;
        this.weight = weight;
        diameter = Math.sqrt(height*2 + weight*2);
    }
}
