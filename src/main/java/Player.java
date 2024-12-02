
import java.io.Console;
import java.util.HashMap;
import java.util.Map;

/**
 * ASCIIADVENTURER Caden Finley Albert Tucker Grijesh Shrestha
 *
 * This class represents a player in the ASCII Adventure game. It contains
 * attributes and methods to manage the player's state and actions.
 *
 * @author ASCIIADVENTURERS
 * @version 1.0
 */
public class Player {

    private static int health = 100;
    private static int maxHealth = 100;
    private static String name;
    public final static Console console = System.console();
    public static String command;
    public static String holdCommand;
    private static int inventorySize = 20;
    private static int gold;
    private static int damage = 0;
    private static int defense = 0;
    public static Map<String, Integer> inventory = new HashMap<>();
    public static int playerX = 0;
    public static int playerY = 0;
    public static String yellowColor = "\033[1;33m"; // yellow color
    public static String resetColor = "\033[0m"; // reset to default color
    public static String redColor = "\033[1;31m"; // red color
    public static String greenColor = "\033[1;32m"; // green color'
    public static String G = "\033[0;32m"; // deep grass color
    public static String b = "\033[1;34m"; // blue color
    public static String g = "\033[0;37m"; // bright gray color
    public static String s = "\033[0;33m"; // sand color
    public static String S = "\033[1;37m"; // snow color
    public static String R = "\033[0m"; // reset to default color
    public static String B = "\033[0;33m"; // brown color
    public static String healthColor = "";

    /**
     * Initializes the player with default values and starts the player.
     *
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public static void playerStart() throws InterruptedException { //start the player
        maxHealth = 100;
        health = maxHealth;
        damage = 0;
        defense = 0;
        gold = 20;
        inventorySize = 20;
        inventory.clear();
        playerCreate();
    }

    public static void debugStart() throws InterruptedException { //start the player with debug settings
        maxHealth = 10000;
        health = maxHealth;
        damage = 0;
        defense = 0;
        gold = 20000000;
        inventorySize = 200;
        name = "Debug!";
        GameEngine.playerCreated = true;
        GameEngine.TESTING = true;
        DungeonGenerator.testing = true;
        GameEngine.playTime.startClock(1);
        TextEngine.printNoDelay("Where do you want to spawn", false);
        TextEngine.printNoDelay(" 1:SpawnRoom", false);
        TextEngine.printNoDelay(" 2:OpenWorld", false);
        TextEngine.printNoDelay(" 3:Dungeon", false);
        TextEngine.printNoDelay(" 4:Village", false);
        TextEngine.printNoDelay("debug spawn: ", true);
        command = console.readLine();
        switch (command) {
            case "1" -> {
                GameEngine.saveSpace("SpawnRoom");
                GameEngine.loadSave();
            }
            case "2" -> {
                GameEngine.saveSpace("OpenWorld");
                GameEngine.loadSave();
            }
            case "3" -> {
                TextEngine.printNoDelay(" 1:Meadow\n 2:Dark Forest\n 3:Mountain Cave\n 4:Mountain Top\n 5:Desert Oasis\n 6:Desert Plains\n 7 Desert Pyramid\n 8:Ocean Kingdom", false);
                TextEngine.printNoDelay("debug dungeon: ", true);
                command = console.readLine();
                switch (command) {
                    case "1" -> {
                        Dungeon.MeadowDungeon.startRoom();
                    }
                    case "2" -> {
                        Dungeon.DarkForestDungeon.startRoom();
                    }
                    case "3" -> {
                        Dungeon.MountainCaveDungeon.startRoom();
                    }
                    case "4" -> {
                        Dungeon.MountainTopDungeon.startRoom();
                    }
                    case "5" -> {
                        Dungeon.DesertOasisDungeon.startRoom();
                    }
                    case "6" -> {
                        Dungeon.DesertPlainsDungeon.startRoom();
                    }
                    case "7" -> {
                        Dungeon.DesertPyramidDungeon.startRoom();
                    }
                    case "8" -> {
                        Dungeon.OceanKingdomDungeon.startRoom();
                    }
                    default -> {
                        GameEngine.saveSpace("SpawnRoom");
                        GameEngine.loadSave();
                    }
                }
            }
            case "4" -> {
                GameEngine.saveSpace("Village");
                GameEngine.loadSave();
            }
            case "5" -> {
                GameEngine.playTime.debugTime(359940);
                GameEngine.saveSpace("SpawnRoom");
                GameEngine.loadSave();
            }
            default -> {
                GameEngine.saveSpace("SpawnRoom");
                GameEngine.loadSave();
            }
        }

    }

    /**
     * Retrieves the name of the player.
     *
     * @return the name of the player, or the literal string "null" if the name
     * is not set or is empty
     */
    public static String getName() {
        if (name == null || name.isEmpty()) {
            return "null";
        }
        return name;
    }

    /**
     * Sets the name of the player.
     *
     * @param name the name to set
     */
    public static void setName(String name) { //set the name
        Player.name = name;
    }

    /**
     * Gets the current health of the player. If the health exceeds the maximum
     * health, it is set to the maximum health.
     *
     * @return the current health
     */
    public static int getHealth() { //get the health
        if (health > maxHealth) {
            health = maxHealth;
        }
        return health;
    }

    /**
     * Gets the maximum health of the player.
     *
     * @return the maximum health
     */
    public static int getMaxHealth() { //get the max health
        return maxHealth;
    }

    /**
     * Gets the current amount of gold the player has.
     *
     * @return the amount of gold
     */
    public static int getGold() { //get the gold
        return gold;
    }

