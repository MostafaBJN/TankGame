package Game.GUIMenu;

import Game.Play.GameInfo;
import Game.Run.RunGameClient;
import Service.Client.MainClient;
import Thing.Map.Ground;
import Thing.Map.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class PlayMultiPlayerMenu extends GUIBase
{
    public PlayMultiPlayerMenu() {
        super("Games");

        ArrayList<GameInfo> allGames = null;
        try {
            allGames = MainClient.playMultiPlayer();
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
            new Thread(new GUIManager.ShowMessage("GETTING LIST OF SERVERS ERROR!", "PLAY MULTIPLAYER", GUIManager.ShowMessage.ERROR)).start();
            GUIManager.openMainMenu();
        }


        JTabbedPane tab = new JTabbedPane();
        for (GameInfo gameInfo : allGames) {

            JLabel nameOfGame = new JLabel("Name : " + gameInfo.getName());

            JLabel teamPlay = new JLabel("Team Play : " + gameInfo.isTeamGame());

            JLabel numberOfPlayers = new JLabel("Number Of Players : " + gameInfo.getNumberOfPlayers());

            JLabel leagueGame = new JLabel("League Game : " + gameInfo.isLeaguePlay());

            JLabel countOfLeague = new JLabel("Number Of Rounds : " + gameInfo.getNumberOfGames());

            JLabel joinedPlayer = new JLabel("Joined Players : " + (gameInfo.getNumberOfPlayers() - gameInfo.getPlayerNeedToJoin()));

            JLabel livesOfTank = new JLabel("Tank Health : " + gameInfo.getTankHealth());

            JLabel livesOfWall = new JLabel("Breakable Wall Health : " + gameInfo.getBWallHealth());

            JLabel powerOfBullet = new JLabel("Bullet Power : " + gameInfo.getBulletPower());

            JLabel mapTemple = new JLabel(resize(gameInfo.getMap(), gameInfo.getMap().getVisualWidth()/4, gameInfo.getMap().getVisualHeight()/4));//TODO:empty Label
            mapTemple.setSize(gameInfo.getMap().getVisualWidth()/4, gameInfo.getMap().getVisualHeight()/4);

            JButton connectBtn = new JButton("Connect");
            int buttonWidth = connectBtn.getPreferredSize().width;
            int buttonHeight = connectBtn.getPreferredSize().height + 10;
            connectBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            connectBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO Run Game
                    if (!RunGameClient.connectToGame(gameInfo.getPort())) {
                        GUIManager.openMainMenu();
                        GUIManager.closePlayMultiPlayerMenu();
                    }
                    else {
                        new RunGameClient(gameInfo);
                    }
                }
            });

            JPanel list = new JPanel(new GridLayout(9,0,10,10));
            list.add(nameOfGame);
            list.add(teamPlay);
            list.add(numberOfPlayers);
            list.add(joinedPlayer);
            list.add(leagueGame);
            list.add(countOfLeague);
            list.add(livesOfTank);
            list.add(livesOfWall);
            list.add(powerOfBullet);

            JPanel picture = new JPanel(new BorderLayout(10,10));
            JPanel button = new JPanel(new BorderLayout(10,10));
            picture.add(mapTemple, BorderLayout.CENTER);
            button.add(connectBtn, BorderLayout.CENTER);

            JPanel panel = new JPanel(new BorderLayout(10,10));
            panel.add(picture, BorderLayout.NORTH);
            panel.add(list, BorderLayout.CENTER);
            panel.add(button, BorderLayout.SOUTH);

            tab.add(gameInfo.getName(), panel);
        }


        JButton exitBtn = new JButton("Menu");
        int buttonWidth = exitBtn.getPreferredSize().width;
        int buttonHeight = exitBtn.getPreferredSize().height + 10;
        exitBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIManager.closePlayMultiPlayerMenu();
                GUIManager.openMainMenu();
            }
        });

        JButton AddBtn = new JButton("Add Game");
        buttonWidth = AddBtn.getPreferredSize().width;
        buttonHeight = AddBtn.getPreferredSize().height + 10;
        AddBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        AddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIManager.openAddGame();
                GUIManager.closePlayMultiPlayerMenu();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(0,2,5,5));
        buttonPanel.add(AddBtn);
        buttonPanel.add(exitBtn);


        JPanel mainPanel = new JPanel(new BorderLayout(5,5));
        //mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPanel.add(tab, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        pack();
    }


    /**
     * resizing a Map to given scale
     */
    public static ImageIcon resize(Map map, int scaledWidth, int scaledHeight){

        BufferedImage img = new BufferedImage(map.getVisualWidth(), map.getVisualHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) img.getGraphics();

        for(ArrayList<Ground> listGround:map.getGrounds()){
            for(Ground ground:listGround){
                g2d.drawImage(ground.getStyleImage(), ground.getStartHorizontalVisualPointInMap(), ground.getStartVerticalVisualPointInMap(),null);
            }
        }
        g2d.dispose();

        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);

        // scales the input image to the output image
        Graphics2D g2D = outputImage.createGraphics();
        g2D.drawImage(img, 0, 0, scaledWidth, scaledHeight, null);
        g2D.dispose();
        return new ImageIcon(outputImage);
    }
}
