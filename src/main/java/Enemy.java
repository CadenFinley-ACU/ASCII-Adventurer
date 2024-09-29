
import java.util.Map;

/**
 * Room Class
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class Enemy {
    private static final Map<String, Integer> enemyDamageValues= Map.of(
            "goblin", 5,
            "orc", 10,
            "troll", 15,
            "dragon", 20,
            "bandit", 3
    );
    public static int spawnEnemy(String type,int quantity,String action) throws InterruptedException { //return the total damage as negative int so that you can change health
        if(action.equals("fight")) {
            if(quantity > 1) {
                TextEngine.printWithDelays("You fight the " + type + "s", false);
            } else {
                TextEngine.printWithDelays("You fight the " + type, false);
            }
        }
        int totalDamage = 0;
        for(int i = 0; i < quantity; i++) {
            //spawn bandit
            totalDamage += enemyDamageValues.get(type);
        }
        return 0-totalDamage;
    }
}