    /**
     * Changes the inventory size by a specified amount.
     *
     * @param change the amount to change the inventory size by
     * @throws InterruptedException if the thread is interrupted
     */
    public static void changeInventorySize(int change) throws InterruptedException { //change the inventory size
        inventorySize += change;
        TextEngine.printWithDelays("Inventory size increased by " + change + " to " + inventorySize + "!", false);
        TextEngine.enterToNext();
    }

    /**
     * Gets the current inventory size.
     *
     * @return the inventory size
     */
    public static int getInventorySize() { //get the inventory size
        return inventorySize;
    }

    /**
     * Sets the inventory size to a specified value.
     *
     * @param size the size to set the inventory to
     */
    public static void hardSetInventorySize(int size) {
        inventorySize = size;
    }

    /**
     * Changes the health of the player by a specified amount. If the change is
     * negative, it is adjusted by the player's defense and damage. If the
     * health drops to zero or below, the player dies and the game ends.
     *
     * @param change the amount to change the health by
     * @throws InterruptedException if the thread is interrupted
     */
    public static void changeHealth(int change) throws InterruptedException {
        if (change == 0) {
            TextEngine.enterToNext();
            return;
        }
        String brightRedStart = "\033[1;31m";
        String brightGreenStart = "\033[1;32m";
        String brightEnd = "\033[0m";
        String space = "     ";
        int damageCalc = (defense + damage - (damage / 3));

        if (change < 0) {
            change += damageCalc;
            if (change >= 0) {
                change = -1;
            }
            TextEngine.printWithDelays(space + brightRedStart + "You took " + change + " damage!" + brightEnd, false);
        } else {
            change = Math.min(change, maxHealth - health);
            if (change != 0) {
                TextEngine.printWithDelays(space + brightGreenStart + "You gained " + change + " health!" + brightEnd, false);
            }
        }

        health += change;
        if (health > maxHealth) {
            health = maxHealth;
        }

        if (health <= 0) {
            TextEngine.printWithDelays(space + brightRedStart + "You have died!" + brightEnd, false);
            TextEngine.printWithDelays(space + brightRedStart + "Game Over!" + brightEnd, false);
            TextEngine.enterToNext();
            GameEngine.wipeSave();
            GameEngine.startMenu();
        }

        TextEngine.enterToNext();
    }

    /**
     * Heals the player to full health if not already at max health.
     *
     * @return true if the player was healed, false if already at max health
     */
    public static boolean fairyHeal() {
        if (health == maxHealth) {
            TextEngine.printNoDelay("You are already at full health!", false);
            TextEngine.enterToNext();
            return false;
        }
        health = maxHealth;
        TextEngine.printNoDelay("You have been fully healed by the fairy!", false);
        TextEngine.enterToNext();
        return true;
    }

    /**
     * Calculates the damage dealt by the player.
     *
     * @return the calculated damage
     */
    public static int getDamageCalc() {
        return (defense + (damage - (damage / 3)));
    }

    /**
     * Changes the player's maximum health by a specified amount.
     *
     * @param change the amount to change the max health by
     * @throws InterruptedException if the thread is interrupted
     */
    public static void changeMaxHealth(int change) throws InterruptedException {
        String brightGreenStart = "\033[1;32m";
        String brightEnd = "\033[0m";
        maxHealth += change;
        health += change;
        TextEngine.printWithDelays(brightGreenStart + "Your max health has increased by " + change + " points" + brightEnd, false);
    }

    /**
     * Changes the player's gold by a specified amount.
     *
     * @param change the amount to change the gold by
     * @throws InterruptedException if the thread is interrupted
     */
    public static void changeGold(int change) throws InterruptedException {
        String brightRedStart = "\033[1;31m";
        String brightGreenStart = "\033[1;32m";
        String brightEnd = "\033[0m";
        String space = "     ";
        gold += change;

        if (change < 0) {
            TextEngine.printWithDelays(space + brightRedStart + "You lost " + Math.abs(change) + " gold! " + "(" + getGold() + ")" + brightEnd, false);
        } else {
            TextEngine.printWithDelays(space + brightGreenStart + "You gained " + change + " gold! " + "(" + getGold() + ")" + brightEnd, false);
        }
    }

    /**
     * Opens the inventory and prints its contents.
     *
     * @throws InterruptedException if the thread is interrupted while opening
     * the inventory
     */
    public static void openInventory() throws InterruptedException {
        InventoryManager.printInventory();
    }

    /**
     * Sets the damage value for the player.
     *
     * @param amount the amount of damage to set
     */
    public static void setDamage(int amount) {
        damage = amount;
    }

    /**
     * Sets the defense value for the player.
     *
     * @param amount the amount of defense to set
     */
    public static void setDefense(int amount) {
        defense = amount;
    }

    /**
     * Gets the current damage value of the player.
     *
     * @return the current damage value
     */
    public static int getDamage() {
        return damage;
    }

    /**
     * Gets the current defense value of the player.
     *
     * @return the current defense value
     */
    public static int getDefense() {
        return defense;
    }

    /**
     * Gets the total number of items in the player's inventory.
     *
     * @return the total number of items in the inventory
     */
    public static int getTotalNumberOfItemsInInventory() {
        int total = 0;
        for (String key : inventory.keySet()) {
            total += inventory.get(key);
        }
        return total;
    }

    /**
     * Gets the string representation of the player's inventory.
     *
     * @return the inventory as a string
     */
    public static String getInventory() {
        return inventory.toString();
    }

