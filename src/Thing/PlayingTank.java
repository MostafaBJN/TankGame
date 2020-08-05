package Thing;


public class PlayingTank extends Tank {

    private int health;

    private int x;
    private int y;

    VisualArea visualArea;

    private boolean prizePicked;
    private Prize currentPrize;

    private static final int DEFAULT_SPEED = 20;
    private static final int DEFAULT_ANGLE = -90;

    private double xVelocity;
    private double yVelocity;
    private Degree direction;

    private boolean invincible;
    private int powerOfTir;

    public PlayingTank(Tank tank, int x, int y){
        super(tank);
        health = defaultHealth;
        this.x = x;
        this.y = y;
        direction = new Degree(DEFAULT_ANGLE);
        invincible = false;
        powerOfTir = Bullet.DEFAULT_DAMAGE_POWER;
        visualArea = new VisualArea(widthOfTank, health, direction, x, y);
    }





    public int getHealth() {
        return health;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Prize getCurrentPrize() {
        return currentPrize;
    }

    public Degree getDirection() {
        return direction;
    }

    public int getPowerOfTir() {
        return powerOfTir;
    }

    public void setCurrentPrize(Prize currentPrize) {
        this.currentPrize = currentPrize;
    }


    public VisualArea getVisualArea() {
        return visualArea;
    }
}
