
import java.io.Console;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Template SE374 F24 Final Project Caden Finley Albert Tucker Grijesh Shrestha
 */
//todo make inventory and health management
public class Player {

    private static int health;
    private static Dictionary<String, Integer> inventory;
    private static String name;
    private final static Console console = System.console();
    private static String command;
    private static String ignore;

    public static void playerStart() throws InterruptedException {
        health = 100;
        inventory = new Hashtable<>();
        playerCreate();
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Player.name = name;
    }

    public static int getHealth() {
        return health;
    }

    public static void changeHealth(int change) {
        health += change;
    }

    public static void getInventory() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void putItem(String item, int amount) throws InterruptedException {
        if (inventory.get(item) != null) {
            inventory.put(item, inventory.get(item) + amount);
        } else {
            inventory.put(item, amount);
        }
        TextEngine.printWithDelays("You have picked up " + amount + " " + item, false);
        TextEngine.printWithDelays("Press Enter to continue", false);
        ignore = console.readLine();
    }

    private static void playerCreate() throws InterruptedException {
        TextEngine.printWithDelays("Welcome to the game! What is your name hero?", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if (command != null && !command.isEmpty()) {
                Player.setName(command);
                break;
            } else {
                TextEngine.printWithDelays("Please enter a name.", true);
            }
        }
        TextEngine.clearScreen();
        TextEngine.printWithDelays("Welcome " + Player.getName() + "!", false);
        TextEngine.printWithDelays("There are a few quick things to know.\nat any point you can type settings to change the text speed.", false);
        TextEngine.printWithDelays("You can also type exit to leave the game at any time.", false);
        TextEngine.printWithDelays("You can type inventory to see your inventory and health.", false);
        TextEngine.printWithDelays("You can type help to see these commands again.\n", false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("Make sure you always press enter when prompted to! (press enter to continue)", false);
        ignore = console.readLine();
        TextEngine.printWithDelays("Good luck! (press enter to continue)", false);
        ignore = console.readLine();
        TextEngine.clearScreen();
        Game.playerCreated = true;
        Game.start();
    }
}
