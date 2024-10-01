
import java.util.ArrayList;

/**
 * MeadowDungeon
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class MeadowDungeon extends Dungeon {

    private static final int[] spawnPosition = DungeonGenerator.findValue(Dungeon.meadowDungeon, 9);
    private static final int[] bossRoom = DungeonGenerator.findValue(Dungeon.meadowDungeon, 8);
    private static int[] save = spawnPosition;
    public static int[][] roomsBeenTo = DungeonGenerator.createRoomsBeenTo(Dungeon.meadowDungeon.length);

    static {
        initializePositions();
    }

    private static void initializePositions() {
        Dungeon.currentPlayerPosition = spawnPosition;
    }

    public static void startRoom() throws InterruptedException { //start room
        Main.checkSave("Meadow Dungeon");
        Main.screenRefresh();
        //initializePositions();
        Dungeon.currentDungeon = "Meadow";
        currentPlayerPosition = save;
        startRooms();
    }

    public static void fresh() { //fresh
        currentPlayerPosition = spawnPosition;
    }

    private static void startRooms() throws InterruptedException {
        System.out.println("You are in the Meadow Dungeon");
            Main.screenRefresh();
            String direction;
            int[] availableMove = DungeonGenerator.getDirections(meadowDungeon, currentPlayerPosition[0], currentPlayerPosition[1]);
            roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(meadowDungeon,roomsBeenTo, currentPlayerPosition);
            ArrayList<String> directionsString = new ArrayList<>();
            TextEngine.printWithDelays("You can move in the following directions: ", false);
            if (availableMove[0] > 0) {
                directionsString.add("north");
                //currentPlayerPosition[0] -= 1;
            }
            if (availableMove[1] > 0) {
                directionsString.add("south");
                //currentPlayerPosition[1] += 1;
            }
            if (availableMove[2] > 0) {
                directionsString.add("west");
                //currentPlayerPosition[0] += 1;
            }
            if (availableMove[3] > 0) {
                directionsString.add("east");
                //currentPlayerPosition[1] -= 1;
            }
            TextEngine.printWithDelays(directionsString.toString(), true);
        while (true) {
            ignore = Room.console.readLine();
            direction = Room.console.readLine();
            switch (direction.toLowerCase()) {
                case "north" -> {
                    if(directionsString.contains(direction.toLowerCase())){
                        currentPlayerPosition[0] -= 1;
                        save = currentPlayerPosition;
                        Main.loadSave();
                    }
                    else{
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                    
                }
                case "east" -> {
                    if(directionsString.contains(direction.toLowerCase())){
                        currentPlayerPosition[1] += 1;
                        save = currentPlayerPosition;
                        Main.loadSave();
                    }
                    else{
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                    
                }
                case "west" -> {
                    if(directionsString.contains(direction.toLowerCase())){
                        currentPlayerPosition[1] -= 1;
                        save = currentPlayerPosition;
                        Main.loadSave();
                    }
                    else{
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                    
                }
                case "south" -> {
                    if(directionsString.contains(direction.toLowerCase())){
                        currentPlayerPosition[0] += 1;
                        save = currentPlayerPosition;
                        Main.loadSave();
                    }
                    else{
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                }
                default -> {
                    Dungeon.defaultDungeonArgs(direction.toLowerCase());
                }
            }
        }
    }

    public static void __init__() {
        //initialize the meadow dungeon
    }

}
