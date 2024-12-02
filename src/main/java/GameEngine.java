
import java.io.Console;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * ASCIIADVENTURER A text-based adventure game with ASCII art. Caden Finley
 * Albert Tucker Grijesh Shrestha
 *
 * The GameEngine class is responsible for initializing and running the game. It
 * sets up the game environment, loads game items, enemies, and dungeons, and
 * starts the game.
 *
 * @author ASCIIADVENTURERS
 * @version 1.0
 */
public class GameEngine {

    static String resetColor = "\033[0m"; // reset to default color
    static String yellowColor = "\033[1;33m"; // yellow color
    private final static Console console = System.console();
    private static String command;
    public static boolean playerCreated = false;
    public static String savedPlace = null;
    private static final String OS_NAME = System.getProperty("os.name");
    public static Map<String, Integer> savedInventory = new HashMap<>();
    public static boolean gameComplete = false;
    public static String[] COMMANDS;
    public static ClockEngine playTime;

    public static boolean TESTING = false;

    /**
     * The main entry point for the game. This method initializes the game
     * environment, loads game items, enemies, and dungeons, and starts the
     * game.
     *
     * @param args Command line arguments (not used).
     * @throws InterruptedException If the thread is interrupted during
     * execution.
     */
    public static void main(String[] args) throws InterruptedException { //main game start
        TextEngine.setWidth();
        TextEngine.clearScreen();
        playTime = new ClockEngine("stopwatch");
        TextEngine.printNoDelay("Loading...", false);
        TextEngine.printNoDelay("Creating Game Items...", false);
        createGameItems();
        TextEngine.printNoDelay("Game Items Created!", false);
        TextEngine.printNoDelay("Creating Enemies...", false);
        Enemy.createEnemies();
        TextEngine.printNoDelay("Enemies Created!", false);
        TextEngine.printNoDelay("Initalizing Dungeons...", false);
        Dungeon.initalizeDungeons();
        TextEngine.printNoDelay("Dungeons Initalized!", false);
        if (!hasSave()) {
            gameStartGenDungeon();
        } else {
            TextEngine.printNoDelay("Locating Save File...", false);
            GameSaveSerialization.loadGameSave();
            Dungeon.setMaps();
            TextEngine.printNoDelay("Save File Located!", false);
            if (PromptEngine.aiGenerationEnabled) {
                TextEngine.printNoDelay("Testing OpenAI API Connection...", false);
                if (PromptEngine.testAPIKey(PromptEngine.USER_API_KEY)) {
                    TextEngine.printNoDelay("OpenAI API Connection Successful!", false);
                } else {
                    TextEngine.printNoDelay("OpenAI API Connection Failed. Please check your internet connection and API key", false);
                    TextEngine.printNoDelay("AI Generation Disabled", false);
                    PromptEngine.aiGenerationEnabled = false;
                }
            }
        }
        TextEngine.printWithDelays("Starting Game!", false);
        startMenu();
    }

    /**
     * Starts the game by generating dungeons. This method prints messages to
     * indicate the start and completion of dungeon generation. If the
     * generation takes more than approximately 10 seconds, it suggests
     * restarting the game. It calls methods to generate dungeons, set maps, and
     * initialize rooms.
     */
    public static void gameStartGenDungeon() {
        TextEngine.printNoDelay("Generating Dungeons...", false);
        TextEngine.printNoDelay("(P.S. if this takes more than ~10 seconds, restart the game.)", false);
        Dungeon.generateDungeons();
        Dungeon.setMaps();
        Dungeon.setRoomsBeenTo();
        TextEngine.printNoDelay("Generated Dungeons!", false);
    }

