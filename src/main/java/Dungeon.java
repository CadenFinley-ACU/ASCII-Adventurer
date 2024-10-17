
import java.util.ArrayList;
import java.util.List;

/**
 * Dungeon Class
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class Dungeon extends Room {

    static String resetColor = "\033[0m"; // reset to default color
    static String yellowColor = "\033[1;33m"; // yellow color
    public static String currentDungeon;
    public static int completedDungeons = 0;
    public static int numberOfEnemies;
    public static String enemyType;

    public static int[][] meadowDungeon;
    public static int[][] darkForestDungeon;
    public static int[][] mountainCaveDungeon;
    public static int[][] mountainTopDungeon;
    public static int[][] desertOasisDungeon;
    public static int[][] desertPlainsDungeon;
    public static int[][] desertPyramidDungeon;
    public static int[][] oceanKingdomDungeon;

    public static int[] currentPlayerPosition = null;
    public static int[] lastPosition = null; // Variable to store the last position
    public static boolean previousAutoSettings;

    public static List<String> missedItems = new ArrayList<>();

    public static void generateDungeons() { //generates all 8 dungeons and stores them in their respective variables
        meadowDungeon = DungeonGenerator.generateAndReturnMatrix(5);
        darkForestDungeon = DungeonGenerator.generateAndReturnMatrix(6);
        mountainCaveDungeon = DungeonGenerator.generateAndReturnMatrix(7);
        mountainTopDungeon = DungeonGenerator.generateAndReturnMatrix(7);
        desertOasisDungeon = DungeonGenerator.generateAndReturnMatrix(8);
        desertPlainsDungeon = DungeonGenerator.generateAndReturnMatrix(8);
        desertPyramidDungeon = DungeonGenerator.generateAndReturnMatrix(9);
        oceanKingdomDungeon = DungeonGenerator.generateAndReturnMatrix(11);
    }

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
                Main.screenRefresh();
                TextEngine.printWithDelays("You open your map and see the following:\n", false);
                System.out.println();
                switch (currentDungeon) {
                    case "Meadow" -> {
                        DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(meadowDungeon, MeadowDungeon.roomsBeenTo, currentPlayerPosition, MeadowDungeon.mapRevealed);
                    }
                    case "Dark Forest" -> {
                        DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(darkForestDungeon, DarkForestDungeon.roomsBeenTo, currentPlayerPosition, DarkForestDungeon.mapRevealed);
                    }
                    case "Mountain Cave" -> {
                        DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(mountainCaveDungeon, MountainCaveDungeon.roomsBeenTo, currentPlayerPosition, MountainCaveDungeon.mapRevealed);
                    }
                    case "Mountain Top" -> {
                        DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(mountainTopDungeon, MountainTopDungeon.roomsBeenTo, currentPlayerPosition, MountainTopDungeon.mapRevealed);
                    }
                    case "Desert Oasis" -> {
                        DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(desertOasisDungeon, DesertOasisDungeon.roomsBeenTo, currentPlayerPosition, DesertOasisDungeon.mapRevealed);
                    }
                    case "Desert Plains" -> {
                        DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(desertPlainsDungeon, DesertPlainsDungeon.roomsBeenTo, currentPlayerPosition, DesertPlainsDungeon.mapRevealed);
                    }
                    case "Desert Pyramid" -> {
                        DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(desertPyramidDungeon, DesertPyramidDungeon.roomsBeenTo, currentPlayerPosition, DesertPyramidDungeon.mapRevealed);
                    }
                    case "Ocean Kingdom" -> {
                        DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(oceanKingdomDungeon, OceanKingdomDungeon.roomsBeenTo, currentPlayerPosition, OceanKingdomDungeon.mapRevealed);
                    }
                    //add more dungeons here if needed
                }
                System.out.println();
                TextEngine.enterToNext();
                Main.loadSave();
            }
            default -> {
                Main.inGameDefaultTextHandling(data);
            }
        }
    }

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

    public static void dungeonCheck() throws InterruptedException {
        if (OpenWorld.holdCommand == null) {
            OpenWorld.holdCommand = "onward";
        }
        switch (completedDungeons) {
            case 0 -> {// the meadow dungeon
                switch (OpenWorld.roomNumber) {
                    case 73, 74 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, east.\n\n ", false);
                    case 65, 66, 68 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the east.\n\n", false);
                    default ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south, east.\n\n", false);
                }
            }
            case 1 -> {// the dark forest dungeon
                switch (OpenWorld.roomNumber) {
                    case 73, 65, 66, 68 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, east.\n\n", false);
                    case 27, 57, 58, 59 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the east.\n\n", false);
                    default ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south, east.\n\n", false);
                }
            }
            case 2 -> {// The Mountain Cave Dungeon
                switch (OpenWorld.roomNumber) {
                    case 66, 57, 50, 41 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north.\n\n", false);
                    case 33, 34, 35, 36, 42, 43, 44, 45, 51, 52, 53, 58, 59, 68 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, west.\n\n", false);
                    case 21, 22 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the east.\n\n", false);
                    case 30, 31, 32, 37, 38, 39, 40, 48, 49, 27, 65, 73 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, east\n\n", false);
                    case 17, 18, 13, 14, 15, 9, 10, 5 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south, west.\n\n", false);
                    case 4 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south.\n\n", false);
                    case 3, 16, 12 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south, east.\n\n", false);
                    default ->
                        TextEngine.printWithDelays("The Mountain cave is not working Doungeon.java\n\n", false);

                }
            }
            case 3 -> {// The Mountain Top Dungeon
                switch (OpenWorld.roomNumber) {
                    case 3, 4, 5 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the east.\n\n", false);
                    case 15, 28, 35, 44, 53 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north.\n\n", false);
                    case 36, 45 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, west.\n\n", false);
                    default ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, east.\n\n", false);
                }
            }
            case 4 -> {// The Desert Oasis Dungeon
                switch (OpenWorld.roomNumber) {
                    case 73 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the west.\n\n", false);
                    case 48, 39, 32 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south.\n\n", false);
                    case 12, 16, 22, 31, 38, 21, 30 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south east.\n\n", false);
                    default ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south, west.\n\n", false);
                }
            }
            case 5 -> {// The Desert Plains Dungeon
                switch (OpenWorld.roomNumber) {
                    case 65, 66, 68 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the west.\n\n", false);
                    case 73 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, west.\n\n", false);
                    case 48, 39, 32 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south.\n\n", false);
                    case 12, 16, 22, 31, 38, 21, 30 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south east.\n\n", false);
                    default ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south west.\n\n", false);
                }
            }
            case 6 -> {// The Desert Pyramid Dungeon
                switch (OpenWorld.roomNumber) {
                    case 27, 57, 58, 59 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the west.\n\n", false);
                    case 65, 66, 68, 73 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, west.\n\n", false);
                    case 48, 39, 32 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south.\n\n", false);
                    case 12, 16, 22, 31, 38, 21, 30 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south east.\n\n", false);
                    default ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south west.\n\n", false);
                }
            }
            case 7 -> { // The Ocean Kingdom Dungeon
                switch (OpenWorld.roomNumber) {
                    case 12, 16, 22, 31, 38 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north.\n\n", false);
                    case 21, 30 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, east.\n\n", false);
                    case 3, 4, 5 ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south.\n\n", false);
                    default ->
                        TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, west.\n\n", false);
                }
            }
            default ->
                TextEngine.printWithDelays("this function isnt working right", false);
        }
    }

    public static boolean confirmBossContinue() throws InterruptedException {
        if (Player.inventory.containsKey("key")) {
            TextEngine.printWithDelays("Would you like to unlock the door to the boss room? " + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor, true);
            while (true) {
                ignore = Room.console.readLine();
                command = Room.console.readLine();
                switch (command.toLowerCase().trim()) {
                    case "yes" -> {
                        return InventoryManager.useKey();
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

    public static void dungeonShop() throws InterruptedException {
        Main.screenRefresh();
        String localDungeon = currentDungeon;
        TextEngine.printNoDelay("Gold: " + Player.getGold(), false);
        TextEngine.printNoDelay("Inventory: " + Player.getTotalNumberOfItemsInInventory() + "/" + Player.inventorySize, false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("Welcome to the Dungeon shop Traveler! I snuck into this dungeon a long time ago and got lost.\nMaybe some of these items can help you on your journey.\nWhat would you like to buy?", false);
        while (true) {
            switch (localDungeon) {
                case "Meadow", "Dark Forest", "Mountain Cave", "Mountain Top" -> {
                    TextEngine.printNoDelay(yellowColor + "health potion" + resetColor + " ~20 gold\n" + yellowColor + "magic map" + resetColor + " ~50\n" + yellowColor + "K.O. Cannon" + resetColor + " ~3000 gold\nor " + yellowColor + " leave" + resetColor, true);
                    ignore = Room.console.readLine();
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
                case "Desert Oasis", "Desert Plains", "Desert Pyramid" -> {
                    TextEngine.printNoDelay(yellowColor + "greater health potion" + resetColor + " ~50 gold\n" + yellowColor + "magic map" + resetColor + " ~50\n" + yellowColor + "K.O. Cannon" + resetColor + " ~3000 gold\nor " + yellowColor + " leave" + resetColor, true);
                    ignore = Room.console.readLine();
                    command = Room.console.readLine();
                    switch (command.toLowerCase().trim()) {
                        case "greater health potion" -> {
                            buyMultiple("greater health potion", 50);
                        }
                        case "magic map" -> {
                            if (Player.getGold() >= 100 && !checkIfCurrentDungeonIsRevealed()) {
                                Player.changeGold(-100);
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
                case "Ocean Kingdom" -> {
                    TextEngine.printNoDelay(yellowColor + "super health potion" + resetColor + " ~75 gold\n" + yellowColor + "magic map" + resetColor + " ~50\n" + yellowColor + "K.O. Cannon" + resetColor + " ~3000 gold\nor " + yellowColor + " leave" + resetColor, true);
                    ignore = Room.console.readLine();
                    command = Room.console.readLine();
                    switch (command.toLowerCase().trim()) {
                        case "super health potion" -> {
                            buyMultiple("super health potion", 75);
                        }
                        case "magic map" -> {
                            if (Player.getGold() >= 200 && !checkIfCurrentDungeonIsRevealed()) {
                                Player.changeGold(-200);
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

    private static void buyMultiple(String type, int cost) throws InterruptedException { //buy multiple clause for certain items in village shop
        TextEngine.printWithDelays("How many would you like to buy?", true);
        ignore = console.readLine();
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

    private static void keepShopping() throws InterruptedException { //keep shopping
        TextEngine.printWithDelays("Would you like to keep shopping? " + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor, true);
        while (true) {
            ignore = console.readLine();
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

    private static void leave() throws InterruptedException {
        currentPlayerPosition = lastPosition;
        switch (currentDungeon) {
            case "Meadow" -> {
                TextEngine.printWithDelays("You leave the shop and return to the Meadow Dungeon.", false);
                TextEngine.enterToNext();
                MeadowDungeon.startRoom();
            }
            case "Dark Forest" -> {
                TextEngine.printWithDelays("You leave the shop and return to the Dark Forest Dungeon.", false);
                TextEngine.enterToNext();
                DarkForestDungeon.startRoom();
            }
            case "Mountain Cave" -> {
                TextEngine.printWithDelays("You leave the shop and return to the Mountain Cave Dungeon.", false);
                TextEngine.enterToNext();
                MountainCaveDungeon.startRoom();
            }
            case "Mountain Top" -> {
                TextEngine.printWithDelays("You leave the shop and return to the Mountain Top Dungeon.", false);
                TextEngine.enterToNext();
                MountainTopDungeon.startRoom();
            }
            case "Desert Oasis" -> {
                TextEngine.printWithDelays("You leave the shop and return to the Desert Oasis Dungeon.", false);
                TextEngine.enterToNext();
                DesertOasisDungeon.startRoom();
            }
            case "Desert Plains" -> {
                TextEngine.printWithDelays("You leave the shop and return to the Desert Plains Dungeon.", false);
                TextEngine.enterToNext();
                DesertPlainsDungeon.startRoom();
            }
            case "Desert Pyramid" -> {
                TextEngine.printWithDelays("You leave the shop and return to the Desert Pyramid Dungeon.", false);
                TextEngine.enterToNext();
                DesertPyramidDungeon.startRoom();
            }
            case "Ocean Kingdom" -> {
                TextEngine.printWithDelays("You leave the shop and return to the Ocean Kingdom Dungeon.", false);
                TextEngine.enterToNext();
                OceanKingdomDungeon.startRoom();
            }
        }
    }

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
}
