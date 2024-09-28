
import java.io.Console;
import java.util.HashMap;
import java.util.Map;

/**
 * Template SE374 F24 Final Project Caden Finley Albert Tucker Grijesh Shrestha
 */
//todo make inventory and health management
public class Player {

    private static int health;
    private static String name;
    public final static Console console = System.console();
    public static String command;
    public static String ignore;
    private static int inventorySize;;
    public static Map<String, Integer> inventory=new HashMap<>();
    public static InventoryManager manager= new InventoryManager();

    public static void playerStart() throws InterruptedException {
        health = 100;
        inventorySize=0;
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
    public static void openInventory() throws InterruptedException{
        manager.printInventory();
        
    }
    public static void putItem(String item, int amount) throws InterruptedException{
        if(inventory.size()<inventorySize){
            TextEngine.printWithDelays("You have no room in your inventory.",false);
            TextEngine.printWithDelays("You can only hold " + inventorySize + " items.",false);
            TextEngine.printWithDelays("You can drop items by typing 'drop' to make room.",false);
            TextEngine.printWithDelays("Press Enter to continue",false);
            return;
        }
        manager.put(item, amount);
    }

    private static void playerCreate() throws InterruptedException {
        TextEngine.printWithDelays("Welcome to the game! What is your name hero?",true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if (command != null && !command.isEmpty()) {
                if("exit".equals(command)){
                    Game.startMenu();  
                    TextEngine.clearScreen();
                }
                else{
                    setName(command);
                    break;
                }
            }else{
                TextEngine.printWithDelays("Please enter a name.",true);
            }
                      
        }
        TextEngine.clearScreen();
        TextEngine.printWithDelays("Welcome " + Player.getName() + "!",false);
        TextEngine.printWithDelays("There are a few quick things to know.\nat any point you can type settings to change the text speed.",false);
        TextEngine.printWithDelays("You can also type exit to leave the game at any time.",false);
        TextEngine.printWithDelays("You can type inventory to see your inventory and health.",false);
        TextEngine.printWithDelays("You can type help to see these commands again.",false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("Make sure you always press enter when prompted to! (press enter to continue)",false);
        ignore = console.readLine();
        TextEngine.printWithDelays("Good luck! (press enter to continue)",false);
        ignore = console.readLine();
        TextEngine.clearScreen();
        Game.playerCreated = true;
        Game.start();
    }
}