    /**
     * Initializes all the items in the game. The value is equal to the damage,
     * defense, or healing potential the item provides. This is only to use when
     * you use the item, not when you have it in your inventory or when it is on
     * the map.
     */
    public static void createGameItems() { //initalize all the items in the game
        //the value is equal to the damage, defense, or healing potential the item provides
        //this is only to use when you use the item not when you have it in your inventory or when it is on the map
        InventoryManager.createItem("weapon", "sword", 2); //spawn room weapon and shop 1

        InventoryManager.createItem("weapon", "axe", 5); //dungeon 1

        InventoryManager.createItem("weapon", "broad sword", 7); //dungeon 2

        InventoryManager.createItem("weapon", "better sword", 10); //dungeon 3

        InventoryManager.createItem("weapon", "great sword", 15); //dungeon 4

        InventoryManager.createItem("weapon", "master sword", 35);//dungeon 5

        InventoryManager.createItem("weapon", "legendary sword", 50); //dungeon 6

        InventoryManager.createItem("weapon", "excalibur", 80); //dungeon 7

        InventoryManager.createItem("weapon", "god slayer hammer", 100); //dungeon 8

        InventoryManager.createItem("weapon", "K.O. Cannon", 10000); //dungeon shop

        //*  ************************************************************************************ */
        InventoryManager.createItem("armor", "shield", 2); //village shop

        InventoryManager.createItem("armor", "chainmail set", 5); //dungeon 1

        InventoryManager.createItem("armor", "full armor kit", 10); //dungeon 2

        InventoryManager.createItem("armor", "ninja armor", 15); //dungeon 3

        InventoryManager.createItem("armor", "knight armor", 25); //dungeon 4

        InventoryManager.createItem("armor", "royal armor", 35); //dungeon 5

        InventoryManager.createItem("armor", "demon armor", 40); //dungeon 6

        InventoryManager.createItem("armor", "angel armor", 50); //dungeon 7

        InventoryManager.createItem("armor", "god slayer armor", 75); //dungeon 8

        //*  ************************************************************************************ */
        InventoryManager.createItem("potion", "health potion", 15); //village level 1 | dungeon 0,1,2
        InventoryManager.createItem("potion", "greater health potion", 30); //village level 2 | dungeon 3,4,5
        InventoryManager.createItem("potion", "super health potion", 50); //village level 3 | dungeon 6,7,8

        InventoryManager.createItem("potion", "Backpack", 15);
        InventoryManager.createItem("potion", "Large Backpack", 30);

        InventoryManager.createItem("potion", "heart container", 20);

        InventoryManager.createItem("key", "key", 0);
    }

