
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ASCIIADVENTURER Caden Finley Albert Tucker Grijesh Shrestha
 *
 * The Dungeon class represents a dungeon in the game. It extends the Room class
 * and includes additional properties and methods specific to a dungeon.
 *
 * @author ASCIIADVENTURERS
 * @version 1.0
 */
public class Dungeon extends Room {

    public static String currentDungeon;
    public static int completedDungeons = 0;
    public static int numberOfEnemies;
    public static String enemyType;
    public static boolean resetedAfterWin = false;

    private static final Random rand = new Random();

    public static int[][] meadowDungeon = null;
    public static int[][] darkForestDungeon = null;
    public static int[][] mountainCaveDungeon = null;
    public static int[][] mountainTopDungeon = null;
    public static int[][] desertOasisDungeon = null;
    public static int[][] desertPlainsDungeon = null;
    public static int[][] desertPyramidDungeon = null;
    public static int[][] oceanKingdomDungeon = null;

    public static int[] currentPlayerPosition = null;
    public static int[] lastPosition = null; // Variable to store the last position

    public static String currentMiniBoss;
    public static String currentBoss;
    public static boolean inBossFight = false;

    public static List<String> missedItems = new ArrayList<>();

    private static int[] availableMove;
    private static ArrayList<String> directionsString;

    public static DungeonInstance MeadowDungeon;
    public static DungeonInstance DarkForestDungeon;
    public static DungeonInstance MountainCaveDungeon;
    public static DungeonInstance MountainTopDungeon;
    public static DungeonInstance DesertOasisDungeon;
    public static DungeonInstance DesertPlainsDungeon;
    public static DungeonInstance DesertPyramidDungeon;
    public static DungeonInstance OceanKingdomDungeon;

    /**
     * The function generates 8 different dungeons using a DungeonGenerator
     * object and stores them in respective variables.
     */
    public static void generateDungeons() { //generates all 8 dungeons and stores them in their respective variables
        DungeonGenerator generator = new DungeonGenerator();
        meadowDungeon = generator.generateDungeon(5);
        darkForestDungeon = generator.generateDungeon(6);
        mountainCaveDungeon = generator.generateDungeon(7);
        mountainTopDungeon = generator.generateDungeon(7);
        desertOasisDungeon = generator.generateDungeon(8);
        desertPlainsDungeon = generator.generateDungeon(8);
        desertPyramidDungeon = generator.generateDungeon(9);
        oceanKingdomDungeon = generator.generateDungeon(11);

    }

    /**
     * The function initializes different dungeon instances with specific enemy
     * and loot configurations.
     */
    public static void initalizeDungeons() {
        MeadowDungeon = new DungeonInstance(new ArrayList<>(List.of("Goblin", "Skeleton", "Slime", "Mimic")), new ArrayList<>(List.of("axe", "chainmail set")), false, false, false, "Meadow", "Meadow Dungeon", "Golem", "Forest Giant", 3);
        DarkForestDungeon = new DungeonInstance(new ArrayList<>(List.of("Goblin", "Skeleton", "Orc", "Mimic", "Zombie")), new ArrayList<>(List.of("broad sword", "full armor kit")), false, false, false, "Dark Forest", "Dark Forest Dungeon", "Forest Guardian", "Forest Spirit", 4);
        MountainCaveDungeon = new DungeonInstance(new ArrayList<>(List.of("Troll", "Skeleton", "Orc", "Ghost", "Demon", "Zombie")), new ArrayList<>(List.of("better sword", "ninja armor")), false, false, false, "Mountain Cave", "Mountain Cave Dungeon", "Elemental", "Wyvern", 5);
        MountainTopDungeon = new DungeonInstance(new ArrayList<>(List.of("Ghost", "Gargoyle", "Orc", "Vampire", "Demon")), new ArrayList<>(List.of("great sword", "knight armor")), false, false, false, "Mountain Top", "Mountain Top Dungeon", "Minotaur", "Ice Dragon", 7);
        DesertOasisDungeon = new DungeonInstance(new ArrayList<>(List.of("Werewolf", "Witch", "Giant", "Mummy", "Minotaur")), new ArrayList<>(List.of("master sword", "royal armor")), false, false, false, "Desert Oasis", "Desert Oasis Dungeon", "Sphinx", "Phoenix", 3);
        DesertPlainsDungeon = new DungeonInstance(new ArrayList<>(List.of("Orc", "Troll", "Mummy", "Demon")), new ArrayList<>(List.of("legendary sword", "demon armor")), false, false, false, "Desert Plains", "Desert Plains Dungeon", "Cyclops", "Giant Scorpion", 5);
        DesertPyramidDungeon = new DungeonInstance(new ArrayList<>(List.of("Werewolf", "Witch", "Giant", "Mummy", "Minotaur")), new ArrayList<>(List.of("excalibur", "angel armor")), false, false, false, "Desert Pyramid", "Desert Pyramid Dungeon", "Medusa", "Giant Sand Worm", 6);
        OceanKingdomDungeon = new DungeonInstance(new ArrayList<>(List.of("Sea Serpent", "Sea Monster", "Sea Witch", "Sea Dragon", "Sea Dragon")), new ArrayList<>(List.of("god slayer hammer", "god slayer armor")), false, false, false, "Ocean Kingdom", "Ocean Kingdom Dungeon", "Leviathan", "Kraken", 4);
    }

    /**
     * The function sets the maps for different dungeons and initializes their
     * values.
     */
    public static void setMaps() {
        MeadowDungeon.map = meadowDungeon;
        DarkForestDungeon.map = darkForestDungeon;
        MountainCaveDungeon.map = mountainCaveDungeon;
        MountainTopDungeon.map = mountainTopDungeon;
        DesertOasisDungeon.map = desertOasisDungeon;
        DesertPlainsDungeon.map = desertPlainsDungeon;
        DesertPyramidDungeon.map = desertPyramidDungeon;
        OceanKingdomDungeon.map = oceanKingdomDungeon;
        MeadowDungeon.setValues();
        DarkForestDungeon.setValues();
        MountainCaveDungeon.setValues();
        MountainTopDungeon.setValues();
        DesertOasisDungeon.setValues();
        DesertPlainsDungeon.setValues();
        DesertPyramidDungeon.setValues();
        OceanKingdomDungeon.setValues();
    }

