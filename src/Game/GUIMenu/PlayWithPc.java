package Game.GUIMenu;

import Game.Play.GameInfo;
import Service.Client.MainClient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Game.Play.GameInfo.*;

public class PlayWithPc extends GUIBase {

    private JButton playBtn;
    private JButton backBtn;

    private JTextField personLimit;
    private JTextField tankHealth;
    private JTextField bulletPower;
    private JTextField bWallHealth;
    private JTextField numberOfGame;

    private JCheckBox teamGame;
    private JCheckBox leaguePlay;

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


        personLimitLabel = new JLabel("Number Of Players :");
        personLimit = new JTextField();

        bulletPowerLabel = new JLabel("Bullet Power :");
        bulletPower = new JTextField(String.valueOf(MainClient.getLoggedPlayer().getDefaultBulletPower()));

        tankHealthLabel = new JLabel("Tank Health :");
        tankHealth = new JTextField(String.valueOf(MainClient.getLoggedPlayer().getDefaultTankHealth()));

        bWallHealthLabel = new JLabel("Breakable Wall Health :");
        bWallHealth = new JTextField(String.valueOf(MainClient.getLoggedPlayer().getDefaultBreakableWallHealth()));

        teamGame = new JCheckBox("Team Game", false);
        leaguePlay = new JCheckBox("League Game", false);
        leaguePlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfGameLabel.setVisible(leaguePlay.isSelected());
                numberOfGame.setVisible(leaguePlay.isSelected());
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
        infoPanel.add(leaguePlay);
        infoPanel.add(numberOfGameLabel);
        infoPanel.add(numberOfGame);


        playBtn = new JButton("PLAY");
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(leaguePlay.isSelected()) {
                    if (numberOfGame.getText().equals("")) {
                        JOptionPane.showMessageDialog(PlayWithPc.this, "NUMBER OF GAMES IS EMPTY!", "NEW GAME", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                if(personLimit.getText().equals("")){
                    JOptionPane.showMessageDialog(PlayWithPc.this,"PLAYER LIMIT IS EMPTY!","NEW GAME", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(tankHealth.getText().equals("")){
                    JOptionPane.showMessageDialog(PlayWithPc.this,"TANK HEALTH IS EMPTY!","NEW GAME", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(bulletPower.getText().equals("")) {
                    JOptionPane.showMessageDialog(PlayWithPc.this,"BULLET POWER IS EMPTY!","NEW GAME", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(bWallHealth.getText().equals("")){
                    JOptionPane.showMessageDialog(PlayWithPc.this,"BREAKABLE WALL HEALTH IS EMPTY!","NEW GAME", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ArrayList<Integer> errors = checkPCGameInfo(teamGame.isSelected(),
                        leaguePlay.isSelected(),
                        numberOfGame.getText(),
                        personLimit.getText(),
                        tankHealth.getText(),
                        bulletPower.getText(),
                        bWallHealth.getText()
                        );
                for (int error:errors) {
                    switch (error) {
                        case NUMBER_OF_PLAYERS_SHOULD_BE_EVEN -> JOptionPane.showMessageDialog(PlayWithPc.this,"NUMBER_OF_PLAYERS_SHOULD_BE_EVEN!", "NEW GAME", JOptionPane.ERROR_MESSAGE);
                        case NUMBER_OF_GAMES_IS_OUT_OF_RANGE -> JOptionPane.showMessageDialog(PlayWithPc.this,"NUMBER_OF_GAMES_IS_OUT_OF_RANGE!", "NEW GAME", JOptionPane.ERROR_MESSAGE);
                        case HEALTH_OF_TANK_IS_OUT_OF_RANGE -> JOptionPane.showMessageDialog(PlayWithPc.this,"HEALTH_OF_TANK_IS_OUT_OF_RANGE!", "NEW GAME", JOptionPane.ERROR_MESSAGE);
                        case POWER_OF_BULLET_IS_OUT_OF_RANGE -> JOptionPane.showMessageDialog(PlayWithPc.this,"POWER_OF_BULLET_IS_OUT_OF_RANGE!", "NEW GAME", JOptionPane.ERROR_MESSAGE);
                        case HEALTH_OF_B_WALL_IS_OUT_OF_RANGE -> JOptionPane.showMessageDialog(PlayWithPc.this,"HEALTH_OF_BREAKABLE_WALL_IS_OUT_OF_RANGE!", "NEW GAME", JOptionPane.ERROR_MESSAGE);
                        case WRONG_INPUT -> JOptionPane.showMessageDialog(PlayWithPc.this,"STRING VALUE IN INTEGER FIELDS!", "NEW GAME", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if(errors.size() > 0){
                    return;
                }

                if(leaguePlay.isSelected()) {
                    GameInfo gameInfo = new GameInfo(-1, "PLAY", true, true,
                            teamGame.isSelected(),
                            leaguePlay.isSelected(),
                            Integer.parseInt(numberOfGame.getText()),
                            Integer.parseInt(personLimit.getText()),
                            Integer.parseInt(personLimit.getText()) - 1,
                            Integer.parseInt(tankHealth.getText()),
                            Integer.parseInt(bulletPower.getText()),
                            Integer.parseInt(bWallHealth.getText()));
                }
                else {
                    GameInfo gameInfo = new GameInfo(-1, "PLAY", true, true,
                            teamGame.isSelected(),
                            leaguePlay.isSelected(),
                            1,
                            Integer.parseInt(personLimit.getText()),
                            Integer.parseInt(personLimit.getText()) - 1,
                            Integer.parseInt(tankHealth.getText()),
                            Integer.parseInt(bulletPower.getText()),
                            Integer.parseInt(bWallHealth.getText()));
                }

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
