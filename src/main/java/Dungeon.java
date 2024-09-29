


/**
 * Spawn Room Class
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
    public static void startRoom() throws InterruptedException{
        switch (currentDungeon){
            case "Meadow" -> MeadowDungeon.startRoom();
            case "Dark Forest" -> DarkForestDungeon.startRoom();
            default -> Main.startMenu();
        }
    }
    public static void initDungeon(String dungeon) throws InterruptedException{
        room = "Dungeon";
        Main.checkSave(room);
        currentDungeon = dungeon;
        Main.loadSave();
    }
    public static int getRoom(){
        switch(currentDungeon){
            case "Meadow" -> {
                return MeadowDungeon.getRoom();
            }
            case "Dark Forest" -> {
                return DarkForestDungeon.getRoom();
            }
            default -> {
                return 0;
            }
        }
    }
    public static void defaultDungeonArgs(String data) throws InterruptedException{
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
    public static String getDungeon(){
        return currentDungeon;
    }
    public static void resetAll(){
        MeadowDungeon.fresh();
        DarkForestDungeon.fresh();
    }
    public static void resetCertain(String dungeon){
        switch(dungeon){
            case "Meadow" -> {
                MeadowDungeon.fresh();
            }
            case "Dark Forest" -> {
                DarkForestDungeon.fresh();
            }
        }
    }
}
