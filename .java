package com.company.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.io.*;
import java.util.ArrayList;

public class LogInFrame
{
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;
    int yOfNewTextField = 130;
    String recieve = "";
    int countOfloop;
    int counter = 1;
    public LogInFrame(int port)
    {
        JFrame logIn = new JFrame("logIn");
        logIn.setVisible(true);
        logIn.setLayout(null);
        logIn.setBounds(0, 0, 1200, 850);
        Socket socket = null;

        try
        {
            socket = new Socket("127.0.0.1", port);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        }
        catch (IOException exe)
        {
            exe.printStackTrace();
        }
        String informationOfGame = "";
        try
        {
            informationOfGame = (String) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        ArrayList<JTextField> recieves = new ArrayList<JTextField>();
        ArrayList<JTextField> responses = new ArrayList<JTextField>();
        ArrayList<JButton> sends = new ArrayList<JButton>();
        JTextField information = new JTextField(informationOfGame);
        information.setBounds(10, 10, 120, 30);
        logIn.add(information);
        JTextField user = new JTextField("username");
        user.setVisible(true);
        user.setBounds(10, 50, 100, 30);
        logIn.add(user);
        JTextField pass = new JTextField("password");
        pass.setVisible(true);
        pass.setBounds(10, 90, 100, 30);
        logIn.add(pass);
        Checkbox inputUserPass = new Checkbox("remember me", false);
        inputUserPass.setBounds(120, 50, 100, 30);
        inputUserPass.setVisible(true);
        logIn.add(inputUserPass);
        JButton logInButton = new JButton("logIn");
        logInButton.setBounds(120, 90, 80, 30);
        logIn.add(logInButton);
        logInButton.setBackground(Color.GREEN);
        if (informationOfGame.toCharArray()[0] == 'y')//first player
        {
            countOfloop = 8;
        }
        else if (informationOfGame.toCharArray()[0] == 'g')//grouping game
        {
            countOfloop = 1;
        }
        else
        {
            countOfloop = 0;
        }
        logInButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (inputUserPass.getState() == false)
                {
                    try
                    {
                        outputStream.writeObject(user.getText());
                        outputStream.writeObject(pass.getText());
                    }
                    catch (IOException exe)
                    {
                        exe.printStackTrace();
                    }
                }
                else
                {
                    try
                    {
                        outputStream.writeObject("");
                        outputStream.writeObject("");
                    }
                    catch (IOException exe)
                    {
                        exe.printStackTrace();
                    }
                }
                try
                {
                    recieve = (String) inputStream.readObject();
                }
                catch (IOException | ClassNotFoundException ex)
                {
                    ex.printStackTrace();
                }
                JTextField newRecieve = new JTextField(recieve);
                newRecieve.setBounds(10, yOfNewTextField, 200, 30);
                logIn.add(newRecieve);
                recieves.add(newRecieve);
                JTextField newResponse = new JTextField("response");
                newResponse.setBounds(230, yOfNewTextField, 160, 30);
                logIn.add(newResponse);
                responses.add(newResponse);
                JButton newSend = new JButton("send");
                newSend.setBounds(400, yOfNewTextField, 60, 30);
                newSend.setBackground(Color.YELLOW);
                sends.add(newSend);
                yOfNewTextField = yOfNewTextField + 40;
            }
        });
        for (counter = 0; counter < countOfloop; counter++)
        {
            sends.get(counter).addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        outputStream.writeObject(responses.get(counter).getText());
                    }
                    catch (IOException exception)
                    {
                        exception.printStackTrace();
                    }
                    if(counter != (countOfloop - 1))
                    {
                        try
                        {
                            recieve = (String) inputStream.readObject();
                        }
                        catch (IOException | ClassNotFoundException ex)
                        {
                            ex.printStackTrace();
                        }
                        JTextField newRecieve = new JTextField(recieve);
                        newRecieve.setBounds(10, yOfNewTextField, 200, 30);
                        logIn.add(newRecieve);
                        recieves.add(newRecieve);
                        JTextField newResponse = new JTextField("response");
                        newResponse.setBounds(230, yOfNewTextField, 160, 30);
                        logIn.add(newResponse);
                        responses.add(newResponse);
                        JButton newSend = new JButton("send");
                        newSend.setBounds(400, yOfNewTextField, 60, 30);
                        newSend.setBackground(Color.YELLOW);
                        sends.add(newSend);
                        yOfNewTextField = yOfNewTextField + 40;
                    }
                }
            });
        }
        logIn.setVisible(false);
    }
}
