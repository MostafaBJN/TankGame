package Game.GUIMenu;

import Game.Run.Preview;
import Map.Ground;
import Map.Map;
import Service.Client.MainClient;

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

        setSize(400,700);
        setLayout(null);
        JTabbedPane tab = new JTabbedPane();
        for (Preview game : allGames) {

            JLabel nameOfGame = new JLabel("nameOfGame=" + game.getNameOfGame());
            nameOfGame.setBounds(10,10,200,30);

            JLabel groupingOr1By1 = new JLabel("groupingOr1By1=" + game.getGroupingOr1By1());
            groupingOr1By1.setBounds(10,50,200,30);

            JLabel playingWithComputerOrPerson = new JLabel("playingWithComputerOrPerson=" + game.getPlayingWithComputerOrPerson());
            playingWithComputerOrPerson.setBounds(10,90,200,30);

            JLabel countOfLeague = new JLabel("countOfLeague=" + game.getCountOfLeague());
            countOfLeague.setBounds(10,130,200,30);

            JLabel livesOfTank = new JLabel("livesOfTank=" + game.getLivesOfTank());
            livesOfTank.setBounds(10,170,200,30);

            JLabel livesOfWall = new JLabel("livesOfWall" + game.getLivesOfWall());
            livesOfWall.setBounds(10,210,200,30);

            JLabel powerOfBullet = new JLabel("powerOfBullet=" + game.getPowerOfBullet());
            powerOfBullet.setBounds(10,250,200,30);

            JLabel mapTemple = new JLabel();//TODO:empty Label
            mapTemple.setBounds(10,290,game.getMap().getVisualWidth()/5,game.getMap().getVisualHeight()/5);
            mapTemple.setIcon(resize(game.getMap(), game.getMap().getVisualWidth()/5,game.getMap().getVisualHeight()/5));


            JButton exitBtn = new JButton("Connect");
            exitBtn.setBounds(10,330 + game.getMap().getVisualHeight()/5 + 10,80,50);
            exitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO Run Game
                }
            });
            add(exitBtn);

            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.add(nameOfGame);
            panel.add(groupingOr1By1);
            panel.add(playingWithComputerOrPerson);
            panel.add(countOfLeague);
            panel.add(livesOfTank);
            panel.add(livesOfWall);
            panel.add(powerOfBullet);
            panel.add(mapTemple);
            tab.setBounds(0, 0, 800,600);
            tab.add(game.getNameOfGame(), panel);

        }
        add(tab);

        JButton exitBtn = new JButton("Menu");
        exitBtn.setBounds(10,600,80,50);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIManager.closePlayMultiPlayerMenu();
                GUIManager.openMainMenu();
            }
        });
        add(exitBtn);

        JButton AddBtn = new JButton("addGame");
        exitBtn.setBounds(100,600,80,50);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Add game
                MainClient
            }
        });
        add(exitBtn);

    }


//    resizing a picture
    public static ImageIcon resize(Map map, int scaledWidth, int scaledHeight){
        // reads input image
        // creates output image

        BufferedImage img = new BufferedImage(map.getVisualWidth(), map.getVisualHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) img.getGraphics();

        for(ArrayList<Ground> listGround:map.getGrounds()){
            for(Ground ground:listGround){
                g2d.drawImage(ground.getStyleImage(), ground.getStartHorizontalVisualPointInMap()+6, ground.getStartVerticalVisualPointInMap()+31,null);
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
