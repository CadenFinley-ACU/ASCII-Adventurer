
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
    public static int[][] darkForestDungeon;
    public static int[][] mountainCaveDungeon;
    public static int[][] mountainTopDungeon;
    public static int[][] desertOasisDungeon;
    public static int[][] desertPlainsDungeon;
    public static int[][] desertPyramidDungeon;

    public static int[] currentPlayerPosition;
    public static boolean previousAutoSettings;
    public static void generateDungeons(){
        meadowDungeon = DungeonGenerator.generateAndReturnMatrix(5);
        darkForestDungeon = DungeonGenerator.generateAndReturnMatrix(5);
        mountainCaveDungeon = DungeonGenerator.generateAndReturnMatrix(7);
        mountainTopDungeon = DungeonGenerator.generateAndReturnMatrix(7);
        desertOasisDungeon = DungeonGenerator.generateAndReturnMatrix(9);
        desertPlainsDungeon = DungeonGenerator.generateAndReturnMatrix(9);
        desertPyramidDungeon = DungeonGenerator.generateAndReturnMatrix(11);
        // meadowDungeon = DungeonGenerator.generateAndReturnMatrix(13);
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
                    case "Dark Forest" -> {
                        DarkForestDungeon.fresh();
                    }
                    case "Mountain Cave" -> {
                        MountainCaveDungeon.fresh();
                    }
                    case "Mountain Top" -> {
                        MountainTopDungeon.fresh();
                    }
                    case "Desert Oasis" -> {
                        DesertOasisDungeon.fresh();
                    }
                    case "Desert Plains" -> {
                        DesertPlainsDungeon.fresh();
                    }
                    case "Desert Pyramid" -> {
                        DesertPyramidDungeon.fresh();
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
                        case "Dark Forest" -> {
                            DungeonGenerator.printMap(darkForestDungeon);
                        }
                        case "Mountain Cave" -> {
                            DungeonGenerator.printMap(mountainCaveDungeon);
                        }
                        case "Mountain Top" -> {
                            DungeonGenerator.printMap(mountainCaveDungeon);
                        }
                        case "Desert Oasis" -> {
                            DungeonGenerator.printMap(desertOasisDungeon);
                        }
                        case "Desert Plains" -> {
                            DungeonGenerator.printMap(desertPlainsDungeon);
                        }
                        case "Desert Pyramid" -> {
                            DungeonGenerator.printMap(desertPyramidDungeon);
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
        DarkForestDungeon.fresh();
        MountainCaveDungeon.fresh();
        MountainTopDungeon.fresh();
        DesertOasisDungeon.fresh();
        DesertPlainsDungeon.fresh();
        DesertPyramidDungeon.fresh();
        //add more dungeons here
    }
    public static void autoCheck(){
        previousAutoSettings = Player.autoFight;
        if(Player.autoFight){
            Player.autoFight = false;
        }
    }

}
