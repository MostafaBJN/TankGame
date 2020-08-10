package Service;

import Service.Server.SaveAndLoad;

public class Command {

    public static final int MAIN_SERVER_PORT = 9999;

    public static class MainMenu {
        public static final int UNKNOWN = -1;
        public static final int SIGN_UP = 0;
        public static final int TRY_TO_LOGIN = 1;
        public static final int LOGGING_IN = 2;
        public static final int GET_LIST_OF_MULTIPLAYER_GAMES = 3;
        public static final int ADD_GAME = 4;
        public static final int GET_PLAYER_INFO_FROM_SERVER = 5;
        public static final int SEND_PLAYER_INFO_TO_SERVER = 6;
    }

    public static class Login {
        public static final int ERROR = -1;
        public static final int WRONG_PASSWORD = 2;
        public static final int NO_USERNAME = 1;
        public static final int SUCCESSFUL = 0;
    }

    public static class PlayGame {
        public static final int SEND_PLAYER_INFO_TO_GAME_SERVER = 0;
        public static final int SEND_GAME_MAP_TO_SERVER = 1;
        public static final int GET_GAME_MAP_FROM_SERVER = 2;
        public static final int GET_GAME_INFO_UPDATE = 3;
        public static final int GET_START_OF_GAME = 4;
    }


}
