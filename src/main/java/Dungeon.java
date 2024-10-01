


/**
 * Dungeon Class
 *
 * Text Adventure Game
 * SE374 F24
 * Final Project
 * Caden Finley
 * Albert Tucker
 * Grijesh Shrestha
 */
public class Dungeon extends Room {
    public static String currentDungeon;
    public static int completedDungeons = 0;
    public static int[][] meadowDungeon;
    public static int[] currentPlayerPosition;
    public static void startRoom(String dungeon) throws InterruptedException{ //start room
        switch(dungeon){
            case "Meadow" -> {
                MeadowDungeon.startRoom();
            }
            //add more dungeons here
        }
    }
    public static void generateDungeons(){
        meadowDungeon = DungeonGenerator.generateAndReturnMatrix(5);
        //add more dungeons here
    }
    public static void defaultDungeonArgs(String data) throws InterruptedException{ //default dungeon arguments
        switch(data){
            case "leave" -> {
                TextEngine.printWithDelays("Im sorry. You cannot leave right now.", true);
            }
            case "restart" -> {
                switch(currentDungeon){
                    case "Meadow" -> {
                        MeadowDungeon.fresh();
                    }
                    //add more dungeons here
                }
            }
            default -> {
                Main.inGameDefaultTextHandling(data);
            }
        }

    }
    public static void resetAll() { //reset all
        MeadowDungeon.fresh();
        //add more dungeons here
    }
}
