/**
 * int lives:counter of lives tank.
 * int x and int y:location of tank.
 * Prize prize:a prize of tank.
 *
 */
package Thing;


import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static Thing.Image.Tank.*;

public class Tank implements Serializable, Thing
{
    /**
     * HEALTH DEFAULTS
     */
    public static final int DEFAULT_HEALTH_VALUE = 200;//should discus
    public static final int MAX_HEALTH_VALUE = 1000;//should discus
    public static final int MIN_HEALTH_VALUE = 1;//should discus


    public static final int heightOfTank = BLACK_TANK_IMAGE.getHeight();
    public static final int widthOfTank = BLACK_TANK_IMAGE.getWidth();

    protected String name;

    protected int defaultHealth;

    protected int style;
    protected transient BufferedImage styleImage;

    protected static final Area DEFAULT_AREA = new Area(widthOfTank, heightOfTank);
    protected Area area;



    public Tank(String name) {
        this.name = name;
        defaultHealth = DEFAULT_HEALTH_VALUE;
        setStyle(selectRandomModel());
        area = DEFAULT_AREA;
    }

    public Tank(String name, int style) {
        this.name = name;
        defaultHealth = DEFAULT_HEALTH_VALUE;
        setStyle(style);
        styleFinder();
        area = DEFAULT_AREA;
    }

    public Tank(int defaultHealth, String name) {
        this.name = name;
        this.defaultHealth = defaultHealth;
        setStyle(selectRandomModel());
        area = DEFAULT_AREA;
    }

    public Tank(String name, int defaultHealth, int style) {
        this.name = name;
        this.defaultHealth = defaultHealth;
        setStyle(style);
        area = DEFAULT_AREA;
    }

    public Tank(Tank tank) {
        this(tank.name, tank.defaultHealth, tank.style);
    }


    private int selectRandomModel() {
        return new Random().nextInt(Image.Tank.NUMBER_OF_TANK_STYLES);
    }


    public void changeTankModel() {
        style = (style + 1) % NUMBER_OF_TANK_STYLES;
    }

    /**
     * Find the Image Of This Ground
     */
    public void styleFinder() {
        switch (style) {
            case BLACK_TANK -> styleImage = BLACK_TANK_IMAGE;
            case SAND_TANK -> styleImage = SAND_TANK_IMAGE;
            case RED_TANK -> styleImage = RED_TANK_IMAGE;
            case BLUE_TANK -> styleImage = BLUE_TANK_IMAGE;
            case GREEN_TANK -> styleImage = GREEN_TANK_IMAGE;
            case BLACK_INVINCIBLE_TANK -> styleImage = BLACK_INVINCIBLE_TANK_IMAGE;
            case SAND_INVINCIBLE_TANK -> styleImage = SAND_INVINCIBLE_TANK_IMAGE;
            case RED_INVINCIBLE_TANK -> styleImage = RED_INVINCIBLE_TANK_IMAGE;
            case BLUE_INVINCIBLE_TANK -> styleImage = BLUE_INVINCIBLE_TANK_IMAGE;
            case GREEN_INVINCIBLE_TANK -> styleImage = GREEN_INVINCIBLE_TANK_IMAGE;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tank)) return false;

        Tank tank = (Tank) o;

        return Objects.equals(name, tank.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public void setStyle(int style) {
        this.style = style;
        styleFinder();
    }

    public BufferedImage getStyleImage() {
        return styleImage;
    }

    public int getDefaultHealth() {
        return defaultHealth;
    }

    public String getName() {
        return name;
    }

    public int getModel() {
        return style;
    }

    public Area getArea() {
        return area;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefaultHealth(int defaultHealth) {
        this.defaultHealth = defaultHealth;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setStyleImage(BufferedImage styleImage) {
        this.styleImage = styleImage;
    }

}
