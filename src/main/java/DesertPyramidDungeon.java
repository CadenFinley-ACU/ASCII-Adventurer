
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

    static String resetColor = "\033[0m"; // reset to default color
    static String yellowColor = "\033[1;33m"; // yellow color

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

    public static void startRoom() throws InterruptedException { //start room
        if (!visited) {
            fresh();
            visited = true;
            items = new ArrayList<>(List.of("excalibur", "angel armor", "super health potion"));
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
        visited = false;
        completed = false;
        spawnPosition = DungeonGenerator.findValue(Dungeon.desertPyramidDungeon, 9);
        bossRoom = DungeonGenerator.findValue(Dungeon.desertPyramidDungeon, 8);
        Dungeon.currentPlayerPosition = spawnPosition;
        roomsBeenTo = DungeonGenerator.createRoomsBeenTo(Dungeon.desertPyramidDungeon.length);
        lastPosition = spawnPosition.clone();
    }

    private static void startRooms() throws InterruptedException {
        availableMove = null;
        Main.screenRefresh();
        DungeonGenerator.drawRoom(desertPyramidDungeon, roomsBeenTo, currentPlayerPosition[0], currentPlayerPosition[1]);
        directionsString = new ArrayList<>();

        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 2 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            if (!items.isEmpty()) {
                String randomItem = items.get(rand.nextInt(items.size()));
                if (hasChestInRoom(randomItem, 1)) {
                    items.remove(randomItem);
                    //lastPosition = currentPlayerPosition.clone();
                    roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                } else {
                    currentPlayerPosition = lastPosition.clone();
                    Main.loadSave();
                }
            } else {
                TextEngine.printWithDelays("You have found all of the items in the dungeon.", false);
                TextEngine.enterToNext();
                //lastPosition = currentPlayerPosition.clone();
                roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                Main.loadSave();
            }
        }
        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            if (hasLockedChestInRoom("heart container", 1)) {
                //lastPosition = currentPlayerPosition.clone();
                roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            } else {
                currentPlayerPosition = lastPosition.clone();
                Main.loadSave();
            }
        }
        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            fightRandomEnemies(4);
        }
        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 6 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            trappedRoom();
            //lastPosition = currentPlayerPosition.clone();
            roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            Main.loadSave();
        }
        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            TextEngine.printWithDelays("You have entered a room with a mini boss", false);
            Player.changeHealth(Enemy.spawnEnemy("Medusa", 1));
            Room.hasItemInRoom("heart container", 1);
            //lastPosition = currentPlayerPosition.clone();
            roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            Main.loadSave();
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

    private static boolean confirmBossContinue() throws InterruptedException {
        TextEngine.printWithDelays("Are you sure you want to continue to the boss room? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ")", true);
        while (true) {
            ignore = Room.console.readLine();
            command = Room.console.readLine();
            switch (command.toLowerCase().trim()) {
                case "yes" -> {
                    return true;
                }
                case "no" -> {
                    return false;
                }
                default -> {
                    Dungeon.defaultDungeonArgs(command.toLowerCase());
                }
            }
        }
    }

    private static void handleDirectionsAndCommands() throws InterruptedException {
        Main.screenRefresh();
        DungeonGenerator.drawRoom(desertPyramidDungeon, roomsBeenTo, currentPlayerPosition[0], currentPlayerPosition[1]);
        availableMove = DungeonGenerator.getDirections(desertPyramidDungeon, currentPlayerPosition[0], currentPlayerPosition[1]);
        if (completed) {
            TextEngine.printWithDelays("You have completed this dungeon. You can now type " + yellowColor + "leave" + resetColor + " to exit this dungeon.", false);
        }
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

    public static void fightRandomEnemies(int number) throws InterruptedException {
        int numberOfEnemies = rand.nextInt(number);
        if (numberOfEnemies == 0) {
            TextEngine.printWithDelays("There were no enemies in this room", false);
            TextEngine.enterToNext();
            //lastPosition = currentPlayerPosition.clone();
            roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            Main.loadSave();
            return;
        }
        TextEngine.printWithDelays("You have entered a room with enemies and were ambushed!", false);
        String enemyType = enemies.get(rand.nextInt(enemies.size()));
        Player.changeHealth(Enemy.spawnEnemy(enemyType, numberOfEnemies));
        //lastPosition = currentPlayerPosition.clone();
        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
        Main.loadSave();
    }

}
