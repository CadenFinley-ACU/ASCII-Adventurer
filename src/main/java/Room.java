
import java.io.Console;
import java.util.Random;

/**
 * ASCIIADVENTURER Caden Finley Albert Tucker Grijesh Shrestha
 *
 * This class represents a room in the ASCII Adventure game. It contains methods
 * to interact with the room, such as checking for chests and processing user
 * commands.
 *
 * @author ASCIIADVENTURERS
 * @version 1.0
 */
public class Room {

    public final static Console console = System.console();
    public static String command;
    public static String room = null;
    public static String redColor = "\033[0;31m"; // red color
    public static String yellowColor = "\033[1;33m"; // yellow color
    public static String resetColor = "\033[0m"; // reset to default color
    public static String bufferedEnviroment = null;
    public static String environment = null;
    public static String[][] currentRoom = {
        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "}
    };

    /**
     * Checks if there is a chest in the room and processes the user's command
     * to either open it or leave it.
     *
     * @param itemName the name of the item to check for in the room
     * @param quantity the quantity of the item to check for in the room
     * @return true if the chest is opened and the item is found in the
     * specified quantity, false otherwise
     * @throws InterruptedException if the thread is interrupted while waiting
     * for user input
     */
    public static boolean hasChestInRoom(String itemName, int quantity) throws InterruptedException {
        String yellowColor = "\033[1;33m"; // yellow color

        TextEngine.printWithDelays("Hey! There is a chest in this room! ", false);
        TextEngine.printWithDelays("What is your command" + yellowColor + " open it " + resetColor + "or " + yellowColor + "leave it" + resetColor, true);
        while (true) {
            command = TextEngine.parseCommand(console.readLine(), new String[]{"open it", "leave it"});
            switch (command.toLowerCase().trim()) {
                case "open it" -> {
                    return hasItemInRoom(itemName, quantity);
                }
                case "leave it" -> {
                    return false;
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    /**
     * Checks if the specified item is in the room and processes the user's
     * command to either take it or leave it.
     *
     * @param itemName the name of the item to check for in the room
     * @param quantity the quantity of the item to check for in the room
     * @return true if the item is taken, false otherwise
     * @throws InterruptedException if the thread is interrupted while waiting
     * for user input
     */
    public static boolean hasItemInRoom(String itemName, int quantity) throws InterruptedException {
        String yellowColor = "\033[1;33m"; // yellow color

        // Display item information
        if (quantity > 1) {
            TextEngine.printWithDelays("Item(s): " + yellowColor + itemName + resetColor + " x" + quantity, false);
        } else {
            TextEngine.printWithDelays("Item: " + yellowColor + itemName + resetColor, false);
        }

        // Highlight 'take it' and 'leave it' in yellow
        TextEngine.printWithDelays("What will you do? Type " + yellowColor + "take it" + resetColor + " to pick up the " + itemName + " or " + yellowColor + "leave it" + resetColor, true);

        // Command handling loop
        while (true) {
            command = TextEngine.parseCommand(console.readLine(), new String[]{"take it", "leave it"});
            switch (command.toLowerCase().trim()) {
                case "take it" -> {
                    return Player.putItem(itemName, quantity);
                }
                case "leave it" -> {
                    if ("Backpack".equals(itemName) || "Large Backpack".equals(itemName)) {
                        TextEngine.printWithDelays("You can't leave your backpack behind!", false);
                        TextEngine.enterToNext();
                        return Player.putItem(itemName, quantity);
                    }
                    return false;
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    /**
     * Resets the specified area by setting the room to null and calling the
     * reset method for the corresponding area.
     *
     * @param area the area to reset, which can be "SpawnRoom", "OpenWorld", or
     * "all"
     */
    public static void reset(String area) { //reset the room
        room = null;
        switch (area) {
            case "SpawnRoom" -> {
                SpawnRoom.resetAll();
            }
            case "OpenWorld" -> {
                OpenWorld.resetAll();
            }
            case "all" -> {
                SpawnRoom.resetAll();
                OpenWorld.resetAll();
                Dungeon.resetAll();
            }
        }
    }

    /**
     * Re-renders the current room. If the player was in a fight, it resets the
     * room by removing enemy markers and prints the updated room. Additionally,
     * it checks if the player has entered a new environment and prints a
     * corresponding message. If the player was not in a fight, it simply draws
     * the current room.
     *
     * @throws InterruptedException if the thread is interrupted while waiting
     * for user input
     */
    public static void reRenderSameRoom() throws InterruptedException {
        if (OpenWorld.wasInFight) {
            OpenWorld.wasInFight = false;
            for (String[] row : currentRoom) {
                for (String cell : row) {
                    if (cell.equals(redColor + "E" + resetColor) || cell.equals(redColor + "G" + resetColor) || cell.equals(redColor + "O" + resetColor) || cell.equals(redColor + "T" + resetColor) || cell.equals(redColor + "B" + resetColor) || cell.equals(redColor + "S" + resetColor) || cell.equals(redColor + "R" + resetColor) || cell.equals(redColor + "D" + resetColor) || cell.equals(redColor + "V" + resetColor) || cell.equals(redColor + "W" + resetColor) || cell.equals(redColor + "M" + resetColor) || cell.equals(redColor + "G" + resetColor) || cell.equals(redColor + "P" + resetColor) || cell.equals(redColor + "S" + resetColor) || cell.equals(redColor + "Z" + resetColor)) {
                        cell = " ";
                    }
                    System.out.print(cell);
                }
                System.out.println();
            }
            System.out.println();
            if (checkNewEnvironment()) {
                switch (Player.getColorOfPlayerPostitionTile()) {
                    case "mountain" ->
                        TextEngine.printWithDelays("You have entered the mountain area", false);
                    case "grassland" ->
                        TextEngine.printWithDelays("You have entered the grassy plains.", false);
                    case "desert" ->
                        TextEngine.printWithDelays("You have entered the desert.", false);
                    case "ocean" ->
                        TextEngine.printWithDelays("You have enter the ocean area", false);
                    case "lost forest" ->
                        TextEngine.printWithDelays("You have entered the lost forest", false);
                    default ->
                        TextEngine.printWithDelays("You are in an empty area", false);
                }
            }
            bufferedEnviroment = environment;
        } else {
            drawCurrentRoom();
        }
    }

    /**
     * Draws the current room based on the player's position and environment.
     * This method initializes the room, sets the environment, places the player
     * and enemies in the room, and prints the room to the console.
     *
     * @throws InterruptedException if the thread is interrupted during
     * execution
     */
    public static void drawCurrentRoom() throws InterruptedException {
        currentRoom = new String[][]{
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "}
        };
        String setting = Player.getColorOfPlayerPostitionTile();
        environment = setting;
        switch (setting.toLowerCase()) {
            case "mountain", "mountain top" ->
                fillMountain(currentRoom);
            case "grassland" ->
                fillGrassland(currentRoom);
            case "desert" ->
                fillDesert(currentRoom);
            case "ocean" ->
                fillOcean(currentRoom);
            case "lost forest" ->
                fillForest(currentRoom);
            default ->
                fillEmpty(currentRoom);
        }
        for (String[] currentRoom1 : currentRoom) {
            currentRoom1[0] = " ";
        }
        int centerX = currentRoom[0].length / 2;
        int centerY = currentRoom.length / 2;
        currentRoom[centerY][centerX] = "P";
        //draw enemies in the room at random postions that arent the same as the player
        String enemyRender = "E";
        if (OpenWorld.enemyType != null) {
            switch (OpenWorld.enemyType) {
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
                default ->
                    enemyRender = "E";
            }
        }
        if (OpenWorld.encounter && OpenWorld.numberOfEnemies > 0) {
            for (int i = 0; i < OpenWorld.numberOfEnemies;) {
                int enemyX = random.nextInt(currentRoom[0].length);
                int enemyY = random.nextInt(currentRoom.length);
                if (!(currentRoom[enemyY][enemyX].equals("P") && enemyX == 0)) {
                    currentRoom[enemyY][enemyX] = redColor + enemyRender + resetColor;
                    i++;
                }
            }
        }
        for (String[] row : currentRoom) {
            for (String cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
        System.out.println();
        if (checkNewEnvironment()) {
            switch (setting) {
                case "mountain" ->
                    TextEngine.printWithDelays("You have entered the mountain area", false);
                case "mountain top" ->
                    TextEngine.printWithDelays("You have scaled the mountain top", false);
                case "grassland" ->
                    TextEngine.printWithDelays("You have entered the grassy plains.", false);
                case "desert" ->
                    TextEngine.printWithDelays("You have treked into the desert.", false);
                case "ocean" ->
                    TextEngine.printWithDelays("You have swam into the ocean area", false);
                case "lost forest" ->
                    TextEngine.printWithDelays("You have wandered into the lost forest", false);
                default ->
                    TextEngine.printWithDelays("You are in an empty area", false);
            }
        }
        bufferedEnviroment = environment;
    }

    private static final Random random = new Random();

    /**
     * The `fillMountain` function randomly fills a 2D array representing a room
     * with mountain peaks or snow based on a 50% chance.
     *
     * @param room The `room` parameter in the `fillMountain` method is a 2D
     * array of Strings. Each element in the array represents a row in a room.
     * The method iterates over each row and randomly fills it with either a
     * mountain peak or mountain snow based on a 50% chance for
     */
    private static void fillMountain(String[][] room) {
        for (String[] room1 : room) {
            for (int j = 0; j < room1.length; j++) {
                if (random.nextDouble() < 0.5) { // 50% chance
                    if (random.nextDouble() < 0.5) { // 50% chance
                        room1[j] = Player.g + "^" + resetColor; // Mountain peak
                    } else {
                        room1[j] = Player.S + "." + resetColor; // Mountain snow
                    }
                }
            }
        }
    }

    /**
     * The `fillGrassland` function populates a 2D array representing a room
     * with grass or bushes based on random probabilities.
     *
     * @param room The `room` parameter is a 2D array of Strings representing a
     * grid or room. Each element in the `room` array is an array of Strings,
     * forming rows and columns in the grid. The method `fillGrassland` iterates
     * over each element in the `room` array
     */
    private static void fillGrassland(String[][] room) {
        for (String[] room1 : room) {
            for (int j = 0; j < room1.length; j++) {
                if (random.nextDouble() < 0.7) { // 70% chance
                    if (random.nextDouble() < 0.5) { // 50% chance
                        room1[j] = Player.G + "\"" + Player.R; // Grass
                    } else {
                        room1[j] = Player.G + "," + Player.R; // Bush
                    }
                }
            }
        }
    }

    /**
     * The fillDesert function populates a 2D array representing a room with
     * different elements such as cactus, sand, and oasis based on specified
     * probabilities.
     *
     * @param room The `room` parameter in the `fillDesert` method is a 2D array
     * of Strings representing a room layout. Each element in the array
     * represents a cell in the room.
     */
    private static void fillDesert(String[][] room) {
        for (String[] room1 : room) {
            for (int j = 0; j < room1.length; j++) {
                if (random.nextDouble() < 0.7) { // 70% chance
                    if (random.nextDouble() < 0.07) { // 7% chance
                        room1[j] = Player.G + "|" + Player.R; // cactus
                    } else {
                        if (random.nextDouble() < 0.75) { // 75 chance
                            room1[j] = Player.s + "." + Player.R; // Sand
                        } else {
                            room1[j] = Player.s + "~" + Player.R; // Oasis
                        }
                    }
                }
            }
        }
    }

    /**
     * The function `fillOcean` fills a 2D array representing a room with water
     * tiles represented by the string "b~R".
     *
     * @param room The `room` parameter is a 2D array of Strings representing a
     * room or space. The method `fillOcean` is designed to fill this room with
     * water represented by the string concatenation of `Player.b + "~" +
     * Player.R`.
     */
    private static void fillOcean(String[][] room) {
        for (String[] room1 : room) {
            for (int j = 0; j < room1.length; j++) {
                room1[j] = Player.b + "~" + Player.R; // Water
            }
        }
    }

    /**
     * The fillForest function populates a 2D array representing a room with
     * elements representing trees, grass, or bushes based on random
     * probabilities.
     *
     * @param room The `fillForest` method you provided takes a 2D array of
     * Strings called `room` as a parameter. This `room` array represents a grid
     * where each element can contain different types of elements such as trees,
     * grass, or bushes. The method iterates over each element in the `
     */
    private static void fillForest(String[][] room) {
        for (String[] room1 : room) {
            for (int j = 0; j < room1.length; j++) {
                if (random.nextDouble() < 0.7) { // 7% chance
                    if (random.nextDouble() < 0.20) { // 20% chance
                        room1[j] = Player.B + "|" + Player.R; // Tree
                    } else if (random.nextDouble() < 0.5) {
                        room1[j] = Player.G + "\"" + Player.R; // grass
                    } else {
                        room1[j] = Player.G + "," + Player.R; // Bush
                    }
                }
            }
        }
    }

    /**
     * The function `fillEmpty` randomly fills empty spaces in a 2D array with a
     * 50% chance.
     *
     * @param room The `room` parameter in the `fillEmpty` method is a 2D array
     * of Strings. It represents a room where each element in the array
     * corresponds to a space in the room. The method fills empty spaces in the
     * room by setting them to a blank space character (" ") with a
     */
    private static void fillEmpty(String[][] room) {
        for (String[] room1 : room) {
            for (int j = 0; j < room1.length; j++) {
                if (random.nextDouble() < 0.5) { // 50% chance
                    room1[j] = " "; // Empty space
                }
            }
        }
    }

    /**
     * The function checks if the current environment is different from the
     * buffered environment.
     *
     * @return The method `checkNewEnvironment()` is returning a boolean value
     * that indicates whether the `environment` is different from the
     * `bufferedEnvironment`.
     */
    private static boolean checkNewEnvironment() {
        return !environment.equals(bufferedEnviroment);
    }

    /**
     * Checks the current dungeon status and provides the appropriate message
     * based on the player's current room and the number of completed dungeons.
     * This method updates the player's command if it is null or empty, and
     * prints a message with delays indicating the direction the player is
     * walking and the entrance to the next dungeon.
     *
     * @throws InterruptedException if the thread is interrupted during
     * execution
     */
    public static void dungeonCheck() throws InterruptedException {
        if (OpenWorld.holdCommand == null || OpenWorld.holdCommand.isEmpty() || OpenWorld.holdCommand.isBlank() || OpenWorld.holdCommand.equals(" ") || OpenWorld.holdCommand.equals("null")) {
            OpenWorld.holdCommand = "onward";
            TextEngine.printWithDelays("You walk " + OpenWorld.holdCommand + ".", false);
            return;
        }
        switch (Dungeon.completedDungeons) {
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

    public static void gameCompletionSequence() throws InterruptedException {
        if (Dungeon.resetedAfterWin) {
            return;
        }
        GameEngine.screenRefresh();
        TextEngine.printWithDelays("Congratulations! You have completed the game!", false);
        TextEngine.printWithDelays("Thank you for playing ASCII Adventure!", false);
        TextEngine.printWithDelays("We hope you enjoyed the game!", false);
        TextEngine.printWithDelays("You can continue to fight tougher enemies in the dungeons by typing " + yellowColor + "reset" + resetColor + " in any dungeon!", false);
        TextEngine.enterToNext();
    }
}
