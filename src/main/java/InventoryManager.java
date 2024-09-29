
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Inventory Manager
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class InventoryManager extends Player {

    public String room;
    public String area;
    //private static String[][] roomItemsMatrix = new String[2][2];
    //map format : itemname, quantity
    public static Map<String, Integer> Weapons = new HashMap<>();
    public static Map<String, Integer> Armor = new HashMap<>();
    public static Map<String, Integer> Potions = new HashMap<>();
    public static Map<String, Integer> Keys = new HashMap<>();

    public static void createItem(String type, String item,int value){
        switch(type) {
            case "weapon" -> {
                Weapons.put(item, value);
            }
            case "armor" -> {
                Armor.put(item, value);
            }
            case "potion" -> {
                Potions.put(item, value);
            }
            case "key" -> {
                Keys.put(item, value);
            }
            default -> {
                System.out.println("Invalid item type/n check createGameItems() in Main.java\nmake sure you set item type correctly");
            }
        }
    }
    public void printInventory() throws InterruptedException {
        int i = 1;
        TextEngine.clearScreen();
        Main.printStatus();
        if (inventory.isEmpty()) {
            TextEngine.printWithDelays("You have nothing in your inventory.", false);
            TextEngine.printWithDelays("Press Enter to continue", false);
            console.readLine();
            leave();
        } else {
            TextEngine.printWithDelays("You have the following items in your inventory:", false);
            Set<String> keys = inventory.keySet();
            for (String key : keys) {
                if (inventory.get(key) > 1) {
                    TextEngine.printNoDelay(i + ": " + key + " x" + inventory.get(key), false);
                } else {
                    TextEngine.printNoDelay(i + ": " + key, false);
                }
                i++;
            }
            TextEngine.printWithDelays("\n", false);
            inventoryManage();
        }
    }

    public void put(String item, int amount) throws InterruptedException {
        if(inventory.size() >= inventorySize){
            TextEngine.printWithDelays("You have no room in your inventory.", false);
            TextEngine.printWithDelays("You can only hold " + Player.inventorySize + " items. You have: "+inventory.size()+" items.", false);
            TextEngine.printWithDelays("You can drop items by typing 'drop' in \nthe 'inventory menu' to make room.", false);
            TextEngine.printWithDelays("Press Enter to continue", false);
            console.readLine();
            leave();
        }
        else {
            if (inventory.get(item) != null) {
                inventory.put(item, inventory.get(item) + amount);
            } else {
                inventory.put(item, amount);
            }
            TextEngine.printWithDelays("You have picked up " + amount + " " + item, false);
            TextEngine.printWithDelays("Press Enter to continue", false);
            console.readLine();
            leave();
        }
    }

    private static void inventoryManage() throws InterruptedException {
        TextEngine.printWithDelays("What would you like to do", false);
        TextEngine.printWithDelays("Use item, drop item, or exit ", false);
        TextEngine.printWithDelays("'use', 'drop', 'exit'", true);
        while (true) {
            console.readLine();
            command = console.readLine();
            switch (command) {
                case "use" -> {
                    break;
                }
                case "drop" -> {
                    TextEngine.printWithDelays("Which item would you like to drop?", true);
                    console.readLine();
                    command = console.readLine();
                    tossItem(command);

                }
                case "exit" -> {
                    TextEngine.clearScreen();
                    leave();
                    break;
                }
                default -> {
                    TextEngine.printWithDelays("Invalid command. Please try again.", true);
                    continue;
                }
            }
        }
    }

    private static void leave() throws InterruptedException {
        TextEngine.printWithDelays("Returning to last saved state.", false);
        TextEngine.clearScreen();
        Main.loadSave();
    }
    private static void tossItem(String item) throws InterruptedException {
        if (inventory.get(item) != null) {
            inventory.remove(item);
            TextEngine.printWithDelays("You have tossed " + item, false);
            TextEngine.printWithDelays("Press Enter to continue", false);
            console.readLine();
            Player.openInventory();
        } else {
            TextEngine.printWithDelays("You do not have that item.", false);
            TextEngine.printWithDelays("Press Enter to continue", false);
            console.readLine();
            Player.openInventory();
        }
    }
    // private static void getRoomList(){
    //     int room = Game.getRoomId();
    //     String area = Game.getSavedPlace();//rooms * areas
    //     String[][] roomItemsMatrix = new String[2][2];
    //     //formations of thougbhs n stuff idk gotta figure out how when you drop an item it is saved in that room
    // }
}
