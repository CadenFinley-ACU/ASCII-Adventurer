
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Dungeon Class
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class Dungeon extends Room {

    static String resetColor = "\033[0m"; // reset to default color
    static String yellowColor = "\033[1;33m"; // yellow color
    static String redColor = "\033[0;31m"; // red color
    static String greenColor = "\033[0;32m"; // green color
    static String blueColor = "\033[0;34m"; // blue color
    static String pinkColor = "\033[0;35m"; // pink color

    public static String currentDungeon;
    public static int completedDungeons = 0;
    public static int numberOfEnemies;
    public static String enemyType;
    public static boolean resetedAfterWin = false;

    private static final Random rand = new Random();

    public static List<String> missedItems = new ArrayList<>();

    private int[] currentPostion;
    private int[] lastPosition; // Variable to store the last position

    private final String currentMiniBoss;
    private final String currentBoss;

    private int[] spawnPosition;
    private String direction;
    private int[] availableMove;
    private ArrayList<String> directionsString;
    private final List<String> enemies;

    private List<String> items;
    private final List<String> itemsOrigin;
    private boolean completed = false;
    private boolean visited = false;
    private boolean mapRevealed;
    private final int enemiesCount;

    private int[][] roomsBeenTo;
    private int[][] map;

    public Dungeon(int[][] map, ArrayList<String> enemies, List<String> items, String miniBoss, String boss, int enemiesCount) {
        this.map = map;
        this.spawnPosition = DungeonGenerator.findValue(this.map, 9);
        this.enemies = enemies;
        this.roomsBeenTo = DungeonGenerator.createRoomsBeenTo(this.map.length);
        this.items = items;
        this.itemsOrigin = items;
        this.currentMiniBoss = miniBoss;
        this.currentBoss = boss;
        this.enemiesCount = enemiesCount;
    }

    public void startRoom(String roomSave, String current) throws InterruptedException { //start room
        if (!this.visited) {
            this.fresh();
            this.items = this.itemsOrigin;
            this.visited = true;
            this.currentPostion = DungeonGenerator.findValue(this.map, 9);
            this.roomsBeenTo = DungeonGenerator.createRoomsBeenTo(this.map.length);
        }
        if (!roomSave.equals(Main.getSavedPlace())) {
            this.currentPostion = DungeonGenerator.findValue(this.map, 9);
        }
        this.spawnPosition = DungeonGenerator.findValue(this.map, 9);
        room = roomSave;
        Main.checkSave(room);
        currentDungeon = current;
        GameSaveSerialization.saveGame();
        Main.screenRefresh();
        this.startRooms();
    }

    public void fresh() { //fresh
        this.mapRevealed = false;
        this.visited = false;
        this.completed = false;
        this.spawnPosition = DungeonGenerator.findValue(this.map, 9);
        this.currentPostion = this.spawnPosition;
        this.roomsBeenTo = DungeonGenerator.createRoomsBeenTo(this.map.length);
        this.lastPosition = this.spawnPosition.clone();
    }

    public void startRooms() throws InterruptedException {
        numberOfEnemies = rand.nextInt(enemiesCount);
        enemyType = enemies.get(rand.nextInt(enemies.size()));
        availableMove = null;
        Main.screenRefresh();
        DungeonGenerator.drawRoom(this.map, this.roomsBeenTo, this.currentPostion[0], this.currentPostion[1], numberOfEnemies, this.mapRevealed, this.currentMiniBoss, this.currentBoss, this.lastPosition);
        directionsString = new ArrayList<>();
        if (this.map[this.currentPostion[0]][this.currentPostion[1]] == 9 && this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] == 0) {
            this.dungeonIntroText();
        }
        if (this.map[this.currentPostion[0]][this.currentPostion[1]] == 2 && this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] == 0) {
            this.itemRoom(items);
        }
        if (this.map[this.currentPostion[0]][this.currentPostion[1]] == 10 && this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] == 0) {
            this.fairyRoom();
        }
        if (this.map[this.currentPostion[0]][this.currentPostion[1]] == 3 && this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] == 0) {
            this.keyRoomSequence();
        }
        if (this.map[this.currentPostion[0]][this.currentPostion[1]] == 5 && this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] == 0) {
            this.keyRoom();
        }
        if (this.map[this.currentPostion[0]][this.currentPostion[1]] == 7 && this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] == 0) {
            this.heartContainerRoom();
        }
        if (this.map[this.currentPostion[0]][this.currentPostion[1]] == 1 && this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] == 0) {
            this.fightRandomEnemies();
        }
        if (this.map[this.currentPostion[0]][this.currentPostion[1]] == 6) {
            this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] = this.map[this.currentPostion[0]][this.currentPostion[1]];
            this.dungeonShop();
        }
        if (this.map[this.currentPostion[0]][this.currentPostion[1]] == 4 && this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] == 0) {
            this.miniBossSequence();
        }
        if (this.map[this.currentPostion[0]][this.currentPostion[1]] == 8 && this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] == 0) {
            this.bossRoom();
        }
        handleDirectionsAndCommands();
    }

    public void handleDirectionsAndCommands() throws InterruptedException {
        Main.screenRefresh();
        DungeonGenerator.drawRoom(this.map, this.roomsBeenTo, this.currentPostion[0], this.currentPostion[1], 0, this.mapRevealed, this.currentMiniBoss, this.currentBoss, this.lastPosition);
        this.availableMove = DungeonGenerator.getDirections(this.map, this.currentPostion[0], this.currentPostion[1]);
        if (this.completed) {
            TextEngine.printWithDelays("You have completed this dungeon. You can now type " + yellowColor + "leave" + resetColor + " to exit this dungeon.", false);
        }
        System.out.println("Type " + yellowColor + "map" + resetColor + " to see the map.");
        System.out.println();
        TextEngine.printWithDelays("You can move in the following directions: ", false);
        if (this.availableMove[0] > 0) {
            if (this.testIfBossRoom(this.availableMove[0])) {
                this.directionsString.add("boss room");
            } else {
                this.directionsString.add("north");
            }
        }
        if (this.availableMove[1] > 0) {
            if (this.testIfBossRoom(this.availableMove[1])) {
                this.directionsString.add("boss room");
            } else {
                this.directionsString.add("south");
            }
        }
        if (this.availableMove[2] > 0) {
            if (this.testIfBossRoom(this.availableMove[2])) {
                this.directionsString.add("boss room");
            } else {
                this.directionsString.add("west");
            }
        }
        if (this.availableMove[3] > 0) {
            if (this.testIfBossRoom(this.availableMove[3])) {
                this.directionsString.add("boss room");
            } else {
                this.directionsString.add("east");
            }
        }
        TextEngine.printNoDelay(directionsInString(directionsString), true);
        while (true) {
            this.direction = Room.console.readLine();
            switch (this.direction.toLowerCase().trim()) {
                case "north", "1" -> {
                    if (this.directionsString.contains(this.direction.toLowerCase())) {
                        this.lastPosition = this.currentPostion.clone(); // Save the current position before moving
                        this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] = this.map[this.currentPostion[0]][this.currentPostion[1]];
                        this.currentPostion[0] -= 1;
                        Main.loadSave();
                    } else {
                        this.defaultDungeonArgs(this.direction.toLowerCase());
                    }
                }
                case "east", "2" -> {
                    if (this.directionsString.contains(this.direction.toLowerCase())) {
                        this.lastPosition = this.currentPostion.clone(); // Save the current position before moving
                        this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] = this.map[this.currentPostion[0]][this.currentPostion[1]];
                        this.currentPostion[1] += 1;
                        Main.loadSave();
                    } else {
                        this.defaultDungeonArgs(this.direction.toLowerCase());
                    }
                }
                case "south", "3" -> {
                    if (this.directionsString.contains(this.direction.toLowerCase())) {
                        this.lastPosition = this.currentPostion.clone(); // Save the current position before moving
                        this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] = this.map[this.currentPostion[0]][this.currentPostion[1]];
                        this.currentPostion[0] += 1;
                        Main.loadSave();
                    } else {
                        this.defaultDungeonArgs(this.direction.toLowerCase());
                    }
                }
                case "west", "4" -> {
                    if (this.directionsString.contains(this.direction.toLowerCase())) {
                        this.lastPosition = this.currentPostion.clone(); // Save the current position before moving
                        this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] = this.map[this.currentPostion[0]][this.currentPostion[1]];
                        this.currentPostion[1] -= 1;
                        Main.loadSave();
                    } else {
                        this.defaultDungeonArgs(this.direction.toLowerCase());
                    }
                }
                case "boss room", "5" -> {
                    if (this.directionsString.contains(this.direction.toLowerCase()) && this.confirmBossContinue()) {
                        this.lastPosition = this.currentPostion.clone(); // Save the current position before moving
                        this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] = this.map[this.currentPostion[0]][this.currentPostion[1]];
                        this.currentPostion = DungeonGenerator.findValue(this.map, 8);
                        Main.loadSave();
                    } else {
                        this.defaultDungeonArgs(this.direction.toLowerCase());
                    }
                }
                default -> {
                    this.defaultDungeonArgs(this.direction.toLowerCase());
                }
            }
        }
    }

    public void defaultDungeonArgs(String data) throws InterruptedException { //default dungeon arguments
        switch (data) {
            case "leave" -> {
                if (this.completed) {
                    TextEngine.printWithDelays("You leave the dungeon and return to the open world.", false);
                    this.lastPosition = null;
                    TextEngine.enterToNext();
                    OpenWorld.startRoom();
                } else {
                    TextEngine.printWithDelays("You cannot leave until you have completed the dungeon.", true);
                }
            }
            case "map" -> {
                if (ableToUseMenuCommands()) {
                    Main.screenRefresh();
                    TextEngine.printWithDelays("You open your map and see the following:\n", false);
                    System.out.println();
                    DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(this.map, this.roomsBeenTo, this.currentPostion, this.mapRevealed);
                    System.out.println();
                    TextEngine.enterToNext();
                    Main.loadSave();
                } else {
                    TextEngine.printWithDelays("You cannot use the map right now", true);
                }
            }
            case "reset" -> {
                if (Main.gameComplete) {
                    TextEngine.printWithDelays("This command will reset" + yellowColor + " ALL " + resetColor + "Dungeons!", false);
                    TextEngine.printWithDelays("Are you sure you want to do this? " + redColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor, true);
                    while (true) {
                        command = Room.console.readLine();
                        switch (command.toLowerCase().trim()) {
                            case "yes" -> {
                                TextEngine.printWithDelays(redColor + "All dungeons have been reset and their difficulty has been increased! Good Luck!" + resetColor, false);
                                TextEngine.enterToNext();
                                resetedAfterWin = true;
                                resetAll();
                                Enemy.resetEnemies();
                                completedDungeons = 8;
                                OpenWorld.roomSave = 74;
                                room = "OpenWorld";
                                Main.checkSave(room);
                                GameSaveSerialization.saveGame();
                                Main.loadSave();
                            }
                            default -> {
                                Main.inGameDefaultTextHandling(data);
                            }
                        }
                    }
                } else {
                    Main.inGameDefaultTextHandling(data);
                }
            }
            default -> {
                Main.inGameDefaultTextHandling(data);
            }
        }
    }

    public static void resetAll() { //reset all dungeons
        Main.MeadowDungeon.fresh();
        Main.DarkForestDungeon.fresh();
        Main.MountainCaveDungeon.fresh();
        Main.MountainTopDungeon.fresh();
        Main.DesertOasisDungeon.fresh();
        Main.DesertPlainsDungeon.fresh();
        Main.DesertPyramidDungeon.fresh();
        Main.OceanKingdomDungeon.fresh();
        completedDungeons = 0;
        Main.buildDungeons();
    }

    public static void dungeonCheck() throws InterruptedException {
        if (OpenWorld.holdCommand == null) {
            OpenWorld.holdCommand = "onward";
            TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ".", resetedAfterWin);
            return;
        }
        switch (completedDungeons) {
            case 0 -> {// the meadow dungeon
                switch (OpenWorld.roomNumber) {
                    case 72, 73, 74 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, east.\n\n ", false);
                    case 64, 65, 66, 67, 68, 69 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the east.\n\n", false);
                    default ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south, east.\n\n", false);
                }
            }
            case 1 -> {// the dark forest dungeon
                switch (OpenWorld.roomNumber) {
                    case 72, 73, 74, 64, 65, 66, 67, 68, 69 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, east.\n\n", false);
                    case 55, 27, 57, 58, 59, 60 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the east.\n\n", false);
                    default ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south, east.\n\n", false);
                }
            }
            case 2 -> {// The Mountain Cave Dungeon
                switch (OpenWorld.roomNumber) {
                    case 74, 66, 57, 50, 41, 62 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north.\n\n", false);
                    case 33, 34, 35, 36, 42, 43, 44, 45, 51, 52, 53, 54, 58, 59, 60, 67, 68, 69 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, west.\n\n", false);
                    case 21, 22 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the east.\n\n", false);
                    case 30, 31, 32, 37, 38, 39, 40, 47, 48, 49, 27, 55, 64, 65, 72, 73 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, east\n\n", false);
                    case 17, 18, 19, 13, 14, 15, 9, 10, 11, 5, 6 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south, west.\n\n", false);
                    case 4 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south.\n\n", false);
                    case 3, 2, 16, 12, 8 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south, east.\n\n", false);
                    default ->
                        TextEngine.printWithDelays("The Mountain cave is not working Doungeon.java\n\n", false);

                }
            }
            case 3 -> {// The Mountain Top Dungeon
                switch (OpenWorld.roomNumber) {
                    case 2, 3, 4, 5, 6 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the east.\n\n", false);
                    case 11, 15, 19, 28, 35, 44, 53, 60, 69 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north.\n\n", false);
                    case 29, 36, 45, 54 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, west.\n\n", false);
                    default ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, east.\n\n", false);
                }
            }
            case 4 -> {// The Desert Oasis Dungeon
                switch (OpenWorld.roomNumber) {
                    case 72, 73, 74 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the west.\n\n", false);
                    case 32, 39, 48, 55, 64 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south.\n\n", false);
                    case 8, 12, 16, 22, 21, 30, 31, 37, 38, 47 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south east.\n\n", false);
                    default ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south, west.\n\n", false);
                }
            }
            case 5 -> {// The Desert Plains Dungeon
                switch (OpenWorld.roomNumber) {
                    case 64, 65, 66, 67, 68, 69 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the west.\n\n", false);
                    case 72, 73, 74 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, west.\n\n", false);
                    case 55, 48, 39, 32 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south.\n\n", false);
                    case 8, 12, 16, 22, 31, 38, 47, 21, 30, 37 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south east.\n\n", false);
                    default ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south west.\n\n", false);
                }
            }
            case 6 -> {// The Desert Pyramid Dungeon
                switch (OpenWorld.roomNumber) {
                    case 55, 27, 57, 58, 59, 60 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the west.\n\n", false);
                    case 64, 65, 66, 67, 68, 69, 72, 73, 74 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, west.\n\n", false);
                    case 48, 39, 32 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south.\n\n", false);
                    case 8, 12, 16, 22, 31, 38, 47, 21, 30, 37 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south east.\n\n", false);
                    default ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south west.\n\n", false);
                }
            }
            case 7 -> { // The Ocean Kingdom Dungeon
                switch (OpenWorld.roomNumber) {
                    case 8, 12, 16, 22, 31, 38, 47 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north.\n\n", false);
                    case 21, 30, 37 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, east.\n\n", false);
                    case 2, 3, 4, 5, 6 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south.\n\n", false);
                    default ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, west.\n\n", false);
                }
            }
            case 8 -> {
                TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + "\n Congratulations! You have ridded the world of these evil dungeons!\n You have now unlocked the ability to go and rechallenge the old dungeons! ", false);
            }
            default ->
                TextEngine.printWithDelays("this function isnt working right", false);
        }
    }

    public boolean confirmBossContinue() throws InterruptedException {
        if (Player.inventory.containsKey("key")) {
            TextEngine.printWithDelays("Would you like to unlock the door to the boss room? " + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor, true);
            while (true) {
                command = Room.console.readLine();
                switch (command.toLowerCase().trim()) {
                    case "yes" -> {
                        if (InventoryManager.useKey()) {
                            TextEngine.printWithDelays("The key was a perfect fit!", false);
                            TextEngine.enterToNext();
                            return true;
                        } else {
                            TextEngine.printWithDelays("You need a key to unlock the door to the boss room, i'm sure there is one around here somewhere", false);
                            TextEngine.enterToNext();
                            return false;
                        }
                    }
                    case "no" -> {
                        return false;
                    }
                    default -> {
                        this.defaultDungeonArgs(command.toLowerCase());
                    }
                }
            }
        } else {
            TextEngine.printWithDelays("You need a key to unlock the door to the boss room, i'm sure there is one around here somewhere", false);
            TextEngine.enterToNext();
            return false;
        }
    }

    public void dungeonShop() throws InterruptedException {
        Main.screenRefresh();
        String localDungeon = currentDungeon;
        TextEngine.printNoDelay("Gold: " + Player.getGold(), false);
        TextEngine.printNoDelay("Inventory: " + Player.getTotalNumberOfItemsInInventory() + "/" + Player.inventorySize, false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("Welcome to the Dungeon shop Traveler! I snuck into this dungeon a long time ago and got lost.\nMaybe some of these items can help you on your journey.\nWhat would you like to buy?", false);
        switch (localDungeon) {
            case "Meadow", "Dark Forest", "Mountain Cave", "Mountain Top" -> {
                TextEngine.printNoDelay(yellowColor + "health potion" + resetColor + " ~20 gold\n" + yellowColor + "magic map" + resetColor + " ~50\n" + yellowColor + "K.O. Cannon" + resetColor + " ~3000 gold\nor " + yellowColor + " leave" + resetColor, true);
                while (true) {
                    command = Room.console.readLine();
                    switch (command.toLowerCase().trim()) {
                        case "health potion" -> {
                            buyMultiple("health potion", 20);
                        }
                        case "magic map" -> {
                            if (Player.getGold() >= 50 && !checkIfCurrentDungeonIsRevealed()) {
                                Player.changeGold(-50);
                                useMagicMap();
                                keepShopping();
                                break;
                            } else {
                                TextEngine.printWithDelays("You don't have enough gold for that", false);
                                keepShopping();
                                break;
                            }
                        }
                        case "k.o. cannon" -> {
                            if (Player.getGold() >= 3000) {
                                if (Player.hasRoomInInventory(1)) {
                                    Player.changeGold(-3000);
                                    Player.putItem("K.O. Cannon", 1);
                                    keepShopping();
                                    break;
                                } else {
                                    TextEngine.printWithDelays("You do not have enough space in your inventory to buy a K.O. Cannon", false);
                                    TextEngine.enterToNext();
                                    keepShopping();
                                    break;
                                }
                            } else {
                                TextEngine.printWithDelays("You don't have enough gold for that", false);
                                keepShopping();
                                break;
                            }
                        }
                        case "leave" -> {
                            leave();
                        }
                        default -> {
                            defaultDungeonArgs(command.toLowerCase());
                        }
                    }
                }
            }
            case "Desert Oasis", "Desert Plains", "Desert Pyramid" -> {
                TextEngine.printNoDelay(yellowColor + "greater health potion" + resetColor + " ~50 gold\n" + yellowColor + "magic map" + resetColor + " ~150\n" + yellowColor + "K.O. Cannon" + resetColor + " ~3000 gold\nor " + yellowColor + " leave" + resetColor, true);
                while (true) {
                    command = Room.console.readLine();
                    switch (command.toLowerCase().trim()) {
                        case "greater health potion" -> {
                            buyMultiple("greater health potion", 50);
                        }
                        case "magic map" -> {
                            if (Player.getGold() >= 150 && !checkIfCurrentDungeonIsRevealed()) {
                                Player.changeGold(-150);
                                useMagicMap();
                                keepShopping();
                                break;
                            } else {
                                TextEngine.printWithDelays("You don't have enough gold for that", false);
                                keepShopping();
                                break;
                            }
                        }
                        case "k.o. cannon" -> {
                            if (Player.getGold() >= 3000) {
                                if (Player.hasRoomInInventory(1)) {
                                    Player.changeGold(-3000);
                                    Player.putItem("K.O. Cannon", 1);
                                    keepShopping();
                                    break;
                                } else {
                                    TextEngine.printWithDelays("You do not have enough space in your inventory to buy a K.O. Cannon", false);
                                    TextEngine.enterToNext();
                                    keepShopping();
                                    break;
                                }
                            } else {
                                TextEngine.printWithDelays("You don't have enough gold for that", false);
                                keepShopping();
                                break;
                            }
                        }
                        case "leave" -> {
                            leave();
                        }
                        default -> {
                            defaultDungeonArgs(command.toLowerCase());
                        }
                    }
                }
            }
            case "Ocean Kingdom" -> {
                TextEngine.printNoDelay(yellowColor + "super health potion" + resetColor + " ~75 gold\n" + yellowColor + "magic map" + resetColor + " ~250\n" + yellowColor + "K.O. Cannon" + resetColor + " ~3000 gold\nor " + yellowColor + " leave" + resetColor, true);
                while (true) {
                    command = Room.console.readLine();
                    switch (command.toLowerCase().trim()) {
                        case "super health potion" -> {
                            buyMultiple("super health potion", 75);
                        }
                        case "magic map" -> {
                            if (Player.getGold() >= 250 && !checkIfCurrentDungeonIsRevealed()) {
                                Player.changeGold(-250);
                                useMagicMap();
                                keepShopping();
                                break;
                            } else {
                                TextEngine.printWithDelays("You don't have enough gold for that", false);
                                keepShopping();
                                break;
                            }
                        }
                        case "k.o. cannon" -> {
                            if (Player.getGold() >= 3000) {
                                if (Player.hasRoomInInventory(1)) {
                                    Player.changeGold(-3000);
                                    Player.putItem("K.O. Cannon", 1);
                                    keepShopping();
                                    break;
                                } else {
                                    TextEngine.printWithDelays("You do not have enough space in your inventory to buy a K.O. Cannon", false);
                                    TextEngine.enterToNext();
                                    keepShopping();
                                    break;
                                }
                            } else {
                                TextEngine.printWithDelays("You don't have enough gold for that", false);
                                keepShopping();
                                break;
                            }
                        }
                        case "leave" -> {
                            leave();
                        }
                        default -> {
                            defaultDungeonArgs(command.toLowerCase());
                        }
                    }
                }
            }
        }
    }

    private void buyMultiple(String type, int cost) throws InterruptedException { //buy multiple clause for certain items in village shop
        TextEngine.printWithDelays("How many would you like to buy?", true);
        while (true) {
            command = console.readLine();
            if (TextEngine.checkValidInput(command)) {
                try {
                    Integer.valueOf(command);
                } catch (NumberFormatException e) {
                    Main.invalidCommand();
                    TextEngine.enterToNext();
                    buyMultiple(type, cost);
                }
                int totalCost = cost * Integer.parseInt(command);
                if (Player.getGold() >= totalCost && !command.equals("0")) {
                    if (Player.hasRoomInInventory(Integer.parseInt(command))) {
                        Player.changeGold(-totalCost);
                        Player.putItem(type, Integer.parseInt(command));
                        keepShopping();
                    } else {
                        TextEngine.printWithDelays("You do not have enough space in your inventory to buy " + command + " " + type + "s", false);
                        TextEngine.enterToNext();
                        keepShopping();
                    }
                } else {
                    switch (command) {
                        case "0" -> {
                            TextEngine.printWithDelays("You did not buy any " + type + "s.", false);
                            TextEngine.enterToNext();
                            keepShopping();
                        }
                        case "1" -> {
                            TextEngine.printWithDelays("You do not have enough gold to buy a " + command, false);
                            TextEngine.enterToNext();
                            keepShopping();
                        }
                        default -> {
                            TextEngine.printWithDelays("You do not have enough gold to buy " + command + " potions", false);
                            TextEngine.enterToNext();
                            keepShopping();
                        }
                    }
                }
            } else {
                Main.invalidCommand();
                keepShopping();
            }
        }
    }

    private void keepShopping() throws InterruptedException { //keep shopping
        TextEngine.printWithDelays("Would you like to keep shopping? " + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor, true);
        while (true) {
            command = console.readLine();
            switch (command.toLowerCase().trim()) {
                case "yes" -> {
                    dungeonShop();
                }
                case "no" -> {
                    leave();
                }
                default ->
                    defaultDungeonArgs(command.toLowerCase());
            }
        }
    }

    private void useMagicMap() throws InterruptedException {
        TextEngine.printWithDelays("You use the magic map...\nIt revealed the layout of the Dungeon!", false);
        TextEngine.enterToNext();
        this.mapRevealed = true;
    }

    private void leave() throws InterruptedException {
        TextEngine.printWithDelays("You leave the shop and return to the Dungeon.", false);
        TextEngine.enterToNext();
        this.handleDirectionsAndCommands();
    }

    private boolean checkIfCurrentDungeonIsRevealed() {
        return this.mapRevealed;
    }

    public boolean ableToUseMenuCommands() {
        if ("OpenWorld".equals(Main.getSavedPlace()) || "Village".equals(Main.getSavedPlace()) || "SpawnRoom".equals(Main.getSavedPlace())) {
            return true;
        }
        return !(this.map[currentPostion[0]][currentPostion[1]] == 1 && this.roomsBeenTo[currentPostion[0]][currentPostion[1]] == 0) && this.map[currentPostion[0]][currentPostion[1]] != 6;
    }

    public void dungeonIntroText() throws InterruptedException {
        TextEngine.printWithDelays("You have entered " + redColor + "The " + currentDungeon + resetColor + "!", false);
        TextEngine.printWithDelays("To beat the dungeon you must beat the " + redColor + currentBoss + resetColor + "!\nBe on the look out for treasure rooms! They hold some powerful loot.\nYou can always type help to see what commands you have available!\nGood Luck!", false);
        TextEngine.enterToNext();
        this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] = this.map[this.currentPostion[0]][this.currentPostion[1]];
        Main.loadSave();
    }

    public void miniBossSequence() throws InterruptedException {
        TextEngine.printWithDelays("You have entered a room with a mini boss", false);
        Player.changeHealth(Enemy.spawnEnemy(currentMiniBoss, 1));
        this.map[this.currentPostion[0]][this.currentPostion[1]] = 7;
        Main.loadSave();
    }

    public void fairySequence() throws InterruptedException {
        this.map[this.currentPostion[0]][this.currentPostion[1]] = 10;
        Main.loadSave();
    }

    public void keyRoomSequence() throws InterruptedException {
        if (numberOfEnemies < 2) {
            numberOfEnemies = 2;
        }
        TextEngine.printWithDelays("You have entered a room with " + numberOfEnemies + " " + redColor + enemyType + "s " + resetColor + "in this room!\nYou were ambushed!", false);
        TextEngine.printWithDelays("They seem to be trying to protect something...", false);
        TextEngine.printWithDelays("What is your command? " + yellowColor + "fight" + resetColor + " or " + yellowColor + "run" + resetColor, true);
        while (true) {
            command = Room.console.readLine();
            switch (command) {
                case "fight" -> {
                    Player.changeHealth(Enemy.spawnEnemy(enemyType, numberOfEnemies));
                    this.map[this.currentPostion[0]][this.currentPostion[1]] = 5;
                    Main.loadSave();
                }
                case "run" -> {
                    Player.changeHealth(Enemy.runSpawnEnemy(enemyType, numberOfEnemies));
                    int[] buffer = this.currentPostion.clone();
                    this.currentPostion = this.lastPosition.clone(); // Save the current position before moving
                    this.lastPosition = buffer.clone();
                    Main.loadSave();
                }
                default -> {
                    defaultDungeonArgs(command);
                }
            }
        }
    }

    public void fightRandomEnemies() throws InterruptedException {
        if (numberOfEnemies == 0) {
            this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] = this.map[this.currentPostion[0]][this.currentPostion[1]];
            Main.loadSave();
            return;
        }
        if (numberOfEnemies > 1) {
            TextEngine.printWithDelays("You have entered a room with " + numberOfEnemies + " " + redColor + enemyType + "s" + resetColor + " in this room!\nYou were ambushed!", false);
        } else {
            TextEngine.printWithDelays("You have entered a room with a " + redColor + enemyType + resetColor + " and were ambushed!", false);
        }
        TextEngine.printWithDelays("What is your command? " + yellowColor + "fight" + resetColor + " or " + yellowColor + "run" + resetColor, true);
        while (true) {
            command = Room.console.readLine();
            switch (command) {
                case "fight" -> {
                    Player.changeHealth(Enemy.spawnEnemy(enemyType, numberOfEnemies));
                    this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] = this.map[this.currentPostion[0]][this.currentPostion[1]];
                    Main.loadSave();
                    return;
                }
                case "run" -> {
                    Player.changeHealth(Enemy.runSpawnEnemy(enemyType, numberOfEnemies));
                    int[] buffer = this.currentPostion.clone();
                    this.currentPostion = this.lastPosition.clone(); // Save the current position before moving
                    this.lastPosition = buffer.clone();
                    Main.loadSave();
                }
                default -> {
                    defaultDungeonArgs(command);
                }
            }
        }
    }

    public void heartContainerRoom() throws InterruptedException {
        if (hasItemInRoom("heart container", 1)) {
            this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] = this.map[this.currentPostion[0]][this.currentPostion[1]];
        } else {
            int[] buffer = this.currentPostion.clone();
            this.currentPostion = this.lastPosition.clone(); // Save the current position before moving
            this.lastPosition = buffer.clone();
            Main.loadSave();
        }
    }

    public void keyRoom() throws InterruptedException {
        if (hasItemInRoom("key", 1)) {
            this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] = this.map[this.currentPostion[0]][this.currentPostion[1]];
        } else {
            int[] buffer = this.currentPostion.clone();
            this.currentPostion = this.lastPosition.clone(); // Save the current position before moving
            this.lastPosition = buffer.clone();
            Main.loadSave();
        }
    }

    public void fairyRoom() throws InterruptedException {
        TextEngine.printWithDelays("You have entered a room with a mystical fairy", false);
        TextEngine.printWithDelays("The fairy has granted you a wish of healing?", false);
        TextEngine.printWithDelays("Do you want to take it? " + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor, true);
        while (true) {
            command = Room.console.readLine();
            switch (command) {
                case "yes" -> {
                    Player.fairyHeal();
                    this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] = this.map[this.currentPostion[0]][this.currentPostion[1]];
                    Main.loadSave();
                }
                case "no" -> {
                    int[] buffer = this.currentPostion.clone();
                    this.currentPostion = this.lastPosition.clone(); // Save the current position before moving
                    this.lastPosition = buffer.clone();
                    Main.loadSave();
                }
                default -> {
                    defaultDungeonArgs(command);
                }
            }
        }
    }

    public boolean testIfBossRoom(int check) {
        if (check != 0) {
            return check == 8;
        }
        return false;
    }

    public void itemRoom(List<String> localItems) throws InterruptedException {
        if (localItems != null && !localItems.isEmpty()) {
            String randomItem = localItems.get(rand.nextInt(localItems.size()));
            if (hasChestInRoom(randomItem, 1)) {
                this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] = this.map[this.currentPostion[0]][this.currentPostion[1]];
                // Ensure items is a mutable collection
                List<String> mutableItems = new ArrayList<>(this.items);
                mutableItems.remove(randomItem);
                this.items = mutableItems;
                Main.loadSave();
            } else {
                int[] buffer = this.currentPostion.clone();
                this.currentPostion = this.lastPosition.clone(); // Save the current position before moving
                this.lastPosition = buffer.clone();
                Main.loadSave();
            }
        } else {
            fairySequence();
        }
    }

    public void bossRoom() throws InterruptedException {
        TextEngine.printWithDelays("You have entered the boss room", false);
        Player.changeHealth(Enemy.spawnEnemy(currentBoss, 1));
        TextEngine.printWithDelays("You have defeated the boss and completed the dungeon!", false);
        TextEngine.enterToNext();
        this.roomsBeenTo[this.currentPostion[0]][this.currentPostion[1]] = this.map[this.currentPostion[0]][this.currentPostion[1]];
        if (!this.completed) {
            completedDungeons++;
            this.completed = true;
            if (completedDungeons == 8) {
                Main.gameComplete = true;
            }
        }
        this.lastPosition = null;
        OpenWorld.startRoom();
    }

    public String directionsInString(ArrayList<String> list) {
        StringBuilder sb = new StringBuilder();
        for (Object item : list) {
            sb.append(yellowColor).append(item.toString()).append(resetColor);
            sb.append(", ");
        }
        // Remove the last comma and space
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public boolean getVisited() {
        return this.visited;
    }

    public int[][] getMap() {
        return this.map;
    }

    public int[][] getRoomsBeenTo() {
        return this.roomsBeenTo;
    }

    public List<String> getItems() {
        return this.items;
    }

    public boolean getMapRevealed() {
        return this.mapRevealed;
    }

    public int[] getCurrentPosition() {
        return this.currentPostion;
    }

    public int[] getLastPosition() {
        return this.lastPosition;
    }

    public void setCompleted(boolean localCompleted) {
        this.completed = localCompleted;
    }

    public void setVisited(boolean localVisited) {
        this.visited = localVisited;
    }

    public void setMap(int[][] matrix) {
        this.map = matrix;
    }

    public void setRoomsBeenTo(int[][] localRoomsBeenTo) {
        this.roomsBeenTo = localRoomsBeenTo;
    }

    public void setItems(List<String> localItems) {
        this.items = localItems;
    }

    public void setMapRevealed(boolean localMapRevealed) {
        this.mapRevealed = localMapRevealed;
    }

    public void setCurrentPosition(int[] localCurrentPosition) {
        this.currentPostion = localCurrentPosition;
    }

    public void setLastPosition(int[] localLastPosition) {
        this.lastPosition = localLastPosition;
    }
}
