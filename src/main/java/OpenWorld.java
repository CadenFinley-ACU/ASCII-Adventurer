/**
 * Open World Class
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class OpenWorld extends Room {
    
    static int roomSave = 74;
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
            case 2 -> {
                Player.playerX = 2;
                Player.playerY = 0;
                roomNumber = 2;
                room2(); //start room
            }
            case 3 -> {
                Player.playerX = 3;
                Player.playerY = 0;
                roomNumber = 3;
                room3(); //start room
            }
            case 4 -> {
                Player.playerX = 4;
                Player.playerY = 0;
                roomNumber = 4;
                room4(); //start room
            }
            case 5 -> {
                Player.playerX = 5;
                Player.playerY = 0;
                roomNumber = 5;
                room5(); //start room
            }
            case 6 -> {
                Player.playerX = 6;
                Player.playerY = 0;
                roomNumber = 6;
                room6(); //start room
            }
            case 8 -> {
                Player.playerX = 1;
                Player.playerY = 1;
                roomNumber = 8;
                room8(); //start room
            }
            case 9 -> {
                Player.playerX = 5;
                Player.playerY = 1;
                roomNumber = 9;
                room9(); //start room
            }
            case 10 -> {
                Player.playerX = 6;
                Player.playerY = 1;
                roomNumber = 10;
                room10(); //start room
            }
            case 11 -> {
                Player.playerX = 7;
                Player.playerY = 1;
                roomNumber = 11;
                room11(); //start room
            }
            case 12 -> {
                Player.playerX = 1;
                Player.playerY = 2;
                roomNumber = 12;
                room12(); //start room
            }
            case 13 -> {
                Player.playerX = 5;
                Player.playerY = 2;
                roomNumber = 13;
                room13(); //start room
            }
            case 14 -> {
                Player.playerX = 6;
                Player.playerY = 2;
                roomNumber = 14;
                room14(); //start room
            }
            case 15 -> {
                Player.playerX = 7;
                Player.playerY = 2;
                roomNumber = 15;
                room15(); //start room
            }
            case 16 -> {
                Player.playerX = 1;
                Player.playerY = 3;
                roomNumber = 16;
                room16(); //start room
            }
            case 17 -> {
                Player.playerX = 5;
                Player.playerY = 3;
                roomNumber = 17;
                room17(); //start room
            }
            case 18 -> {
                Player.playerX = 6;
                Player.playerY = 3;
                roomNumber = 18;
                room18(); //start room
            }
            case 19 -> {
                Player.playerX = 7;
                Player.playerY = 3;
                roomNumber = 19;
                room19(); //start room
            }
            case 21 -> {
                Player.playerX = 0;
                Player.playerY = 4;
                roomNumber = 21;
                room21();
            }
            case 22 -> {
                Player.playerX = 1;
                Player.playerY = 4;
                roomNumber = 22;
                room22();
            }
            case 25 -> {
                Player.playerX = 5;
                Player.playerY = 4;
                roomNumber = 25;
                room25();
            }
            case 26 -> {
                Player.playerX = 6;
                Player.playerY = 4;
                roomNumber = 26;
                room26();
            }
            case 27 -> {
                Player.playerX = 3;
                Player.playerY = 8;
                roomNumber = 27;
                room27();
            }
            case 28 -> {
                Player.playerX = 7;
                Player.playerY = 4;
                roomNumber = 28;
                room28();
            }
            case 29 -> {
                Player.playerX = 8;
                Player.playerY = 4;
                roomNumber = 29;
                room29();
            }
            case 30 -> {
                Player.playerX = 0;
                Player.playerY = 5;
                roomNumber = 30;
                room30();
            }
            case 31 -> {
                Player.playerX = 1;
                Player.playerY = 5;
                roomNumber = 31;
                room31();
            }
            case 32 -> {
                Player.playerX = 2;
                Player.playerY = 5;
                roomNumber = 32;
                room32();
            }
            case 33 -> {
                Player.playerX = 5;
                Player.playerY = 5;
                roomNumber = 33;
                room33();
            }
            case 34 -> {
                Player.playerX = 6;
                Player.playerY = 5;
                roomNumber = 34;
                room34();
            }
            case 35 -> {
                Player.playerX = 7;
                Player.playerY = 5;
                roomNumber = 35;
                room35();
            }
            case 36 -> {
                Player.playerX = 8;
                Player.playerY = 5;
                roomNumber = 36;
                room36();
            }
            case 37 -> {
                Player.playerX = 0;
                Player.playerY = 6;
                roomNumber = 37;
                room37();
            }
            case 38 -> {
                Player.playerX = 1;
                Player.playerY = 6;
                roomNumber = 38;
                room38();
            }
            case 39 -> {
                Player.playerX = 2;
                Player.playerY = 6;
                roomNumber = 39;
                room39();
            }
            case 40 -> {
                Player.playerX = 3;
                Player.playerY = 6;
                roomNumber = 40;
                room40();
            }
            case 41 -> {
                Player.playerX = 4;
                Player.playerY = 6;
                roomNumber = 41;
                room41();
            }
            case 42 -> {
                Player.playerX = 5;
                Player.playerY = 6;
                roomNumber = 42;
                room42();
            }
            case 43 -> {
                Player.playerX = 6;
                Player.playerY = 6;
                roomNumber = 43;
                room43();
            }
            case 44 -> {
                Player.playerX = 7;
                Player.playerY = 6;
                roomNumber = 44;
                room44();
            }
            case 45 -> {
                Player.playerX = 8;
                Player.playerY = 6;
                roomNumber = 45;
                room45();
            }
            case 47 -> {
                Player.playerX = 1;
                Player.playerY = 7;
                roomNumber = 47;
                room47();
            }
            case 48 -> {
                Player.playerX = 2;
                Player.playerY = 7;
                roomNumber = 48;
                room48();
            }
            case 49 -> {
                Player.playerX = 3;
                Player.playerY = 7;
                roomNumber = 49;
                room49();
            }
            case 50 -> {
                Player.playerX = 4;
                Player.playerY = 7;
                roomNumber = 50;
                room50();
            }
            case 51 -> {
                Player.playerX = 5;
                Player.playerY = 7;
                roomNumber = 51;
                room51();
            }
            case 52 -> {
                Player.playerX = 6;
                Player.playerY = 7;
                roomNumber = 52;
                room52();
            }
            case 53 -> {
                Player.playerX = 7;
                Player.playerY = 7;
                roomNumber = 53;
                room53();
            }
            case 54 -> {
                Player.playerX = 8;
                Player.playerY = 7;
                roomNumber = 54;
                room54();
            }
            case 55 -> {
                Player.playerX = 2;
                Player.playerY = 8;
                roomNumber = 55;
                room55();
            }
            case 57 -> {
                Player.playerX = 4;
                Player.playerY = 8;
                roomNumber = 57;
                room57();
            }
            case 58 -> {
                Player.playerX = 5;
                Player.playerY = 8;
                roomNumber = 58;
                room58();
            }
            case 59 -> {
                Player.playerX = 6;
                Player.playerY = 8;
                roomNumber = 59;
                room59();
            }
            case 60 -> {
                Player.playerX = 7;
                Player.playerY = 8;
                roomNumber = 60;
                room60();
            }
            case 62 -> {
                Player.playerX = 4;
                Player.playerY = 5;
                roomNumber = 62;
                room62();
            }
            case 64 -> {
                Player.playerX = 2;
                Player.playerY = 9;
                roomNumber = 64;
                room64();
            }
            case 65 -> {
                Player.playerX = 3;
                Player.playerY = 9;
                roomNumber = 65;
                room65();
            }
            case 66 -> {
                Player.playerX = 4;
                Player.playerY = 9;
                roomNumber = 66;
                room66();
            }
            case 67 -> {
                Player.playerX = 5;
                Player.playerY = 9;
                roomNumber = 67;
                room67();
            }
            case 68 -> {
                Player.playerX = 6;
                Player.playerY = 9;
                roomNumber = 68;
                room68();
            }
            case 69 -> {
                Player.playerX = 7;
                Player.playerY = 9;
                roomNumber = 69;
                room69();
            }
            case 72 -> {
                Player.playerX = 2;
                Player.playerY = 10;
                roomNumber = 72;
                room72();
            }
            case 73 -> {
                Player.playerX = 3;
                Player.playerY = 10;
                roomNumber = 73;
                room73();
            }
            case 74 -> {
                Player.playerX = 4;
                Player.playerY = 10;
                roomNumber = 74;
                room74();
            }
        }
    }

    public static void resetAll() { //reset all
        roomSave = 74;
        roomNumber = 0;
    }
    private static void room2() throws InterruptedException {
        if (Dungeon.completedDungeons <= 7) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Plains dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Desert Plains dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }        TextEngine.printWithDelays("As you walk, you notice the winding paths leading back to the village,\n    where the comforting sights and sounds of town life await you.", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "east" + resetColor + " or " + yellowColor + "The Ocean Kingdom" + resetColor+ " to decide", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim().trim()) {
                case "east" -> {
                    roomSave = 3;
                    Main.loadSave();
                }case "the ocean kingdom" -> {
                    if (Dungeon.completedDungeons > 7) {
                        DesertOasisDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Pyramid dungeon first.\n Try going to the south", true);
                    }
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room3() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "east" + resetColor + ", or " + yellowColor + "west" + resetColor + " to make your choice", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "east" -> {
                    roomSave = 4;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 2;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room4() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "east" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "east" -> {
                    roomSave = 5;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 3;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room5() throws InterruptedException {
        if (Dungeon.completedDungeons <= 2) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Mountain Cave Dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrets! you have beaten The Mountain Cave dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "east" -> {
                    roomSave = 6;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 9;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 4;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room6() throws InterruptedException {
        if (Dungeon.completedDungeons <= 6) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Mountain Top dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrets! you have beaten The Mountain Cave dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "The Mountain Top" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey", true);
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
                }case "west" -> {
                    roomSave = 5;
                    Main.loadSave();
                }case "the mountain top" -> {
                    if (Dungeon.completedDungeons > 2) {
                        MountainTopDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Mountain Cave dungeons first, try going west.", true);
                    }
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room8() throws InterruptedException {
        if (Dungeon.completedDungeons <= 7) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Dark Forest dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Dark Forest dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", or " + yellowColor + "south" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "the ocean kingdom" -> {
                    if (Dungeon.completedDungeons > 7) {
                        DesertOasisDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Pyramid dungeon first.\n Try going to the south", true);
                    }
                }case "south" -> {
                    roomSave = 12;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room9() throws InterruptedException {
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
                    roomSave = 5;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 10;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 13;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room10() throws InterruptedException {
        if (Dungeon.completedDungeons <= 4) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Oasis dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Mountain Cave dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + "  to continue your journey", true);
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
                }case "east" -> {
                    roomSave = 11;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 14;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 9;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room11() throws InterruptedException {
        if (Dungeon.completedDungeons <= 6) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Plains dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Desert Plains dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("Worrning: going into a dungeon you will trigger fights, but you might find something in the rooms\nIf you do not DIE", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "The Mountain Top" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "the mountain top" -> {
                    if (Dungeon.completedDungeons > 2) {
                        MountainTopDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Mountain Cave dungeons first, try going west.", true);
                    }
                }case "south" -> {
                    roomSave = 15;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 10;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room12() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", or " + yellowColor + "south" + resetColor + " to continue your journey", true);
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
                }case "south" -> {
                    roomSave = 16;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room13() throws InterruptedException {
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
                    roomSave = 9;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 14;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 17;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room14() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " +yellowColor+ "east" +resetColor+ ", " +yellowColor+ "south" +resetColor+ ", or " +yellowColor+ "west" +resetColor+ " to continue your journey", true);
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
                }case "east" -> {
                    roomSave = 15;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 18;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 13;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room15() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey", true);
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
                }case "south" -> {
                    roomSave = 19;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 14;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room16() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", or " + yellowColor + "south" + resetColor + " to continue your journey", true);
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
                }case "south" -> {
                    roomSave = 22;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room17() throws InterruptedException {
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
                    roomSave = 13;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 18;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 25;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room18() throws InterruptedException {
        if (Dungeon.completedDungeons <= 6) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Pyramid dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Mountain Cave dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + " or " + yellowColor + "west" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 14;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 19;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 26;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 17;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room19() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "The Village" + resetColor + ", " + yellowColor + "south" + resetColor + " or " +yellowColor+ "west" +resetColor+ " to continue your journey", true);
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
                }case "the village" -> {
                    Main.saveSpace("Village");
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 28;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 18;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room21() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "east" + resetColor + ", or " + yellowColor + "south" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase()) {
                case "east" -> {
                    roomSave = 22;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 30;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room22() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
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
                }case "south" -> {
                    roomSave = 31;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 21;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room25() throws InterruptedException {
        if (Dungeon.completedDungeons <= 5) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Plains dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Desert Plains dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }        
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "The Mountain Cave" + resetColor + " to continue your journey ", true);
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
                }case "east" -> {
                    roomSave = 26;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 33;
                    Main.loadSave();
                }case "the mountain cave" -> {
                    if (Dungeon.completedDungeons > 1) {
                        MountainCaveDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Dark Forest first.\nTry going west", true);
                    }
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room26() throws InterruptedException {
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
                    roomSave = 18;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 28;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 34;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 25;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room27() throws InterruptedException {
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
                    roomSave = 49;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 57;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 65;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 55;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room28() throws InterruptedException {
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
                    roomSave = 19;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 29;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 35;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 26;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room29() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "The Village" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "the village" -> {
                    Main.saveSpace("Village");
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 36;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 28;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room30() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", or " + yellowColor + "south" + resetColor + " to continue your journey ", true);
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
                }case "east" -> {
                    roomSave = 31;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 37;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room31() throws InterruptedException {
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
                    roomSave = 22;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 32;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 38;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 30;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room32() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "south" -> {
                    roomSave = 39;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 31;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room33() throws InterruptedException {
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
                    roomSave = 25;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 34;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 42;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 62;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room34() throws InterruptedException {
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
                    roomSave = 26;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 35;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 43;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 33;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room35() throws InterruptedException {
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
                    roomSave = 28;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 36;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 44;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 34;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room36() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 29;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 45;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 35;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room37() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", or " + yellowColor + "The Village" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 30;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 38;
                    Main.loadSave();
                }case "the village" -> {
                    Main.saveSpace("Village");
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room38() throws InterruptedException {
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
                    roomSave = 31;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 39;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 47;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 37;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room39() throws InterruptedException {
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
                    roomSave = 32;
                    Main.loadSave();
                }case "east" -> {
                    roomSave =40;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 48;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 38;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room40() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "east" -> {
                    roomSave = 41;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 49;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 39;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room41() throws InterruptedException {
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
                    roomSave = 62;
                    Main.loadSave();
                } case "east" -> {
                    roomSave = 42;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 50;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 40;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room42() throws InterruptedException {
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
                    roomSave = 33;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 43;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 51;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 41;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room43() throws InterruptedException {
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
                    roomSave = 34;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 44;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 52;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 42;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room44() throws InterruptedException {
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
                    roomSave = 35;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 45;
                    Main.loadSave();
                } case "south" -> {
                    roomSave = 53;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 43;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room45() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 36;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 54;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 44;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room47() throws InterruptedException {
        if (Dungeon.completedDungeons <= 4) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Plains dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Desert Plains dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }        
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "The Dark Forest" + resetColor + ", or " + yellowColor + "The Village" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 38;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 48;
                    Main.loadSave();
                }case "the dark forest" -> {
                    if (Dungeon.completedDungeons > 0) {
                        DarkForestDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Meadow dungeon first.\nTry going south", true);
                    }
                }case "the village" -> {
                    Main.saveSpace("Village");
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room48() throws InterruptedException {
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
                    roomSave = 39;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 49;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 55;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 47;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room49() throws InterruptedException {
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
                    roomSave = 40;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 50;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 27;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 48;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room50() throws InterruptedException {
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
                    roomSave = 41;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 51;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 57;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 49;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room51() throws InterruptedException {
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
                    roomSave = 42;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 52;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 58;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 50;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room52() throws InterruptedException {
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
                    roomSave = 43;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 53;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 59;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 51;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room53() throws InterruptedException {
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
                    roomSave = 44;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 54;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 60;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 52;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room54() throws InterruptedException {
        if (Dungeon.completedDungeons <= 2) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Plains dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Desert Plains dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }        
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "west" + resetColor + ", or " +yellowColor+ "The Dark Forest" +resetColor+ " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 45;
                    Main.loadSave();
                }case "the dark forest" -> {
                    if (Dungeon.completedDungeons > 0) {
                        DarkForestDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Meadow dungeon first.\nTry going south", true);
                    }
                }case "west" -> {
                    roomSave = 53;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room55() throws InterruptedException {
        if (Dungeon.completedDungeons <= 4) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Plains dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Desert Plains dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }        
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "The Desert Pyramid" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 48;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 27;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 64;
                    Main.loadSave();
                }case "the desrt pryramid" -> {
                    if (Dungeon.completedDungeons > 3) {
                        DarkForestDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Plains dungeon first.\nTry going south", true);
                    }
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room57() throws InterruptedException {
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
                    roomSave = 50;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 58;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 66;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 27;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room58() throws InterruptedException {
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
                    roomSave = 51;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 59;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 67;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 57;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room59() throws InterruptedException {
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
                    roomSave = 52;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 60;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 68;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 58;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room60() throws InterruptedException {
        if (Dungeon.completedDungeons <= 1) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Plains dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Desert Plains dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "south" + resetColor + ", " + yellowColor + "west" + resetColor + ", or " +yellowColor+ "The Dark Forest" +resetColor+ " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 53;
                    Main.loadSave();
                }case "the dark forest" -> {
                    if (Dungeon.completedDungeons > 0) {
                        DarkForestDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Meadow dungeon first.\nTry going south", true);
                    }
                }case "south" -> {
                    roomSave = 69;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 59;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room62() throws InterruptedException {
        if (Dungeon.completedDungeons <= 5) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Plains dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Desert Plains dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }        
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "The Mountain Cave" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "east" -> {
                    roomSave = 33;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 41;
                    Main.loadSave();
                }case "the mountain cave" -> {
                    if (Dungeon.completedDungeons > 1) {
                        MountainCaveDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Dark Forest first.\nTry going west", true);
                    }
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room64() throws InterruptedException {
        if (Dungeon.completedDungeons <= 3) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Plains dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Desert Plains dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }        
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "The Desert Plains" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 55;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 65;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 72;
                    Main.loadSave();
                }case "the desert plains" -> {
                    if (Dungeon.completedDungeons > 4) {
                        DesertPlainsDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Oasis dungeons first.\nTry going to the south east/north", true);
                    }
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room65() throws InterruptedException {
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
                    roomSave = 27;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 66;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 73;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 64;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room66() throws InterruptedException {
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
                    roomSave = 57;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 67;
                    Main.loadSave();
                }case "south" -> {
                    roomSave = 74;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 65;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room67() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "west" + resetColor + ", or "+yellowColor+ "The Village" +resetColor+ " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 58;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 68;
                    Main.loadSave();
                }case "the village" -> {
                    Main.saveSpace("Village");
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 66;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room68() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 59;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 69;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 67;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room69() throws InterruptedException {
        if (Dungeon.completedDungeons < 1) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Meadow dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Desert Plains dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }        
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "west" + resetColor + ", or " +yellowColor+ "The Meadow" +resetColor+ " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 60;
                    Main.loadSave();
                }case "the meadow" -> {
                    MeadowDungeon.startRoom();
                }case "west" -> {
                    roomSave = 68;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room72() throws InterruptedException {
        if (Dungeon.completedDungeons <= 2) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Plains dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Desert Plains dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }        
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", or " +yellowColor+ "The Desert Oasis" +resetColor+ " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 64;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 73;
                    Main.loadSave();
                }case "the desert oasis" -> {
                    if (Dungeon.completedDungeons > 3) {
                        DesertOasisDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Mountain Top dungeon first.\n Try going to the south", true);
                    }
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room73() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 65;
                    Main.loadSave();
                }case "east" -> {
                    roomSave = 74;
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 72;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room74() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "west" + resetColor + ", or "+yellowColor+ "the village" +resetColor+ " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 66;
                    Main.loadSave();
                }case "the village" -> {
                    Main.saveSpace("Village");
                    Main.loadSave();
                }case "west" -> {
                    roomSave = 73;
                    Main.loadSave();
                }default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
}
