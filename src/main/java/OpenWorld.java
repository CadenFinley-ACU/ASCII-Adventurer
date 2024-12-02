
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ASCIIADVENTURER Albert Tucker Grijesh Shrestha
 *
 * The OpenWorld class represents a dynamic environment where the player can
 * encounter various enemies based on the type of terrain they are in. It
 * extends the Room class and includes methods to start the room and handle
 * encounters.
 *
 * <p>
 * This class includes methods to initialize the room, check for changes in the
 * room, and manage enemy encounters based on the player's position.</p>
 *
 * @version 1.0
 * @author ASCIIADVENTURERS
 */
public class OpenWorld extends Room {

    static int roomSave = 74;
    public static String holdCommand = null;
    static int roomNumber = 0;
    static int saveRoomNumber = 0;
    static String yellowColor = "\033[1;33m"; // yellow color
    private static final Random rand = new Random();
    public static int previousRoomSave = 74;

    public static int numberOfEnemies;
    public static String enemyType;
    public static boolean encounter = false;
    private static int encounterRate;
    private static List<String> enemies;
    public static boolean inFight = false;
    public static boolean wasInFight = false;

    /**
     * Starts the room by initializing the room name, checking the save state,
     * saving the game, refreshing the screen, and setting up the list of
     * enemies based on the player's position.
     *
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public static void startRoom() throws InterruptedException { //start room
        room = "OpenWorld";
        GameEngine.checkSave(room);
        GameSaveSerialization.saveGame();
        GameEngine.screenRefresh();
        enemies = new ArrayList<>(List.of("Goblin", "Slime", "Bandit"));
        if (checkChangeInRoom()) {
            switch (Player.getColorOfPlayerPostitionTile()) {
                case "grassland" -> {
                    numberOfEnemies = rand.nextInt(3);
                    encounterRate = 25;
                    enemies = new ArrayList<>(List.of("Goblin", "Slime", "Bandit"));
                }
                case "desert" -> {
                    numberOfEnemies = rand.nextInt(5);
                    encounterRate = 30;
                    enemies = new ArrayList<>(List.of("Scorpion", "Barbarian", "Bandit"));
                }
                case "mountain", "mountain top" -> {
                    numberOfEnemies = rand.nextInt(3);
                    encounterRate = 30;
                    enemies = new ArrayList<>(List.of("Goblin", "Mountain Lion", "Mimic", "Orc"));
                }
                case "ocean" -> {
                    numberOfEnemies = rand.nextInt(7);
                    encounterRate = 40;
                    enemies = new ArrayList<>(List.of("Pirate", "Shark"));
                }
                case "lost forest" -> {
                    encounterRate = 40;
                    numberOfEnemies = rand.nextInt(5);
                    enemies = new ArrayList<>(List.of("Skeleton", "Slime", "Zombie", "Orc", "Bandit"));
                }
                default -> {
                    encounterRate = 25;
                    numberOfEnemies = rand.nextInt(3);
                    enemies = new ArrayList<>(List.of("Goblin", "Slime", "Bandit"));
                }
            }
        }
        encounter = (rand.nextInt(100) <= encounterRate);
        //determine open world encounter
        if (numberOfEnemies == 0) {
            numberOfEnemies = 1;
        }
        if (!encounter) {
            numberOfEnemies = 0;
        }
        enemyType = enemies.get(rand.nextInt(enemies.size()));
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
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room2(); //start room
            }
            case 3 -> {
                Player.playerX = 3;
                Player.playerY = 0;
                roomNumber = 3;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room3(); //start room
            }
            case 4 -> {
                Player.playerX = 4;
                Player.playerY = 0;
                roomNumber = 4;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room4(); //start room
            }
            case 5 -> {
                Player.playerX = 5;
                Player.playerY = 0;
                roomNumber = 5;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room5(); //start room
            }
            case 6 -> {
                Player.playerX = 6;
                Player.playerY = 0;
                roomNumber = 6;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room6(); //start room
            }
            case 8 -> {
                Player.playerX = 1;
                Player.playerY = 1;
                roomNumber = 8;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room8(); //start room
            }
            case 9 -> {
                Player.playerX = 5;
                Player.playerY = 1;
                roomNumber = 9;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room9(); //start room
            }
            case 10 -> {
                Player.playerX = 6;
                Player.playerY = 1;
                roomNumber = 10;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room10(); //start room
            }
            case 11 -> {
                Player.playerX = 7;
                Player.playerY = 1;
                roomNumber = 11;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room11(); //start room
            }
            case 12 -> {
                Player.playerX = 1;
                Player.playerY = 2;
                roomNumber = 12;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room12(); //start room
            }
            case 13 -> {
                Player.playerX = 5;
                Player.playerY = 2;
                roomNumber = 13;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room13(); //start room
            }
            case 14 -> {
                Player.playerX = 6;
                Player.playerY = 2;
                roomNumber = 14;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room14(); //start room
            }
            case 15 -> {
                Player.playerX = 7;
                Player.playerY = 2;
                roomNumber = 15;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room15(); //start room
            }
            case 16 -> {
                Player.playerX = 1;
                Player.playerY = 3;
                roomNumber = 16;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room16(); //start room
            }
            case 17 -> {
                Player.playerX = 5;
                Player.playerY = 3;
                roomNumber = 17;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room17(); //start room
            }
            case 18 -> {
                Player.playerX = 6;
                Player.playerY = 3;
                roomNumber = 18;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room18(); //start room
            }
            case 19 -> {
                Player.playerX = 7;
                Player.playerY = 3;
                roomNumber = 19;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room19(); //start room
            }
            case 21 -> {
                Player.playerX = 0;
                Player.playerY = 4;
                roomNumber = 21;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room21();
            }
            case 22 -> {
                Player.playerX = 1;
                Player.playerY = 4;
                roomNumber = 22;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room22();
            }
            case 25 -> {
                Player.playerX = 5;
                Player.playerY = 4;
                roomNumber = 25;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room25();
            }
            case 26 -> {
                Player.playerX = 6;
                Player.playerY = 4;
                roomNumber = 26;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room26();
            }
            case 27 -> {
                Player.playerX = 3;
                Player.playerY = 8;
                roomNumber = 27;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room27();
            }
            case 28 -> {
                Player.playerX = 7;
                Player.playerY = 4;
                roomNumber = 28;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room28();
            }
            case 29 -> {
                Player.playerX = 8;
                Player.playerY = 4;
                roomNumber = 29;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room29();
            }
            case 30 -> {
                Player.playerX = 0;
                Player.playerY = 5;
                roomNumber = 30;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room30();
            }
            case 31 -> {
                Player.playerX = 1;
                Player.playerY = 5;
                roomNumber = 31;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room31();
            }
            case 32 -> {
                Player.playerX = 2;
                Player.playerY = 5;
                roomNumber = 32;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room32();
            }
            case 33 -> {
                Player.playerX = 5;
                Player.playerY = 5;
                roomNumber = 33;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room33();
            }
            case 34 -> {
                Player.playerX = 6;
                Player.playerY = 5;
                roomNumber = 34;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room34();
            }
            case 35 -> {
                Player.playerX = 7;
                Player.playerY = 5;
                roomNumber = 35;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room35();
            }
            case 36 -> {
                Player.playerX = 8;
                Player.playerY = 5;
                roomNumber = 36;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room36();
            }
            case 37 -> {
                Player.playerX = 0;
                Player.playerY = 6;
                roomNumber = 37;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room37();
            }
            case 38 -> {
                Player.playerX = 1;
                Player.playerY = 6;
                roomNumber = 38;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room38();
            }
            case 39 -> {
                Player.playerX = 2;
                Player.playerY = 6;
                roomNumber = 39;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room39();
            }
            case 40 -> {
                Player.playerX = 3;
                Player.playerY = 6;
                roomNumber = 40;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room40();
            }
            case 41 -> {
                Player.playerX = 4;
                Player.playerY = 6;
                roomNumber = 41;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room41();
            }
            case 42 -> {
                Player.playerX = 5;
                Player.playerY = 6;
                roomNumber = 42;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room42();
            }
            case 43 -> {
                Player.playerX = 6;
                Player.playerY = 6;
                roomNumber = 43;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room43();
            }
            case 44 -> {
                Player.playerX = 7;
                Player.playerY = 6;
                roomNumber = 44;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room44();
            }
            case 45 -> {
                Player.playerX = 8;
                Player.playerY = 6;
                roomNumber = 45;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room45();
            }
            case 47 -> {
                Player.playerX = 1;
                Player.playerY = 7;
                roomNumber = 47;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room47();
            }
            case 48 -> {
                Player.playerX = 2;
                Player.playerY = 7;
                roomNumber = 48;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room48();
            }
            case 49 -> {
                Player.playerX = 3;
                Player.playerY = 7;
                roomNumber = 49;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room49();
            }
            case 50 -> {
                Player.playerX = 4;
                Player.playerY = 7;
                roomNumber = 50;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room50();
            }
            case 51 -> {
                Player.playerX = 5;
                Player.playerY = 7;
                roomNumber = 51;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room51();
            }
            case 52 -> {
                Player.playerX = 6;
                Player.playerY = 7;
                roomNumber = 52;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room52();
            }
            case 53 -> {
                Player.playerX = 7;
                Player.playerY = 7;
                roomNumber = 53;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room53();
            }
            case 54 -> {
                Player.playerX = 8;
                Player.playerY = 7;
                roomNumber = 54;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room54();
            }
            case 55 -> {
                Player.playerX = 2;
                Player.playerY = 8;
                roomNumber = 55;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room55();
            }
            case 57 -> {
                Player.playerX = 4;
                Player.playerY = 8;
                roomNumber = 57;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room57();
            }
            case 58 -> {
                Player.playerX = 5;
                Player.playerY = 8;
                roomNumber = 58;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room58();
            }
            case 59 -> {
                Player.playerX = 6;
                Player.playerY = 8;
                roomNumber = 59;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room59();
            }
            case 60 -> {
                Player.playerX = 7;
                Player.playerY = 8;
                roomNumber = 60;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room60();
            }
            case 62 -> {
                Player.playerX = 4;
                Player.playerY = 5;
                roomNumber = 62;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room62();
            }
            case 64 -> {
                Player.playerX = 2;
                Player.playerY = 9;
                roomNumber = 64;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room64();
            }
            case 65 -> {
                Player.playerX = 3;
                Player.playerY = 9;
                roomNumber = 65;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room65();
            }
            case 66 -> {
                Player.playerX = 4;
                Player.playerY = 9;
                roomNumber = 66;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room66();
            }
            case 67 -> {
                Player.playerX = 5;
                Player.playerY = 9;
                roomNumber = 67;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room67();
            }
            case 68 -> {
                Player.playerX = 6;
                Player.playerY = 9;
                roomNumber = 68;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room68();
            }
            case 69 -> {
                Player.playerX = 7;
                Player.playerY = 9;
                roomNumber = 69;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room69();
            }
            case 72 -> {
                Player.playerX = 2;
                Player.playerY = 10;
                roomNumber = 72;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room72();
            }
            case 73 -> {
                Player.playerX = 3;
                Player.playerY = 10;
                roomNumber = 73;
                PromptEngine.buildPrompt();
                fightEnemies();
                reRenderSameRoom();
                room73();
            }
            case 74 -> {
                Player.playerX = 4;
                Player.playerY = 10;
                roomNumber = 74;
                numberOfEnemies = 0;
                PromptEngine.buildPrompt();
                reRenderSameRoom();
                room74();
            }
        }
    }

    /**
     * Resets all game settings to their default values. Sets the room save to
     * 74 and the room number to 0.
     */
    public static void resetAll() { //reset all
        roomSave = 74;
        roomNumber = 0;
    }

