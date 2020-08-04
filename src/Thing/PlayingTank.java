package Thing;

public class PlayingTank extends Tank {

    private int health;

    private int x;
    private int y;

    private boolean prizePicked;
    private Prize currentPrize;

    private double velocity;
    private Degree direction;

    private boolean invincible;
    int powerOfTir;

    public PlayingTank(int health, int x, int y) {
        super();
        this.health = health;
        this.x = x;
        this.y = y;
        direction = new Degree();
        invincible = false;
    }

    public PlayingTank(Tank tank){
        super(tank);
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

    public double getVelocity() {
        return velocity;
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

}
