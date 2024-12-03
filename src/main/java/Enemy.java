
import java.io.Console;
import java.util.Map;

/**
 * The Enemy class represents various enemies in the game and their damage
 * values. It provides functionality to initialize enemy damage values based on
 * the game state.
 *
 * ASCIIADVENTURER Caden Finley Albert Tucker Grijesh Shrestha
 *
 * @author ASCIIADVENTURERS
 * @version 1.0
 */
public class Enemy {

    public final static Console console = System.console();
    public static String command;
    public static Map<String, Integer> enemyDamageValues;

    /**
     * This method initializes the enemyDamageValues map with the damage values
     * for each enemy. If the dungeon has been reset after a win and the game is
     * complete, the enemies are powered up with higher damage values.
     * Otherwise, the enemies have their normal damage values.
     */
    public static void createEnemies() {
        enemyDamageValues = null;
        if (Dungeon.resetedAfterWin && GameEngine.gameComplete) {
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

    /**
     * This method resets the enemyDamageValues
     */
    public static void resetEnemies() {
        createEnemies();
    }

    /**
     * Spawns enemies of a given type and quantity, and returns the total damage
     * dealt by the enemies.
     *
     * @param type The type of enemy to spawn.
     * @param quantity The number of enemies to spawn.
     * @return The total damage dealt by the enemies as a negative integer.
     * @throws InterruptedException If the thread is interrupted while printing
     * text with delays.
     */
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
        int damage = 0 - (enemyDamageValues.get(type) * quantity);
        if (damage > -1) {
            damage = -1;
        }
        return damage;
    }

    /**
     * Runs the spawn enemy sequence and calculates the damage taken.
     *
     * @param type The type of enemy.
     * @param quantity The number of enemies.
     * @return The damage taken.
     * @throws InterruptedException If the thread is interrupted.
     */
    public static int runSpawnEnemy(String type, int quantity) throws InterruptedException {
        String brightRedStart = "\033[1;31m"; // Start bright red text
        String brightRedEnd = "\033[0m"; // Reset formatting
        String space = "     ";
        if (quantity > 1) {
            TextEngine.printWithDelays(space + brightRedStart + "You run from the " + quantity + " " + type + "s!" + brightRedEnd, false);
        } else {
            TextEngine.printWithDelays(space + brightRedStart + "You run from the " + type + "!" + brightRedEnd, false);
        }
        int damage = 0 - (enemyDamageValues.get(type) * quantity) / 2;
        if (damage < 1) {
            damage = 1;
        }
        checkhealth(type, quantity, false);
        return damage;
    }

    /**
     * Checks the player's health and determines the outcome of a fight or
     * escape attempt.
     *
     * @param type The type of enemy.
     * @param quantity The number of enemies.
     * @param fight A boolean indicating whether the player is fighting (true)
     * or escaping (false).
     * @throws InterruptedException If the thread is interrupted during the
     * delay in printing text.
     */
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
            if (!(Player.getHealth() <= ((enemyDamageValues.get(type) * quantity) / 2) - Player.getDamageCalc())) {
                // Print victory message in green
                if (quantity > 1) {
                    TextEngine.printWithDelays(space + brightGreenStart + "You escaped the " + quantity + " " + type + "s!" + brightGreenEnd, false);
                } else {
                    TextEngine.printWithDelays(space + brightGreenStart + "You escaped the " + type + "!" + brightGreenEnd, false);
                }
            }
        }
    }

    public static void bossFight(String boss) throws InterruptedException {
        String brightRedStart = "\033[1;31m"; // Start bright red text
        String brightRedEnd = "\033[0m"; // Reset formatting
        TextEngine.printWithDelays("You have entered the boss room!", false);
        TextEngine.printWithDelays("You are now fighting the " + brightRedStart + boss + brightRedEnd + "!", false);
        TextEngine.printWithDelays("You have 5 minutes to defeat the boss!", false);
        TextEngine.enterToNext();
        GameEngine.screenRefresh();
        bossFightLoop(boss);
    }

    private static void bossFightLoop(String boss) throws InterruptedException {
        ClockEngine timer = new ClockEngine("timer");
        int currentMaxBossHealth = enemyDamageValues.get(boss);
        int currentBossHealth = currentMaxBossHealth;
        int hit = 1;
        timer.startClock(60 * 1); //5 minutes
        while (true) { //bossfight loop
            drawRoom();
            System.out.println("Time Remaining: " + timer.returnTime());
            displayBossHealth(boss, currentBossHealth, currentMaxBossHealth);
            command = askBossCommand();
            switch (command) {
                case "attack" -> {
                    hit = 1;
                    int damage = Player.getDamage();
                    if (damage < 1) {
                        damage = 1;
                    }
                    TextEngine.printWithDelays("You attack the " + boss + "!", false);
                    int criticalHit = (int) (Math.random() * 10);
                    if (criticalHit == 0) {
                        damage *= 2;
                        TextEngine.printWithDelays("It was a critical hit!", false);
                    }
                    TextEngine.printWithDelays("You deal " + damage + " damage!", false);
                    currentBossHealth -= damage;
                    if (currentBossHealth <= 0) {
                        return;
                    }
                    TextEngine.enterToNext();
                }
                case "dodge" -> {
                    hit = (int) (Math.random() * 2);
                }
                case "heal" -> {
                    hit = 1;
                    Player.heal();
                }
            }
            if (!timer.isRunning()) {
                TextEngine.printWithDelays("You ran out of time!", false);
                TextEngine.printWithDelays("The " + boss + " has defeated you!", false);
                Player.changeHealth(-Player.getMaxHealth());
            }
            int bossHealthChange = (int) (Math.random() * 5);
            if (bossHealthChange == 0 && currentBossHealth < currentMaxBossHealth / 3) {
                if (hit == 0) {
                    TextEngine.printWithDelays("You tried to dodge an attack but the " + boss + " heals itself!", false);
                }
                currentBossHealth += enemyDamageValues.get(boss);
                TextEngine.printWithDelays("The " + boss + " heals itself for " + currentMaxBossHealth / 2 + " health!", false);
                TextEngine.enterToNext();
            } else {
                TextEngine.printWithDelays("The " + boss + " attacks you!", false);
                if (hit == 0) {
                    TextEngine.printWithDelays("You dodge the attack!", false);
                    TextEngine.enterToNext();
                } else {
                    if (command.equals("dodge")) {
                        TextEngine.printWithDelays("You tried to dodge the attack but failed!", false);
                    }
                    float damageTaken = ((enemyDamageValues.get(boss) / (5 / 2)) - 2) - Player.getDefense();
                    Player.changeHealth((int) -damageTaken);
                }
            }
            GameEngine.screenRefresh();
        }
    }

    private static String askBossCommand() throws InterruptedException {
        String yellowColor = "\033[0;33m"; // Start yellow text 
        String resetColor = "\033[0m"; // Reset formatting
        TextEngine.printWithDelays("What would you like to do?", false);
        TextEngine.printWithDelays(yellowColor + "Attack, Dodge, or Heal" + resetColor, true);
        while (true) {
            command = TextEngine.parseCommand(Room.console.readLine().toLowerCase().trim(), new String[]{"attack", "dodge", "heal"});
            if (command.equals("attack") || command.equals("dodge") || command.equals("heal")) {
                break;
            } else {
                Dungeon.defaultDungeonArgs(command);
            }
        }
        return command;
    }

    private static void displayBossHealth(String boss, int health, int maxHealth) {
        int hearts = maxHealth / 20;
        String redColor = "\033[0;31m"; // Start red text
        String resetColor = "\033[0m"; // Reset formatting
        String healthColor;
        if (hearts == 0) {
            hearts = 1;
        }
        if (hearts > 25) {
            hearts = 25;
        }
        int filledBars = (int) Math.round(((double) health / maxHealth) * hearts);
        if (filledBars == 0 && health > 0) {
            filledBars = 1;
        }
        healthColor = redColor;
        StringBuilder bar = new StringBuilder("|");
        for (int i = 0; i < hearts; i++) {
            if (i < filledBars) {
                bar.append(healthColor).append("█").append(resetColor);
            } else {
                bar.append("_");
            }
        }
        bar.append("|");
        String healthBar = bar.toString();
        System.out.println(boss + " Health: " + health + " / " + maxHealth);
        System.out.println(healthBar);
        System.out.println();
    }

    private static void drawRoom() {
        int[][] roomsBeenTo = null;
        int[][] localDungeon = null;
        boolean mapRevealed = false;
        switch (Dungeon.currentDungeon) {
            case "Meadow" -> {
                roomsBeenTo = Dungeon.MeadowDungeon.roomsBeenTo;
                localDungeon = Dungeon.meadowDungeon;
                mapRevealed = Dungeon.MeadowDungeon.mapRevealed;
            }
            case "Dark Forest" -> {
                roomsBeenTo = Dungeon.DarkForestDungeon.roomsBeenTo;
                localDungeon = Dungeon.darkForestDungeon;
                mapRevealed = Dungeon.DarkForestDungeon.mapRevealed;
            }
            case "Mountain Cave" -> {
                roomsBeenTo = Dungeon.MountainCaveDungeon.roomsBeenTo;
                localDungeon = Dungeon.mountainCaveDungeon;
                mapRevealed = Dungeon.MountainCaveDungeon.mapRevealed;
            }
            case "Mountain Top" -> {
                roomsBeenTo = Dungeon.MountainTopDungeon.roomsBeenTo;
                localDungeon = Dungeon.mountainTopDungeon;
                mapRevealed = Dungeon.MountainTopDungeon.mapRevealed;
            }
            case "Desert Oasis" -> {
                roomsBeenTo = Dungeon.DesertOasisDungeon.roomsBeenTo;
                localDungeon = Dungeon.desertOasisDungeon;
                mapRevealed = Dungeon.DesertOasisDungeon.mapRevealed;
            }
            case "Desert Plains" -> {
                roomsBeenTo = Dungeon.DesertPlainsDungeon.roomsBeenTo;
                localDungeon = Dungeon.desertPlainsDungeon;
                mapRevealed = Dungeon.DesertPlainsDungeon.mapRevealed;
            }
            case "Desert Pyramid" -> {
                roomsBeenTo = Dungeon.DesertPyramidDungeon.roomsBeenTo;
                localDungeon = Dungeon.desertPyramidDungeon;
                mapRevealed = Dungeon.DesertPyramidDungeon.mapRevealed;
            }
            case "Ocean Kingdom" -> {
                roomsBeenTo = Dungeon.OceanKingdomDungeon.roomsBeenTo;
                localDungeon = Dungeon.oceanKingdomDungeon;
                mapRevealed = Dungeon.OceanKingdomDungeon.mapRevealed;
            }
        }
        DungeonGenerator.drawRoom(localDungeon, roomsBeenTo, Dungeon.currentPlayerPosition[0], Dungeon.currentPlayerPosition[1], 0, mapRevealed);
    }
}
