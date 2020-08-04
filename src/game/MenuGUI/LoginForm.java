package game.MenuGUI;

import Service.Client.ClientMain;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LoginForm extends GUIBase {

    private JButton loginButton;
    private JButton signUpButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JRadioButton saveLoggedRBtn;

    private JPanel mainPanel;
    private JPanel infoPanel;
    private JPanel buttonPanel;

    private JLabel titleLabel;
    private JLabel enterUsernameLabel;
    private JLabel enterPasswordLabel;


    private static final String SAMPLE_ENTER_USERNAME_MASSAGE = "username";
    private static final String SAMPLE_ENTER_PASSWORD_MASSAGE = "********";

    private static final String TITLE = "Login/Sign Up";

    public LoginForm() {
        super(TITLE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        titleLabel = new JLabel(" Please Enter Your Information ");
        Color c = new Color(245,186,19);
        titleLabel.setBackground(c);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setOpaque(true);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        titleLabel.setBorder(border);

        int labelWidth = titleLabel.getPreferredSize().width;
        int labelHeight = titleLabel.getPreferredSize().height + 10;
        titleLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));

        ButtonHandler handler = new ButtonHandler();
        EnterTextFocusListener listener = new EnterTextFocusListener();

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
        pack();
    }

    /**
     * changer of showing samples in text fields
     */
    protected class EnterTextFocusListener extends FocusAdapter {


        @Override
        public void focusGained(FocusEvent e) {
            super.focusGained(e);
            if (e.getSource().equals(usernameField)) {
                if (usernameField.getText().equals(SAMPLE_ENTER_USERNAME_MASSAGE)) {
                    usernameField.setText("");
                }
            }
            else if (e.getSource().equals(passwordField)) {
                if (passwordField.getText().equals(SAMPLE_ENTER_PASSWORD_MASSAGE)) {
                    passwordField.setText("");
                }
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            super.focusLost(e);
            if (e.getSource().equals(usernameField)) {
                if(usernameField.getText().equals("")){
                    usernameField.setText(SAMPLE_ENTER_USERNAME_MASSAGE);
                }
            }
            else if (e.getSource().equals(passwordField)) {
                if(new String(passwordField.getPassword()).equals("")){
                    passwordField.setText(SAMPLE_ENTER_PASSWORD_MASSAGE);
                }
            }
        }

    }

    private class ButtonHandler implements ActionListener {

    	@Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            boolean keepLogin = saveLoggedRBtn.isSelected();
            //TODO SEARCH IN USER
            if (e.getSource().equals(loginButton) || e.getSource().equals(usernameField) || e.getSource().equals(passwordField)) {
                int result = ClientMain.ERROR;
                try {
                    result = ClientMain.login(username, password, keepLogin);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(LoginForm.this, "ERROR!", "Login", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                switch (result){
                    case ClientMain.SUCCESSFUL:
                        JOptionPane.showMessageDialog(LoginForm.this, "Logged Successfully!", "Login", JOptionPane.INFORMATION_MESSAGE);
                        try {
                            if(ClientMain.loggingIn(username, password, keepLogin)) {
                                GUIManager.closeLogin();
                                GUIManager.openMainMenu();
                            }
                        } catch (IOException exception) {
                            JOptionPane.showMessageDialog(LoginForm.this, "ERROR!", "Login", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case ClientMain.NO_USERNAME:
                        JOptionPane.showMessageDialog(LoginForm.this, "You have'nt Sign Up yet!", "Login", JOptionPane.ERROR_MESSAGE);
                        break;
                    case ClientMain.WRONG_PASSWORD:
                        JOptionPane.showMessageDialog(LoginForm.this, "Password is Wrong!", "Login", JOptionPane.ERROR_MESSAGE);
                        break;
                    default:
                        JOptionPane.showMessageDialog(LoginForm.this, "ERROR!", "Login", JOptionPane.ERROR_MESSAGE);
                }
            } else if(e.getSource().equals(signUpButton)) {
                //TODO ADD THIS USER
                try {
                    ClientMain.signUp(username, password);
                    JOptionPane.showMessageDialog(LoginForm.this, "Sign Up Successfully!", "Sign Up", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(LoginForm.this, "ERROR!", "Sign Up", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}
