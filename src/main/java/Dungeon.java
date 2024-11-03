
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

    private int[] currentPosition;
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
        if (Main.TESTING) {
            System.out.println("Running function: Dungeon Constructor on dungeon: " + boss);
        }
        this.map = map;
        this.spawnPosition = DungeonGenerator.findValue(this.map, 9);
        this.enemies = enemies;
        this.roomsBeenTo = DungeonGenerator.createRoomsBeenTo(this.map.length);
        this.items = items;
        this.itemsOrigin = items;
        this.currentMiniBoss = miniBoss;
        this.currentBoss = boss;
        this.enemiesCount = enemiesCount;
        if (Main.TESTING) {
            System.out.println(redColor + "Function Complete: Dungeon Constructor on dungeon: " + boss + resetColor);
        }
    }

    public void startRoom(String roomSave, String current) throws InterruptedException { //start room
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: startRoom");
        }
        if (!this.visited) {
            this.fresh();
            this.items = this.itemsOrigin;
            this.visited = true;
            this.currentPosition = DungeonGenerator.findValue(this.map, 9);
            this.roomsBeenTo = DungeonGenerator.createRoomsBeenTo(this.map.length);
            this.spawnPosition = DungeonGenerator.findValue(this.map, 9);
        }
        if (!roomSave.equals(Main.getSavedPlace())) {
            this.currentPosition = DungeonGenerator.findValue(this.map, 9);
        }
        room = roomSave;
        Main.checkSave(room);
        currentDungeon = current;
        GameSaveSerialization.saveGame();
        this.runRoom();
        if (Main.TESTING) {
            System.out.println(redColor + this.currentBoss + "Function Complete: startRoom" + resetColor);
        }
    }

    public void fresh() { //fresh
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: fresh");
        }
        this.mapRevealed = false;
        this.visited = false;
        this.completed = false;
        this.spawnPosition = DungeonGenerator.findValue(this.map, 9);
        this.currentPosition = this.spawnPosition;
        this.roomsBeenTo = DungeonGenerator.createRoomsBeenTo(this.map.length);
        this.lastPosition = this.spawnPosition.clone();
        if (Main.TESTING) {
            System.out.println(redColor + this.currentBoss + "Function Complete: fresh" + resetColor);
        }
    }

    public void runRoom() throws InterruptedException {
        numberOfEnemies = rand.nextInt(enemiesCount);
        enemyType = enemies.get(rand.nextInt(enemies.size()));
        availableMove = null;
        Main.screenRefresh();
        DungeonGenerator.drawRoom(this.map, this.roomsBeenTo, this.currentPosition[0], this.currentPosition[1], numberOfEnemies, this.mapRevealed, this.currentMiniBoss, this.currentBoss, this.lastPosition);
        int localCurrentRoom = this.map[this.currentPosition[0]][this.currentPosition[1]];
        boolean roomNotVisited = this.roomsBeenTo[this.currentPosition[0]][this.currentPosition[1]] == 0;
        if (localCurrentRoom == 9 && roomNotVisited) {
            this.dungeonIntroText();
        } else if (localCurrentRoom == 2 && roomNotVisited) {
            this.itemRoom(items);
        } else if (localCurrentRoom == 10 && roomNotVisited) {
            this.fairyRoom();
        } else if (localCurrentRoom == 3 && roomNotVisited) {
            this.keyRoomSequence();
        } else if (localCurrentRoom == 5 && roomNotVisited) {
            this.keyRoom();
        } else if (localCurrentRoom == 7 && roomNotVisited) {
            this.heartContainerRoom();
        } else if (localCurrentRoom == 1 && roomNotVisited) {
            this.fightRandomEnemies();
        } else if (localCurrentRoom == 6) {
            this.roomsBeenTo[this.currentPosition[0]][this.currentPosition[1]] = localCurrentRoom;
            this.dungeonShop();
        } else if (localCurrentRoom == 4 && roomNotVisited) {
            this.miniBossSequence();
        } else if (localCurrentRoom == 8 && roomNotVisited) {
            this.bossRoom();
        }
        handleDirectionsAndCommands();
        if (Main.TESTING) {
            System.out.println(redColor + this.currentBoss + "Function Complete: runRoom" + resetColor);
        }
    }

    public void handleDirectionsAndCommands() throws InterruptedException {
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: handleDirectionsAndCommands");
        }
        directionsString = new ArrayList<>();
        int localCurrentRoom = this.map[this.currentPosition[0]][this.currentPosition[1]];
        if (localCurrentRoom == 6) {
            Main.screenRefresh();
            DungeonGenerator.drawRoom(this.map, this.roomsBeenTo, this.currentPosition[0], this.currentPosition[1], numberOfEnemies, this.mapRevealed, this.currentMiniBoss, this.currentBoss, this.lastPosition);
        }
        this.availableMove = DungeonGenerator.getDirections(this.map, this.currentPosition[0], this.currentPosition[1]);
        if (this.completed) {
            TextEngine.printWithDelays("You have completed this dungeon. You can now type " + yellowColor + "leave" + resetColor + " to exit this dungeon.", false);
        }
        System.out.println("Type " + yellowColor + "map" + resetColor + " to see the map.");
        System.out.println();
        TextEngine.printWithDelays("You can move in the following directions: ", false);
        String[] directions = {"north", "south", "west", "east"};
        for (int i = 0; i < this.availableMove.length; i++) {
            if (this.availableMove[i] > 0) {
                if (this.testIfBossRoom(this.availableMove[i])) {
                    this.directionsString.add("boss room");
                } else {
                    this.directionsString.add(directions[i]);
                }
            }
        }
        TextEngine.printNoDelay(directionsInString(directionsString), true);
        while (true) {
            this.direction = Room.console.readLine();
            String directionLower = this.direction.toLowerCase().trim();
            if (!this.directionsString.contains(directionLower)) {
                this.defaultDungeonArgs(directionLower);
                continue;
            }
            this.lastPosition = this.currentPosition.clone(); // Save the current position before moving
            this.roomsBeenTo[this.currentPosition[0]][this.currentPosition[1]] = this.map[this.currentPosition[0]][this.currentPosition[1]];
            directionsString.clear();
            switch (directionLower) {
                case "north", "1" ->
                    this.currentPosition[0] -= 1;
                case "east", "2" ->
                    this.currentPosition[1] += 1;
                case "south", "3" ->
                    this.currentPosition[0] += 1;
                case "west", "4" ->
                    this.currentPosition[1] -= 1;
                case "boss room", "5" -> {
                    if (this.confirmBossContinue()) {
                        this.currentPosition = DungeonGenerator.findValue(this.map, 8);
                    } else {
                        this.defaultDungeonArgs(directionLower);
                        continue;
                    }
                }
                default -> {
                    this.defaultDungeonArgs(directionLower);
                    continue;
                }
            }
            break;
        }
        Main.loadSave();
        if (Main.TESTING) {
            System.out.println(redColor + this.currentBoss + "Function Complete: handleDirectionsAndCommands" + resetColor);
        }
    }

    public void defaultDungeonArgs(String data) throws InterruptedException { //default dungeon arguments
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: defaultDungeonArgs");
        }
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
                    DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(this.map, this.roomsBeenTo, this.currentPosition, this.mapRevealed);
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
                                break;
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
        if (Main.TESTING) {
            System.out.println(redColor + this.currentBoss + "Function Complete: defaultDungeonArgs" + resetColor);
        }
    }

    public static void resetAll() { //reset all dungeons
        if (Main.TESTING) {
            System.out.println("Running function: resetAll");
        }
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

    public boolean confirmBossContinue() throws InterruptedException {
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: confirmBossContinue");
        }
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
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: dungeonShop");
        }
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
                            break;
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
        if (Main.TESTING) {
            System.out.println(redColor + this.currentBoss + "Function Complete: dungeonShop" + resetColor);
        }
    }

    private void buyMultiple(String type, int cost) throws InterruptedException { //buy multiple clause for certain items in village shop
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: buyMultiple");
        }
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
                        break;
                    } else {
                        TextEngine.printWithDelays("You do not have enough space in your inventory to buy " + command + " " + type + "s", false);
                        TextEngine.enterToNext();
                        keepShopping();
                        break;
                    }
                } else {
                    switch (command) {
                        case "0" -> {
                            TextEngine.printWithDelays("You did not buy any " + type + "s.", false);
                            TextEngine.enterToNext();
                            keepShopping();
                            break;
                        }
                        case "1" -> {
                            TextEngine.printWithDelays("You do not have enough gold to buy a " + command, false);
                            TextEngine.enterToNext();
                            keepShopping();
                            break;
                        }
                        default -> {
                            TextEngine.printWithDelays("You do not have enough gold to buy " + command + " potions", false);
                            TextEngine.enterToNext();
                            keepShopping();
                            break;
                        }
                    }
                }
            } else {
                Main.invalidCommand();
                keepShopping();
                break;
            }
        }
        if (Main.TESTING) {
            System.out.println(redColor + this.currentBoss + "Function Complete: buyMultiple" + resetColor);
        }
    }

    private void keepShopping() throws InterruptedException { //keep shopping
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: keepShopping");
        }
        TextEngine.printWithDelays("Would you like to keep shopping? " + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor, true);
        while (true) {
            command = console.readLine();
            switch (command.toLowerCase().trim()) {
                case "yes" -> {
                    dungeonShop();
                    break;
                }
                case "no" -> {
                    leave();
                    break;
                }
                default ->
                    defaultDungeonArgs(command.toLowerCase());
            }
        }
    }

    private void useMagicMap() throws InterruptedException {
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: useMagicMap");
        }
        TextEngine.printWithDelays("You use the magic map...\nIt revealed the layout of the Dungeon!", false);
        TextEngine.enterToNext();
        this.mapRevealed = true;
        if (Main.TESTING) {
            System.out.println(redColor + this.currentBoss + "Function Complete: useMagicMap" + resetColor);
        }
    }

    private void leave() throws InterruptedException {
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: leave");
        }
        TextEngine.printWithDelays("You leave the shop and return to the Dungeon.", false);
        TextEngine.enterToNext();
        this.handleDirectionsAndCommands();
        if (Main.TESTING) {
            System.out.println(redColor + this.currentBoss + "Function Complete: leave" + resetColor);
        }
    }

    private boolean checkIfCurrentDungeonIsRevealed() {
        return this.mapRevealed;
    }

    public boolean ableToUseMenuCommands() {
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: ableToUseMenuCommands");
        }
        if ("OpenWorld".equals(Main.getSavedPlace()) || "Village".equals(Main.getSavedPlace()) || "SpawnRoom".equals(Main.getSavedPlace())) {
            return true;
        }
        boolean inEnemyRoom = (this.map[this.currentPosition[0]][this.currentPosition[1]] == 1 && this.roomsBeenTo[currentPosition[0]][currentPosition[1]] == 0);
        boolean inEnemyKeyRoom = (this.map[this.currentPosition[0]][this.currentPosition[1]] == 3 && this.roomsBeenTo[currentPosition[0]][currentPosition[1]] == 0);
        return !(inEnemyRoom || inEnemyKeyRoom);
    }

    public void dungeonIntroText() throws InterruptedException {
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: dungeonIntroText");
        }
        TextEngine.printWithDelays("You have entered " + redColor + "The " + currentDungeon + resetColor + "!", false);
        TextEngine.printWithDelays("To beat the dungeon you must beat the " + redColor + currentBoss + resetColor + "!\nBe on the look out for treasure rooms! They hold some powerful loot.\nYou can always type help to see what commands you have available!\nGood Luck!", false);
        TextEngine.enterToNext();
        this.roomsBeenTo[this.currentPosition[0]][this.currentPosition[1]] = this.map[this.currentPosition[0]][this.currentPosition[1]];
        Main.loadSave();
        if (Main.TESTING) {
            System.out.println(redColor + this.currentBoss + "Function Complete: dungeonIntroText" + resetColor);
        }
    }

    public void miniBossSequence() throws InterruptedException {
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: miniBossSequence");
        }
        TextEngine.printWithDelays("You have entered a room with a mini boss", false);
        Player.changeHealth(Enemy.spawnEnemy(currentMiniBoss, 1));
        this.map[this.currentPosition[0]][this.currentPosition[1]] = 7;
        Main.loadSave();
        if (Main.TESTING) {
            System.out.println(redColor + this.currentBoss + "Function Complete: miniBossSequence" + resetColor);
        }
    }

    public void fairySequence() throws InterruptedException {
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: fairySequence");
        }
        this.map[this.currentPosition[0]][this.currentPosition[1]] = 10;
        Main.loadSave();
        if (Main.TESTING) {
            System.out.println(redColor + this.currentBoss + "Function Complete: fairySequence" + resetColor);
        }
    }

    public void keyRoomSequence() throws InterruptedException {
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: keyRoomSequence");
        }
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
                    this.map[this.currentPosition[0]][this.currentPosition[1]] = 5;
                    Main.loadSave();
                    break;
                }
                case "run" -> {
                    Player.changeHealth(Enemy.runSpawnEnemy(enemyType, numberOfEnemies));
                    int[] buffer = this.currentPosition.clone();
                    this.currentPosition = this.lastPosition.clone(); // Save the current position before moving
                    this.lastPosition = buffer.clone();
                    Main.loadSave();
                    break;
                }
                default -> {
                    defaultDungeonArgs(command);
                }
            }
        }
    }

    public void fightRandomEnemies() throws InterruptedException {
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: fightRandomEnemies");
        }
        if (numberOfEnemies == 0) {
            this.roomsBeenTo[this.currentPosition[0]][this.currentPosition[1]] = this.map[this.currentPosition[0]][this.currentPosition[1]];
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
                    this.roomsBeenTo[this.currentPosition[0]][this.currentPosition[1]] = this.map[this.currentPosition[0]][this.currentPosition[1]];
                    Main.loadSave();
                    break;
                }
                case "run" -> {
                    Player.changeHealth(Enemy.runSpawnEnemy(enemyType, numberOfEnemies));
                    int[] buffer = this.currentPosition.clone();
                    this.currentPosition = this.lastPosition.clone(); // Save the current position before moving
                    this.lastPosition = buffer.clone();
                    Main.loadSave();
                    break;
                }
                default -> {
                    defaultDungeonArgs(command);
                }
            }
        }

    }

    public void heartContainerRoom() throws InterruptedException {
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: heartContainerRoom");
        }
        if (hasItemInRoom("heart container", 1)) {
            this.roomsBeenTo[this.currentPosition[0]][this.currentPosition[1]] = this.map[this.currentPosition[0]][this.currentPosition[1]];
        } else {
            int[] buffer = this.currentPosition.clone();
            this.currentPosition = this.lastPosition.clone(); // Save the current position before moving
            this.lastPosition = buffer.clone();
            Main.loadSave();
        }
    }

    public void keyRoom() throws InterruptedException {
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: keyRoom");
        }
        if (hasItemInRoom("key", 1)) {
            this.roomsBeenTo[this.currentPosition[0]][this.currentPosition[1]] = this.map[this.currentPosition[0]][this.currentPosition[1]];
        } else {
            int[] buffer = this.currentPosition.clone();
            this.currentPosition = this.lastPosition.clone(); // Save the current position before moving
            this.lastPosition = buffer.clone();
            Main.loadSave();
        }
    }

    public void fairyRoom() throws InterruptedException {
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: fairyRoom");
        }
        TextEngine.printWithDelays("You have entered a room with a mystical fairy", false);
        TextEngine.printWithDelays("The fairy has granted you a wish of healing?", false);
        TextEngine.printWithDelays("Do you want to take it? " + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor, true);
        while (true) {
            command = Room.console.readLine();
            switch (command) {
                case "yes" -> {
                    Player.fairyHeal();
                    this.roomsBeenTo[this.currentPosition[0]][this.currentPosition[1]] = this.map[this.currentPosition[0]][this.currentPosition[1]];
                    Main.loadSave();
                    break;
                }
                case "no" -> {
                    int[] buffer = this.currentPosition.clone();
                    this.currentPosition = this.lastPosition.clone(); // Save the current position before moving
                    this.lastPosition = buffer.clone();
                    Main.loadSave();
                    break;
                }
                default -> {
                    defaultDungeonArgs(command);
                }
            }
        }
    }

    public boolean testIfBossRoom(int check) {
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: testIfBossRoom");
        }
        if (check != 0) {
            return check == 8;
        }
        return false;
    }

    public void itemRoom(List<String> localItems) throws InterruptedException {
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: itemRoom");
        }
        if (localItems != null && !localItems.isEmpty()) {
            String randomItem = localItems.get(rand.nextInt(localItems.size()));
            if (hasChestInRoom(randomItem, 1)) {
                this.roomsBeenTo[this.currentPosition[0]][this.currentPosition[1]] = this.map[this.currentPosition[0]][this.currentPosition[1]];
                // Ensure items is a mutable collection
                List<String> mutableItems = new ArrayList<>(this.items);
                mutableItems.remove(randomItem);
                this.items = mutableItems;
                Main.loadSave();
            } else {
                int[] buffer = this.currentPosition.clone();
                this.currentPosition = this.lastPosition.clone(); // Save the current position before moving
                this.lastPosition = buffer.clone();
                Main.loadSave();
            }
        } else {
            fairySequence();
        }
        if (Main.TESTING) {
            System.out.println(redColor + this.currentBoss + "Function Complete: itemRoom" + resetColor);
        }
    }

    public void bossRoom() throws InterruptedException {
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: bossRoom");
        }
        TextEngine.printWithDelays("You have entered the boss room", false);
        Player.changeHealth(Enemy.spawnEnemy(currentBoss, 1));
        TextEngine.printWithDelays("You have defeated the boss and completed the dungeon!", false);
        TextEngine.enterToNext();
        this.roomsBeenTo[this.currentPosition[0]][this.currentPosition[1]] = this.map[this.currentPosition[0]][this.currentPosition[1]];
        if (!this.completed) {
            completedDungeons++;
            this.completed = true;
            if (completedDungeons == 8) {
                Main.gameComplete = true;
            }
        }
        this.lastPosition = null;
        OpenWorld.startRoom();
        if (Main.TESTING) {
            System.out.println(redColor + this.currentBoss + "Function Complete: bossRoom" + resetColor);
        }
    }

    public String directionsInString(ArrayList<String> list) {
        if (Main.TESTING) {
            System.out.println(this.currentBoss + "Running function: directionsInString");
        }
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
        return this.currentPosition;
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
        this.currentPosition = localCurrentPosition;
    }

    public void setLastPosition(int[] localLastPosition) {
        this.lastPosition = localLastPosition;
    }
}
