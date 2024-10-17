
/**
 * DesertPyramidDungeon.java
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DesertPyramidDungeon extends Dungeon {

    private static int[] spawnPosition = DungeonGenerator.findValue(Dungeon.desertPyramidDungeon, 9);
    private static int[] bossRoom = DungeonGenerator.findValue(Dungeon.desertPyramidDungeon, 8);
    public static int[][] roomsBeenTo = DungeonGenerator.createRoomsBeenTo(Dungeon.desertPyramidDungeon.length);
    public static String direction;
    public static int[] availableMove;
    public static ArrayList<String> directionsString;
    public static List<String> items;
    private static final List<String> enemies = new ArrayList<>(List.of("Werewolf", "Witch", "Giant", "Mummy", "Minotaur"));
    private static final Random rand = new Random();
    public static boolean completed = false;
    public static boolean visited = false;
    public static boolean mapRevealed;

    public static void startRoom() throws InterruptedException { //start room
        if (!visited) {
            fresh();
            visited = true;
            items = new ArrayList<>(List.of("excalibur", "angel armor"));
        }
        if (!"Desert Pyramid Dungeon".equals(Main.getSavedPlace())) {
            currentPlayerPosition = DungeonGenerator.findValue(Dungeon.desertPyramidDungeon, 9);
        }
        room = "Desert Pyramid Dungeon";
        Main.checkSave(room);
        Main.screenRefresh();
        Dungeon.currentDungeon = "Desert Pyramid";
        GameSaveSerialization.saveGame();
        startRooms();
    }

    public static void fresh() { //fresh
        mapRevealed = false;
        visited = false;
        completed = false;
        spawnPosition = DungeonGenerator.findValue(Dungeon.desertPyramidDungeon, 9);
        bossRoom = DungeonGenerator.findValue(Dungeon.desertPyramidDungeon, 8);
        Dungeon.currentPlayerPosition = spawnPosition;
        roomsBeenTo = DungeonGenerator.createRoomsBeenTo(Dungeon.desertPyramidDungeon.length);
        lastPosition = spawnPosition.clone();
    }

    private static void startRooms() throws InterruptedException {
        numberOfEnemies = rand.nextInt(4);
        enemyType = enemies.get(rand.nextInt(enemies.size()));
        availableMove = null;
        Main.screenRefresh();
        DungeonGenerator.drawRoom(desertPyramidDungeon, roomsBeenTo, currentPlayerPosition[0], currentPlayerPosition[1], numberOfEnemies);
        directionsString = new ArrayList<>();

        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 2 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            if (!items.isEmpty()) {
                String randomItem = items.get(rand.nextInt(items.size()));
                if (hasChestInRoom(randomItem, 1)) {
                    items.remove(randomItem);
                    //lastPosition = currentPlayerPosition.clone();
                    roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                } else {
                    int[] buffer = currentPlayerPosition.clone();
                    currentPlayerPosition = lastPosition.clone(); // Save the current position before moving
                    lastPosition = buffer.clone();
                    Main.loadSave();
                }
            } else {
                fairySequence();
            }
        }
        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 10 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            TextEngine.printWithDelays("You have entered a room with a mystical fairy", false);
            TextEngine.printWithDelays("The fairy has granted you a wish of healing?", false);
            TextEngine.printWithDelays("Do you want to take it? " + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor, true);
            while (true) {
                ignore = Room.console.readLine();
                command = Room.console.readLine();
                switch (command) {
                    case "yes" -> {
                        Player.fairyHeal();
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
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
        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            keyRoomSequence();
        }
        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 5 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            if (hasItemInRoom("key", 1)) {
                roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            } else {
                int[] buffer = currentPlayerPosition.clone();
                currentPlayerPosition = lastPosition.clone(); // Save the current position before moving
                lastPosition = buffer.clone();
                Main.loadSave();
            }
        }
        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 7 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            if (hasItemInRoom("heart container", 1)) {
                roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            } else {
                int[] buffer = currentPlayerPosition.clone();
                currentPlayerPosition = lastPosition.clone(); // Save the current position before moving
                lastPosition = buffer.clone();
                Main.loadSave();
            }
        }
        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            fightRandomEnemies();
        }
        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 6) {
            roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            dungeonShop();
        }
        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            miniBossSequence();
        }
        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 8 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            TextEngine.printWithDelays("You have entered the boss room", false);
            Player.changeHealth(Enemy.spawnEnemy("Giant Sand Worm", 1));
            TextEngine.printWithDelays("You have defeated the boss and completed the dungeon!", false);
            TextEngine.enterToNext();
            Room.hasItemInRoom("Large Backpack", 1);
            //lastPosition = currentPlayerPosition.clone();
            roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            if (!completed) {
                completedDungeons++;
                completed = true;
            }
            lastPosition = null;
            OpenWorld.startRoom();
        }
        handleDirectionsAndCommands();
    }

    private static boolean testIfBossRoom(int check) throws InterruptedException {
        if (check != 0) {
            return check == 8;
        }
        return false;
    }

    public static void handleDirectionsAndCommands() throws InterruptedException {
        Main.screenRefresh();
        DungeonGenerator.drawRoom(desertPyramidDungeon, roomsBeenTo, currentPlayerPosition[0], currentPlayerPosition[1], 0);
        availableMove = DungeonGenerator.getDirections(desertPyramidDungeon, currentPlayerPosition[0], currentPlayerPosition[1]);
        if (completed) {
            TextEngine.printWithDelays("You have completed this dungeon. You can now type " + yellowColor + "leave" + resetColor + " to exit this dungeon.", false);
        }
        System.out.println("Type " + yellowColor + "map" + resetColor + " to see the map.");
        System.out.println();
        TextEngine.printWithDelays("You can move in the following directions: ", false);
        //System.out.println(availableMove[0] + "" + availableMove[1] + "" + availableMove[2] + "" + availableMove[3]);
        if (availableMove[0] > 0) {
            if (testIfBossRoom(availableMove[0])) {
                directionsString.add("boss room");
            } else {
                directionsString.add("north");
            }
        }
        if (availableMove[1] > 0) {
            if (testIfBossRoom(availableMove[1])) {
                directionsString.add("boss room");
            } else {
                directionsString.add("south");
            }
        }
        if (availableMove[2] > 0) {
            if (testIfBossRoom(availableMove[2])) {
                directionsString.add("boss room");
            } else {
                directionsString.add("west");

            }
        }
        if (availableMove[3] > 0) {
            if (testIfBossRoom(availableMove[3])) {
                directionsString.add("boss room");
            } else {
                directionsString.add("east");

            }
        }
        TextEngine.printNoDelay(yellowColor + directionsString.toString() + resetColor, true);
        while (true) {
            ignore = Room.console.readLine();
            direction = Room.console.readLine();
            switch (direction.toLowerCase().trim()) {
                case "north", "1" -> {
                    if (directionsString.contains(direction.toLowerCase())) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[0] -= 1;
                        Main.loadSave();
                    } else {
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                }
                case "east", "2" -> {
                    if (directionsString.contains(direction.toLowerCase())) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[1] += 1;
                        Main.loadSave();
                    } else {
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                }
                case "south", "3" -> {
                    if (directionsString.contains(direction.toLowerCase())) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[0] += 1;
                        Main.loadSave();
                    } else {
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                }
                case "west", "4" -> {
                    if (directionsString.contains(direction.toLowerCase())) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[1] -= 1;
                        Main.loadSave();
                    } else {
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                }
                case "boss room", "5" -> {
                    if (confirmBossContinue()) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition = bossRoom;
                        Main.loadSave();
                    } else {
                        Main.loadSave();
                    }
                }
                default -> {
                    Dungeon.defaultDungeonArgs(direction.toLowerCase());
                }
            }
        }
    }

    public static void fightRandomEnemies() throws InterruptedException {
        if (numberOfEnemies == 0) {
            TextEngine.printWithDelays("There were no enemies in this room", false);
            TextEngine.enterToNext();
            //lastPosition = currentPlayerPosition.clone();
            roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            Main.loadSave();
            return;
        }
        if (numberOfEnemies > 1) {
            TextEngine.printWithDelays("You have entered a room with " + numberOfEnemies + " " + enemyType + "s in this room!\nYou were ambushed!", false);
        } else {
            TextEngine.printWithDelays("You have entered a room with a " + enemyType + " and were ambushed!", false);
        }
        TextEngine.printWithDelays("What is your command? " + yellowColor + "fight" + resetColor + " or " + yellowColor + "run" + resetColor, true);
        while (true) {
            ignore = Room.console.readLine();
            command = Room.console.readLine();
            switch (command) {
                case "fight" -> {
                    Player.changeHealth(Enemy.spawnEnemy(enemyType, numberOfEnemies));
                    roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
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

    public static void keyRoomSequence() throws InterruptedException {
        if (numberOfEnemies == 0) {
            numberOfEnemies = 1;
        }
        if (numberOfEnemies > 1) {
            TextEngine.printWithDelays("You have entered a room with " + numberOfEnemies + " " + enemyType + "s in this room!\nYou were ambushed!", false);
        } else {
            TextEngine.printWithDelays("You have entered a room with a " + enemyType + " and were ambushed!", false);
        }
        if (numberOfEnemies > 1) {
            TextEngine.printWithDelays("They seem to be trying to protect something...", false);
        } else {
            TextEngine.printWithDelays("It seems to be trying to protect something...", false);
        }
        TextEngine.printWithDelays("What is your command? " + yellowColor + "fight" + resetColor + " or " + yellowColor + "run" + resetColor, true);
        while (true) {
            ignore = Room.console.readLine();
            command = Room.console.readLine();
            switch (command) {
                case "fight" -> {
                    Player.changeHealth(Enemy.spawnEnemy(enemyType, numberOfEnemies));
                    desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 5;
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

    private static void miniBossSequence() throws InterruptedException {
        TextEngine.printWithDelays("You have entered a room with a mini boss", false);
        Player.changeHealth(Enemy.spawnEnemy("Medusa", 1));
        desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 7;
        Main.loadSave();
    }

    private static void fairySequence() throws InterruptedException {
        desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] = 10;
        Main.loadSave();
    }
}
