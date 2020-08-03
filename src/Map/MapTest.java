package Map;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MapTest {

    public static void main(String[] args) throws InterruptedException {
        String name = "test2";
        MapManager.makeLoadedMap(name, MapManager.loadMap(name));
        MapManager.selectMap(name);
        Map map = MapManager.getSelectedMap();

        JFrame logIn = new JFrame(name);
        logIn.setLayout(new GridLayout(map.getHeight(), map.getWidth(), 0, 0));
        logIn.setSize(map.getVisualWidth(), map.getVisualHeight());
        BreakableWall breakableWall = null;
        for(ArrayList<Ground> listGround:map.getGrounds()){
            for(Ground ground:listGround){
                if(ground instanceof BreakableWall){
                    breakableWall = (BreakableWall) ground;
                }
                logIn.add(new PP(ground.getStyleImage()));
            }
        }
//        for(Ground ground:map.getGroundsOneLineList()){
//            if(ground instanceof BreakableWall){
//                breakableWall = (BreakableWall) ground;
//            }
//            logIn.add(new PP(ground.getStyleImage()));
//        }
        logIn.setVisible(true);
        //logIn.pack();


        map.breakableWallBreaks(breakableWall);


        JFrame logI = new JFrame(name+"@@22");
        logI.setLayout(new GridLayout(map.getHeight(), map.getWidth(), 0, 0));
        //logIn.setResizable(false);
        System.out.println(map.getVisualHeight() + "   " + map.getVisualWidth());
        logI.setSize(map.getVisualWidth(), map.getVisualHeight());
        for(ArrayList<Ground> listGround:map.getGrounds()){
            for(Ground ground:listGround){
                logI.add(new PP(ground.getStyleImage()));
            }
        }
        logI.setVisible(true);
    }

    static class PP extends JPanel {

        BufferedImage image;

        public PP(BufferedImage image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
        }
    }
}