    /**
     * Adds an item to the player's inventory.
     *
     * @param item The name of the item to be added.
     * @param amount The quantity of the item to be added.
     * @return True if the item was successfully added, false otherwise.
     * @throws InterruptedException If the thread is interrupted while waiting
     * for user input.
     */
    public static Boolean putItem(String item, int amount) throws InterruptedException {
        // Check if the item is a backpack
        if ("Backpack".equals(item) || "Large Backpack".equals(item)) {
            // Change the inventory size based on the type of backpack
            changeInventorySize(InventoryManager.Potions.get(item));
            return true;
        } else {
            // Check if there is enough room in the inventory for the item
            if (inventorySize >= getTotalNumberOfItemsInInventory() + amount) {
                // Add the item to the inventory
                InventoryManager.giveItem(item, amount);
                // Update the inventory stats to the highest values
                InventoryManager.setStatsToHighestInInventory();
                return true;
            } else {
                // Notify the player that there is no room in the inventory
                TextEngine.printWithDelays("You have no room in your inventory!", false);
                TextEngine.enterToNext();
                return false;
            }
        }
    }

    /**
     * Creates a new player and initializes the game settings.
     *
     * @throws InterruptedException if the thread is interrupted while sleeping
     */
    private static void playerCreate() throws InterruptedException { //create the player
        String brightBoldEnd = "\033[0m";
        String brightYellowStart = "\033[1;33m";
        while (true) {
            TextEngine.printWithDelays("Welcome to the game! What is your name hero?", true);
            command = console.readLine();
            if (command != null && !command.isEmpty() && command.length() < 26) {
                if ("exit".equals(command)) {
                    GameEngine.startMenu();
                    TextEngine.clearScreen();
                } else {
                    setName(command);
                    break;
                }
            } else {
                TextEngine.printNoDelay("Invalid Input! Name must be 12 characters or less!", false);
                TextEngine.enterToNext();
                TextEngine.clearScreen();
            }

        }
        TextEngine.clearScreen();
        TextEngine.printWithDelays("Welcome " + Player.getName() + "!", false);
        TextEngine.printWithDelays("There are a few quick things to know.\nat any point you can type " + brightYellowStart + "settings" + brightBoldEnd + " to change the text speed.", false);
        TextEngine.printWithDelays("You can also type " + brightYellowStart + "exit" + brightBoldEnd + " to leave the game at any time.", false);
        TextEngine.printWithDelays("You can type " + brightYellowStart + "inventory" + brightBoldEnd + " to see your inventory and health.", false);
        TextEngine.printWithDelays("You can type " + brightYellowStart + "help" + brightBoldEnd + " to see these commands again.", false);
        TextEngine.printWithDelays("You can type " + brightYellowStart + "map" + brightBoldEnd + " to see where you are in the openWorld.", false);
        TextEngine.printWithDelays("you can type " + brightYellowStart + "stats" + brightBoldEnd + " to see you stats in the game.", false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("Make sure you always press enter when prompted to!", false);
        TextEngine.enterToNext();
        TextEngine.printWithDelays("Good luck!", false);
        TextEngine.enterToNext();
        TextEngine.clearScreen();
        GameEngine.playerCreated = true;
        GameEngine.playTime.startClock(1);
        GameEngine.saveSpace("SpawnRoom");
        GameEngine.loadSave();
    }

    /**
     * Prints the player's stats to the console.
     *
     * @throws InterruptedException if the thread is interrupted while printing
     * stats
     */
    public static void printStats() throws InterruptedException { //print the stats
        InventoryManager.setStatsToHighestInInventory();
        TextEngine.clearScreen();
        TextEngine.printNoDelay("Player Stats:", false);
        TextEngine.printNoDelay("Name: " + name, false);
        TextEngine.printNoDelay("Play Time: " + GameEngine.playTime.returnTime(), false);
        drawHealthBar();
        TextEngine.printNoDelay("Gold: " + gold, false);
        TextEngine.printNoDelay("Damage: " + damage, false);
        TextEngine.printNoDelay("Defense: " + defense, false);
        TextEngine.printNoDelay("Inventory: " + getTotalNumberOfItemsInInventory() + "/" + inventorySize, false);
        TextEngine.printNoDelay(yellowColor + "Press Enter to continue" + resetColor, false);
        command = console.readLine();
        if ("[][][]CadenTesting".equals(command)) {
            maxHealth = 10000;
            health = maxHealth;
            gold = 20000000;
            inventorySize = 200;
            putItem("k.o. cannon", 10);
            GameEngine.loadSave();
        } else if ("complete".equals(command) && name.equals("Debug!")) {
            maxHealth = 10000;
            health = maxHealth;
            gold = 20000000;
            inventorySize = 200;
            putItem("k.o. cannon", 10);
            Dungeon.completedDungeons = 8;
            GameEngine.gameComplete = true;
            GameEngine.loadSave();
        } else {
            command = null;
            GameEngine.loadSave();
        }
    }

    /**
     * Heals the player using available health potions from the inventory.
     *
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    public static void heal() throws InterruptedException {
        // Check if the inventory contains any health potions
        if (!inventory.containsKey("super health potion") && !inventory.containsKey("greater health potion") && !inventory.containsKey("health potion")) {
            TextEngine.printWithDelays("You have no health potions!", false);
            TextEngine.enterToNext();
        } else if (health >= maxHealth) {
            // Check if the player's health is already at maximum
            TextEngine.printWithDelays("You are already at full health!", false);
            TextEngine.enterToNext();
        } else {
            // Use the appropriate health potion from the inventory
            if (inventory.containsKey("super health potion")) {
                InventoryManager.useItemNoMenu("super health potion");
            } else if (inventory.containsKey("greater health potion")) {
                InventoryManager.useItemNoMenu("greater health potion");
            } else if (inventory.containsKey("health potion")) {
                InventoryManager.useItemNoMenu("health potion");
            }
        }
        // Load the saved game state
        GameEngine.loadSave();
    }

    /**
     * Copies the inventory.
     *
     * @return a map representing the inventory with item names as keys and
     * their quantities as values.
     */
    public static Map<String, Integer> copyInventory() { //get the inventory Manager
        return inventory;
    }

    /**
     * This method prints the game map to the console. It updates the map with
     * the player's position and the status of the dungeons based on the number
     * of completed dungeons.
     *
     * @throws InterruptedException if the thread is interrupted while sleeping
     */
    public static void printMap() throws InterruptedException {
        TextEngine.clearScreen();
        String[][] map = {
            //  0       1          2       3         4       5         6       7       8
            {"     ", b + "[ D ]" + R, b + "[   ]" + R, s + "[   ]" + R, s + "[   ]" + R, s + "[   ]" + R, S + "[   ]" + R, S + "[ D ]" + R, "     ", "", ""}, //0
            {"     ", b + "[   ]" + R, "     ", "     ", "     ", g + "[   ]" + R, g + "[   ]" + R, S + "[   ]" + R, "     ", "", ""}, //1
            {"     ", b + "[   ]" + R, "     ", "     ", "     ", g + "[   ]" + R, g + "[   ]" + R, g + "[   ]" + R, "     ", "", ""}, //2
            {"     ", s + "[   ]" + R, "     ", "     ", "     ", g + "[   ]" + R, g + "[   ]" + R, g + "[   ]" + R, g + "[ V ]" + R, "", ""}, //3
            {s + "[   ]" + R, s + "[   ]" + R, "     ", "     ", g + "[ D ]" + R, g + "[   ]" + R, g + "[   ]" + R, g + "[   ]" + R, g + "[   ]" + R, "", ""}, //4
            {s + "[   ]" + R, s + "[   ]" + R, s + "[   ]" + R, "     ", g + "[   ]" + R, g + "[   ]" + R, g + "[   ]" + R, g + "[   ]" + R, g + "[   ]" + R, "", ""}, //5
            {s + "[   ]" + R, s + "[   ]" + R, s + "[   ]" + R, G + "[   ]" + R, G + "[   ]" + R, G + "[   ]" + R, G + "[   ]" + R, G + "[   ]" + R, G + "[   ]" + R, "", ""}, //6
            {s + "[ V ]" + R, s + "[   ]" + R, s + "[   ]" + R, G + "[   ]" + R, G + "[   ]" + R, G + "[   ]" + R, G + "[   ]" + R, G + "[   ]" + R, G + "[   ]" + R, "", ""}, //7
            {"     ", s + "[ D ]" + R, s + "[   ]" + R, s + "[   ]" + R, G + "[   ]" + R, G + "[   ]" + R, G + "[   ]" + R, G + "[   ]" + R, G + "[ D ]" + R, "", ""}, //8
            {"     ", s + "[ D ]" + R, s + "[   ]" + R, s + "[   ]" + R, G + "[   ]" + R, G + "[   ]" + R, G + "[   ]" + R, G + "[   ]" + R, G + "[ D ]" + R, "", ""}, //9
            {"     ", s + "[ D ]" + R, s + "[   ]" + R, s + "[   ]" + R, G + "[   ]" + R, G + "[ V ]" + R, "     ", "    ", "     ", "", ""} //10
        };
        // Update the map with the player's position
        map[playerY][playerX] = "[ P ]";
        switch (Dungeon.completedDungeons) {
            case 0 -> {
                map[9][8] = "[ ! ]";
                map[8][8] = "[ L ]";
                map[4][4] = "[ L ]";
                map[0][7] = "[ L ]";
                map[10][1] = "[ L ]";
                map[9][1] = "[ L ]";
                map[8][1] = "[ L ]";
                map[0][1] = "[ L ]";
            }
            case 1 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ ! ]";
                map[4][4] = "[ L ]";
                map[0][7] = "[ L ]";
                map[10][1] = "[ L ]";
                map[9][1] = "[ L ]";
                map[8][1] = "[ L ]";
                map[0][1] = "[ L ]";
            }
            case 2 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ ! ]";
                map[0][7] = "[ L ]";
                map[10][1] = "[ L ]";
                map[9][1] = "[ L ]";
                map[8][1] = "[ L ]";
                map[0][1] = "[ L ]";
            }
            case 3 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ D ]";
                map[0][7] = "[ ! ]";
                map[10][1] = "[ L ]";
                map[9][1] = "[ L ]";
                map[8][1] = "[ L ]";
                map[0][1] = "[ L ]";
            }
            case 4 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ D ]";
                map[0][7] = "[ D ]";
                map[10][1] = "[ ! ]";
                map[9][1] = "[ L ]";
                map[8][1] = "[ L ]";
                map[0][1] = "[ L ]";
            }
            case 5 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ D ]";
                map[0][7] = "[ D ]";
                map[10][1] = "[ D ]";
                map[9][1] = "[ ! ]";
                map[8][1] = "[ L ]";
                map[0][1] = "[ L ]";
            }
            case 6 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ D ]";
                map[0][7] = "[ D ]";
                map[10][1] = "[ D ]";
                map[9][1] = "[ D ]";
                map[8][1] = "[ ! ]";
                map[0][1] = "[ L ]";
            }
            case 7 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ D ]";
                map[0][7] = "[ D ]";
                map[10][1] = "[ D ]";
                map[9][1] = "[ D ]";
                map[8][1] = "[ D ]";
                map[0][1] = "[ ! ]";
            }
            default -> {
                if (Dungeon.resetedAfterWin) {
                    map[9][8] = redColor + "[ D ]" + resetColor;
                    map[8][8] = redColor + "[ D ]" + resetColor;
                    map[4][4] = redColor + "[ D ]" + resetColor;
                    map[0][7] = redColor + "[ D ]" + resetColor;
                    map[10][1] = redColor + "[ D ]" + resetColor;
                    map[9][1] = redColor + "[ D ]" + resetColor;
                    map[8][1] = redColor + "[ D ]" + resetColor;
                    map[0][1] = redColor + "[ D ]" + resetColor;
                } else {
                    map[9][8] = "[ D ]";
                    map[8][8] = "[ D ]";
                    map[4][4] = "[ D ]";
                    map[0][7] = "[ D ]";
                    map[10][1] = "[ D ]";
                    map[9][1] = "[ D ]";
                    map[8][1] = "[ D ]";
                    map[0][1] = "[ D ]";
                }
            }

        }
        // Print the map
        String orangeColor = "\033[38;2;255;165;0m"; // ACU Purple
        System.out.println("Map: ");
        for (String[] row : map) {
            for (String cell : row) {
                if (null == cell) {
                    System.out.print(cell + " ");
                } else {
                    switch (cell) {
                        case "[ V ]" ->
                            System.out.print(greenColor + cell + resetColor + " ");
                        case "[ ! ]" ->
                            System.out.print(redColor + cell + resetColor + " ");
                        case "[ P ]" ->
                            System.out.print(orangeColor + cell + resetColor + " ");
                        default ->
                            System.out.print(cell + " ");
                    }
                }
            }
            System.out.println();
        }
        map[playerY][playerX] = "[   ]"; // Reset the player's position
        System.out.println("Key: ");
        System.out.println(redColor + " '!'" + resetColor + " = Next Dungeon");
        System.out.println(" 'D' = Unlocked Dungeon");
        System.out.println(" 'L' = Locked Dungeon");
        System.out.println(greenColor + " 'V'" + resetColor + "= Village");
        System.out.println(yellowColor + " 'P'" + resetColor + " = Player");
        System.out.println();
        System.out.println("If you are still feeling lost type " + yellowColor + "help" + resetColor + " for a list of commands.");
        TextEngine.enterToNext();
        GameEngine.loadSave();
    }

    /**
     * Checks if there is enough room in the inventory to add a specified amount
     * of items.
     *
     * @param amount the number of items to be added to the inventory
     * @return true if there is enough room in the inventory, false otherwise
     */
    public static Boolean hasRoomInInventory(int amount) {
        return inventorySize >= getTotalNumberOfItemsInInventory() + amount;
    }

    /**
     * Sets the player's attributes.
     *
     * @param localName the name of the player
     * @param localHealth the current health of the player
     * @param localMaxHealth the maximum health of the player
     * @param localGold the amount of gold the player has
     * @param localInventory the inventory of the player, represented as a map
     * of item names to their quantities
     * @param localInventorySize the size of the player's inventory
     */
    public static void playerSetSave(String localName, int localHealth, int localMaxHealth, int localGold, Map<String, Integer> localInventory, int localInventorySize) {
        name = localName;
        health = localHealth;
        maxHealth = localMaxHealth;
        gold = localGold;
        inventory = localInventory;
        inventorySize = localInventorySize;
    }

    /**
     * Changes the name of the player. Prompts the user to enter a new name and
     * validates the input. If the input is valid, the player's name is updated.
     * If the user types "exit", the start menu is displayed.
     *
     * @throws InterruptedException if the thread is interrupted while waiting
     * for user input
     */
    public static void changeName() throws InterruptedException {
        String brightYellowStart = "\033[1;33m";
        String brightEnd = "\033[0m";
        String space = "     ";
        TextEngine.printWithDelays(space + "What is your new name hero?", true);
        while (true) {
            command = console.readLine();
            if (command != null && !command.isEmpty() && command.length() < 13) {
                if ("exit".equals(command)) {
                    GameEngine.startMenu();
                    TextEngine.clearScreen();
                } else {
                    setName(command);
                    break;
                }
            } else {
                TextEngine.printWithDelays("Please enter a name.", true);
            }

        }
        TextEngine.printWithDelays(space + "Your name has been changed to " + brightYellowStart + name + brightEnd, false);
        TextEngine.enterToNext();
        GameEngine.startMenu();
    }

    /**
     * Draws the health bar for the player. The health bar is represented by a
     * series of colored bars and underscores. The color of the bars changes
     * based on the player's current health. - Green: Health is more than half
     * of the maximum health. - Yellow: Health is between half and a quarter
     * plus a tenth of the maximum health. - Red: Health is less than or equal
     * to a quarter plus a tenth of the maximum health. The health bar is
     * printed to the console.
     */
    public static void drawHealthBar() {
        int hearts = maxHealth / 20;
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
        if (getHealth() > Player.getMaxHealth() / 2) {
            healthColor = greenColor;
        } else if (getHealth() <= getMaxHealth() / 2 && getHealth() > (getMaxHealth() / 4) + (getMaxHealth() / 10)) {
            healthColor = yellowColor;
        } else {
            healthColor = redColor;
        }
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
        System.out.println("Health: " + health + " / " + maxHealth);
        System.out.println(healthBar);
    }

    /**
     * This method returns the environment type of the player's current position
     * on the map. The map is represented as a 2D array of strings, where each
     * string corresponds to a specific environment type. The method uses a
     * predefined mapping of color codes to environment types to determine the
     * environment type.
     *
     * @return A string representing the environment type of the player's
     * current position. Possible values are "grassland", "desert", "mountain
     * top", "ocean", "mountain", "lost forest", or "unknown".
     */
    public static String getColorOfPlayerPostitionTile() {
        //     public static String G = "\033[0;32m"; // deep grass color
        // public static String b = "\033[1;34m"; // blue color
        // public static String g = "\033[0;37m"; // bright gray color
        // public static String s = "\033[0;33m"; // sand color
        // public static String S = "\033[1;37m"; // snow color
        String[][] map = {
            //  0    1    2    3    4    5    6    7    8
            {" ", "b", "b", "s", "s", "s", "S", "S", " ", "", ""}, //0
            {" ", "b", " ", " ", " ", "g", "g", "S", " ", "", ""}, //1
            {" ", "b", " ", " ", " ", "g", "g", "g", " ", "", ""}, //2
            {" ", "s", " ", " ", " ", "g", "g", "g", "g", "", ""}, //3
            {"s", "s", " ", " ", "g", "g", "g", "g", "g", "", ""}, //4
            {"s", "s", "s", " ", "g", "g", "g", "g", "g", "", ""}, //5
            {"s", "s", "s", "G", "G", "G", "G", "F", "F", "", ""}, //6
            {"s", "s", "s", "G", "G", "G", "G", "F", "F", "", ""}, //7
            {" ", "s", "s", "s", "G", "G", "F", "F", "F", "", ""}, //8
            {" ", "s", "s", "s", "G", "G", "F", "F", "F", "", ""}, //9
            {" ", "s", "s", "s", "G", "G", " ", " ", " ", "", ""} //10
        };
        Map<String, String> colorToEnvironment = new HashMap<>();
        colorToEnvironment.put("G", "grassland"); // Green
        colorToEnvironment.put("s", "desert");    // Yellow
        colorToEnvironment.put("S", "mountain top"); // White
        colorToEnvironment.put("b", "ocean");     // Blue
        colorToEnvironment.put("g", "mountain"); // Gray
        colorToEnvironment.put("F", "lost forest"); // Gray
        String tileValue = String.valueOf(map[playerY][playerX]);

        for (Map.Entry<String, String> entry : colorToEnvironment.entrySet()) {
            if (tileValue == null ? (entry.getKey()) == null : tileValue.equals(entry.getKey())) {
                //System.out.println("test" + entry.getValue());
                return entry.getValue();
            }
        }
        //System.out.println("test" + "Unknown environment");
        return "unknown";
    }

    /**
     * This method calculates the compass direction to the closest village from
     * the player's current position.
     *
     * @return A string representing the compass direction to the closest
     * village. Possible values are: "North", "Northeast", "East", "Southeast",
     * "South", "Southwest", "West", "Northwest", or "No village found".
     */
    public static String getCompassDirectionToClosestVillage() {
        String[][] map = {
            {"     ", "", "", "", "", "", "", "", "     ", "", ""}, //0
            {"     ", "", "     ", "     ", "     ", "", "", "", "     ", "", ""}, //1
            {"     ", "", "     ", "     ", "     ", "", "", "", "     ", "", ""}, //2
            {"     ", "", "     ", "     ", "     ", "", "", "", "[ V ]", "", ""}, //3
            {"", "", "     ", "     ", "", "", "", "", "", "", ""}, //4
            {"", "", "", "     ", "", "", "", "", "", "", ""}, //5
            {"", "", "", "", "", "", "", "", "", "", ""}, //6
            {"[ V ]", "", "", "", "", "", "", "", "", "", ""}, //7
            {"     ", "", "", "", "", "", "", "", "", "", ""}, //8
            {"     ", "", "", "", "", "", "", "", "", "", ""}, //9
            {"     ", "", "", "", "", "[ V ]", "     ", "    ", "     ", "", ""} //10
        };

        int closestVillageX = -1;
        int closestVillageY = -1;
        double minDistance = Double.MAX_VALUE;

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if ("[ V ]".equals(map[y][x])) {
                    double distance = Math.sqrt(Math.pow(x - playerX, 2) + Math.pow(y - playerY, 2));
                    if (distance < minDistance) {
                        minDistance = distance;
                        closestVillageX = x;
                        closestVillageY = y;
                    }
                }
            }
        }

        if (closestVillageX == -1 || closestVillageY == -1) {
            return "No village found";
        }

        int deltaX = closestVillageX - playerX;
        int deltaY = closestVillageY - playerY;

        if (deltaX == 0 && deltaY < 0) {
            return "North";
        }
        if (deltaX > 0 && deltaY < 0) {
            return "Northeast";
        }
        if (deltaX > 0 && deltaY == 0) {
            return "East";
        }
        if (deltaX > 0 && deltaY > 0) {
            return "Southeast";
        }
        if (deltaX == 0 && deltaY > 0) {
            return "South";
        }
        if (deltaX < 0 && deltaY > 0) {
            return "South west";
        }
        if (deltaX < 0 && deltaY == 0) {
            return "West";
        }
        if (deltaX < 0 && deltaY < 0) {
            return "Northwest";
        }

        return "Unknown direction";
    }

    /**
     * Returns the compass direction to the closest dungeon based on the
     * player's current position.
     *
     * @return A string representing the compass direction to the closest
     * dungeon.
     */
    public static String getCompassDirectionToClosestDungeon() {
        String[][] map = {
            {"     ", "[ D ]", "", "", "", "", "", "[ D ]", "     ", "", ""}, //0
            {"     ", "", "     ", "     ", "     ", "", "", "", "     ", "", ""}, //1
            {"     ", "", "     ", "     ", "     ", "", "", "", "     ", "", ""}, //2
            {"     ", "", "     ", "     ", "     ", "", "", "", "", "", ""}, //3
            {"", "", "     ", "     ", "[ D ]", "", "", "", "", "", ""}, //4
            {"", "", "", "     ", "", "", "", "", "", "", ""}, //5
            {"", "", "", "", "", "", "", "", "", "", ""}, //6
            {"", "", "", "", "", "", "", "", "", "", ""}, //7
            {"     ", "[ D ]", "", "", "", "", "", "", "[ D ]", "", ""}, //8
            {"     ", "[ D ]", "", "", "", "", "", "", "[ D ]", "", ""}, //9
            {"     ", "[ D ]", "", "", "", "", "", "    ", "     ", "", ""} //10
        };
        switch (Dungeon.completedDungeons) {
            case 0 -> {
                map[9][8] = "[ ! ]";
                map[8][8] = "[ L ]";
                map[4][4] = "[ L ]";
                map[0][7] = "[ L ]";
                map[10][1] = "[ L ]";
                map[9][1] = "[ L ]";
                map[8][1] = "[ L ]";
                map[0][1] = "[ L ]";
            }
            case 1 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ ! ]";
                map[4][4] = "[ L ]";
                map[0][7] = "[ L ]";
                map[10][1] = "[ L ]";
                map[9][1] = "[ L ]";
                map[8][1] = "[ L ]";
                map[0][1] = "[ L ]";
            }
            case 2 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ ! ]";
                map[0][7] = "[ L ]";
                map[10][1] = "[ L ]";
                map[9][1] = "[ L ]";
                map[8][1] = "[ L ]";
                map[0][1] = "[ L ]";
            }
            case 3 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ D ]";
                map[0][7] = "[ ! ]";
                map[10][1] = "[ L ]";
                map[9][1] = "[ L ]";
                map[8][1] = "[ L ]";
                map[0][1] = "[ L ]";
            }
            case 4 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ D ]";
                map[0][7] = "[ D ]";
                map[10][1] = "[ ! ]";
                map[9][1] = "[ L ]";
                map[8][1] = "[ L ]";
                map[0][1] = "[ L ]";
            }
            case 5 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ D ]";
                map[0][7] = "[ D ]";
                map[10][1] = "[ D ]";
                map[9][1] = "[ ! ]";
                map[8][1] = "[ L ]";
                map[0][1] = "[ L ]";
            }
            case 6 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ D ]";
                map[0][7] = "[ D ]";
                map[10][1] = "[ D ]";
                map[9][1] = "[ D ]";
                map[8][1] = "[ ! ]";
                map[0][1] = "[ L ]";
            }
            case 7 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ D ]";
                map[0][7] = "[ D ]";
                map[10][1] = "[ D ]";
                map[9][1] = "[ D ]";
                map[8][1] = "[ D ]";
                map[0][1] = "[ ! ]";
            }
            default -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ D ]";
                map[0][7] = "[ D ]";
                map[10][1] = "[ D ]";
                map[9][1] = "[ D ]";
                map[8][1] = "[ D ]";
                map[0][1] = "[ D ]";
            }
        }
        int closestDungeonX = -1;
        int closestDungeonY = -1;
        double minDistance = Double.MAX_VALUE;

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if ("[ ! ]".equals(map[y][x])) {
                    double distance = Math.sqrt(Math.pow(x - playerX, 2) + Math.pow(y - playerY, 2));
                    if (distance < minDistance) {
                        minDistance = distance;
                        closestDungeonX = x;
                        closestDungeonY = y;
                    }
                }
            }
        }

        if (closestDungeonX == -1 || closestDungeonY == -1) {
            return "No dungeon found";
        }

        int deltaX = closestDungeonX - playerX;
        int deltaY = closestDungeonY - playerY;

        if (deltaX == 0 && deltaY < 0) {
            return "North";
        }
        if (deltaX > 0 && deltaY < 0) {
            return "Northeast";
        }
        if (deltaX > 0 && deltaY == 0) {
            return "East";
        }
        if (deltaX > 0 && deltaY > 0) {
            return "Southeast";
        }
        if (deltaX == 0 && deltaY > 0) {
            return "South";
        }
        if (deltaX < 0 && deltaY > 0) {
            return "Southwest";
        }
        if (deltaX < 0 && deltaY == 0) {
            return "West";
        }
        if (deltaX < 0 && deltaY < 0) {
            return "Northwest";
        }

        return "Unknown direction";
    }

    /**
     * Returns the name of the next dungeon based on the number of completed
     * dungeons.
     *
     * @return the name of the next dungeon or a completion message if all
     * dungeons are completed.
     */
    public static String getNextDungeon() {
        return switch (Dungeon.completedDungeons) {
            case 0 ->
                "Meadow Dungeon";
            case 1 ->
                "Dark Forest Dungeon";
            case 2 ->
                "Mountain Cave Dungeon";
            case 3 ->
                "Mountain Top Dungeon";
            case 4 ->
                "Desert Oasis Dungeon";
            case 5 ->
                "Desert Plains Dungeon";
            case 6 ->
                "Desert Pyramid Dungeon";
            case 7 ->
                "Ocean Kingdom Dungeon";
            case 8 ->
                "You have completed all of the dungeons!";
            default ->
                "SpawnRoom";
        };
    }

    /**
     * Calculates the distance from the player's current position to the closest
     * village on the map.
     *
     * @return the distance to the closest village, or -1 if no village is
     * found.
     */
    public static int distanceToVillage() {
        String[][] map = {
            {"     ", "", "", "", "", "", "", "", "     ", "", ""}, //0
            {"     ", "", "     ", "     ", "     ", "", "", "", "     ", "", ""}, //1
            {"     ", "", "     ", "     ", "     ", "", "", "", "     ", "", ""}, //2
            {"     ", "", "     ", "     ", "     ", "", "", "", "[ V ]", "", ""}, //3
            {"", "", "     ", "     ", "", "", "", "", "", "", ""}, //4
            {"", "", "", "     ", "", "", "", "", "", "", ""}, //5
            {"", "", "", "", "", "", "", "", "", "", ""}, //6
            {"[ V ]", "", "", "", "", "", "", "", "", "", ""}, //7
            {"     ", "", "", "", "", "", "", "", "", "", ""}, //8
            {"     ", "", "", "", "", "", "", "", "", "", ""}, //9
            {"     ", "", "", "", "", "[ V ]", "     ", "    ", "     ", "", ""} //10
        };
        int closestVillageX = -1;
        int closestVillageY = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if ("[ V ]".equals(map[y][x])) {
                    int distance = Math.abs(x - playerX) + Math.abs(y - playerY);
                    if (distance < minDistance) {
                        minDistance = distance;
                        closestVillageX = x;
                        closestVillageY = y;
                    }
                }
            }
        }
        if (closestVillageX == -1 || closestVillageY == -1) {
            return -1;
        }
        return minDistance - 1;
    }

    /**
     * Calculates the distance to the next dungeon based on the current state of
     * the map and the number of completed dungeons.
     *
     * @return the distance to the next dungeon, or -1 if no dungeon is found.
     */
    public static int distanceToNextDungeon() {
        String[][] map = {
            {"     ", "[ D ]", "", "", "", "", "", "[ D ]", "     ", "", ""}, //0
            {"     ", "", "     ", "     ", "     ", "", "", "", "     ", "", ""}, //1
            {"     ", "", "     ", "     ", "     ", "", "", "", "     ", "", ""}, //2
            {"     ", "", "     ", "     ", "     ", "", "", "", "", "", ""}, //3
            {"", "", "     ", "     ", "[ D ]", "", "", "", "", "", ""}, //4
            {"", "", "", "     ", "", "", "", "", "", "", ""}, //5
            {"", "", "", "", "", "", "", "", "", "", ""}, //6
            {"", "", "", "", "", "", "", "", "", "", ""}, //7
            {"     ", "[ D ]", "", "", "", "", "", "", "[ D ]", "", ""}, //8
            {"     ", "[ D ]", "", "", "", "", "", "", "[ D ]", "", ""}, //9
            {"     ", "[ D ]", "", "", "", "", "", "    ", "     ", "", ""} //10
        };
        switch (Dungeon.completedDungeons) {
            case 0 -> {
                map[9][8] = "[ ! ]";
                map[8][8] = "[ L ]";
                map[4][4] = "[ L ]";
                map[0][7] = "[ L ]";
                map[10][1] = "[ L ]";
                map[9][1] = "[ L ]";
                map[8][1] = "[ L ]";
                map[0][1] = "[ L ]";
            }
            case 1 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ ! ]";
                map[4][4] = "[ L ]";
                map[0][7] = "[ L ]";
                map[10][1] = "[ L ]";
                map[9][1] = "[ L ]";
                map[8][1] = "[ L ]";
                map[0][1] = "[ L ]";
            }
            case 2 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ ! ]";
                map[0][7] = "[ L ]";
                map[10][1] = "[ L ]";
                map[9][1] = "[ L ]";
                map[8][1] = "[ L ]";
                map[0][1] = "[ L ]";
            }
            case 3 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ D ]";
                map[0][7] = "[ ! ]";
                map[10][1] = "[ L ]";
                map[9][1] = "[ L ]";
                map[8][1] = "[ L ]";
                map[0][1] = "[ L ]";
            }
            case 4 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ D ]";
                map[0][7] = "[ D ]";
                map[10][1] = "[ ! ]";
                map[9][1] = "[ L ]";
                map[8][1] = "[ L ]";
                map[0][1] = "[ L ]";
            }
            case 5 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ D ]";
                map[0][7] = "[ D ]";
                map[10][1] = "[ D ]";
                map[9][1] = "[ ! ]";
                map[8][1] = "[ L ]";
                map[0][1] = "[ L ]";
            }
            case 6 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ D ]";
                map[0][7] = "[ D ]";
                map[10][1] = "[ D ]";
                map[9][1] = "[ D ]";
                map[8][1] = "[ ! ]";
                map[0][1] = "[ L ]";
            }
            case 7 -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ D ]";
                map[0][7] = "[ D ]";
                map[10][1] = "[ D ]";
                map[9][1] = "[ D ]";
                map[8][1] = "[ D ]";
                map[0][1] = "[ ! ]";
            }
            default -> {
                map[9][8] = "[ D ]";
                map[8][8] = "[ D ]";
                map[4][4] = "[ D ]";
                map[0][7] = "[ D ]";
                map[10][1] = "[ D ]";
                map[9][1] = "[ D ]";
                map[8][1] = "[ D ]";
                map[0][1] = "[ D ]";
            }
        }
        int closestDungeonX = -1;
        int closestDungeonY = -1;
        double minDistance = Double.MAX_VALUE;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if ("[ ! ]".equals(map[y][x])) {
                    int distance = Math.abs(x - playerX) + Math.abs(y - playerY);
                    if (distance < minDistance) {
                        minDistance = distance;
                        closestDungeonX = x;
                        closestDungeonY = y;
                        break;
                    }
                }
            }
        }
        if (closestDungeonX == -1 || closestDungeonY == -1) {
            return -1;
        }
        return (int) minDistance - 1;
    }
}
