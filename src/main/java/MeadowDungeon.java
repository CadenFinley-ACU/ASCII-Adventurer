
import java.util.ArrayList;

public class MeadowDungeon extends Dungeon {

    private static final int[] spawnPosition = DungeonGenerator.findValue(Dungeon.meadowDungeon, 9);
    private static final int[] bossRoom = DungeonGenerator.findValue(Dungeon.meadowDungeon, 8);
    private static int[] save = spawnPosition;
    private static int[] lastPosition = spawnPosition.clone(); // Variable to store the last position
    public static int[][] roomsBeenTo = DungeonGenerator.createRoomsBeenTo(Dungeon.meadowDungeon.length);
    public static String direction;
    public static int[] availableMove;
    public static ArrayList<String> directionsString;
    private static int itemRooms;
    private static int foundItemRooms=0;

    static {
        try {
            initializePositions();
        } catch (InterruptedException ex) {
        }
    }

    private static void initializePositions() throws InterruptedException {
        Dungeon.currentPlayerPosition = spawnPosition;
    }

    public static void startRoom() throws InterruptedException { //start room
        itemRooms = DungeonGenerator.countRooms(meadowDungeon, 2);
        room = "Meadow Dungeon";
        Main.checkSave(room);
        Main.screenRefresh();
        Dungeon.currentDungeon = "Meadow";
        currentPlayerPosition = save;
        startRooms();
    }

    public static void fresh() { //fresh
        Dungeon.currentPlayerPosition = spawnPosition;
        currentPlayerPosition = spawnPosition;
        roomsBeenTo = DungeonGenerator.createRoomsBeenTo(Dungeon.meadowDungeon.length);
        save = spawnPosition;
        lastPosition = spawnPosition.clone();
    }

    private static void startRooms() throws InterruptedException {
        System.out.println("You are in the Meadow Dungeon");
        Main.screenRefresh();
        availableMove = DungeonGenerator.getDirections(meadowDungeon, currentPlayerPosition[0], currentPlayerPosition[1]);
        DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(meadowDungeon, roomsBeenTo, currentPlayerPosition);
        directionsString = new ArrayList<>();
        if (meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            boolean finsihed = false;
            TextEngine.printWithDelays("You have entered a room with enemies and were ambushed!", false);
            TextEngine.printWithDelays("What is your command (run or fight)?", true);
            while (true) {
                ignore = Room.console.readLine();
                command = Room.console.readLine();
                switch (command.toLowerCase()) {
                    case "run" -> {
                        TextEngine.printWithDelays("You ran away from the enemies", false);
                        currentPlayerPosition = lastPosition.clone(); // Restore the last position
                        save = currentPlayerPosition.clone();
                        TextEngine.enterToNext();
                        Main.loadSave();
                        return; // Exit the method to prevent further execution
                    }
                    case "fight" -> {
                        Player.changeHealth(Enemy.spawnEnemy("goblin", 1) + Enemy.spawnEnemy("skeleton", 1));
                        lastPosition = currentPlayerPosition.clone();
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        finsihed = true;
                        break;
                    }
                    default -> {
                        Dungeon.defaultDungeonArgs(command.toLowerCase());
                    }
                }
                if (finsihed) {
                    break;
                }
            }
        }
        if (meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 2 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            switch(itemRooms) {
                case 1 -> {
                    hasItemInRoom("axe", 1);
                    currentPlayerPosition = lastPosition.clone(); // Restore the last position
                    save = currentPlayerPosition.clone();
                    foundItemRooms++;
                    Main.loadSave();
                }
                case 2 -> {
                    hasItemInRoom("chainmail set", 2);
                    currentPlayerPosition = lastPosition.clone(); // Restore the last position
                    save = currentPlayerPosition.clone();
                    foundItemRooms++;
                    Main.loadSave();
                }
            }
        }
        Main.screenRefresh();
        DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(meadowDungeon, roomsBeenTo, currentPlayerPosition);
        TextEngine.printWithDelays("You can move in the following directions: ", false);
        if (availableMove[0] > 0) {
            if (testIfBossRoom(availableMove[0])) {
                directionsString.add("boss room");
            } else {
                directionsString.add("north");
            }
        }
        if (availableMove[1] > 0) {
            if (testIfBossRoom(availableMove[1])) {
                directionsString.add("boss room");
            } else {
                directionsString.add("south");
            }
        }
        if (availableMove[2] > 0) {
            if (testIfBossRoom(availableMove[2])) {
                directionsString.add("boss room");
            } else {
                directionsString.add("west");
            }
        }
        if (availableMove[3] > 0) {
            if (testIfBossRoom(availableMove[3])) {
                directionsString.add("boss room");
            } else {
                directionsString.add("east");
            }
        }
        TextEngine.printWithDelays(directionsString.toString(), true);

        while (true) {
            ignore = Room.console.readLine();
            direction = Room.console.readLine();
            switch (direction.toLowerCase()) {
                case "north" -> {
                    if (directionsString.contains(direction.toLowerCase())) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[0] -= 1;
                        save = currentPlayerPosition.clone();
                        Main.loadSave();
                    } else {
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                }
                case "east" -> {
                    if (directionsString.contains(direction.toLowerCase())) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[1] += 1;
                        save = currentPlayerPosition.clone();
                        Main.loadSave();
                    } else {
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                }
                case "west" -> {
                    if (directionsString.contains(direction.toLowerCase())) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[1] -= 1;
                        save = currentPlayerPosition.clone();
                        Main.loadSave();
                    } else {
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                }
                case "south" -> {
                    if (directionsString.contains(direction.toLowerCase())) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[0] += 1;
                        save = currentPlayerPosition.clone();
                        Main.loadSave();
                    } else {
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                }
                case "boss room" -> {
                    if (confirmBossContinue()) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition = bossRoom;
                        save = currentPlayerPosition.clone();
                        Main.loadSave();
                    } else {
                        Main.loadSave();
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

    private static boolean testIfBossRoom(int check) throws InterruptedException {
        if (check != 0) {
            return check == 8;
        }
        return false;
    }

    private static boolean confirmBossContinue() throws InterruptedException {
        TextEngine.printWithDelays("Are you sure you want to continue to the boss room? (yes or no)", true);
        while (true) {
            ignore = Room.console.readLine();
            command = Room.console.readLine();
            switch (command.toLowerCase()) {
                case "yes" -> {
                    return true;
                }
                case "no" -> {
                    return false;
                }
                default -> {
                    Dungeon.defaultDungeonArgs(command.toLowerCase());
                }
            }
        }
    }
}
