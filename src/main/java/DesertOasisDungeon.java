
/**
 * Desert Oasis Dungeon
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DesertOasisDungeon extends Dungeon {

    private static int[] spawnPosition = DungeonGenerator.findValue(Dungeon.desertOasisDungeon, 9);
    private static int[] bossRoom = DungeonGenerator.findValue(Dungeon.desertOasisDungeon, 8);
    public static int[][] roomsBeenTo = DungeonGenerator.createRoomsBeenTo(Dungeon.desertOasisDungeon.length);
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
            items = new ArrayList<>(List.of("master sword", "royal armor"));
        }
        if (!"Desert Oasis Dungeon".equals(Main.getSavedPlace())) {
            currentPlayerPosition = DungeonGenerator.findValue(Dungeon.desertOasisDungeon, 9);
        }
        room = "Desert Oasis Dungeon";
        Main.checkSave(room);
        Main.screenRefresh();
        Dungeon.currentDungeon = "Desert Oasis";
        GameSaveSerialization.saveGame();
        startRooms();
    }

    public static void fresh() { //fresh
        mapRevealed = false;
        visited = false;
        completed = false;
        spawnPosition = DungeonGenerator.findValue(Dungeon.desertOasisDungeon, 9);
        bossRoom = DungeonGenerator.findValue(Dungeon.desertOasisDungeon, 8);
        Dungeon.currentPlayerPosition = spawnPosition;
        currentPlayerPosition = spawnPosition;
        roomsBeenTo = DungeonGenerator.createRoomsBeenTo(Dungeon.desertOasisDungeon.length);
        lastPosition = spawnPosition.clone();
    }

    private static void startRooms() throws InterruptedException {
        currentMiniBoss = "Sphinx";
        currentBoss = "Phoenix";
        numberOfEnemies = rand.nextInt(3);
        enemyType = enemies.get(rand.nextInt(enemies.size()));
        availableMove = null;
        Main.screenRefresh();
        DungeonGenerator.drawRoom(desertOasisDungeon, roomsBeenTo, currentPlayerPosition[0], currentPlayerPosition[1], numberOfEnemies, mapRevealed);
        directionsString = new ArrayList<>();
        if (desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 2 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            if (!items.isEmpty()) {
                String randomItem = items.get(rand.nextInt(items.size()));
                if (hasChestInRoom(randomItem, 1)) {
                    items.remove(randomItem);
                    //lastPosition = currentPlayerPosition.clone();
                    roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                } else {
                    currentPlayerPosition = lastPosition.clone();
                    Main.loadSave();
                }
            } else {
                fairySequence();
            }
        }
        if (desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 9 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            Dungeon.dungeonIntroText();
            roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
        }
        if (desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 10 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            fairyRoom();
        }
        if (desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            keyRoomSequence();
        }
        if (desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 5 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            keyRoom();
        }
        if (desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 7 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            heartContainerRoom();
        }
        if (desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            fightRandomEnemies();
        }
        if (desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 6) {
            roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            dungeonShop();
        }
        if (desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            miniBossSequence();
        }
        if (desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 8 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            TextEngine.printWithDelays("You have entered the boss room", false);
            Player.changeHealth(Enemy.spawnEnemy("Phoenix", 1));
            TextEngine.printWithDelays("You have defeated the boss and completed the dungeon!", false);
            TextEngine.enterToNext();
            //lastPosition = currentPlayerPosition.clone();
            roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            if (!completed) {
                completedDungeons++;
                completed = true;
            }
            lastPosition = null;
            OpenWorld.startRoom();
        }
        handleDirectionsAndCommands();
    }

    public static void handleDirectionsAndCommands() throws InterruptedException {
        Main.screenRefresh();
        DungeonGenerator.drawRoom(desertOasisDungeon, roomsBeenTo, currentPlayerPosition[0], currentPlayerPosition[1], 0, mapRevealed);
        availableMove = DungeonGenerator.getDirections(desertOasisDungeon, currentPlayerPosition[0], currentPlayerPosition[1]);
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
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[0] -= 1;
                        Main.loadSave();
                    } else {
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                }
                case "east", "2" -> {
                    if (directionsString.contains(direction.toLowerCase())) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[1] += 1;
                        Main.loadSave();
                    } else {
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                }
                case "south", "3" -> {
                    if (directionsString.contains(direction.toLowerCase())) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[0] += 1;
                        Main.loadSave();
                    } else {
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                }
                case "west", "4" -> {
                    if (directionsString.contains(direction.toLowerCase())) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
                        currentPlayerPosition[1] -= 1;
                        Main.loadSave();
                    } else {
                        Dungeon.defaultDungeonArgs(direction.toLowerCase());
                    }
                }
                case "boss room", "5" -> {
                    if (confirmBossContinue()) {
                        lastPosition = currentPlayerPosition.clone(); // Save the current position before moving
                        roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = desertOasisDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
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
}
