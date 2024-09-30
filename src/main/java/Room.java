
import java.io.Console;
import java.util.HashMap;
import java.util.Map;

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
    public static final Map<String, Integer> ROOMITEMS_MAP = new HashMap<>();

    public static void hasItemInRoom(String itemName, int quantity) throws InterruptedException{    //check if there is an item in the room
        TextEngine.printWithDelays("Hey! There is an item in this room: ", false);
        if(quantity>1){
            TextEngine.printWithDelays("Item(s): "+itemName+" x"+quantity, false);
        } else {
            TextEngine.printWithDelays("Item: "+itemName, false);
        }
        TextEngine.printWithDelays("What is your command: take it or leave it", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "take it" -> {Player.putItem(itemName, quantity);
                    Main.screenRefresh();
                    return;
                }
                case "leave it" -> {
                    Main.screenRefresh();
                    return;
                }
                default -> Main.inGameDefaultTextHandling(command);
            }
        }
    }
    public static void chest(Map<String,Integer> chestItems) throws InterruptedException{ 
        //chest implementation
    }
    public static int getRoom(){ //get the room
        String save = Main.getSavedPlace();
        switch (save) {
            case "SpawnRoom" -> {
                return SpawnRoom.roomSave;
            }
            case "OpenWorld" -> {
                return OpenWorld.roomSave;
            }
            case "Dungeon" -> {
                return Dungeon.getRoom();
            }
            default -> {
                return 0;
            }
        }
    }
    public static void reset(String area){ //reset the room
        switch (area) {
            case "SpawnRoom" -> {
                SpawnRoom.resetAll();
            }
            case "OpenWorld" -> {
                OpenWorld.resetAll();
            }
            case "Dungeon" -> {
                Dungeon.resetAll();
            }
            case "all" -> {
                SpawnRoom.resetAll();
                OpenWorld.resetAll();
                Dungeon.resetAll();
            }
        }
    }
}
