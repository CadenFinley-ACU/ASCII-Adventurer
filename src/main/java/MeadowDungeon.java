


/**
 * MeadowDungeon.java
 *
 * Text Adventure Game
 * SE374 F24
 * Final Project
 * Caden Finley
 * Albert Tucker
 * Grijesh Shrestha
 */
public class MeadowDungeon extends Dungeon {
    static int roomSave = 0;
    public static void startRoom() throws InterruptedException{
        Main.screenRefresh();
        switch (roomSave){
            case 0 -> room0();
            default -> Main.startMenu();
        }
    }
    public static int getRoom(){
        return roomSave;
    }
    private static void room0() throws InterruptedException{
        TextEngine.printWithDelays("You enter a meadow with a small stream running through it", false);
        TextEngine.printWithDelays("You see a small wooden bridge to the north", false);
        TextEngine.printWithDelays("What is your command: north or help", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave++;
                    Main.loadSave();
                }
                default -> Main.inGameDefaultTextHandling(command);
            }
        }
    }
}
