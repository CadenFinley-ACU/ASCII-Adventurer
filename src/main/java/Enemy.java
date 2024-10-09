
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
    private static final Map<String, Integer> enemyDamageValues = Map.copyOf(Map.ofEntries( //name of enemy / damage values for each enemy
            //enemies
            Map.entry("Goblin", 5),
            Map.entry("Orc", 10),
            Map.entry("Troll", 15),
            Map.entry("Bandit", 3),
            Map.entry("Spider", 5),
            Map.entry("Giant Rat", 7),
            Map.entry("Skeleton", 5),
            Map.entry("Zombie", 7),
            Map.entry("Ghost", 10),
            Map.entry("Demon", 15),
            Map.entry("Vampire", 20),
            Map.entry("Werewolf", 25),
            Map.entry("Witch", 35),
            Map.entry("Giant", 40),
            Map.entry("Mummy", 30),
            Map.entry("Slime", 2),
            Map.entry("Mimic", 5),
            Map.entry("Gargoyle", 20),
            Map.entry("Sea Serpent", 50),
            Map.entry("Sea Monster", 60),
            Map.entry("Sea Witch", 55),
            Map.entry("Sea Dragon", 70),
            Map.entry("Sea Giant", 60),
            //minibosses
            Map.entry("Golem", 20), //dungeon 1
            Map.entry("Forest Guardian", 25), //dungeon 2  //forest area

            Map.entry("Elemental", 35), //dungeon 3
            Map.entry("Minotaur", 45), //dungeon 4  //mountain area

            Map.entry("Sphinx", 60), //dungeon 5
            Map.entry("Cyclops", 80), //dungeon 6  //desert area
            Map.entry("Medusa", 100), //dungeon 7

            Map.entry("Leviathan", 150), //dungeon 8 //ocean area

            //bosses
            Map.entry("Forest Giant", 30), //dungeon 1 
            Map.entry("Forest Spirit", 45), //dungeon 2

            Map.entry("Wyvern", 50), //dungeon 3
            Map.entry("Ice Dragon", 60), //dungeon 4

            Map.entry("Phoenix", 70), //dungeon 5
            Map.entry("Giant Scorpion", 100), //dungeon 6
            Map.entry("Giant Sand Worm", 110), //dungeon 7

            Map.entry("Kraken", 200) //dungeon 8
    ));

    public static int spawnEnemy(String type, int quantity) throws InterruptedException { // Return the total damage as negative int so that you can change health
        String brightRedStart = "\033[1;31m"; // Start bright red text
        String brightRedEnd = "\033[0m"; // Reset formatting
        String space = "     ";
        if (quantity > 1) {
            TextEngine.printWithDelays(space + brightRedStart + "You fight the " + quantity + " " + type + "s!" + brightRedEnd, false);
        } else {
            TextEngine.printWithDelays(space + brightRedStart + "You fight the " + type + "!" + brightRedEnd, false);
        }
        checkhealth(type, quantity);
        return 0 - (enemyDamageValues.get(type) * quantity);
    }

    private static void checkhealth(String type, int quantity) throws InterruptedException {
        String brightGreenStart = "\033[1;32m"; // Start bright green text
        String brightGreenEnd = "\033[0m"; // Reset formatting
        String space = "     ";

        // Check the player's health
        if (Player.getHealth() <= (enemyDamageValues.get(type) * quantity)-Player.getDamageCalc()) {
            return;
        }
        // Print victory message in green
        if (quantity > 1) {
            TextEngine.printWithDelays(space + brightGreenStart + "You beat the " + quantity + " " + type + "s!" + brightGreenEnd, false);
        } else {
            TextEngine.printWithDelays(space + brightGreenStart + "You beat the " + type + "!" + brightGreenEnd, false);
        }
        // Update player's gold
        Player.changeGold(enemyDamageValues.get(type) * quantity);
    }

}


//
