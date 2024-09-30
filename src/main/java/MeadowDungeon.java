

/**
 * MeadowDungeon
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class MeadowDungeon extends Dungeon {

    static int roomSave = 0;
    static boolean room2Completed = false;
    public static void startRoom() throws InterruptedException {
        Main.screenRefresh();
        switch (roomSave) {
            case 0 ->
                room0();
            case 1 ->
                room1();
            case 2 ->
                room2();
            default ->
                Main.startMenu();
        }
    }

    public static int getRoom() {
        return roomSave;
    }

    public static void fresh() {
        roomSave = 0;
    }

    private static void room0() throws InterruptedException {
        TextEngine.printWithDelays("You enter a meadow with a small stream running through it!", false);
        TextEngine.printWithDelays("You see a small wooden bridge to the north. You also see winding path to the east", false);
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

    private static void room1() throws InterruptedException {
        TextEngine.printWithDelays("You cross the bridge and see a chest", false);
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

    private static void room2() throws InterruptedException { //2 came from room1
        if (room2Completed) {
            TextEngine.printWithDelays("Three doors open up.\nWhich way would you like to go?", false);
            TextEngine.printWithDelays("What is your command: Left, right, center, or go back", true);
            while (true) {
                ignore = console.readLine();
                command = console.readLine();
                switch (command.toLowerCase()) {
                    case "left" -> {
                        roomSave = 3;
                        Main.loadSave();
                    }
                    case "right" -> {
                        roomSave = 4;
                        Main.loadSave();
                    }
                    case "center" -> {
                        roomSave = 5;
                        Main.loadSave();
                    }
                    case "go back" -> {
                        roomSave = 1;
                        Main.loadSave();
                    }
                    default ->
                        defaultDungeonArgs(command);
                }
            }
        } else {
            TextEngine.printWithDelays("You head east and run into 3 goblins and an orc.", false);
            TextEngine.printWithDelays("What is your command: fight or run", true);
            while (true) {
                ignore = console.readLine();
                command = console.readLine();
                switch (command.toLowerCase()) {
                    case "fight" -> {
                        Player.changeHealth(Enemy.spawnEnemy("goblin", 3) + Enemy.spawnEnemy("orc", 1));
                        TextEngine.enterToNext();
                        room2Completed = true;
                        roomSave = 2;
                        Main.loadSave();
                    }
                    case "run" -> {
                        TextEngine.printWithDelays("You run from the goblins and the orc", false);
                        roomSave = 1;
                        Main.loadSave();
                    }
                    default ->
                        defaultDungeonArgs(command);
                }
            }
        }
    }
}
