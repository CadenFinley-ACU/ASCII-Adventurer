


/**
 * darkforestdungeon
 *
 * Text Adventure Game
 * SE374 F24
 * Final Project
 * Caden Finley
 * Albert Tucker
 * Grijesh Shrestha
 */
public class DarkForestDungeon extends Dungeon {
    static int roomSave = 0;
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
