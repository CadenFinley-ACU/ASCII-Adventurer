
import java.io.Console;
import java.io.File;
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

    public static void main(String[] args) throws InterruptedException { //main game start
        Dungeon.generateDungeons();
        GameSaveSerialization.loadGameSave();
        createGameItems();
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

        InventoryManager.createItem("potion", "heart container", 10);

        InventoryManager.createItem("key", "key", 0);
    }

    public static void startMenu() throws InterruptedException { //main menu and sstart menu text
        TextEngine.clearScreen();
        splashScreen();
        TextEngine.printNoDelay("               by: Albert Tucker, Caden Finley, and Grijesh Shrestha", false);
        System.out.println();
        if (hasSave() && Player.getName() != null) {
            TextEngine.printNoDelay("Welcome " + Player.getName() + "!", false);
        } else {
            TextEngine.printNoDelay("Welcome Hero!", false);
        }
        TextEngine.printWithDelays("What is your command: " + yellowColor + "Start" + resetColor + ", " + yellowColor + "Settings" + resetColor + ", " + yellowColor + "Exit" + resetColor, true);
        handleMenuCommands();
    }

    private static void handleMenuCommands() throws InterruptedException { //main menu command handling
        while (true) {
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
                default ->
                    TextEngine.printWithDelays("I'm sorry, I don't understand that command.", true);
            }
        }
    }

    private static void splashScreen() {
        String brightBoldEnd = "\033[0m"; // end color
        String darkPurpleStart = "\033[38;2;255;165;0m"; // ACU Purple
        System.err.println(
                darkPurpleStart
                + 

     
           

    
    
        
             

    
     
        
           
                
           
         
           

    
    
        
              
              
        
            
              
             
            
              
          
          
         
                         
          
           
             

    
    
        
              
        
              
          
          
                         
             

    
    
        
              
        
            
                    
        
                  
        
             

    
    
        
            
                
              

    
             
            
           

    
     
           

    
    
        
        
          
          
             

    
    
        
            
                      
                
                      
                
                      
                
                      
                
                      
                
                      
                
                      
                
                      
                
                      
                
                      
                
                      
                
                      
                  
            
            
              
        
              
                
             

    
      
        
         
            
                
              

    
    
                                                         
              
                                                                 
              
        
                 
              
             

    
     
             

    
     
             

    
    
        
                  
            
            
                 
                      
                
                      
                   
            
                  
            
                  
            
            
                
                
                 
                   
            
                  
            
                  
            
                  
            
                  
              
              

    
    
        
           
           
           
        
          
             

    
       
          
              

    
    
        
        
         
        
             

    
    
                                                         
              
                                                                 
              
             

    
      """
                                   _    ____   ____ ___ ___                                     
                                  / \\  / ___| / ___|_ _|_ _|                                    
                                 / _ \\ \\___ \\| |    | | | |                                     
                                / ___ \\ ___) | |___ | | | |                                     
                               /_/ _ \\_\\____/ \\____|___|___|   _ _____ _   _ ____  _____ ____  
                                  / \\  |  _ \\ \\   / / ____| \\ | |_   _| | | |  _ \\| ____|  _ \\ 
                                 / _ \\ | | | \\ \\ / /|  _| |  \\| | | | | | | | |_) |  _| | |_) |
                                / ___ \\| |_| |\\ V / | |___| |\\  | | | | |_| |  _ <| |___|  _ < 
                               /_/   \\_\\____/  \\_/  |_____|_| \\_| |_|  \\___/|_| \\_\\_____|_| \\_\\""" + brightBoldEnd);
    }
    

    private static void displayHelp() throws InterruptedException { //main menu help command
        if (getSavedPlace().equals("OpenWorld")) {
            TextEngine.printWithDelays("Things you could say:\n" +yellowColor+ "stats" +resetColor+" to see your stats\n" +yellowColor+ "inventory" +resetColor+ " to see your inventory\n" +yellowColor+ "heal" +resetColor+ " to heal you health using any available healing potions\n" +yellowColor+ "settings" +resetColor+ " or type " +yellowColor+ "save" +resetColor+ " to save\n" +yellowColor+ "map" +resetColor+ " to see the map\n" +yellowColor+ "exit" +resetColor+ " to return to the main menu.", true);
        } else {
            TextEngine.printWithDelays("Things you could say:\n" +yellowColor+ "stats" +resetColor+ " to see your stats\n" +yellowColor+ "inventory" +resetColor+ " to see your inventory\n" +yellowColor+ "heal" +resetColor+ " to heal you health using any available healing potions\n" +yellowColor+ "settings" +resetColor+ " or type " +yellowColor+ "save" +resetColor+ " to save\n" +yellowColor+ "exit" +resetColor+ " to return to the main menu.", true);
        }
    }

    private static void exitGame() throws InterruptedException {   //exit game command
        GameSaveSerialization.saveGame();
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
        switch (data) {
            case "help" ->
                displayInGameHelp();
            case "inventory" ->
                Player.openInventory();
            case "settings" ->
                SettingsMenu.start();
            case "save" ->
                checkSave(getSavedPlace());
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
                if (getSavedPlace().equals("OpenWorld")) {
                    Player.printMap();
                } else {
                    TextEngine.printWithDelays("You cannot use that command here.", true);
                }
            }
            default ->
                invalidCommandWithBuffer();
        }
    }

    public static void invalidCommandWithBuffer() throws InterruptedException {
        TextEngine.printWithDelays("I'm sorry, I don't understand that command.", true);
    }

    public static void invalidCommand() throws InterruptedException {
        TextEngine.printWithDelays("I'm sorry, I don't understand that command.", false);
    }

    private static void displayInGameHelp() throws InterruptedException { //in game help command
        if (getSavedPlace().equals("Dungeon")) {
            TextEngine.printWithDelays("You can type " +yellowColor+ "restart" +resetColor+ " to restart the dungeon", false);
        }
        if (getSavedPlace().equals("OpenWorld")) {
            TextEngine.printWithDelays("Things you could say:\n" +yellowColor+ "stats" +resetColor+ " to see your stats\n" +yellowColor+ "inventory" +resetColor+ " to see your inventory\n" +yellowColor+ "heal" +resetColor+ " to heal you health using any available healing potions\n" +yellowColor+ "settings" +resetColor+ " or type " +yellowColor+ "save" +resetColor+ " to save\n" +yellowColor+ "map" +resetColor+ " to see the map\n" +yellowColor+ "exit" +resetColor+ " to return to the main menu.", true);
        } else {
            TextEngine.printWithDelays("Things you could say:\n" +yellowColor+ "stats" +resetColor+ " to see your stats\n" +yellowColor+ "inventory" +resetColor+ " to see your inventory\n" +yellowColor+ "heal" +resetColor+ " to heal you health using any available healing potions\n" +yellowColor+ "settings" +resetColor+ " or type " +yellowColor+ "save" +resetColor+ " to save\n" +yellowColor+ "exit" +resetColor+ " to return to the main menu.", true);
        }

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
        Room.reset("all");
        Player.setName(null);
        GameSaveSerialization.saveGame();
    }

    public static String getSavedPlace() { //get the saved place
        return savedPlace;
    }

    public static boolean hasSave() { // Check if there is a save
        // Check if getSavedPlace() is not null
        // Check if the file game_save.txt exists
        File saveFile = new File("game_save.txt");
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
        if (hasSave() && Player.getName() != null) {
            promptLoadSavedGame();
        } else if (playerCreated && Player.getName() != null) {
            saveSpace("SpawnRoom");
            loadSave();
        } else {
            Player.playerStart();
        }
    }

    private static void promptLoadSavedGame() throws InterruptedException { //prompt to load saved game
        TextEngine.printWithDelays("Would you like to load your saved game? (" +yellowColor+ "yes" +resetColor+ " or " +yellowColor+ "no" +resetColor+ ") ", true);
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
        TextEngine.printWithDelays("All data will be wiped if you proceed. (" +yellowColor+ "yes" +resetColor+ " or " +yellowColor+ "no" +resetColor+ ") ", false);
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
        TextEngine.printNoDelay(Player.getName(), false);
        TextEngine.printNoDelay("Health: " + Player.getHealth(), false);
        if (getSavedPlace() != null) {
            TextEngine.printNoDelay("Location: " + getSavedPlace(), false);
        }
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