    /**
     * Displays the start menu and handles user input commands.
     *
     * This method stops the play time clock, clears the screen, displays the
     * splash screen, and prints the welcome message. If a saved game exists and
     * the player's name is not null, it welcomes the player by name. Otherwise,
     * it welcomes the player as "Hero". It then prompts the user for a command
     * (Start, Settings, Exit) and handles the input.
     *
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public static void startMenu() throws InterruptedException { //main menu and sstart menu text
        playTime.stopClock();
        TextEngine.clearScreen();
        splashScreen();
        TextEngine.printNoDelay("               by: Albert Tucker, Caden Finley, and Grijesh Shrestha", false);
        System.out.println();
        if (hasSave() && Player.getName() != null && !"null".equals(Player.getName())) {
            TextEngine.printNoDelay("Welcome " + Player.getName() + "!", false);
        } else {
            TextEngine.printNoDelay("Welcome Hero!", false);
        }
        TextEngine.printWithDelays("What is your command: " + yellowColor + "Start" + resetColor + ", " + yellowColor + "Settings" + resetColor + ", " + yellowColor + "Exit" + resetColor, true);
        handleMenuCommands();
    }

    /**
     * Handles the main menu commands in an infinite loop. This method reads
     * commands from the console and executes the corresponding actions.
     *
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    private static void handleMenuCommands() throws InterruptedException { //main menu command handling
        while (true) {
            COMMANDS = new String[]{"start", "settings", "exit"};
            command = console.readLine();
            switch (command.toLowerCase().trim()) {
                case "start" ->
                    start();
                case "settings" ->
                    SettingsMenu.startFromStartMenu();
                case "help" ->
                    displayHelp();
                case "exit" ->
                    exitGame();
                case "fast" ->
                    setTextSpeed("Fast");
                case "slow" ->
                    setTextSpeed("Slow");
                case "normal" ->
                    setTextSpeed("Normal");
                case "nodelay" ->
                    setTextSpeed("NoDelay");
                case "debug" ->
                    debugInfo();
                case "change name" ->
                    Player.changeName();
                case "link" ->
                    showQrCode();
                default ->
                    TextEngine.printWithDelays("I'm sorry, I don't understand that command.", true);
            }
            COMMANDS = null;
        }
    }

    /**
     * Displays the splash screen with a custom ASCII art message. The color of
     * the text is adjusted based on the operating system.
     *
     * The splash screen includes a message about the AI feature and a link to
     * the git repository.
     *
     * The colors used are: - Dark Purple for non-Mac operating systems - Bright
     * Yellow for Mac operating systems
     *
     * The ASCII art displayed is:
     *
     * <pre>
     *     _    ____   ____ ___ ___         Now With AI!
     *    / \  / ___| / ___|_ _|_ _|        Type 'link' To see the git repo
     *   / _ \ \___ \| |    | | | |         of this project!
     *  / ___ \ ___) | |___ | | | |
     * /_/ _ \_\____/ \____|___|___| _
     *    / \   __| |_   _____ _ __ | |_ _   _ _ __ ___ _ __
     *   / _ \ / _` \ \ / / _ \ '_ \| __| | | | '__/ _ \ '__|
     *  / ___ \ (_| |\ V /  __/ | | | |_| |_| | | |  __/ |
     * /_/   \_\__,_| \_/ \___|_| |_|\__|\__,_|_|  \___|_|
     * </pre>
     */
    private static void splashScreen() {
        String brightBoldEnd = "\033[0m"; // end color
        String darkPurpleStart = "\033[38;2;255;165;0m"; // ACU Purple
        if (getOS_NAME().contains("Mac")) {
            darkPurpleStart = "\033[1;33m";
        }
        System.out.println(darkPurpleStart + "    _    ____   ____ ___ ___         Now With AI!");
        System.out.println("   / \\  / ___| / ___|_ _|_ _|        Type 'link' To see the git repo");
        System.out.println("  / _ \\ \\___ \\| |    | | | |         of this project!");
        System.out.println(" / ___ \\ ___) | |___ | | | |                                        ");
        System.out.println("/_/ _ \\_\\____/ \\____|___|___| _                                   ");
        System.out.println("   / \\   __| |_   _____ _ __ | |_ _   _ _ __ ___ _ __               ");
        System.out.println("  / _ \\ / _` \\ \\ / / _ \\ '_ \\| __| | | | '__/ _ \\ '__|         ");
        System.out.println(" / ___ \\ (_| |\\ V /  __/ | | | |_| |_| | | |  __/ |                ");
        System.out.println("/_/   \\_\\__,_| \\_/ \\___|_| |_|\\__|\\__,_|_|  \\___|_|           ");
        System.out.print(brightBoldEnd);
    }

    /**
     * Displays the help menu with a list of available commands.
     *
     * This method prints out the possible commands that the user can input,
     * including "stats", "inventory", "heal", "settings", "save", "map", and
     * "exit". Each command is displayed with a delay for better readability.
     *
     * @throws InterruptedException if the thread executing the method is
     * interrupted
     */
    private static void displayHelp() throws InterruptedException { //main menu help command
        TextEngine.printWithDelays("Things you could say:\n" + yellowColor + "stats" + resetColor + " to see your stats\n" + yellowColor + "inventory" + resetColor + " to see your inventory\n" + yellowColor + "heal" + resetColor + " to heal you health using any available healing potions\n" + yellowColor + "settings" + resetColor + " or type " + yellowColor + "save" + resetColor + " to save\n" + yellowColor + "map" + resetColor + " to see the map\n" + yellowColor + "exit" + resetColor + " to return to the main menu.", true);
    }

    /**
     * Exits the game by performing the following actions:
     * <ul>
     * <li>Saves the current game state using
     * {@link GameSaveSerialization#saveGame()}</li>
     * <li>Wipes the runtime file ".runtime.txt" using
     * {@link #wipeFile(String)}</li>
     * <li>Prints a farewell message with delays using
     * {@link TextEngine#printWithDelays(String, boolean)}</li>
     * <li>Waits for user input to proceed using
     * {@link TextEngine#enterToNext()}</li>
     * <li>Clears the screen using {@link TextEngine#clearScreen()}</li>
     * <li>Exits the program with status code 0 using
     * {@link System#exit(int)}</li>
     * </ul>
     *
     * @throws InterruptedException if any thread has interrupted the current
     * thread
     */
    private static void exitGame() throws InterruptedException {
        GameSaveSerialization.saveGame();
        wipeFile(".runtime.txt");
        TextEngine.printWithDelays("See ya next time!", false);
        TextEngine.enterToNext();
        TextEngine.clearScreen();
        System.exit(0);
    }

