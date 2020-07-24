public class BreakableWall extends Wall {
    
    private static final int DEFAULT_HEALTH_VALUE = 100;
    private static int defaultHealth;
    private int health;

    public BreakableWall() {
        defaultHealth = DEFAULT_HEALTH_VALUE;
        health = defaultHealth;
    }


}
