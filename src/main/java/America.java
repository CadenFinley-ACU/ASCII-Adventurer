import java.io.Console;
/**
 * America Test Area
 * 
 * Text Adventure Game
 * SE374 F24
 * Final Project
 * Caden Finley
 * Albert Tucker
 * Grijesh Shrestha
 */
public class America {
    private final static Console console = System.console();
    private static String command;
    @SuppressWarnings("unused")
    private static String ignore;
    public static void startRoom() throws InterruptedException{
        TextEngine.printWithDelays("You are in a lucious, green area with trees, not Abilene.\nWhat is your command: north or exit?",true);
            while (true) {
                ignore = console.readLine();
                command = console.readLine();
                switch (command.toLowerCase()) {
                    case "north":
                        TextEngine.clearScreen();
                        Canada.startRoom();;
                    default:
                        GameStart.defaultTextHandling(command);
            }
        }
    }     
}