    private static void setTextSpeed(String speed) throws InterruptedException { //set text speed command
        TextEngine.speedSetting = speed;
        TextEngine.printWithDelays("Text speed set to " + speed, true);
    }

    private static void debugInfo() throws InterruptedException { //debug command
        TextEngine.speedSetting = "NoDelay";
        TextEngine.clearScreen();
        TextEngine.printWithDelays("Using System Property: " + getOS_NAME(), false);
        TextEngine.printWithDelays("Using Console: " + console, false);
        TextEngine.printWithDelays("Text Speed: " + TextEngine.speedSetting, false);
        TextEngine.enterToNext();
        Player.debugStart();
    }

    public static void inGameDefaultTextHandling(String data) throws InterruptedException { //default in game commands
        if (!Dungeon.ableToUseMenuCommands()) {
            COMMANDS = new String[]{"help", "save", "exit"};
            switch (data.toLowerCase().trim()) {
                case "help" ->
                    displayHelp();
                case "save" -> {
                    checkSave(getSavedPlace());
                    TextEngine.printWithDelays("Game saved!", true);
                }
                case "exit" -> {
                    TextEngine.printWithDelays("Returning to main menu.", false);
                    TextEngine.clearScreen();
                    saveSpace(savedPlace);
                    startMenu();
                }
                default ->
                    invalidCommandWithBuffer();
            }
            COMMANDS = null;
        } else {
            COMMANDS = new String[]{"help", "inventory", "settings", "save", "exit", "heal", "stats", "map"};
            switch (data.toLowerCase().trim()) {
                case "help" ->
                    displayHelp();
                case "inventory" ->
                    Player.openInventory();
                case "settings" ->
                    SettingsMenu.start();
                case "save" -> {
                    checkSave(getSavedPlace());
                    TextEngine.printWithDelays("Game saved!", false);
                    TextEngine.enterToNext();
                }
                case "exit" -> {
                    TextEngine.printWithDelays("Returning to main menu.", false);
                    TextEngine.clearScreen();
                    saveSpace(savedPlace);
                    startMenu();
                }
                case "heal" ->
                    Player.heal();
                case "stats" ->
                    Player.printStats();
                case "map" -> {
                    if (getSavedPlace().equals("OpenWorld") && !OpenWorld.inFight) {
                        OpenWorld.previousRoomSave = OpenWorld.roomSave;
                        Player.printMap();
                    } else {
                        TextEngine.printWithDelays("You cannot use that command right now.", true);
                    }
                }
                default ->
                    invalidCommandWithBuffer();
            }
            COMMANDS = null;
        }
    }

    public static void invalidCommandWithBuffer() throws InterruptedException {
        TextEngine.printWithDelays("I'm sorry, I don't understand that command.", true);
    }

    public static void invalidCommand() throws InterruptedException {
        TextEngine.printWithDelays("I'm sorry, I don't understand that command.", false);
    }

    public static void saveSpace(String place) throws InterruptedException { //save game command
        if (savedPlace != null) {
            GameSaveSerialization.saveGame();
            displaySaveInfo();
            TextEngine.printWithDelays("Game saved!", false);
        }
        savedPlace = place;
    }

    public static void loadSave() throws InterruptedException { //load saved game command
        if (getSavedPlace() == null) {
            playerCreated = false;
            Player.playerStart();
        } else {
            playTime.startClock(1);
            GameSaveSerialization.saveGame();
            InventoryManager.setStatsToHighestInInventory();
            switch (getSavedPlace()) {
                case "SpawnRoom" ->
                    SpawnRoom.startRoom();
                case "OpenWorld" ->
                    OpenWorld.startRoom();
                case "Village" ->
                    Village.startRoom();
                case "Meadow Dungeon" ->
                    Dungeon.MeadowDungeon.startRoom();
                case "Dark Forest Dungeon" ->
                    Dungeon.DarkForestDungeon.startRoom();
                case "Mountain Cave Dungeon" ->
                    Dungeon.MountainCaveDungeon.startRoom();
                case "Mountain Top Dungeon" ->
                    Dungeon.MountainTopDungeon.startRoom();
                case "Desert Oasis Dungeon" ->
                    Dungeon.DesertOasisDungeon.startRoom();
                case "Desert Plains Dungeon" ->
                    Dungeon.DesertPlainsDungeon.startRoom();
                case "Desert Pyramid Dungeon" ->
                    Dungeon.DesertPyramidDungeon.startRoom();
                case "Ocean Kingdom Dungeon" ->
                    Dungeon.OceanKingdomDungeon.startRoom();
                default ->
                    startMenu();
            }
        }
    }

