
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Open World Class Written by Albert Tucker and Grijesh Shrestha
 *
 * Text Adventure Game SE374 F24 Final Project
 *
 */
public class OpenWorld extends Room {

    static int roomSave = 74;
    public static String holdCommand = null;
    static int roomNumber = 0;
    static int saveRoomNumber = 0;
    static String resetColor = "\033[0m"; // reset to default color
    static String yellowColor = "\033[1;33m"; // yellow color
    private static final Random rand = new Random();
    public static int previousRoomSave = 74;

    public static int numberOfEnemies;
    public static String enemyType;
    public static boolean encounter = false;
    private static final List<String> enemies = new ArrayList<>(List.of("Goblin", "Skeleton", "Slime", "Bandit", "Spider"));//at some point make some enemies environment exclusive
    public static boolean inFight = false;

    public static void startRoom() throws InterruptedException { //start room
        room = "OpenWorld";
        Main.checkSave(room);
        GameSaveSerialization.saveGame();
        Main.screenRefresh();

        if (checkChangeInRoom()) {
            //determine open world encounter
            numberOfEnemies = rand.nextInt(3);
            enemyType = enemies.get(rand.nextInt(enemies.size())); //at some point make some enemies environment exclusive
            boolean encounter1 = rand.nextBoolean();
            boolean encounter2 = rand.nextBoolean();
            encounter = encounter1 || encounter2;
        }

        switch (roomSave) {
            case 1 -> {
                Player.playerX = 0;
                Player.playerY = 0;
                roomNumber = 1;
                Village.startRoom();
                drawCurrentRoom();
            }
            case 2 -> {
                Player.playerX = 2;
                Player.playerY = 0;
                roomNumber = 2;
                fightEnemies();
                drawCurrentRoom();
                room2(); //start room
            }
            case 3 -> {
                Player.playerX = 3;
                Player.playerY = 0;
                roomNumber = 3;
                fightEnemies();
                drawCurrentRoom();
                room3(); //start room
            }
            case 4 -> {
                Player.playerX = 4;
                Player.playerY = 0;
                roomNumber = 4;
                fightEnemies();
                drawCurrentRoom();
                room4(); //start room
            }
            case 5 -> {
                Player.playerX = 5;
                Player.playerY = 0;
                roomNumber = 5;
                fightEnemies();
                drawCurrentRoom();
                room5(); //start room
            }
            case 6 -> {
                Player.playerX = 6;
                Player.playerY = 0;
                roomNumber = 6;
                fightEnemies();
                drawCurrentRoom();
                room6(); //start room
            }
            case 8 -> {
                Player.playerX = 1;
                Player.playerY = 1;
                roomNumber = 8;
                fightEnemies();
                drawCurrentRoom();
                room8(); //start room
            }
            case 9 -> {
                Player.playerX = 5;
                Player.playerY = 1;
                roomNumber = 9;
                fightEnemies();
                drawCurrentRoom();
                room9(); //start room
            }
            case 10 -> {
                Player.playerX = 6;
                Player.playerY = 1;
                roomNumber = 10;
                fightEnemies();
                drawCurrentRoom();
                room10(); //start room
            }
            case 11 -> {
                Player.playerX = 7;
                Player.playerY = 1;
                roomNumber = 11;
                fightEnemies();
                drawCurrentRoom();
                room11(); //start room
            }
            case 12 -> {
                Player.playerX = 1;
                Player.playerY = 2;
                roomNumber = 12;
                fightEnemies();
                drawCurrentRoom();
                room12(); //start room
            }
            case 13 -> {
                Player.playerX = 5;
                Player.playerY = 2;
                roomNumber = 13;
                fightEnemies();
                drawCurrentRoom();
                room13(); //start room
            }
            case 14 -> {
                Player.playerX = 6;
                Player.playerY = 2;
                roomNumber = 14;
                fightEnemies();
                drawCurrentRoom();
                room14(); //start room
            }
            case 15 -> {
                Player.playerX = 7;
                Player.playerY = 2;
                roomNumber = 15;
                fightEnemies();
                drawCurrentRoom();
                room15(); //start room
            }
            case 16 -> {
                Player.playerX = 1;
                Player.playerY = 3;
                roomNumber = 16;
                fightEnemies();
                drawCurrentRoom();
                room16(); //start room
            }
            case 17 -> {
                Player.playerX = 5;
                Player.playerY = 3;
                roomNumber = 17;
                fightEnemies();
                drawCurrentRoom();
                room17(); //start room
            }
            case 18 -> {
                Player.playerX = 6;
                Player.playerY = 3;
                roomNumber = 18;
                fightEnemies();
                drawCurrentRoom();
                room18(); //start room
            }
            case 19 -> {
                Player.playerX = 7;
                Player.playerY = 3;
                roomNumber = 19;
                fightEnemies();
                drawCurrentRoom();
                room19(); //start room
            }
            case 21 -> {
                Player.playerX = 0;
                Player.playerY = 4;
                roomNumber = 21;
                fightEnemies();
                drawCurrentRoom();
                room21();
            }
            case 22 -> {
                Player.playerX = 1;
                Player.playerY = 4;
                roomNumber = 22;
                fightEnemies();
                drawCurrentRoom();
                room22();
            }
            case 25 -> {
                Player.playerX = 5;
                Player.playerY = 4;
                roomNumber = 25;
                fightEnemies();
                drawCurrentRoom();
                room25();
            }
            case 26 -> {
                Player.playerX = 6;
                Player.playerY = 4;
                roomNumber = 26;
                fightEnemies();
                drawCurrentRoom();
                room26();
            }
            case 27 -> {
                Player.playerX = 3;
                Player.playerY = 8;
                roomNumber = 27;
                fightEnemies();
                drawCurrentRoom();
                room27();
            }
            case 28 -> {
                Player.playerX = 7;
                Player.playerY = 4;
                roomNumber = 28;
                fightEnemies();
                drawCurrentRoom();
                room28();
            }
            case 29 -> {
                Player.playerX = 8;
                Player.playerY = 4;
                roomNumber = 29;
                fightEnemies();
                drawCurrentRoom();
                room29();
            }
            case 30 -> {
                Player.playerX = 0;
                Player.playerY = 5;
                roomNumber = 30;
                fightEnemies();
                drawCurrentRoom();
                room30();
            }
            case 31 -> {
                Player.playerX = 1;
                Player.playerY = 5;
                roomNumber = 31;
                fightEnemies();
                drawCurrentRoom();
                room31();
            }
            case 32 -> {
                Player.playerX = 2;
                Player.playerY = 5;
                roomNumber = 32;
                fightEnemies();
                drawCurrentRoom();
                room32();
            }
            case 33 -> {
                Player.playerX = 5;
                Player.playerY = 5;
                roomNumber = 33;
                fightEnemies();
                drawCurrentRoom();
                room33();
            }
            case 34 -> {
                Player.playerX = 6;
                Player.playerY = 5;
                roomNumber = 34;
                fightEnemies();
                drawCurrentRoom();
                room34();
            }
            case 35 -> {
                Player.playerX = 7;
                Player.playerY = 5;
                roomNumber = 35;
                fightEnemies();
                drawCurrentRoom();
                room35();
            }
            case 36 -> {
                Player.playerX = 8;
                Player.playerY = 5;
                roomNumber = 36;
                fightEnemies();
                drawCurrentRoom();
                room36();
            }
            case 37 -> {
                Player.playerX = 0;
                Player.playerY = 6;
                roomNumber = 37;
                fightEnemies();
                drawCurrentRoom();
                room37();
            }
            case 38 -> {
                Player.playerX = 1;
                Player.playerY = 6;
                roomNumber = 38;
                fightEnemies();
                drawCurrentRoom();
                room38();
            }
            case 39 -> {
                Player.playerX = 2;
                Player.playerY = 6;
                roomNumber = 39;
                fightEnemies();
                drawCurrentRoom();
                room39();
            }
            case 40 -> {
                Player.playerX = 3;
                Player.playerY = 6;
                roomNumber = 40;
                fightEnemies();
                drawCurrentRoom();
                room40();
            }
            case 41 -> {
                Player.playerX = 4;
                Player.playerY = 6;
                roomNumber = 41;
                fightEnemies();
                drawCurrentRoom();
                room41();
            }
            case 42 -> {
                Player.playerX = 5;
                Player.playerY = 6;
                roomNumber = 42;
                fightEnemies();
                drawCurrentRoom();
                room42();
            }
            case 43 -> {
                Player.playerX = 6;
                Player.playerY = 6;
                roomNumber = 43;
                fightEnemies();
                drawCurrentRoom();
                room43();
            }
            case 44 -> {
                Player.playerX = 7;
                Player.playerY = 6;
                roomNumber = 44;
                fightEnemies();
                drawCurrentRoom();
                room44();
            }
            case 45 -> {
                Player.playerX = 8;
                Player.playerY = 6;
                roomNumber = 45;
                fightEnemies();
                drawCurrentRoom();
                room45();
            }
            case 47 -> {
                Player.playerX = 1;
                Player.playerY = 7;
                roomNumber = 47;
                fightEnemies();
                drawCurrentRoom();
                room47();
            }
            case 48 -> {
                Player.playerX = 2;
                Player.playerY = 7;
                roomNumber = 48;
                fightEnemies();
                drawCurrentRoom();
                room48();
            }
            case 49 -> {
                Player.playerX = 3;
                Player.playerY = 7;
                roomNumber = 49;
                fightEnemies();
                drawCurrentRoom();
                room49();
            }
            case 50 -> {
                Player.playerX = 4;
                Player.playerY = 7;
                roomNumber = 50;
                fightEnemies();
                drawCurrentRoom();
                room50();
            }
            case 51 -> {
                Player.playerX = 5;
                Player.playerY = 7;
                roomNumber = 51;
                fightEnemies();
                drawCurrentRoom();
                room51();
            }
            case 52 -> {
                Player.playerX = 6;
                Player.playerY = 7;
                roomNumber = 52;
                fightEnemies();
                drawCurrentRoom();
                room52();
            }
            case 53 -> {
                Player.playerX = 7;
                Player.playerY = 7;
                roomNumber = 53;
                fightEnemies();
                drawCurrentRoom();
                room53();
            }
            case 54 -> {
                Player.playerX = 8;
                Player.playerY = 7;
                roomNumber = 54;
                fightEnemies();
                drawCurrentRoom();
                room54();
            }
            case 55 -> {
                Player.playerX = 2;
                Player.playerY = 8;
                roomNumber = 55;
                fightEnemies();
                drawCurrentRoom();
                room55();
            }
            case 57 -> {
                Player.playerX = 4;
                Player.playerY = 8;
                roomNumber = 57;
                fightEnemies();
                drawCurrentRoom();
                room57();
            }
            case 58 -> {
                Player.playerX = 5;
                Player.playerY = 8;
                roomNumber = 58;
                fightEnemies();
                drawCurrentRoom();
                room58();
            }
            case 59 -> {
                Player.playerX = 6;
                Player.playerY = 8;
                roomNumber = 59;
                fightEnemies();
                drawCurrentRoom();
                room59();
            }
            case 60 -> {
                Player.playerX = 7;
                Player.playerY = 8;
                roomNumber = 60;
                fightEnemies();
                drawCurrentRoom();
                room60();
            }
            case 62 -> {
                Player.playerX = 4;
                Player.playerY = 5;
                roomNumber = 62;
                fightEnemies();
                drawCurrentRoom();
                room62();
            }
            case 64 -> {
                Player.playerX = 2;
                Player.playerY = 9;
                roomNumber = 64;
                fightEnemies();
                drawCurrentRoom();
                room64();
            }
            case 65 -> {
                Player.playerX = 3;
                Player.playerY = 9;
                roomNumber = 65;
                fightEnemies();
                drawCurrentRoom();
                room65();
            }
            case 66 -> {
                Player.playerX = 4;
                Player.playerY = 9;
                roomNumber = 66;
                fightEnemies();
                drawCurrentRoom();
                room66();
            }
            case 67 -> {
                Player.playerX = 5;
                Player.playerY = 9;
                roomNumber = 67;
                fightEnemies();
                drawCurrentRoom();
                room67();
            }
            case 68 -> {
                Player.playerX = 6;
                Player.playerY = 9;
                roomNumber = 68;
                fightEnemies();
                drawCurrentRoom();
                room68();
            }
            case 69 -> {
                Player.playerX = 7;
                Player.playerY = 9;
                roomNumber = 69;
                fightEnemies();
                drawCurrentRoom();
                room69();
            }
            case 72 -> {
                Player.playerX = 2;
                Player.playerY = 10;
                roomNumber = 72;
                fightEnemies();
                drawCurrentRoom();
                room72();
            }
            case 73 -> {
                Player.playerX = 3;
                Player.playerY = 10;
                roomNumber = 73;
                fightEnemies();
                drawCurrentRoom();
                room73();
            }
            case 74 -> {
                Player.playerX = 4;
                Player.playerY = 10;
                roomNumber = 74;
                drawCurrentRoom();
                room74();
            }
        }
    }

