
import java.io.Console;
import java.util.Map;

/**
 * Room Class
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class Enemy {
    public final static Console console = System.console();
    public static String command;
    public static String ignore;
    private static final Map<String, Integer> enemyDamageValues= Map.of(
            "goblin", 5,
            "orc", 10, 
            "troll", 15,
            "dragon", 20,
            "bandit", 3
    );
    public static int spawnEnemy(String type,int quantity) throws InterruptedException { //return the total damage as negative int so that you can change health
        if(quantity > 1) {
            TextEngine.printWithDelays("You fight the " + type + "s!", false);
        } else {
            TextEngine.printWithDelays("You fight the " + type+"!", false);
        }
        checkhealth(type,quantity);
        return 0-(enemyDamageValues.get(type)*quantity);
    }
    private static void checkhealth(String type,int quantity) throws InterruptedException {
        if(Player.getHealth() <= 0){
            return;
        }
        if(quantity > 1) {
            TextEngine.printWithDelays("You beat the " + type + "s!", false);
        } else {
            TextEngine.printWithDelays("You beat the " + type+"!", false);
        }
    }
}


//