package Service.Server;

import Service.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SaveAndLoad{

    /**
     * PATHs
     */
    public static final String SAVE_FOLDER_PATH = ".\\saves\\";
    public static final String PLAYER_FOLDER_PATH = SAVE_FOLDER_PATH + "players\\";

    /**
     * TYPEs
     */
    public static final String PLAYER_FILE_TYPE = ".player";

    /*
     SAVE
     */

    public static <T> void saveListData(ArrayList<T> list, String folderPath, String fileType) throws IOException {
        new File(folderPath).mkdirs();
        for (T object:list){
            saveObjectData(object, folderPath, "", fileType);
        }
    }

    public static <T> void saveObjectData(T object ,String folderPath, String fileName, String fileType) throws IOException {
        if (fileName.equals("")) {
            if(object instanceof Player) {
                fileName = ((Player) object).getUsername();
            }
            else {
                fileName = String.valueOf(System.currentTimeMillis());
            }
        }
        File file = new File(folderPath + fileName + fileType);
        file.createNewFile();
        saveObject(file, object);
    }

    public static void saveObject(File file, Object object) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(file,false);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(object);
        objectOut.close();
    }

    /*
     LOAD
     */

    public static <T> ArrayList<T> loadListData(String path) throws IOException, ClassNotFoundException {
        ArrayList<T> list = new ArrayList<>();

        ArrayList<Object> objects = loadObjectListData(path);
        for (Object object:objects){
            list.add((T) object);
        }

        return list;
    }

    public static ArrayList<Object> loadObjectListData(String folderPath) throws IOException, ClassNotFoundException {

        File[] files = new File(folderPath).listFiles();
        ArrayList<File> fileList = new ArrayList<>(Arrays.asList(files));

        ArrayList<Object> objects = new ArrayList<>();

        for (File file:fileList){
            Object object = loadObject(file);
            objects.add(object);
        }
        return objects;
    }

    public static Object loadObject(File file) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        Object object = objectIn.readObject();
        objectIn.close();
        return object;
    }

}
