
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

    public static int[] currentPlayerPosition = null;
    public static int[] lastPosition = null; // Variable to store the last position

    public static List<String> missedItems = new ArrayList<>();

    private String currentMiniBoss;
    private String currentBoss;

    private int[] spawnPosition;
    private int[] bossRoom;
    private String direction;
    private int[] availableMove;
    private ArrayList<String> directionsString;
    private List<String> enemies;

    private int[][] roomsBeenTo;
    private List<String> items;
    private List<String> itemsOrigin;
    private boolean completed = false;
    private boolean visited = false;
    private boolean mapRevealed;
    private int enemiesCount;

    private int[][] map;

    public Dungeon(int size, ArrayList<String> enemies, List<String> items, String miniBoss, String boss, int enemiesCount) {
        this.map = DungeonGenerator.generateAndReturnMatrix(size);
        spawnPosition = DungeonGenerator.findValue(this.map, 9);
        bossRoom = DungeonGenerator.findValue(this.map, 8);
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
            currentPlayerPosition = DungeonGenerator.findValue(this.map, 9);
        }
        if (!roomSave.equals(Main.getSavedPlace())) {  //make this dynamic
            currentPlayerPosition = DungeonGenerator.findValue(this.map, 9);
        }
        spawnPosition = DungeonGenerator.findValue(this.map, 9);
        room = roomSave;  //make this dynamic
        Main.checkSave(room);
        Main.screenRefresh();
        currentDungeon = current; //make this dynamic
        GameSaveSerialization.saveGame();
        this.startRooms();
    }

    public void fresh() { //fresh
        this.mapRevealed = false;
        this.visited = false;
        this.completed = false;
        this.spawnPosition = DungeonGenerator.findValue(this.map, 9);
        this.bossRoom = DungeonGenerator.findValue(this.map, 8);
        currentPlayerPosition = spawnPosition;
        this.roomsBeenTo = DungeonGenerator.createRoomsBeenTo(this.map.length);
        lastPosition = spawnPosition.clone();
    }

    public void startRooms() throws InterruptedException {
        numberOfEnemies = rand.nextInt(enemiesCount);
        enemyType = enemies.get(rand.nextInt(enemies.size()));
        availableMove = null;
        Main.screenRefresh();
        drawRoom(this.map, this.roomsBeenTo, currentPlayerPosition[0], currentPlayerPosition[1], numberOfEnemies, this.mapRevealed);
        directionsString = new ArrayList<>();
        if (this.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 9 && this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            this.dungeonIntroText();
        }
        if (this.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 2 && this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            this.itemRoom(items);
        }
        if (this.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 10 && this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            this.fairyRoom();
        }
        if (this.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 && this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            this.keyRoomSequence();
        }
        if (this.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 5 && this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            this.keyRoom();
        }
        if (this.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 7 && this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            this.heartContainerRoom();
        }
        if (this.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            this.fightRandomEnemies();
        }
        if (this.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 6) {
            this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = this.map[currentPlayerPosition[0]][currentPlayerPosition[1]];
            this.dungeonShop();
        }
        if (this.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 && this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            this.miniBossSequence();
        }
        if (this.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 8 && this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            this.bossRoom();
        }
        handleDirectionsAndCommands();
    }

    public void handleDirectionsAndCommands() throws InterruptedException {
        Main.screenRefresh();
        drawRoom(this.map, this.roomsBeenTo, currentPlayerPosition[0], currentPlayerPosition[1], 0, this.mapRevealed);
        this.availableMove = DungeonGenerator.getDirections(this.map, currentPlayerPosition[0], currentPlayerPosition[1]);
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
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = this.map[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[0] -= 1;
                        Main.loadSave();
                    } else {
                        this.defaultDungeonArgs(this.direction.toLowerCase());
                    }
                }
                case "east", "2" -> {
                    if (this.directionsString.contains(this.direction.toLowerCase())) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = this.map[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[1] += 1;
                        Main.loadSave();
                    } else {
                        this.defaultDungeonArgs(this.direction.toLowerCase());
                    }
                }
                case "south", "3" -> {
                    if (this.directionsString.contains(this.direction.toLowerCase())) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = this.map[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[0] += 1;
                        Main.loadSave();
                    } else {
                        this.defaultDungeonArgs(this.direction.toLowerCase());
                    }
                }
                case "west", "4" -> {
                    if (this.directionsString.contains(this.direction.toLowerCase())) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = this.map[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[1] -= 1;
                        Main.loadSave();
                    } else {
                        this.defaultDungeonArgs(this.direction.toLowerCase());
                    }
                }
                case "boss room", "5" -> {
                    if (this.directionsString.contains(this.direction.toLowerCase()) && this.confirmBossContinue()) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = this.map[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition = DungeonGenerator.findValue(this.map, 8);
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
                    lastPosition = null;
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
                    DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(this.map, this.roomsBeenTo, currentPlayerPosition, this.mapRevealed);
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

    public static boolean ableToUseMenuCommands() {
        if ("OpenWorld".equals(Main.getSavedPlace()) || "Village".equals(Main.getSavedPlace()) || "SpawnRoom".equals(Main.getSavedPlace())) {
            return true;
        }
        switch (currentDungeon) {
            case "Meadow" -> {
                return (!(Main.MeadowDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || Main.MeadowDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (Main.MeadowDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && Main.MeadowDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Dark Forest" -> {
                return (!(Main.DarkForestDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || Main.DarkForestDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (Main.DarkForestDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && Main.DarkForestDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Mountain Cave" -> {
                return (!(Main.MountainCaveDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || Main.MountainCaveDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (Main.MountainCaveDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && Main.MountainCaveDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Mountain Top" -> {
                return (!(Main.MountainTopDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || Main.MountainTopDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (Main.MountainTopDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && Main.MountainTopDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Desert Oasis" -> {
                return (!(Main.DesertOasisDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || Main.DesertOasisDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (Main.DesertOasisDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && Main.DesertOasisDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Desert Plains" -> {
                return (!(Main.DesertPlainsDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || Main.DesertPlainsDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (Main.DesertPlainsDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && Main.DesertPlainsDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Desert Pyramid" -> {
                return (!(Main.DesertPyramidDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || Main.DesertPyramidDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (Main.DesertPyramidDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && Main.DesertPyramidDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Ocean Kingdom" -> {
                return (!(Main.OceanKingdomDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || Main.OceanKingdomDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (Main.OceanKingdomDungeon.map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && Main.OceanKingdomDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            default -> {
                return true;
            }
        }
    }

    public void dungeonIntroText() throws InterruptedException {
        TextEngine.printWithDelays("You have entered " + redColor + "The " + currentDungeon + resetColor + "!", false);
        TextEngine.printWithDelays("To beat the dungeon you must beat the " + redColor + currentBoss + resetColor + "!\nBe on the look out for treasure rooms! They hold some powerful loot.\nYou can always type help to see what commands you have available!\nGood Luck!", false);
        TextEngine.enterToNext();
        this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = this.map[currentPlayerPosition[0]][currentPlayerPosition[1]];
        Main.loadSave();
    }

    public void miniBossSequence() throws InterruptedException {
        TextEngine.printWithDelays("You have entered a room with a mini boss", false);
        Player.changeHealth(Enemy.spawnEnemy(currentMiniBoss, 1));
        this.map[currentPlayerPosition[0]][currentPlayerPosition[1]] = 7;
        Main.loadSave();
    }

    public void fairySequence() throws InterruptedException {
        this.map[currentPlayerPosition[0]][currentPlayerPosition[1]] = 10;
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
                    this.map[currentPlayerPosition[0]][currentPlayerPosition[1]] = 5;
                    Main.loadSave();
                }
                case "run" -> {
                    Player.changeHealth(Enemy.runSpawnEnemy(enemyType, numberOfEnemies));
                    int[] buffer = currentPlayerPosition.clone();
                    currentPlayerPosition = lastPosition.clone(); // Save the current position before moving
                    lastPosition = buffer.clone();
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
            this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = this.map[currentPlayerPosition[0]][currentPlayerPosition[1]];
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
                    this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = this.map[currentPlayerPosition[0]][currentPlayerPosition[1]];
                    Main.loadSave();
                    return;
                }
                case "run" -> {
                    Player.changeHealth(Enemy.runSpawnEnemy(enemyType, numberOfEnemies));
                    int[] buffer = currentPlayerPosition.clone();
                    currentPlayerPosition = lastPosition.clone(); // Save the current position before moving
                    lastPosition = buffer.clone();
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
            this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = this.map[currentPlayerPosition[0]][currentPlayerPosition[1]];
        } else {
            int[] buffer = currentPlayerPosition.clone();
            currentPlayerPosition = lastPosition.clone(); // Save the current position before moving
            lastPosition = buffer.clone();
            Main.loadSave();
        }
    }

    public void keyRoom() throws InterruptedException {
        if (hasItemInRoom("key", 1)) {
            this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = this.map[currentPlayerPosition[0]][currentPlayerPosition[1]];
        } else {
            int[] buffer = currentPlayerPosition.clone();
            currentPlayerPosition = lastPosition.clone(); // Save the current position before moving
            lastPosition = buffer.clone();
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
                    this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = this.map[currentPlayerPosition[0]][currentPlayerPosition[1]];
                    Main.loadSave();
                }
                case "no" -> {
                    int[] buffer = currentPlayerPosition.clone();
                    currentPlayerPosition = lastPosition.clone(); // Save the current position before moving
                    lastPosition = buffer.clone();
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
        if (!localItems.isEmpty()) {
            String randomItem = localItems.get(rand.nextInt(localItems.size()));
            if (hasChestInRoom(randomItem, 1)) {
                this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = this.map[currentPlayerPosition[0]][currentPlayerPosition[1]];
                this.items.remove(randomItem);
                Main.loadSave();
            } else {
                int[] buffer = currentPlayerPosition.clone();
                currentPlayerPosition = lastPosition.clone(); // Save the current position before moving
                lastPosition = buffer.clone();
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
        this.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = this.map[currentPlayerPosition[0]][currentPlayerPosition[1]];
        if (!this.completed) {
            completedDungeons++;
            this.completed = true;
            if (completedDungeons == 8) {
                Main.gameComplete = true;
            }
        }
        lastPosition = null;
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

    public void drawRoom(int[][] localDungeon, int[][] visitedRoom, int x, int y, int numberofEnemies, boolean revealed) {
        int[] moves = DungeonGenerator.getDirections(localDungeon, x, y);
        //default room layout
        String[][] room = {
            {"┌", "─", "─", "─", "─", "─", "─", "─", "─", "─", "─", "─", "─", "─", "┐"},
            {"|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"},
            {"|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"},
            {"|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"},
            {"└", "─", "─", "─", "─", "─", "─", "─", "─", "─", "─", "─", "─", "─", "┘"}
        };
        //render room objects
        if (visitedRoom[x][y] > 0) {
            room[2][7] = "P"; // Player
        } else {
            switch (localDungeon[x][y]) {
                case 1, 3 -> {
                    room[2][7] = "P"; // Player
                    List<int[]> emptySpots = new ArrayList<>();
                    Random random = new Random();
                    // Collect all empty spots in the room
                    for (int row = 0; row < room.length; row++) {
                        for (int col = 0; col < room[row].length; col++) {
                            if (room[row][col].equals(" ")) { // Assuming ' ' represents an empty spot
                                emptySpots.add(new int[]{row, col});
                            }
                        }
                    }
                    // Place enemies at random empty spots
                    if (localDungeon[x][y] == 3) {
                        if (numberofEnemies < 2) {
                            numberofEnemies = 2;
                        }
                    }
                    for (int i = 0; i < numberofEnemies && !emptySpots.isEmpty(); i++) {
                        int randomIndex = random.nextInt(emptySpots.size());
                        int[] spot = emptySpots.remove(randomIndex);
                        String enemyRender;
                        switch (Dungeon.enemyType) {
                            case "Goblin" ->
                                enemyRender = "G";
                            case "Orc" ->
                                enemyRender = "O";
                            case "Troll" ->
                                enemyRender = "T";
                            case "Bandit" ->
                                enemyRender = "B";
                            case "Spider" ->
                                enemyRender = "S";
                            case "Giant Rat" ->
                                enemyRender = "R";
                            case "Skeleton" ->
                                enemyRender = "S";
                            case "Zombie" ->
                                enemyRender = "Z";
                            case "Ghost" ->
                                enemyRender = "G";
                            case "Demon" ->
                                enemyRender = "D";
                            case "Vampire" ->
                                enemyRender = "V";
                            case "Werewolf" ->
                                enemyRender = "W";
                            case "Witch" ->
                                enemyRender = "W";
                            case "Giant" ->
                                enemyRender = "G";
                            case "Mummy" ->
                                enemyRender = "M";
                            case "Slime" ->
                                enemyRender = "S";
                            case "Mimic" ->
                                enemyRender = "M";
                            case "Gargoyle" ->
                                enemyRender = "G";
                            case "Sea Serpent" ->
                                enemyRender = "S";
                            case "Sea Monster" ->
                                enemyRender = "M";
                            case "Sea Witch" ->
                                enemyRender = "W";
                            case "Sea Dragon" ->
                                enemyRender = "D";
                            case "Sea Giant" ->
                                enemyRender = "G";
                            case "Scorpion" ->
                                enemyRender = "S";
                            case "Mountain Lion" ->
                                enemyRender = "M";
                            case "Barbarian" ->
                                enemyRender = "B";
                            case "Shark" ->
                                enemyRender = "S";
                            case "Pirate" ->
                                enemyRender = "P";
                            case "Minotaur" ->
                                enemyRender = "M";
                            default ->
                                enemyRender = "E";
                        }
                        room[spot[0]][spot[1]] = redColor + enemyRender + resetColor; // Enemy
                    }
                }
                case 2 -> {
                    room[2][5] = "P"; // Item Room
                    room[2][7] = greenColor + "C" + resetColor; // Item
                }
                case 5 -> {
                    room[2][5] = "P"; // key Room
                    room[2][7] = greenColor + "K" + resetColor; // key
                }
                case 7 -> {
                    room[2][5] = "P"; // heart container Room
                    room[2][7] = redColor + "H" + resetColor; // heart container
                }
                case 6 -> {
                    room[2][5] = "P"; // Shop Room
                    room[2][7] = greenColor + "$" + resetColor; // Shop
                }
                case 4 -> {
                    room[2][5] = "P"; // Mini Boss Room
                    String miniBossRender;
                    switch (this.currentMiniBoss) {
                        case "Golem" ->
                            miniBossRender = "G";
                        case "Forest Guardian" ->
                            miniBossRender = "F";
                        case "Elemental" ->
                            miniBossRender = "E";
                        case "Minotaur" ->
                            miniBossRender = "M";
                        case "Sphinx" ->
                            miniBossRender = "S";
                        case "Cyclops" ->
                            miniBossRender = "C";
                        case "Medusa" ->
                            miniBossRender = "M";
                        case "Leviathan" ->
                            miniBossRender = "L";
                        default ->
                            miniBossRender = "M";
                    }
                    room[2][7] = redColor + miniBossRender + resetColor; // Mini Boss
                }
                case 8 -> {
                    room[2][5] = "P"; // Boss Room
                    String bossRender;
                    switch (this.currentBoss) {
                        case "Forest Giant" ->
                            bossRender = "F";
                        case "Forest Spirit" ->
                            bossRender = "S";
                        case "Wyvern" ->
                            bossRender = "W";
                        case "Ice Dragon" ->
                            bossRender = "I";
                        case "Phoenix" ->
                            bossRender = "P";
                        case "Giant Scorpion" ->
                            bossRender = "S";
                        case "Giant Sand Worm" ->
                            bossRender = "W";
                        case "Kraken" ->
                            bossRender = "K";
                        default ->
                            bossRender = "B";
                    }
                    room[2][7] = redColor + bossRender + resetColor; // Boss
                }
                case 10 -> {
                    room[2][5] = "P"; // fairy Room
                    room[2][7] = pinkColor + "F" + resetColor; // fairy
                }
                default -> {
                    room[2][7] = "P"; // Default
                }
            }
        }
        //get the last postinon to render the last position icon
        if (Dungeon.lastPosition != null) {
            if (x - 1 == Dungeon.lastPosition[0] && y == Dungeon.lastPosition[1]) {
                moves[0] = 15;
            } else if (x + 1 == Dungeon.lastPosition[0] && y == Dungeon.lastPosition[1]) {
                moves[1] = 15;
            } else if (x == Dungeon.lastPosition[0] && y - 1 == Dungeon.lastPosition[1]) {
                moves[2] = 15;
            } else if (x == Dungeon.lastPosition[0] && y + 1 == Dungeon.lastPosition[1]) {
                moves[3] = 15;
            }
        }
        // render all 4 possible moves with ajacent rooms icon
        switch (moves[0]) {
            case 1, 3, 9 -> {
                if (moves[0] == 3) {
                    if (revealed) {
                        room[0][7] = greenColor + "K" + resetColor;
                    } else {
                        room[0][7] = " ";
                    }
                } else {
                    room[0][7] = " ";
                }
                room[0][6] = " ";
                room[0][8] = " ";
            }
            case 2, 5, 7 -> {
                if (visitedRoom[x - 1][y] > 0) {
                    room[0][7] = " ";
                } else {
                    if (revealed) {
                        if (moves[0] == 5) {
                            room[0][7] = greenColor + "K" + resetColor;
                        } else {
                            room[0][7] = greenColor + "I" + resetColor;
                        }
                    } else {
                        room[0][7] = greenColor + "?" + resetColor;
                    }
                }
                room[0][6] = " ";
                room[0][8] = " ";
            }
            case 4 -> {
                if (visitedRoom[x - 1][y] > 0) {
                    room[0][7] = " ";
                } else {
                    room[0][7] = redColor + "!" + resetColor;
                }
                room[0][6] = " ";
                room[0][8] = " ";
            }
            case 8 -> {
                if (visitedRoom[x - 1][y] > 0) {
                    room[0][7] = " ";
                } else {
                    room[0][7] = redColor + "B" + resetColor;
                }
                room[0][6] = " ";
                room[0][8] = " ";
            }
            case 15 -> {
                room[0][7] = yellowColor + "*" + resetColor;
                room[0][6] = " ";
                room[0][8] = " ";
            }
            case 6 -> {
                room[0][7] = greenColor + "$" + resetColor;
                room[0][6] = " ";
                room[0][8] = " ";
            }
            case 10 -> {
                if (visitedRoom[x - 1][y] > 0) {
                    room[0][7] = " ";
                } else {
                    room[0][7] = pinkColor + "F" + resetColor;
                }
                room[0][6] = " ";
                room[0][8] = " ";
            }
            default ->
                room[0][7] = "─";
        }
        switch (moves[1]) {
            case 1, 3, 9 -> {
                if (moves[1] == 3) {
                    if (revealed) {
                        room[4][7] = greenColor + "K" + resetColor;
                    } else {
                        room[4][7] = " ";
                    }
                } else {
                    room[4][7] = " ";
                }
                room[4][6] = " ";
                room[4][8] = " ";
            }
            case 2, 5, 7 -> {
                if (visitedRoom[x + 1][y] > 0) {
                    room[4][7] = " ";
                } else {
                    if (revealed) {
                        if (moves[1] == 5) {
                            room[4][7] = greenColor + "K" + resetColor;
                        } else {
                            room[4][7] = greenColor + "I" + resetColor;
                        }
                    } else {
                        room[4][7] = greenColor + "?" + resetColor;
                    }
                }
                room[4][6] = " ";
                room[4][8] = " ";
            }
            case 4 -> {
                if (visitedRoom[x + 1][y] > 0) {
                    room[4][7] = " ";
                } else {
                    room[4][7] = redColor + "!" + resetColor;
                }
                room[4][6] = " ";
                room[4][8] = " ";
            }
            case 8 -> {
                if (visitedRoom[x + 1][y] > 0) {
                    room[4][7] = " ";
                } else {
                    room[4][7] = redColor + "B" + resetColor;
                }
                room[4][6] = " ";
                room[4][8] = " ";
            }
            case 15 -> {
                room[4][7] = yellowColor + "*" + resetColor;
                room[4][6] = " ";
                room[4][8] = " ";
            }
            case 6 -> {
                room[4][7] = greenColor + "$" + resetColor;
                room[4][6] = " ";
                room[4][8] = " ";
            }
            case 10 -> {
                if (visitedRoom[x + 1][y] > 0) {
                    room[4][7] = " ";
                } else {
                    room[4][7] = pinkColor + "F" + resetColor;
                }
                room[4][6] = " ";
                room[4][8] = " ";
            }
            default ->
                room[4][7] = "─";
        }
        switch (moves[2]) {
            case 1, 3, 9 -> {
                if (moves[2] == 3) {
                    if (revealed) {
                        room[2][0] = greenColor + "K" + resetColor;
                    } else {
                        room[2][0] = " ";
                    }
                } else {
                    room[2][0] = " ";
                }
            }
            case 2, 5, 7 -> {
                if (visitedRoom[x][y - 1] > 0) {
                    room[2][0] = " ";
                } else {
                    if (revealed) {
                        if (moves[2] == 5) {
                            room[2][0] = greenColor + "K" + resetColor;
                        } else {
                            room[2][0] = greenColor + "I" + resetColor;
                        }
                    } else {
                        room[2][0] = greenColor + "?" + resetColor;
                    }
                }
            }
            case 4 -> {
                if (visitedRoom[x][y - 1] > 0) {
                    room[2][0] = " ";
                } else {
                    room[2][0] = redColor + "!" + resetColor;
                }
            }
            case 8 -> {
                if (visitedRoom[x][y - 1] > 0) {
                    room[2][0] = " ";
                } else {
                    room[2][0] = redColor + "B" + resetColor;
                }
            }
            case 15 -> {
                room[2][0] = yellowColor + "*" + resetColor;
            }
            case 6 -> {
                room[2][0] = greenColor + "$" + resetColor;
            }
            case 10 -> {
                if (visitedRoom[x][y - 1] > 0) {
                    room[2][0] = " ";
                } else {
                    room[2][0] = pinkColor + "F" + resetColor;
                }
            }
            default ->
                room[2][0] = "│";
        }
        switch (moves[3]) {
            case 1, 3, 9 -> {
                if (moves[3] == 3) {
                    if (revealed) {
                        room[2][14] = greenColor + "K" + resetColor;
                    } else {
                        room[2][14] = " ";
                    }
                } else {
                    room[2][14] = " ";
                }
            }
            case 2, 5, 7 -> {
                if (visitedRoom[x][y + 1] > 0) {
                    room[2][14] = " ";
                } else {
                    if (revealed) {
                        if (moves[3] == 5) {
                            room[2][14] = greenColor + "K" + resetColor;
                        } else {
                            room[2][14] = greenColor + "I" + resetColor;
                        }
                    } else {
                        room[2][14] = greenColor + "?" + resetColor;
                    }
                }
            }
            case 4 -> {
                if (visitedRoom[x][y + 1] > 0) {
                    room[2][14] = " ";
                } else {
                    room[2][14] = redColor + "!" + resetColor;
                }
            }
            case 8 -> {
                if (visitedRoom[x][y + 1] > 0) {
                    room[2][14] = " ";
                } else {
                    room[2][14] = redColor + "B" + resetColor;
                }
            }
            case 15 -> {
                room[2][14] = yellowColor + "*" + resetColor;
            }
            case 6 -> {
                room[2][14] = greenColor + "$" + resetColor;
            }
            case 10 -> {
                if (visitedRoom[x][y + 1] > 0) {
                    room[2][14] = " ";
                } else {
                    room[2][14] = pinkColor + "F" + resetColor;
                }
            }
            default ->
                room[2][14] = "│";
        }

        // Print the room to the console
        for (int i = 0; i < 5; i++) {
            System.out.print("    ");
            for (int j = 0; j < 15; j++) {
                System.out.print(room[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /*
 * key
 * 1 = enemy rooms
 * 2 - item rooms
 * 3 - enemy key rooms
 * 4 - mini boss rooms
 * 5 - key rooms
 * 6 - shop rooms
 * 7 - heart container rooms
 * 8 - boss rooms
 * 9 - spawn room
 * 10 - fairy rooms
 * 15 - last position
     */
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
}
