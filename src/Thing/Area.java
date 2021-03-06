package Thing;

import java.io.Serializable;

/**
 * Area of Any Object In G
 */
public class Area implements Serializable {

    private int width;
    private int height;
    private double diameter;

    public Area(int width, int height) {
        this.width = width;
        this.height = height;
        diameter = Math.sqrt(height*2 + width*2);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
