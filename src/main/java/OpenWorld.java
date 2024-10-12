
/**
 * Open World Class
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
//maybe procurally generated dungeons using birary states to matrix arrrys ti create rooms in  path
public class OpenWorld extends Room {

    static int roomSave = 1;
    public static String holdCommand = null;
    static int roomNumber = 0;
    static String resetColor = "\033[0m"; // reset to default color
    static String yellowColor = "\033[1;33m"; // yellow color

    public static void startRoom() throws InterruptedException { //start room
        room = "OpenWorld";
        Main.checkSave(room);
        GameSaveSerialization.saveGame();
        Main.screenRefresh();
        switch (roomSave) {
            case 1 -> {
                Player.playerX = 2;
                Player.playerY = 7;
                roomNumber = 1;
                room1(); //start room   
            }
            case 2 -> {
                Player.playerX = 1;
                Player.playerY = 7;
                roomNumber = 2;
                room2(); //start room
            }
            case 3 -> {
                Player.playerX = 1;
                Player.playerY = 6;
                roomNumber = 3;
                room3(); //start room
            }
            case 4 -> {
                Player.playerX = 2;
                Player.playerY = 6;
                roomNumber = 4;
                room4(); //start room
            }
            case 5 -> {
                Player.playerX = 3;
                Player.playerY = 6;
                roomNumber = 5;
                room5(); //start room
            }
            case 6 -> {
                Player.playerX = 3;
                Player.playerY = 5;
                roomNumber = 6;
                room6(); //start room
            }
            case 7 -> {
                Player.playerX = 2;
                Player.playerY = 5;
                roomNumber = 7;
                room7(); //start room
            }
            case 8 -> {
                Player.playerX = 1;
                Player.playerY = 5;
                roomNumber = 8;
                room8(); //start room
            }
            case 9 -> {
                Player.playerX = 2;
                Player.playerY = 4;
                roomNumber = 9;
                room9(); //start room
            }
            case 10 -> {
                Player.playerX = 3;
                Player.playerY = 4;
                roomNumber = 10;
                room10(); //start room
            }
            case 11 -> {
                Player.playerX = 3;
                Player.playerY = 3;
                roomNumber = 11;
                room11(); //start room
            }
            case 12 -> {
                Player.playerX = 2;
                Player.playerY = 3;
                roomNumber = 12;
                room12(); //start room
            }
            case 13 -> {
                Player.playerX = 1;
                Player.playerY = 3;
                roomNumber = 13;
                room13(); //start room
            }
            case 14 -> {
                Player.playerX = 0;
                Player.playerY = 3;
                roomNumber = 14;
                room14(); //start room
            }
            case 15 -> {
                Player.playerX = 0;
                Player.playerY = 2;
                roomNumber = 15;
                room15(); //start room
            }
            case 16 -> {
                Player.playerX = 1;
                Player.playerY = 2;
                roomNumber = 16;
                room16(); //start room
            }
            case 17 -> {
                Player.playerX = 2;
                Player.playerY = 2;
                roomNumber = 17;
                room17(); //start room
            }
            case 18 -> {
                Player.playerX = 3;
                Player.playerY = 1;
                roomNumber = 18;
                room18(); //start room
            }
            case 19 -> {
                Player.playerX = 2;
                Player.playerY = 1;
                roomNumber = 19;
                room19(); //start room
            }
            case 20 -> {
                Player.playerX = 2;
                Player.playerY = 0;
                roomNumber = 20;
                room20(); //start room
            }
            case 21 -> {
                Player.playerX = 1;
                Player.playerY = 4;
                roomNumber = 21;
                room21();
            }
            default ->
                Main.startMenu();
        }
    }

    public static void resetAll() { //reset all
        roomSave = 1;
        roomNumber = 0;
    }

    private static void room1() throws InterruptedException { //0
        TextEngine.printWithDelays("You step into the open world.\nThe sunlight overwhelms your senses, momentarily blinding you after the darkness of the cave.", false);
        TextEngine.printWithDelays("As your vision clears, you notice winding paths stretching out before you:\n    one leads north, another to the west,\n    and in the distance, you can make out a village to the east.\n\n         How overwhelming...", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "west" + resetColor + ", or " + yellowColor + "The Village" + resetColor + " to decide", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 4;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 2;
                    Main.loadSave();
                }
                case "the village" -> {
                    Main.saveSpace("Village");
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room2() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("As you walk, you notice the winding paths leading back to the village,\n    where the comforting sights and sounds of town life await you.", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + " or " + yellowColor + "east" + resetColor, true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim().trim()) {
                case "north" -> {
                    roomSave = 3;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 1;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room3() throws InterruptedException {
        if (Dungeon.completedDungeons == 0) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to the Meadow Dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Meadow dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "The Meadow" + resetColor + " to make your choice", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 8;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 4;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 2;
                    Main.loadSave();
                }
                case "the meadow" -> {
                    Main.saveSpace("Meadow Dungeon");
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room4() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 7;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 5;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 1;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 3;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room5() throws InterruptedException {
        if (Dungeon.completedDungeons > 1) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Mountain Cave Dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrets! you have beaten The Mountain Cave dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "The Mountain Cave" + resetColor + ", " + yellowColor + "The Village" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 6;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 4;
                    Main.loadSave();
                }
                case "the mountain cave" -> {
                    if (Dungeon.completedDungeons > 1) {
                        Main.saveSpace("Mountain Cave Dungeon");
                        Main.loadSave();
                    } else {
                        TextEngine.printWithDelays("You must complete The Dark Forest first.\nTry going west", true);
                    }
                }
                case "the village" -> {
                    Main.saveSpace("Village");
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room6() throws InterruptedException {
        if (Dungeon.completedDungeons > 2) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Mountain Top dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrets! you have beaten The Mountain Cave dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "south" + resetColor + ", " + yellowColor + "west" + resetColor + ", or " + yellowColor + "The Mountain Top" + resetColor + "  to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 10;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 5;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 7;
                    Main.loadSave();
                }
                case "the mountain top" -> {
                    if (Dungeon.completedDungeons > 2) {
                        Main.saveSpace("Mountain Top Dungeon");
                        Main.loadSave();
                    } else {
                        TextEngine.printWithDelays("You must complete The Mountain Cave dungeons first, try going west.", true);
                    }
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room7() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + "  to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 9;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 6;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 4;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 8;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room8() throws InterruptedException {
        if (Dungeon.completedDungeons == 1) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Dark Forest dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Dark Forest dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "The Dark Forest" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 21;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 7;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 3;
                    Main.loadSave();
                }
                case "the dark forest" -> {
                    if (Dungeon.completedDungeons > 0) {
                        Main.saveSpace("Dark Forest Dungeon");
                        Main.loadSave();
                    } else {
                        TextEngine.printWithDelays("You must complete The Meadow dungeon first.\nTry going south", true);
                    }
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room9() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + "  to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 12;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 10;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 7;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 21;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room10() throws InterruptedException {
        if (Dungeon.completedDungeons > 3) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Oasis dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Mountain Cave dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "south" + resetColor + ", " + yellowColor + "west" + resetColor + ", " + yellowColor + "The Desert Oasis" + resetColor + "  to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 11;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 6;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 9;
                    Main.loadSave();
                }
                case "the desert oasis" -> {
                    if (Dungeon.completedDungeons > 3) {
                        Main.saveSpace("Desert Oasis Dungeon");
                        Main.loadSave();
                    } else {
                        TextEngine.printWithDelays("You must complete The Mountain Top dungeon first.\n Try going to the south", true);
                    }
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room11() throws InterruptedException {
        if (Dungeon.completedDungeons > 4) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Plains dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Desert Plains dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("Worrning: going into a dungeon you will trigger fights, but you might find something in the rooms\nIf you do not DIE", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "The Village" + resetColor + ", " + yellowColor + "south" + resetColor + ", " + yellowColor + "west" + resetColor + ", or " + yellowColor + "The Desert Plains" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "south" -> {
                    roomSave = 10;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 12;
                    Main.loadSave();
                }
                case "the desert plains" -> {
                    if (Dungeon.completedDungeons > 4) {
                        Main.saveSpace("Desert Plains Dungeon");
                        Main.loadSave();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Oasis dungeons first.\nTry going to the south east/north", true);
                    }
                }
                case "the village" -> {
                    Main.saveSpace("Village");
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room12() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 17;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 11;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 9;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 13;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room13() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 16;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 12;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 21;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 14;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room14() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + " or " + yellowColor + "east" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 15;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 13;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room15() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "The village" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "east" -> {
                    roomSave = 16;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 14;
                    Main.loadSave();
                }
                case "the village" -> {
                    Village.startRoom();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room16() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + " or " + yellowColor + "west" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "east" -> {
                    roomSave = 17;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 13;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 15;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room17() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "south" + resetColor + ", " + yellowColor + "west" + resetColor + ", or " + yellowColor + "The Village" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 19;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 12;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 16;
                    Main.loadSave();
                }
                case "the village" -> {
                    Village.startRoom();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room18() throws InterruptedException {
        if (Dungeon.completedDungeons > 5) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Pyramid dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Mountain Cave dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "The Ocean Kingdom" + resetColor + ", " + yellowColor + "The Village" + resetColor + ", " + yellowColor + "west" + resetColor + " or " + yellowColor + "The Desert Pyramid" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "west" -> {
                    roomSave = 19;
                    Main.loadSave();
                }
                case "the desert pyramid" -> {
                    if (Dungeon.completedDungeons > 5) {
                        Main.saveSpace("Desert Pyramid Dungeon");
                        Main.loadSave();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Plains dungeons first.\nTry going to the north/east", true);
                    }
                }
                case "the village" -> {
                    Main.saveSpace("Village");
                    Main.loadSave();
                }
                case "the ocean kingdom" -> {
                    if (Dungeon.completedDungeons > 6) {
                        Main.saveSpace("Ocean Kingdom Dungeon");
                        Main.loadSave();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Pyramis dungeon first.\nTry going to the north", true);
                    }
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room19() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", or " + yellowColor + "south" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 20;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 18;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 17;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room20() throws InterruptedException {
        if (Dungeon.completedDungeons > 6) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Ocean Kingdom dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Mountain Cave dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "south" + resetColor + " or " + yellowColor + "The Ocean Kingdom" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "south" -> {
                    roomSave = 19;
                    Main.loadSave();
                }
                case "the ocean kingdom" -> {
                    if (Dungeon.completedDungeons > 6) {
                        Main.saveSpace("Ocean Kingdom Dungeon");
                        Main.loadSave();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Pyramis dungeon first.\nTry going to the north", true);
                    }
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room21() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", or " + yellowColor + "south" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 13;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 9;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 8;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
}
