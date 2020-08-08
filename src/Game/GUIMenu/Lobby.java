package Game.GUIMenu;

import Game.Play.GameInfo;
import Game.Run.RunGameClient;
import Service.Command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Lobby extends GUIBase{

    JLabel joinedPlayer;

    public Lobby(String title, GameInfo gameInfo) {
        super(title);
        new Thread(new RunGameClient(gameInfo)).start();

        JLabel nameOfGame = new JLabel("Name : " + gameInfo.getName());

        JLabel teamPlay = new JLabel("Team Play : " + gameInfo.isTeamGame());

        JLabel numberOfPlayers = new JLabel("Number Of Players : " + gameInfo.getNumberOfPlayers());

        JLabel leagueGame = new JLabel("League Game : " + gameInfo.isLeaguePlay());

        JLabel countOfLeague = new JLabel("Number Of Rounds : " + gameInfo.getNumberOfGames());

        joinedPlayer = new JLabel("Joined Players : " + (gameInfo.getNumberOfPlayers() - gameInfo.getPlayerNeedToJoin()));

        JLabel livesOfTank = new JLabel("Tank Health : " + gameInfo.getTankHealth());

        JLabel livesOfWall = new JLabel("Breakable Wall Health : " + gameInfo.getBWallHealth());

        JLabel powerOfBullet = new JLabel("Bullet Power : " + gameInfo.getBulletPower());

        JLabel mapTemple = new JLabel(PlayMultiPlayerMenu.resize(gameInfo.getMap(), gameInfo.getMap().getVisualWidth()/4, gameInfo.getMap().getVisualHeight()/4));//TODO:empty Label
        mapTemple.setSize(gameInfo.getMap().getVisualWidth()/4, gameInfo.getMap().getVisualHeight()/4);

        JButton refreshBtn = new JButton("Refresh");
        int buttonWidth = refreshBtn.getPreferredSize().width;
        int buttonHeight = refreshBtn.getPreferredSize().height + 10;
        refreshBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Run Game
                try {
                    RunGameClient.outputStream.writeInt(Command.PlayGame.SEND_PLAYER_INFO_TO_GAME_SERVER);
                    RunGameClient.outputStream.flush();
                    GameInfo newGameInfo = (GameInfo) RunGameClient.inputStream.readObject();
                    RunGameClient.setGameInfo(newGameInfo);
                    GUIManager.getLobby().getJoinedPlayer().setText("Joined Players : " + (gameInfo.getNumberOfPlayers() - gameInfo.getPlayerNeedToJoin()));
                } catch (IOException | ClassNotFoundException exception) {
                    exception.printStackTrace();
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
        button.add(refreshBtn, BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.add(picture, BorderLayout.NORTH);
        panel.add(list, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);

        add(panel);
        pack();
    }

    public JLabel getJoinedPlayer() {
        return joinedPlayer;
    }

}
