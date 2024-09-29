


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
        TextEngine.printWithDelays("You enter a meadow with a small stream running through it", false);
        TextEngine.printWithDelays("You see a small wooden bridge to the north", false);
        TextEngine.printWithDelays("What is your command: north or help", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 1;
                    Main.loadSave();
                }
                default -> Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room1() throws InterruptedException{
        TextEngine.printWithDelays("You enter room 1", false);
        TextEngine.printWithDelays("What is your command: north or help", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 2;
                    Main.loadSave();
                }
                default -> Main.inGameDefaultTextHandling(command);
            }
        }
    }
}
