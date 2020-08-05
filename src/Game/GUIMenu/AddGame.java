package Game.GUIMenu;

import Game.Run.Preview;
import Service.Client.MainClient;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGame extends GUIBase{

    private JButton addBtn;
    private JButton cancelBtn;

    private JTextField name;
    private JTextField personLimit;
    private JTextField tankHealth;
    private JTextField bulletPower;
    private JTextField bWallHealth;

    private JCheckBox teamGame;
    private JCheckBox finishGame;

    private JPanel infoPanel;
    private JPanel mainPanel;
    private JPanel buttonPanel;


    private JLabel nameLabel;
    private JLabel personLimitLabel;
    private JLabel tankHealthLabel;
    private JLabel bulletPowerLabel;
    private JLabel bWallHealthLabel;

    protected AddGame(String title) {
        super(title);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));


        nameLabel = new JLabel("Name :");
        name = new JTextField();

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

        infoPanel = new JPanel(new GridLayout(6, 2, 5, 5));
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
        infoPanel.add(finishGame);





        addBtn = new JButton("ADD");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GET DATAS




                //Preview preview = new Preview();
                //MainClient.addGame(preview);
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
