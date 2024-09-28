import java.io.Console;

/**
 * Room Engine /make this game engine and each room or area of rooms in their own individual classes
 * 
 * Text Adventure Game
 * SE374 F24
 * Final Project
 * Caden Finley
 * Albert Tucker
 * Grijesh Shrestha
 */
class GameStart {
    private final static Console console = System.console();
    private static String command;
    @SuppressWarnings("unused")
    private static String ignore;
    public static void main(String[] args) throws InterruptedException{
        TextEngine.clearScreen();
        startMenu();
    }
    public static void startMenu() throws InterruptedException {
        TextEngine.printNoDelay("Adventure V1, by BarrettHall:: Albert Tucker, Caden Finley, and Grijesh Shrestha",false);
        TextEngine.printWithDelays("Welcome to the game! What is your command: start, settings, or exit?",true);
        while(true){
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "start":
                    TextEngine.clearScreen();
                    America.startRoom();       
                case "settings":
                    TextEngine.clearScreen();
                    SettingsMenu.start();
                case "exit":
                    TextEngine.printWithDelays("See ya next time!",false);
                    TextEngine.clearScreen();
                    System.exit(0);
                default:
                    TextEngine.printWithDelays("I'm sorry, I don't understand that command.",true);
            }
        }
    }
}