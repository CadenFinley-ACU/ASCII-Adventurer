
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
    public static String holdCommand;
    public static String ignore;
    public static int inventorySize;
    private static int gold;
    private static int damage; //find way to set damage automatically to strongest weapon in inventory
    private static int defense; //find way to set defense automatically to the strongest 4-5 defense items combined
    public static Map<String, Integer> inventory = new HashMap<>();
    public static InventoryManager manager = new InventoryManager();
    public static boolean autoFight = false;
    public static int playerX = 0;
    public static int playerY = 0;

    public static void playerStart() throws InterruptedException { //start the player
        maxHealth = 100;
        health = maxHealth;
        damage = 0;
        defense = 0;
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
        Main.playerCreated = true;
        DungeonGenerator.testing = false;
        TextEngine.printNoDelay("Where do you want to spawn", false);
        TextEngine.printNoDelay(" 1:SpawnRoom", false);
        TextEngine.printNoDelay(" 2:OpenWorld", false);
        TextEngine.printNoDelay(" 3:Dungeon", false);
        TextEngine.printNoDelay(" 4:Village", false);
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
                TextEngine.printNoDelay(" 1:Meadow\n 2:Dark Forest\n 3:Mountain Cave\n 4:Mountain Top\n 5:Desert Oasis\n 6:Desert Plains\n 7 Desert Pyramid\n 8:Ocean Kingdom", false);
                TextEngine.printNoDelay("debug dungeon: ", true);
                ignore = console.readLine();
                command = console.readLine();
                switch (command) {
                    case "1" -> {
                        Main.saveSpace("Meadow Dungeon");
                        Main.loadSave();
                    }
                    case "2" -> {
                        Main.saveSpace("Dark Forest Dungeon");
                        Main.loadSave();
                    }
                    case "3" -> {
                        Main.saveSpace("Mountain Cave Dungeon");
                        Main.loadSave();
                    }
                    case "4" -> {
                        Main.saveSpace("Mountain Top Dungeon");
                        Main.loadSave();
                    }
                    case "5" -> {
                        Main.saveSpace("Desert Oasis Dungeon");
                        Main.loadSave();
                    }
                    case "6" -> {
                        Main.saveSpace("Desert Plains Dungeon");
                        Main.loadSave();
                    }
                    case "7" -> {
                        Main.saveSpace("Desert Pyramid Dungeon");
                        Main.loadSave();
                    }
                    case "8" -> {
                        Main.saveSpace("Ocean Kingdom Dungeon");
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

    public static void changeHealth(int change) throws InterruptedException {
        String brightRedStart = "\033[1;31m";
        String brightGreenStart = "\033[1;32m";
        String brightEnd = "\033[0m";
        String space = "     ";
        int damageCalc = (defense + (damage - (damage / 3)));

        if (change < 0) {
            change += damageCalc;
            if (change >= 0) {
                change = -1;
            }
            TextEngine.printWithDelays(space + brightRedStart + "You took " + change + " damage!" + brightEnd, false);
        } else {
            TextEngine.printWithDelays(space + brightGreenStart + "You gained " + change + " health!" + brightEnd, false);
        }

        health += change;
        if (health > maxHealth) {
            health = maxHealth;
        }

        if (health <= 0) {
            TextEngine.printWithDelays(space + brightRedStart + "You have died!" + brightEnd, false);
            TextEngine.printWithDelays(space + brightRedStart + "Game Over!" + brightEnd, false);
            TextEngine.enterToNext();
            Main.wipeSave();
            Main.startMenu();
        }

        TextEngine.enterToNext();
    }

    public static void changeMaxHealth(int change) throws InterruptedException { //change the max health
        maxHealth += change;
        health += change;
        TextEngine.printWithDelays("Your max health has increased by " + change + " points", false);
    }

    public static void changeGold(int change) throws InterruptedException {
        String brightRedStart = "\033[1;31m";
        String brightGreenStart = "\033[1;32m";
        String brightEnd = "\033[0m";
        String space = "     ";
        gold += change;

        if (change < 0) {
            TextEngine.printWithDelays(space + brightRedStart + "You lost " + Math.abs(change) + " gold!" + brightEnd, false);
        } else {
            TextEngine.printWithDelays(space + brightGreenStart + "You gained " + change + " gold!" + brightEnd, false);
        }
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

    public static int getTotalNumberOfItemsInInventory() {
        int total = 0;
        for (String key : inventory.keySet()) {
            total += inventory.get(key);
        }
        return total;
    }

    public static void putItem(String item, int amount) throws InterruptedException { //put an item in the inventory
        if ("Backpack".equals(item) || "Large Backpack".equals(item)) {
            changeInventorySize(InventoryManager.Potions.get(item));
        } else {
            manager.put(item, amount);
        }
    }

    private static void playerCreate() throws InterruptedException { //create the player
        String brightBoldEnd = "\033[0m";
        String brightYellowStart = "\033[1;33m";

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
        TextEngine.printWithDelays("There are a few quick things to know.\nat any point you can type " + brightYellowStart + "'settings'" + brightBoldEnd + " to change the text speed.", false);
        TextEngine.printWithDelays("You can also type " + brightYellowStart + "'exit'" + brightBoldEnd + " to leave the game at any time.", false);
        TextEngine.printWithDelays("You can type " + brightYellowStart + "'inventory'" + brightBoldEnd + " to see your inventory and health.", false);
        TextEngine.printWithDelays("You can type " + brightYellowStart + "'help'" + brightBoldEnd + " to see these commands again.", false);
        TextEngine.printWithDelays("You can type " + brightYellowStart + "'map'" + brightBoldEnd + " to see where you are in the openWorld.", false);
        TextEngine.printWithDelays("you can type " + brightYellowStart + "'stats'" + brightBoldEnd + " to see you stats in the game.", false);
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
        TextEngine.printNoDelay("Inventory: " + getTotalNumberOfItemsInInventory() + "/" + inventorySize, false);
        TextEngine.enterToNext();
        Main.loadSave();
    }

    public static void heal() throws InterruptedException { //use available health potions in inventory to heal
        if (inventory.containsKey("super health potion")) {
            if (health < maxHealth) {
                InventoryManager.useItem("super health potion");
            } else {
                TextEngine.printWithDelays("You are already at full health!", false);
            }
        } else {
            if (inventory.containsKey("greater health potion")) {
                if (health < maxHealth) {
                    InventoryManager.useItem("greater health potion");
                } else {
                    TextEngine.printWithDelays("You are already at full health!", false);
                }
            } else {
                if (inventory.containsKey("health potion")) {
                    if (health < maxHealth) {
                        InventoryManager.useItem("health potion");
                    } else {
                        TextEngine.printWithDelays("You are already at full health!", false);
                    }
                } else {
                    TextEngine.printWithDelays("You have no health potions!", false);
                }
            }
        }
        TextEngine.enterToNext();
        Main.loadSave();
    }

    public static Map<String, Integer> copyInventory() { //get the inventory manager
        return inventory;
    }

    public static void printMap() throws InterruptedException {
        TextEngine.clearScreen();
        String[][] map = {
            //  0        1        2        3        4
            {"     ", "     ", "[   ]", "[ D ]", "     ", "     ", " ", " ", " "}, //0
            {"[ V ]", "     ", "[   ]", "[   ]", "[ D ]", "   ", "", " ", " "}, //1
            {"[   ]", "[   ]", "[   ]", "[ V ]", "     ", " ", " ", " "}, //2
            {"[   ]", "[   ]", "[   ]", "[   ]", "[ D ]", "", "", " ", " "}, //3
            {"     ", "[   ]", "[   ]", "[   ]", "[ D ]", " ", " ", " "}, //4
            {"[ D ]", "[   ]", "[   ]", "[   ]", "[ D ]", " ", " ", " "}, //5
            {"[ D ]", "[   ]", "[   ]", "[   ]", "[ D ]", " ", " ", " "}, //6
            {"     ", "[   ]", "[   ]", "[ V ]", "     ", " ", " "} //7
        };

        // Update the map with the player's position
        map[playerY][playerX] = "[ P ]";
        switch (Dungeon.completedDungeons) {
            case 0 -> {
                map[6][0] = "[ ! ]";
                map[5][0] = "[ L ]";
                map[6][4] = "[ L ]";
                map[5][4] = "[ L ]";
                map[4][4] = "[ L ]";
                map[3][4] = "[ L ]";
                map[1][4] = "[ L ]";
                map[0][3] = "[ L ]";
            }
            case 1 -> {
                map[6][0] = "[ D ]";
                map[5][0] = "[ ! ]";
                map[6][4] = "[ L ]";
                map[5][4] = "[ L ]";
                map[4][4] = "[ L ]";
                map[3][4] = "[ L ]";
                map[1][4] = "[ L ]";
                map[0][3] = "[ L ]";
            }
            case 2 -> {
                map[6][0] = "[ D ]";
                map[5][0] = "[ D ]";
                map[6][4] = "[ ! ]";
                map[5][4] = "[ L ]";
                map[4][4] = "[ L ]";
                map[3][4] = "[ L ]";
                map[1][4] = "[ L ]";
                map[0][3] = "[ L ]";
            }
            case 3 -> {
                map[6][0] = "[ D ]";
                map[5][0] = "[ D ]";
                map[6][4] = "[ D ]";
                map[5][4] = "[ ! ]";
                map[4][4] = "[ L ]";
                map[3][4] = "[ L ]";
                map[1][4] = "[ L ]";
                map[0][3] = "[ L ]";
            }
            case 4 -> {
                map[6][0] = "[ D ]";
                map[5][0] = "[ D ]";
                map[6][4] = "[ D ]";
                map[5][4] = "[ D ]";
                map[4][4] = "[ ! ]";
                map[3][4] = "[ L ]";
                map[1][4] = "[ L ]";
                map[0][3] = "[ L ]";
            }
            case 5 -> {
                map[6][0] = "[ D ]";
                map[5][0] = "[ D ]";
                map[6][4] = "[ D ]";
                map[5][4] = "[ D ]";
                map[4][4] = "[ D ]";
                map[3][4] = "[ ! ]";
                map[1][4] = "[ L ]";
                map[0][3] = "[ L ]";
            }
            case 6 -> {
                map[6][0] = "[ D ]";
                map[5][0] = "[ D ]";
                map[6][4] = "[ D ]";
                map[5][4] = "[ D ]";
                map[4][4] = "[ D ]";
                map[3][4] = "[ D ]";
                map[1][4] = "[ ! ]";
                map[0][3] = "[ L ]";
            }
            case 7 -> {
                map[6][0] = "[ D ]";
                map[5][0] = "[ D ]";
                map[6][4] = "[ D ]";
                map[5][4] = "[ D ]";
                map[4][4] = "[ D ]";
                map[3][4] = "[ D ]";
                map[1][4] = "[ D ]";
                map[0][3] = "[ ! ]";
            }
            default -> {
                map[6][0] = "[ D ]";
                map[5][0] = "[ D ]";
                map[6][4] = "[ D ]";
                map[5][4] = "[ D ]";
                map[4][4] = "[ D ]";
                map[3][4] = "[ D ]";
                map[1][4] = "[ D ]";
                map[0][3] = "[ D ]";
            }

        }
        // Print the map
        System.out.println("Map: ");
        for (String[] row : map) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        map[playerY][playerX] = "[   ]"; // Reset the player's position
        System.out.println("Key: ");
        System.out.println(" '!' = Next Dungeon");
        System.out.println(" 'D' = Unlocked Dungeon");
        System.out.println(" 'L' = Locked Dungeon");
        System.out.println(" 'V' = Village");
        System.out.println(" 'P' = Player");
        TextEngine.enterToNext();
        Main.loadSave();
    }
}
