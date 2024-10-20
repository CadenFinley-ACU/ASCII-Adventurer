
import java.io.Console;
import java.io.IOException;
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

    public static void printWithDelays(String data, boolean buffer) throws InterruptedException { //use buffer is you are accepting input after the text is printed
        if (buffer) {
            data = data + yellowColor + " (press enter to type)" + resetColor;
        }
        if (data.charAt(data.length() - 1) != '\n' && !buffer) {
            data = data + "\n";
        }
        StringBuilder remainingData = new StringBuilder(data);
        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);
            if (String.valueOf(ch).matches("^[a-zA-Z0-9]+$")) {
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
            } else {
                TimeUnit.MILLISECONDS.sleep(0);
            }
            System.out.print(ch);
            remainingData.setCharAt(i, ' ');
        }
    }

    public static void printNoDelay(String data, boolean buffer) { //use buffer is you are accepting input after the text is printed
        if (buffer) {
            data = data + yellowColor + " (press enter to type)" + resetColor;
        }
        if (data.charAt(data.length() - 1) != '\n' && !buffer) {
            data = data + "\n";
        }
        System.out.print(data);
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
