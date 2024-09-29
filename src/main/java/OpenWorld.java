


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
        Main.screenRefresh();
        room = "OpenWorld";
        Main.checkSave(room);
        switch (roomSave) {
            case 0:
                part0(); //start room
            case 1:
                part1(); //forest start
            case 2:
                part2(); //village path start
            case 3:
                part3(); //meadow start
            case 4:
                part4(); //bandit start
            case 5:
                part5(); //village start
            default:
                Main.startMenu();
        }
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
                    startRoom();
                }
                case "go back" -> {
                    SpawnRoom.startRoom();
                }
                case "left" -> {
                    roomSave = 2;
                    startRoom();
                }
                case "right" -> {
                    roomSave = 3;
                    startRoom();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void part2() throws InterruptedException { //2 came from 0
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: enter village or go back", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "enter village" -> {
                    roomSave = 4;
                    startRoom();
                }
                case "go back" -> {
                    roomSave = 0;
                    startRoom();
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
                        Player.changeGold(40);
                        Player.changeHealth(-38);
                        TextEngine.printWithDelays("You managed to fight them off and send them running,\nand take 40 of their gold,\nbut not without them leaving a few cuts on you.", false);
                        TextEngine.printWithDelays("You lose -38 health.\nYou continue on to the village", false);
                        TextEngine.printWithDelays("Press Enter to continue", false);
                        console.readLine();
                        completedPart4 = true;
                        roomSave = 5; //tovillage
                        startRoom();
                    }
                    case "run" -> {
                        Player.changeHealth(-17);
                        TextEngine.printWithDelays("You managed to run away from the bandits,\nbut not without them leaving a few cuts on you.", false);
                        TextEngine.printWithDelays("You lose -17 health.", false);
                        TextEngine.printWithDelays("Press Enter to continue", false);
                        roomSave = 5; //to village
                        startRoom();
                    }
                    case "plead" -> {
                        TextEngine.printWithDelays("You plead deperately from them to let you go\nThe demand all of your gold.", false);
                        TextEngine.printWithDelays("You lose -"+Player.getGold()+" gold.", false);
                        Player.changeGold(-Player.getGold());
                        TextEngine.printWithDelays("Press Enter to continue", false);
                        roomSave = 5; //to village
                        completedPart4 = true;
                        startRoom();
                    }
                    default ->
                        Main.inGameDefaultTextHandling(command);
                }
            }
        } else {
            TextEngine.printWithDelays("The path to the village we serene and quiet.\n the bandits were nowhere to be seen.", false);
            TextEngine.printWithDelays("A new path here has open up through a a quiet meadow\ndo you want to go down this path?", false);
            TextEngine.printWithDelays("What is your command: meadow or village", true);
            while (true) { 
                ignore = console.readLine();
                command=console.readLine();
                switch (command.toLowerCase()) {
                    case "meadow" -> {
                        roomSave = 3;
                        startRoom();
                    }
                    case "village" -> {
                        roomSave = 5;
                        startRoom();
                    }
                    default ->
                        Main.inGameDefaultTextHandling(command);
                }
                roomSave = 0;
            }
            //maybe this unlocks shortcut now that you have cleared the path
        }

    }

    private static void part1() throws InterruptedException { //1 came from 0 create a forest dungeon
        TextEngine.printWithDelays("You walk into a dark spooky forest, you see thre paths", false);
        TextEngine.printWithDelays("What is your command: left, right, center, go back", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "left" -> {
                }
                case "right" -> {
                }
                case "center" -> {
                }
                case "go back" -> {
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void part3() throws InterruptedException { //3 came from 4 create a meadow dungeon
        TextEngine.printWithDelays("You walk into the meadow, you see two paths", false);
        TextEngine.printWithDelays("What is your command: left, right or go back", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "left" -> {
                    roomSave = 0;
                    startRoom();
                }
                case "right" -> {
                    roomSave = 0;
                    startRoom();
                }
                case "go back" -> {
                    roomSave = 4;
                    startRoom();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void part5() throws InterruptedException { //5 came from 4 need to create village class
        TextEngine.printWithDelays("You walk into the village, there are multiple builings", false);
        TextEngine.printWithDelays("What is your command: church, hotel, shop, leave village", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "church" -> {
                }
                case "hotel" -> {
                }
                case "shop" -> {
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
}
