package Game.GUIMenu;

import Service.Client.MainClient;
import Service.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Setting extends GUIBase {


    private JButton backBtn;

    private JButton changeTankBtn;

    private JTextField playedTime;
    private JTextField username;
    private JTextField onlineWins;
    private JTextField offlineWins;
    private JTextField onlineLoses;
    private JTextField offlineLoses;
    private JTextField tankHealth;
    private JTextField bulletPower;
    private JTextField bWallHealth;

    private JCheckBox teamGame;
    private JCheckBox finishGame;

    private JPanel infoPanel;
    private JPanel mainPanel;
    private JPanel buttonPanel;


    private JLabel playedTimeLabel;
    private JLabel usernameLabel;
    private JLabel tankHealthLabel;
    private JLabel bulletPowerLabel;
    private JLabel bWallHealthLabel;
    private JLabel onlineWinsLabel;
    private JLabel offlineWinsLabel;
    private JLabel onlineLosesLabel;
    private JLabel offlineLosesLabel;
    private JLabel tankImage;
    private JLabel tankImageLabel;
    private JLabel changeTankLabel;

    protected Setting(String title, Player player) {
        super(title);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));


        playedTimeLabel = new JLabel("Played Time :");
        playedTime = new JTextField(String.valueOf(player.getTotalPlayedTime()));
        playedTime.setEditable(false);

        usernameLabel = new JLabel("Username :");
        username = new JTextField(player.getUsername());
        username.setEditable(false);

        offlineLosesLabel = new JLabel("offline Loses :");
        offlineLoses = new JTextField(String.valueOf(player.getNumberOfLosesOffline()));
        offlineLoses.setEditable(false);

        onlineLosesLabel = new JLabel("online Loses :");
        onlineLoses = new JTextField(String.valueOf(player.getNumberOfLosesOnline()));
        onlineLoses.setEditable(false);

        offlineWinsLabel = new JLabel("offline Wins :");
        offlineWins = new JTextField(String.valueOf(player.getNumberOfWinsOffline()));
        offlineWins.setEditable(false);

        onlineWinsLabel = new JLabel("online Wins :");
        onlineWins = new JTextField(String.valueOf(player.getNumberOfWinsOnline()));
        onlineWins.setEditable(false);

        bulletPowerLabel = new JLabel("Bullet Power :");
        bulletPower = new JTextField(String.valueOf(player.getDefaultBulletPower()));


        tankHealthLabel = new JLabel("Tank Health :");
        tankHealth = new JTextField(String.valueOf(player.getDefaultTankHealth()));


        bWallHealthLabel = new JLabel("Breakable Wall Health :");
        bWallHealth = new JTextField(String.valueOf(player.getDefaultBreakableWallHealth()));


        tankImageLabel = new JLabel("Your Tank :");
        player.getTank().styleFinder();
        tankImage = new JLabel((Icon) player.getTank().getStyleImage());

        changeTankLabel = new JLabel("Change Tank Model");
        changeTankBtn = new JButton("Tank Model");


        infoPanel = new JPanel(new GridLayout(9, 2, 5, 5));
        infoPanel.add(playedTimeLabel);
        infoPanel.add(playedTime);
        infoPanel.add(usernameLabel);
        infoPanel.add(username);
        infoPanel.add(onlineWinsLabel);
        infoPanel.add(onlineWins);
        infoPanel.add(onlineLosesLabel);
        infoPanel.add(onlineLoses);
        infoPanel.add(offlineWinsLabel);
        infoPanel.add(offlineWins);
        infoPanel.add(offlineLosesLabel);
        infoPanel.add(offlineLoses);
        infoPanel.add(bulletPowerLabel);
        infoPanel.add(bulletPower);
        infoPanel.add(tankHealthLabel);
        infoPanel.add(tankHealth);
        infoPanel.add(bWallHealthLabel);
        infoPanel.add(bWallHealth);



        backBtn = new JButton("BACK");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainClient.getLoggedPlayer().setDefaultBulletPower(Integer.parseInt(bulletPower.getText()));
                MainClient.getLoggedPlayer().setDefaultTankHealth(Integer.parseInt(tankHealth.getText()));
                MainClient.getLoggedPlayer().setDefaultBreakableWallHealth(Integer.parseInt(bWallHealth.getText()));
                
                GUIManager.closeSetting();
                GUIManager.openMainMenu();
            }
        });


        int buttonWidth = backBtn.getPreferredSize().width;
        int buttonHeight = backBtn.getPreferredSize().height + 10;
        backBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));


        buttonPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        buttonPanel.add(backBtn);

        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
    }

}
