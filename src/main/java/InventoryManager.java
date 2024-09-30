
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
    //inventory format is itemname, amount
    //map format : itemname, damage
    public static Map<String, Integer> Weapons = new HashMap<>();
    public static Map<String, Integer> Armor = new HashMap<>();
    public static Map<String, Integer> Potions = new HashMap<>();
    public static Map<String, Integer> Keys = new HashMap<>();

    public static void createItem(String type, String item,int value){ //create an item
        switch(type) {
            case "weapon" -> Weapons.put(item, value);
            case "armor" -> Armor.put(item, value);
            case "potion" -> Potions.put(item, value);
            case "key" -> Keys.put(item, value);
            default -> System.out.println("Invalid item type/n check createGameItems() in Main.java\nmake sure you set item type correctly");   
        }
    }
    public void printInventory() throws InterruptedException { //print the inventory
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
                    if (Weapons.containsKey(key)) {
                        TextEngine.printNoDelay(i + ": " + key + " x" + inventory.get(key)+" Damage: " + Weapons.get(key), false);
                    }
                    else if (Armor.containsKey(key)) {
                        TextEngine.printNoDelay(" Defense: " + Armor.get(key)+" Defense: " + Armor.get(key), false);
                    }
                    else {
                        TextEngine.printNoDelay(i + ": " + key + " x" + inventory.get(key), false);
                    }
                } else {
                    if (Weapons.containsKey(key)) {
                        TextEngine.printNoDelay(i + ": " + key+" Damage: " + Weapons.get(key), false);
                    }
                    else if (Armor.containsKey(key)) {
                        TextEngine.printNoDelay(i + ": " + key+" Defense: " + Armor.get(key), false);
                    }
                    else {
                        TextEngine.printNoDelay(i + ": " + key, false);
                    }
                }
                i++;
            }
            TextEngine.printWithDelays("\n", false);
            inventoryManage();
        }
    }

    public void put(String item, int amount) throws InterruptedException { //put an item in the inventory
        if(inventory.size() >= inventorySize){
            TextEngine.printWithDelays("You have no room in your inventory.", false);
            TextEngine.printWithDelays("You can only hold " + Player.inventorySize + " items. You have: "+inventory.size()+" items.", false);
            TextEngine.printWithDelays("You can drop items by typing 'drop' in \nthe 'inventory menu' to make room.", false);
            TextEngine.enterToNext();
        }
        else {
            if (inventory.get(item) != null) {
                inventory.put(item, inventory.get(item) + amount);     
            } else {
                inventory.put(item, amount);        
            }
            setStatsToHighestInInventory();
            TextEngine.printWithDelays("You have picked up " + amount + " " + item, false);
            TextEngine.enterToNext();
        }
    }

    private static void inventoryManage() throws InterruptedException { //manage the inventory
        TextEngine.printWithDelays("What would you like to do", false);
        TextEngine.printWithDelays("Use item, drop item, or exit ", false);
        TextEngine.printWithDelays("'use', 'drop', 'exit'", true);
        while (true) {
            console.readLine();
            command = console.readLine();
            switch (command) {
                case "use" -> {
                    TextEngine.printWithDelays("Which item would you like to use?", false);
                    printInventoryNoMenu();
                    console.readLine();
                    command = console.readLine();
                    if (Potions.containsKey(command)) {
                        Player.changeHealth(Potions.get(command));
                        TextEngine.printWithDelays("You have used " + command, false);
                        TextEngine.enterToNext();
                        inventory.put(command, inventory.get(command) - 1);
                        if (inventory.get(command) == 0) {
                            inventory.remove(command);
                        }
                        setStatsToHighestInInventory();
                    } else {
                        TextEngine.printWithDelays("You cannot use that item.", false);
                        TextEngine.enterToNext();
                        Player.openInventory();
                    }
                }
                case "drop" -> {
                    TextEngine.printWithDelays("Which item would you like to drop?", false);
                    printInventoryNoMenu();
                    console.readLine();
                    command = console.readLine();
                    tossItem(command);
                    if(Weapons.containsKey(command)){
                        if(Weapons.get(command) == Player.getDamage()){
                            Player.setDamage(-Weapons.get(command));
                        }
                    }
                    setStatsToHighestInInventory();
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

    private static void leave() throws InterruptedException { //leave the inventory
        TextEngine.clearScreen();
        Main.loadSave();
    }
    private static void tossItem(String item) throws InterruptedException { //toss an item
        int amount =1;
        if (inventory.get(item) != null) {
            if(inventory.get(item) > 1){
                TextEngine.printWithDelays("How many would you like to toss?\n" + getIndividualItemString(item), true);
                console.readLine();
                command = console.readLine();
                amount = Integer.parseInt(command);
                inventory.put(item, inventory.get(item) - amount);  
            }
            else{
                inventory.remove(item);
            }
            TextEngine.printWithDelays("You have tossed " + item+" " + amount, false);
            TextEngine.enterToNext();
            Player.openInventory();
        } else {
            TextEngine.printWithDelays("You do not have that item.", false);
            TextEngine.enterToNext();
            Player.openInventory();
        }
    }
    public static void setStatsToHighestInInventory() { //set the stats to the highest in the inventory
        Player.setDamage(0);
        Player.setDefense(0);
        Set<String> keys = inventory.keySet();
        for (String key : keys) {
            if (Weapons.containsKey(key)) {
                if (Weapons.get(key) > Player.getDamage()) {
                    Player.setDamage(Weapons.get(key));
                }
            }
            if (Armor.containsKey(key)) {
                if (Armor.get(key) > Player.getDefense()) {
                    Player.setDefense(Armor.get(key));
                }
            }
        } 
    }
    public static void printInventoryNoMenu() throws InterruptedException{ //print the inventory without the menu
        int i = 1;
        if (inventory.isEmpty()) {
            leave();
        } else {
            TextEngine.printNoDelay("You have the following items in your inventory:", false);
            Set<String> keys = inventory.keySet();
            for (String key : keys) {
                if (inventory.get(key) > 1) {
                    if (Weapons.containsKey(key)) {
                        TextEngine.printNoDelay(i + ": " + key + " x" + inventory.get(key)+" Damage: " + Weapons.get(key), false);
                    }
                    else if (Armor.containsKey(key)) {
                        TextEngine.printNoDelay(" Defense: " + Armor.get(key)+" Defense: " + Armor.get(key), false);
                    }
                    else {
                        TextEngine.printNoDelay(i + ": " + key + " x" + inventory.get(key), false);
                    }
                } else {
                    if (Weapons.containsKey(key)) {
                        TextEngine.printNoDelay(i + ": " + key+" Damage: " + Weapons.get(key), false);
                    }
                    else if (Armor.containsKey(key)) {
                        TextEngine.printNoDelay(i + ": " + key+" Defense: " + Armor.get(key), false);
                    }
                    else {
                        TextEngine.printNoDelay(i + ": " + key, false);
                    }
                }
                i++;
            }
            TextEngine.printWithDelays("", true);
        }
    }
    public static String getIndividualItemString(String item) {
        return item + " " + inventory.get(item);
    }
}
