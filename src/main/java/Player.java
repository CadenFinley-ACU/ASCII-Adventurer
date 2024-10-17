
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
    public static int playerX = 0;
    public static int playerY = 0;
    public static String yellowColor = "\033[1;33m"; // yellow color
    public static String resetColor = "\033[0m"; // reset to default color
    public static String redColor = "\033[1;31m"; // red color
    public static String greenColor = "\033[1;32m"; // green color

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

    public static int getInventorySize() { //get the inventory size
        return inventorySize;
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
            change = Math.min(change, maxHealth - health);
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

    public static int getDamageCalc() {
        return (defense + (damage - (damage / 3)));
    }

    public static void changeMaxHealth(int change) throws InterruptedException { //change the max health
        String brightGreenStart = "\033[1;32m";
        String brightEnd = "\033[0m";
        maxHealth += change;
        health += change;
        TextEngine.printWithDelays(brightGreenStart + "Your max health has increased by " + change + " points" + brightEnd, false);
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
        InventoryManager.printInventory();
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

    public static String getInventory() {
        return inventory.toString();
    }

    public static Boolean putItem(String item, int amount) throws InterruptedException { //put an item in the inventory
        if ("Backpack".equals(item) || "Large Backpack".equals(item)) {
            changeInventorySize(InventoryManager.Potions.get(item));
            return true;
        } else {
            if (inventorySize >= getTotalNumberOfItemsInInventory() + amount) {
                InventoryManager.put(item, amount);
                return true;
            } else {
                TextEngine.printWithDelays("You have no room in your inventory!", false);
                TextEngine.enterToNext();
                return false;
            }
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
        Main.playerCreated = true;
        Main.saveSpace("SpawnRoom");
        Main.loadSave();
    }

    public static void printStats() throws InterruptedException { //print the stats
        String redColor = "\033[1;31m"; // red color
        String yellowColor = "\033[1;33m"; // yellow color
        String greenColor = "\033[1;32m"; // green color
        String healthColor;
        String resetColor = "\033[0m"; // reset to default color

        if (getHealth() > getMaxHealth() / 2) {
            healthColor = greenColor;
        } else if (getHealth() <= getMaxHealth() / 2 && getHealth() > (getMaxHealth() / 4) + (getMaxHealth() / 10)) {
            healthColor = yellowColor;
        } else {
            healthColor = redColor;
        }

        InventoryManager.setStatsToHighestInInventory();
        TextEngine.clearScreen();
        TextEngine.printNoDelay("Player Stats:", false);
        TextEngine.printNoDelay("Name: " + name, false);
        TextEngine.printNoDelay("Health: " + healthColor + health + resetColor + "/" + maxHealth, false);
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
                InventoryManager.useItemNoMenu("super health potion");
            } else {
                TextEngine.printWithDelays("You are already at full health!", false);
                TextEngine.enterToNext();
            }
        } else {
            if (inventory.containsKey("greater health potion")) {
                if (health < maxHealth) {
                    InventoryManager.useItemNoMenu("greater health potion");
                } else {
                    TextEngine.printWithDelays("You are already at full health!", false);
                    TextEngine.enterToNext();
                }
            } else {
                if (inventory.containsKey("health potion")) {
                    if (health < maxHealth) {
                        InventoryManager.useItemNoMenu("health potion");
                    } else {
                        TextEngine.printWithDelays("You are already at full health!", false);
                        TextEngine.enterToNext();
                    }
                } else {
                    TextEngine.printWithDelays("You have no health potions!", false);
                    TextEngine.enterToNext();
                }
            }
        }
        Main.loadSave();
    }

    public static Map<String, Integer> copyInventory() { //get the inventory Manager
        return inventory;
    }

    public static void printMap() throws InterruptedException {
        TextEngine.clearScreen();

        String[][] map = {
            //  0       1          2       3         4       5         6       7       8
            {"     ", "[ D ]", "[   ]", "[   ]", "[   ]", "[   ]", "[   ]", "[ D ]", "     ", "", ""}, //0
            {"     ", "[   ]", "     ", "     ", "     ", "[   ]", "[   ]", "[   ]", "     ", "", ""}, //1
            {"     ", "[   ]", "     ", "     ", "     ", "[   ]", "[   ]", "[   ]", "     ", "", ""}, //2
            {"     ", "[   ]", "     ", "     ", "     ", "[   ]", "[   ]", "[   ]", "[ V ]", "", ""}, //3
            {"[   ]", "[   ]", "     ", "     ", "[ D ]", "[   ]", "[   ]", "[   ]", "[   ]", "", ""}, //4
            {"[   ]", "[   ]", "[   ]", "     ", "[   ]", "[   ]", "[   ]", "[   ]", "[   ]", "", ""}, //5
            {"[   ]", "[   ]", "[   ]", "[   ]", "[   ]", "[   ]", "[   ]", "[   ]", "[   ]", "", ""}, //6
            {"[ V ]", "[   ]", "[   ]", "[   ]", "[   ]", "[   ]", "[   ]", "[   ]", "[   ]", "", ""}, //7
            {"     ", "[ D ]", "[   ]", "[   ]", "[   ]", "[   ]", "[   ]", "[   ]", "[ D ]", "", ""}, //8
            {"     ", "[ D ]", "[   ]", "[   ]", "[   ]", "[   ]", "[   ]", "[   ]", "[ D ]", "", ""}, //9
            {"     ", "[ D ]", "[   ]", "[   ]", "[   ]", "[ V ]", "     ", "    ", "     ", "", ""} //10
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
                map[9][8] = redColor + "[ D ]" + resetColor;
                map[8][8] = redColor + "[ D ]" + resetColor;
                map[4][4] = redColor + "[ D ]" + resetColor;
                map[0][7] = redColor + "[ D ]" + resetColor;
                map[10][1] = redColor + "[ D ]" + resetColor;
                map[9][1] = redColor + "[ D ]" + resetColor;
                map[8][1] = redColor + "[ D ]" + resetColor;
                map[0][1] = redColor + "[ D ]" + resetColor;
            }

        }
        // Print the map
        System.out.println("Map: ");
        for (String[] row : map) {
            for (String cell : row) {
                if ("[ V ]".equals(cell)) {
                    System.out.print(greenColor + cell + resetColor + " ");
                } else if ("[ ! ]".equals(cell)) {
                    System.out.print(redColor + cell + resetColor + " ");
                } else if ("[ P ]".equals(cell)) {
                    System.out.print(yellowColor + cell + resetColor + " ");
                } else {
                    System.out.print(cell + " ");
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
        Main.loadSave();
    }

    public static Boolean hasRoomInInventory(int amount) {
        return inventorySize >= getTotalNumberOfItemsInInventory() + amount;
    }

    public static void playerSetSave(String localName, int localHealth, int localMaxHealth, int localGold, Map<String, Integer> localInventory, int localInventorySize) {
        name = localName;
        health = localHealth;
        maxHealth = localMaxHealth;
        gold = localGold;
        inventory = localInventory;
        inventorySize = localInventorySize;
    }
}