    public static void wipeSave() { //wipe save command
        playerCreated = false;
        savedPlace = null;
        gameComplete = false;
        Dungeon.resetedAfterWin = false;
        Room.reset("all");
        Player.setName(null);
        Dungeon.generateDungeons();
        PromptEngine.aiGenerationEnabled = false;
        GameEngine.playTime.setSavedTimeInSeconds(0);
        wipeFile(".runtime.txt");
        GameSaveSerialization.saveGame();
        Enemy.resetEnemies();
    }

    public static String getSavedPlace() { //get the saved place
        if (savedPlace == null) {
            return "SpawnRoom";
        }
        return savedPlace;
    }

    public static boolean hasSave() { // Check if there is a save
        // Check if getSavedPlace() is not null
        // Check if the file game_save.txt exists
        File saveFile = new File(GameSaveSerialization.filePath);
        return saveFile.exists() && (getSavedPlace() != null || Player.getName() != null);
    }

    public static void checkSave(String place) throws InterruptedException { //check if there is a save and if that save is where you currently are
        if (!hasSave() || !getSavedPlace().equals(place)) {
            saveSpace(place);
            GameSaveSerialization.saveGame();
        }
    }

    public static void start() throws InterruptedException { //start the game
        TextEngine.clearScreen();
        if (hasSave() && Player.getName() != null && !"null".equals(Player.getName())) {
            promptLoadSavedGame();
        } else if (playerCreated && Player.getName() != null && !"null".equals(Player.getName())) {
            saveSpace("SpawnRoom");
            playTime.startClock(1);
            loadSave();
        } else {
            Player.playerStart();
        }
    }

    private static void promptLoadSavedGame() throws InterruptedException { //prompt to load saved game
        displaySaveInfo();
        TextEngine.printWithDelays("Would you like to load your saved game? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ") ", true);
        command = console.readLine();
        if (command.toLowerCase().equals("no")) {
            confirmWipeSave();
        } else {
            loadSave();
        }
    }

    private static void confirmWipeSave() throws InterruptedException { //confirm to wipe save
        String textState = TextEngine.speedSetting;
        TextEngine.speedSetting = "Slow";
        TextEngine.printWithDelays("All data will be wiped if you proceed. (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ") ", false);
        TextEngine.printWithDelays("Are you sure?", true);
        command = console.readLine();
        if (command.toLowerCase().trim().equals("yes")) {
            TextEngine.clearScreen();
            TextEngine.printWithDelays("Starting new game...", false);
            TextEngine.speedSetting = textState;
            wipeSave();
            Player.playerStart();
        } else {
            TextEngine.speedSetting = textState;
            loadSave();
        }
    }

    public static void printStatus() { //print the status of the player
        if (getSavedPlace() != null) {
            TextEngine.printNoDelay(Player.getName() + "'s adventure through the " + getSavedPlace(), false);
        } else {
            TextEngine.printNoDelay(Player.getName() + "'s adventure", false);
        }
        //TextEngine.printNoDelay("Health: " + healthColor+Player.getHealth()+resetColor, false);
        Player.drawHealthBar();
        //TextEngine.printNoDelay("Gold: " + Player.getGold(), false);
        System.out.println();
    }

    public static void screenRefresh() { //refresh the screen
        TextEngine.clearScreen();
        printStatus();
    }

    public static String getOS_NAME() { //get the os name
        return OS_NAME;
    }

