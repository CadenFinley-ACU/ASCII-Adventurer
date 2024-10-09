
import java.util.ArrayList;
import java.util.List;

/**
 * Dungeon Class
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class Dungeon extends Room {

    public static String currentDungeon;
    public static int completedDungeons = 0;

    public static int[][] meadowDungeon;
    public static int[][] darkForestDungeon;
    public static int[][] mountainCaveDungeon;
    public static int[][] mountainTopDungeon;
    public static int[][] desertOasisDungeon;
    public static int[][] desertPlainsDungeon;
    public static int[][] desertPyramidDungeon;
    public static int[][] oceanKingdomDungeon;

    public static int[] currentPlayerPosition;
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
                            TextEngine.enterToNext();
                            OpenWorld.startRoom();
                        } else {
                            TextEngine.printWithDelays("You cannot leave until you have completed the dungeon.", true);
                        }
                    }
                    case "Dark Forest" -> {
                        if (DarkForestDungeon.completed) {
                            TextEngine.printWithDelays("You leave the dungeon and return to the open world.", false);
                            TextEngine.enterToNext();
                            OpenWorld.startRoom();
                        } else {
                            TextEngine.printWithDelays("You cannot leave until you have completed the dungeon.", true);
                        }
                    }
                    case "Mountain Cave" -> {
                        if (MountainCaveDungeon.completed) {
                            TextEngine.printWithDelays("You leave the dungeon and return to the open world.", false);
                            TextEngine.enterToNext();
                            OpenWorld.startRoom();
                        } else {
                            TextEngine.printWithDelays("You cannot leave until you have completed the dungeon.", true);
                        }
                    }
                    case "Mountain Top" -> {
                        if (MountainTopDungeon.completed) {
                            TextEngine.printWithDelays("You leave the dungeon and return to the open world.", false);
                            TextEngine.enterToNext();
                            OpenWorld.startRoom();
                        } else {
                            TextEngine.printWithDelays("You cannot leave until you have completed the dungeon.", true);
                        }
                    }
                    case "Desert Oasis" -> {
                        if (DesertOasisDungeon.completed) {
                            TextEngine.printWithDelays("You leave the dungeon and return to the open world.", false);
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
                            TextEngine.enterToNext();
                            OpenWorld.startRoom();
                        } else {
                            TextEngine.printWithDelays("You cannot leave until you have completed the dungeon.", true);
                        }
                    }
                    case "Ocean Kingdom" -> {
                        if (OceanKingdomDungeon.completed) {
                            TextEngine.printWithDelays("You leave the dungeon and return to the open world.", false);
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
                if (Player.getName().equals("Debug!")) {
                    switch (currentDungeon) {
                        case "Meadow" -> {
                            DungeonGenerator.printMap(meadowDungeon);
                        }
                        case "Dark Forest" -> {
                            DungeonGenerator.printMap(darkForestDungeon);
                        }
                        case "Mountain Cave" -> {
                            DungeonGenerator.printMap(mountainCaveDungeon);
                        }
                        case "Mountain Top" -> {
                            DungeonGenerator.printMap(mountainTopDungeon);
                        }
                        case "Desert Oasis" -> {
                            DungeonGenerator.printMap(desertOasisDungeon);
                        }
                        case "Desert Plains" -> {
                            DungeonGenerator.printMap(desertPlainsDungeon);
                        }
                        case "Desert Pyramid" -> {
                            DungeonGenerator.printMap(desertPyramidDungeon);
                        }
                        case "Ocean Kingdom" -> {
                            DungeonGenerator.printMap(oceanKingdomDungeon);
                        }
                        //add more dungeons here if needed
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
    }

    public static void autoCheck() {
        previousAutoSettings = Player.autoFight;
        if (Player.autoFight) {
            Player.autoFight = false;
        }
    }

    public static void dungeonCheck() throws InterruptedException {
        // the meadow dungeon 
        if (completedDungeons == 0) {
            switch (OpenWorld.roomNumber) {
                case 2 ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north.\n\n", false);
                case 4, 5 ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the west.\n\n", false);
                default ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south, west.\n\n", false);
            }
        } else if (completedDungeons
                > 0) // the dark forest dungeon
        {
            switch (OpenWorld.roomNumber) {
                case 1, 2, 3, 4 ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, west.\n\n", false);
                case 6, 7 ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the west.\n\n", false);
                default ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south, west.\n\n", false);
            }
        } else if (completedDungeons
                > 1) // The Mountain Cave Dungeon
        {
            switch (OpenWorld.roomNumber) {
                case 1, 2 ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, east.\n\n", false);
                case 3, 4 ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the east.\n\n", false);
                default ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south, east.\n\n", false);
            }
        } else if (completedDungeons
                > 2) // The Mountain Top Dungeon
        {
            switch (OpenWorld.roomNumber) {
                case 1, 2, 3, 4, 5 ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, east.\n\n", false);
                case 7, 8 ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the east.\n\n", false);
                default ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south, east.\n\n", false);
            }
        } else if (completedDungeons
                > 3) // The Desert Oasis Dungeon
        {
            switch (OpenWorld.roomNumber) {
                case 1, 2, 3, 4, 5, 6, 7, 8 ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, east.\n\n", false);
                case 9, 10, 21 ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the east.\n\n", false);
                default ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south, east.\n\n", false);
            }
        } else if (completedDungeons
                > 4) // The Desert Plains Dungeon
        {
            switch (OpenWorld.roomNumber) {
                case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 21 ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, east.\n\n", false);
                case 11, 12, 13, 14 ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the east.\n\n", false);
                default ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south, east.\n\n", false);
            }
        } else if (completedDungeons
                > 4) // The Desert Pyramid Dungeon
        {
            switch (OpenWorld.roomNumber) {
                case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 21, 11, 12, 13, 14, 15, 16, 17 ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north, east.\n\n", false);
                case 19 ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the east.\n\n", false);
                default ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south, east.\n\n", false);
            }
        } else if (completedDungeons
                > 4) // The Ocean Kingdom Dungeon
        {
            switch (OpenWorld.roomNumber) {
                case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 21, 11, 12, 13, 14, 15, 16, 17, 18, 19 ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the north.\n\n", false);
                //case  19-> TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the east.\n\n", false);
                default ->
                    TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ", feeling a sense of adventure as you leave the open paths behind.\n Ahead, you notice the entrance to the next dungeon lying just to the south, east.\n\n", false);
            }
        } else {
            TextEngine.printWithDelays("this function isnt working right", false);
        }
    }

    public static void addItemsToMiniDungeons(List<String> items) {
        for (String item : missedItems) {
            missedItems.add(item);
        }
    }
}
