package Game.Run;

import Thing.Map.*;

public class Preview
{
    int port;

    String nameOfGame;
    String groupingOr1By1;
    String playingWithComputerOrPerson;
    String countOfLeague;
    String livesOfTank;
    String livesOfWall;
    String powerOfBullet;
    Map map;

    public Preview(String nameOfGame,
                   String groupingOr1By1,
                   String playingWithComputerOrPerson,
                   String countOfLeague,
                   String livesOfTank,
                   String livesOfWall,
                   String powerOfBullet) {
        this.nameOfGame = nameOfGame;
        this.groupingOr1By1 = groupingOr1By1;
        this.playingWithComputerOrPerson = playingWithComputerOrPerson;
        this.countOfLeague = countOfLeague;
        this.livesOfTank = livesOfTank;
        this.livesOfWall = livesOfWall;
        this.powerOfBullet = powerOfBullet;
        map = MapManager.getSelectedMap();
    }

    public String getCountOfLeague()
    {
        return countOfLeague;
    }

    public String getGroupingOr1By1()
    {
        return groupingOr1By1;
    }

    public String getLivesOfTank()
    {
        return livesOfTank;
    }

    public String getLivesOfWall()
    {
        return livesOfWall;
    }

    public String getNameOfGame()
    {
        return nameOfGame;
    }

    public String getPlayingWithComputerOrPerson()
    {
        return playingWithComputerOrPerson;
    }

    public String getPowerOfBullet()
    {
        return powerOfBullet;
    }

    public void setGroupingOr1By1(String groupingOr1By1)
    {
        this.groupingOr1By1 = groupingOr1By1;
    }

    public void setNameOfGame(String nameOfGame)
    {
        this.nameOfGame = nameOfGame;
    }

    public void setCountOfLeague(String countOfLeague)
    {
        this.countOfLeague = countOfLeague;
    }

    public void setLivesOfTank(String livesOfTank)
    {
        this.livesOfTank = livesOfTank;
    }

    public void setLivesOfWall(String livesOfWall)
    {
        this.livesOfWall = livesOfWall;
    }

    public void setPlayingWithComputerOrPerson(String playingWithComputerOrPerson)
    {
        this.playingWithComputerOrPerson = playingWithComputerOrPerson;
    }

    public void setPowerOfBullet(String powerOfBullet)
    {
        this.powerOfBullet = powerOfBullet;
    }

    public Map getMap() {
        return map;
    }
}
