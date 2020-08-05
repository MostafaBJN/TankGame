package Game.GUIMenu;

import Service.Client.MainClient;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends GUIBase {

    private JButton settingBtn;
    private JButton playWithPcBtn;
    private JButton playMultiPlayerBtn;
    private JButton logOutBtn;

    private JPanel mainPanel;

    private JLabel titleLabel;

    private JPanel buttonsPanel;


    public MainMenu(String title) {
        super(title);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border emptyBorder = new EmptyBorder(5, 5, 5, 5);

        mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(border);

        titleLabel = new JLabel("MAIN MENU");
        Color c = new Color(100, 160, 6);
        titleLabel.setBackground(c);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setBorder(border);
        int labelWidth = titleLabel.getPreferredSize().width;
        int labelHeight = titleLabel.getPreferredSize().height + 10;
        titleLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));

        ActionLsnr handler = new ActionLsnr();
//
//        enterUsernameLabel = new JLabel("USERNAME :");
//        usernameField = new JTextField(SAMPLE_ENTER_USERNAME_MASSAGE);
//
//        usernameField.addActionListener(handler);
//        usernameField.addFocusListener(listener);
//
//        enterPasswordLabel = new JLabel("PASSWORD :");
//        passwordField = new JPasswordField(SAMPLE_ENTER_PASSWORD_MASSAGE);
//
//        passwordField.addActionListener(handler);
//        passwordField.addFocusListener(listener);
//
//        saveLoggedRBtn = new JRadioButton("Keep Me Logged");
//        saveLoggedRBtn.setSelected(false);
//
//        infoPanel = new JPanel(new GridLayout(3, 2, 5, 5));
//        infoPanel.add(enterUsernameLabel);
//        infoPanel.add(usernameField);
//        infoPanel.add(enterPasswordLabel);
//        infoPanel.add(passwordField);
//        infoPanel.add(saveLoggedRBtn);

        settingBtn = new JButton("Setting");
        settingBtn.addActionListener(handler);
        int buttonWidth = settingBtn.getPreferredSize().width;
        int buttonHeight = settingBtn.getPreferredSize().height + 10;
        settingBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        playMultiPlayerBtn = new JButton("MultiPlayer");
        playMultiPlayerBtn.addActionListener(handler);
        buttonWidth = playMultiPlayerBtn.getPreferredSize().width;
        buttonHeight = playMultiPlayerBtn.getPreferredSize().height + 10;
        playMultiPlayerBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        playWithPcBtn = new JButton("Play with AI");
        playWithPcBtn.addActionListener(handler);
        buttonWidth = playWithPcBtn.getPreferredSize().width;
        buttonHeight = playWithPcBtn.getPreferredSize().height + 10;
        playWithPcBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        logOutBtn = new JButton("Log Out");
        logOutBtn.addActionListener(handler);
        buttonWidth = logOutBtn.getPreferredSize().width;
        buttonHeight = logOutBtn.getPreferredSize().height + 10;
        logOutBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        buttonsPanel = new JPanel(new GridLayout(4, 0, 5, 5));
        buttonsPanel.add(playMultiPlayerBtn);
        buttonsPanel.add(playWithPcBtn);
        buttonsPanel.add(settingBtn);
        buttonsPanel.add(logOutBtn);


//        buttonWidth = signUpButton.getPreferredSize().width;
//        buttonHeight = signUpButton.getPreferredSize().height + 10;
//        loginButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        mainPanel.add(buttonsPanel, BorderLayout.CENTER);


//        mainPanel.add(titleLabel, BorderLayout.NORTH);
//        mainPanel.add(infoPanel, BorderLayout.CENTER);
//        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
    }

    class ActionLsnr implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(settingBtn)){
                //TODO OPEN SETTING OPTIONS
            }
            if(e.getSource().equals(playWithPcBtn)){
                //TODO PC PLAYING
            }
            if(e.getSource().equals(playMultiPlayerBtn)){
                //TODO MULTI PLAYER SERVER MAKING
            }
            if(e.getSource().equals(logOutBtn)){
                MainClient.logout();
                GUIManager.closeMainMenu();
                GUIManager.openLogin();
            }
        }
    }
}
