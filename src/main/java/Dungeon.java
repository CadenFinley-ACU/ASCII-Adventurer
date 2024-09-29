


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
}
