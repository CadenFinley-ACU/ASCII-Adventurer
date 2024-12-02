
/**
 * DungeonInstance.java
 *
 * Text Adventure Game SE374 F24 Final Project
 * Caden Finley, Albert Tucker, Grijesh Shrestha
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The DungeonInstance class represents an instance of a dungeon in a text
 * adventure game. It extends the Dungeon class and includes various attributes
 * and methods to manage the state and behavior of the dungeon.
 */
public class DungeonInstance extends Dungeon {

    private static final Random rand = new Random();

    private int[] spawnPosition;
    private final List<String> enemies;
    public int[][] roomsBeenTo;
    public List<String> items;
    public boolean completed = false;
    public boolean visited = false;
    public boolean mapRevealed;
    public int[][] map;
    public int potentialEnemies;

    public String dungeonName;
    public String dungeonSaveName;

    public String bossType;
    public String miniBossType;

    /**
     * Initializes a new instance of the DungeonInstance class with the
     * specified parameters.
     *
     * @param enemies List of enemy types present in the dungeon.
     * @param items List of items available in the dungeon.
     * @param completed Indicates whether the dungeon has been completed.
     * @param visited Indicates whether the dungeon has been visited.
     * @param mapRevealed Indicates whether the map has been revealed.
     * @param dungeonName The name of the dungeon.
     * @param dungeonSaveName The save name of the dungeon.
     * @param currentMiniBoss The type of the mini-boss in the dungeon.
     * @param currentBoss The type of the boss in the dungeon.
     * @param numberOfEnemies The potential number of enemies in the dungeon.
     */
    public DungeonInstance(List<String> enemies, List<String> items, boolean completed, boolean visited, boolean mapRevealed, String dungeonName, String dungeonSaveName, String currentMiniBoss, String currentBoss, int numberOfEnemies) {
        super();
        this.enemies = enemies;
        this.items = items;
        this.completed = completed;
        this.visited = visited;
        this.mapRevealed = mapRevealed;
        this.dungeonName = dungeonName;
        this.dungeonSaveName = dungeonSaveName;
        this.miniBossType = currentMiniBoss;
        this.bossType = currentBoss;
        this.potentialEnemies = numberOfEnemies;
    }

    /**
     * Sets the spawn position of the player in the dungeon.
     */
    public void setValues() {
        this.spawnPosition = DungeonGenerator.findValue(map, 9);
    }

    /**
     * Starts the room sequence in the dungeon. Initializes items and player
     * position if the dungeon has not been visited.
     *
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    public void startRoom() throws InterruptedException {
        if (!visited) {
            fresh();
            items = new ArrayList<>(List.of("axe", "chainmail set"));
            visited = true;
            currentPlayerPosition = DungeonGenerator.findValue(map, 9);
        }
        if (!dungeonSaveName.equals(GameEngine.getSavedPlace())) {
            currentPlayerPosition = DungeonGenerator.findValue(map, 9);
        }
        room = dungeonSaveName;
        GameEngine.checkSave(room);
        Dungeon.currentDungeon = dungeonName;
        GameSaveSerialization.saveGame();
        startRooms();
    }

    /**
     * Initializes the roomsBeenTo array based on the size of the map.
     */
    public void setShownMap() {
        this.roomsBeenTo = DungeonGenerator.createRoomsBeenTo(map.length);
    }

    /**
     * Resets the dungeon state, including map reveal status, visited status,
     * completion status, spawn position, and rooms visited.
     */
    public void fresh() {
        mapRevealed = false;
        visited = false;
        completed = false;
        spawnPosition = DungeonGenerator.findValue(map, 9);
        currentPlayerPosition = spawnPosition;
        roomsBeenTo = DungeonGenerator.createRoomsBeenTo(map.length);
        lastPosition = spawnPosition.clone();
    }

    /**
     * Starts the rooms in the dungeon and handles various room types based on
     * the player's current position on the map.
     *
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    public void startRooms() throws InterruptedException {
        currentMiniBoss = miniBossType;
        currentBoss = bossType;
        numberOfEnemies = rand.nextInt(potentialEnemies);
        enemyType = enemies.get(rand.nextInt(enemies.size()));
        GameEngine.screenRefresh();
        DungeonGenerator.drawRoom(map, roomsBeenTo, currentPlayerPosition[0], currentPlayerPosition[1], numberOfEnemies, mapRevealed);
        if (map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 9 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            dungeonIntroText();
        }
        if (map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 2 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            itemRoom(items);
        }
        if (map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 10 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            fairyRoom();
        }
        if (map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 3 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            keyRoomSequence();
        }
        if (map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 5 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            keyRoom();
        }
        if (map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 7 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            heartContainerRoom();
        }
        if (map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 1 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            fightRandomEnemies();
        }
        if (map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 6) {
            roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] = map[currentPlayerPosition[0]][currentPlayerPosition[1]];
            dungeonShop();
        }
        if (map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 4 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            miniBossSequence();
        }
        if (map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 8 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            bossRoom();
        }
        if (map[currentPlayerPosition[0]][currentPlayerPosition[1]] == 20 && roomsBeenTo[currentPlayerPosition[0]][currentPlayerPosition[1]] == 0) {
            trappedRoom();
        }
        handleDirectionsAndCommands(true);
    }
}
