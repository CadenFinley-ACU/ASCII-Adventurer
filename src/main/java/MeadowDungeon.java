
import java.util.ArrayList;



/**
 * MeadowDungeon
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class MeadowDungeon extends Dungeon {

    private static int[] spawnPosition;
    private static int[] bossRoom;
    private static int[] currentPlayerPosition;
    private static int[] save;
    static {
        initializePositions();
    }
    private static void initializePositions() {
        spawnPosition = DungeonGenerator.findValue(meadowDungeon, 9);
        bossRoom = DungeonGenerator.findValue(meadowDungeon, 8);
        currentPlayerPosition = spawnPosition;
        save = spawnPosition;
    }

    public static void startRoom() throws InterruptedException { //start room
        Main.checkSave("Meadow Dungeon");
        Main.screenRefresh();
        initializePositions();
        Dungeon.currentDungeon = "Meadow";
        startRooms();
    }

    public static void fresh() { //fresh
        currentPlayerPosition = spawnPosition;
    }
    private static void startRooms() throws InterruptedException {
        Main.screenRefresh();
        String direction;
        int[] availableMove = DungeonGenerator.getDirections(meadowDungeon, currentPlayerPosition[0], currentPlayerPosition[1]);
        ArrayList<String> directionsString = new ArrayList<>();
        System.out.println("You are in the Meadow Dungeon");
        while (true) { 
            DungeonGenerator.printMapPass(meadowDungeon,5, currentPlayerPosition);
        TextEngine.printWithDelays("You can move in the following directions: ",false);
        if (availableMove[0] >0) {
            directionsString.add("North");
            //currentPlayerPosition[0] -= 1;
        }
        if (availableMove[1] >0) {
            directionsString.add("West");
            //currentPlayerPosition[1] += 1;
        }
        if (availableMove[2] >0) {
            directionsString.add("South");
            //currentPlayerPosition[0] += 1;
        }
        if (availableMove[3] >0) {
            directionsString.add("East");
            //currentPlayerPosition[1] -= 1;
        }
        TextEngine.printWithDelays(directionsString.toString(),true);
        ignore = Room.console.readLine();
        direction = Room.console.readLine();
        switch (direction.toLowerCase()) {
            case "north" -> {
                currentPlayerPosition[0] -= 1;
                save = currentPlayerPosition;
            }
            case "east" -> {
                currentPlayerPosition[1] += 1;
                save = currentPlayerPosition;
            }
            case "west" -> {
                currentPlayerPosition[1] -= 1;
                save = currentPlayerPosition;
            }
            case "south" -> {
                currentPlayerPosition[0] += 1;
                save = currentPlayerPosition;
            }
            default -> {
                Main.inGameDefaultTextHandling(direction);
            }
        }
    }
    }
    public static void __init__() {
        //initialize the meadow dungeon
    }
    
}
