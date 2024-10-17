
import java.io.Console;

/**
 * Settings Menu
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public abstract class SettingsMenu {

    static String resetColor = "\033[0m"; // reset to default color
    static String yellowColor = "\033[1;33m"; // yellow color

    private final static Console console = System.console();
    private static String command;
    @SuppressWarnings("unused")
    private static String ignore;

    public static void start() throws InterruptedException {  //start the settings menu
        String lastSavedState = TextEngine.speedSetting;
        TextEngine.clearScreen();
        TextEngine.printNoDelay("Settings:", false);
        TextEngine.printNoDelay("This is the settings menu. Here you can change the speed of the text.", false);
        TextEngine.printNoDelay("The current speed is set to: " + TextEngine.speedSetting, false);
        TextEngine.printWithDelays("You can change the speed to: " + yellowColor + "Slow" + resetColor + ", " + yellowColor + "Normal" + resetColor + ", " + yellowColor + "Fast" + resetColor + ", or " + yellowColor + "NoDelay" + resetColor, false);
        TextEngine.printWithDelays("Type " + yellowColor + "exit" + resetColor + " to leave this menu", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase().trim()) {
                case "slow" -> {
                    TextEngine.speedSetting = "Slow";
                    TextEngine.printNoDelay("Slow", false);
                    TextEngine.printWithDelays("This is what SLOW text looks like", false);
                    TextEngine.printNoDelay("Confirm? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ")", true);
                    ignore = console.readLine();
                    command = console.readLine();
                    if (command.toLowerCase().trim().equals("yes")) {
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
                    TextEngine.printNoDelay("Confirm? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ")", true);
                    ignore = console.readLine();
                    command = console.readLine();
                    if (command.toLowerCase().trim().equals("yes")) {
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
                    TextEngine.printNoDelay("Confirm? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ")", true);
                    ignore = console.readLine();
                    command = console.readLine();
                    if (command.toLowerCase().trim().equals("yes")) {
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
                    TextEngine.printNoDelay("Confirm? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ")", true);
                    ignore = console.readLine();
                    command = console.readLine();
                    if (command.toLowerCase().trim().equals("yes")) {
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
                case "change name" -> {
                    Player.changeName();
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

    public static void startFromStartMenu() throws InterruptedException {  //start the settings menu
        String lastSavedState = TextEngine.speedSetting;
        TextEngine.clearScreen();
        TextEngine.printNoDelay("Settings:", false);
        TextEngine.printNoDelay("This is the settings menu. Here you can change the speed of the text.", false);
        TextEngine.printNoDelay("The current speed is set to: " + TextEngine.speedSetting, false);
        TextEngine.printWithDelays("You can change the speed to: " + yellowColor + "Slow" + resetColor + ", " + yellowColor + "Normal" + resetColor + ", " + yellowColor + "Fast" + resetColor + ", or " + yellowColor + "NoDelay" + resetColor, false);
        TextEngine.printWithDelays("Type " + yellowColor + "exit" + resetColor + " to leave this menu", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase().trim()) {
                case "slow" -> {
                    TextEngine.speedSetting = "Slow";
                    TextEngine.printNoDelay("Slow", false);
                    TextEngine.printWithDelays("This is what SLOW text looks like", false);
                    TextEngine.printNoDelay("Confirm? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ")", true);
                    ignore = console.readLine();
                    command = console.readLine();
                    if (command.toLowerCase().trim().equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.clearScreen();
                        leaveToStart();
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
                    TextEngine.printNoDelay("Confirm? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ")", true);
                    ignore = console.readLine();
                    command = console.readLine();
                    if (command.toLowerCase().trim().equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.clearScreen();
                        leaveToStart();
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
                    TextEngine.printNoDelay("Confirm? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ")", true);
                    ignore = console.readLine();
                    command = console.readLine();
                    if (command.toLowerCase().trim().equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.clearScreen();
                        leaveToStart();
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
                    TextEngine.printNoDelay("Confirm? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ")", true);
                    ignore = console.readLine();
                    command = console.readLine();
                    if (command.toLowerCase().trim().equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.clearScreen();
                        leaveToStart();
                    } else {
                        TextEngine.printWithDelays("Settings not saved.", false);
                        TextEngine.speedSetting = lastSavedState;
                        TextEngine.clearScreen();
                        continue;
                    }
                }
                case "change name" -> {
                    Player.changeName();
                }
                case "exit" -> {
                    leaveToStart();
                    TextEngine.clearScreen();
                }
                default ->
                    TextEngine.printWithDelays("I'm sorry, I don't understand that command.", true);
            }
        }
    }

    private static void leave() throws InterruptedException { //leave the settings menu
        TextEngine.printWithDelays("Returning to last saved state.", false);
        TextEngine.clearScreen();
        Main.loadSave();
    }

    private static void leaveToStart() throws InterruptedException { //leave the settings menu
        TextEngine.printWithDelays("Returning to last saved state.", false);
        TextEngine.clearScreen();
        Main.startMenu();
    }
}
