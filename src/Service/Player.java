package Service;

import Thing.Tank;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class Player extends Setting implements Serializable {

    private String username;
    private String password;
    private boolean keepLogin;
    private boolean computer;
    // private ??? computer info (for checking player for no login screen)

    private Tank tank;

    public Player(String username, String password, boolean keepLogin) {
        super();
        this.username = username;
        this.password = password;
        this.keepLogin = keepLogin;
        tank = new Tank(username);
    }

    public Player(String username, String password) {
        super();
        this.username = username;
        this.password = password;
        keepLogin = false;
        tank = new Tank(username);
    }

    public Player(String username) {
        super();
        computer = true;
        this.username = username;
        tank = new Tank(username);
    }

    public static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static Random rnd = new Random();

    public static String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
    public static String randomString(){
        int len = rnd.nextInt(10);
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public BufferedImage getPictureOfTank() {
        return tank.getStyleImage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        return Objects.equals(username, player.username);
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

    public void setKeepLogin(boolean keepLogin) {
        this.keepLogin = keepLogin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isKeepLogin() {
        return keepLogin;
    }

    public Tank getTank() {
        return tank;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public boolean isComputer() {
        return computer;
    }

    public void setComputer(boolean computer) {
        this.computer = computer;
    }
}
