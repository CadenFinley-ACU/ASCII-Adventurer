
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
    private static Map<String, Integer> enemyDamageValues;

    public static void createEnemies() {
        enemyDamageValues = null;
        if (Dungeon.resetedAfterWin && Main.gameComplete) {
            //powered up enemies
            enemyDamageValues = Map.copyOf(Map.ofEntries( //name of enemy / damage values for each enemy
                    //enemies
                    Map.entry("Mountain Lion", 20),
                    Map.entry("Barbarian", 10),
                    Map.entry("Shark", 10),
                    Map.entry("Pirate", 20),
                    Map.entry("Scorpion", 15),
                    Map.entry("Goblin", 25),
                    Map.entry("Orc", 50),
                    Map.entry("Troll", 60),
                    Map.entry("Bandit", 15),
                    Map.entry("Spider", 25),
                    Map.entry("Giant Rat", 35),
                    Map.entry("Skeleton", 30),
                    Map.entry("Zombie", 25),
                    Map.entry("Ghost", 30),
                    Map.entry("Demon", 50),
                    Map.entry("Vampire", 50),
                    Map.entry("Werewolf", 60),
                    Map.entry("Witch", 40),
                    Map.entry("Giant", 50),
                    Map.entry("Mummy", 50),
                    Map.entry("Slime", 10),
                    Map.entry("Mimic", 10),
                    Map.entry("Gargoyle", 40),
                    Map.entry("Sea Serpent", 80),
                    Map.entry("Sea Monster", 100),
                    Map.entry("Sea Witch", 70),
                    Map.entry("Sea Dragon", 90),
                    Map.entry("Sea Giant", 80),
                    //minibosses
                    Map.entry("Golem", 100), //dungeon 1
                    Map.entry("Forest Guardian", 150), //dungeon 2  //forest area

                    Map.entry("Elemental", 200), //dungeon 3
                    Map.entry("Minotaur", 250), //dungeon 4  //mountain area
                    Map.entry("Sphinx", 300), //dungeon 5
                    Map.entry("Cyclops", 350), //dungeon 6  //desert area
                    Map.entry("Medusa", 400), //dungeon 7

                    Map.entry("Leviathan", 450), //dungeon 8 //ocean area

                    //bosses
                    Map.entry("Forest Giant", 450), //dungeon 1 
                    Map.entry("Forest Spirit", 500), //dungeon 2

                    Map.entry("Wyvern", 550), //dungeon 3
                    Map.entry("Ice Dragon", 600), //dungeon 4

                    Map.entry("Phoenix", 650), //dungeon 5
                    Map.entry("Giant Scorpion", 700), //dungeon 6
                    Map.entry("Giant Sand Worm", 750), //dungeon 7

                    Map.entry("Kraken", 800) //dungeon 8
            ));
        } else {
            enemyDamageValues = Map.copyOf(Map.ofEntries( //name of enemy / damage values for each enemy
                    //enemies
                    Map.entry("Mountain Lion", 10),
                    Map.entry("Barbarian", 5),
                    Map.entry("Shark", 5),
                    Map.entry("Pirate", 10),
                    Map.entry("Scorpion", 7),
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
                    Map.entry("Golem", 35), //dungeon 1
                    Map.entry("Forest Guardian", 45), //dungeon 2  //forest area

                    Map.entry("Elemental", 60), //dungeon 3
                    Map.entry("Minotaur", 65), //dungeon 4  //mountain area

                    Map.entry("Sphinx", 75), //dungeon 5
                    Map.entry("Cyclops", 80), //dungeon 6  //desert area
                    Map.entry("Medusa", 100), //dungeon 7

                    Map.entry("Leviathan", 150), //dungeon 8 //ocean area

                    //bosses
                    Map.entry("Forest Giant", 50), //dungeon 1 
                    Map.entry("Forest Spirit", 55), //dungeon 2

                    Map.entry("Wyvern", 70), //dungeon 3
                    Map.entry("Ice Dragon", 80), //dungeon 4

                    Map.entry("Phoenix", 90), //dungeon 5
                    Map.entry("Giant Scorpion", 100), //dungeon 6
                    Map.entry("Giant Sand Worm", 110), //dungeon 7

                    Map.entry("Kraken", 200) //dungeon 8
            ));
        }
    }

    public static void resetEnemies() {
        createEnemies();
    }

    public static int spawnEnemy(String type, int quantity) throws InterruptedException { // Return the total damage as negative int so that you can change health
        String brightRedStart = "\033[1;31m"; // Start bright red text
        String brightRedEnd = "\033[0m"; // Reset formatting
        String space = "     ";
        if (quantity > 1) {
            TextEngine.printWithDelays(space + brightRedStart + "You fight the " + quantity + " " + type + "s!" + brightRedEnd, false);
        } else {
            TextEngine.printWithDelays(space + brightRedStart + "You fight the " + type + "!" + brightRedEnd, false);
        }
        checkhealth(type, quantity, true);
        return 0 - (enemyDamageValues.get(type) * quantity);
    }

    public static int runSpawnEnemy(String type, int quantity) throws InterruptedException {
        String brightRedStart = "\033[1;31m"; // Start bright red text
        String brightRedEnd = "\033[0m"; // Reset formatting
        String space = "     ";
        if (quantity > 1) {
            TextEngine.printWithDelays(space + brightRedStart + "You run from the " + quantity + " " + type + "s!" + brightRedEnd, false);
        } else {
            TextEngine.printWithDelays(space + brightRedStart + "You run from the " + type + "!" + brightRedEnd, false);
        }
        if (quantity < 2) {
            quantity = 0;
        } else {
            quantity = 1;
        }
        checkhealth(type, quantity, false);
        int damage = enemyDamageValues.get(type) / 2 * quantity;
        if (damage < 1) {
            damage = 1;
        }
        return 0 - (damage);
    }

    private static void checkhealth(String type, int quantity, boolean fight) throws InterruptedException {
        String brightGreenStart = "\033[1;32m"; // Start bright green text
        String brightGreenEnd = "\033[0m"; // Reset formatting
        String space = "     ";
        if (fight) {
            // Check the player's health
            if (!(Player.getHealth() <= (enemyDamageValues.get(type) * quantity) - Player.getDamageCalc())) {
                // Print victory message in green
                if (quantity > 1) {
                    TextEngine.printWithDelays(space + brightGreenStart + "You beat the " + quantity + " " + type + "s!" + brightGreenEnd, false);
                } else {
                    TextEngine.printWithDelays(space + brightGreenStart + "You beat the " + type + "!" + brightGreenEnd, false);
                }
                // Update player's gold
                int goldChange = ((enemyDamageValues.get(type) / (5 / 2)) * quantity);
                if (goldChange < 1) {
                    goldChange = 1;
                }
                Player.changeGold(goldChange);
            }
        } else {
            // Check the player's health
            if (!(Player.getHealth() <= (enemyDamageValues.get(type) / 2 * quantity) - Player.getDamageCalc())) {
                // Print victory message in green
                if (quantity > 1) {
                    TextEngine.printWithDelays(space + brightGreenStart + "You escaped the " + quantity + " " + type + "s!" + brightGreenEnd, false);
                } else {
                    TextEngine.printWithDelays(space + brightGreenStart + "You escaped the " + type + "!" + brightGreenEnd, false);
                }
            }
        }
    }
}
