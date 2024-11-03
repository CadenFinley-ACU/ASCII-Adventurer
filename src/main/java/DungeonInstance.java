
/**
 * map.java
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DungeonInstance extends Dungeon {

    private static final Random rand = new Random();

    private int[] spawnPosition;
    private int[] bossRoom;
    private List<String> enemies;
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

    public DungeonInstance(List<String> enemies, List<String> items, boolean completed, boolean visited, boolean mapRevealed, int[][] map, String dungeonName, String dungeonSaveName, String currentMiniBoss, String currentBoss, int numberOfEnemies) {
        super();
        this.enemies = enemies;
        this.items = items;
        this.completed = completed;
        this.visited = visited;
        this.mapRevealed = mapRevealed;
        this.map = map;
        this.spawnPosition = DungeonGenerator.findValue(map, 9);
        this.bossRoom = DungeonGenerator.findValue(map, 8);
        this.roomsBeenTo = DungeonGenerator.createRoomsBeenTo(map.length);
        this.dungeonName = dungeonName;
        this.dungeonSaveName = dungeonSaveName;
        this.miniBossType = currentMiniBoss;
        this.bossType = currentBoss;
        this.potentialEnemies = numberOfEnemies;
    }

    public void startRoom() throws InterruptedException { //start room
        if (!visited) {
            fresh();
            items = new ArrayList<>(List.of("axe", "chainmail set"));
            visited = true;
            currentPlayerPosition = DungeonGenerator.findValue(map, 9);
        }
        if (!dungeonSaveName.equals(Main.getSavedPlace())) {
            currentPlayerPosition = DungeonGenerator.findValue(map, 9);
        }
        room = dungeonSaveName;
        Main.checkSave(room);
        Dungeon.currentDungeon = dungeonName;
        GameSaveSerialization.saveGame();
        startRooms();
    }

    public void fresh() { //fresh
        mapRevealed = false;
        visited = false;
        completed = false;
        spawnPosition = DungeonGenerator.findValue(map, 9);
        bossRoom = DungeonGenerator.findValue(map, 8);
        currentPlayerPosition = spawnPosition;
        roomsBeenTo = DungeonGenerator.createRoomsBeenTo(map.length);
        lastPosition = spawnPosition.clone();
    }

    public void startRooms() throws InterruptedException {
        currentMiniBoss = miniBossType;
        currentBoss = bossType;
        numberOfEnemies = rand.nextInt(potentialEnemies);
        enemyType = enemies.get(rand.nextInt(enemies.size()));
        Main.screenRefresh();
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
        handleDirectionsAndCommands();
    }
}
