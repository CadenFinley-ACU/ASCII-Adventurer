


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
    public static void startRoom() throws InterruptedException{
        Main.screenRefresh();
        switch (roomSave){
            case 0 -> room0();
            case 1 -> room1();
            default -> Main.startMenu();
        }
    }
    public static int getRoom(){
        return roomSave;
    }
    public static void fresh(){
        roomSave = 0;
    }
    private static void room0() throws InterruptedException{
        
    }
    private static void room1() throws InterruptedException{
        
    }
}
