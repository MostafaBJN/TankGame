package game;


import javax.swing.*;

public class GUIManager {
    //All GUIs
    private static LoginForm login;
    private static MainMenu mainMenu;

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
        login.showGUI();
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


//    public static void open(){
//         = new ("");
//        .showGUI();
//    }
//
//    public static void close(){
//        closeGUI();
//    }


}
