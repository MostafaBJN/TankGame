package Game.GUIMenu;

import Game.Play.GameInfo;
import Game.Run.RunGameServer;
import Service.Client.MainClient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static Game.Play.GameInfo.*;

public class AddGame extends GUIBase{

    private JButton addBtn;
    private JButton cancelBtn;

    private JTextField name;
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


    private JLabel nameLabel;
    private JLabel personLimitLabel;
    private JLabel tankHealthLabel;
    private JLabel bulletPowerLabel;
    private JLabel bWallHealthLabel;
    private JLabel numberOfGameLabel;

    protected AddGame(String title) {
        super(title);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));


        nameLabel = new JLabel("Name :");
        name = new JTextField();

        personLimitLabel = new JLabel("Number Of Players :");
        personLimit = new JTextField();

        bulletPowerLabel = new JLabel("Bullet Power :");
        bulletPower = new JTextField();

        tankHealthLabel = new JLabel("Tank Health :");
        tankHealth = new JTextField();

        bWallHealthLabel = new JLabel("Breakable Wall Health :");
        bWallHealth = new JTextField();


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

        infoPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        infoPanel.add(nameLabel);
        infoPanel.add(name);
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


        addBtn = new JButton("ADD");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GET DATA

                if(leaguePlay.isSelected()) {
                    if (numberOfGame.getText().equals("")) {
                        JOptionPane.showMessageDialog(AddGame.this, "NUMBER OF GAMES IS EMPTY!", "NEW GAME", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                if(personLimit.getText().equals("")){
                    JOptionPane.showMessageDialog(AddGame.this,"PLAYER LIMIT IS EMPTY!","NEW GAME", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(tankHealth.getText().equals("")){
                    JOptionPane.showMessageDialog(AddGame.this,"TANK HEALTH IS EMPTY!","NEW GAME", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(bulletPower.getText().equals("")) {
                    JOptionPane.showMessageDialog(AddGame.this,"BULLET POWER IS EMPTY!","NEW GAME", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(bWallHealth.getText().equals("")){
                    JOptionPane.showMessageDialog(AddGame.this,"BREAKABLE WALL HEALTH IS EMPTY!","NEW GAME", JOptionPane.ERROR_MESSAGE);
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
                        case NUMBER_OF_PLAYERS_SHOULD_BE_EVEN -> JOptionPane.showMessageDialog(AddGame.this,"NUMBER_OF_PLAYERS_SHOULD_BE_EVEN!", "NEW GAME", JOptionPane.ERROR_MESSAGE);
                        case NUMBER_OF_GAMES_IS_OUT_OF_RANGE -> JOptionPane.showMessageDialog(AddGame.this,"NUMBER_OF_GAMES_IS_OUT_OF_RANGE!", "NEW GAME", JOptionPane.ERROR_MESSAGE);
                        case HEALTH_OF_TANK_IS_OUT_OF_RANGE -> JOptionPane.showMessageDialog(AddGame.this,"HEALTH_OF_TANK_IS_OUT_OF_RANGE!", "NEW GAME", JOptionPane.ERROR_MESSAGE);
                        case POWER_OF_BULLET_IS_OUT_OF_RANGE -> JOptionPane.showMessageDialog(AddGame.this,"POWER_OF_BULLET_IS_OUT_OF_RANGE!", "NEW GAME", JOptionPane.ERROR_MESSAGE);
                        case HEALTH_OF_B_WALL_IS_OUT_OF_RANGE -> JOptionPane.showMessageDialog(AddGame.this,"HEALTH_OF_BREAKABLE_WALL_IS_OUT_OF_RANGE!", "NEW GAME", JOptionPane.ERROR_MESSAGE);
                        case WRONG_INPUT -> JOptionPane.showMessageDialog(AddGame.this,"STRING VALUE IN INTEGER FIELDS!", "NEW GAME", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if(errors.size() > 0){
                    return;
                }

                String nameOfGame = name.getText();
                if(name.getText().equals("")){
                    nameOfGame = String.valueOf(System.currentTimeMillis());
                }
                GameInfo gameInfo;

                if(leaguePlay.isSelected()) {
                    gameInfo = new GameInfo(GameInfo.selectRandomPort(), name.getText(), false, false,
                            teamGame.isSelected(),
                            leaguePlay.isSelected(),
                            Integer.parseInt(numberOfGame.getText()),
                            Integer.parseInt(personLimit.getText()),
                            0,
                            Integer.parseInt(tankHealth.getText()),
                            Integer.parseInt(bulletPower.getText()),
                            Integer.parseInt(bWallHealth.getText()));
                }
                else {
                    gameInfo = new GameInfo(GameInfo.selectRandomPort(), name.getText(), false, false,
                            teamGame.isSelected(),
                            leaguePlay.isSelected(),
                            1,
                            Integer.parseInt(personLimit.getText()),
                            0,
                            Integer.parseInt(tankHealth.getText()),
                            Integer.parseInt(bulletPower.getText()),
                            Integer.parseInt(bWallHealth.getText()));
                }


                try {
                    MainClient.addGame(gameInfo);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

                //TODO RUN GAME
                try {
                    RunGameServer gameServer = new RunGameServer(gameInfo);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        int buttonWidth = addBtn.getPreferredSize().width;
        int buttonHeight = addBtn.getPreferredSize().height + 10;
        addBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        cancelBtn = new JButton("BACK");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIManager.closeAddGame();
                GUIManager.openMainMenu();
            }
        });


        buttonWidth = cancelBtn.getPreferredSize().width;
        buttonHeight = cancelBtn.getPreferredSize().height + 10;
        cancelBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));


        buttonPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        buttonPanel.add(addBtn);
        buttonPanel.add(cancelBtn);

        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
    }

}
