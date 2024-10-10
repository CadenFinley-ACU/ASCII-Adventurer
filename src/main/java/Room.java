
import java.io.Console;
import java.util.Random;

/**
 * Room Class
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class Room {

    public final static Console console = System.console();
    public static String command;
    public static String ignore;
    public static String room = null;

    public static boolean hasChestInRoom(String itemName, int quantity) throws InterruptedException {
        String resetColor = "\033[0m"; // reset to default color
        String yellowColor = "\033[1;33m"; // yellow color

        TextEngine.printWithDelays("Hey! There is a chest in this room! ", false);
        TextEngine.printWithDelays("What is your command" + yellowColor + " open it " + resetColor + "or " + yellowColor + "leave it" +resetColor, true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase().trim()) {
                case "open it" -> {
                    return hasItemInRoom(itemName, quantity);
                }
                case "leave it" -> {
                    return false;
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    public static boolean hasItemInRoom(String itemName, int quantity) throws InterruptedException {
        String resetColor = "\033[0m"; // reset to default color
        String yellowColor = "\033[1;33m"; // yellow color

        // Display item information
        TextEngine.printWithDelays("An item lies before you:", false);
        if (quantity > 1) {
            TextEngine.printWithDelays("Item(s): " + itemName + " x" + quantity, false);
        } else {
            TextEngine.printWithDelays("Item: " + itemName, false);
        }

        // Highlight 'take it' and 'leave it' in yellow
        TextEngine.printWithDelays("What will you do? Type " + yellowColor + "take it" + resetColor + " to pick up the sword or " + yellowColor + "leave it"+resetColor, true);

        // Command handling loop
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase().trim()) {
                case "take it" -> {
                    return Player.putItem(itemName, quantity);
                }
                case "leave it" -> {
                    return false;
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    public static void trappedRoom() throws InterruptedException {
        TextEngine.printWithDelays("You have entered a trapped room! ", false);
        int hit = new Random().nextBoolean() ? 1 : 2;
        if (hit == 1) {
            TextEngine.printWithDelays("You have been hit by a trap! ", false);
            Player.changeHealth(-(Player.getHealth() / 4));
        } else {
            TextEngine.printWithDelays("You avoid the trap! ", false);
            TextEngine.enterToNext();
        }
    }

    public static void reset(String area) { //reset the room
        room = null;
        switch (area) {
            case "SpawnRoom" -> {
                SpawnRoom.resetAll();
            }
            case "OpenWorld" -> {
                OpenWorld.resetAll();
            }
            case "all" -> {
                SpawnRoom.resetAll();
                OpenWorld.resetAll();
                Dungeon.resetAll();
            }
        }
    }
}