    /**
     * Checks if there has been a change in the room.
     *
     * @return true if the current room save is different from the previous room
     * save, false otherwise.
     */
    public static boolean checkChangeInRoom() {
        return roomSave != previousRoomSave;
    }

    /**
     * Handles the encounter with enemies in the game. If there is an encounter
     * and the number of enemies is greater than 0, it initiates a fight
     * sequence. The player can choose to either fight or run.
     *
     * @throws InterruptedException if the thread is interrupted during the
     * delay in text printing.
     */
    private static void fightEnemies() throws InterruptedException {
        if (encounter && numberOfEnemies > 0) {
            drawCurrentRoom();
            inFight = true;
            wasInFight = true;
            if (numberOfEnemies == 1) {
                TextEngine.printWithDelays("You were ambushed by " + numberOfEnemies + " " + enemyType + "!", false);
            } else {
                TextEngine.printWithDelays("You were ambushed by " + numberOfEnemies + " " + enemyType + "s!", false);
            }
            TextEngine.printWithDelays("What is your command? " + yellowColor + "fight" + resetColor + " or " + yellowColor + "run" + resetColor, true);
            while (true) {
                command = TextEngine.parseCommand(console.readLine(), new String[]{"fight", "run"});
                switch (command.toLowerCase().trim()) {
                    case "fight" -> {
                        Player.changeHealth(Enemy.spawnEnemy(enemyType, numberOfEnemies));
                        inFight = false;
                        encounter = false;
                        GameEngine.screenRefresh();
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
                        GameEngine.screenRefresh();
                        return;
                    }
                    default ->
                        GameEngine.inGameDefaultTextHandling(command);
                }
            }
        }
    }

