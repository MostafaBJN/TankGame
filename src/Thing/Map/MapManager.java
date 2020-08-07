package Thing.Map;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MapManager {

    private static final String TYPE_OF_FILE = ".map";
    private static final String FOLDER_PATH = "saves\\maps\\";


    private static ArrayList<Map> maps;
    private static Map selectedMap;


    private MapManager() { }

    public static void selectMap(String name) {
        for (Map map:maps){
            if(map.getName().equals(name)) {
                selectedMap = map;
                return;
            }
        }
        JOptionPane.showMessageDialog(null, name + " Does Not Exist! ", "Selecting Thing.Map", JOptionPane.ERROR_MESSAGE);
    }

    public static void loadAllMaps() {
        maps = new ArrayList<Map>();
        File[] files = new File(FOLDER_PATH).listFiles();
        ArrayList<File> fileList = new ArrayList<>(Arrays.asList(files));

        for (File file:fileList){
            ArrayList<String> mapIntsString;
            try {
                Scanner read = new Scanner(file);
                mapIntsString = new ArrayList<>();
                int i =0;
                while (read.hasNextLine()) {
                    mapIntsString.add(read.nextLine());
                    i++;
                }
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println(file.getName() + " MAP NOT LOADED");
                fileNotFoundException.printStackTrace();
                continue;
            }
            makeLoadedMap(file.getName(), mapIntsString);
        }
    }

//
//    public static ArrayList<String> loadMap(String nameOfFile) {
//        File mapFile;
//        try {
//            mapFile = new File(FOLDER_PATH + nameOfFile + TYPE_OF_FILE);
//            System.out.println(mapFile);
//        } catch (Exception e) {
//            try {
//                mapFile = new File(FOLDER_PATH + nameOfFile + ".txt");
//            } catch (Exception ee) {
//                return null;
//            }
//        }
//        System.out.println(mapFile);
//        try {
//            Scanner read = new Scanner(mapFile);
//            ArrayList<String> mapIntsString = new ArrayList<>();
//            int i =0;
//            while (read.hasNextLine()) {
//                mapIntsString.add(read.nextLine());
//                System.out.println(mapIntsString.get(i));
//                i++;
//            }
//            return mapIntsString;
//        }
//        catch (FileNotFoundException e){
//            System.out.println("Can't Read the File");
//            e.fillInStackTrace();
//        }
//        System.out.println("NO MAP LOADED");
//        return null;
//    }

    public static void makeLoadedMap(String name, ArrayList<String> mapIntsString) {
        //no map loaded
        if(mapIntsString == null) {
            JOptionPane.showMessageDialog(null, "No Thing.Map Loaded", "Loading Thing.Map", JOptionPane.ERROR_MESSAGE);
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

    public static Map getSelectedMap() {
        return selectedMap;
    }


    public static ArrayList<Map> getMaps() {
        return maps;
    }
}
