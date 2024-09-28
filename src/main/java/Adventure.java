import java.io.Console;

/**
 * Room Engine???
 * 
 * Text Adventure Game
 * SE374 F24
 * Final Project
 * Caden Finley
 * Albert Tucker
 * Grijesh Shrestha
 */
class Adventure {
    private final static Console console = System.console();
    private static String command;
    @SuppressWarnings("unused")
    private static String ignore;
    public static void main(String[] args) throws InterruptedException{
        TextEngine.clearScreen();
        startMenu();
    }
    private static void startMenu() throws InterruptedException {
        TextEngine.printNoDelay("Adventure V1, by BarrettHall:: Albert Tucker, Caden Finley, and Grijesh Shrestha",false);
        TextEngine.printWithDelays("Welcome to the game! What is your command: start, settings, or exit?",true);
        while(true){
            ignore = console.readLine();
            command = console.readLine();
            switch (command) {
                case "start":
                    TextEngine.clearScreen();
                    inAmerica();       
                case "settings":
                    TextEngine.clearScreen();
                    settings();
                case "exit":
                    TextEngine.printWithDelays("See ya next time!",false);
                    TextEngine.clearScreen();
                    System.exit(0);
                default:
                    TextEngine.printWithDelays("I'm sorry, I don't understand that command.",true);
            }
        }
    }
    private static void settings() throws InterruptedException{
        String lastSavedState = TextEngine.speedSetting;
        TextEngine.printNoDelay("Settings",false);
        TextEngine.printNoDelay("This is the settings menu. Here you can change the speed of the text.", false);
        TextEngine.printNoDelay("The current speed is set to: " + TextEngine.speedSetting, false);
        TextEngine.printWithDelays("You can change the speed to: Slow, Normal, Fast, or NoDelay", true);
        while(true){
            ignore = console.readLine();
            command = console.readLine();
            switch (command) {
                case "slow" -> {
                    TextEngine.speedSetting = "Slow";
                    TextEngine.printNoDelay("Slow",false);
                    TextEngine.printWithDelays("This is what SLOW text looks like", false);
                    TextEngine.printNoDelay("Confirm?(yes or no)", true);
                    ignore = console.readLine();
                    command = console.readLine();
                    if (command.equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.speedSetting = "Slow";
                        TextEngine.clearScreen();
                        startMenu();
                    } else {
                        TextEngine.printWithDelays("Settings not saved.", false);
                        TextEngine.speedSetting = lastSavedState;
                        TextEngine.clearScreen();
                        settings();
                    }
                }
                case "normal" -> {
                    TextEngine.speedSetting = "Normal";
                    TextEngine.printNoDelay("Normal",false);
                    TextEngine.printWithDelays("This is what NORMAL text looks like. It is like this by default.", false);
                    TextEngine.printNoDelay("Confirm?(yes or no)", true);
                    ignore = console.readLine();
                    command = console.readLine();
                    if (command.equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.speedSetting = "Normal";
                        TextEngine.clearScreen();
                        startMenu();
                    } else {
                        TextEngine.printWithDelays("Settings not saved.", false);
                        TextEngine.speedSetting = lastSavedState;
                        TextEngine.clearScreen();
                        settings();
                    }
                }
                case "fast" -> {
                    TextEngine.speedSetting = "Fast";
                    TextEngine.printNoDelay("Fast",false);
                    TextEngine.printWithDelays("This is what FAST text looks like", false);
                    TextEngine.printNoDelay("Confirm?(yes or no)", true);
                    ignore = console.readLine();
                    command = console.readLine();
                    if (command.equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.speedSetting = "Fast";
                        TextEngine.clearScreen();
                        startMenu();
                    } else {
                        TextEngine.printWithDelays("Settings not saved.", false);
                        TextEngine.speedSetting = lastSavedState;
                        TextEngine.clearScreen();
                        settings();
                    }
                }
                case "nodelay" -> {
                    TextEngine.speedSetting = "NoDelay";
                    TextEngine.printNoDelay("NoDelay",false);
                    TextEngine.printNoDelay("You in a rush or somethin?", false);
                    TextEngine.printNoDelay("Confirm?(yes or no)", true);
                    ignore = console.readLine();
                    command = console.readLine();
                    if (command.equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.speedSetting = "NoDelay";
                        TextEngine.clearScreen();
                        startMenu();
                    } else {
                        TextEngine.printWithDelays("Settings not saved.", false);
                        TextEngine.speedSetting = lastSavedState;
                        TextEngine.clearScreen();
                        settings();
                    }
                }
                case "exit" -> {
                    TextEngine.printWithDelays("Returning to main menu.", false);
                    TextEngine.clearScreen();
                    startMenu();
                }
                default -> TextEngine.printWithDelays("I'm sorry, I don't understand that command.", true);
            }
        }
    }
    private static void inAmerica() throws InterruptedException{
        TextEngine.printWithDelays("You are in a lucious, green area with trees, not Abilene.\nWhat is your command: north or exit?",true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command) {
                case "north":
                    TextEngine.clearScreen();
                    inCanada();
                case "exit":
                    TextEngine.printWithDelays("See ya next time!",false);
                    System.exit(0);
                default:
                    TextEngine.printWithDelays("I'm sorry, I don't understand that command.",true);                  
            }
        }
    }

    private static void inCanada() throws InterruptedException{
        TextEngine.printWithDelays("Eh, you are in Canada. Good luck with all the mooses.\nWhat is your command: north, south or exit?",true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command) {
                case "north":
                    TextEngine.printWithDelays("Eh, you are on the North Pole. Good luck with the cold, hope you brought your coat. brrrrrrr...",false);
                    System.exit(0);
                case "south":
                    TextEngine.clearScreen();
                    inAmerica();
                case "exit":
                    TextEngine.printWithDelays("See ya next time!",false);
                    System.exit(0);
                default:
                    TextEngine.printWithDelays("I'm sorry, I don't understand that command.",false);
            }
        }
    }
}