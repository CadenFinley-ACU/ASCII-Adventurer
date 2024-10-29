
import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class Main {

    static String resetColor = "\033[0m"; // reset to default color
    static String yellowColor = "\033[1;33m"; // yellow color
    private final static Console console = System.console();
    private static String command;
    private static String ignore;
    public static boolean playerCreated = false;
    public static String savedPlace = null;
    private static final String OS_NAME = System.getProperty("os.name");
    public static Map<String, Integer> savedInventory = new HashMap<>();
    public static boolean gameComplete = false;
    public static String[] COMMANDS;

    public static void main(String[] args) throws InterruptedException { //main game start
        TextEngine.clearScreen();
        TextEngine.printNoDelay("Loading...", false);
        if (!hasSave()) {
            TextEngine.printNoDelay("Generating Dungeons...", false);
            TextEngine.printNoDelay("(P.S. if this takes more than ~10 seconds, restart the game.)", false);
            Dungeon.generateDungeons();
            TextEngine.printNoDelay("Generated Dungeons!", false);
        } else {
            TextEngine.printNoDelay("Locating Save File...", false);
            GameSaveSerialization.loadGameSave();
            TextEngine.printNoDelay("Save File Located!", false);
            if (PromptEngine.aiGenerationEnabled) {
                TextEngine.printNoDelay("Testing OpenAI API Connection...", false);
                if (PromptEngine.testAPIKey(PromptEngine.userAPIKey)) {
                    TextEngine.printNoDelay("OpenAI API Connection Successful!", false);
                } else {
                    TextEngine.printNoDelay("OpenAI API Connection Failed. Please check your internet connection and API key", false);
                    TextEngine.printNoDelay("AI Generation Disabled", false);
                    PromptEngine.aiGenerationEnabled = false;
                }
            }
        }
        TextEngine.printNoDelay("Creating Game Items...", false);
        createGameItems();
        TextEngine.printNoDelay("Game Items Created!", false);
        TextEngine.printNoDelay("Creating Enemies...", false);
        Enemy.createEnemies();
        TextEngine.printNoDelay("Enemies Created!", false);
        TextEngine.printWithDelays("Starting Game!", false);
        startMenu();

    }

    private static void createGameItems() { //initalize all the items in the game
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

    public static void startMenu() throws InterruptedException { //main menu and sstart menu text
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

    private static void handleMenuCommands() throws InterruptedException { //main menu command handling
        while (true) {
            COMMANDS = new String[]{"start", "settings", "exit"};
            ignore = console.readLine();
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
                default ->
                    TextEngine.printWithDelays("I'm sorry, I don't understand that command.", true);
            }
            COMMANDS = null;
        }
    }

    private static void splashScreen() {
        String brightBoldEnd = "\033[0m"; // end color
        String darkPurpleStart = "\033[38;2;255;165;0m"; // ACU Purple
        if (getOS_NAME().contains("Mac")) {
            darkPurpleStart = "\033[1;33m";
        }
        System.out.println(darkPurpleStart + "    _    ____   ____ ___ ___ ");
        System.out.println("   / \\  / ___| / ___|_ _|_ _| ");
        System.out.println("  / _ \\ \\___ \\| |    | | | |  ");
        System.out.println(" / ___ \\ ___) | |___ | | | |  ");
        System.out.println("/_/ _ \\_\\____/ \\____|___|___| _ ");
        System.out.println("   / \\   __| |_   _____ _ __ | |_ _   _ _ __ ___ _ __ ");
        System.out.println("  / _ \\ / _` \\ \\ / / _ \\ '_ \\| __| | | | '__/ _ \\ '__|");
        System.out.println(" / ___ \\ (_| |\\ V /  __/ | | | |_| |_| | | |  __/ |   ");
        System.out.println("/_/   \\_\\__,_| \\_/ \\___|_| |_|\\__|\\__,_|_|  \\___|_|   ");
        System.out.println(brightBoldEnd);

    }

    private static void displayHelp() throws InterruptedException { //main menu help command
        if (PromptEngine.aiGenerationEnabled && false) {
            PromptEngine.buildHelpPrompt(COMMANDS);
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            TextEngine.printWithDelays("Things you could say:\n" + yellowColor + "stats" + resetColor + " to see your stats\n" + yellowColor + "inventory" + resetColor + " to see your inventory\n" + yellowColor + "heal" + resetColor + " to heal you health using any available healing potions\n" + yellowColor + "settings" + resetColor + " or type " + yellowColor + "save" + resetColor + " to save\n" + yellowColor + "map" + resetColor + " to see the map\n" + yellowColor + "exit" + resetColor + " to return to the main menu.", true);
        }
    }

    private static void exitGame() throws InterruptedException {   //exit game command
        GameSaveSerialization.saveGame();
        TextEngine.printWithDelays("See ya next time!", false);
        try {
            FileWriter fwOb = new FileWriter(".runtime.txt", false);
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    GameSaveSerialization.saveGame();
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
                    GameSaveSerialization.saveGame();
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
            TextEngine.printWithDelays("Game saved!", false);
        }
        savedPlace = place;
    }

    public static void loadSave() throws InterruptedException { //load saved game command
        if (getSavedPlace() == null) {
            playerCreated = false;
            Player.playerStart();
        } else {
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
                    MeadowDungeon.startRoom();
                case "Dark Forest Dungeon" ->
                    DarkForestDungeon.startRoom();
                case "Mountain Cave Dungeon" ->
                    MountainCaveDungeon.startRoom();
                case "Mountain Top Dungeon" ->
                    MountainTopDungeon.startRoom();
                case "Desert Oasis Dungeon" ->
                    DesertOasisDungeon.startRoom();
                case "Desert Plains Dungeon" ->
                    DesertPlainsDungeon.startRoom();
                case "Desert Pyramid Dungeon" ->
                    DesertPyramidDungeon.startRoom();
                case "Ocean Kingdom Dungeon" ->
                    OceanKingdomDungeon.startRoom();
                default ->
                    startMenu();
            }
        }
    }

    public static void wipeSave() throws InterruptedException { //wipe save command
        playerCreated = false;
        savedPlace = null;
        gameComplete = false;
        Dungeon.resetedAfterWin = false;
        Room.reset("all");
        Player.setName(null);
        Dungeon.generateDungeons();
        PromptEngine.aiGenerationEnabled = false;
        PromptEngine.userAPIKey = null;
        try {
            FileWriter fwOb = new FileWriter(".runtime.txt", false);
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        GameSaveSerialization.saveGame();
        Enemy.resetEnemies();
    }

    public static String getSavedPlace() { //get the saved place
        return savedPlace;
    }

    public static boolean hasSave() { // Check if there is a save
        // Check if getSavedPlace() is not null
        // Check if the file game_save.txt exists
        File saveFile = new File(GameSaveSerialization.filePath);
        return getSavedPlace() != null || saveFile.exists() || Player.getName() != null;
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
            loadSave();
        } else {
            Player.playerStart();
        }
    }

    private static void promptLoadSavedGame() throws InterruptedException { //prompt to load saved game
        TextEngine.printWithDelays("Would you like to load your saved game? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ") ", true);
        ignore = console.readLine();
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
        ignore = console.readLine();
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
        TextEngine.printNoDelay("\n", false);
    }

    public static void screenRefresh() throws InterruptedException { //refresh the screen
        TextEngine.clearScreen();
        printStatus();
    }

    public static String getOS_NAME() { //get the os name
        return OS_NAME;
    }
}
