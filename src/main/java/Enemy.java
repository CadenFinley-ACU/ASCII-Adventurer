
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

    private static String yellowColor = "\033[0;33m"; // Start yellow text 
    private static String resetColor = "\033[0m"; // Reset formatting
    public final static Console console = System.console();
    public static String command;
    public static Map<String, Integer> enemyDamageValues;
    private static ClockEngine timer;

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
                    //enemies int = atk
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
                    //minibosses int = atk
                    Map.entry("Golem", 35), //dungeon 1
                    Map.entry("Forest Guardian", 45), //dungeon 2  //forest area

                    Map.entry("Elemental", 60), //dungeon 3
                    Map.entry("Minotaur", 65), //dungeon 4  //mountain area

                    Map.entry("Sphinx", 75), //dungeon 5
                    Map.entry("Cyclops", 80), //dungeon 6  //desert area
                    Map.entry("Medusa", 100), //dungeon 7

                    Map.entry("Leviathan", 150), //dungeon 8 //ocean area

                    //bosses int = atk health = atk * 3
                    Map.entry("Forest Giant", 20), //dungeon 1 
                    Map.entry("Forest Spirit", 30), //dungeon 2

                    Map.entry("Wyvern", 35), //dungeon 3
                    Map.entry("Ice Dragon", 45), //dungeon 4

                    Map.entry("Phoenix", 65), //dungeon 5
                    Map.entry("Giant Scorpion", 70), //dungeon 6
                    Map.entry("Giant Sand Worm", 80), //dungeon 7

                    Map.entry("Kraken", 130) //dungeon 8
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
        int damage = 0 - ((enemyDamageValues.get(type) * quantity) - Player.getDamageCalc());
        if (damage > -1) {
            damage = -1;
        }
        return damage;
    }

    /**
     *
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
        int damage = 0 - (((enemyDamageValues.get(type) * quantity) / 2) - Player.getDamageCalc() / 2);
        if (damage >= 0) {
            damage = -1;
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

    public static boolean bossFight(String boss) throws InterruptedException {
        String brightRedStart = "\033[1;31m"; // Start bright red text
        String brightRedEnd = "\033[0m"; // Reset formatting
        TextEngine.printWithDelays("You have entered the boss room!", false);
        TextEngine.printWithDelays("You are now fighting the " + brightRedStart + boss + brightRedEnd + "!", false);
        TextEngine.printWithDelays("You have 3 minutes to defeat the boss!", false);
        TextEngine.enterToNext();
        GameEngine.screenRefresh();
        return bossFightLoop(boss);
    }

    private static boolean bossFightLoop(String boss) throws InterruptedException {
        Enemy trigger = new Enemy();
        timer = new ClockEngine("timer", trigger);
        int heals = 3;
        int currentMaxBossHealth = enemyDamageValues.get(boss) * 3;
        int currentBossHealth = currentMaxBossHealth;
        int currentBossDamage = enemyDamageValues.get(boss);
        int hit = 1;
        int bossAnger = 10;
        float attackMultiplier = 1.0f;
        timer.startClock(3 * 60); //3 minutes
        trigger.startBossTrigger(boss);
        while (true) { //bossfight loop
            if (!timer.isRunning()) {
                break;
            }
            drawRoom();
            if (bossAnger < 0) {
                bossAnger = 0;
            }
            displayBossHealth(boss, currentBossHealth, currentMaxBossHealth);
            TextEngine.printWithDelays("What would you like to do?", false);
            TextEngine.printWithDelays(yellowColor + "Attack, Dodge, or Heal" + resetColor, true);
            while (true) {
                command = TextEngine.parseCommand(Room.console.readLine().toLowerCase().trim(), new String[]{"attack", "dodge", "heal"});
                if (!timer.isRunning()) {
                    break;
                }
                if (command.equals("attack") || command.equals("dodge") || command.equals("heal")) {
                    break;
                } else {
                    Dungeon.defaultDungeonArgs(command);
                }
            }
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
                        bossAnger--;
                        TextEngine.printWithDelays("It was a critical hit!", false);
                    }
                    TextEngine.printWithDelays("You deal " + damage + " damage!", false);
                    currentBossHealth -= damage;
                    if (currentBossHealth <= 0) {
                        timer.stopClock();
                        return true;
                    }
                    TextEngine.enterToNext();
                }
                case "dodge" -> {
                    hit = (int) (Math.random() * 2);
                }
                case "heal" -> {
                    if (Player.canHeal()) {
                        Player.heal();
                        hit = 1;
                        bossAnger--;
                    } else {
                        hit = (int) (Math.random() * 2);
                        command = "dodge";
                    }
                }
            }
            if (!timer.isRunning()) {
                break;
            }
            if (bossAnger <= 1) {
                attackMultiplier = 1.0f;
                TextEngine.printWithDelays("The " + boss + " is enraged!", false);
            }
            int bossHealthChange = (int) (Math.random() * 5);
            if (bossHealthChange == 0 && currentBossHealth < currentMaxBossHealth / 4 && heals > 0) {
                if (hit == 0) {
                    TextEngine.printWithDelays("You tried to dodge an attack but the " + boss + " heals itself!", false);
                }
                TextEngine.printWithDelays("The " + boss + " heals itself for " + currentMaxBossHealth / 3 + " health!", false);
                currentBossHealth += currentMaxBossHealth / 3;
                heals--;
                attackMultiplier -= 0.2f;
                bossAnger++;
                TextEngine.enterToNext();
                if (!timer.isRunning()) {
                    break;
                }
            } else {
                TextEngine.printWithDelays("The " + boss + " attacks you!", false);
                if (hit == 0) {
                    TextEngine.printWithDelays("You dodge the attack!", false);
                    TextEngine.printWithDelays("You gained back 15 seconds!", false);
                    timer.addTimeToTimerInSeconds(15);
                    bossAnger--;
                    TextEngine.enterToNext();
                    if (!timer.isRunning()) {
                        break;
                    }
                } else {
                    if (command.equals("dodge")) {
                        TextEngine.printWithDelays("You tried to dodge the attack but failed!", false);
                    }
                    int attackType = (int) (Math.random() * bossAnger);
                    float damageTaken = (currentBossDamage * attackMultiplier) - Player.getDefense();
                    if (damageTaken < 1.0f) {
                        damageTaken = 1.0f;
                    }
                    switch (attackType) {
                        case 0 -> {
                            TextEngine.printWithDelays("The " + boss + " uses a powerful attack!", false);
                            damageTaken *= 1.3f;
                        }
                        case 1 -> {
                            TextEngine.printWithDelays("The " + boss + " missed!", false);
                            damageTaken = 0.0f;
                        }
                        default -> {
                            TextEngine.printWithDelays("The " + boss + " uses a normal attack!", false);
                            damageTaken *= 0.9f;
                        }
                    }
                    if (!timer.isRunning()) {
                        break;
                    }
                    if (Player.getHealth() - (int) damageTaken <= 0) {
                        timer.stopClock();
                    }
                    Player.changeHealth((int) -damageTaken);
                }
            }
            if (!timer.isRunning()) {
                break;
            }
            GameEngine.screenRefresh();
        }
        timer.stopClock();
        return false;
    }

    private static void displayBossHealth(String boss, int health, int maxHealth) {
        int hearts = maxHealth / 20;
        String redColor = "\033[0;31m"; // Start red text
        String healthColor;
        if (hearts == 0) {
            hearts = 1;
        }
        if (hearts > 50) {
            hearts = 50;
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
        System.out.print(boss + " Health: " + health + " / " + maxHealth);
        System.out.println("        Time Remaining: " + timer.returnTime());
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

    public static void playerDefeat(String boss) throws InterruptedException {
        TextEngine.printWithDelays("\nYou ran out of time!", false);
        TextEngine.printWithDelays("The " + boss + " has defeated you!", false);
        TextEngine.printNoDelay("\033[1;33m" + "Press Enter to continue" + resetColor, false);
    }

    private void startBossTrigger(String boss) {
        new Thread(() -> {
            while (timer.isRunning()) {
                try {
                    synchronized (this) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Thread Interrupted");
                }
            }
            try {
                playerDefeat(boss);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
