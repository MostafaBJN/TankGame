package Game.GUIMenu;


import Service.Player;

import javax.swing.*;

public class GUIManager {
    //All GUIs
    private static LoginForm login;
    private static MainMenu mainMenu;
    private static PlayMultiPlayerMenu playMultiPlayerMenu;
    private static AddGame addGame;
    private static Setting setting;
//    private static PlayWithPc playWithPc;

    static {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private GUIManager() { }

    public static void openLogin(){
        login = new LoginForm();
        new Thread(login).start();
    }

    public static void closeLogin(){
        login.closeGUI();
    }

    public static void openMainMenu(){
        mainMenu = new MainMenu("MAIN MENU");
        mainMenu.showGUI();
    }

    public static void closeMainMenu(){
        mainMenu.closeGUI();
    }

    public static void openPlayMultiPlayerMenu(){
        playMultiPlayerMenu = new PlayMultiPlayerMenu();
        playMultiPlayerMenu.showGUI();
    }

    public static void closePlayMultiPlayerMenu(){
        playMultiPlayerMenu.closeGUI();
    }

    public static void openAddGame(){
        addGame = new AddGame("Add Game");
        addGame.showGUI();
    }

    public static void closeAddGame(){
        addGame.closeGUI();
    }

    public static void openSetting(){
        setting = new Setting("Setting", new Player("",""));
        setting.showGUI();
    }

    public static void closeSetting(){
        setting.closeGUI();
    }

//    public static void open(){
//         = new ("");
//        .showGUI();
//    }
//
//    public static void close(){
//        closeGUI();
//    }

    public static class ShowMessage implements Runnable{

        private String message;
        private String title;
        private int type;
        public static final int ERROR = JOptionPane.ERROR_MESSAGE;
        public static final int INFORMATION = JOptionPane.INFORMATION_MESSAGE;

        public ShowMessage(String message, String title, int type) {
            this.message = message;
            this.title = title;
            this.type = type;
        }

        @Override
        public void run() {
            JOptionPane.showMessageDialog(null, message, title, type);
        }
    }


}
