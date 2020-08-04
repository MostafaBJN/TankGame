package Service;

import Service.Server.SaveAndLoad;

public class Command {

    public static final int MAIN_SERVER_PORT = 9999;

    public static class MainMenu {
        public static final int UNKNOWN = -1;
        public static final int SIGN_UP = 0;
        public static final int TRY_TO_LOGIN = 1;
        public static final int LOGGING_IN = 2;
    }

    public static class Login {
        public static final int ERROR = -1;
        public static final int WRONG_PASSWORD = 2;
        public static final int NO_USERNAME = 1;
        public static final int SUCCESSFUL = 0;
    }


}