    public static void showQrCode() throws InterruptedException {
        TextEngine.clearScreen();
        System.out.println("Scan this QR code to view the source code of this project:   ( https://github.com/CadenFinley-ACU/ASCII-Adventurer )");
        System.out.println("██████████████████████████████████████████████████████████████████████");
        System.out.println("██              ██████████  ██  ██  ██        ██  ████              ██");
        System.out.println("██  ██████████  ██████  ██  ████    ██  ██      ██████  ██████████  ██");
        System.out.println("██  ██      ██  ██      ██████      ████████    ██████  ██      ██  ██");
        System.out.println("██  ██      ██  ██    ██████████  ████  ██████  ██  ██  ██      ██  ██");
        System.out.println("██  ██      ██  ██  ██████          ██████    ██  ████  ██      ██  ██");
        System.out.println("██  ██████████  ██  ██  ██  ████████████  ████████  ██  ██████████  ██");
        System.out.println("██              ██  ██  ██  ██  ██  ██  ██  ██  ██  ██              ██");
        System.out.println("██████████████████  ████    ██  ██  ██        ██  ████████████████████");
        System.out.println("██  ██          ██████  ██    ██████  ██  ████████████          ██████");
        System.out.println("██████  ██  ████  ████  ████  ████          ████  ████    ██      ████");
        System.out.println("██    ██    ██    ██      ██      ██  ████  ██  ██  ██  ██  ██    ████");
        System.out.println("████      ██  ████        ██  ██          ██  ████    ████          ██");
        System.out.println("████████        ████  ████  ██  ██  ██████████    ██  ██  ██  ████████");
        System.out.println("██████      ████    ██████    ██  ████  ██████    ██████████        ██");
        System.out.println("██  ██      ██          ██  ██      ██████████  ██      ██        ████");
        System.out.println("████████████████    ██████        ██████  ██  ██████      ████  ██████");
        System.out.println("██  ██████████      ██████        ██  ████████  ██    ██    ██████  ██");
        System.out.println("████          ██  ████  ██████████  ██  ██    ██  ████    ██    ██████");
        System.out.println("██    ████  ██  ██  ██████  ████  ████  ██      ██  ██      ██    ████");
        System.out.println("██  ██  ████  ████████████      ██  ████      ██████  ██          ████");
        System.out.println("██        ██    ██        ██    ██████      ██  ██  ████  ██  ████████");
        System.out.println("██  ██        ██    ████  ██  ██      ██          ████  ████  ████  ██");
        System.out.println("██  ██████  ██  ██      ██  ██  ████  ████  ████████  ████    ██  ████");
        System.out.println("██  ████    ████    ██████  ██████  ██  ██      ██        ██        ██");
        System.out.println("██  ██  ██  ██  ██  ████    ██  ██████████████  ██          ██  ██████");
        System.out.println("██████████████████      ██████████        ██  ██    ██████  ██  ██████");
        System.out.println("██              ██████  ████          ████          ██  ██  ██    ████");
        System.out.println("██  ██████████  ██  ██  ██  ██    ██    ██          ██████        ████");
        System.out.println("██  ██      ██  ██  ██    ████████  ████  ████  ██            ██    ██");
        System.out.println("██  ██      ██  ██  ████████      ████      ██        ████  ██      ██");
        System.out.println("██  ██      ██  ██  ██  ██████        ████████  ██████    ██    ██████");
        System.out.println("██  ██████████  ████████      ██  ████    ██    ██      ██      ██████");
        System.out.println("██              ██      ██        ██  ████████        ██  ██      ████");
        System.out.println("██████████████████████████████████████████████████████████████████████");
        System.out.println();
        TextEngine.enterToNext();
        TextEngine.clearScreen();
        startMenu();
    }

    private static void displaySaveInfo() {
        TextEngine.printNoDelay("Name: " + Player.getName(), false);
        TextEngine.printNoDelay("Play time: " + playTime.returnTime(), false);
        TextEngine.printNoDelay("Location: " + getSavedPlace(), false);
        TextEngine.printNoDelay("Health: " + Player.getHealth() + "/" + Player.getMaxHealth(), false);
        TextEngine.printNoDelay("Gold: " + Player.getGold(), false);
        TextEngine.printNoDelay("Completed Dungeons: " + Dungeon.completedDungeons, false);
    }

    public static void wipeFile(String fileName) {
        File file = new File(fileName);
        if (!file.delete()) {
            System.out.println("Failed to delete the file: " + fileName);
        }
    }
}
