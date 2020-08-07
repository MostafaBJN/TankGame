package Thing.Map;

import javax.swing.*;
import java.io.Serializable;

public class BreakableWall extends Ground implements Serializable {
    
    public static final int DEFAULT_HEALTH_VALUE = 1000;//should discus
    public static final int MAX_HEALTH_VALUE = 10000;//should discus
    public static final int MIN_HEALTH_VALUE = 1;//should discus

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

    /**
     * give Damage to Breakable Wall
     * & checking for Breaking a Wall
     *
     * @param damage value of taken damage
     * @return Is Wall Still Live
     */
    public boolean givingDamage(int damage) {
        //should change type of this Ground
        if(health - damage <= 0)
            return false;
        health -= damage;
        return true;
    }

    public static int getDefaultHealth() {
        return defaultHealth;
    }

    public int getHealth() {
        return health;
    }
}
