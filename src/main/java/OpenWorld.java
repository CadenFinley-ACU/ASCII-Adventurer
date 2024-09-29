



/**
 * Open World Class
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
//maybe procurally generated dungeons using birary states to matrix arrrys ti create rooms in  path
public class OpenWorld extends Room {

    static int roomSave = 0;
    static boolean completedPart4 = false;

    public static void startRoom() throws InterruptedException {
        room = "OpenWorld";
        Main.checkSave(room);
        Main.screenRefresh();
        switch (roomSave) {
            case 0:
                part0(); //start room
            case 1:
                part1(); //forest dungeon start
            case 2:
                part2(); //village path start
            case 3:
                part3(); //meadow dungeon start
            case 4:
                part4(); //bandit start
            case 5:
                Village.startRoom(); //village start
            default:
                Main.startMenu();
        }
    }
    public static void resetAll(){
        roomSave = 0;
        completedPart4 = false;
    }
    private static void part0() throws InterruptedException { //0
        TextEngine.printWithDelays("You are in the open world,\nthe sunlight blinds your eyes as they have not adjusted from the dark cave.", false);
        TextEngine.printWithDelays("You see a long winding paths in all four directions.\nHow overwhelming...", false);
        TextEngine.printWithDelays("Which path will you take:\nForward, Left, Right, Go Back, or help", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "forward" -> {
                    roomSave = 1;
                    Main.loadSave();
                }
                case "go back" -> {
                    SpawnRoom.startRoom();
                }
                case "left" -> {
                    roomSave = 2;
                    Main.loadSave();
                }
                case "right" -> {
                    roomSave = 3;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void part2() throws InterruptedException { //2 came from 0
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: go on or go back", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "go on" -> {
                    roomSave = 4;
                    Main.loadSave();
                }
                case "go back" -> {
                    roomSave = 0;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void part4() throws InterruptedException { //4 came from 2 maybe create combat system for later fights as this is just example for class
        if (!completedPart4) {
            TextEngine.printWithDelays("As you are walking along the path to the village you are jumped by some bandits", false);
            TextEngine.printWithDelays("What is your command: fight, run, plead", true);
            while (true) {
                ignore = console.readLine();
                command = console.readLine();
                switch (command.toLowerCase()) {
                    case "fight" -> {
                        Main.screenRefresh();
                        Player.changeHealth(Enemy.spawnEnemy("bandit",3));
                        TextEngine.enterToNext();
                        completedPart4 = true;
                        roomSave = 5; //tovillage
                        Main.loadSave();
                    }
                    case "run" -> {
                        Main.screenRefresh();
                        TextEngine.printWithDelays("You managed to run away from the bandits.", false);
                        TextEngine.enterToNext();
                        roomSave = 2; //goes back
                        Main.loadSave();
                    }
                    case "plead" -> {
                        Player.changeGold(-Player.getGold());
                        Main.screenRefresh();
                        TextEngine.printWithDelays("You plead deperately from them to let you go\nThe demand all of your gold.", false);
                        TextEngine.printWithDelays("They took everything you had.", false);
                        TextEngine.enterToNext();
                        roomSave = 5; //to village
                        completedPart4 = true;
                        Main.loadSave();
                    }
                    default ->
                        Main.inGameDefaultTextHandling(command);
                }
            }
        } else {
            TextEngine.printWithDelays("The path was serene and quiet.\n the bandits were nowhere to be seen.", false);
            TextEngine.printWithDelays("A path here has opened up through a meadow", false);
            TextEngine.printWithDelays("What is your command: meadow, village, or back to start", true);
            while (true) { 
                ignore = console.readLine();
                command=console.readLine();
                switch (command.toLowerCase()) {
                    case "meadow" -> {
                        roomSave = 3;
                        Main.loadSave();
                    }
                    case "village" -> {
                        roomSave = 5;
                        Main.loadSave();
                    }
                    case "back to start" -> {
                        roomSave = 2;
                        Main.loadSave();
                    }
                    default ->
                        Main.inGameDefaultTextHandling(command);
                }
                roomSave = 0;
            }
        }

    }

    private static void part1() throws InterruptedException { //1 came from 0 create a forest dungeon
        TextEngine.printWithDelays("You walk into a dark spooky forest, if you choose to go on you cannot go back.", false);
        TextEngine.printWithDelays("What is your command: go on or go back", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "go on" -> {
                    roomSave =0;
                    //create forest dungeon
                }
                case "go back" -> {
                    roomSave = 0;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void part3() throws InterruptedException { //3 came from 4 create a meadow dungeon
        TextEngine.printWithDelays("You walk into the meadow, if you choose to go on you cannot go back.", false);
        TextEngine.printWithDelays("What is your command: go on or go back", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "go on" -> {
                    roomSave=4;
                    //create meadow dungeon
                    Dungeon.initDungeon("Meadow");
                }
                case "go back" -> {
                    roomSave = 4;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
}
