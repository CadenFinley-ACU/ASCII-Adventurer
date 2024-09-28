import java.io.Console;

/**
 * Room Engine /make this game engine and each room or area of rooms in their
 * own individual classes
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
class Main {

    private final static Console console = System.console();
    private static String command;
    private static String ignore;
    public static boolean playerCreated = false;
    private static String savedPlace = null;
    public static String os = null;

    public static void main(String[] args) throws InterruptedException {
        setOS();
        startMenu();
    }
    public static String getOS() throws InterruptedException {
        return os;
    }
    private static void setOS() throws InterruptedException {
        os = System.getProperty("os.name");
    }

    public static void startMenu() throws InterruptedException {
        TextEngine.clearScreen();
        TextEngine.printNoDelay("Adventure V1, by BarrettHall: Albert Tucker, Caden Finley, and Grijesh Shrestha", false);
        if (hasSave()) {
            TextEngine.printNoDelay("Welcome " + Player.getName() + "!", false);
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
                    TextEngine.printWithDelays("See ya next time!", true);
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
                case "debug":
                    TextEngine.speedSetting = "NoDelay";
                    TextEngine.clearScreen();
                    TextEngine.printWithDelays("Using System Property: " + getOS(), false);
                    TextEngine.printNoDelay("(Press enter to continue", false);
                    start();
                default:
                    TextEngine.printWithDelays("I'm sorry, I don't understand that command.", true);
            }
        }
    }

    public static void inGameDefaultTextHandling(String data) throws InterruptedException {
        switch (data) {
            case "help" ->
                TextEngine.printWithDelays("You can type 'inventory' to see your health and inventory\nor 'settings' or type 'save' to save \n or 'exit' to return to the main menu.", true);
            case "inventory" ->
                Player.openInventory();
            case "settings" -> {
                SettingsMenu.start();
                TextEngine.clearScreen();
            }

            case "save" ->
                checkSave(getSavedPlace());
            case "exit" -> {
                TextEngine.printWithDelays("Returning to main menu.", false);
                TextEngine.clearScreen();
                startMenu();
            }
            default ->
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

    public static void wipeSave() {
        savedPlace = null;
    }

    public static int getRoomId() {
        String save = getSavedPlace();
        switch (save) {
            case "SpawnRoom" -> {
                return SpawnRoom.roomSave;
            }
            case "OpenWorld" -> {
                return OpenWorld.roomSave;
            }
            default -> {
                return 0;
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
            if (command.toLowerCase().equals("no")) {
                String textState = TextEngine.speedSetting;
                TextEngine.speedSetting = "Slow";
                TextEngine.printWithDelays("All data will be wiped if you proceed.", false);
                TextEngine.printWithDelays("Are you sure?", true);
                ignore = console.readLine();
                command = console.readLine();
                if (command.toLowerCase().equals("yes")) {
                    TextEngine.clearScreen();
                    TextEngine.printWithDelays("Starting new game...", false);
                    wipeSave();
                    Player.playerStart();
                } else {
                    TextEngine.speedSetting = textState;
                    loadSave();
                }
            } else {
                loadSave();
            }
        } else if (playerCreated) {
            loadSave();
        } else {
            Player.playerStart();
        }
    }

    public static void printStatus() {
        TextEngine.printNoDelay("Name: " + Player.getName(), false);
        TextEngine.printNoDelay("Health: " + Player.getHealth() + " Gold: " + Player.getGold(), false);
        TextEngine.printNoDelay("Room: " + getSavedPlace() + " " + getRoomId() + "\n", false);
        TextEngine.printNoDelay("\n", false);
    }

    public static void screenRefresh() throws InterruptedException {
        TextEngine.clearScreen();
        printStatus();
    }
}
