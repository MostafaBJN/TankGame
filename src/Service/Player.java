package Service;

import Thing.Tank;

import java.net.Socket;
import java.util.Objects;

public class Player extends Setting {

    private String userName;
    private String password;
    private boolean keepLogin;

    // private ??? computer info (for checking player for no login screen)

    private Socket socket;

    private Tank tank;

    public Player(String userName, String password, boolean keepLogin) {
        this.userName = userName;
        this.password = password;
        this.keepLogin = keepLogin;
    }

    public Player(String userName, String password) {
        this.userName = userName;
        this.password = password;
        keepLogin = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        return Objects.equals(userName, player.userName);
    }

    @Override
    public int hashCode() {
        return userName != null ? userName.hashCode() : 0;
    }

    public void setKeepLogin(boolean keepLogin) {
        this.keepLogin = keepLogin;
    }
}
