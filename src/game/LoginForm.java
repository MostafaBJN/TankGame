package game;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
    }

    public void showGUI() {
    	pack();
        setVisible(true);
    }

    public void closeGUI() {
        this.dispose();
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
            boolean saveLogged = saveLoggedRBtn.isSelected();
            //TODO SEARCH IN USER
            if (e.getSource().equals(loginButton) || e.getSource().equals(usernameField) || e.getSource().equals(passwordField)) {
                if (username.equals("0")) {
                    if(password.equals("0")){
                        JOptionPane.showMessageDialog(LoginForm.this, "Logged Successfully!", "Login", JOptionPane.INFORMATION_MESSAGE);
                        //TODO close this And Open MAinMEnu
                    }
                    else {
                        JOptionPane.showMessageDialog(LoginForm.this, "Password is Wrong!", "Login", JOptionPane.ERROR_MESSAGE);
                        //noting
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "You have'nt Sign Up yet!", "Login", JOptionPane.ERROR_MESSAGE);
                    //noting
                }
            } else if(e.getSource().equals(signUpButton)) {
                //TODO ADD THIS USER
                JOptionPane.showMessageDialog(LoginForm.this, "Sign Up Successfully!", "Sign Up", JOptionPane.INFORMATION_MESSAGE);
                //noting
            }
        }
    }

}
