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
class GameStart {
    private final static Console console = System.console();
    private static String command;
    @SuppressWarnings("unused")
    private static String ignore;
    public static boolean playerCreated = false;
    public static Player player;
        public static void main(String[] args) throws InterruptedException{
            startMenu();
        }
        public static void playerStart(Player passedPlayer) throws InterruptedException{
            player = passedPlayer;
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
                            //America.startRoom();  
                            //create game saves somehow 
                        } else {
                            playerCreated = true;
                            Player.playerCreate();
                        } 
                    case "settings":
                        TextEngine.clearScreen();
                        SettingsMenu.start();
                    case "exit":
                        TextEngine.printWithDelays("See ya next time!",false);
                        TextEngine.clearScreen();
                        System.exit(0);
                    default:
                        TextEngine.printWithDelays("I'm sorry, I don't understand that command.",true);
            }
        }     
    }
    public static void defaultTextHandling(String data) throws InterruptedException{
        switch(data){
            case "help":
                TextEngine.printWithDelays("You can type inventory to see your health and inventory\nor exit to return to the main menu.",true);
                return;
            case "inventory":
                player.getInventory();
                return;
            case "exit":
                TextEngine.printWithDelays("Returning to main menu.", false);
                TextEngine.clearScreen();
                startMenu();      
            default:
            TextEngine.printWithDelays("I'm sorry, I don't understand that command.",true);
        }

    }
}
