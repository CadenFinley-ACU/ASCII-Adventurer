
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
    public static boolean previousAutoSettings;
    public static void generateDungeons(){
        meadowDungeon = DungeonGenerator.generateAndReturnMatrix(5);
        meadowDungeon = DungeonGenerator.generateAndReturnMatrix(5);
        meadowDungeon = DungeonGenerator.generateAndReturnMatrix(7);
        meadowDungeon = DungeonGenerator.generateAndReturnMatrix(7);
        meadowDungeon = DungeonGenerator.generateAndReturnMatrix(9);
        meadowDungeon = DungeonGenerator.generateAndReturnMatrix(9);
        meadowDungeon = DungeonGenerator.generateAndReturnMatrix(9);
        meadowDungeon = DungeonGenerator.generateAndReturnMatrix(13);
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
            case "map" -> {
                if(Player.getName().equals("Debug!")){
                    switch(currentDungeon){
                        case "Meadow" -> {
                            DungeonGenerator.printMap(meadowDungeon);
                        }
                        //add more dungeons here
                    }
                }
                else{
                    Main.inGameDefaultTextHandling(data);
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
    public static void autoCheck(){
        previousAutoSettings = Player.autoFight;
        if(Player.autoFight){
            Player.autoFight = false;
        }
    }

}
