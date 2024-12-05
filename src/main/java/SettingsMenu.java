
import java.io.Console;
import java.util.concurrent.TimeoutException;

/**
 * ASCIIADVENTURER Caden Finley Albert Tucker Grijesh Shrestha
 *
 * This class represents a settings menu for the ASCIIADVENTURER game. It allows
 * users to adjust text speed, enable/disable AI-generated prompts, change their
 * name, and exit the menu.
 *
 * @author ASCIIADVENTURERS
 * @version 1.0
 */
public abstract class SettingsMenu {

    static String resetColor = "\033[0m"; // reset to default color
    static String yellowColor = "\033[1;33m"; // yellow color

    private final static Console console = System.console();
    private static String command;

    /**
     * The `start()` function in Java presents a settings menu where users can
     * adjust text speed, enable/disable AI-generated prompts, change their
     * name, and exit the menu.
     */
    public static void start() throws InterruptedException {  //start the settings menu
        String lastSavedState = TextEngine.speedSetting;
        TextEngine.clearScreen();
        TextEngine.printNoDelay("Settings:", false);
        TextEngine.printNoDelay("This is the settings menu. Here you can change the speed of the text.", false);
        TextEngine.printNoDelay("The current speed is set to: " + TextEngine.speedSetting, false);
        TextEngine.printWithDelays("You can change the speed to: " + yellowColor + "Slow" + resetColor + ", " + yellowColor + "Normal" + resetColor + ", " + yellowColor + "Fast" + resetColor + ", or " + yellowColor + "NoDelay" + resetColor, false);
        TextEngine.printWithDelays("You can also type: " + yellowColor + "AI " + resetColor + "to enable or disable AI generated prompts in game", false);
        TextEngine.printWithDelays("You can also type: " + yellowColor + "change name " + resetColor + "to change your name", false);
        TextEngine.printWithDelays("Type " + yellowColor + "exit" + resetColor + " to leave this menu", true);
        while (true) {
            command = console.readLine();
            switch (command.toLowerCase().trim()) {
                case "slow" -> {
                    TextEngine.speedSetting = "Slow";
                    TextEngine.printNoDelay("Slow", false);
                    TextEngine.printWithDelays("This is what SLOW text looks like", false);
                    TextEngine.printNoDelay("Confirm? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ")", true);
                    command = console.readLine();
                    if (command.toLowerCase().trim().equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.enterToNext();
                        TextEngine.clearScreen();
                        leave();
                    } else {
                        TextEngine.printWithDelays("Settings not saved.", false);
                        TextEngine.enterToNext();
                        TextEngine.speedSetting = lastSavedState;
                        TextEngine.clearScreen();
                        start();
                    }
                }
                case "normal" -> {
                    TextEngine.speedSetting = "Normal";
                    TextEngine.printNoDelay("Normal", false);
                    TextEngine.printWithDelays("This is what NORMAL text looks like. It is like this by default.", false);
                    TextEngine.printNoDelay("Confirm? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ")", true);
                    command = console.readLine();
                    if (command.toLowerCase().trim().equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.enterToNext();
                        TextEngine.clearScreen();
                        leave();
                    } else {
                        TextEngine.printWithDelays("Settings not saved.", false);
                        TextEngine.enterToNext();
                        TextEngine.speedSetting = lastSavedState;
                        TextEngine.clearScreen();
                        start();
                    }
                }
                case "fast" -> {
                    TextEngine.speedSetting = "Fast";
                    TextEngine.printNoDelay("Fast", false);
                    TextEngine.printWithDelays("This is what FAST text looks like", false);
                    TextEngine.printNoDelay("Confirm? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ")", true);
                    command = console.readLine();
                    if (command.toLowerCase().trim().equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.enterToNext();
                        TextEngine.clearScreen();
                        leave();
                    } else {
                        TextEngine.printWithDelays("Settings not saved.", false);
                        TextEngine.enterToNext();
                        TextEngine.speedSetting = lastSavedState;
                        TextEngine.clearScreen();
                        start();
                    }
                }
                case "nodelay" -> {
                    TextEngine.speedSetting = "NoDelay";
                    TextEngine.printNoDelay("NoDelay", false);
                    TextEngine.printNoDelay("You in a rush or somethin?", false);
                    TextEngine.printNoDelay("Confirm? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ")", true);
                    command = console.readLine();
                    if (command.toLowerCase().trim().equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.enterToNext();
                        TextEngine.clearScreen();
                        leave();
                    } else {
                        TextEngine.printWithDelays("Settings not saved.", false);
                        TextEngine.enterToNext();
                        TextEngine.speedSetting = lastSavedState;
                        TextEngine.clearScreen();
                        start();
                    }
                }
                case "change name" -> {
                    Player.changeName();
                }
                case "ai" -> {
                    if (PromptEngine.aiGenerationEnabled) {
                        TextEngine.printNoDelay("AI generation is already enabled. Would you like to disable it? (yes or no)", true);
                        command = console.readLine();
                        switch (command) {
                            case "yes" -> {
                                PromptEngine.aiGenerationEnabled = false;
                                TextEngine.printWithDelays("AI generation disabled.", false);
                                TextEngine.enterToNext();
                                TextEngine.clearScreen();
                                leave();
                            }
                            default -> {
                                TextEngine.printWithDelays("AI generation remains enabled.", false);
                                TextEngine.enterToNext();
                                TextEngine.clearScreen();
                                leave();
                            }
                        }
                    } else {
                        TextEngine.printWithDelays("AI generation is disabled. Would you like to enable it? (yes or no)", true);
                        command = console.readLine();
                        switch (command) {
                            case "yes" -> {
                                try {
                                    if (!PromptEngine.testAPIKey(PromptEngine.USER_API_KEY)) {
                                        TextEngine.printWithDelays("AI generation could not be enabled. Please check your API key.", false);
                                        TextEngine.enterToNext();
                                        TextEngine.clearScreen();
                                        leave();
                                    } else {
                                        PromptEngine.aiGenerationEnabled = true;
                                        TextEngine.printWithDelays("AI generation enabled.", false);
                                        TextEngine.enterToNext();
                                        TextEngine.clearScreen();
                                        leave();
                                    }
                                } catch (TimeoutException e) {
                                    leave();
                                }
                            }
                            default -> {
                                TextEngine.printWithDelays("AI generation remains disabled.", false);
                                TextEngine.enterToNext();
                                TextEngine.clearScreen();
                                leave();
                            }
                        }
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

    /**
     * The function `startFromStartMenu` in Java allows the user to access and
     * modify settings such as text speed, AI prompts, and name changes through
     * a console interface.
     */
    public static void startFromStartMenu() throws InterruptedException {  //start the settings menu
        String lastSavedState = TextEngine.speedSetting;
        TextEngine.clearScreen();
        TextEngine.printNoDelay("Settings:", false);
        TextEngine.printNoDelay("This is the settings menu. Here you can change the speed of the text.", false);
        TextEngine.printNoDelay("The current speed is set to: " + TextEngine.speedSetting, false);
        TextEngine.printWithDelays("You can change the speed to: " + yellowColor + "Slow" + resetColor + ", " + yellowColor + "Normal" + resetColor + ", " + yellowColor + "Fast" + resetColor + ", or " + yellowColor + "NoDelay" + resetColor, false);
        TextEngine.printWithDelays("You can also type: " + yellowColor + "AI " + resetColor + "to enable or disable AI generated prompts in game", false);
        TextEngine.printWithDelays("You can also type: " + yellowColor + "change name " + resetColor + "to change your name", false);
        TextEngine.printWithDelays("Type " + yellowColor + "exit" + resetColor + " to leave this menu", true);
        while (true) {
            command = console.readLine();
            switch (command.toLowerCase().trim()) {
                case "slow" -> {
                    TextEngine.speedSetting = "Slow";
                    TextEngine.printNoDelay("Slow", false);
                    TextEngine.printWithDelays("This is what SLOW text looks like", false);
                    TextEngine.printNoDelay("Confirm? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ")", true);
                    command = console.readLine();
                    if (command.toLowerCase().trim().equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.enterToNext();
                        TextEngine.clearScreen();
                        leaveToStart();
                    } else {
                        TextEngine.printWithDelays("Settings not saved.", false);
                        TextEngine.enterToNext();
                        TextEngine.speedSetting = lastSavedState;
                        TextEngine.clearScreen();
                        startFromStartMenu();
                    }
                }
                case "normal" -> {
                    TextEngine.speedSetting = "Normal";
                    TextEngine.printNoDelay("Normal", false);
                    TextEngine.printWithDelays("This is what NORMAL text looks like. It is like this by default.", false);
                    TextEngine.printNoDelay("Confirm? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ")", true);
                    command = console.readLine();
                    if (command.toLowerCase().trim().equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.enterToNext();
                        TextEngine.clearScreen();
                        leaveToStart();
                    } else {
                        TextEngine.printWithDelays("Settings not saved.", false);
                        TextEngine.enterToNext();
                        TextEngine.speedSetting = lastSavedState;
                        TextEngine.clearScreen();
                        startFromStartMenu();
                    }
                }
                case "fast" -> {
                    TextEngine.speedSetting = "Fast";
                    TextEngine.printNoDelay("Fast", false);
                    TextEngine.printWithDelays("This is what FAST text looks like", false);
                    TextEngine.printNoDelay("Confirm? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ")", true);
                    command = console.readLine();
                    if (command.toLowerCase().trim().equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.enterToNext();
                        TextEngine.clearScreen();
                        leaveToStart();
                    } else {
                        TextEngine.printWithDelays("Settings not saved.", false);
                        TextEngine.enterToNext();
                        TextEngine.speedSetting = lastSavedState;
                        TextEngine.clearScreen();
                        startFromStartMenu();
                    }
                }
                case "nodelay" -> {
                    TextEngine.speedSetting = "NoDelay";
                    TextEngine.printNoDelay("NoDelay", false);
                    TextEngine.printNoDelay("You in a rush or somethin?", false);
                    TextEngine.printNoDelay("Confirm? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ")", true);
                    command = console.readLine();
                    if (command.toLowerCase().trim().equals("yes")) {
                        TextEngine.printWithDelays("Settings saved.", false);
                        TextEngine.enterToNext();
                        TextEngine.clearScreen();
                        leaveToStart();
                    } else {
                        TextEngine.printWithDelays("Settings not saved.", false);
                        TextEngine.enterToNext();
                        TextEngine.speedSetting = lastSavedState;
                        TextEngine.clearScreen();
                        startFromStartMenu();
                    }
                }
                case "change name" -> {
                    Player.changeName();
                }
                case "ai" -> {
                    if (PromptEngine.aiGenerationEnabled) {
                        TextEngine.printNoDelay("AI generation is already enabled. Would you like to disable it? (yes or no)", true);
                        command = console.readLine();
                        switch (command) {
                            case "yes" -> {
                                PromptEngine.aiGenerationEnabled = false;
                                TextEngine.printWithDelays("AI generation disabled.", false);
                                TextEngine.enterToNext();
                                TextEngine.clearScreen();
                                leaveToStart();
                            }
                            default -> {
                                TextEngine.printWithDelays("AI generation remains enabled.", false);
                                TextEngine.enterToNext();
                                TextEngine.clearScreen();
                                leaveToStart();
                            }
                        }
                    } else {
                        TextEngine.printWithDelays("AI generation is disabled. Would you like to enable it? (yes or no)", true);
                        command = console.readLine();
                        switch (command) {
                            case "yes" -> {
                                try {
                                    if (!PromptEngine.testAPIKey(PromptEngine.USER_API_KEY)) {
                                        TextEngine.printWithDelays("AI generation could not be enabled. Please check your API key.", false);
                                        TextEngine.enterToNext();
                                        TextEngine.clearScreen();
                                        leaveToStart();
                                    } else {
                                        PromptEngine.aiGenerationEnabled = true;
                                        TextEngine.printWithDelays("AI generation enabled.", false);
                                        TextEngine.enterToNext();
                                        TextEngine.clearScreen();
                                        leaveToStart();
                                    }
                                } catch (TimeoutException e) {
                                    leaveToStart();
                                }
                            }
                            default -> {
                                TextEngine.printWithDelays("AI generation remains disabled.", false);
                                TextEngine.enterToNext();
                                TextEngine.clearScreen();
                                leaveToStart();
                            }
                        }
                    }
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

    /**
     * The `leave()` function returns to the last saved state after displaying a
     * message and clearing the screen.
     */
    private static void leave() throws InterruptedException { //leave the settings menu
        TextEngine.printWithDelays("Returning to last saved state.", false);
        TextEngine.clearScreen();
        GameEngine.loadSave();
    }

    /**
     * The function `leaveToStart` returns to the start menu after displaying a
     * message and clearing the screen.
     */
    private static void leaveToStart() throws InterruptedException { //leave the settings menu
        TextEngine.printWithDelays("Returning to last saved state.", false);
        TextEngine.clearScreen();
        GameEngine.startMenu();
    }
}
