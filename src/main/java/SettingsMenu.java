
import java.io.Console;

/**
 * Settings Menu
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public abstract class SettingsMenu {

    private final static Console console = System.console();
    private static String command;
    @SuppressWarnings("unused")
    private static String ignore;

    public static void start() throws InterruptedException {
        String lastSavedState = TextEngine.speedSetting;
        TextEngine.clearScreen();
        TextEngine.printNoDelay("Settings:", false);
        TextEngine.printNoDelay("This is the settings menu. Here you can change the speed of the text.", false);
        TextEngine.printNoDelay("The current speed is set to: " + TextEngine.speedSetting, false);
        TextEngine.printWithDelays("You can change the speed to: Slow, Normal, Fast, or NoDelay", false);
        TextEngine.printWithDelays("Type 'exit' to leave this menu", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "slow" -> {
                    TextEngine.speedSetting = "Slow";
                    TextEngine.printNoDelay("Slow", false);
                    TextEngine.printWithDelays("This is what SLOW text looks like", false);
                    TextEngine.printNoDelay("Confirm?(yes or no)", true);
                    ignore = console.readLine();
                    command = console.readLine();
                    if (command.toLowerCase().equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.clearScreen();
                        leave();
                    } else {
                        TextEngine.printWithDelays("Settings not saved.", false);
                        TextEngine.speedSetting = lastSavedState;
                        TextEngine.clearScreen();
                        continue;
                    }
                }
                case "normal" -> {
                    TextEngine.speedSetting = "Normal";
                    TextEngine.printNoDelay("Normal", false);
                    TextEngine.printWithDelays("This is what NORMAL text looks like. It is like this by default.", false);
                    TextEngine.printNoDelay("Confirm?(yes or no)", true);
                    ignore = console.readLine();
                    command = console.readLine();
                    if (command.toLowerCase().equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.clearScreen();
                        leave();
                    } else {
                        TextEngine.printWithDelays("Settings not saved.", false);
                        TextEngine.speedSetting = lastSavedState;
                        TextEngine.clearScreen();
                        continue;
                    }
                }
                case "fast" -> {
                    TextEngine.speedSetting = "Fast";
                    TextEngine.printNoDelay("Fast", false);
                    TextEngine.printWithDelays("This is what FAST text looks like", false);
                    TextEngine.printNoDelay("Confirm?(yes or no)", true);
                    ignore = console.readLine();
                    command = console.readLine();
                    if (command.toLowerCase().equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.clearScreen();
                        leave();
                    } else {
                        TextEngine.printWithDelays("Settings not saved.", false);
                        TextEngine.speedSetting = lastSavedState;
                        TextEngine.clearScreen();
                        continue;
                    }
                }
                case "nodelay" -> {
                    TextEngine.speedSetting = "NoDelay";
                    TextEngine.printNoDelay("NoDelay", false);
                    TextEngine.printNoDelay("You in a rush or somethin?", false);
                    TextEngine.printNoDelay("Confirm?(yes or no)", true);
                    ignore = console.readLine();
                    command = console.readLine();
                    if (command.toLowerCase().equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.clearScreen();
                        leave();
                    } else {
                        TextEngine.printWithDelays("Settings not saved.", false);
                        TextEngine.speedSetting = lastSavedState;
                        TextEngine.clearScreen();
                        continue;
                    }
                }
                case "exit" -> {
                    leave();
                    TextEngine.clearScreen();
                }
                default ->
                    TextEngine.printWithDelays("I'm sorry, I don't understand that command.", true);
            }
        }
    }

    private static void leave() throws InterruptedException {
        TextEngine.printWithDelays("Returning to last saved state.", false);
        TextEngine.clearScreen();
        Main.loadSave();
    }
}
