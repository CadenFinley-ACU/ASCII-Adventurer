
/**
 * Open World Class
 *
 * Text Adventure Game
 * SE374 F24
 * Final Project
 * Caden Finley
 * Albert Tucker
 * Grijesh Shrestha
 */
public class OpenWorld extends Room {

    static int roomSave = 0;

    public static void startRoom() throws InterruptedException {
        TextEngine.clearScreen();
        room = "OpenWorld";
        Game.checkSave(room);
        switch (roomSave) {
            case 0:
                part0();
            case 1:
                part0();
            default:
                Game.startMenu();
        }
    }

    private static void part0() throws InterruptedException {
        Game.printStatus();
        TextEngine.printWithDelays("You are in the open world,\nthe sunlight blinds your eyes as they have not adjusted from the dark cave.",false);
        TextEngine.printWithDelays("You see a long winding paths in all four directions.\nHow overwhelming...",false);
        TextEngine.printWithDelays("Which path will you take:\nForward, Left, Right, Go Back, or help",true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "forward" -> {
                    TextEngine.clearScreen();
                    roomSave = 1;
                    System.out.println("you are to the north");
                }
                case "go back" -> {
                    TextEngine.clearScreen();
                    SpawnRoom.startRoom();
                }
                case "left" -> {
                    TextEngine.clearScreen();
                    roomSave = 3;
                    System.out.println("you went to the left");
                }
                case "right" -> {
                    TextEngine.clearScreen();
                    roomSave = 4;
                    System.out.println("you went to the right");
                }
                default ->
                    Game.inGameDefaultTextHandling(command);
            }
        }
    }
}
