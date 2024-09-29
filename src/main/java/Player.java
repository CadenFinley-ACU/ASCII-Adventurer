
import java.io.Console;
import java.util.HashMap;
import java.util.Map;

/**
 * Text Engine
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
//todo make inventory and health management **in progress**
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

    public static void playerStart() throws InterruptedException {
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
    public static void debugStart() throws InterruptedException{
        maxHealth = 1;
        health = maxHealth;
        damage = 0;
        defense = 0;
        gold = 20000000;
        inventorySize = 200;
        name = "Debug!";
        Main.playerCreated = true;
        TextEngine.printNoDelay("Where do you want to spawn", false);
        TextEngine.printNoDelay("1: SpawnRoom", false);
        TextEngine.printNoDelay("2: OpenWorld", false);
        TextEngine.printNoDelay("3: Dungeon", false);
        TextEngine.printNoDelay("4: Village", false);
        TextEngine.printNoDelay("debug spawn: ", true);
        ignore = console.readLine();
        command = console.readLine();
        switch (command){
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
                TextEngine.printNoDelay("2: Dark Forest", false);
                TextEngine.printNoDelay("debug dungeon: ", true);
                ignore = console.readLine();
                command = console.readLine();
                switch (command){
                    case "1" -> {
                        Dungeon.initDungeon("Meadow");
                    }
                    case "2" -> {
                        Dungeon.initDungeon("Dark Forest");
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

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Player.name = name;
    }

    public static int getHealth() {
        return health;
    }
    public static int getMaxHealth(){
        return maxHealth;
    }
    public static int getGold() {
        return gold;
    }
    public static void changeInventorySize(int change) throws InterruptedException {
        inventorySize += change;
    }
    public static void changeHealth(int change) throws InterruptedException {
        int damageCalc = (defense + (damage/2));
        if(change<0) {
            change += damageCalc;
            if (change >= 0) {
                change = -1;
            }
            TextEngine.printWithDelays("You took " + change + " damage!", false);
            TextEngine.printWithDelays("Press Enter to continue", false);
            console.readLine();
        }
        health += change;
        if(health>maxHealth){
            health = maxHealth;
        }
        if(health<=0){
            TextEngine.printWithDelays("You have died!", false);
            TextEngine.printWithDelays("Game Over!", false);
            TextEngine.printWithDelays("Press Enter to Continue", false);
            ignore = console.readLine();
            Main.screenRefresh();
            Main.startMenu();
        }
    }
    public static void changeMaxHealth(int change) throws InterruptedException {
        Main.screenRefresh();
        maxHealth += change;
        health += change;
    }
    public static void changeGold(int change) throws InterruptedException {
        Main.screenRefresh();
        gold += change;
    }
    public static void openInventory() throws InterruptedException {
        manager.printInventory();
    }
    public static void setDamage(int amount){
        damage = amount;
    }
    public static void setDefense(int amount){
        defense = amount;
    }
    public static int getDamage(){
        return damage;
    }
    public static int getDefense(){
        return defense;
    }
    public static void putItem(String item, int amount) throws InterruptedException {
        manager.put(item, amount);
    }
    private static void playerCreate() throws InterruptedException {
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
        TextEngine.printWithDelays("Make sure you always press enter when prompted to! (press enter to continue)", false);
        ignore = console.readLine();
        TextEngine.printWithDelays("Good luck! (press enter to continue)", false);
        ignore = console.readLine();
        TextEngine.clearScreen();
        Main.playerCreated = true;
        Main.start();
    }
    public static void printStats() throws InterruptedException{
        InventoryManager.setStatsToHighestInInventory();
        TextEngine.clearScreen();
        TextEngine.printNoDelay("Player Stats:", false);
        TextEngine.printNoDelay("Name: "+name, false);
        TextEngine.printNoDelay("Health: "+health+"/"+maxHealth, false);
        TextEngine.printNoDelay("Gold: "+gold, false);
        TextEngine.printNoDelay("Damage: "+damage, false);
        TextEngine.printNoDelay("Defense: "+defense, false);
        TextEngine.printNoDelay("Inventory: "+inventory.size()+"/"+inventorySize, false);
        TextEngine.printNoDelay("(Press Enter to Leave menu)", false);
        ignore = console.readLine();
        Main.loadSave();
    }
}
