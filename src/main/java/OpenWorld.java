
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
                part0();
            case 1:
                part1();
            case 2:
                part2();
            case 3:
            //part3();
            case 4:
                part4();
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

    private static void part2() throws InterruptedException { //2
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
                    roomSave = 4;
                    startRoom();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void part4() throws InterruptedException { //4
        if (!completedPart4) {
            TextEngine.printWithDelays("As you are walking along the path to the village you are jumped by some bandits", false);
            TextEngine.printWithDelays("What is your command: fight, run, plead", true);
            while (true) {
                ignore = console.readLine();
                command = console.readLine();
                switch (command.toLowerCase()) {
                    case "fight" -> {
                        Player.changeGold(40);
                        Player.changeHealth(-17);
                        TextEngine.printWithDelays("You managed to fight them off and send them running,\n and take 40 of their gold,\nbut not without them leaving a few cuts on you.", false);
                        TextEngine.printWithDelays("You lose -17 health.\nYou continue on to the village", false);
                        TextEngine.printWithDelays("Press Enter to continue", false);
                        console.readLine();
                        completedPart4 = true;
                        roomSave = 6;
                        startRoom();
                    }
                    case "run" -> {
                        roomSave = 0;
                        startRoom();
                    }
                    case "plead" -> {
                        roomSave = 0;
                        startRoom();
                    }
                    default ->
                        Main.inGameDefaultTextHandling(command);
                }
            }
        } else {
            TextEngine.printWithDelays("The path to the village we serene and quiet.", false);
            TextEngine.printWithDelays("Press Enter to continue", false);
            console.readLine();
            roomSave = 0;
            //maybe this unlocks shortcut now that you have cleared the path
        }

    }

    private static void part1() throws InterruptedException { //1
        TextEngine.printWithDelays("You walk into a dark spooky forest", false);
        TextEngine.printWithDelays("What is your command: enter forest or go back", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "enter village" -> {
                    roomSave = 2;
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
}
