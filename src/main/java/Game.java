import java.io.Console;

/**
 * Room Engine /make this game engine and each room or area of rooms in their own individual classes
 * 
 * Text Adventure Game
 * SE374 F24
 * Final Project
 * Caden Finley
 * Albert Tucker
 * Grijesh Shrestha
 */
class Game {
    private final static Console console = System.console();
    private static String command;
    @SuppressWarnings("unused")
    private static String ignore;
    public static boolean playerCreated = false;
    public static Player player;
    private static String savedPlace=null;
        public static void main(String[] args) throws InterruptedException{
            startMenu();
        }
        public static void playerStart(Player passedPlayer) throws InterruptedException{
            player = passedPlayer;
            playerCreated = true;
            America.startRoom();
        }
        public static void startMenu() throws InterruptedException {
            TextEngine.printNoDelay("Adventure V1, by BarrettHall: Albert Tucker, Caden Finley, and Grijesh Shrestha",false);
            TextEngine.printNoDelay("Welcome Hero!",false);
            TextEngine.printWithDelays("What is your command: Start, Settings, Exit",true);
            while(true){
                ignore = console.readLine();
                command = console.readLine();
                switch (command.toLowerCase()) {
                    case "start":
                        TextEngine.clearScreen();
                        if(playerCreated) {
                            loadSave();
                        } else {
                            Player.playerCreate();
                        } 
                    case "settings":
                        TextEngine.clearScreen();
                        SettingsMenu.start();
                    case "exit":
                        TextEngine.printWithDelays("See ya next time!",false);
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
                        TextEngine.printWithDelays("I'm sorry, I don't understand that command.",true);
            }
        }     
    }
    public static void inGameDefaultTextHandling(String data) throws InterruptedException{
        switch(data){
            case "help":
                TextEngine.printWithDelays("You can type 'inventory' to see your health and inventory\nor 'settings' or 'exit' to return to the main menu.",true);
                return;
            case "inventory":
                player.getInventory();
                return;
            case "save":
                saveSpace(savedPlace);
                return;
            case "settings":
                SettingsMenu.start();
                TextEngine.clearScreen();
                return;
            case "exit":
                TextEngine.printWithDelays("Returning to main menu.", false);
                TextEngine.clearScreen();
                startMenu();      
            default:
            TextEngine.printWithDelays("I'm sorry, I don't understand that command.",true);
        }
    }
    public static void saveSpace(String place) throws InterruptedException{
        if (savedPlace != null){
            TextEngine.printWithDelays("Game saved!",false);
        }
        savedPlace = place;
    }
    public static void loadSave() throws InterruptedException{
        switch(savedPlace){
            case "America":
                America.startRoom();
            case "Canada":
                Canada.startRoom();
            default:
                startMenu();
        }
    }
    public static String getSavedPlace(){
        return savedPlace;
    }
    public static void checkSave(String place) throws InterruptedException{
        if(savedPlace == null){
            saveSpace(place);
        }
        else if(!getSavedPlace().equals(place)){
            saveSpace(place);
        }
    }
}
