
import java.io.Console;
import java.util.Map;

/**
 * Enemy Class
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class Enemy {
    public final static Console console = System.console();
    public static String command;
    public static String ignore;
    private static final Map<String, Integer> enemyDamageValues = Map.copyOf(Map.ofEntries( //damage values for each enemy
            Map.entry("goblin", 5),
            Map.entry("orc", 10),
            Map.entry("troll", 15),
            Map.entry("dragon", 20),
            Map.entry("bandit", 3),
            Map.entry("skeleton", 5),
            Map.entry("zombie", 7),
            Map.entry("ghost", 10),
            Map.entry("demon", 15),
            Map.entry("vampire", 20),
            Map.entry("werewolf", 25),
            Map.entry("witch", 30),
            Map.entry("giant", 35),
            Map.entry("slime", 2),
            Map.entry("mimic", 5),
            Map.entry("golem", 10),
            Map.entry("elemental", 15),
            Map.entry("wyvern", 20),
            Map.entry("hydra", 25),
            Map.entry("kraken", 30),
            Map.entry("phoenix", 35),
            Map.entry("chimera", 40),
            Map.entry("minotaur", 45),
            Map.entry("cyclops", 50),
            Map.entry("medusa", 55),
            Map.entry("sphinx", 60),
            Map.entry("leviathan", 65),
            Map.entry("behemoth", 70),
            Map.entry("tiamat", 75),
            Map.entry("fenrir", 80)
    ));
    public static int spawnEnemy(String type,int quantity) throws InterruptedException { //return the total damage as negative int so that you can change health
        if(quantity > 1) {
            TextEngine.printWithDelays("You fight the " + type + "s!", false);
        } else {
            TextEngine.printWithDelays("You fight the " + type+"!", false);
        }
        checkhealth(type,quantity);
        TextEngine.enterToNext();
        return 0-(enemyDamageValues.get(type)*quantity);
    }
    private static void checkhealth(String type,int quantity) throws InterruptedException { //check the health of the player
        if(Player.getHealth() <= 0){
            return;
        }
        if(quantity > 1) {
            TextEngine.printWithDelays("You beat the " + type + "s!", false);
        } else {
            TextEngine.printWithDelays("You beat the " + type+"!", false);
        }
        Player.changeGold(enemyDamageValues.get(type)*quantity);
    }
}


//