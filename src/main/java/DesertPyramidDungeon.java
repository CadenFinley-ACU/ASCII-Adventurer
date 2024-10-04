
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

    private static final int[] spawnPosition = DungeonGenerator.findValue(Dungeon.desertPyramidDungeon, 9);
    private static final int[] bossRoom = DungeonGenerator.findValue(Dungeon.desertPyramidDungeon, 8);
    private static int[] save = spawnPosition;
    private static int[] lastPosition = spawnPosition.clone(); // Variable to store the last position
    public static int[][] roomsBeenTo = DungeonGenerator.createRoomsBeenTo(Dungeon.desertPyramidDungeon.length);
    public static String direction;
    public static int[] availableMove;
    public static ArrayList<String> directionsString;
    private static int foundItemRooms = 0;
    private static List<String> items = new ArrayList<>(List.of("excalibur", "angel armor", "super health potion"));
    private static final List<String> enemies = new ArrayList<>(List.of("Werewolf", "Witch", "Giant", "Mummy", "Minotaur"));
    private static final Random rand = new Random();
    public static boolean completed = false;

    public static void startRoom() throws InterruptedException { //start room
        room = "Desert Pyramid Dungeon";
        Main.checkSave(room);
        Main.screenRefresh();
        Dungeon.currentDungeon = "Desert Pyramid";
        currentPlayerPosition = save;
        startRooms();
    }

    public static void fresh() { //fresh
        Dungeon.autoCheck();
        completed = false;
        items = new ArrayList<>(List.of("excalibur", "angel armor", "super health potion"));
        foundItemRooms = 0;
        Dungeon.currentPlayerPosition = spawnPosition;
        currentPlayerPosition = spawnPosition;
        roomsBeenTo = DungeonGenerator.createRoomsBeenTo(Dungeon.desertPyramidDungeon.length);
        save = spawnPosition;
        lastPosition = spawnPosition.clone();
    }

    private static void startRooms() throws InterruptedException {
        availableMove = null;
        Main.screenRefresh();
        DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(desertPyramidDungeon, roomsBeenTo, currentPlayerPosition);
        directionsString = new ArrayList<>();

        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 2 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            if (foundItemRooms < items.size()) {
                String randomItem = items.get(rand.nextInt(items.size()));
                if (hasChestInRoom(randomItem, 1)) {
                    items.remove(randomItem);
                    lastPosition = currentPlayerPosition.clone();
                    roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                    foundItemRooms++;
                } else {
                    currentPlayerPosition = lastPosition.clone();
                    save = currentPlayerPosition.clone();
                    Main.loadSave();
                }
            } else {
                TextEngine.printWithDelays("You have already found all the items in this dungeon", false);
                lastPosition = currentPlayerPosition.clone();
                roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                Main.loadSave();
            }
        }
        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            if (hasItemInRoom("heart container", 1)) {
                lastPosition = currentPlayerPosition.clone();
                roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                foundItemRooms++;
            } else {
                currentPlayerPosition = lastPosition.clone();
                save = currentPlayerPosition.clone();
                Main.loadSave();
            }
        }
        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            fightRandomEnemies(4);
        }
        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 6 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            trappedRoom();
            lastPosition = currentPlayerPosition.clone();
            roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            Main.loadSave();
        }
        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            TextEngine.printWithDelays("You have entered a room with a mini boss", false);
            Player.changeHealth(Enemy.spawnEnemy("Medusa", 1));
            Room.hasItemInRoom("heart container", 1);
            lastPosition = currentPlayerPosition.clone();
            roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            Main.loadSave();
        }
        if (desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 8 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            TextEngine.printWithDelays("You have entered the boss room", false);
            Player.changeHealth(Enemy.spawnEnemy("Giant Sand Worm", 1));
            TextEngine.printWithDelays("You have defeated the boss and completed the dungeon!", false);
            Room.hasItemInRoom("Large Backpack", 1);
            lastPosition = currentPlayerPosition.clone();
            roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            if (!completed) {
                completedDungeons++;
            }
            Player.autoFight = Dungeon.previousAutoSettings;
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
        TextEngine.printWithDelays("Are you sure you want to continue to the boss room? (yes or no)", true);
        while (true) {
            ignore = Room.console.readLine();
            command = Room.console.readLine();
            switch (command.toLowerCase()) {
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
        DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(desertPyramidDungeon, roomsBeenTo, currentPlayerPosition);
        availableMove = DungeonGenerator.getDirections(desertPyramidDungeon, currentPlayerPosition[0], currentPlayerPosition[1]);
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
        TextEngine.printNoDelay(directionsString.toString(), true);
        while (true) {
            ignore = Room.console.readLine();
            direction = Room.console.readLine();
            switch (direction.toLowerCase()) {
                case "north" -> {
                    if (directionsString.contains(direction.toLowerCase())) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[0] -= 1;
                        save = currentPlayerPosition.clone();
                        Main.loadSave();
                    } else {
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                }
                case "east" -> {
                    if (directionsString.contains(direction.toLowerCase())) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[1] += 1;
                        save = currentPlayerPosition.clone();
                        Main.loadSave();
                    } else {
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                }
                case "west" -> {
                    if (directionsString.contains(direction.toLowerCase())) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[1] -= 1;
                        save = currentPlayerPosition.clone();
                        Main.loadSave();
                    } else {
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                }
                case "south" -> {
                    if (directionsString.contains(direction.toLowerCase())) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[0] += 1;
                        save = currentPlayerPosition.clone();
                        Main.loadSave();
                    } else {
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                }
                case "boss room" -> {
                    if (confirmBossContinue()) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition = bossRoom;
                        save = currentPlayerPosition.clone();
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
            lastPosition = currentPlayerPosition.clone();
            roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            Main.loadSave();
            return;
        }
        TextEngine.printWithDelays("You have entered a room with enemies and were ambushed!", false);
        String enemyType = enemies.get(rand.nextInt(enemies.size()));
        Player.changeHealth(Enemy.spawnEnemy(enemyType, numberOfEnemies));
        lastPosition = currentPlayerPosition.clone();
        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertPyramidDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
        Main.loadSave();
    }

}
