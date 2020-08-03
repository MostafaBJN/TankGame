package Map;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MapManager {

    private static final String TYPE_OF_FILE = ".map";

    private static final ArrayList<Map> maps;
    private static Map selectedMap;

    static {
        maps = new ArrayList<>();
        selectedMap = null;
    }

    private MapManager() { }

    public static void selectMap(String name) {
        for (Map map:maps){
            if(map.getName().equals(name)) {
                selectedMap = map;
            }
        }
        JOptionPane.showMessageDialog(null, "This Map Does Not Exist! ", "Selecting Map", JOptionPane.ERROR_MESSAGE);
    }

    public static ArrayList<String> loadMap(String nameOfFile) {
        File mapFile;
        try {
            mapFile = new File(nameOfFile + TYPE_OF_FILE);
        } catch (Exception e) {
            try {
                mapFile = new File(nameOfFile + ".txt");
            } catch (Exception ee) {
                return null;
            }
        }
        try {
            Scanner read = new Scanner(mapFile);
            ArrayList<String> mapIntsString = new ArrayList<>();
            while (read.hasNextInt()) {
                mapIntsString.add(read.nextLine());
            }
            return mapIntsString;
        }
        catch (FileNotFoundException e){
            System.out.println("Can't Read the File");
            e.fillInStackTrace();
        }
        System.out.println("NO MAP LOADED");
        return null;
    }

    public static void makeLoadedMap(String name, ArrayList<String> mapIntsString) {
        //no map loaded
        if(mapIntsString == null) {
            JOptionPane.showMessageDialog(null, "No Map Loaded", "Loading Map", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int height = mapIntsString.size();
        int width = mapIntsString.get(0).length();

        ArrayList<ArrayList<Integer>> intsOfMap = new ArrayList<>();

        int j = 0;
        for (String horizontalLine:mapIntsString) {
            intsOfMap.add(new ArrayList<>());
            for (int i = 0; i < width; i++) {
                intsOfMap.get(j).add(Integer.parseInt(String.valueOf(horizontalLine.charAt(i))));
            }
            j++;
        }

        addMap(new Map(name,intsOfMap, height, width));
    }

    public static void addMap(Map mapToAdd) {
        maps.add(mapToAdd);
    }

    public static void saveMap(Map map) {
        try(FileWriter fw = new FileWriter(map.getName() + TYPE_OF_FILE, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            for (ArrayList<Integer> intList : map.getIntsOfMap()) {
                for (int data : intList) {
                    out.print(data);
                }
                out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteMap(Map mapToDelete) {
        return maps.remove(mapToDelete);
    }
}
