
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ASCIIADVENTURER Caden Finley Albert Tucker Grijesh Shrestha
 *
 * This class manages the inventory of a player, including weapons, armor,
 * potions, and keys. It provides methods to create items and print the current
 * inventory.
 *
 * @author ASCIIADVENTURERS
 * @version 1.0
 */
public class InventoryManager extends Player {

    public static Map<String, Integer> Weapons = new HashMap<>();
    public static Map<String, Integer> Armor = new HashMap<>();
    public static Map<String, Integer> Potions = new HashMap<>();
    public static Map<String, Integer> Keys = new HashMap<>();

    /**
     * Creates an item and adds it to the appropriate inventory based on the
     * type.
     *
     * @param type The type of the item (e.g., "weapon", "armor", "potion",
     * "key").
     * @param item The name of the item to be created.
     * @param value The value associated with the item.
     */
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

    /**
     * Prints the current inventory of the player.
     *
     * This method clears the screen, prints the player's status, and then
     * checks if the inventory is empty. If the inventory is empty, it prints a
     * message indicating that and then exits. If the inventory is not empty, it
     * prints the total number of items in the inventory and lists each item.
     * For each item, it prints the quantity and additional information such as
     * damage for weapons or defense for armor. Finally, it calls the inventory
     * management method.
     *
     * @throws InterruptedException if the thread is interrupted while printing
     * with delays
     */
    public static void printInventory() throws InterruptedException {
        // Clear the screen and print the player's status
        TextEngine.clearScreen();
        GameEngine.printStatus();

        // Check if the inventory is empty
        if (inventory.isEmpty()) {
            TextEngine.printWithDelays("You have nothing in your inventory.", false);
            TextEngine.enterToNext();
            leave();
        } else {
            // Print the total number of items in the inventory
            TextEngine.printNoDelay("Inventory: " + getTotalNumberOfItemsInInventory() + "/" + Player.getInventorySize(), false);
            TextEngine.printWithDelays("You have the following items in your inventory:", false);

            // Iterate through the inventory and print each item
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

            // Print a new line and call the inventory management method
            TextEngine.printWithDelays("\n", false);
            inventoryManage();
        }
    }

    /**
     * Checks if there is enough room in the inventory to add a specified amount
     * of items.
     *
     * @param amount The number of items to be added to the inventory.
     * @return true if there is enough room in the inventory to add the
     * specified amount of items, false otherwise.
     * @throws InterruptedException if the thread is interrupted while printing
     * messages.
     */
    public static boolean inventoryHasRoom(int amount) throws InterruptedException {
        if (getTotalNumberOfItemsInInventory() + amount > getInventorySize()) {
            TextEngine.printWithDelays("You have no room in your inventory.", false);
            TextEngine.printWithDelays("You can only hold " + Player.getInventorySize() + " items. You have: " + getTotalNumberOfItemsInInventory() + " items.", false);
            TextEngine.printWithDelays("You can drop items by typing " + yellowColor + "drop" + resetColor + " in \nthe " + yellowColor + "inventory" + resetColor + "menu to make room.", false);
            TextEngine.enterToNext();
        }
        return getTotalNumberOfItemsInInventory() + amount <= getInventorySize();
    }

    /**
     * Adds a specified amount of an item to the inventory.
     *
     * @param item The name of the item to add.
     * @param amount The amount of the item to add. If the amount is negative,
     * the method returns without adding the item.
     * @throws InterruptedException If the thread is interrupted while printing
     * messages.
     */
    public static void giveItem(String item, int amount) throws InterruptedException {
        // Check if there is room in the inventory for the specified amount
        if (inventoryHasRoom(amount)) {
            // If the item already exists in the inventory, update its amount
            if (inventory.get(item) != null) {
                inventory.put(item, inventory.get(item) + amount);
            } else {
                // Otherwise, add the item to the inventory with the specified amount
                inventory.put(item, amount);
            }
            // If the amount of the item is zero or less, remove it from the inventory
            if (inventory.get(item) <= 0) {
                inventory.remove(item);
            }
            // Update the stats to the highest values in the inventory
            setStatsToHighestInInventory();
            // Print a message indicating the item has been picked up
            if (amount > 1) {
                TextEngine.printWithDelays("You have picked up " + amount + " " + yellowColor + item + "s" + resetColor + "!", false);
            } else if (amount < 0) {
                return;
            } else {
                TextEngine.printWithDelays("You have picked up " + amount + " " + yellowColor + item + resetColor + "!", false);
            }
            // Wait for the user to press enter to continue
            TextEngine.enterToNext();
        }
    }

