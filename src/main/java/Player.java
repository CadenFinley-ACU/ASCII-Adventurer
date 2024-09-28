import java.io.Console;
import java.util.Dictionary;
import java.util.Hashtable;
/**
 * Template
 * SE374 F24
 * Final Project
 * Caden Finley
 * Albert Tucker
 * Grijesh Shrestha
 */
//todo make inventory and health management
public class Player {
    private static int health=100;
    private static Dictionary<String, Integer> inventory = new Hashtable<>();
    private static String name;
    private final static Console console = System.console();
    private static String command;
    private static String ignore;

    public String getName(){
        return String.valueOf(name);
    }
    public void setName(String name){
            Player.name = name;
    }
    public static int getHealth(){
        return health;
    }
    public static void changeHealth(int change){
        health += change;
    }
    public void getInventory() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public static void playerCreate() throws InterruptedException{
        Player player = new Player();
        TextEngine.printWithDelays("Welcome to the game! What is your name hero?",true);
        ignore = console.readLine();
        command = console.readLine();
        player.setName(command);
        TextEngine.clearScreen();
        TextEngine.printWithDelays("Welcome "+player.getName()+"!",false);
        TextEngine.printWithDelays("There are a few quick things to know\nat any point you can type settings to change the text speed.",false);
        TextEngine.printWithDelays("You can also type exit to leave the game at any time.",false);
        TextEngine.printWithDelays("You can type inventory to see your inventory and health.",false);
        TextEngine.printWithDelays("You can type help to see these commands again.",false);
        TextEngine.printWithDelays("Make sure you always press enter when prompted to! (press enter to continue)",false);
        ignore = console.readLine();
        TextEngine.printWithDelays("Good luck! (press enter to continue)",false);
        ignore = console.readLine();
        TextEngine.clearScreen();
        Game.playerStart (player);
    }
}
