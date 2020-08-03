package game;

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

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        LoginForm.ButtonHandler handler = new LoginForm.ButtonHandler();

        enterUsernameLabel = new JLabel("USERNAME :");
        usernameField = new JTextField(SAMPLE_ENTER_USERNAME_MASSAGE);

        usernameField.addActionListener(handler);
        usernameField.addFocusListener(listener);

        enterPasswordLabel = new JLabel("PASSWORD :");
        passwordField = new JPasswordField(SAMPLE_ENTER_PASSWORD_MASSAGE);

        passwordField.addActionListener(handler);
        passwordField.addFocusListener(listener);

        saveLoggedRBtn = new JRadioButton("Keep Me Logged");
        saveLoggedRBtn.setSelected(false);

        infoPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        infoPanel.add(enterUsernameLabel);
        infoPanel.add(usernameField);
        infoPanel.add(enterPasswordLabel);
        infoPanel.add(passwordField);
        infoPanel.add(saveLoggedRBtn);

        loginButton = new JButton("Login");
        loginButton.addActionListener(handler);


        int buttonWidth = loginButton.getPreferredSize().width;
        int buttonHeight = loginButton.getPreferredSize().height + 10;
        loginButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(handler);



        buttonWidth = signUpButton.getPreferredSize().width;
        buttonHeight = signUpButton.getPreferredSize().height + 10;
        loginButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        buttonPanel = new JPanel(new GridLayout(2, 0, 5, 5));
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);


        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
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
                //TODO LOG OUT
                //TODO disable keep login
                GUIManager.closeMainMenu();
                GUIManager.openLogin();
            }
        }
    }
}