    /**
     * Manages the inventory by allowing the user to use, drop, or exit the
     * inventory menu. This method continuously prompts the user for a command
     * and performs the corresponding action.
     *
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    private static void inventoryManage() throws InterruptedException {
        // Prompt the user for an action
        TextEngine.printWithDelays("What would you like to do", false);
        TextEngine.printWithDelays("Use item, drop item, or exit ", false);
        TextEngine.printWithDelays(yellowColor + "use" + resetColor + ", " + yellowColor + "drop" + resetColor + ", " + yellowColor + "exit" + resetColor, true);

        // Continuously read user commands and perform actions
        while (true) {
            command = console.readLine();
            switch (command) {
                case "use" -> {
                    // Clear the screen and prompt the user to select an item to use
                    TextEngine.clearScreen();
                    printInventoryNoMenu();
                    TextEngine.printWithDelays("Which item would you like to use?", true);
                    command = console.readLine();
                    useItem(command);
                }
                case "drop" -> {
                    // Clear the screen and prompt the user to select an item to drop
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
                    // Clear the screen and exit the inventory menu
                    TextEngine.clearScreen();
                    leave();
                    break;
                }
                default -> {
                    // Handle any other commands
                    GameEngine.inGameDefaultTextHandling(command);
                }
            }
        }
    }

    /**
     * Clears the screen and loads the saved state of the main application.
     *
     * @throws InterruptedException if the thread is interrupted while clearing
     * the screen.
     */
    private static void leave() throws InterruptedException {
        //leave the inventory
        TextEngine.clearScreen();
        GameEngine.loadSave();
    }

    /**
     * Tosses an item from the player's inventory.
     *
     * @param item The name of the item to be tossed.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    private static void tossItem(String item) throws InterruptedException { //toss an item
        int amount = 1;

        // Check if the item is a key, which cannot be dropped
        if ("key".equals(item)) {
            TextEngine.printWithDelays("You cannot drop the key.", false);
            TextEngine.enterToNext();
            Player.openInventory();
            return;
        }

        // Check if the item is in the inventory
        if (inventory.get(item) == null) {
            TextEngine.printWithDelays("You do not have that item.", false);
            TextEngine.enterToNext();
            Player.openInventory();
            return;
        }

        // If the item quantity is more than 1, ask the player how many to toss
        if (inventory.get(item) > 1) {
            TextEngine.printWithDelays("How many would you like to toss?\n" + getIndividualItemString(item), true);
            command = console.readLine();
            try {
                Integer.valueOf(command);
            } catch (NumberFormatException e) {
                GameEngine.invalidCommand();
                TextEngine.enterToNext();
                Player.openInventory();
                return;
            }

            // Check if the player has enough items to toss
            if (Integer.valueOf(command) > inventory.get(item)) {
                TextEngine.printWithDelays("You do not have that many items.", false);
                TextEngine.enterToNext();
                Player.openInventory();
                return;
            } else {
                amount = Integer.parseInt(command);
            }
        }

        // Update the inventory by reducing the item quantity
        inventory.put(item, inventory.get(item) - amount);
        setStatsToHighestInInventory();

        // Remove the item from the inventory if the quantity is zero or less
        if (inventory.get(item) <= 0) {
            inventory.remove(item);
            setStatsToHighestInInventory();
        }

        // Print the message indicating the number of items tossed
        if (amount > 1) {
            TextEngine.printWithDelays("You have tossed " + amount + " x" + item + "s", false);
        } else {
            TextEngine.printWithDelays("You have tossed " + amount + " x" + item, false);
        }

        TextEngine.enterToNext();
        Player.openInventory();
    }

    /**
     * Uses an item from the inventory if it is a potion and the player's health
     * is not at maximum.
     *
     * @param item The name of the item to be used.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    public static void useItem(String item) throws InterruptedException {
        // Check if the item is a potion, the player's health is not at maximum, and the item is not a heart container
        if (Potions.containsKey(item) && Player.getHealth() < Player.getMaxHealth() && !"heart container".equals(item)) {
            // Change the player's health based on the potion's effect
            Player.changeHealth(Potions.get(item));
            // Decrease the quantity of the item in the inventory
            inventory.put(item, inventory.get(item) - 1);
            // Remove the item from the inventory if its quantity is zero
            if (inventory.get(item) == 0) {
                inventory.remove(item);
            }
            // Update the player's stats to the highest values in the inventory
            setStatsToHighestInInventory();
            // Reopen the player's inventory
            Player.openInventory();
        } else {
            // Inform the player that the item cannot be used
            TextEngine.printWithDelays("You cannot use that item.", false);
            TextEngine.enterToNext();
            // Reopen the player's inventory
            Player.openInventory();
        }
    }

    /**
     * Sets the player's damage and defense stats to the highest values found in
     * the inventory.
     *
     * This method iterates through the player's inventory and checks each item
     * to see if it is a weapon or armor. If the item is a weapon and its damage
     * value is higher than the player's current damage, the player's damage is
     * updated. Similarly, if the item is armor and its defense value is higher
     * than the player's current defense, the player's defense is updated.
     *
     * The player's damage and defense are initially set to 0 before the
     * inventory is checked.
     */
    public static void setStatsToHighestInInventory() {
        // Set the player's initial damage and defense to 0
        Player.setDamage(0);
        Player.setDefense(0);

        // Get the set of keys from the inventory
        Set<String> keys = inventory.keySet();

        // Iterate through each key in the inventory
        for (String key : keys) {
            // Check if the key corresponds to a weapon
            if (Weapons.containsKey(key)) {
                // Update the player's damage if the weapon's damage is higher
                if (Weapons.get(key) > Player.getDamage()) {
                    Player.setDamage(Weapons.get(key));
                }
            }
            // Check if the key corresponds to armor
            if (Armor.containsKey(key)) {
                // Update the player's defense if the armor's defense is higher
                if (Armor.get(key) > Player.getDefense()) {
                    Player.setDefense(Armor.get(key));
                }
            }
        }
    }

