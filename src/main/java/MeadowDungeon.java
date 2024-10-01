

/**
 * MeadowDungeon
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class MeadowDungeon extends Dungeon {

    public static int[][] meadowDungeon;
    private static int[] spawnPosition;
    private static int[] bossRoom;
    private static int[] currentPlayerPosition;
    private static int[] meadowSave;
    static {
        initializePositions();
    }
    private static void initializePositions() {
        meadowDungeon = DungeonGenerator.generateAndReturnMatrix(5);
        spawnPosition = DungeonGenerator.findValue(meadowDungeon, 9);
        bossRoom = DungeonGenerator.findValue(meadowDungeon, 8);
        currentPlayerPosition = spawnPosition;
        meadowSave = spawnPosition;
    }

    public static void startRoom() throws InterruptedException { //start room
        Main.screenRefresh();
        initializePositions();
        Dungeon.currentDungeon = "Meadow";
        startRooms();
    }

    public static void fresh() { //fresh
        currentPlayerPosition = spawnPosition;
    }
    private static void startRooms() throws InterruptedException {
        int[] availableDirections = DungeonGenerator.getDirections(meadowDungeon, currentPlayerPosition[0],currentPlayerPosition[1]);
        TextEngine.printWithDelays("You are in the Meadow Dungeon", true);
        ignore = console.readLine();
        TextEngine.printNoDelay("Available Directions: " + availableDirections[0] + " " + availableDirections[1] + " " + availableDirections[2] + " " + availableDirections[3],false);
        ignore = console.readLine();
    }
    public static void __init__() {
        //initialize the meadow dungeon
    }
    
}
