
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
        TextEngine.printWithDelays("You can also type: " + yellowColor + "AI " + resetColor + "to enable or disable AI generated prompts in game", false);
        TextEngine.printWithDelays("(This is still experimental and requires you to have your own API key and an internet connection...)", false);
        TextEngine.printWithDelays("You can also type: " + yellowColor + "change name " + resetColor + "to change your name", false);
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
                    ignore = console.readLine();
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
                    ignore = console.readLine();
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
                    ignore = console.readLine();
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
                        TextEngine.printNoDelay("AI generation is already enabled. Would you like to disable it? (yes, no, or more settings)", true);
                        ignore = console.readLine();
                        command = console.readLine();
                        switch (command) {
                            case "yes" -> {
                                PromptEngine.aiGenerationEnabled = false;
                                PromptEngine.userAPIKey = null;
                                TextEngine.printWithDelays("AI generation disabled.", false);
                                TextEngine.enterToNext();
                                TextEngine.clearScreen();
                                leave();
                            }
                            case "no" -> {
                                TextEngine.printWithDelays("AI generation remains enabled.", false);
                                TextEngine.enterToNext();
                                TextEngine.clearScreen();
                                leave();
                            }
                            case "more settings" -> {
                                TextEngine.printNoDelay("Would you like to 'change' your API key or 'change advanced' settings or exit?", true);
                                ignore = console.readLine();
                                command = console.readLine();
                                switch (command) {
                                    case "change" -> {
                                        TextEngine.printNoDelay("Please enter your OpenAI API key:", true);
                                        ignore = console.readLine();
                                        command = console.readLine();
                                        if (PromptEngine.testAPIKey(command)) {
                                            PromptEngine.userAPIKey = command;
                                            TextEngine.printWithDelays("API key accepted. AI generation enabled.", false);
                                            TextEngine.enterToNext();
                                            TextEngine.clearScreen();
                                            leave();
                                        } else {
                                            TextEngine.printWithDelays("API key not accepted.", false);
                                            TextEngine.enterToNext();
                                            TextEngine.clearScreen();
                                            start();
                                        }
                                    }
                                    case "change advanced" -> {
                                        TextEngine.printNoDelay("This is the advanced settings menu.", false);
                                        TextEngine.printWithDelays("You can change the 'prompt length or exit", true);
                                        ignore = console.readLine();
                                        command = console.readLine();
                                        switch (command) {
                                            case "prompt length" -> {
                                                TextEngine.printNoDelay("Please enter the prompt length you would like to set: (short, medium, long, or exit)", true);
                                                ignore = console.readLine();
                                                command = console.readLine();
                                                switch (command) {
                                                    case "short" -> {
                                                        PromptEngine.setPromptLength("short");
                                                        TextEngine.printWithDelays("Prompt length set to short.", false);
                                                        TextEngine.enterToNext();
                                                        TextEngine.clearScreen();
                                                        leave();
                                                    }
                                                    case "medium" -> {
                                                        PromptEngine.setPromptLength("medium");
                                                        TextEngine.printWithDelays("Prompt length set to medium.", false);
                                                        TextEngine.enterToNext();
                                                        TextEngine.clearScreen();
                                                        leave();
                                                    }
                                                    case "long" -> {
                                                        PromptEngine.setPromptLength("long");
                                                        TextEngine.printWithDelays("Prompt length set to long.", false);
                                                        TextEngine.enterToNext();
                                                        TextEngine.clearScreen();
                                                        leave();
                                                    }
                                                    case "exit" -> {
                                                        leave();
                                                        TextEngine.clearScreen();
                                                    }
                                                    default ->
                                                        TextEngine.printWithDelays("I'm sorry, I don't understand that command.", true);
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
                                    case "exit" -> {
                                        leave();
                                        TextEngine.clearScreen();
                                    }
                                    default ->
                                        TextEngine.printWithDelays("I'm sorry, I don't understand that command.", true);
                                }
                            }
                        }
                    } else {
                        TextEngine.printNoDelay("(This feature is still experimental)", false);
                        TextEngine.printNoDelay("Please enter your OpenAI API key:", true);
                        ignore = console.readLine();
                        command = console.readLine();
                        if (PromptEngine.testAPIKey(command)) {
                            PromptEngine.userAPIKey = command;
                            TextEngine.printWithDelays("API key accepted. AI generation enabled.", false);
                            TextEngine.enterToNext();
                            TextEngine.clearScreen();
                            leave();
                        } else {
                            TextEngine.printWithDelays("API key not accepted.", false);
                            TextEngine.enterToNext();
                            TextEngine.clearScreen();
                            start();
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

    public static void startFromStartMenu() throws InterruptedException {  //start the settings menu
        String lastSavedState = TextEngine.speedSetting;
        TextEngine.clearScreen();
        TextEngine.printNoDelay("Settings:", false);
        TextEngine.printNoDelay("This is the settings menu. Here you can change the speed of the text.", false);
        TextEngine.printNoDelay("The current speed is set to: " + TextEngine.speedSetting, false);
        TextEngine.printWithDelays("You can change the speed to: " + yellowColor + "Slow" + resetColor + ", " + yellowColor + "Normal" + resetColor + ", " + yellowColor + "Fast" + resetColor + ", or " + yellowColor + "NoDelay" + resetColor, false);
        TextEngine.printWithDelays("You can also type: " + yellowColor + "AI " + resetColor + "to enable or disable AI generated prompts in game", false);
        TextEngine.printWithDelays("(This is still experimental and requires you to have your own API key and an internet connection...)", false);
        TextEngine.printWithDelays("You can also type: " + yellowColor + "change name " + resetColor + "to change your name", false);
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
                    ignore = console.readLine();
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
                    ignore = console.readLine();
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
                    ignore = console.readLine();
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
                        TextEngine.printNoDelay("AI generation is already enabled. Would you like to disable it? (yes, no, or more settings)", true);
                        ignore = console.readLine();
                        command = console.readLine();
                        switch (command) {
                            case "yes" -> {
                                PromptEngine.aiGenerationEnabled = false;
                                PromptEngine.userAPIKey = null;
                                TextEngine.printWithDelays("AI generation disabled.", false);
                                TextEngine.enterToNext();
                                TextEngine.clearScreen();
                                leaveToStart();
                            }
                            case "no" -> {
                                TextEngine.printWithDelays("AI generation remains enabled.", false);
                                TextEngine.enterToNext();
                                TextEngine.clearScreen();
                                leaveToStart();
                            }
                            case "more settings" -> {
                                TextEngine.printNoDelay("Would you like to 'change' your API key or 'change advanced' settings or exit?", true);
                                ignore = console.readLine();
                                command = console.readLine();
                                switch (command) {
                                    case "change" -> {
                                        TextEngine.printNoDelay("Please enter your OpenAI API key:", true);
                                        ignore = console.readLine();
                                        command = console.readLine();
                                        if (PromptEngine.testAPIKey(command)) {
                                            PromptEngine.userAPIKey = command;
                                            TextEngine.printWithDelays("API key accepted. AI generation enabled.", false);
                                            TextEngine.enterToNext();
                                            TextEngine.clearScreen();
                                            leaveToStart();
                                        } else {
                                            TextEngine.printWithDelays("API key not accepted.", false);
                                            TextEngine.enterToNext();
                                            TextEngine.clearScreen();
                                            startFromStartMenu();
                                        }
                                    }
                                    case "change advanced" -> {
                                        TextEngine.printNoDelay("This is the advanced settings menu.", false);
                                        TextEngine.printWithDelays("You can change the 'prompt length or exit", true);
                                        ignore = console.readLine();
                                        command = console.readLine();
                                        switch (command) {
                                            case "prompt length" -> {
                                                TextEngine.printNoDelay("Please enter the prompt length you would like to set: (short, medium, long, or exit)", true);
                                                ignore = console.readLine();
                                                command = console.readLine();
                                                switch (command) {
                                                    case "short" -> {
                                                        PromptEngine.setPromptLength("short");
                                                        TextEngine.printWithDelays("Prompt length set to short.", false);
                                                        TextEngine.enterToNext();
                                                        TextEngine.clearScreen();
                                                        leaveToStart();
                                                    }
                                                    case "medium" -> {
                                                        PromptEngine.setPromptLength("medium");
                                                        TextEngine.printWithDelays("Prompt length set to medium.", false);
                                                        TextEngine.enterToNext();
                                                        TextEngine.clearScreen();
                                                        leaveToStart();
                                                    }
                                                    case "long" -> {
                                                        PromptEngine.setPromptLength("long");
                                                        TextEngine.printWithDelays("Prompt length set to long.", false);
                                                        TextEngine.enterToNext();
                                                        TextEngine.clearScreen();
                                                        leaveToStart();
                                                    }
                                                    case "exit" -> {
                                                        leaveToStart();
                                                        TextEngine.clearScreen();
                                                    }
                                                    default ->
                                                        TextEngine.printWithDelays("I'm sorry, I don't understand that command.", true);
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
                                    case "exit" -> {
                                        leaveToStart();
                                        TextEngine.clearScreen();
                                    }
                                    default ->
                                        TextEngine.printWithDelays("I'm sorry, I don't understand that command.", true);
                                }
                            }
                        }
                    } else {
                        TextEngine.printNoDelay("(This feature is still experimental)", false);
                        TextEngine.printNoDelay("Please enter your OpenAI API key:", true);
                        ignore = console.readLine();
                        command = console.readLine();
                        if (PromptEngine.testAPIKey(command)) {
                            PromptEngine.userAPIKey = command;
                            TextEngine.printWithDelays("API key accepted. AI generation enabled.", false);
                            TextEngine.enterToNext();
                            TextEngine.clearScreen();
                            leaveToStart();
                        } else {
                            TextEngine.printWithDelays("API key not accepted.", false);
                            TextEngine.enterToNext();
                            TextEngine.clearScreen();
                            startFromStartMenu();
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