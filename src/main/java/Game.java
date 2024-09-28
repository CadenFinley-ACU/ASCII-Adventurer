
import java.io.Console;

/**
 * Room Engine /make this game engine and each room or area of rooms in their
 * own individual classes
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
class Game {

    private final static Console console = System.console();
    private static String command;
    private static String ignore;
    public static boolean playerCreated = false;
    private static String savedPlace = null;

    public static void main(String[] args) throws InterruptedException {
        TextEngine.clearScreen();
        startMenu();
    }

    public static void startMenu() throws InterruptedException {
        TextEngine.printNoDelay("Adventure V1, by BarrettHall: Albert Tucker, Caden Finley, and Grijesh Shrestha", false);
        if (hasSave()) {
            TextEngine.printNoDelay("Welcome " + Player.getName(), false);
        } else {
            TextEngine.printNoDelay("Welcome Hero!", false);
        }
        TextEngine.printWithDelays("What is your command: Start, Settings, Exit", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "start":
                    TextEngine.clearScreen();
                    start();
                case "settings":
                    TextEngine.clearScreen();
                    SettingsMenu.start();
                case "help":
                    TextEngine.printWithDelays("You can type 'start' to start the game\nor 'settings' to change the text speed\nor 'exit' to leave the game.", true);
                    continue;
                case "exit":
                    TextEngine.printWithDelays("See ya next time!", false);
                    TextEngine.clearScreen();
                    System.exit(0);
                case "fast":
                    TextEngine.speedSetting = "Fast";
                    TextEngine.printWithDelays("Text speed set to Fast", true);
                    continue;
                case "slow":
                    TextEngine.speedSetting = "Slow";
                    TextEngine.printWithDelays("Text speed set to Slow", true);
                    continue;
                case "normal":
                    TextEngine.speedSetting = "Normal";
                    TextEngine.printWithDelays("Text speed set to Normal", true);
                    continue;
                case "nodelay":
                    TextEngine.speedSetting = "NoDelay";
                    TextEngine.printWithDelays("Text speed set to NoDelay", true);
                    continue;
                default:
                    TextEngine.printWithDelays("I'm sorry, I don't understand that command.", true);
            }
        }
    }

    public static void inGameDefaultTextHandling(String data) throws InterruptedException {
        switch (data) {
            case "help":
                TextEngine.printWithDelays("You can type 'inventory' to see your health and inventory\nor 'settings' or type 'save' to save \n or 'exit' to return to the main menu.", true);
                return;
            case "inventory":
                Player.getInventory();
                return;
            case "settings":
                SettingsMenu.start();
                TextEngine.clearScreen();
                return;
            case "save":
                //should auto save when you get to every area so this shouldnt matter unless we add spots that dont auto save
                checkSave(getSavedPlace());
                return;
            case "exit":
                TextEngine.printWithDelays("Returning to main menu.", false);
                TextEngine.clearScreen();
                startMenu();
            default:
                TextEngine.printWithDelays("I'm sorry, I don't understand that command.", true);
        }
    }

    public static void saveSpace(String place) throws InterruptedException {
        if (savedPlace != null) {
            TextEngine.printWithDelays("Game saved!", false);
        }
        savedPlace = place;
    }

    public static void loadSave() throws InterruptedException {
        if (getSavedPlace() == null) {
            SpawnRoom.startRoom();
        } else {
            switch (getSavedPlace()) {
                case "SpawnRoom" ->
                    SpawnRoom.startRoom();
                case "OpenWorld" ->
                    OpenWorld.startRoom();
                default ->
                    startMenu();
            }
        }
    }

    public static String getSavedPlace() {
        return savedPlace;
    }

    private static boolean hasSave() {
        return getSavedPlace() != null;
    }

    public static void checkSave(String place) throws InterruptedException {
        if (!hasSave()) {
            saveSpace(place);
        } else if (!getSavedPlace().equals(place)) {
            saveSpace(place);
        }
    }

    public static void start() throws InterruptedException {
        if (hasSave()) {
            TextEngine.printWithDelays("Would you like to load your saved game?", true);
            ignore = console.readLine();
            command = console.readLine();
            if (command.toLowerCase().equals("yes")) {
                loadSave();
            } else {
                Player.playerStart();
            }
        } else if (playerCreated) {
            loadSave();
        } else {
            Player.playerStart();
        }
    }

    public static void printStatus() {
        TextEngine.printNoDelay("Current Status: " + Player.getName(), false);
        TextEngine.printNoDelay("Health: " + Player.getHealth(), false);
        TextEngine.printNoDelay("Room: " + getSavedPlace() + "\n", false);
        TextEngine.printNoDelay("\n", false);

    }
}
