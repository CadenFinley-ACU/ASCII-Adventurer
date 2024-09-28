
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
}
