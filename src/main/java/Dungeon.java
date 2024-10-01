


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
    public static void startRoom(String dungeon) throws InterruptedException{ //start room
        switch(dungeon){
            case "Meadow" -> {
                MeadowDungeon.startRoom();
            }
        }
    }
    public static void defaultDungeonArgs(String data) throws InterruptedException{ //default dungeon arguments
        switch(data){
            case "leave" -> {
                TextEngine.printWithDelays("Im sorry. You cannot leave right now.", true);
            }
            case "restart" -> {
                //figure out later
            }
            case "map" -> {
                switch (currentDungeon){
                    case "Meadow" -> {
                        DungeonGenerator.printMap(MeadowDungeon.meadowDungeon.length);
                    }
                }
            }
            default -> {
                Main.inGameDefaultTextHandling(data);
            }
        }

    }
}
