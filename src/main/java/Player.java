
import java.io.Console;
import java.util.HashMap;
import java.util.Map;

/**
 * Player Engine
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class Player {

    private static int health;
    private static int maxHealth;
    private static String name;
    public final static Console console = System.console();
    public static String command;
    public static String ignore;
    public static int inventorySize;
    private static int gold;
    private static int damage; //find way to set damage automatically to strongest weapon in inventory
    private static int defense; //find way to set defense automatically to the strongest 4-5 defense items combined
    public static Map<String, Integer> inventory = new HashMap<>();
    public static InventoryManager manager = new InventoryManager();

    public static void playerStart() throws InterruptedException { //start the player
        maxHealth = 100;
        health = maxHealth;
        damage = 0;
        defense = 0;
        damage = 0;
        defense = 0;
        gold = 0;
        inventorySize = 20;
        inventory.clear();
        playerCreate();
    }

    public static void debugStart() throws InterruptedException { //start the player with debug settings
        maxHealth = 100;
        health = maxHealth;
        damage = 0;
        defense = 0;
        gold = 20000000;
        inventorySize = 200;
        name = "Debug!";
        Main.playerCreated = true;
        DungeonGenerator.testing = false;
        TextEngine.printNoDelay("Where do you want to spawn", false);
        TextEngine.printNoDelay("1: SpawnRoom", false);
        TextEngine.printNoDelay("2: OpenWorld", false);
        TextEngine.printNoDelay("3: Dungeon", false);
        TextEngine.printNoDelay("4: Village", false);
        TextEngine.printNoDelay("debug spawn: ", true);
        ignore = console.readLine();
        command = console.readLine();
        switch (command) {
            case "1" -> {
                Main.saveSpace("SpawnRoom");
                Main.loadSave();
            }
            case "2" -> {
                Main.saveSpace("OpenWorld");
                Main.loadSave();
            }
            case "3" -> {
                TextEngine.printNoDelay("1: Meadow", false);
                TextEngine.printNoDelay("debug dungeon: ", true);
                ignore = console.readLine();
                command = console.readLine();
                switch (command) {
                    case "1" ->{
                        Main.saveSpace("Meadow Dungeon");
                        Main.loadSave();
                }
                    default -> {
                        Main.saveSpace("SpawnRoom");
                        Main.loadSave();
                    }
                }
            }
            case "4" -> {
                Main.saveSpace("Village");
                Main.loadSave();
            }
            default -> {
                Main.saveSpace("SpawnRoom");
                Main.loadSave();
            }
        }

    }

    public static String getName() { //get the name
        return name;
    }

    public static void setName(String name) { //set the name
        Player.name = name;
    }

    public static int getHealth() { //get the health
        return health;
    }

    public static int getMaxHealth() { //get the max health
        return maxHealth;
    }

    public static int getGold() { //get the gold
        return gold;
    }

    public static void changeInventorySize(int change) throws InterruptedException { //change the inventory size
        inventorySize += change;
    }

    public static void changeHealth(int change) throws InterruptedException { //change the health
        int damageCalc = (defense + (damage / 2));
        if (change < 0) {
            change += damageCalc;
            if (change >= 0) {
                change = -1;
            }
            TextEngine.printWithDelays("You took " + change + " damage!", false);
        } else {
            TextEngine.printWithDelays("You gained " + (change - (change - (maxHealth - getHealth()))) + " health!", false);
        }
        health += change;
        if (health > maxHealth) {
            health = maxHealth;
        }
        if (health <= 0) {
            TextEngine.printWithDelays("You have died!", false);
            TextEngine.printWithDelays("Game Over!", false);
            TextEngine.enterToNext();
            Main.startMenu();
        }
    }

    public static void changeMaxHealth(int change) throws InterruptedException { //change the max health
        maxHealth += change;
        health += change;
    }

    public static void changeGold(int change) throws InterruptedException { //change the gold
        gold += change;
        TextEngine.printWithDelays("You gained " + change + " gold!", false);
    }

    public static void openInventory() throws InterruptedException { //open the inventory
        manager.printInventory();
    }

    public static void setDamage(int amount) { //set the damage
        damage = amount;
    }

    public static void setDefense(int amount) { //set the defense
        defense = amount;
    }

    public static int getDamage() { //get the damage
        return damage;
    }

    public static int getDefense() { //get the defense
        return defense;
    }

    public static void putItem(String item, int amount) throws InterruptedException { //put an item in the inventory
        manager.put(item, amount);
    }

    private static void playerCreate() throws InterruptedException { //create the player
        TextEngine.printWithDelays("Welcome to the game! What is your name hero?", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            if (command != null && !command.isEmpty()) {
                if ("exit".equals(command)) {
                    Main.startMenu();
                    TextEngine.clearScreen();
                } else {
                    setName(command);
                    break;
                }
            } else {
                TextEngine.printWithDelays("Please enter a name.", true);
            }

        }
        TextEngine.clearScreen();
        TextEngine.printWithDelays("Welcome " + Player.getName() + "!", false);
        TextEngine.printWithDelays("There are a few quick things to know.\nat any point you can type settings to change the text speed.", false);
        TextEngine.printWithDelays("You can also type exit to leave the game at any time.", false);
        TextEngine.printWithDelays("You can type inventory to see your inventory and health.", false);
        TextEngine.printWithDelays("You can type help to see these commands again.", false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("Make sure you always press enter when prompted to!", false);
        TextEngine.enterToNext();
        TextEngine.printWithDelays("Good luck!", false);
        TextEngine.enterToNext();
        TextEngine.clearScreen();
        Main.playerCreated = true;
        Main.start();
    }

    public static void printStats() throws InterruptedException { //print the stats
        InventoryManager.setStatsToHighestInInventory();
        TextEngine.clearScreen();
        TextEngine.printNoDelay("Player Stats:", false);
        TextEngine.printNoDelay("Name: " + name, false);
        TextEngine.printNoDelay("Health: " + health + "/" + maxHealth, false);
        TextEngine.printNoDelay("Gold: " + gold, false);
        TextEngine.printNoDelay("Damage: " + damage, false);
        TextEngine.printNoDelay("Defense: " + defense, false);
        TextEngine.printNoDelay("Inventory: " + inventory.size() + "/" + inventorySize, false);
        TextEngine.enterToNext();
        Main.loadSave();
    }

    public static void heal() throws InterruptedException { //use available health potions in inventory to heal
        if (inventory.containsKey("health potion")) {
            if (health < maxHealth) {
                InventoryManager.useItem("health potion");
            } else {
                TextEngine.printWithDelays("You are already at full health!", false);
            }
        } else {
            TextEngine.printWithDelays("You have no health potions!", false);
        }
        TextEngine.enterToNext();
        Main.loadSave();
    }

}