    /**
     * The function `setRoomsBeenTo` sets the shown map for various dungeons in
     * a game.
     */
    public static void setRoomsBeenTo() {
        MeadowDungeon.setShownMap();
        DarkForestDungeon.setShownMap();
        MountainCaveDungeon.setShownMap();
        MountainTopDungeon.setShownMap();
        DesertOasisDungeon.setShownMap();
        DesertPlainsDungeon.setShownMap();
        DesertPyramidDungeon.setShownMap();
        OceanKingdomDungeon.setShownMap();
    }

    /**
     * Handles default dungeon arguments based on the provided data.
     *
     * @param data the command to be processed (e.g., "leave", "map", "reset")
     * @throws InterruptedException if the thread is interrupted
     */
    public static void defaultDungeonArgs(String data) throws InterruptedException { //default dungeon arguments
        switch (data) {
            case "leave" -> {
                switch (currentDungeon) {
                    case "Meadow" -> {
                        if (MeadowDungeon.completed) {
                            TextEngine.printWithDelays("You leave the dungeon and return to the open world.", false);
                            lastPosition = null;
                            TextEngine.enterToNext();
                            OpenWorld.startRoom();
                        } else {
                            TextEngine.printWithDelays("You cannot leave until you have completed the dungeon.", true);
                        }
                    }
                    case "Dark Forest" -> {
                        if (DarkForestDungeon.completed) {
                            TextEngine.printWithDelays("You leave the dungeon and return to the open world.", false);
                            lastPosition = null;
                            TextEngine.enterToNext();
                            OpenWorld.startRoom();
                        } else {
                            TextEngine.printWithDelays("You cannot leave until you have completed the dungeon.", true);
                        }
                    }
                    case "Mountain Cave" -> {
                        if (MountainCaveDungeon.completed) {
                            TextEngine.printWithDelays("You leave the dungeon and return to the open world.", false);
                            lastPosition = null;
                            TextEngine.enterToNext();
                            OpenWorld.startRoom();
                        } else {
                            TextEngine.printWithDelays("You cannot leave until you have completed the dungeon.", true);
                        }
                    }
                    case "Mountain Top" -> {
                        if (MountainTopDungeon.completed) {
                            TextEngine.printWithDelays("You leave the dungeon and return to the open world.", false);
                            lastPosition = null;
                            TextEngine.enterToNext();
                            OpenWorld.startRoom();
                        } else {
                            TextEngine.printWithDelays("You cannot leave until you have completed the dungeon.", true);
                        }
                    }
                    case "Desert Oasis" -> {
                        if (DesertOasisDungeon.completed) {
                            TextEngine.printWithDelays("You leave the dungeon and return to the open world.", false);
                            lastPosition = null;
                            TextEngine.enterToNext();
                            OpenWorld.startRoom();
                        } else {
                            TextEngine.printWithDelays("You cannot leave until you have completed the dungeon.", true);
                        }
                    }
                    case "Desert Plains" -> {
                        if (DesertPlainsDungeon.completed) {
                            TextEngine.printWithDelays("You leave the dungeon and return to the open world.", false);
                            TextEngine.enterToNext();
                            OpenWorld.startRoom();
                        } else {
                            TextEngine.printWithDelays("You cannot leave until you have completed the dungeon.", true);
                        }
                    }
                    case "Desert Pyramid" -> {
                        if (DesertPyramidDungeon.completed) {
                            TextEngine.printWithDelays("You leave the dungeon and return to the open world.", false);
                            lastPosition = null;
                            TextEngine.enterToNext();
                            OpenWorld.startRoom();
                        } else {
                            TextEngine.printWithDelays("You cannot leave until you have completed the dungeon.", true);
                        }
                    }
                    case "Ocean Kingdom" -> {
                        if (OceanKingdomDungeon.completed) {
                            TextEngine.printWithDelays("You leave the dungeon and return to the open world.", false);
                            lastPosition = null;
                            TextEngine.enterToNext();
                            OpenWorld.startRoom();
                        } else {
                            TextEngine.printWithDelays("You cannot leave until you have completed the dungeon.", true);
                        }
                    }
                    //add more dungeons here if needed
                }
            }
            case "map" -> {
                switch (currentDungeon) {
                    case "Meadow" -> {
                        if (ableToUseMenuCommands()) {
                            GameEngine.screenRefresh();
                            TextEngine.printWithDelays("You open your map and see the following:\n", false);
                            System.out.println();
                            DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(meadowDungeon, MeadowDungeon.roomsBeenTo, currentPlayerPosition, MeadowDungeon.mapRevealed);
                            System.out.println();
                            TextEngine.enterToNext();
                            GameEngine.loadSave();
                        } else {
                            TextEngine.printWithDelays("You cannot use the map right now", true);
                        }
                    }
                    case "Dark Forest" -> {
                        if (ableToUseMenuCommands()) {
                            GameEngine.screenRefresh();
                            TextEngine.printWithDelays("You open your map and see the following:\n", false);
                            System.out.println();
                            DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(darkForestDungeon, DarkForestDungeon.roomsBeenTo, currentPlayerPosition, DarkForestDungeon.mapRevealed);
                            System.out.println();
                            TextEngine.enterToNext();
                            GameEngine.loadSave();
                        } else {
                            TextEngine.printWithDelays("You cannot use the map right now", true);
                        }
                    }
                    case "Mountain Cave" -> {
                        if (ableToUseMenuCommands()) {
                            GameEngine.screenRefresh();
                            TextEngine.printWithDelays("You open your map and see the following:\n", false);
                            System.out.println();
                            DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(mountainCaveDungeon, MountainCaveDungeon.roomsBeenTo, currentPlayerPosition, MountainCaveDungeon.mapRevealed);
                            System.out.println();
                            TextEngine.enterToNext();
                            GameEngine.loadSave();
                        } else {
                            TextEngine.printWithDelays("You cannot use the map right now", true);
                        }
                    }
                    case "Mountain Top" -> {
                        if (ableToUseMenuCommands()) {
                            GameEngine.screenRefresh();
                            TextEngine.printWithDelays("You open your map and see the following:\n", false);
                            System.out.println();
                            DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(mountainTopDungeon, MountainTopDungeon.roomsBeenTo, currentPlayerPosition, MountainTopDungeon.mapRevealed);
                            System.out.println();
                            TextEngine.enterToNext();
                            GameEngine.loadSave();
                        } else {
                            TextEngine.printWithDelays("You cannot use the map right now", true);
                        }
                    }
                    case "Desert Oasis" -> {
                        if (ableToUseMenuCommands()) {
                            GameEngine.screenRefresh();
                            TextEngine.printWithDelays("You open your map and see the following:\n", false);
                            System.out.println();
                            DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(desertOasisDungeon, DesertOasisDungeon.roomsBeenTo, currentPlayerPosition, DesertOasisDungeon.mapRevealed);
                            System.out.println();
                            TextEngine.enterToNext();
                            GameEngine.loadSave();
                        } else {
                            TextEngine.printWithDelays("You cannot use the map right now", true);
                        }
                    }
                    case "Desert Plains" -> {
                        if (ableToUseMenuCommands()) {
                            GameEngine.screenRefresh();
                            TextEngine.printWithDelays("You open your map and see the following:\n", false);
                            System.out.println();
                            DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(desertPlainsDungeon, DesertPlainsDungeon.roomsBeenTo, currentPlayerPosition, DesertPlainsDungeon.mapRevealed);
                            System.out.println();
                            TextEngine.enterToNext();
                            GameEngine.loadSave();
                        } else {
                            TextEngine.printWithDelays("You cannot use the map right now", true);
                        }
                    }
                    case "Desert Pyramid" -> {
                        if (ableToUseMenuCommands()) {
                            GameEngine.screenRefresh();
                            TextEngine.printWithDelays("You open your map and see the following:\n", false);
                            System.out.println();
                            DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(desertPyramidDungeon, DesertPyramidDungeon.roomsBeenTo, currentPlayerPosition, DesertPyramidDungeon.mapRevealed);
                            System.out.println();
                            TextEngine.enterToNext();
                            GameEngine.loadSave();
                        } else {
                            TextEngine.printWithDelays("You cannot use the map right now", true);
                        }
                    }
                    case "Ocean Kingdom" -> {
                        if (ableToUseMenuCommands()) {
                            GameEngine.screenRefresh();
                            TextEngine.printWithDelays("You open your map and see the following:\n", false);
                            System.out.println();
                            DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(oceanKingdomDungeon, OceanKingdomDungeon.roomsBeenTo, currentPlayerPosition, OceanKingdomDungeon.mapRevealed);
                            System.out.println();
                            TextEngine.enterToNext();
                            GameEngine.loadSave();
                        } else {
                            TextEngine.printWithDelays("You cannot use the map right now", true);
                        }
                    }
                    //add more dungeons here if needed
                }
            }
            case "reset" -> {
                if (GameEngine.gameComplete) {
                    TextEngine.printWithDelays("This command will reset" + yellowColor + " ALL " + resetColor + "Dungeons!", false);
                    TextEngine.printWithDelays("Are you sure you want to do this? " + redColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor, true);
                    while (true) {
                        command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"yes", "no"});
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
                                GameEngine.checkSave(room);
                                GameSaveSerialization.saveGame();
                                GameEngine.loadSave();
                            }
                            default -> {
                                GameEngine.inGameDefaultTextHandling(data);
                            }
                        }
                    }
                } else {
                    GameEngine.inGameDefaultTextHandling(data);
                }
            }
            default -> {
                GameEngine.inGameDefaultTextHandling(data);
            }
        }
    }

    /**
     * The `resetAll` function resets all dungeons and sets the completed
     * dungeons count to 0.
     */
    public static void resetAll() { //reset all dungeons
        generateDungeons();
        MeadowDungeon.fresh();
        DarkForestDungeon.fresh();
        MountainCaveDungeon.fresh();
        MountainTopDungeon.fresh();
        DesertOasisDungeon.fresh();
        DesertPlainsDungeon.fresh();
        DesertPyramidDungeon.fresh();
        OceanKingdomDungeon.fresh();
        completedDungeons = 0;
    }

    /**
     * Prompts the player to decide whether to unlock the door to the boss room
     * if they have a key.
     *
     * @return {@code true} if the player chooses to unlock the door and
     * successfully uses the key, {@code false} otherwise.
     * @throws InterruptedException if the thread is interrupted while waiting
     * for user input.
     */
    public static boolean confirmBossContinue() throws InterruptedException {
        if (Player.inventory.containsKey("key")) {
            TextEngine.printWithDelays("Would you like to unlock the door to the boss room? " + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor, true);
            while (true) {
                command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"yes", "no"});
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
                        Dungeon.defaultDungeonArgs(command.toLowerCase());
                    }
                }
            }
        } else {
            TextEngine.printWithDelays("You need a key to unlock the door to the boss room, i'm sure there is one around here somewhere", false);
            TextEngine.enterToNext();
            return false;
        }
    }

    /**
     * Opens the dungeon shop where the player can buy items based on their
     * current dungeon location. Displays the player's gold and inventory
     * status, and offers different items for purchase depending on the dungeon.
     *
     * @throws InterruptedException if the thread is interrupted while waiting
     * for user input.
     */
    public static void dungeonShop() throws InterruptedException {
        GameEngine.screenRefresh();
        String localDungeon = currentDungeon;
        TextEngine.printNoDelay("Gold: " + Player.getGold(), false);
        TextEngine.printNoDelay("Inventory: " + Player.getTotalNumberOfItemsInInventory() + "/" + Player.getInventorySize(), false);
        TextEngine.printWithDelays("\nWelcome to the Dungeon shop Traveler! I snuck into this dungeon a long time ago and got lost.\nMaybe some of these items can help you on your journey.\nWhat would you like to buy?", false);
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
                                Dungeon.useMagicMap();
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
                            Dungeon.defaultDungeonArgs(command.toLowerCase());
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
                                Dungeon.useMagicMap();
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
                            Dungeon.defaultDungeonArgs(command.toLowerCase());
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
                                Dungeon.useMagicMap();
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
                            Dungeon.defaultDungeonArgs(command.toLowerCase());
                        }
                    }
                }
            }
        }
    }

    /**
     * Allows the player to buy multiple items of a specified type from the
     * village shop.
     *
     * @param type The type of item to buy.
     * @param cost The cost of a single item.
     * @throws InterruptedException If the thread is interrupted while waiting
     * for user input.
     */
    private static void buyMultiple(String type, int cost) throws InterruptedException { //buy multiple clause for certain items in village shop
        TextEngine.printWithDelays("How many would you like to buy?", true);
        while (true) {
            command = console.readLine();
            if (TextEngine.checkValidInput(command)) {
                try {
                    Integer.valueOf(command);
                } catch (NumberFormatException e) {
                    GameEngine.invalidCommand();
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
                GameEngine.invalidCommand();
                keepShopping();
            }
        }
    }

    /**
     * Prompts the player to decide whether to keep shopping or not. If the
     * player chooses "yes", the dungeon shop is reopened. If the player chooses
     * "no", the player leaves the shop.
     *
     * @throws InterruptedException If the thread is interrupted while waiting
     * for user input.
     */
    private static void keepShopping() throws InterruptedException { //keep shopping
        TextEngine.printWithDelays("Would you like to keep shopping? " + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor, true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"yes", "no"});
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

    /**
     * Uses the magic map to reveal the layout of the current dungeon. This
     * method prints a message indicating the map has been used and reveals the
     * layout of the dungeon. It then sets the mapRevealed flag to true for the
     * respective dungeon.
     *
     * @throws InterruptedException if the thread is interrupted while printing
     * the message
     */
    private static void useMagicMap() throws InterruptedException {
        switch (currentDungeon) {
            case "Meadow" -> {
                TextEngine.printWithDelays("You use the magic map...\nIt revealed the layout of the Meadow Dungeon!", false);
                TextEngine.enterToNext();
                MeadowDungeon.mapRevealed = true;
            }
            case "Dark Forest" -> {
                TextEngine.printWithDelays("You use the magic map...\nIt revealed the layout of the Dark Forest Dungeon!", false);
                TextEngine.enterToNext();
                DarkForestDungeon.mapRevealed = true;
            }
            case "Mountain Cave" -> {
                TextEngine.printWithDelays("You use the magic map...\nIt revealed the layout of the Mountain Cave Dungeon!", false);
                TextEngine.enterToNext();
                MountainCaveDungeon.mapRevealed = true;
            }
            case "Mountain Top" -> {
                TextEngine.printWithDelays("You use the magic map...\nIt revealed the layout of the Mountain Top Dungeon!", false);
                TextEngine.enterToNext();
                MountainTopDungeon.mapRevealed = true;
            }
            case "Desert Oasis" -> {
                TextEngine.printWithDelays("You use the magic map...\nIt revealed the layout of the Desert Oasis Dungeon!", false);
                TextEngine.enterToNext();
                DesertOasisDungeon.mapRevealed = true;
            }
            case "Desert Plains" -> {
                TextEngine.printWithDelays("You use the magic map...\nIt revealed the layout of the Desert Plains Dungeon!", false);
                TextEngine.enterToNext();
                DesertPlainsDungeon.mapRevealed = true;
            }
            case "Desert Pyramid" -> {
                TextEngine.printWithDelays("You use the magic map...\nIt revealed the layout of the Desert Pyramid Dungeon!", false);
                TextEngine.enterToNext();
                DesertPyramidDungeon.mapRevealed = true;
            }
            case "Ocean Kingdom" -> {
                TextEngine.printWithDelays("You use the magic map...\nIt revealed the layout of the Ocean Kingdom Dungeon!", false);
                TextEngine.enterToNext();
                OceanKingdomDungeon.mapRevealed = true;
            }
        }
    }

    /**
     * Leaves the current dungeon. This method handles the directions and
     * commands for leaving the dungeon.
     *
     * @throws InterruptedException if the thread is interrupted while handling
     * directions and commands
     */
    private static void leave() throws InterruptedException {
        handleDirectionsAndCommands(true);
    }

    /**
     * The function `checkIfCurrentDungeonIsRevealed` checks if the map of the
     * current dungeon is revealed based on the dungeon name.
     *
     * @return The method `checkIfCurrentDungeonIsRevealed` returns a boolean
     * value based on whether the map of the current dungeon is revealed or not.
     * The specific dungeon's `mapRevealed` property is checked based on the
     * value of the `currentDungeon` variable.
     */
    private static boolean checkIfCurrentDungeonIsRevealed() {
        switch (currentDungeon) {
            case "Meadow" -> {
                return MeadowDungeon.mapRevealed;
            }
            case "Dark Forest" -> {
                return DarkForestDungeon.mapRevealed;
            }
            case "Mountain Cave" -> {
                return MountainCaveDungeon.mapRevealed;
            }
            case "Mountain Top" -> {
                return MountainTopDungeon.mapRevealed;
            }
            case "Desert Oasis" -> {
                return DesertOasisDungeon.mapRevealed;
            }
            case "Desert Plains" -> {
                return DesertPlainsDungeon.mapRevealed;
            }
            case "Desert Pyramid" -> {
                return DesertPyramidDungeon.mapRevealed;
            }
            case "Ocean Kingdom" -> {
                return OceanKingdomDungeon.mapRevealed;
            }
            default -> {
                return false;
            }
        }
    }

    /**
     * The function `ableToUseMenuCommands` checks if the player is allowed to
     * use menu commands based on their current location in the game world.
     *
     * @return The method `ableToUseMenuCommands()` returns a boolean value. The
     * return value depends on the conditions specified in the method.
     */
    public static boolean ableToUseMenuCommands() {
        if ("OpenWorld".equals(GameEngine.getSavedPlace()) || "Village".equals(GameEngine.getSavedPlace()) || "SpawnRoom".equals(GameEngine.getSavedPlace())) {
            return true;
        }
        switch (currentDungeon) {
            case "Meadow" -> {
                return (!(meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 8 || meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && MeadowDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Dark Forest" -> {
                return (!(darkForestDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 8 || darkForestDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || darkForestDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (darkForestDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && DarkForestDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Mountain Cave" -> {
                return (!(mountainCaveDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 8 || mountainCaveDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || mountainCaveDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (mountainCaveDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && MountainCaveDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Mountain Top" -> {
                return (!(mountainTopDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 8 || mountainTopDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || mountainTopDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (mountainTopDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && MountainTopDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Desert Oasis" -> {
                return (!(desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 8 || desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && DesertOasisDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Desert Plains" -> {
                return (!(desertPlainsDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 8 || desertPlainsDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || desertPlainsDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (desertPlainsDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && DesertPlainsDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Desert Pyramid" -> {
                return (!(desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 8 || desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && DesertPyramidDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Ocean Kingdom" -> {
                return (!(oceanKingdomDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 8 || oceanKingdomDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || oceanKingdomDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (oceanKingdomDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && OceanKingdomDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            default -> {
                return true;
            }
        }
    }

    /**
     * Displays the introductory text for the current dungeon and updates the
     * player's position. This method prints a welcome message, provides
     * instructions, and updates the player's current position in the dungeon's
     * map based on the current dungeon.
     *
     * @throws InterruptedException if the thread is interrupted while printing
     * the messages
     */
    public static void dungeonIntroText() throws InterruptedException {
        TextEngine.printWithDelays("You have entered " + redColor + "The " + currentDungeon + resetColor + "!", false);
        TextEngine.printWithDelays("To beat the dungeon you must beat the " + redColor + currentBoss + resetColor + "!\nBe on the look out for treasure rooms! They hold some powerful loot.\nYou can always type help to see what commands you have available!\nGood Luck!", false);
        TextEngine.enterToNext();
        updateRoomsBeenTo();
    }

    /**
     * Initiates the mini boss sequence, updates the player's health, and marks
     * the current room as visited.
     *
     * @throws InterruptedException if the thread is interrupted
     */
    public static void miniBossSequence() throws InterruptedException {
        TextEngine.printWithDelays("You have entered a room with a mini boss", false);
        Player.changeHealth(Enemy.spawnEnemy(currentMiniBoss, 1));
        changeCurrentRoomOfPlayerPositionToNewRoom(7);
        setMaps();
        GameEngine.loadSave();
    }

    /**
     * Updates the player's position on the current dungeon map and then loads a
     * saved game.
     *
     * @throws InterruptedException if the thread is interrupted
     */
    public static void fairySequence() throws InterruptedException {
        changeCurrentRoomOfPlayerPositionToNewRoom(10);
        setMaps();
        GameEngine.loadSave();
    }

    /**
     * Handles the sequence of events when the player enters a key room in the
     * dungeon. This method will determine the number of enemies, prompt the
     * player for a command, and execute the corresponding actions based on the
     * player's choice.
     *
     * @throws InterruptedException if the thread is interrupted during the
     * delay in text display.
     */
    public static void keyRoomSequence() throws InterruptedException {
        if (numberOfEnemies < 2) {
            numberOfEnemies = 2;
        }
        TextEngine.printWithDelays("You have entered a room with " + numberOfEnemies + " " + redColor + enemyType + "s" + resetColor + " in this room!\nYou were ambushed!", false);
        TextEngine.printWithDelays("They seem to be trying to protect something...", false);
        TextEngine.printWithDelays("What is your command? " + yellowColor + "fight" + resetColor + " or " + yellowColor + "run" + resetColor, true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"fight", "run"});
            switch (command) {
                case "fight" -> {
                    Player.changeHealth(Enemy.spawnEnemy(enemyType, numberOfEnemies));
                    changeCurrentRoomOfPlayerPositionToNewRoom(5);
                    setMaps();
                    GameEngine.loadSave();
                }
                case "run" -> {
                    Player.changeHealth(Enemy.runSpawnEnemy(enemyType, numberOfEnemies));
                    int[] buffer = currentPlayerPosition.clone();
                    currentPlayerPosition = lastPosition.clone(); // Save the current position before moving
                    lastPosition = buffer.clone();
                    GameEngine.loadSave();
                }
                default -> {
                    defaultDungeonArgs(command);
                }
            }
        }
    }

    /**
     * Handles the sequence of events when the player enters a room with random
     * enemies. This method will determine the number of enemies, prompt the
     * player for a command, and execute the corresponding actions based on the
     * player's choice.
     *
     * @throws InterruptedException if the thread is interrupted during the
     * delay in text display.
     */
    public static void fightRandomEnemies() throws InterruptedException {
        if (numberOfEnemies == 0) {
            updateRoomsBeenTo();
            return;
        }
        if (numberOfEnemies > 1) {
            TextEngine.printWithDelays("You have entered a room with " + numberOfEnemies + " " + redColor + enemyType + "s" + resetColor + " in this room!\nYou were ambushed!", false);
        } else {
            TextEngine.printWithDelays("You have entered a room with a " + redColor + enemyType + resetColor + " and were ambushed!", false);
        }
        TextEngine.printWithDelays("What is your command? " + yellowColor + "fight" + resetColor + " or " + yellowColor + "run" + resetColor, true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine(), new String[]{"fight", "run"});
            switch (command) {
                case "fight" -> {
                    Player.changeHealth(Enemy.spawnEnemy(enemyType, numberOfEnemies));
                    updateRoomsBeenTo();
                    return;
                }
                case "run" -> {
                    Player.changeHealth(Enemy.runSpawnEnemy(enemyType, numberOfEnemies));
                    int[] buffer = currentPlayerPosition.clone();
                    currentPlayerPosition = lastPosition.clone(); // Save the current position before moving
                    lastPosition = buffer.clone();
                    GameEngine.loadSave();
                }
                default -> {
                    defaultDungeonArgs(command);
                }
            }
        }
    }

    /**
     * The treasureRoom function allows the player to interact with a treasure
     * chest and receive a random item. The player's position is updated on the
     * dungeon map based on the current dungeon.
     *
     * @throws InterruptedException if the thread is interrupted while printing
     * the message
     */
    public static void heartContainerRoom() throws InterruptedException {
        if (hasItemInRoom("heart container", 1)) {
            updateRoomsBeenTo();
        }
    }

    /**
     * The keyRoom function allows the player to interact with a key and unlock
     * a door. The player's position is updated on the dungeon map based on the
     * current dungeon.
     *
     * @throws InterruptedException if the thread is interrupted while printing
     * the message
     */
    public static void keyRoom() throws InterruptedException {
        if (hasItemInRoom("key", 1)) {
            updateRoomsBeenTo();
        }
    }

    /**
     * The fairyRoom function allows the player to interact with a fairy and
     * receive a wish of healing. The player's position is updated on the
     * dungeon map based on the current dungeon.
     *
     * @throws InterruptedException if the thread is interrupted while printing
     * the message
     */
    public static void fairyRoom() throws InterruptedException {
        TextEngine.printWithDelays("You have entered a room with a mystical fairy", false);
        TextEngine.printWithDelays("The fairy has granted you a wish of healing?", false);
        TextEngine.printWithDelays("Do you want to take it? " + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor, true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine(), new String[]{"yes", "no"});
            switch (command) {
                case "yes" -> {
                    if (Player.fairyHeal()) {
                        updateRoomsBeenTo();
                        return;
                    } else {
                        return;
                    }
                }
                case "no" -> {
                    return;
                }
                default -> {
                    defaultDungeonArgs(command);
                }
            }
        }
    }

    /**
     * The function `testIfBossRoom` checks if the input integer is equal to 8
     * and returns true if it is not equal to 0.
     *
     * @param check The `check` parameter in the `testIfBossRoom` method is used
     * to determine if a room is a boss room. The method checks if the value of
     * `check` is not equal to 0, and if it is not, it returns `true` if `check`
     * is equal
     * @return The method `testIfBossRoom` returns a boolean value. It returns
     * `true` if the input `check` is not equal to 0 and is equal to 8,
     * otherwise it returns `false`.
     */
    public static boolean testIfBossRoom(int check) {
        if (check != 0) {
            return check == 8;
        }
        return false;
    }

    /**
     * The function `testIfItemRoom` checks if the input integer is equal to 6
     * and returns true if it is not equal to 0.
     *
     * @param check The `check` parameter in the `testIfItemRoom` method is used
     * to determine if a room is an item room. The method checks if the value of
     * `check` is not equal to 0, and if it is not, it returns `true` if `check`
     * is equal
     * @return The method `testIfItemRoom` returns a boolean value. It returns
     * `true` if the input `check` is not equal to 0 and is equal to 6,
     * otherwise it returns `false`.
     *
     * @throws InterruptedException if the thread is interrupted while printing
     */
    public static void itemRoom(List<String> localItems) throws InterruptedException {
        if (!localItems.isEmpty()) {
            String randomItem = localItems.get(rand.nextInt(localItems.size()));
            if (hasChestInRoom(randomItem, 1)) {
                updateRoomsBeenTo();
                switch (currentDungeon) {
                    case "Meadow" -> {
                        MeadowDungeon.items.remove(randomItem);
                    }
                    case "Dark Forest" -> {
                        DarkForestDungeon.items.remove(randomItem);
                    }
                    case "Mountain Cave" -> {
                        MountainCaveDungeon.items.remove(randomItem);
                    }
                    case "Mountain Top" -> {
                        MountainTopDungeon.items.remove(randomItem);
                    }
                    case "Desert Oasis" -> {
                        DesertOasisDungeon.items.remove(randomItem);
                    }
                    case "Desert Plains" -> {
                        DesertPlainsDungeon.items.remove(randomItem);
                    }
                    case "Desert Pyramid" -> {
                        DesertPyramidDungeon.items.remove(randomItem);
                    }
                    case "Ocean Kingdom" -> {
                        OceanKingdomDungeon.items.remove(randomItem);
                    }
                }
            }
        } else {
            switch (currentDungeon) {
                case "Meadow" -> {
                    if (DungeonGenerator.numberOfRooms(MeadowDungeon.map, 10) == 0) {
                        fairySequence();
                    } else {
                        trappedRoomSequence();
                    }
                }
                case "Dark Forest" -> {
                    if (DungeonGenerator.numberOfRooms(DarkForestDungeon.map, 10) == 0) {
                        fairySequence();
                    } else {
                        trappedRoomSequence();
                    }
                }
                case "Mountain Cave" -> {
                    if (DungeonGenerator.numberOfRooms(MountainCaveDungeon.map, 10) == 0) {
                        fairySequence();
                    } else {
                        trappedRoomSequence();
                    }
                }
                case "Mountain Top" -> {
                    if (DungeonGenerator.numberOfRooms(MountainTopDungeon.map, 10) == 0) {
                        fairySequence();
                    } else {
                        trappedRoomSequence();
                    }
                }
                case "Desert Oasis" -> {
                    if (DungeonGenerator.numberOfRooms(DesertOasisDungeon.map, 10) == 0) {
                        fairySequence();
                    } else {
                        trappedRoomSequence();
                    }
                }
                case "Desert Plains" -> {
                    if (DungeonGenerator.numberOfRooms(DesertPlainsDungeon.map, 10) == 0) {
                        fairySequence();
                    } else {
                        trappedRoomSequence();
                    }
                }
                case "Desert Pyramid" -> {
                    if (DungeonGenerator.numberOfRooms(DesertPyramidDungeon.map, 10) == 0) {
                        fairySequence();
                    } else {
                        trappedRoomSequence();
                    }
                }
                case "Ocean Kingdom" -> {
                    if (DungeonGenerator.numberOfRooms(OceanKingdomDungeon.map, 10) == 0) {
                        fairySequence();
                    } else {
                        trappedRoomSequence();
                    }
                }
            }
        }
    }

    /**
     * The `trappedRoomSequence` function randomly sets a specific value in a
     * dungeon map based on the current dungeon the player is in and then
     * updates the maps and loads the game save.
     *
     * @throws InterruptedException if the thread is interrupted while printing
     */
    public static void trappedRoomSequence() throws InterruptedException {
        int hit = new Random().nextBoolean() ? 1 : 2;
        if (hit == 1) {
            changeCurrentRoomOfPlayerPositionToNewRoom(20);
        } else {
            TextEngine.printWithDelays("There seems to have been a trap in this room, but it was disarmed!", false);
            TextEngine.printWithDelays("We need to be on the lookout for other traps!", false);
            TextEngine.enterToNext();
            changeCurrentRoomOfPlayerPositionToNewRoom(1);
            updateRoomsBeenTo();
        }
        setMaps();
        GameEngine.loadSave();
    }

    /**
     * The trappedRoom function simulates a player encountering a trap in a
     * dungeon, potentially losing health, and updating the rooms visited in
     * different dungeon locations.
     *
     * @throws InterruptedException if the thread is interrupted while printing
     */
    public static void trappedRoom() throws InterruptedException {
        TextEngine.printWithDelays("You have entered a trapped room! ", false);
        int hit = new Random().nextBoolean() ? 1 : 2;
        if (hit == 1) {
            TextEngine.printWithDelays("You have been hit by a trap! ", false);
            Player.changeHealth(-(Player.getHealth() / 4));
        } else {
            TextEngine.printWithDelays("You avoid the trap! ", false);
            TextEngine.enterToNext();
        }
        updateRoomsBeenTo();
    }

    /**
     * The bossRoom function handles the player's encounter with a boss, updates
     * dungeon completion status, and progresses the game to the next room in
     * the open world.
     *
     * @throws InterruptedException if the thread is interrupted while printing
     */
    public static void bossRoom() throws InterruptedException {
        inBossFight = true;
        if (!Enemy.bossFight(currentBoss)) {
            Player.changeHealth(-Player.getMaxHealth());
        }
        TextEngine.printWithDelays("You have defeated the " + currentBoss + " and completed the dungeon!", false);
        Player.changeGold(Enemy.enemyDamageValues.get(currentBoss) * 2);
        TextEngine.enterToNext();
        changeCurrentRoomOfPlayerPositionToNewRoom(1);
        updateRoomsBeenTo();
        switch (currentDungeon) {
            case "Meadow" -> {
                if (!MeadowDungeon.completed) {
                    completedDungeons++;
                    MeadowDungeon.completed = true;
                }
            }
            case "Dark Forest" -> {
                if (!DarkForestDungeon.completed) {
                    completedDungeons++;
                    DarkForestDungeon.completed = true;
                }
            }
            case "Mountain Cave" -> {
                if (!MountainCaveDungeon.completed) {
                    completedDungeons++;
                    MountainCaveDungeon.completed = true;
                    backpackSequence("Backpack");
                }
            }
            case "Mountain Top" -> {
                if (!MountainTopDungeon.completed) {
                    completedDungeons++;
                    MountainTopDungeon.completed = true;
                }
            }
            case "Desert Oasis" -> {
                if (!DesertOasisDungeon.completed) {
                    completedDungeons++;
                    DesertOasisDungeon.completed = true;
                }
            }
            case "Desert Plains" -> {
                if (!DesertPlainsDungeon.completed) {
                    completedDungeons++;
                    DesertPlainsDungeon.completed = true;
                    backpackSequence("Large Backpack");
                }
            }
            case "Desert Pyramid" -> {
                if (!DesertPyramidDungeon.completed) {
                    completedDungeons++;
                    DesertPyramidDungeon.completed = true;
                }
            }
            case "Ocean Kingdom" -> {
                if (!OceanKingdomDungeon.completed) {
                    completedDungeons++;
                    OceanKingdomDungeon.completed = true;
                    GameEngine.gameComplete = true;
                    gameCompletionSequence();
                }
            }
        }
        inBossFight = false;
        lastPosition = null;
        OpenWorld.startRoom();
    }

    /**
     * Converts a list of direction strings into a single formatted string. Each
     * direction is colored with yellowColor and resetColor. If there are
     * multiple directions, they are separated by commas, and the last direction
     * is prefixed with "or ".
     *
     * @param list the list of direction strings to be converted
     * @return a formatted string of directions
     */
    private static String directionsInString(ArrayList<String> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i == size - 1 && size > 1) {
                sb.append("or ");
            }
            sb.append(yellowColor).append(list.get(i)).append(resetColor);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * Handles the directions and commands for navigating through the dungeon.
     *
     * @param clearScreen A boolean indicating whether to clear the screen.
     * @throws InterruptedException If the thread is interrupted.
     */
    public static void handleDirectionsAndCommands(boolean clearScreen) throws InterruptedException {
        boolean completed = false;
        int[][] roomsBeenTo = null;
        int[][] localDungeon = null;
        boolean mapRevealed = false;
        switch (currentDungeon) {
            case "Meadow" -> {
                completed = MeadowDungeon.completed;
                roomsBeenTo = MeadowDungeon.roomsBeenTo;
                localDungeon = meadowDungeon;
                mapRevealed = MeadowDungeon.mapRevealed;
            }
            case "Dark Forest" -> {
                completed = DarkForestDungeon.completed;
                roomsBeenTo = DarkForestDungeon.roomsBeenTo;
                localDungeon = darkForestDungeon;
                mapRevealed = DarkForestDungeon.mapRevealed;
            }
            case "Mountain Cave" -> {
                completed = MountainCaveDungeon.completed;
                roomsBeenTo = MountainCaveDungeon.roomsBeenTo;
                localDungeon = mountainCaveDungeon;
                mapRevealed = MountainCaveDungeon.mapRevealed;
            }
            case "Mountain Top" -> {
                completed = MountainTopDungeon.completed;
                roomsBeenTo = MountainTopDungeon.roomsBeenTo;
                localDungeon = mountainTopDungeon;
                mapRevealed = MountainTopDungeon.mapRevealed;
            }
            case "Desert Oasis" -> {
                completed = DesertOasisDungeon.completed;
                roomsBeenTo = DesertOasisDungeon.roomsBeenTo;
                localDungeon = desertOasisDungeon;
                mapRevealed = DesertOasisDungeon.mapRevealed;
            }
            case "Desert Plains" -> {
                completed = DesertPlainsDungeon.completed;
                roomsBeenTo = DesertPlainsDungeon.roomsBeenTo;
                localDungeon = desertPlainsDungeon;
                mapRevealed = DesertPlainsDungeon.mapRevealed;
            }
            case "Desert Pyramid" -> {
                completed = DesertPyramidDungeon.completed;
                roomsBeenTo = DesertPyramidDungeon.roomsBeenTo;
                localDungeon = desertPyramidDungeon;
                mapRevealed = DesertPyramidDungeon.mapRevealed;
            }
            case "Ocean Kingdom" -> {
                completed = OceanKingdomDungeon.completed;
                roomsBeenTo = OceanKingdomDungeon.roomsBeenTo;
                localDungeon = oceanKingdomDungeon;
                mapRevealed = OceanKingdomDungeon.mapRevealed;
            }
        }
        availableMove = null;
        directionsString = new ArrayList<>();
        availableMove = DungeonGenerator.getDirections(localDungeon, currentPlayerPosition[0], currentPlayerPosition[1]);
        if (completed) {
            TextEngine.printWithDelays("You have completed this dungeon. You can now type " + yellowColor + "leave" + resetColor + " to exit this dungeon.", false);
        }
        if (clearScreen) {
            GameEngine.screenRefresh();
            DungeonGenerator.drawRoom(localDungeon, roomsBeenTo, currentPlayerPosition[0], currentPlayerPosition[1], 0, mapRevealed);
            System.out.println("Type " + yellowColor + "map" + resetColor + " to see the map.");
        }
        System.out.println();
        TextEngine.printWithDelays("You can move in the following directions: ", false);
        addDirections(availableMove);
        TextEngine.printWithDelays(directionsInString(directionsString), true);
        while (true) {
            command = TextEngine.parseCommand(Room.console.readLine().toLowerCase().trim(), directionsString.toArray(String[]::new));
            switch (command) {
                case "north" ->
                    movePlayer(command, new int[]{-1, 0});
                case "east" ->
                    movePlayer(command, new int[]{0, 1});
                case "south" ->
                    movePlayer(command, new int[]{1, 0});
                case "west" ->
                    movePlayer(command, new int[]{0, -1});
                case "boss room" -> {
                    if (confirmBossContinue()) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = localDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition = DungeonGenerator.findValue(localDungeon, 8);
                        GameEngine.loadSave();
                    } else {
                        GameEngine.loadSave();
                    }
                }
                default ->
                    //return original command
                    Dungeon.defaultDungeonArgs(command);
            }
        }
    }

    /**
     * Adds the available directions to the directionsString list based on the
     * available moves. If the move leads to a boss room, "boss room" is added
     * instead of the direction.
     *
     * @param availableMove an array of integers representing the available
     * moves. Each index corresponds to a direction: 0 - north, 1 - south, 2 -
     * west, 3 - east.
     */
    private static void addDirections(int[] availableMove) {
        String[] directions = {"north", "south", "west", "east"};
        for (int i = 0; i < availableMove.length; i++) {
            if (availableMove[i] > 0) {
                if (testIfBossRoom(availableMove[i])) {
                    directionsString.add("boss room");
                } else {
                    directionsString.add(directions[i]);
                }
            }
        }
    }

    /**
     * Moves the player in the specified direction and updates the dungeon
     * state.
     *
     * @param direction The direction in which the player is to be moved.
     * @param positionChange An array representing the change in position for
     * the player.
     * @param roomsBeenTo A 2D array representing the rooms the player has been
     * to.
     * @param localDungeon A 2D array representing the current state of the
     * dungeon.
     * @throws InterruptedException If the thread is interrupted while moving
     * the player.
     */
    private static void movePlayer(String direction, int[] positionChange) throws InterruptedException {
        if (directionsString.contains(direction)) {
            lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
            currentPlayerPosition[0] += positionChange[0];
            currentPlayerPosition[1] += positionChange[1];
            GameEngine.loadSave();
        } else {
            Dungeon.defaultDungeonArgs(direction);
        }
    }

    /**
     * Handles the sequence of adding an item to the player's backpack. If the
     * item cannot be added, it prompts the player to take the item and retries.
     *
     * @param pack The name of the item to be added to the backpack.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    private static void backpackSequence(String pack) throws InterruptedException {
        if (Player.putItem(pack, 1)) {
            return;
        }
        TextEngine.printWithDelays("You must take the " + yellowColor + pack + resetColor, false);
        TextEngine.enterToNext();
        backpackSequence(pack);
    }

    private static void updateRoomsBeenTo() {
        switch (currentDungeon) {
            case "Meadow" -> {
                MeadowDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            }
            case "Dark Forest" -> {
                DarkForestDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = darkForestDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            }
            case "Mountain Cave" -> {
                MountainCaveDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = mountainCaveDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            }
            case "Mountain Top" -> {
                MountainTopDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = mountainTopDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            }
            case "Desert Oasis" -> {
                DesertOasisDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            }
            case "Desert Plains" -> {
                DesertPlainsDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPlainsDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            }
            case "Desert Pyramid" -> {
                DesertPyramidDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            }
            case "Ocean Kingdom" -> {
                OceanKingdomDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = oceanKingdomDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            }
        }
    }

    private static void changeCurrentRoomOfPlayerPositionToNewRoom(int change) {
        switch (currentDungeon) {
            case "Meadow" -> {
                meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = change;
            }
            case "Dark Forest" -> {
                darkForestDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = change;
            }
            case "Mountain Cave" -> {
                mountainCaveDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = change;
            }
            case "Mountain Top" -> {
                mountainTopDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = change;
            }
            case "Desert Oasis" -> {
                desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = change;
            }
            case "Desert Plains" -> {
                desertPlainsDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = change;
            }
            case "Desert Pyramid" -> {
                desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = change;
            }
            case "Ocean Kingdom" -> {
                oceanKingdomDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = change;
            }
        }
    }
}