    public static void resetAll() { //reset all
        roomSave = 74;
        roomNumber = 0;
    }

    private static boolean checkChangeInRoom() {
        return roomSave != previousRoomSave;
    }

    private static void fightEnemies() throws InterruptedException {
        if (encounter && numberOfEnemies > 0) {
            drawCurrentRoom();
            inFight = true;
            if (numberOfEnemies == 1) {
                TextEngine.printWithDelays("You were ambushed by " + numberOfEnemies + " " + enemyType + "!", false);
            } else {
                TextEngine.printWithDelays("You were ambushed by " + numberOfEnemies + " " + enemyType + "s!", false);
            }
            TextEngine.printWithDelays("What is your command? " + yellowColor + "fight" + resetColor + " or " + yellowColor + "run" + resetColor, true);
            while (true) {
                ignore = console.readLine();
                command = console.readLine();
                switch (command.toLowerCase().trim()) {
                    case "fight" -> {
                        Player.changeHealth(Enemy.spawnEnemy(enemyType, numberOfEnemies));
                        inFight = false;
                        encounter = false;
                        Main.screenRefresh();
                        return;
                    }
                    case "run" -> {
                        boolean runSafe = rand.nextBoolean();
                        if (!runSafe) {
                            Player.changeHealth(Enemy.runSpawnEnemy(enemyType, numberOfEnemies));
                        } else {
                            TextEngine.printWithDelays("You managed to escape!", false);
                            TextEngine.enterToNext();
                        }
                        inFight = false;
                        encounter = false;
                        Main.screenRefresh();
                        return;
                    }
                    default ->
                        Main.inGameDefaultTextHandling(command);
                }
            }
        }
    }

