package Service.Server;

import Service.Player;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServer {

    private static ServerSocket serverSocket;
    private static ArrayList<Socket> connectionSocket;

    //all players info
    private static ArrayList<Player> players;
//    private static ArrayList<GameServer> gameServers;
    private static ArrayList<Thread> playerThreads;

    private static ObjectInputStream inputStream;
    private static ObjectOutputStream outputStream;

    public static void main(String[] args) {
        int count = 0;
        try {
            serverSocket = new ServerSocket(9999);
            while(true) {
                Socket connectionSocket = serverSocket.accept();
                count++;
                playerThreads.add(new Thread(new ClientHandler(connectionSocket, count), "User " + (count-1)));
                playerThreads.get(count-1).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {

        private Socket connectionSocket;
        private int clientNum;

        public ClientHandler(Socket connectionSocket, int clientNum) {
            this.connectionSocket = connectionSocket;
            this.clientNum = clientNum;
        }

        @Override
        public void run() {
            try {
//                outputStream = new ObjectOutputStream(connectionSocket.getOutputStream());
//                inputStream = new ObjectInputStream(connectionSocket.getInputStream());

                //TODO first login info send
                while (true) {
                    //a player connect

                }
                //TODO working with client
            } finally {
                try {
                    connectionSocket.close();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }

//        public void logIn()
//        {
//            try
//            {
//                serverSocket = new ServerSocket(7000);
//                try
//                {
//                    connectionSocket = serverSocket.accept();//waiting for connection.
//                    outputStream = new ObjectOutputStream(connectionSocket.getOutputStream());
//                    inputStream = new ObjectInputStream(connectionSocket.getInputStream());
//                }
//                catch(IOException e)
//                {
//                    e.printStackTrace();
//                }
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
    }
}
