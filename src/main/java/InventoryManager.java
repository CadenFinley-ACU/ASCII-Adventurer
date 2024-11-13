
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

    static String resetColor = "\033[0m"; // reset to default color
    static String yellowColor = "\033[1;33m"; // yellow color
    // public String room;
    // public String area;
    //inventory format is itemname, amount
    //map format : itemname, damage
    public static Map<String, Integer> Weapons = new HashMap<>();
    public static Map<String, Integer> Armor = new HashMap<>();
    public static Map<String, Integer> Potions = new HashMap<>();
    public static Map<String, Integer> Keys = new HashMap<>();

    public static void createItem(String type, String item, int value) { //create an item
        switch (type) {
            case "weapon" ->
                Weapons.put(item, value);
            case "armor" ->
                Armor.put(item, value);
            case "potion" ->
                Potions.put(item, value);
            case "key" ->
                Keys.put(item, value);
            default ->
                System.out.println("Invalid item type/n check createGameItems() in Main.java\nmake sure you set item type correctly");
        }
    }

    public static void printInventory() throws InterruptedException { //print the inventory
        TextEngine.clearScreen();
        Main.printStatus();
        if (inventory.isEmpty()) {
            TextEngine.printWithDelays("You have nothing in your inventory.", false);
            TextEngine.enterToNext();
            leave();
        } else {
            TextEngine.printNoDelay("Inventory: " + getTotalNumberOfItemsInInventory() + "/" + Player.inventorySize, false);
            TextEngine.printWithDelays("You have the following items in your inventory:", false);
            Set<String> keys = inventory.keySet();
            for (String key : keys) {
                if (inventory.get(key) > 1) {
                    if (Weapons.containsKey(key)) {
                        TextEngine.printNoDelay(" " + inventory.get(key) + " x " + key + " Damage: " + Weapons.get(key), false);
                    } else if (Armor.containsKey(key)) {
                        TextEngine.printNoDelay(" " + inventory.get(key) + " x " + "Defense:" + Armor.get(key), false);
                    } else {
                        TextEngine.printNoDelay(" " + inventory.get(key) + " x " + key, false);
                    }
                } else {
                    if (Weapons.containsKey(key)) {
                        TextEngine.printNoDelay(" " + key + " Damage: " + Weapons.get(key), false);
                    } else if (Armor.containsKey(key)) {
                        TextEngine.printNoDelay(" " + key + " Defense: " + Armor.get(key), false);
                    } else {
                        TextEngine.printNoDelay(" " + key, false);
                    }
                }
            }
            TextEngine.printWithDelays("\n", false);
            inventoryManage();
        }
    }

    public static boolean inventoryHasRoom(int amount) throws InterruptedException {
        if (getTotalNumberOfItemsInInventory() + amount > inventorySize) {
            TextEngine.printWithDelays("You have no room in your inventory.", false);
            TextEngine.printWithDelays("You can only hold " + Player.inventorySize + " items. You have: " + getTotalNumberOfItemsInInventory() + " items.", false);
            TextEngine.printWithDelays("You can drop items by typing " + yellowColor + "drop" + resetColor + " in \nthe " + yellowColor + "inventory" + resetColor + "menu to make room.", false);
            TextEngine.enterToNext();
        }
        return getTotalNumberOfItemsInInventory() + amount <= inventorySize;
    }

    public static void put(String item, int amount) throws InterruptedException { //put an item in the inventory
        if (inventoryHasRoom(amount)) {
            if (inventory.get(item) != null) {
                inventory.put(item, inventory.get(item) + amount);
            } else {
                inventory.put(item, amount);
            }
            if (inventory.get(item) <= 0) {
                inventory.remove(item);
            }
            setStatsToHighestInInventory();
            if (amount > 1) {
                TextEngine.printWithDelays("You have picked up " + amount + " " + yellowColor + item + "s" + resetColor + "!", false);
            } else if (amount < 0) {
                return;
            } else {
                TextEngine.printWithDelays("You have picked up " + amount + " " + yellowColor + item + resetColor + "!", false);
            }
            TextEngine.enterToNext();
        }
    }

    private static void inventoryManage() throws InterruptedException { //manage the inventory
        TextEngine.printWithDelays("What would you like to do", false);
        TextEngine.printWithDelays("Use item, drop item, or exit ", false);
        TextEngine.printWithDelays(yellowColor + "use" + resetColor + ", " + yellowColor + "drop" + resetColor + ", " + yellowColor + "exit" + resetColor, true);
        while (true) {
            command = console.readLine();
            switch (command) {
                case "use" -> {
                    TextEngine.clearScreen();
                    printInventoryNoMenu();
                    TextEngine.printWithDelays("Which item would you like to use?", true);
                    command = console.readLine();
                    useItem(command);
                }
                case "drop" -> {
                    TextEngine.clearScreen();
                    printInventoryNoMenu();
                    TextEngine.printWithDelays("Which item would you like to drop? (or " + yellowColor + "leave" + resetColor + ")", true);
                    command = console.readLine();
                    if (command.equals("leave")) {
                        Player.openInventory();
                    }
                    tossItem(command);
                }
                case "exit" -> {
                    TextEngine.clearScreen();
                    leave();
                    break;
                }
                default -> {
                    Main.inGameDefaultTextHandling(command);
                }
            }
        }
    }

    private static void leave() throws InterruptedException { //leave the inventory
        TextEngine.clearScreen();
        Main.loadSave();
    }

    private static void tossItem(String item) throws InterruptedException { //toss an item
        int amount = 1;
        if ("key".equals(item)) {
            TextEngine.printWithDelays("You cannot drop the key.", false);
            TextEngine.enterToNext();
            Player.openInventory();
            return;
        }
        if (inventory.get(item) == null) {
            TextEngine.printWithDelays("You do not have that item.", false);
            TextEngine.enterToNext();
            Player.openInventory();
            return;
        }
        if (inventory.get(item) > 1) {
            TextEngine.printWithDelays("How many would you like to toss?\n" + getIndividualItemString(item), true);
            command = console.readLine();
            try {
                Integer.valueOf(command);
            } catch (NumberFormatException e) {
                Main.invalidCommand();
                TextEngine.enterToNext();
                Player.openInventory();
                return;
            }
            if (Integer.valueOf(command) > inventory.get(item)) {
                TextEngine.printWithDelays("You do not have that many items.", false);
                TextEngine.enterToNext();
                Player.openInventory();
                return;
            } else {
                amount = Integer.parseInt(command);
            }
        }
        inventory.put(item, inventory.get(item) - amount);
        setStatsToHighestInInventory();
        if (inventory.get(item) <= 0) {
            inventory.remove(item);
            setStatsToHighestInInventory();
        }
        if (amount > 1) {
            TextEngine.printWithDelays("You have tossed " + amount + " x" + item + "s", false);
        } else {
            TextEngine.printWithDelays("You have tossed " + amount + " x" + item, false);
        }
        TextEngine.enterToNext();
        Player.openInventory();
    }

    public static void useItem(String item) throws InterruptedException { //this only shouls run with potions as those are the only items you can use from the inventory menu
        if (Potions.containsKey(item) && Player.getHealth() < Player.getMaxHealth() && !"heart container".equals(item)) {
            Player.changeHealth(Potions.get(item));
            inventory.put(item, inventory.get(item) - 1);
            if (inventory.get(item) == 0) {
                inventory.remove(item);
            }
            setStatsToHighestInInventory();
            Player.openInventory();
        } else {
            TextEngine.printWithDelays("You cannot use that item.", false);
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

    public static void printInventoryNoMenu() throws InterruptedException { //print the inventory items without the menu
        if (inventory.isEmpty()) {
            leave();
        } else {
            TextEngine.printNoDelay("Inventory: " + getTotalNumberOfItemsInInventory() + "/" + Player.inventorySize, false);
            TextEngine.printWithDelays("You have the following items in your inventory:", false);
            Set<String> keys = inventory.keySet();
            for (String key : keys) {
                if (inventory.get(key) > 1) {
                    if (Weapons.containsKey(key)) {
                        TextEngine.printNoDelay(" " + inventory.get(key) + " x " + key + " Damage: " + Weapons.get(key), false);
                    } else if (Armor.containsKey(key)) {
                        TextEngine.printNoDelay(" " + inventory.get(key) + " x " + "Defense:" + Armor.get(key), false);
                    } else {
                        TextEngine.printNoDelay(" " + inventory.get(key) + " x " + key, false);
                    }
                } else {
                    if (Weapons.containsKey(key)) {
                        TextEngine.printNoDelay(" " + key + " Damage: " + Weapons.get(key), false);
                    } else if (Armor.containsKey(key)) {
                        TextEngine.printNoDelay(" " + key + " Defense: " + Armor.get(key), false);
                    } else {
                        TextEngine.printNoDelay(" " + key, false);
                    }
                }
            }
            TextEngine.printWithDelays(" ", false);
        }
    }

    public static String getIndividualItemString(String item) { //get the individual item string name
        if (inventory.get(item) > 1) {
            return inventory.get(item).toString() + " x " + item;
        }
        return item + " ";
    }

    public static int getKeyValue(String item) {
        if (Weapons.containsKey(item)) {
            return Weapons.get(item);
        } else if (Armor.containsKey(item)) {
            return Armor.get(item);
        } else if (Potions.containsKey(item)) {
            return Potions.get(item);
        } else if (Keys.containsKey(item)) {
            return Keys.get(item);
        }
        return 0; // or any other default value
    }

    public static void useItemNoMenu(String item) throws InterruptedException { //this only shouls run with Player.heal() and keys
        if (Potions.containsKey(item) && !"heart container".equals(item)) {
            Player.changeHealth(Potions.get(item));
            Player.inventory.put(item, inventory.get(item) - 1);
            if (Player.inventory.get(item) == 0) {
                Player.inventory.remove(item);
            }
            setStatsToHighestInInventory();
        } else {
            TextEngine.printWithDelays("You cannot use that item.", false);
            TextEngine.enterToNext();
        }
    }

    public static boolean useKey() {
        if (Player.inventory.containsKey("key") && Player.inventory.get("key") > 0) {
            Player.inventory.put("key", inventory.get("key") - 1);
            if (Player.inventory.get("key") == 0) {
                Player.inventory.remove("key");
            }
            return true;
        }
        return false;
    }
}
