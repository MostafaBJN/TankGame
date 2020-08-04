package Service;

import Thing.Tank;

import java.net.Socket;

public class Player {

    private String userName;
    private String password;
    private boolean keepLogin;

    // private ??? computer info (for checking player for no login screen)

    private Socket socket;

    private boolean isComputer;

    private Tank tank;

    public Setting setting;

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
}
