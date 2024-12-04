
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * ASCIIADVENTURER Caden Finley Albert Tucker Grijesh Shrestha
 *
 * The TextEngine class provides methods for printing text with delays, clearing
 * the console screen, and waiting for user input. It also includes methods for
 * checking the validity of input commands, parsing commands, and matching
 * commands against a list of possible commands.
 *
 * @author ASCIIADVENTURERS
 * @version 1.0
 */
public abstract class TextEngine {

    public static String speedSetting = "Normal";
    public final static Console console = System.console();
    public static String yellowColor = "\033[1;33m";
    public static String resetColor = "\033[0m";

    public static int MAX_LINE_WIDTH = 30; // Define the maximum line width

    /**
     * Sets the terminal width by querying the terminal for its current width.
     * On Windows, it uses the "mode con" command, and on Unix-like systems, it
     * uses the "tput cols" command. If the terminal width cannot be determined,
     * a default width of 80 is used.
     */
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
        if (speedSetting.equals("NoDelay") || GameEngine.TESTING) {
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
                        case "Normal" -> {
                            TimeUnit.MILLISECONDS.sleep(20);
                        }
                        default -> {
                            //do nothing
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

    /**
     * Prints the given data with delays between characters based on the speed
     * setting. If buffering is enabled, it adds a prompt to press enter to type
     * after the text is printed.
     *
     * @param data The data to be printed.
     * @param buffer If true, adds a prompt to press enter to type after the
     * text is printed.
     */
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

    /**
     * Clears the console screen. This method determines the operating system
     * and clears the screen accordingly. For Windows, it uses a combination of
     * ANSI escape codes and the "clear" command. For other operating systems,
     * it uses only ANSI escape codes.
     */
    public static void clearScreen() { //clears the screen
        String OS_Name = GameEngine.getOS_NAME();
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

    /**
     * Adds a pause and waits for the user to press Enter to continue. If the
     * application is not in testing mode, it will print a message prompting the
     * user to press Enter and then wait for the Enter key to be pressed.
     */
    public static void enterToNext() { //adds a pause and waits for enter
        if (GameEngine.TESTING) {
            return;
        }
        printNoDelay(yellowColor + "Press Enter to continue" + resetColor, false);
        console.readLine();
    }

    /**
     * Checks if the input command is valid.
     *
     * @param command the input command to be checked
     * @return {@code true} if the command is not null, not empty, and not an
     * empty string; {@code false} otherwise
     */
    public static Boolean checkValidInput(String command) { //checks for valid input command
        return command != null && !command.isEmpty() && !"".equals(command);
    }

    /**
     * Parses the input command and matches it against a list of possible
     * commands. If the command is found in the list of illegal commands, it
     * returns the command as is. Otherwise, it tries to find the best match
     * from the possible commands.
     *
     * @param command the input command to be parsed
     * @param possibleCommands an array of possible commands to match against
     * @return the matched command if found, otherwise the original command
     */
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
        return (maxMatchLength > 0 && has(possibleCommands, matchedCommand)) ? matchedCommand.toLowerCase() : command.toLowerCase();
    }

    /**
     * Calculates the length of the matching prefix between two strings.
     *
     * @param command the first string to compare
     * @param possibleCommand the second string to compare
     * @return the length of the matching prefix
     */
    public static int getMatchLength(String command, String possibleCommand) {
        if (command == null || possibleCommand == null) {
            return 0;
        }
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

    /**
     * Checks if a given command is present in an array of possible commands.
     *
     * @param possibleCommands an array of possible commands
     * @param matchedCommand the command to check for
     * @return true if the matchedCommand is found in possibleCommands, false
     * otherwise
     */
    public static boolean has(String[] possibleCommands, String matchedCommand) {
        for (String possibleCommand : possibleCommands) {
            if (possibleCommand.equals(matchedCommand)) {
                return true;
            }
        }
        return false;
    }
}