    /**
     * Prints the inventory items without displaying the menu.
     *
     * This method checks if the inventory is empty. If it is, the method calls
     * the leave() function. If the inventory is not empty, it prints the total
     * number of items in the inventory and the player's inventory size. It then
     * iterates through the inventory and prints each item. If an item is a
     * weapon, it prints the item's damage. If an item is armor, it prints the
     * item's defense. If an item is neither, it just prints the item's name. If
     * there are multiple quantities of an item, it prints the quantity as well.
     *
     * @throws InterruptedException if the thread is interrupted while printing
     * with delays
     */
    public static void printInventoryNoMenu() throws InterruptedException {
        // Check if the inventory is empty
        if (inventory.isEmpty()) {
            leave();
        } else {
            // Print the total number of items in the inventory and the player's inventory size
            TextEngine.printNoDelay("Inventory: " + getTotalNumberOfItemsInInventory() + "/" + Player.getInventorySize(), false);
            TextEngine.printWithDelays("You have the following items in your inventory:", false);

            // Get the set of keys from the inventory
            Set<String> keys = inventory.keySet();

            // Iterate through each key in the inventory
            for (String key : keys) {
                // Check if there are multiple quantities of the item
                if (inventory.get(key) > 1) {
                    // Print the item with its quantity and stats if applicable
                    if (Weapons.containsKey(key)) {
                        TextEngine.printNoDelay(" " + inventory.get(key) + " x " + key + " Damage: " + Weapons.get(key), false);
                    } else if (Armor.containsKey(key)) {
                        TextEngine.printNoDelay(" " + inventory.get(key) + " x " + key + " Defense: " + Armor.get(key), false);
                    } else {
                        TextEngine.printNoDelay(" " + inventory.get(key) + " x " + key, false);
                    }
                } else {
                    // Print the item with its stats if applicable
                    if (Weapons.containsKey(key)) {
                        TextEngine.printNoDelay(" " + key + " Damage: " + Weapons.get(key), false);
                    } else if (Armor.containsKey(key)) {
                        TextEngine.printNoDelay(" " + key + " Defense: " + Armor.get(key), false);
                    } else {
                        TextEngine.printNoDelay(" " + key, false);
                    }
                }
            }
            // Print a blank line for spacing
            TextEngine.printWithDelays(" ", false);
        }
    }

    /**
     * Returns a string representation of an individual item in the inventory.
     * If the item quantity is greater than 1, the string will include the
     * quantity.
     *
     * @param item the name of the item
     * @return a string representing the item and its quantity if greater than 1
     */
    public static String getIndividualItemString(String item) { //get the individual item string name
        if (inventory.get(item) > 1) {
            return inventory.get(item).toString() + " x " + item;
        }
        return item + " ";
    }

    /**
     * Retrieves the value associated with a given item from the inventory. The
     * method checks in the following order: Weapons, Armor, Potions, and Keys.
     * If the item is found in any of these categories, its value is returned.
     * If the item is not found in any category, the method returns 0.
     *
     * @param item the name of the item to look up
     * @return the value associated with the item, or 0 if the item is not found
     */
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

    /**
     * Uses an item from the player's inventory without displaying a menu. This
     * method is intended to be used with healing potions and keys. If the item
     * is a potion (excluding "heart container"), it will heal the player and
     * decrease the quantity of the item in the inventory. If the quantity
     * reaches zero, the item is removed from the inventory. The player's stats
     * are then updated to reflect the highest values in the inventory. If the
     * item cannot be used, a message is displayed to the player.
     *
     * @param item the name of the item to use
     * @throws InterruptedException if the thread is interrupted while
     * displaying messages
     */
    public static void useItemNoMenu(String item) throws InterruptedException { //this only should run with Player.heal() and keys
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

    /**
     * Attempts to use a key from the player's inventory.
     *
     * This method checks if the player's inventory contains at least one key.
     * If a key is found, it decrements the key count by one. If the key count
     * reaches zero after decrementing, the key is removed from the inventory.
     *
     * @return true if a key was successfully used, false otherwise.
     */
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
