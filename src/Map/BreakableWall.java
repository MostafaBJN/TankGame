package Map;

import javax.swing.*;

public class BreakableWall extends Ground {
    
    private static final int DEFAULT_HEALTH_VALUE = 100;//should discus
    private static final int MAX_HEALTH_VALUE = 5000;//should discus
    private static final int MIN_HEALTH_VALUE = 1;//should discus

    private static int defaultHealth;

    private int health;

    static {
        defaultHealth = DEFAULT_HEALTH_VALUE;
    }

    public BreakableWall(int type, int place, int VerticalPlaceInMap, int HorizontalPlaceInMap) {
        super(type, place, VerticalPlaceInMap, HorizontalPlaceInMap);
        health = defaultHealth;
    }

    public static void changeDefaultHealth(int newDefaultHealth) {
        if(newDefaultHealth < MIN_HEALTH_VALUE || newDefaultHealth > MAX_HEALTH_VALUE) {
            JOptionPane.showMessageDialog(null, "This Value Is Out Of Range", "Change Breakable Walls Health", JOptionPane.ERROR_MESSAGE);
            return;
        }
        defaultHealth = newDefaultHealth;
    }

    public boolean givingDamage(int damage) {
        //should change type of this Ground
        if(health - damage <= 0)
            return false;
        health -= damage;
        return true;
    }
}
