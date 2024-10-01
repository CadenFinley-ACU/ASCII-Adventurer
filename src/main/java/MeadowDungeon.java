

/**
 * MeadowDungeon
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class MeadowDungeon extends Dungeon {

    static int roomSave = 0;
    private static int[][] meadowDungeon = DungeonGenerator.generateAndReturnMatrix(5);
    private static int[] playerPosition;
    private static int[] bossRoom;
    static {
        playerPosition = DungeonGenerator.findValue(meadowDungeon, 9);
        bossRoom = DungeonGenerator.findValue(meadowDungeon, 8);

    }
    public static void startRoom() throws InterruptedException { //start room
        Main.screenRefresh();

    }

    public static int getRoom() { //get the room
        return roomSave;
    }

    public static void fresh() { //fresh
        roomSave = 0;
    }

    
}
