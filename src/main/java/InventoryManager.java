import java.util.Set;

/**
 * Template SE374 F24 Final Project Caden Finley Albert Tucker Grijesh Shrestha
 */
//todo make inventory and health management
public class InventoryManager extends Player {
    public String room;
    public String area;
    //private static String[][] roomItemsMatrix = new String[2][2];

    public void printInventory() throws InterruptedException {
        int i = 1;
        if (inventory.isEmpty()) {
            TextEngine.clearScreen();
            Game.printStatus(); 
            TextEngine.printWithDelays("You have nothing in your inventory.", false);
            TextEngine.printWithDelays("Press Enter to continue", false);
            console.readLine();
            leave();
        } else {
            TextEngine.clearScreen();
            Game.printStatus(); 
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
        if (inventory.get(item) != null) {
            inventory.put(item, inventory.get(item) + amount);
        } else {
            inventory.put(item, amount);
        }
        TextEngine.printWithDelays("You have picked up " + amount + " " + item, false);
        TextEngine.printWithDelays("Press Enter to continue", false);
        console.readLine();
    }

    private static void inventoryManage() throws InterruptedException {
        TextEngine.printWithDelays("What would you like to do", false);
        TextEngine.printWithDelays("Use item, drop item, or exit ", false);
        TextEngine.printWithDelays("'use', 'drop', 'exit'", false);
        while (true) {
            console.readLine();
            command = console.readLine();
            switch (command) {
                case "use" -> {
                    break;
                }
                case "toss" -> {
                    TextEngine.printWithDelays("Which item would you like to toss?", true);
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
        Game.loadSave();
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


