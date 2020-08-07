package Game.GUIMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayWithPc extends GUIBase {

    private JButton playBtn;
    private JButton backBtn;

    private JTextField personLimit;
    private JTextField tankHealth;
    private JTextField bulletPower;
    private JTextField bWallHealth;
    private JTextField numberOfGame;

    private JCheckBox teamGame;
    private JCheckBox finishGame;

    private JPanel infoPanel;
    private JPanel mainPanel;
    private JPanel buttonPanel;


    private JLabel personLimitLabel;
    private JLabel tankHealthLabel;
    private JLabel bulletPowerLabel;
    private JLabel bWallHealthLabel;
    private JLabel numberOfGameLabel;

    protected PlayWithPc(String title) {
        super(title);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));


        personLimitLabel = new JLabel("Player Limit :");
        personLimit = new JTextField();

        bulletPowerLabel = new JLabel("Bullet Power :");
        bulletPower = new JTextField();

        tankHealthLabel = new JLabel("Tank Health :");
        tankHealth = new JTextField();

        bWallHealthLabel = new JLabel("Breakable Wall Health :");
        bWallHealth = new JTextField();

        teamGame = new JCheckBox("Team Game", false);
        finishGame = new JCheckBox("League Game", false);
        finishGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfGameLabel.setVisible(finishGame.isSelected());
                numberOfGame.setVisible(finishGame.isSelected());
            }
        });

        numberOfGameLabel = new JLabel("Number Of Games :");
        numberOfGame = new JTextField();
        numberOfGameLabel.setVisible(false);
        numberOfGame.setVisible(false);

        infoPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        infoPanel.add(personLimitLabel);
        infoPanel.add(personLimit);
        infoPanel.add(bulletPowerLabel);
        infoPanel.add(bulletPower);
        infoPanel.add(tankHealthLabel);
        infoPanel.add(tankHealth);
        infoPanel.add(bWallHealthLabel);
        infoPanel.add(bWallHealth);
        infoPanel.add(teamGame);
        infoPanel.add(finishGame);
        infoPanel.add(numberOfGameLabel);
        infoPanel.add(numberOfGame);


        playBtn = new JButton("PLAY");
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personLimit.getText();
                tankHealth.getText();
                bulletPower.getText();
                bWallHealth.getText();
                numberOfGame.getText();

                teamGame.isSelected();
                finishGame.isSelected();
                //TODO Play Game
            }
        });

        int buttonWidth = playBtn.getPreferredSize().width;
        int buttonHeight = playBtn.getPreferredSize().height + 10;
        playBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        backBtn = new JButton("BACK");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIManager.closePlayWithPc();
                GUIManager.openMainMenu();
            }
        });


        buttonWidth = backBtn.getPreferredSize().width;
        buttonHeight = backBtn.getPreferredSize().height + 10;
        backBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));


        buttonPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        buttonPanel.add(playBtn);
        buttonPanel.add(backBtn);

        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
    }
}
