
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
 */
public abstract class TextEngine {

    public static String speedSetting = "Normal";
    public final static Console console = System.console();
    public static String yellowColor = "\033[1;33m";
    public static String resetColor = "\033[0m";

    public static int MAX_LINE_WIDTH = 30; // Define the maximum line width

    public static int getTerminalWidth() {
        ProcessBuilder processBuilder = new ProcessBuilder("sh", "-c", "stty size < /dev/tty");
        processBuilder.redirectErrorStream(true);
        Process process;
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            return -1;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                String[] dimensions = line.split(" ");
                if (dimensions.length == 2) {
                    return Integer.parseInt(dimensions[1]) + 10;
                }
            }
        } catch (Exception e) {
            return -1;
        }
        return -1; // Return -1 if there is an error
    }

    public static void setWidth() {
        int width = getTerminalWidth();
        if (width != -1) {
            MAX_LINE_WIDTH = width;
        }
    }

    public static void printWithDelays(String data, boolean buffer) throws InterruptedException { //use buffer is you are accepting input after the text is printed
        if (speedSetting.equals("NoDelay")) {
            printNoDelay(data, buffer);
            return;
        }
        setWidth();
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
            for (char ch : word.toCharArray()) {
                if (String.valueOf(ch).matches("^[a-zA-Z0-9]+$") && !String.valueOf(ch).matches(" ")) {
                    switch (speedSetting) {
                        case "Slow" ->
                            TimeUnit.MILLISECONDS.sleep(30);
                        case "Fast" ->
                            TimeUnit.MILLISECONDS.sleep(10);
                        case "NoDelay" ->
                            TimeUnit.MILLISECONDS.sleep(0);
                        default -> {
                            TimeUnit.MILLISECONDS.sleep(20);
                        }
                    }
                }
                System.out.print(ch);
                currentLineWidth++;
            }
            if (currentLineWidth > 0) {
                System.out.print(' ');
                currentLineWidth++;
            }
        }
        if (data.charAt(data.length() - 1) != '\n' && !buffer) {
            System.out.print('\n');
        }
    }

    public static void printNoDelay(String data, boolean buffer) { //use buffer is you are accepting input after the text is printed
        setWidth();
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
            for (char ch : word.toCharArray()) {
                System.out.print(ch);
                currentLineWidth++;
            }
            System.out.print(' '); // Print the space after the word
            currentLineWidth++;
        }
        if (data.charAt(data.length() - 1) != '\n' && !buffer) {
            System.out.print('\n');
        }
    }

    public static void clearScreen() throws InterruptedException { //clears the screen
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

    public static void enterToNext() throws InterruptedException { //adds a pause and waits for enter
        printNoDelay(yellowColor + "Press Enter to continue" + resetColor, false);
        console.readLine();
    }

    public static Boolean checkValidInput(String command) { //checks for valid input command
        return command != null && !command.isEmpty() && !"".equals(command);
    }
}
