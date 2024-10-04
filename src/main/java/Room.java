
import java.io.Console;

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

    public static boolean hasItemInRoom(String itemName, int quantity) throws InterruptedException {
        TextEngine.printWithDelays("Hey! There is a chest in this room! ", false);
        TextEngine.printWithDelays("What is your command open it or leave it", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "open it" -> {
                    TextEngine.printWithDelays("Hey! There is an item! ", false);
                    if (quantity > 1) {
                        TextEngine.printWithDelays("Item(s): " + itemName + " x" + quantity, false);
                    } else {
                        TextEngine.printWithDelays("Item: " + itemName, false);
                    }
                    TextEngine.printWithDelays("What is your command: take it or leave it", true);
                    while (true) {
                        ignore = console.readLine();
                        command = console.readLine();
                        switch (command.toLowerCase()) {
                            case "take it" -> {
                                Player.putItem(itemName, quantity);
                                Main.screenRefresh();
                                return true;
                            }
                            case "leave it" -> {
                                Main.screenRefresh();
                                return false;
                            }
                            default ->
                                Main.inGameDefaultTextHandling(command);
                        }
                    }
                }
                case "leave it" -> {
                    Main.screenRefresh();
                    return false;
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
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
