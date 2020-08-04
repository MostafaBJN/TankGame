package Thing;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bullet {

    private static final long LIFE_TIME = 4000;
    private static final long DEFAULT_VELOCITY = 20;

    public static final int DEFAULT_DAMAGE_POWER = 50;
    public static final int MAX_DAMAGE_POWER = 1000;
    public static final int MIN_DAMAGE_POWER = 1;

    private Tank shooterTank;

    private int power;
    private double velocity;
    private Tank.Degree direction;
    private Timer lifeTimer;
    private long startTime;

    public Bullet(Tank bulletShooterTank) {
        this.shooterTank = bulletShooterTank;
        lifeTimer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(System.currentTimeMillis() - startTime >= LIFE_TIME){
                    //Kill Bullet
                    lifeTimer.stop();
                    shooterTank.setShootedBullet(null);
                }
            }
        });
        lifeTimer.start();
        startTime = System.currentTimeMillis();
    }

}
