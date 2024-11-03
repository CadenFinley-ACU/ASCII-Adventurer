
import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class Main {

    public static final boolean TESTING = true;

    static String resetColor = "\033[0m"; // reset to default color
    static String yellowColor = "\033[1;33m"; // yellow color
    static String redColor = "\033[0;31m"; // red color
    private final static Console console = System.console();
    private static String command;
    public static boolean playerCreated = false;
    public static String savedPlace = null;
    private static final String OS_NAME = System.getProperty("os.name");
    public static Map<String, Integer> savedInventory = new HashMap<>();
    public static boolean gameComplete = false;
    public static String[] COMMANDS;
    public static TimerEngine playTime;

    public static Dungeon MeadowDungeon;
    public static Dungeon DarkForestDungeon;
    public static Dungeon MountainCaveDungeon;
    public static Dungeon MountainTopDungeon;
    public static Dungeon DesertOasisDungeon;
    public static Dungeon DesertPlainsDungeon;
    public static Dungeon DesertPyramidDungeon;
    public static Dungeon OceanKingdomDungeon;

    public static void main(String[] args) throws InterruptedException, IOException { //main game start
        if (TESTING) {
            System.out.println("Running function: main");
        }
        TextEngine.clearScreen();
        TextEngine.printNoDelay("Loading...", false);
        playTime = new TimerEngine();
        TextEngine.setWidth();
        TextEngine.printNoDelay("Generating Dungeons...", false);
        TextEngine.printNoDelay("(P.S. if this takes more than ~10 seconds, restart the game.)", false);
        buildDungeons();
        TextEngine.printNoDelay("Generated Dungeons!", false);
        if (hasSave()) {
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
        if (TESTING) {
            System.out.println(redColor + "Function Complete: main" + resetColor);
        }
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

    public static void buildDungeons() {
        if (TESTING) {
            System.out.println("Running function: buildDungeons");
        }
        MeadowDungeon = new Dungeon(DungeonGenerator.generateAndReturnMatrix(5), new ArrayList<>(List.of("Goblin", "Skeleton", "Slime", "Mimic")), List.of("axe", "chainmail set"), "Golem", "Forest Giant", 3);
        DarkForestDungeon = new Dungeon(DungeonGenerator.generateAndReturnMatrix(6), new ArrayList<>(List.of("Goblin", "Skeleton", "Orc", "Mimic", "Zombie")), List.of("broad sword", "full armor kit"), "Forest Guardian", "Forest Spirit", 4);
        MountainCaveDungeon = new Dungeon(DungeonGenerator.generateAndReturnMatrix(7), new ArrayList<>(List.of("Troll", "Skeleton", "Orc", "Ghost", "Demon", "Zombie")), List.of("better sword", "ninja armor"), "Elemental", "Wyvern", 5);
        MountainTopDungeon = new Dungeon(DungeonGenerator.generateAndReturnMatrix(7), new ArrayList<>(List.of("Ghost", "Gargoyle", "Orc", "Vampire", "Demon")), List.of("great sword", "knight armor"), "Minotaur", "Ice Dragon", 7);
        DesertOasisDungeon = new Dungeon(DungeonGenerator.generateAndReturnMatrix(8), new ArrayList<>(List.of("Werewolf", "Witch", "Giant", "Mummy", "Minotaur")), List.of("master sword", "royal armor"), "Sphinx", "Pheonix", 3);
        DesertPlainsDungeon = new Dungeon(DungeonGenerator.generateAndReturnMatrix(8), new ArrayList<>(List.of("Orc", "Troll", "Mummy", "Demon")), List.of("legendary sword", "demon armor"), "Cyclops", "Giant Scorpion", 4);
        DesertPyramidDungeon = new Dungeon(DungeonGenerator.generateAndReturnMatrix(9), new ArrayList<>(List.of("Werewolf", "Witch", "Giant", "Mummy", "Minotaur")), List.of("excalibur", "angel armor"), "Medusa", "Giant Sand Worm", 4);
        OceanKingdomDungeon = new Dungeon(DungeonGenerator.generateAndReturnMatrix(11), new ArrayList<>(List.of("Sea Serpent", "Sea Monster", "Sea Witch", "Sea Dragon", "Sea Dragon")), List.of("god slayer hammer", "god slayer armor"), "Leviathan", "Kraken", 5);
        if (TESTING) {
            System.out.println(redColor + "Function Complete: buildDungeons" + resetColor);
        }
    }

    public static void startMenu() throws InterruptedException { //main menu and sstart menu text
        if (TESTING) {
            System.out.println("Running function: startMenu");
        }
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
        if (TESTING) {
            System.out.println(redColor + "Function Complete: startMenu" + resetColor);
        }
    }

    private static void handleMenuCommands() throws InterruptedException { //main menu command handling
        if (TESTING) {
            System.out.println("Running function: handleMenuCommands");
        }
        while (true) {
            COMMANDS = new String[]{"start", "settings", "exit"};
            command = console.readLine();
            switch (command.toLowerCase().trim()) {
                case "start":
                    start();
                    break;
                case "settings":
                    SettingsMenu.startFromStartMenu();
                    break;
                case "help":
                    displayHelp();
                    break;
                case "exit":
                    exitGame();
                    break;
                case "fast":
                    setTextSpeed("Fast");
                    break;
                case "slow":
                    setTextSpeed("Slow");
                    break;
                case "normal":
                    setTextSpeed("Normal");
                    break;
                case "nodelay":
                    setTextSpeed("NoDelay");
                    break;
                case "debug":
                    debugInfo();
                    break;
                case "change name":
                    Player.changeName();
                    break;
                case "source code":
                    showQrCode();
                    break;
                default:
                    TextEngine.printWithDelays("I'm sorry, I don't understand that command.", true);
                    continue;
            }
            COMMANDS = null;
            break;
        }
        if (TESTING) {
            System.out.println(redColor + "Function Complete: handleMenuCommands" + resetColor);
        }
    }

    private static void splashScreen() {
        if (TESTING) {
            System.out.println("Running function: slashScreen");
        }
        String brightBoldEnd = "\033[0m"; // end color
        String darkPurpleStart = "\033[38;2;255;165;0m"; // ACU Purple
        if (getOS_NAME().contains("Mac")) {
            darkPurpleStart = "\033[1;33m";
        }
        System.out.println(darkPurpleStart + "    _    ____   ____ ___ ___                       ");
        System.out.println("   / \\  / ___| / ___|_ _|_ _|                                       ");
        System.out.println("  / _ \\ \\___ \\| |    | | | |                                      ");
        System.out.println(" / ___ \\ ___) | |___ | | | |                                        ");
        System.out.println("/_/ _ \\_\\____/ \\____|___|___| _                                   ");
        System.out.println("   / \\   __| |_   _____ _ __ | |_ _   _ _ __ ___ _ __               ");
        System.out.println("  / _ \\ / _` \\ \\ / / _ \\ '_ \\| __| | | | '__/ _ \\ '__|         ");
        System.out.println(" / ___ \\ (_| |\\ V /  __/ | | | |_| |_| | | |  __/ |                ");
        System.out.println("/_/   \\_\\__,_| \\_/ \\___|_| |_|\\__|\\__,_|_|  \\___|_|           ");
        System.out.print(brightBoldEnd);
        if (TESTING) {
            System.out.println(redColor + "Function Complete: splashScreen" + resetColor);
        }
    }

    private static void displayHelp() throws InterruptedException { //main menu help command
        if (TESTING) {
            System.out.println("Running function: displayHelp");
        }
        if (PromptEngine.aiGenerationEnabled && false) {
            PromptEngine.buildHelpPrompt(COMMANDS);
            TextEngine.printWithDelays(PromptEngine.returnPrompt(), false);
        } else {
            TextEngine.printWithDelays("Things you could say:\n" + yellowColor + "stats" + resetColor + " to see your stats\n" + yellowColor + "inventory" + resetColor + " to see your inventory\n" + yellowColor + "heal" + resetColor + " to heal you health using any available healing potions\n" + yellowColor + "settings" + resetColor + " or type " + yellowColor + "save" + resetColor + " to save\n" + yellowColor + "map" + resetColor + " to see the map\n" + yellowColor + "exit" + resetColor + " to return to the main menu.", true);
        }
        if (TESTING) {
            System.out.println(redColor + "Function Complete: displayHelp" + resetColor);
        }
    }

    private static void exitGame() throws InterruptedException {   //exit game command
        if (TESTING) {
            System.out.println("Running function: exitGame");
        }
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
        if (TESTING) {
            System.out.println("Running function: setTextSpeed");
        }
        TextEngine.speedSetting = speed;
        TextEngine.printWithDelays("Text speed set to " + speed, true);
        if (TESTING) {
            System.out.println(redColor + "Function Complete: setTextSpeed" + resetColor);
        }
    }

    private static void debugInfo() throws InterruptedException { //debug command
        if (TESTING) {
            System.out.println("Running function: debugInfo");
        }
        TextEngine.speedSetting = "NoDelay";
        TextEngine.clearScreen();
        TextEngine.printWithDelays("Using System Property: " + getOS_NAME(), false);
        TextEngine.printWithDelays("Using Console: " + console, false);
        TextEngine.printWithDelays("Text Speed: " + TextEngine.speedSetting, false);
        TextEngine.enterToNext();
        Player.debugStart();
        if (TESTING) {
            System.out.println(redColor + "Function Complete: debugInfo" + resetColor);
        }
    }

    public static void inGameDefaultTextHandling(String data) throws InterruptedException { //default in game commands
        if (TESTING) {
            System.out.println("Running function: inGameDefaultTextHandling");
        }
        boolean menuCommandsCheck;
        if (Dungeon.currentDungeon != null) {
            menuCommandsCheck = switch (Dungeon.currentDungeon) {
                case "Meadow" ->
                    MeadowDungeon.ableToUseMenuCommands();
                case "Dark Forest" ->
                    DarkForestDungeon.ableToUseMenuCommands();
                case "Mountain Cave" ->
                    MountainCaveDungeon.ableToUseMenuCommands();
                case "Mountain Top" ->
                    MountainTopDungeon.ableToUseMenuCommands();
                case "Desert Oasis" ->
                    DesertOasisDungeon.ableToUseMenuCommands();
                case "Desert Plains" ->
                    DesertPlainsDungeon.ableToUseMenuCommands();
                case "Desert Pyramid" ->
                    DesertPyramidDungeon.ableToUseMenuCommands();
                case "Ocean Kingdom" ->
                    OceanKingdomDungeon.ableToUseMenuCommands();
                default ->
                    true;
            };
        } else {
            menuCommandsCheck = true;
        }
        if (!menuCommandsCheck) {
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
        if (TESTING) {
            System.out.println(redColor + "Function Complete: inGameDefaultTextHandling" + resetColor);
        }
    }

    public static void invalidCommandWithBuffer() throws InterruptedException {
        if (TESTING) {
            System.out.println("Running function: invalidCommandWithBuffer");
        }
        TextEngine.printWithDelays("I'm sorry, I don't understand that command.", true);
        if (TESTING) {
            System.out.println(redColor + "Function Complete: invalidCommandWithBuffer" + resetColor);
        }
    }

    public static void invalidCommand() throws InterruptedException {
        if (TESTING) {
            System.out.println("Running function: invalidCommand");
        }
        TextEngine.printWithDelays("I'm sorry, I don't understand that command.", false);
        if (TESTING) {
            System.out.println(redColor + "Function Complete: invalidCommand" + resetColor);
        }
    }

    public static void saveSpace(String place) throws InterruptedException { //save game command
        if (TESTING) {
            System.out.println("Running function: saveSpace");
        }
        if (savedPlace != null) {
            GameSaveSerialization.saveGame();
            TextEngine.printWithDelays("Game saved!", false);
        }
        savedPlace = place;
        if (TESTING) {
            System.out.println(redColor + "Function Complete: saveSpace" + resetColor);
        }
    }

    public static void loadSave() throws InterruptedException { //load saved game command
        if (TESTING) {
            System.out.println("Running function: loadSave");
        }
        if (getSavedPlace() == null) {
            playerCreated = false;
            Player.playerStart();
        } else {
            playTime.startClock();
            GameSaveSerialization.saveGame();
            InventoryManager.setStatsToHighestInInventory();
            switch (getSavedPlace()) {
                case "SpawnRoom" ->
                    SpawnRoom.startRoom();
                case "OpenWorld" ->
                    OpenWorld.startRoom();
                case "Village" ->
                    Village.startRoom();
                case "Meadow Dungeon" -> {
                    MeadowDungeon.startRoom("Meadow Dungeon", "Meadow");
                    return;
                }
                case "Dark Forest Dungeon" -> {
                    DarkForestDungeon.startRoom("Dark Forest Dungeon", "Dark Forest");
                    return;
                }
                case "Mountain Cave Dungeon" -> {
                    MountainCaveDungeon.startRoom("Mountain Cave Dungeon", "Mountain Cave");
                    return;
                }
                case "Mountain Top Dungeon" -> {
                    MountainTopDungeon.startRoom("Mountain Top Dungeon", "Mountain Top");
                    return;
                }
                case "Desert Oasis Dungeon" -> {
                    DesertOasisDungeon.startRoom("Desert Oasis Dungeon", "Desert Oasis");
                    return;
                }
                case "Desert Plains Dungeon" -> {
                    DesertPlainsDungeon.startRoom("Desert Plains Dungeon", "Desert Plains");
                    return;
                }
                case "Desert Pyramid Dungeon" -> {
                    DesertPyramidDungeon.startRoom("Desert Pyramid Dungeon", "Desert Pyramid");
                    return;
                }
                case "Ocean Kingdom Dungeon" -> {
                    OceanKingdomDungeon.startRoom("Ocean Kingdom Dungeon", "Ocean Kingdom");
                    return;
                }
                default ->
                    startMenu();
            }
        }
        if (TESTING) {
            System.out.println(redColor + "Function Complete: loadSave" + resetColor);
        }
    }

    public static void wipeSave() throws InterruptedException { //wipe save command
        if (TESTING) {
            System.out.println("Running function: wipeSave");
        }
        playerCreated = false;
        savedPlace = null;
        gameComplete = false;
        Dungeon.resetedAfterWin = false;
        Room.reset("all");
        Player.setName(null);
        buildDungeons();
        PromptEngine.aiGenerationEnabled = false;
        PromptEngine.userAPIKey = null;
        Main.playTime.setSavedTime(0);
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
        if (TESTING) {
            System.out.println(redColor + "Function Complete: wipeSave" + resetColor);
        }
    }

    public static String getSavedPlace() { //get the saved place
        return savedPlace;
    }

    public static boolean hasSave() { // Check if there is a save
        // Check if getSavedPlace() is not null
        // Check if the file game_save.txt exists
        File saveFile = new File(GameSaveSerialization.filePath);
        return saveFile.exists() || getSavedPlace() != null || Player.getName() != null;
    }

    public static void checkSave(String place) throws InterruptedException { //check if there is a save and if that save is where you currently are
        if (TESTING) {
            System.out.println("Running function: checkSave");
        }
        if (!hasSave() || !getSavedPlace().equals(place)) {
            saveSpace(place);
            GameSaveSerialization.saveGame();
        }
        if (TESTING) {
            System.out.println(redColor + "Function Complete: checkSave" + resetColor);
        }
    }

    public static void start() throws InterruptedException { //start the game
        if (TESTING) {
            System.out.println("Running function: start");
        }
        TextEngine.clearScreen();
        if (hasSave() && Player.getName() != null && !"null".equals(Player.getName())) {
            promptLoadSavedGame();
        } else if (playerCreated && Player.getName() != null && !"null".equals(Player.getName())) {
            saveSpace("SpawnRoom");
            playTime.startClock();
            loadSave();
        } else {
            Player.playerStart();
        }
        if (TESTING) {
            System.out.println(redColor + "Function Complete: start" + resetColor);
        }
    }

    private static void promptLoadSavedGame() throws InterruptedException { //prompt to load saved game
        if (TESTING) {
            System.out.println("Running function: promptLoadSavedGame");
        }
        displaySaveInfo();
        TextEngine.printWithDelays("Would you like to load your saved game? (" + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor + ") ", true);
        command = console.readLine();
        if (command.toLowerCase().equals("no")) {
            confirmWipeSave();
        } else {
            loadSave();
        }
        if (TESTING) {
            System.out.println(redColor + "Function Complete: promptLoadSavedGame" + resetColor);
        }
    }

    private static void confirmWipeSave() throws InterruptedException { //confirm to wipe save
        if (TESTING) {
            System.out.println("Running function: confirmWipeSave");
        }
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
        if (TESTING) {
            System.out.println(redColor + "Function Complete: confirmWipeSave" + resetColor);
        }
    }

    public static void printStatus() { //print the status of the player
        if (TESTING) {
            System.out.println("Running function: printStatus");
        }
        if (getSavedPlace() != null) {
            TextEngine.printNoDelay(Player.getName() + "'s adventure through the " + getSavedPlace(), false);
        } else {
            TextEngine.printNoDelay(Player.getName() + "'s adventure", false);
        }
        //TextEngine.printNoDelay("Health: " + healthColor+Player.getHealth()+resetColor, false);
        Player.drawHealthBar();
        //TextEngine.printNoDelay("Gold: " + Player.getGold(), false);
        System.out.println();
        if (TESTING) {
            System.out.println(redColor + "Function Complete: printStatus" + resetColor);
        }
    }

    public static void screenRefresh() throws InterruptedException { //refresh the screen
        if (TESTING) {
            System.out.println("Running function: screenRefresh");
        }
        TextEngine.clearScreen();
        printStatus();
        if (TESTING) {
            System.out.println(redColor + "Function Complete: screenRefresh" + resetColor);
        }
    }

    public static String getOS_NAME() { //get the os name
        if (TESTING) {
            System.out.println("Running function: getOS_NAME");
        }
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
        if (TESTING) {
            System.out.println("Running function: displaySaveInfo");
        }
        TextEngine.printNoDelay("Name: " + Player.getName(), false);
        TextEngine.printNoDelay("Play time: " + playTime.returnTime(), false);
        TextEngine.printNoDelay("Location: " + getSavedPlace(), false);
        TextEngine.printNoDelay("Health: " + Player.getHealth() + "/" + Player.getMaxHealth(), false);
        TextEngine.printNoDelay("Gold: " + Player.getGold(), false);
        TextEngine.printNoDelay("Completed Dungeons: " + Dungeon.completedDungeons, false);
        if (TESTING) {
            System.out.println(redColor + "Function Complete: displaySaveInfo" + resetColor);
        }
    }
}
