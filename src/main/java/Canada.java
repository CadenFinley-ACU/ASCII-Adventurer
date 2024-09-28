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
public class Canada {
    private final static Console console = System.console();
    private static String command;
    @SuppressWarnings("unused")
    private static String ignore;
    public static boolean startRoom() throws InterruptedException{
        TextEngine.printWithDelays("\"Eh, you are in Canada. Good luck with all the mooses.\n What is your command: north, south or exit?",true);
            while (true) {
                ignore = console.readLine();
                command = console.readLine();
                switch (command.toLowerCase()) {
                    case "north":
                        TextEngine.clearScreen();
                        TextEngine.printWithDelays("Eh, you are on the North Pole. Good luck with the cold, hope you brought your coat. brrrrrrr...",false);
                        System.exit(0);
                    case "south":
                        TextEngine.clearScreen();
                        America.startRoom();
                    case "exit":
                        TextEngine.printWithDelays("Returning to main menu.", false);
                        TextEngine.clearScreen();
                        GameStart.startMenu();
                    default:
                        TextEngine.printWithDelays("I'm sorry, I don't understand that command.",true);                  
                }
                return true;
            }
        }
}