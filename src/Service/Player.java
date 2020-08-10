package Service;

import Thing.Tank;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Objects;

public class Player extends Setting implements Serializable {

    private String username;
    private String password;
    private boolean keepLogin;

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


}
