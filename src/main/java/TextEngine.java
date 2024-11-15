
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * Text Engine
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 *
 * Written by Caden Finley
 *
 */
public abstract class TextEngine {

    public static String speedSetting = "Normal";
    public final static Console console = System.console();
    public static String yellowColor = "\033[1;33m";
    public static String resetColor = "\033[0m";

    public static int MAX_LINE_WIDTH = 30; // Define the maximum line width

    public static void setWidth() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder processBuilder;
            if (os.contains("win")) {
                processBuilder = new ProcessBuilder("cmd", "/c", "mode con");
            } else {
                processBuilder = new ProcessBuilder("sh", "-c", "tput cols");
            }
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            if (os.contains("win")) {
                while ((line = reader.readLine()) != null) {
                    if (line.contains("Columns")) {
                        String[] parts = line.split(":");
                        if (parts.length > 1) {
                            line = parts[1].trim();
                            break;
                        }
                    }
                }
            } else {
                line = reader.readLine();
            }
            if (line != null && !line.isEmpty()) {
                System.out.println("Terminal width: " + line);
                MAX_LINE_WIDTH = Integer.parseInt(line);
            } else {
                System.out.println("Could not get the terminal width, using default value");
                MAX_LINE_WIDTH = 80; // Default width if tput/mode con fails
            }
        } catch (IOException e) {
            System.out.println("Could not get the terminal width, using default value");
            MAX_LINE_WIDTH = 80; // Default width if an exception occurs
        }
    }

    public static void printWithDelays(String data, boolean buffer) throws InterruptedException {
        // Use buffer if you are accepting input after the text is printed
        if (speedSetting.equals("NoDelay")) {
            printNoDelay(data, buffer);
            return;
        }
        if (buffer) {
            data = data + yellowColor + " (press enter to type)" + resetColor;
        }
        int currentLineWidth = 0; // Initialize the current line width
        String[] words = data.split(" "); // Split the data into words
        StringBuilder remainingChars = new StringBuilder(data); // Initialize remaining characters
        for (String word : words) {
            if (buffer) {
                if ((currentLineWidth + word.length() >= MAX_LINE_WIDTH + 30) && currentLineWidth != 0) {
                    System.out.print('\n');
                    currentLineWidth = 0;
                }
            } else {
                if ((currentLineWidth + word.length() >= MAX_LINE_WIDTH) && currentLineWidth != 0) {
                    System.out.print('\n');
                    currentLineWidth = 0;
                }
            }
            if (word.contains("\n")) {
                currentLineWidth = 0;
            }
            for (char ch : word.toCharArray()) {
                if (String.valueOf(ch).matches("^[a-zA-Z0-9]+$") && !String.valueOf(ch).matches(" ")) {
                    switch (speedSetting) {
                        case "Slow" ->
                            TimeUnit.MILLISECONDS.sleep(30);
                        case "Fast" ->
                            TimeUnit.MILLISECONDS.sleep(10);
                        case "NoDelay" -> {
                        }
                        // Do nothing
                        default -> {
                            TimeUnit.MILLISECONDS.sleep(20);
                        }
                    }
                }
                System.out.print(ch);
                currentLineWidth++;
                remainingChars.deleteCharAt(0); // Remove the printed character from remainingChars
                if (ch == '\n') {
                    currentLineWidth = 0;
                }
            }
            if (currentLineWidth > 0) {
                System.out.print(' ');
                currentLineWidth++;
                //remainingChars.deleteCharAt(0); // Remove the printed space from remainingChars
            }
        }
        if (data.charAt(data.length() - 1) != '\n' && !buffer) {
            System.out.print('\n');
        }
        if (buffer) {
            console.readLine();
        }
    }

    public static void printNoDelay(String data, boolean buffer) { //use buffer is you are accepting input after the text is printed
        if (buffer) {
            data = data + yellowColor + " (press enter to type)" + resetColor;
        }
        int currentLineWidth = 0; // Initialize the current line width
        String[] words = data.split(" "); // Split the data into words
        for (String word : words) {
            if (buffer) {
                if ((currentLineWidth + word.length() >= MAX_LINE_WIDTH + 30) && currentLineWidth != 0) {
                    System.out.print('\n');
                    currentLineWidth = 0;
                }
            } else {
                if ((currentLineWidth + word.length() >= MAX_LINE_WIDTH) && currentLineWidth != 0) {
                    System.out.print('\n');
                    currentLineWidth = 0;
                }
            }
            if (word.contains("\n")) {
                currentLineWidth = 0;
            }
            for (char ch : word.toCharArray()) {
                System.out.print(ch);
                currentLineWidth++;
                if (ch == '\n') {
                    currentLineWidth = 0;
                }
            }
            System.out.print(' '); // Print the space after the word
            currentLineWidth++;
        }
        if (data.charAt(data.length() - 1) != '\n' && !buffer) {
            System.out.print('\n');
        }
        if (buffer) {
            console.readLine();
        }
    }

    public static void clearScreen() { //clears the screen
        String OS_Name = Main.getOS_NAME();
        try {
            if (OS_Name.contains("Windows")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                Runtime.getRuntime().exec(new String[]{"clear"});
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final IOException e) {
            //System.out.println("Error, No OS obtained: " + e.getMessage());
        }
    }

    public static void enterToNext() { //adds a pause and waits for enter
        printNoDelay(yellowColor + "Press Enter to continue" + resetColor, false);
        console.readLine();
    }

    public static Boolean checkValidInput(String command) { //checks for valid input command
        return command != null && !command.isEmpty() && !"".equals(command);
    }

    public static String parseCommand(String command, String possibleCommands[]) {
        String[] illegalCommands = {"exit", "quit", "stats", "map", "inventory", "help", "save", "settings", "take it", "leave it", "open it", "leave"};
        String matchedCommand = command;
        int maxMatchLength = 0;
        for (String illegalCommand : illegalCommands) {
            if (command.equals(illegalCommand)) {
                return command;
            }
        }
        for (String possibleCommand : possibleCommands) {
            if (command.equals(possibleCommand)) {
                return command;
            }
            int matchLength = getMatchLength(command, possibleCommand);
            if (matchLength > maxMatchLength) {
                maxMatchLength = matchLength;
                matchedCommand = possibleCommand;
            }
        }
        if (!matchedCommand.equals(command)) {
            System.out.println("Matched command: " + matchedCommand);
        }
        return (maxMatchLength > 0 && has(possibleCommands, matchedCommand)) ? matchedCommand : command;
    }

    private static int getMatchLength(String command, String possibleCommand) {
        int length = Math.min(command.length(), possibleCommand.length());
        int matchLength = 0;
        for (int i = 0; i < length; i++) {
            if (command.charAt(i) == possibleCommand.charAt(i)) {
                matchLength++;
            } else {
                break;
            }
        }
        return matchLength;
    }

    private static boolean has(String[] possibleCommands, String matchedCommand) {
        for (String possibleCommand : possibleCommands) {
            if (possibleCommand.equals(matchedCommand)) {
                return true;
            }
        }
        return false;
    }
}
