
/**
 * MeadowDungeon.java
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MeadowDungeon extends Dungeon {

    private static int[] spawnPosition = DungeonGenerator.findValue(Dungeon.meadowDungeon, 9);
    private static int[] bossRoom = DungeonGenerator.findValue(Dungeon.meadowDungeon, 8);
    private static final List<String> enemies = new ArrayList<>(List.of("Goblin", "Skeleton", "Slime", "Mimic"));
    private static final Random rand = new Random();
    public static int[][] roomsBeenTo = DungeonGenerator.createRoomsBeenTo(Dungeon.meadowDungeon.length);
    public static List<String> items;
    public static boolean completed = false;
    public static boolean visited = false;
    public static boolean mapRevealed;

    public static void startRoom() throws InterruptedException { //start room
        if (!visited) {
            fresh();
            items = new ArrayList<>(List.of("axe", "chainmail set"));
            visited = true;
            currentPlayerPosition = DungeonGenerator.findValue(Dungeon.meadowDungeon, 9);
        }
        if (!"Meadow Dungeon".equals(Main.getSavedPlace())) {
            currentPlayerPosition = DungeonGenerator.findValue(Dungeon.meadowDungeon, 9);
        }
        room = "Meadow Dungeon";
        Main.checkSave(room);
        Dungeon.currentDungeon = "Meadow";
        GameSaveSerialization.saveGame();
        startRooms();
    }

    public static void fresh() { //fresh
        mapRevealed = false;
        visited = false;
        completed = false;
        spawnPosition = DungeonGenerator.findValue(Dungeon.meadowDungeon, 9);
        bossRoom = DungeonGenerator.findValue(Dungeon.meadowDungeon, 8);
        currentPlayerPosition = spawnPosition;
        roomsBeenTo = DungeonGenerator.createRoomsBeenTo(Dungeon.meadowDungeon.length);
        lastPosition = spawnPosition.clone();
    }

    public static void startRooms() throws InterruptedException {
        currentMiniBoss = "Golem";
        currentBoss = "Forest Giant";
        numberOfEnemies = rand.nextInt(3);
        enemyType = enemies.get(rand.nextInt(enemies.size()));
        Main.screenRefresh();
        DungeonGenerator.drawRoom(meadowDungeon, roomsBeenTo, currentPlayerPosition[0], currentPlayerPosition[1], numberOfEnemies, mapRevealed);
        if (meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 9 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            dungeonIntroText();
        }
        if (meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 2 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            itemRoom(items);
        }
        if (meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 10 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            fairyRoom();
        }
        if (meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            keyRoomSequence();
        }
        if (meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 5 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            keyRoom();
        }
        if (meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 7 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            heartContainerRoom();
        }
        if (meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            fightRandomEnemies();
        }
        if (meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 6) {
            roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]];
            dungeonShop();
        }
        if (meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            miniBossSequence();
        }
        if (meadowDungeon[currentPlayerPosition[0]][currentPlayerPosition[1]] == 8 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            bossRoom();
        }
        handleDirectionsAndCommands();
    }
}
