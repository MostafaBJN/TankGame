package Game.GUIMenu;

import Game.Run.Preview;
import Thing.Map.Ground;
import Thing.Map.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayMultiPlayerMenu extends GUIBase
{
    public PlayMultiPlayerMenu()
    {
        super("Games");
        ArrayList<Preview> allGame ;//TODO GET LIST OF GAMES
        ArrayList<Preview> allGames = new ArrayList<>();
        allGames.add(new Preview("Hello","1","2","3","4","5","6"));
        allGames.add(new Preview("grbg","44","55","66","78","--","vgfe"));
        allGames.add(new Preview("fff","46hh","2d","6u","5y3","g53","43f"));


        JTabbedPane tab = new JTabbedPane();
        for (Preview game : allGames) {

            JLabel nameOfGame = new JLabel("Name : " + game.getNameOfGame());

            JLabel groupingOr1By1 = new JLabel("Team Play : " + game.getGroupingOr1By1());

            JLabel playingWithComputerOrPerson = new JLabel("Robots Allowed : " + game.getPlayingWithComputerOrPerson());

            JLabel minOfPlayer = new JLabel("Player Number : " );

            JLabel joinedPlayer = new JLabel("Joined Players : " );

            JLabel countOfLeague = new JLabel("Number Of Rounds : " + game.getCountOfLeague());

            JLabel livesOfTank = new JLabel("Tank Health : " + game.getLivesOfTank());


            JLabel livesOfWall = new JLabel("Breakable Wall Health : " + game.getLivesOfWall());


            JLabel powerOfBullet = new JLabel("Bullet Power : " + game.getPowerOfBullet());


            JLabel mapTemple = new JLabel(resize(game.getMap(), game.getMap().getVisualWidth()/4,game.getMap().getVisualHeight()/4));//TODO:empty Label
            mapTemple.setSize(game.getMap().getVisualWidth()/4,game.getMap().getVisualHeight()/4);

            JButton connectBtn = new JButton("Connect");
            int buttonWidth = connectBtn.getPreferredSize().width;
            int buttonHeight = connectBtn.getPreferredSize().height + 10;
            connectBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            connectBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO Run Game
                }
            });

            JPanel list = new JPanel(new GridLayout(9,0,10,10));
            list.add(nameOfGame);
            list.add(groupingOr1By1);
            list.add(minOfPlayer);
            list.add(joinedPlayer);
            list.add(playingWithComputerOrPerson);
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

            tab.add(game.getNameOfGame(), panel);
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


//    resizing a picture
    public static ImageIcon resize(Map map, int scaledWidth, int scaledHeight){
        // reads input image
        // creates output image

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