    /**
     * Handles the logic for rooms in the game. All rooms in this class are
     * handled in a similar way.
     *
     * @throws InterruptedException if the thread is interrupted while sleeping.
     */
    private static void room2() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            if (Dungeon.completedDungeons <= 6) {
                TextEngine.printWithDelays("You find yourself standing right by the entrance to The Ocean Kingdom dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
            } else {
                TextEngine.printWithDelays("Congrats! you have beaten The Ocean Kingdom dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
            }
            TextEngine.printWithDelays("As you walk, you notice the winding paths leading back to the village,\n    where the comforting sights and sounds of town life await you.", false);
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "east" + resetColor + " or " + yellowColor + "The Ocean Kingdom" + resetColor + " to decide", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim().trim()) {
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 3;
                    GameEngine.loadSave();
                }
                case "the ocean kingdom" -> {
                    if (Dungeon.completedDungeons > 6) {
                        previousRoomSave = roomSave;
                        Dungeon.DesertOasisDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Pyramid dungeon first.\n Try going to the south", true);
                    }
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room3() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
            TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        }
        // TextEngine.printWithDelays("    You have entered the deep sea", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "east" + resetColor + ", or " + yellowColor + "west" + resetColor + " to make your choice", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 4;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 2;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room4() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
            TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        }
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "east" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 5;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 3;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room5() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            if (Dungeon.completedDungeons <= 2) {
                TextEngine.printWithDelays("You find yourself standing right by the entrance to The Mountain Cave Dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
            } else {
                TextEngine.printWithDelays("Congrets! you have beaten The Mountain Cave dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
            }
            TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        }
        //TextEngine.printWithDelays("    You have started to climb the mountain", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 6;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 9;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 4;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room6() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            if (Dungeon.completedDungeons >= 3) {
                TextEngine.printWithDelays("You find yourself standing right by the entrance to The Mountain Top dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
            } else {
                TextEngine.printWithDelays("Congrets! you have beaten The Mountain Top dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
            }
            TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        }
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "The Mountain Top" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 10;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 5;
                    GameEngine.loadSave();
                }
                case "the mountain top" -> {
                    if (Dungeon.completedDungeons > 2) {
                        previousRoomSave = roomSave;
                        Dungeon.MountainTopDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Mountain Cave dungeons first, try going west.", true);
                    }
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room8() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            if (Dungeon.completedDungeons <= 6) {
                TextEngine.printWithDelays("You find yourself standing right by the entrance to The Ocean Kingdom dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
            } else {
                TextEngine.printWithDelays("Congrats! you have beaten The Ocean Kingdom dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
            }
            TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        }
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "The Ocean Kingdom" + resetColor + ", or " + yellowColor + "south" + resetColor + " to continue your journey", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "the ocean kingdom" -> {
                    if (Dungeon.completedDungeons > 6) {
                        previousRoomSave = roomSave;
                        Dungeon.OceanKingdomDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Pyramid dungeon first.\n Try going to the south", true);
                    }
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 12;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room9() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", or " + yellowColor + "south" + resetColor + " to continue your journey", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 5;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 10;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 13;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room10() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            if (Dungeon.completedDungeons <= 4) {
                TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Oasis dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
            } else {
                TextEngine.printWithDelays("Congrats! you have beaten The Mountain Cave dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
            }
            TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        }
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + "  to continue your journey", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 6;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 11;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 14;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 9;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room11() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            if (Dungeon.completedDungeons <= 2) {
                TextEngine.printWithDelays("You find yourself standing right by the entrance to The Mountain Top dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
            } else {
                TextEngine.printWithDelays("Congrats! you have beaten The Mountain Top dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
            }
            TextEngine.printWithDelays("Worrning: going into a dungeon you will trigger fights, but you might find something in the rooms\nIf you do not DIE", false);
        }
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "The Mountain Top" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "the mountain top" -> {
                    if (Dungeon.completedDungeons > 2) {
                        previousRoomSave = roomSave;
                        Dungeon.MountainTopDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Mountain Cave dungeons first, try going south west.", true);
                    }
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 15;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 10;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room12() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", or " + yellowColor + "south" + resetColor + " to continue your journey", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 8;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 16;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room13() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", or " + yellowColor + "south" + resetColor + " to continue your journey", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 9;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 14;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 17;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room14() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 10;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 15;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 18;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 13;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room15() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 11;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 19;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 14;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room16() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", or " + yellowColor + "south" + resetColor + " to continue your journey", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 12;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 22;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room17() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", or " + yellowColor + "south" + resetColor + " to continue your journey", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 13;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 18;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 25;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room18() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            if (Dungeon.completedDungeons <= 6) {
                TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Pyramid dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
            } else {
                TextEngine.printWithDelays("Congrats! you have beaten The Mountain Cave dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
            }
            TextEngine.printWithDelays("Warning: Stepping into a dungeon will trigger battles,\n    but you may uncover valuable treasures within its chambers if you manage to survive.\n", false);
        }
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + " or " + yellowColor + "west" + resetColor + " to continue your journey", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 14;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 19;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 26;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 17;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room19() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "The Village" + resetColor + ", " + yellowColor + "south" + resetColor + " or " + yellowColor + "west" + resetColor + " to continue your journey", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 15;
                    GameEngine.loadSave();
                }
                case "the village" -> {
                    previousRoomSave = roomSave;
                    saveRoomNumber = roomNumber;
                    roomSave = 1;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 28;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 18;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room21() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        //TextEngine.printWithDelays("    You have entered the deep sea", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("Which path will you choose? Type " + yellowColor + "east" + resetColor + ", or " + yellowColor + "south" + resetColor + " to continue your journey", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase()) {
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 22;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 30;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room22() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        //TextEngine.printWithDelays("    You have entered the deep sea", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 16;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 31;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 21;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room25() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            if (Dungeon.completedDungeons <= 1) {
                TextEngine.printWithDelays("You find yourself standing right by the entrance to The Mountain Cave dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
            } else {
                TextEngine.printWithDelays("Congrats! you have beaten The Mountain Cave dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
            }
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "The Mountain Cave" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 17;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 26;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 33;
                    GameEngine.loadSave();
                }
                case "the mountain cave" -> {
                    if (Dungeon.completedDungeons > 1) {
                        previousRoomSave = roomSave;
                        Dungeon.MountainCaveDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Dark Forest first.\nTry going south east", true);
                    }
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room26() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 18;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 28;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 34;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 25;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room27() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        //TextEngine.printWithDelays("    You have entered the desert", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 49;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 57;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 65;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 55;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room28() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 19;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 29;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 35;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 26;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room29() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "The Village" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "the village" -> {
                    previousRoomSave = roomSave;
                    saveRoomNumber = roomNumber;
                    roomSave = 1;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 36;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 28;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room30() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", or " + yellowColor + "south" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 21;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 31;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 37;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room31() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 22;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 32;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 38;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 30;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room32() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 39;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 31;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room33() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        //TextEngine.printWithDelays("    You have started to climb the mountain", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 25;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 34;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 42;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 62;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room34() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        //TextEngine.printWithDelays("    You have started to climb the mountain", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 26;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 35;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 43;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 33;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room35() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        //TextEngine.printWithDelays("    You have started to climb the mountain", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 28;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 36;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 44;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 34;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room36() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        //TextEngine.printWithDelays("    You have started to climb the mountain", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 29;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 45;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 35;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room37() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        // TextEngine.printWithDelays("    You have entered the desert", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", or " + yellowColor + "The Village" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 30;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 38;
                    GameEngine.loadSave();
                }
                case "the village" -> {
                    previousRoomSave = roomSave;
                    saveRoomNumber = roomNumber;
                    roomSave = 1;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room38() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        // TextEngine.printWithDelays("    You have entered the desert", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 31;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 39;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 47;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 37;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room39() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        //TextEngine.printWithDelays("    You have entered the desert", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 32;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 40;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 48;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 38;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room40() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        //TextEngine.printWithDelays("    You have entered the desert", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 41;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 49;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 39;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room41() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 62;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 42;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 50;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 40;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room42() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 33;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 43;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 51;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 41;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room43() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 34;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 44;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 52;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 42;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room44() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 35;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 45;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 53;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 43;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room45() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 36;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 54;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 44;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room47() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            if (Dungeon.completedDungeons <= 5) {
                TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Pyramid dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
            } else {
                TextEngine.printWithDelays("Congrats! you have beaten The Desert Pyramid dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
            }
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "The Desert Pyramid" + resetColor + ", or " + yellowColor + "The Village" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 38;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 48;
                    GameEngine.loadSave();
                }
                case "the desert pyramid" -> {
                    if (Dungeon.completedDungeons > 5) {
                        previousRoomSave = roomSave;
                        Dungeon.DesertPyramidDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Plains dungeon first.\nTry going south", true);
                    }
                }
                case "the village" -> {
                    previousRoomSave = roomSave;
                    saveRoomNumber = roomNumber;
                    roomSave = 1;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room48() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 39;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 49;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 55;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 47;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room49() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        //TextEngine.printWithDelays("    You have entered the desert", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 40;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 50;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 27;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 48;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room50() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 41;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 51;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 57;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 49;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room51() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        //TextEngine.printWithDelays("    You have entered the lost forest", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 42;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 52;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 58;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 50;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room52() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        //TextEngine.printWithDelays("    You have entered the lost forest", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 43;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 53;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 59;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 51;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room53() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        //TextEngine.printWithDelays("    You have entered the lost forest", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 44;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 54;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 60;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 52;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room54() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            if (Dungeon.completedDungeons <= 2) {
                TextEngine.printWithDelays("You find yourself standing right by the entrance to The Dark Forest dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
            } else {
                TextEngine.printWithDelays("Congrats! you have beaten The Dark Forest dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
            }
        }
        //TextEngine.printWithDelays("    You have entered the lost forest", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "west" + resetColor + ", or " + yellowColor + "The Dark Forest" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 45;
                    GameEngine.loadSave();
                }
                case "the dark forest" -> {
                    if (Dungeon.completedDungeons > 0) {
                        previousRoomSave = roomSave;
                        Dungeon.DarkForestDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Meadow dungeon first.\nTry going south", true);
                    }
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 53;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room55() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            if (Dungeon.completedDungeons <= 5) {
                TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Pyramid dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
            } else {
                TextEngine.printWithDelays("Congrats! you have beaten The Desert Pyramid dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
            }
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "The Desert Pyramid" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 48;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 27;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 64;
                    GameEngine.loadSave();
                }
                case "the desert pyramid" -> {
                    if (Dungeon.completedDungeons > 5) {
                        previousRoomSave = roomSave;
                        Dungeon.DesertPyramidDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Plains dungeon first.\nTry going south", true);
                    }
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room57() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 50;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 58;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 66;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 27;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room58() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        //TextEngine.printWithDelays("    You have entered the lost forest", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 51;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 59;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 67;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 57;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room59() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 52;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 60;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 68;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 58;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room60() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            if (Dungeon.completedDungeons <= 1) {
                TextEngine.printWithDelays("You find yourself standing right by the entrance to The Dark Forest dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
            } else {
                TextEngine.printWithDelays("Congrats! you have beaten The Dark Forest dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
            }
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "south" + resetColor + ", " + yellowColor + "west" + resetColor + ", or " + yellowColor + "The Dark Forest" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 53;
                    GameEngine.loadSave();
                }
                case "the dark forest" -> {
                    if (Dungeon.completedDungeons > 0) {
                        previousRoomSave = roomSave;
                        Dungeon.DarkForestDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Meadow dungeon first.\nTry going south", true);
                    }
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 69;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 59;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room62() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            if (Dungeon.completedDungeons <= 1) {
                TextEngine.printWithDelays("You find yourself standing right by the entrance to The Mountain Cave dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
            } else {
                TextEngine.printWithDelays("Congrats! you have beaten The Mountain Cave dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
            }
        }
        //TextEngine.printWithDelays("    You have started to climb the mountain", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "The Mountain Cave" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 33;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 41;
                    GameEngine.loadSave();
                }
                case "the mountain cave" -> {
                    if (Dungeon.completedDungeons > 1) {
                        previousRoomSave = roomSave;
                        Dungeon.MountainCaveDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Dark Forest first.\nTry going south east", true);
                    }
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room64() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            if (Dungeon.completedDungeons <= 4) {
                TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Plains dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
            } else {
                TextEngine.printWithDelays("Congrats! you have beaten The Desert Plains dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
            }
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "The Desert Plains" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 55;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 65;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 72;
                    GameEngine.loadSave();
                }
                case "the desert plains" -> {
                    if (Dungeon.completedDungeons > 4) {
                        previousRoomSave = roomSave;
                        Dungeon.DesertPlainsDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Oasis dungeons first.\nTry going to the south south", true);
                    }
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room65() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        //TextEngine.printWithDelays("    You have entered the desert", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 27;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 66;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 73;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 64;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room66() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "south" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 57;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 67;
                    GameEngine.loadSave();
                }
                case "south" -> {
                    previousRoomSave = roomSave;
                    roomSave = 74;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 65;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room67() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        //TextEngine.printWithDelays("    You have entered the lost forest", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", " + yellowColor + "west" + resetColor + ", or " + yellowColor + "The Village" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 58;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 68;
                    GameEngine.loadSave();
                }
                case "the village" -> {
                    previousRoomSave = roomSave;
                    saveRoomNumber = roomNumber;
                    roomSave = 1;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 66;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room68() throws InterruptedException {

        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 59;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 69;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 67;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room69() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            if (Dungeon.completedDungeons < 1) {
                TextEngine.printWithDelays("You find yourself standing right by the entrance to The Meadow dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
            } else {
                TextEngine.printWithDelays("Congrats! you have beaten The Meadow dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
            }
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "west" + resetColor + ", or " + yellowColor + "The Meadow" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 60;
                    GameEngine.loadSave();
                }
                case "the meadow" -> {
                    previousRoomSave = roomSave;
                    Dungeon.MeadowDungeon.startRoom();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 68;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room72() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            if (Dungeon.completedDungeons <= 3) {
                TextEngine.printWithDelays("You find yourself standing right by the entrance to The Desert Oasis dungeon,\n    its ancient stone archway beckoning you to enter.\n\n", false);
            } else {
                TextEngine.printWithDelays("Congrats! you have beaten The Desert Oasis dungeon,\n     time to go back to a village rest up, check out the shop, and head to the next dungeon.\n\n", false);
            }
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", or " + yellowColor + "The Desert Oasis" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 64;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 73;
                    GameEngine.loadSave();
                }
                case "the desert oasis" -> {
                    if (Dungeon.completedDungeons > 3) {
                        previousRoomSave = roomSave;
                        Dungeon.DesertOasisDungeon.startRoom();
                    } else {
                        TextEngine.printWithDelays("You must complete The Mountain Top dungeon first.\n Try going to the north, east", true);
                    }
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room73() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        //TextEngine.printWithDelays("    You have entered the desert", false);
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "east" + resetColor + ", or " + yellowColor + "west" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 65;
                    GameEngine.loadSave();
                }
                case "east" -> {
                    previousRoomSave = roomSave;
                    roomSave = 74;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 72;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room74() throws InterruptedException {
        if (PromptEngine.aiGenerationEnabled) {
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            dungeonCheck();
        }
        TextEngine.printWithDelays("If you find yourself feeling lost, don't forget to check out the " + yellowColor + "map" + resetColor + " for guidance.\n", false);
        TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + ", " + yellowColor + "west" + resetColor + ", or " + yellowColor + "The Village" + resetColor + " to continue your journey ", true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine().toLowerCase().trim(), new String[]{"north", "east", "south", "west"});
            if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                holdCommand = command;
            }
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    previousRoomSave = roomSave;
                    roomSave = 66;
                    GameEngine.loadSave();
                }
                case "the village" -> {
                    previousRoomSave = roomSave;
                    saveRoomNumber = roomNumber;
                    roomSave = 1;
                    GameEngine.loadSave();
                }
                case "west" -> {
                    previousRoomSave = roomSave;
                    roomSave = 73;
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }
}
