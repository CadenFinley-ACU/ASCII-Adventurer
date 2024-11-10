
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
                            Main.screenRefresh();
                            TextEngine.printWithDelays("You open your map and see the following:\n", false);
                            System.out.println();
                            DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(meadowDungeon, MeadowDungeon.roomsBeenTo, currentPlayerPosition, MeadowDungeon.mapRevealed);
                            System.out.println();
                            TextEngine.enterToNext();
                            Main.loadSave();
                        } else {
                            TextEngine.printWithDelays("You cannot use the map right now", true);
                        }
                    }
                    case "Dark Forest" -> {
                        if (ableToUseMenuCommands()) {
                            Main.screenRefresh();
                            TextEngine.printWithDelays("You open your map and see the following:\n", false);
                            System.out.println();
                            DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(darkForestDungeon, DarkForestDungeon.roomsBeenTo, currentPlayerPosition, DarkForestDungeon.mapRevealed);
                            System.out.println();
                            TextEngine.enterToNext();
                            Main.loadSave();
                        } else {
                            TextEngine.printWithDelays("You cannot use the map right now", true);
                        }
                    }
                    case "Mountain Cave" -> {
                        if (ableToUseMenuCommands()) {
                            Main.screenRefresh();
                            TextEngine.printWithDelays("You open your map and see the following:\n", false);
                            System.out.println();
                            DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(mountainCaveDungeon, MountainCaveDungeon.roomsBeenTo, currentPlayerPosition, MountainCaveDungeon.mapRevealed);
                            System.out.println();
                            TextEngine.enterToNext();
                            Main.loadSave();
                        } else {
                            TextEngine.printWithDelays("You cannot use the map right now", true);
                        }
                    }
                    case "Mountain Top" -> {
                        if (ableToUseMenuCommands()) {
                            Main.screenRefresh();
                            TextEngine.printWithDelays("You open your map and see the following:\n", false);
                            System.out.println();
                            DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(mountainTopDungeon, MountainTopDungeon.roomsBeenTo, currentPlayerPosition, MountainTopDungeon.mapRevealed);
                            System.out.println();
                            TextEngine.enterToNext();
                            Main.loadSave();
                        } else {
                            TextEngine.printWithDelays("You cannot use the map right now", true);
                        }
                    }
                    case "Desert Oasis" -> {
                        if (ableToUseMenuCommands()) {
                            Main.screenRefresh();
                            TextEngine.printWithDelays("You open your map and see the following:\n", false);
                            System.out.println();
                            DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(desertOasisDungeon, DesertOasisDungeon.roomsBeenTo, currentPlayerPosition, DesertOasisDungeon.mapRevealed);
                            System.out.println();
                            TextEngine.enterToNext();
                            Main.loadSave();
                        } else {
                            TextEngine.printWithDelays("You cannot use the map right now", true);
                        }
                    }
                    case "Desert Plains" -> {
                        if (ableToUseMenuCommands()) {
                            Main.screenRefresh();
                            TextEngine.printWithDelays("You open your map and see the following:\n", false);
                            System.out.println();
                            DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(desertPlainsDungeon, DesertPlainsDungeon.roomsBeenTo, currentPlayerPosition, DesertPlainsDungeon.mapRevealed);
                            System.out.println();
                            TextEngine.enterToNext();
                            Main.loadSave();
                        } else {
                            TextEngine.printWithDelays("You cannot use the map right now", true);
                        }
                    }
                    case "Desert Pyramid" -> {
                        if (ableToUseMenuCommands()) {
                            Main.screenRefresh();
                            TextEngine.printWithDelays("You open your map and see the following:\n", false);
                            System.out.println();
                            DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(desertPyramidDungeon, DesertPyramidDungeon.roomsBeenTo, currentPlayerPosition, DesertPyramidDungeon.mapRevealed);
                            System.out.println();
                            TextEngine.enterToNext();
                            Main.loadSave();
                        } else {
                            TextEngine.printWithDelays("You cannot use the map right now", true);
                        }
                    }
                    case "Ocean Kingdom" -> {
                        if (ableToUseMenuCommands()) {
                            Main.screenRefresh();
                            TextEngine.printWithDelays("You open your map and see the following:\n", false);
                            System.out.println();
                            DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(oceanKingdomDungeon, OceanKingdomDungeon.roomsBeenTo, currentPlayerPosition, OceanKingdomDungeon.mapRevealed);
                            System.out.println();
                            TextEngine.enterToNext();
                            Main.loadSave();
                        } else {
                            TextEngine.printWithDelays("You cannot use the map right now", true);
                        }
                    }
                    //add more dungeons here if needed
                }
            }
            case "reset" -> {
                if (Main.gameComplete) {
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
        if (OpenWorld.holdCommand == null || OpenWorld.holdCommand.isEmpty() || OpenWorld.holdCommand.isBlank() || OpenWorld.holdCommand.equals(" ") || OpenWorld.holdCommand.equals("null")) {
            OpenWorld.holdCommand = "onward";
            TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ".", false);
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

    public static void dungeonShop() throws InterruptedException {
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

    private static void buyMultiple(String type, int cost) throws InterruptedException { //buy multiple clause for certain items in village shop
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
        TextEngine.printWithDelays("You leave the shop and return to the " + currentDungeon + " Dungeon.", false);
        TextEngine.enterToNext();
        handleDirectionsAndCommands();
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

    public static boolean ableToUseMenuCommands() {
        if ("OpenWorld".equals(Main.getSavedPlace()) || "Village".equals(Main.getSavedPlace()) || "SpawnRoom".equals(Main.getSavedPlace())) {
            return true;
        }
        switch (currentDungeon) {
            case "Meadow" -> {
                return (!(meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && MeadowDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Dark Forest" -> {
                return (!(darkForestDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || darkForestDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (darkForestDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && DarkForestDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Mountain Cave" -> {
                return (!(mountainCaveDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || mountainCaveDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (mountainCaveDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && MountainCaveDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Mountain Top" -> {
                return (!(mountainTopDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || mountainTopDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (mountainTopDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && MountainTopDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Desert Oasis" -> {
                return (!(desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && DesertOasisDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Desert Plains" -> {
                return (!(desertPlainsDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || desertPlainsDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (desertPlainsDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && DesertPlainsDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Desert Pyramid" -> {
                return (!(desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && DesertPyramidDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            case "Ocean Kingdom" -> {
                return (!(oceanKingdomDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 || oceanKingdomDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 || (oceanKingdomDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && OceanKingdomDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0)));
            }
            default -> {
                return true;
            }
        }
    }

    public static void dungeonIntroText() throws InterruptedException {
        TextEngine.printWithDelays("You have entered " + redColor + "The " + currentDungeon + resetColor + "!", false);
        TextEngine.printWithDelays("To beat the dungeon you must beat the " + redColor + currentBoss + resetColor + "!\nBe on the look out for treasure rooms! They hold some powerful loot.\nYou can always type help to see what commands you have available!\nGood Luck!", false);
        TextEngine.enterToNext();
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
        Main.loadSave();
    }

    public static void miniBossSequence() throws InterruptedException {
        TextEngine.printWithDelays("You have entered a room with a mini boss", false);
        Player.changeHealth(Enemy.spawnEnemy(currentMiniBoss, 1));
        switch (currentDungeon) {
            case "Meadow" -> {
                meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 7;
            }
            case "Dark Forest" -> {
                darkForestDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 7;
            }
            case "Mountain Cave" -> {
                mountainCaveDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 7;
            }
            case "Mountain Top" -> {
                mountainTopDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 7;
            }
            case "Desert Oasis" -> {
                desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 7;
            }
            case "Desert Plains" -> {
                desertPlainsDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 7;
            }
            case "Desert Pyramid" -> {
                desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 7;
            }
            case "Ocean Kingdom" -> {
                oceanKingdomDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 7;
            }
        }
        setMaps();
        Main.loadSave();
    }

    public static void fairySequence() throws InterruptedException {
        switch (currentDungeon) {
            case "Meadow" -> {
                meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 10;
            }
            case "Dark Forest" -> {
                darkForestDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 10;
            }
            case "Mountain Cave" -> {
                mountainCaveDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 10;
            }
            case "Mountain Top" -> {
                mountainTopDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 10;
            }
            case "Desert Oasis" -> {
                desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 10;
            }
            case "Desert Plains" -> {
                desertPlainsDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 10;
            }
            case "Desert Pyramid" -> {
                desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 10;
            }
            case "Ocean Kingdom" -> {
                oceanKingdomDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 10;
            }
        }
        setMaps();
        Main.loadSave();
    }

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
                    switch (currentDungeon) {
                        case "Meadow" -> {
                            meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 5;
                        }
                        case "Dark Forest" -> {
                            darkForestDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 5;
                        }
                        case "Mountain Cave" -> {
                            mountainCaveDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 5;
                        }
                        case "Mountain Top" -> {
                            mountainTopDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 5;
                        }
                        case "Desert Oasis" -> {
                            desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 5;
                        }
                        case "Desert Plains" -> {
                            desertPlainsDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 5;
                        }
                        case "Desert Pyramid" -> {
                            desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 5;
                        }
                        case "Ocean Kingdom" -> {
                            oceanKingdomDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 5;
                        }
                    }
                    setMaps();
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

    public static void fightRandomEnemies() throws InterruptedException {
        if (numberOfEnemies == 0) {
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
            command = TextEngine.parseCommand(console.readLine(), new String[]{"fight", "run"});
            switch (command) {
                case "fight" -> {
                    Player.changeHealth(Enemy.spawnEnemy(enemyType, numberOfEnemies));
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

    public static void heartContainerRoom() throws InterruptedException {
        if (hasItemInRoom("heart container", 1)) {
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
        } else {
            int[] buffer = currentPlayerPosition.clone();
            currentPlayerPosition = lastPosition.clone(); // Save the current position before moving
            lastPosition = buffer.clone();
            Main.loadSave();
        }
    }

    public static void keyRoom() throws InterruptedException {
        if (hasItemInRoom("key", 1)) {
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
        } else {
            int[] buffer = currentPlayerPosition.clone();
            currentPlayerPosition = lastPosition.clone(); // Save the current position before moving
            lastPosition = buffer.clone();
            Main.loadSave();
        }
    }

    public static void fairyRoom() throws InterruptedException {
        TextEngine.printWithDelays("You have entered a room with a mystical fairy", false);
        TextEngine.printWithDelays("The fairy has granted you a wish of healing?", false);
        TextEngine.printWithDelays("Do you want to take it? " + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor, true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine(), new String[]{"yes", "no"});
            switch (command) {
                case "yes" -> {
                    if (Player.fairyHeal()) {
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
                        Main.loadSave();
                    }
                    int[] buffer = currentPlayerPosition.clone();
                    currentPlayerPosition = lastPosition.clone(); // Save the current position before moving
                    lastPosition = buffer.clone();
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

    public static boolean testIfBossRoom(int check) {
        if (check != 0) {
            return check == 8;
        }
        return false;
    }

    public static void itemRoom(List<String> localItems) throws InterruptedException {
        if (!localItems.isEmpty()) {
            String randomItem = localItems.get(rand.nextInt(localItems.size()));
            if (hasChestInRoom(randomItem, 1)) {
                switch (currentDungeon) {
                    case "Meadow" -> {
                        MeadowDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        MeadowDungeon.items.remove(randomItem);
                    }
                    case "Dark Forest" -> {
                        DarkForestDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = darkForestDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        DarkForestDungeon.items.remove(randomItem);
                    }
                    case "Mountain Cave" -> {
                        MountainCaveDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = mountainCaveDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        MountainCaveDungeon.items.remove(randomItem);
                    }
                    case "Mountain Top" -> {
                        MountainTopDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = mountainTopDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        MountainTopDungeon.items.remove(randomItem);
                    }
                    case "Desert Oasis" -> {
                        DesertOasisDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        DesertOasisDungeon.items.remove(randomItem);
                    }
                    case "Desert Plains" -> {
                        DesertPlainsDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPlainsDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        DesertPlainsDungeon.items.remove(randomItem);
                    }
                    case "Desert Pyramid" -> {
                        DesertPyramidDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        DesertPyramidDungeon.items.remove(randomItem);
                    }
                    case "Ocean Kingdom" -> {
                        OceanKingdomDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = oceanKingdomDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        OceanKingdomDungeon.items.remove(randomItem);
                    }
                }
                Main.loadSave();
            } else {
                int[] buffer = currentPlayerPosition.clone();
                currentPlayerPosition = lastPosition.clone(); // Save the current position before moving
                lastPosition = buffer.clone();
                Main.loadSave();
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

    public static void trappedRoomSequence() throws InterruptedException {
        int hit = new Random().nextBoolean() ? 1 : 2;
        if (hit == 1) {
            switch (currentDungeon) {
                case "Meadow" -> {
                    meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 20;
                }
                case "Dark Forest" -> {
                    darkForestDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 20;
                }
                case "Mountain Cave" -> {
                    mountainCaveDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 20;
                }
                case "Mountain Top" -> {
                    mountainTopDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 20;
                }
                case "Desert Oasis" -> {
                    desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 20;
                }
                case "Desert Plains" -> {
                    desertPlainsDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 20;
                }
                case "Desert Pyramid" -> {
                    desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 20;
                }
                case "Ocean Kingdom" -> {
                    oceanKingdomDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 20;
                }
            }
        } else {
            switch (currentDungeon) {
                case "Meadow" -> {
                    meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 1;
                    MeadowDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                }
                case "Dark Forest" -> {
                    darkForestDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 1;
                    DarkForestDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = darkForestDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                }
                case "Mountain Cave" -> {
                    mountainCaveDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 1;
                    MountainCaveDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = mountainCaveDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                }
                case "Mountain Top" -> {
                    mountainTopDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 1;
                    MountainTopDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = mountainTopDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                }
                case "Desert Oasis" -> {
                    desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 1;
                    DesertOasisDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                }
                case "Desert Plains" -> {
                    desertPlainsDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 1;
                    DesertPlainsDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPlainsDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                }
                case "Desert Pyramid" -> {
                    desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 1;
                    DesertPyramidDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                }
                case "Ocean Kingdom" -> {
                    oceanKingdomDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 1;
                    OceanKingdomDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = oceanKingdomDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                }
            }
        }
        setMaps();
        Main.loadSave();
    }

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
        Main.loadSave();
    }

    public static void bossRoom() throws InterruptedException {
        TextEngine.printWithDelays("You have entered the boss room", false);
        Player.changeHealth(Enemy.spawnEnemy(currentBoss, 1));
        TextEngine.printWithDelays("You have defeated the boss and completed the dungeon!", false);
        TextEngine.enterToNext();
        switch (currentDungeon) {
            case "Meadow" -> {
                MeadowDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                if (!MeadowDungeon.completed) {
                    completedDungeons++;
                    MeadowDungeon.completed = true;
                }
            }
            case "Dark Forest" -> {
                DarkForestDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = darkForestDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                if (!DarkForestDungeon.completed) {
                    completedDungeons++;
                    DarkForestDungeon.completed = true;
                }
            }
            case "Mountain Cave" -> {
                MountainCaveDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = mountainCaveDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                if (!MountainCaveDungeon.completed) {
                    completedDungeons++;
                    MountainCaveDungeon.completed = true;
                }
            }
            case "Mountain Top" -> {
                MountainTopDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = mountainTopDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                if (!MountainTopDungeon.completed) {
                    completedDungeons++;
                    MountainTopDungeon.completed = true;
                }
            }
            case "Desert Oasis" -> {
                DesertOasisDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                if (!DesertOasisDungeon.completed) {
                    completedDungeons++;
                    DesertOasisDungeon.completed = true;
                }
            }
            case "Desert Plains" -> {
                DesertPlainsDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPlainsDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                if (!DesertPlainsDungeon.completed) {
                    completedDungeons++;
                    DesertPlainsDungeon.completed = true;
                }
            }
            case "Desert Pyramid" -> {
                DesertPyramidDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                if (!DesertPyramidDungeon.completed) {
                    completedDungeons++;
                    DesertPyramidDungeon.completed = true;
                }
            }
            case "Ocean Kingdom" -> {
                OceanKingdomDungeon.roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = oceanKingdomDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                if (!OceanKingdomDungeon.completed) {
                    completedDungeons++;
                    OceanKingdomDungeon.completed = true;
                    Main.gameComplete = true;
                }
            }
        }
        lastPosition = null;
        OpenWorld.startRoom();
    }

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

    public static void handleDirectionsAndCommands() throws InterruptedException {
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
        Main.screenRefresh();
        DungeonGenerator.drawRoom(localDungeon, roomsBeenTo, currentPlayerPosition[0], currentPlayerPosition[1], 0, mapRevealed);
        availableMove = DungeonGenerator.getDirections(localDungeon, currentPlayerPosition[0], currentPlayerPosition[1]);
        if (completed) {
            TextEngine.printWithDelays("You have completed this dungeon. You can now type " + yellowColor + "leave" + resetColor + " to exit this dungeon.", false);
        }
        System.out.println("Type " + yellowColor + "map" + resetColor + " to see the map.");
        System.out.println();
        TextEngine.printWithDelays("You can move in the following directions: ", false);
        addDirections(availableMove);
        TextEngine.printWithDelays(directionsInString(directionsString), true);
        while (true) {
            command = TextEngine.parseCommand(Room.console.readLine().toLowerCase().trim(), directionsString.toArray(String[]::new));
            switch (command) {
                case "north" ->
                    movePlayer(command, new int[]{-1, 0}, roomsBeenTo, localDungeon);
                case "east" ->
                    movePlayer(command, new int[]{0, 1}, roomsBeenTo, localDungeon);
                case "south" ->
                    movePlayer(command, new int[]{1, 0}, roomsBeenTo, localDungeon);
                case "west" ->
                    movePlayer(command, new int[]{0, -1}, roomsBeenTo, localDungeon);
                case "boss room" -> {
                    if (confirmBossContinue()) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = localDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition = DungeonGenerator.findValue(localDungeon, 8);
                        changeDungeonRoomsBeenTo(roomsBeenTo);
                        Main.loadSave();
                    } else {
                        Main.loadSave();
                    }
                }
                default ->
                    //return original command
                    Dungeon.defaultDungeonArgs(command);
            }
        }
    }

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

    private static void movePlayer(String direction, int[] positionChange, int[][] roomsBeenTo, int[][] localDungeon) throws InterruptedException {
        if (directionsString.contains(direction)) {
            lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
            roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = localDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            currentPlayerPosition[0] += positionChange[0];
            currentPlayerPosition[1] += positionChange[1];
            changeDungeonRoomsBeenTo(roomsBeenTo);
            Main.loadSave();
        } else {
            Dungeon.defaultDungeonArgs(direction);
        }
    }

    private static void changeDungeonRoomsBeenTo(int[][] changedRoomsBeenTo) {
        switch (currentDungeon) {
            case "Meadow" -> {
                MeadowDungeon.roomsBeenTo = changedRoomsBeenTo;
            }
            case "Dark Forest" -> {
                DarkForestDungeon.roomsBeenTo = changedRoomsBeenTo;
            }
            case "Mountain Cave" -> {
                MountainCaveDungeon.roomsBeenTo = changedRoomsBeenTo;
            }
            case "Mountain Top" -> {
                MountainTopDungeon.roomsBeenTo = changedRoomsBeenTo;
            }
            case "Desert Oasis" -> {
                DesertOasisDungeon.roomsBeenTo = changedRoomsBeenTo;
            }
            case "Desert Plains" -> {
                DesertPlainsDungeon.roomsBeenTo = changedRoomsBeenTo;
            }
            case "Desert Pyramid" -> {
                DesertPyramidDungeon.roomsBeenTo = changedRoomsBeenTo;
            }
            case "Ocean Kingdom" -> {
                OceanKingdomDungeon.roomsBeenTo = changedRoomsBeenTo;
            }
        }
    }
}
