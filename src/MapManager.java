import java.util.ArrayList;

public class MapManager {

    private static ArrayList<Map> maps = new ArrayList<>();

    private MapManager() { }

    public static void loadMap() {

    }

    public static void addMap(Map mapToAdd) {
        maps.add(mapToAdd);
    }

    public static void makeMap() {

    }

    public static void saveMap() {

    }

    public static boolean deleteMap(Map mapToDelete) {
        return maps.remove(mapToDelete);
    }
}
