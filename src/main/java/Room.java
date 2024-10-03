
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

    public static void hasItemInRoom(String itemName, int quantity) throws InterruptedException {    //check if there is an item in the room
        TextEngine.printWithDelays("Hey! There is an item in this room: ", false);
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
                    return;
                }
                case "leave it" -> {
                    Main.screenRefresh();
                    return;
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
