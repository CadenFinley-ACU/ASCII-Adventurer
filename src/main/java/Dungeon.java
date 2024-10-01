


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
    private static String currentDungeon;
    public static int completedDungeons = 0;
    public static void startRoom() throws InterruptedException{ //start room
        
    }
    
    public static int getRoom(){
        return 0;
    }
    public static void defaultDungeonArgs(String data) throws InterruptedException{ //default dungeon arguments
        switch(data){
            case "leave" -> {
                TextEngine.printWithDelays("Im sorry. You cannot leave right now.", true);
            }
            case "restart" -> {
                TextEngine.printWithDelays("Are you sure you want to restart?\nThis cannot be reversed", false);
                TextEngine.printWithDelays("What is your command: yes or no", true);
                while (true) {
                    ignore = console.readLine();
                    command = console.readLine();
                    switch (command.toLowerCase()) {
                        case "yes" -> {
                            resetCertain(currentDungeon);
                            startRoom();
                        }
                        case "no" -> {
                            Main.screenRefresh();
                            return;
                        }
                        default -> Main.inGameDefaultTextHandling(command);
                    }
                }
            }
            default -> {
                Main.inGameDefaultTextHandling(data);
            }
        }

    }
    public static String getDungeon(){ //get the dungeon
        return currentDungeon;
    }
    public static void resetAll(){ //reset all
        completedDungeons = 0;
        MeadowDungeon.fresh();
        DarkForestDungeon.fresh();
    }
    public static void resetCertain(String dungeon){ //reset certain dungeon
        
    }
    public static void completed(){
        completedDungeons++;
    }
}
