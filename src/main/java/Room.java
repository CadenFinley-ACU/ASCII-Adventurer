
import java.io.Console;
import java.util.Random;

/**
 * Room Class
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class Room {

    public final static Console console = System.console();
    public static String command;
    public static String ignore;
    public static String room = null;
    public static String redColor = "\033[0;31m"; // red color
    public static String resetColor = "\033[0m"; // reset to default color
    public static String bufferedEnviroment = null;
    public static String environment = null;

    public static boolean hasChestInRoom(String itemName, int quantity) throws InterruptedException {
        String yellowColor = "\033[1;33m"; // yellow color

        TextEngine.printWithDelays("Hey! There is a chest in this room! ", false);
        TextEngine.printWithDelays("What is your command" + yellowColor + " open it " + resetColor + "or " + yellowColor + "leave it" + resetColor, true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase().trim()) {
                case "open it" -> {
                    return hasItemInRoom(itemName, quantity);
                }
                case "leave it" -> {
                    return false;
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    public static boolean hasItemInRoom(String itemName, int quantity) throws InterruptedException {
        String yellowColor = "\033[1;33m"; // yellow color

        // Display item information
        if (quantity > 1) {
            TextEngine.printWithDelays("Item(s): " + yellowColor + itemName + resetColor + " x" + quantity, false);
        } else {
            TextEngine.printWithDelays("Item: " + yellowColor + itemName + resetColor, false);
        }

        // Highlight 'take it' and 'leave it' in yellow
        TextEngine.printWithDelays("What will you do? Type " + yellowColor + "take it" + resetColor + " to pick up the sword or " + yellowColor + "leave it" + resetColor, true);

        // Command handling loop
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
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
                    Main.inGameDefaultTextHandling(command);
            }
        }
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
    }

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

    public static void drawCurrentRoom() throws InterruptedException {
        String[][] currentRoom = {
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "}
        };
        String setting = Player.getColorOfPlayerPostitionTile();
        environment = setting;
        switch (setting.toLowerCase()) {
            case "mountain" ->
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
                if (!(currentRoom[enemyY][enemyX].equals("P") || enemyX == 0)) {
                    currentRoom[enemyY][enemyX] = redColor + enemyRender + resetColor;
                    i++;
                }
            }
        }

        for (String[] currentRoom1 : currentRoom) {
            currentRoom1[0] = " ";
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
                    TextEngine.printWithDelays("You are in the mountain area", false);
                case "grassland" ->
                    TextEngine.printWithDelays("You have entered the grassy plains.", false);
                case "desert" ->
                    TextEngine.printWithDelays("You have entered the desert.", false);
                case "ocean" ->
                    TextEngine.printWithDelays("You are in the ocean area", false);
                case "lost forest" ->
                    TextEngine.printWithDelays("You are in the lost forest", false);
                default ->
                    TextEngine.printWithDelays("You are in an empty area", false);
            }
        }
        bufferedEnviroment = environment;
    }

    private static final Random random = new Random();

    private static void fillMountain(String[][] room) {
        for (String[] room1 : room) {
            for (int j = 0; j < room1.length; j++) {
                if (random.nextDouble() < 0.5) { // 50% chance
                    room1[j] = "^"; // Mountain peak
                }
            }
        }
    }

    private static void fillGrassland(String[][] room) {
        for (String[] room1 : room) {
            for (int j = 0; j < room1.length; j++) {
                if (random.nextDouble() < 0.5) { // 50% chance
                    if (random.nextDouble() < 0.5) { // 50% chance
                        room1[j] = Player.G + "\"" + Player.R; // Grass
                    } else {
                        room1[j] = Player.G + "B" + Player.R; // Dirt
                    }
                }
            }
        }
    }

    private static void fillDesert(String[][] room) {
        for (String[] room1 : room) {
            for (int j = 0; j < room1.length; j++) {
                if (random.nextDouble() < 0.5) { // 50% chance
                    if (random.nextDouble() < 0.5) { // 50% chance
                        room1[j] = Player.S + "." + Player.R; // Sand
                    } else {
                        room1[j] = Player.S + "~" + Player.R; // Oasis
                    }
                }
            }
        }
    }

    private static void fillOcean(String[][] room) {
        for (String[] room1 : room) {
            for (int j = 0; j < room1.length; j++) {
                if (random.nextDouble() < 0.5) { // 50% chance
                    room1[j] = Player.b + "~" + Player.R; // Water
                }
            }
        }
    }

    private static void fillForest(String[][] room) {
        for (String[] room1 : room) {
            for (int j = 0; j < room1.length; j++) {
                if (random.nextDouble() < 0.5) { // 50% chance
                    if (random.nextDouble() < 0.5) { // 50% chance
                        room1[j] = Player.B + "|" + Player.R; // Grass
                    } else if (random.nextDouble() < 0.5) {
                        room1[j] = Player.G + "," + Player.R; // Tree
                    } else {
                        room1[j] = Player.G + "'" + Player.R; // Bush
                    }
                }
            }
        }
    }

    private static void fillEmpty(String[][] room) {
        for (String[] room1 : room) {
            for (int j = 0; j < room1.length; j++) {
                if (random.nextDouble() < 0.5) { // 50% chance
                    room1[j] = " "; // Empty space
                }
            }
        }
    }

    private static boolean checkNewEnvironment() {
        return !environment.equals(bufferedEnviroment);
    }
}
