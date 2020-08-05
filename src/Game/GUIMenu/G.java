package Game.GUIMenu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class G
{
//    static ServerSocket serverSocket;
//    Socket connectionSocket;
//    static int playingWithComputer;
//    static int grouhi;
//    int countOfPlayers;
//    static int league;
//    int livesOfTank;
//    int powerOfTir;
//    int livesOfWall;
//    String nameOfGame = "";
//    ObjectOutputStream outputStream = null;
//    ObjectInputStream inputStream = null;
//    Player player;
//    static ArrayList<Player> group1 = null;
//    static ArrayList<Player> group2 = null;
//    static ArrayList<Player> allPlayers = null;
//    static int countOfWinGroup1 = 0;
//    static int countOfWinGroup2 = 0;
//    public G()
//    {
//
//    }
//
//    public static void endOfGame()
//    {
//        if (G.playingWithComputer == 0)//playing with others
//        {
//            if (G.grouhi == 1)//grouping game
//            {
//                String result = "bazi tamam shod. emtiaz group1=" + G.countOfWinGroup1 + "emtiaz group2=" + G.countOfWinGroup2;
//                for (Player p : G.group1)
//                {
//                    Socket connectEachPlayer = p.getSocket();
//                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(connectEachPlayer.getOutputStream());
//                    try
//                    {
//                        objectOutputStream.writeObject(result);
//                    }
//                    catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//                for (Player p : G.group2)
//                {
//                    Socket connectEachPlayer = p.getSocket();
//                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(connectEachPlayer.getOutputStream());
//                    try
//                    {
//                        objectOutputStream.writeObject(result);
//                    }
//                    catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            else//playing 1 by 1
//            {
//                StringBuffer result = new StringBuffer();
//                for (int i = 0; i < G.allPlayers.size(); i++)
//                {
//                    result.append("emtiaz nafar(i+1)=" + G.allPlayers.get(i).getScores() + " ");
//                }
//                for (Player p : G.allPlayers)
//                {
//                    Socket connectEachPlayer = p.getSocket();
//                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(connectEachPlayer.getOutputStream());
//                    try
//                    {
//                        objectOutputStream.writeObject(result.toString());
//                    }
//                    catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        else//playing with computer
//        {
//            if (G.grouhi == 1)//grouping game
//            {
//                System.out.println("bazi tamam shod. emtiaz group1=" + G.countOfWinGroup1 + "emtiaz group2=" + G.countOfWinGroup2);
//            }
//            else //playing 1 by 1
//            {
//                StringBuffer result = new StringBuffer();
//                for (int i = 0; i < G.allPlayers.size(); i++)
//                {
//                    result.append("emtiaz nafar(i+1)=" + G.allPlayers.get(i).getScores() + " ");
//                }
//                System.out.println(result.toString());
//            }
//        }
//    }
//    public synchronized static void increamentWin1()
//    {
//        G.countOfWinGroup1 = G.countOfWinGroup1 +1;
//        if(G.countOfWinGroup1 + G.countOfWinGroup2 < G.league)
//        {
//            //TODO method aghaz bazi.gharagiri tankHa.
//        }
//        else
//        {
//            endOfGame();
//        }
//    }
//
//    public synchronized static void increamentWin2()
//    {
//        G.countOfWinGroup2 = G.countOfWinGroup2 +1;
//        if(G.countOfWinGroup1 + G.countOfWinGroup2 < G.league)
//        {
//            //TODO method aghaz bazi.gharagiri tankHa.
//        }
//        else
//        {
//            endOfGame();
//        }
//    }
//
//    public synchronized static void increamentScore(int index)
//    {
//        allPlayers.get(index).addScore();
//        int sum = 0;
//        for(Player p :allPlayers)
//        {
//            sum = sum + p.getScores();
//        }
//        if(sum < league)
//        {
//            //TODO method aghaz bazi.gharagiri tankHa.
//        }
//        else
//        {
//            endOfGame();
//        }
//    }
//
//    public static void replacePlayerInLogOut()
//    {
//        if(grouhi == 1)//grouping game
//        {
//            for(int i = 0 ; i < group1.size() ; i++)
//            {
//                if(group1.get(i).getSocket() == null)
//                {
//                    Socket socket = serverSocket.accept();
//                    Player player = new Player(socket,false);
//                    //TODO random gharar dadane tank.
//                    group1.set(i,player);
//                    Thread t = new Thread(player);
//                    t.start();
//                    return;
//                }
//                if(group2.get(i).getSocket() == null)
//                {
//                    Socket socket = serverSocket.accept();
//                    Player player = new Player(socket,false);
//                    //TODO random gharar dadane tank.
//                    group2.set(i,player);
//                    return;
//                }
//            }
//        }
//        else//playing 1 by 1
//        {
//            for(int i = 0 ; i < allPlayers.size() ; i++)
//            {
//                if(allPlayers.get(i).getSocket() == null)
//                {
//                    Socket socket = serverSocket.accept();
//                    Player player = new Player(socket,false);
//                    //TODO random gharar dadane tank.
//                    allPlayers.set(i,player);
//                    return;
//                }
//            }
//        }
//    }
//
//    public void logIn()
//    {
//        try
//        {
//            serverSocket = new ServerSocket(7000);
//            try
//            {
//                connectionSocket = serverSocket.accept();//waiting for connection.
//                outputStream = new ObjectOutputStream(connectionSocket.getOutputStream());
//                inputStream = new ObjectInputStream(connectionSocket.getInputStream());
//            }
//            catch(IOException e)
//            {
//                    e.printStackTrace();
//            }
//                player = new Player(connectionSocket, false);
//                Thread t = new Thread(player);
//                try
//                {
//                    outputStream.writeObject("you are first player");
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    String user = (String) inputStream.readObject();
//                    String pass = (String) inputStream.readObject();
//                    player.setUserName(user);
//                    player.setPassword(pass);
//                }
//                catch (IOException | ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    outputStream.writeObject("name bazi ra vared konid");
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    nameOfGame = (String) inputStream.readObject();
//                }
//                catch (IOException | ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    outputStream.writeObject("tedad jan tankHa ra vared konid.");
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    String livesOfTankString = (String) inputStream.readObject();
//                    livesOfTank = Integer.parseInt(livesOfTankString);
//                }
//                catch (IOException | ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    outputStream.writeObject("ghodrat tirHa ravared konid.");
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    String powerOfTirString = (String) inputStream.readObject();
//                    powerOfTir = Integer.parseInt(powerOfTirString);
//                }
//                catch (IOException | ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    outputStream.writeObject("tedad jan divarHa ra vared konid.");
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    String playingWithComputerString = (String) inputStream.readObject();
//                    playingWithComputer = Integer.parseInt(playingWithComputerString);
//                }
//                catch (IOException | ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    outputStream.writeObject("bazi ba digaran:Input 1  bazi ba computer:Input 2");
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    String playingWithComputerString = (String) inputStream.readObject();
//                    playingWithComputer = Integer.parseInt(playingWithComputerString);
//                }
//                catch (IOException | ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    outputStream.writeObject("tak be tak:Input 1  grouhi:Input 2");
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    String grouhiString = (String) inputStream.readObject();
//                    grouhi = Integer.parseInt(grouhiString);
//                }
//                catch (IOException | ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    outputStream.writeObject("tedade bazikonan ra vared konid");
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    String countOfPlayersString = (String) inputStream.readObject();
//                    countOfPlayers = Integer.parseInt(countOfPlayersString);
//                }
//                catch (IOException | ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    outputStream.writeObject("tedade bazihaye league ra vared konid");
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                try
//                {
//                    String leagueString = (String) inputStream.readObject();
//                    league = Integer.parseInt(leagueString);
//                }
//                catch (IOException | ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                t.start();
//            }
//            catch (java.net.SocketException e)
//            {
//                e.printStackTrace();
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }
//
//
//    public void playing()
//    {
//        if(grouhi == 1)//grouping game
//        {
//            group1 = new ArrayList<Player>();
//            group2 = new ArrayList<Player>();
//            int group = 0;
//            try
//            {
//                outputStream.writeObject("shomare grouh ra vared konid.");
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//            try
//            {
//                String groupString = (String) inputStream.readObject();
//                group = Integer.parseInt(groupString);
//            }
//            catch (IOException | ClassNotFoundException e)
//            {
//                e.printStackTrace();
//            }
//            if(group == 1)
//            {
//                group1.add(player);
//                player.setGroup(1);
//            }
//            else
//            {
//                group2.add(player);
//                player.setGroup(2);
//            }
//            if(playingWithComputer == 0)//playing with others.
//            {
//                for(int i=1 ; i<countOfPlayers ; i++)//communicating in the game.
//                {
//                    try
//                    {
//                        connectionSocket = serverSocket.accept();//waiting for connection.
//                        outputStream = new ObjectOutputStream(connectionSocket.getOutputStream());
//                        inputStream = new ObjectInputStream(connectionSocket.getInputStream());
//                    }
//                    catch(IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//                    player = new Player(connectionSocket, false);
//                    Thread t = new Thread(player);
//                    try
//                    {
//                        outputStream.writeObject("Grouping G-count of present player="+i);
//                    }
//                    catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//                    try
//                    {
//                        String user = (String) inputStream.readObject();
//                        String pass = (String) inputStream.readObject();
//                        player.setUserName(user);
//                        player.setPassword(pass);
//                    }
//                    catch (IOException | ClassNotFoundException ex)
//                    {
//                        ex.printStackTrace();
//                    }
//                    try
//                    {
//                        outputStream.writeObject("shomare grouh ra vared konid.");
//                    }
//                    catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//                    try
//                    {
//                        String groupString = (String) inputStream.readObject();
//                        group = Integer.parseInt(groupString);
//                    }
//                    catch (IOException | ClassNotFoundException e)
//                    {
//                        e.printStackTrace();
//                    }
//                    if(group == 1)
//                    {
//                        group1.add(player);
//                        player.setGroup(1);
//                    }
//                    else
//                    {
//                        group2.add(player);
//                        player.setGroup(2);
//                    }
//                    t.start();
//                }
//            }
//            else//playing with computer.
//            {
//                for(int i=1 ; i<countOfPlayers ; i++)
//                {
//                    Player player = new Player(null,true);
//                    Thread t = new Thread(player);
//                    t.start();
//                    if(group1.size() == 1)//nafar aval dar grouh 1 ast.
//                    {
//                        if(i % 2 == 1)
//                        {
//                            group2.add(player);
//                        }
//                        else
//                        {
//                            group1.add(player);
//                        }
//                    }
//                    else //nafar aval dar grouh 2 ast.
//                    {
//                        if(i % 2 == 1)
//                        {
//                            group1.add(player);
//                        }
//                        else
//                        {
//                            group2.add(player);
//                        }
//                    }
//                }
//            }
//        }
//        else//playing 1 by 1.
//        {
//            if(playingWithComputer == 0)//playing with others.
//            {
//                for(int i=1 ; i<countOfPlayers ; i++)//communicating in the game.
//                {
//                    try
//                    {
//                        connectionSocket = serverSocket.accept();//waiting for connection.
//                        outputStream = new ObjectOutputStream(connectionSocket.getOutputStream());
//                        inputStream = new ObjectInputStream(connectionSocket.getInputStream());
//                    }
//                    catch(IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//                    Player player = new Player(connectionSocket,false);
//                    Thread t = new Thread(player);
//                    allPlayers.add(player);
//                    try
//                    {
//                        outputStream.writeObject("1 by 1 G-count of present player="+i);
//                    }
//                    catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//                    try
//                    {
//                        String user = (String) inputStream.readObject();
//                        String pass = (String) inputStream.readObject();
//                        player.setUserName(user);
//                        player.setPassword(pass);
//                    }
//                    catch (IOException |ClassNotFoundException e)
//                    {
//                        e.printStackTrace();
//                    }
//                    t.start();
//                }
//            }
//            else//playing with computer
//            {
//                for(int i=1 ; i<countOfPlayers ; i++)
//                {
//                    Player player = new Player(null,true);
//                    Thread t = new Thread(player);
//                    t.start();
//                    allPlayers.add(player);
//                }
//            }
//        }
//        if(grouhi == 1)//grouping game
//        {//sending map in beginning of the game.
//            for(Player p:group1)
//            {
//                if(p.getSocket() != null)
//                {
//                    Socket connectEachPlayer = p.getSocket();
//                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(connectEachPlayer.getOutputStream());
//                    objectOutputStream.writeObject(map);
//                }
//            }
//            for(Player p:group2)
//            {
//                if(p.getSocket() != null)
//                {
//                    Socket connectEachPlayer = p.getSocket();
//                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(connectEachPlayer.getOutputStream());
//                    objectOutputStream.writeObject(map);
//                }
//            }
//        }
//        else//playing 1 by 1
//        {
//            for(Player p:allPlayers)
//            {
//                if(p.getSocket() != null)
//                {
//                    Socket connectEachPlayer = p.getSocket();
//                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(connectEachPlayer.getOutputStream());
//                    objectOutputStream.writeObject(map);
//                }
//            }
//        }
//    }
}