    private static void room2() throws InterruptedException {
        if (Dungeon.completedDungeons <= 7) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Ocean Kingdom dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Ocean Kingdom dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        //TextEngine.printWithDelays("        You have entered the deep sea\n\n", false);
        TextEngine.printWithDelays("As you walk, you notice the winding paths leading back to the village,\n    where the comforting sights and sounds of town life await you.", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "east" + resetColor + " or " + yellowColor + "The Ocean Kingdom" + resetColor + " to decide", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim().trim()) {
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 3;
                    Main.loadSave();
                }
                case "the ocean kingdom" -> {
                    if (Dungeon.completedDungeons > 7) {
                        previousRoomSave = roomSave;
                        DesertOasisDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Pyramid dungeon first.\n Try going to the south", true);
                    }
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room3() throws InterruptedException {
        Dungeon.dungeonCheck();
        // TextEngine.printWithDelays("    You have entered the deep sea", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 4;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 2;
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
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "east" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 5;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 3;
                    Main.loadSave();
                }
                default ->
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
        //TextEngine.printWithDelays("    You have started to climb the mountain", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 6;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 9;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 4;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room6() throws InterruptedException {
        if (Dungeon.completedDungeons <= 6) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Mountain Top dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrets! you have beaten The Mountain Top dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 10;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 5;
                    Main.loadSave();
                }
                case "the mountain top" -> {
                    if (Dungeon.completedDungeons > 2) {
                        previousRoomSave = roomSave;
                        MountainTopDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Mountain Cave dungeons first, try going west.", true);
                    }
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room8() throws InterruptedException {
        if (Dungeon.completedDungeons <= 7) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Ocean Kingdom dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Ocean Kingdom dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
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
                    if (Dungeon.completedDungeons > 6) {
                        previousRoomSave = roomSave;
                        OceanKingdomDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Pyramid dungeon first.\n Try going to the south", true);
                    }
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 12;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 5;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 10;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 13;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 6;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 11;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 14;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 9;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room11() throws InterruptedException {
        if (Dungeon.completedDungeons <= 6) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Mountain Top dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Mountain Top dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
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
                        previousRoomSave = roomSave;
                        MountainTopDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Mountain Cave dungeons first, try going south west.", true);
                    }
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 15;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 10;
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
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", or " + yellowColor + "south" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 8;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 16;
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
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", or " + yellowColor + "south" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 9;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 14;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 17;
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
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 10;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 15;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 18;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
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
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 11;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 19;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 14;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 12;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 22;
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
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", or " + yellowColor + "south" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 13;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 18;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 25;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 14;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 19;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 26;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 17;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room19() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "The Village" + resetColor + ", " + yellowColor + "south" + resetColor + " or " + yellowColor + "west" + resetColor + " to continue your journey", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 15;
                    Main.loadSave();
                }
                case "the village" -> {
                    previousRoomSave = roomSave;
                    saveRoomNumber = roomNumber;
                    roomSave = 1;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 28;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 18;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room21() throws InterruptedException {
        Dungeon.dungeonCheck();
        //TextEngine.printWithDelays("    You have entered the deep sea", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 22;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 30;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room22() throws InterruptedException {
        Dungeon.dungeonCheck();
        //TextEngine.printWithDelays("    You have entered the deep sea", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 16;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 31;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 21;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room25() throws InterruptedException {
        if (Dungeon.completedDungeons <= 5) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Mountain Cave dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Mountain Cave dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 17;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 26;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 33;
                    Main.loadSave();
                }
                case "the mountain cave" -> {
                    if (Dungeon.completedDungeons > 1) {
                        previousRoomSave = roomSave;
                        MountainCaveDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Dark Forest first.\nTry going south east", true);
                    }
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 18;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 28;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 34;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 25;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room27() throws InterruptedException {
        Dungeon.dungeonCheck();
        //TextEngine.printWithDelays("    You have entered the desert", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 49;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 57;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 65;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 55;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 19;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 29;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 35;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 26;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    saveRoomNumber = roomNumber;
                    roomSave = 1;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 36;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 28;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 21;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 31;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 37;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 22;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 32;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 38;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 30;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 39;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 31;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room33() throws InterruptedException {
        Dungeon.dungeonCheck();
        //TextEngine.printWithDelays("    You have started to climb the mountain", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 25;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 34;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 42;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 62;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room34() throws InterruptedException {
        Dungeon.dungeonCheck();
        //TextEngine.printWithDelays("    You have started to climb the mountain", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 26;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 35;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 43;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 33;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room35() throws InterruptedException {
        Dungeon.dungeonCheck();
        //TextEngine.printWithDelays("    You have started to climb the mountain", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 28;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 36;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 44;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 34;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room36() throws InterruptedException {
        Dungeon.dungeonCheck();
        //TextEngine.printWithDelays("    You have started to climb the mountain", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 29;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 45;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 35;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room37() throws InterruptedException {
        Dungeon.dungeonCheck();
        // TextEngine.printWithDelays("    You have entered the desert", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 30;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 38;
                    Main.loadSave();
                }
                case "the village" -> {
                    previousRoomSave = roomSave;
                    saveRoomNumber = roomNumber;
                    roomSave = 1;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room38() throws InterruptedException {
        Dungeon.dungeonCheck();
        // TextEngine.printWithDelays("    You have entered the desert", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 31;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 39;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 47;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 37;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room39() throws InterruptedException {
        Dungeon.dungeonCheck();
        //TextEngine.printWithDelays("    You have entered the desert", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 32;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 40;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 48;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 38;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room40() throws InterruptedException {
        Dungeon.dungeonCheck();
        //TextEngine.printWithDelays("    You have entered the desert", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 41;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 49;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 39;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 62;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 42;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 50;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 40;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 33;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 43;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 51;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 41;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 34;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 44;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 52;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 42;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 35;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 45;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 53;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 43;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 36;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 54;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 44;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room47() throws InterruptedException {
        if (Dungeon.completedDungeons <= 4) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Pyramid dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Desert Pyramid dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "The Desert Pyramid" + resetColor + ", or " + yellowColor + "The Village" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 38;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 48;
                    Main.loadSave();
                }
                case "the desert pyramid" -> {
                    if (Dungeon.completedDungeons > 0) {
                        previousRoomSave = roomSave;
                        DesertPyramidDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Plains dungeon first.\nTry going south", true);
                    }
                }
                case "the village" -> {
                    previousRoomSave = roomSave;
                    saveRoomNumber = roomNumber;
                    roomSave = 1;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 39;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 49;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 55;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 47;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room49() throws InterruptedException {
        Dungeon.dungeonCheck();
        //TextEngine.printWithDelays("    You have entered the desert", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 40;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 50;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 27;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 48;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 41;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 51;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 57;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 49;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room51() throws InterruptedException {
        Dungeon.dungeonCheck();
        //TextEngine.printWithDelays("    You have entered the lost forest", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 42;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 52;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 58;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 50;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room52() throws InterruptedException {
        Dungeon.dungeonCheck();
        //TextEngine.printWithDelays("    You have entered the lost forest", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 43;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 53;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 59;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 51;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room53() throws InterruptedException {
        Dungeon.dungeonCheck();
        //TextEngine.printWithDelays("    You have entered the lost forest", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 44;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 54;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 60;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 52;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room54() throws InterruptedException {
        if (Dungeon.completedDungeons <= 2) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Dark Forest dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Dark Forest dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        //TextEngine.printWithDelays("    You have entered the lost forest", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "west" + resetColor + ", or " + yellowColor + "The Dark Forest" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 45;
                    Main.loadSave();
                }
                case "the dark forest" -> {
                    if (Dungeon.completedDungeons > 0) {
                        previousRoomSave = roomSave;
                        DarkForestDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Meadow dungeon first.\nTry going south", true);
                    }
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 53;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room55() throws InterruptedException {
        if (Dungeon.completedDungeons <= 4) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Pyramid dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Desert Pyramid dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 48;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 27;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 64;
                    Main.loadSave();
                }
                case "the desert pyramid" -> {
                    if (Dungeon.completedDungeons > 3) {
                        previousRoomSave = roomSave;
                        DesertPyramidDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Plains dungeon first.\nTry going south", true);
                    }
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 50;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 58;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 66;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 27;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room58() throws InterruptedException {
        Dungeon.dungeonCheck();
        //TextEngine.printWithDelays("    You have entered the lost forest", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 51;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 59;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 67;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 57;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 52;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 60;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 68;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 58;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room60() throws InterruptedException {
        if (Dungeon.completedDungeons <= 1) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Dark Forest dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Dark Forest dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "south" + resetColor + ", " + yellowColor + "west" + resetColor + ", or " + yellowColor + "The Dark Forest" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 53;
                    Main.loadSave();
                }
                case "the dark forest" -> {
                    if (Dungeon.completedDungeons > 0) {
                        previousRoomSave = roomSave;
                        DarkForestDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Meadow dungeon first.\nTry going south", true);
                    }
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 69;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 59;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room62() throws InterruptedException {
        if (Dungeon.completedDungeons <= 5) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Mountain Cave dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Mountain Cave dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        //TextEngine.printWithDelays("    You have started to climb the mountain", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 33;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 41;
                    Main.loadSave();
                }
                case "the mountain cave" -> {
                    if (Dungeon.completedDungeons > 1) {
                        previousRoomSave = roomSave;
                        MountainCaveDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Dark Forest first.\nTry going south east", true);
                    }
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 55;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 65;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 72;
                    Main.loadSave();
                }
                case "the desert plains" -> {
                    if (Dungeon.completedDungeons > 4) {
                        previousRoomSave = roomSave;
                        DesertPlainsDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Oasis dungeons first.\nTry going to the south south", true);
                    }
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room65() throws InterruptedException {
        Dungeon.dungeonCheck();
        //TextEngine.printWithDelays("    You have entered the desert", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 27;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 66;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 73;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 64;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 57;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 67;
                    Main.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 74;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 65;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room67() throws InterruptedException {
        Dungeon.dungeonCheck();
        //TextEngine.printWithDelays("    You have entered the lost forest", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "west" + resetColor + ", or " + yellowColor + "The Village" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 58;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 68;
                    Main.loadSave();
                }
                case "the village" -> {
                    previousRoomSave = roomSave;
                    saveRoomNumber = roomNumber;
                    roomSave = 1;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 66;
                    Main.loadSave();
                }
                default ->
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
                    previousRoomSave = roomSave;
                    roomSave = 59;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 69;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 67;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room69() throws InterruptedException {
        if (Dungeon.completedDungeons < 1) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Meadow dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Meadow dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "west" + resetColor + ", or " + yellowColor + "The Meadow" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 60;
                    Main.loadSave();
                }
                case "the meadow" -> {
                    previousRoomSave = roomSave;
                    MeadowDungeon.startRoom();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 68;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room72() throws InterruptedException {
        if (Dungeon.completedDungeons <= 2) {
            TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Oasis dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
        } else {
            TextEngine.printWithDelays("Congrats! you have beaten The Desert Oasis dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", or " + yellowColor + "The Desert Oasis" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 64;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 73;
                    Main.loadSave();
                }
                case "the desert oasis" -> {
                    if (Dungeon.completedDungeons > 3) {
                        previousRoomSave = roomSave;
                        DesertOasisDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Mountain Top dungeon first.\n Try going to the north, east", true);
                    }
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room73() throws InterruptedException {
        Dungeon.dungeonCheck();
        //TextEngine.printWithDelays("    You have entered the desert", false);
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
                    previousRoomSave = roomSave;
                    roomSave = 65;
                    Main.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 74;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 72;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room74() throws InterruptedException {
        Dungeon.dungeonCheck();
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "west" + resetColor + ", or " + yellowColor + "The Village" + resetColor + " to continue your journey ", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 66;
                    Main.loadSave();
                }
                case "the village" -> {
                    previousRoomSave = roomSave;
                    saveRoomNumber = roomNumber;
                    roomSave = 1;
                    Main.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 73;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
}
