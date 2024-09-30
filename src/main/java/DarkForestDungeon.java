


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
    public static void startRoom() throws InterruptedException{ //start room
        Main.screenRefresh();
        switch (roomSave){
            case 0 -> room0();
            case 1 -> room1();
            default -> Main.startMenu();
        }
    }
    public static int getRoom(){ //get the room
        return roomSave;
    }
    public static void fresh(){ //fresh
        roomSave = 0;
    }
    private static void room0() throws InterruptedException{ //0
        TextEngine.printWithDelays("You enter a dark forest with a small path leading to the north", false);
        TextEngine.printWithDelays("You see a small clearing to the east", false);
        TextEngine.printWithDelays("What is your command: north, east or help", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 1;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 2;
                    Main.loadSave();
                }
                default ->
                    defaultDungeonArgs(command);
            }
        }
        
    }
    private static void room1() throws InterruptedException{
        TextEngine.printWithDelays("You follow the path and see a chest", false);
        TextEngine.printWithDelays("What is your command: open or go back", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "open" -> {

                }
                case "go back" -> {
                    roomSave = 0;
                    Main.loadSave();
                }
                default ->
                    defaultDungeonArgs(command);
            }
        }
    }
}
