
/**
 * Village Class
 *
 * Text Adventure Game SE374 F24 Final Project
 * Caden Finley, Albert Tucker, Grijesh Shrestha
 */
public class Village extends Room {

    static String yellowColor = "\033[1;33m"; // yellow color

    /**
     * The startRoom function in Java sets the player in a village environment
     * with various options to interact with different locations and warp to
     * other areas based on completed dungeons.
     */
    public static void startRoom() throws InterruptedException { //start room
        room = "Village";
        GameEngine.checkSave(room);
        GameEngine.screenRefresh();
        TextEngine.printWithDelays("You step into the village, surrounded by the hustle and bustle of life.\nVarious buildings dot the landscape, each offering something unique.", false);
        if (Dungeon.completedDungeons > 5) {
            TextEngine.printWithDelays("You can now warp to different areas using the " + yellowColor + "portal" + resetColor, false);
        }
        TextEngine.printNoDelay("What will you do? Type " + yellowColor + "church" + resetColor + ", " + yellowColor + "hotel" + resetColor + ", " + yellowColor + "shop" + resetColor + ", or " + yellowColor + "leave village" + resetColor + " to decide", true);
        while (true) {
            command = console.readLine();
            switch (command.toLowerCase().trim()) {
                case "church" -> {
                    Village.church();
                }
                case "hotel" -> {
                    Village.hotel();
                }
                case "shop" -> {
                    if (Dungeon.completedDungeons < 3) { //0,1,2
                        shop1();
                    } else if (Dungeon.completedDungeons > 2 && Dungeon.completedDungeons < 6) { //3,4,5
                        shop2();
                    } else { //6,7,8
                        shop3();
                    }
                }
                case "leave village" -> {
                    switch (OpenWorld.saveRoomNumber) {
                        case 74, 67 -> {
                            TextEngine.printWithDelays("Which way would you like to leave the village\n you can go west to go to the desert\n or north to the forest", false);
                            TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + " or " + yellowColor + "west" + resetColor + " to decide", true);
                            while (true) {

                                command = console.readLine();
                                if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                                    OpenWorld.holdCommand = command;
                                }
                                switch (command.toLowerCase().trim().trim()) {
                                    case "north" -> {
                                        OpenWorld.roomSave = 67;
                                        leave();
                                    }
                                    case "west" -> {
                                        OpenWorld.roomSave = 74;
                                        leave();
                                    }
                                    default ->
                                        GameEngine.inGameDefaultTextHandling(command);
                                }
                            }
                        }
                        case 47, 37 -> {
                            TextEngine.printWithDelays("Which way would you like to leave the village\nyou can go north to the ocean kingdom\nor east to the grass plains", false);
                            TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "north" + resetColor + " or " + yellowColor + "east" + resetColor + " to decide", true);
                            while (true) {

                                command = console.readLine();
                                if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                                    OpenWorld.holdCommand = command;
                                }
                                switch (command.toLowerCase().trim().trim()) {
                                    case "north" -> {
                                        OpenWorld.roomSave = 37;
                                        leave();
                                    }
                                    case "east" -> {
                                        OpenWorld.roomSave = 47;
                                        leave();
                                    }
                                    default ->
                                        GameEngine.inGameDefaultTextHandling(command);
                                }
                            }
                        }
                        default -> {
                            TextEngine.printWithDelays("Which way would you like to leave the village\n you can go west to stay in the mountain\n or north to the grass plains", false);
                            TextEngine.printWithDelays("What will you do next? Type " + yellowColor + "south" + resetColor + " or " + yellowColor + "west" + resetColor + " to decide", true);
                            while (true) {

                                command = console.readLine();
                                if ("north".equals(command) || "east".equals(command) || "south".equals(command) || "west".equals(command)) {
                                    OpenWorld.holdCommand = command;
                                }
                                switch (command.toLowerCase().trim().trim()) {
                                    case "south" -> {
                                        OpenWorld.roomSave = 29;
                                        leave();
                                    }
                                    case "west" -> {
                                        OpenWorld.roomSave = 19;
                                        leave();
                                    }
                                    default ->
                                        GameEngine.inGameDefaultTextHandling(command);
                                }
                            }
                        }
                    }
                }
                case "portal" -> {
                    if (Dungeon.completedDungeons > 5) {
                        TextEngine.printWithDelays("Which area would you like to warp to?", false);
                        TextEngine.printWithDelays("Forest, Mountain, Desert, Ocean?", true);

                        command = console.readLine();
                        switch (command.toLowerCase()) {
                            case "forest" -> {
                                OpenWorld.roomSave = 51;
                                OpenWorld.startRoom();
                            }
                            case "mountain" -> {
                                OpenWorld.roomSave = 5;
                                OpenWorld.startRoom();
                            }
                            case "desert" -> {
                                OpenWorld.roomSave = 40;
                                OpenWorld.startRoom();
                            }
                            case "ocean" -> {
                                OpenWorld.roomSave = 3;
                                OpenWorld.startRoom();
                            }
                            default ->
                                GameEngine.inGameDefaultTextHandling(command);
                        }
                    } else {
                        GameEngine.inGameDefaultTextHandling(command);
                    }
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    /**
     * The function `church()` in Java simulates a player interacting with a
     * priest in a village church, allowing them to give or leave a heart
     * container for blessings and strength enhancements.
     */
    public static void church() throws InterruptedException { //church

        GameEngine.screenRefresh();
        //church implementation
        TextEngine.printWithDelays("You step inside the village church, the air filled with the scent of incense and the soft glow of candlelight.\nThe serene atmosphere surrounds you as you approach the altar.\n\n", false);
        if (Player.inventory.containsKey("heart container")) {
            TextEngine.printWithDelays("A priest stands before you,\noffering blessings to those in need. You approach him, holding out your heart container.\n", false);
            TextEngine.printWithDelays("would you like to " + yellowColor + "give it" + resetColor + " or " + yellowColor + "leave it" + resetColor, true);
            while (true) {

                command = console.readLine();
                switch (command.toLowerCase().trim()) {
                    case "give it" -> {
                        TextEngine.printWithDelays("\n\"Ah, a heart container!\" he exclaims, a warm smile spreading across his face.\n        \"With this, I can bless your heart and grant you even greater strength.\"\n", false);
                        TextEngine.printWithDelays("As you hand over the heart container, you feel a surge of energy coursing through you.\n        The priest chants a blessing, and you sense your heart capacity increase.", false);
                        int amountofHeartContainers = Player.inventory.get("heart container");
                        Player.changeMaxHealth(InventoryManager.Potions.get("heart container") * amountofHeartContainers);
                        Player.putItem("heart container", -amountofHeartContainers);
                        TextEngine.enterToNext();
                        GameEngine.loadSave();
                    }
                    case "leave it" -> {
                        GameEngine.loadSave();
                    }
                    default ->
                        GameEngine.inGameDefaultTextHandling(command);
                }
            }
        } else {
            TextEngine.printWithDelays("A priest stands before you,\nready to offer blessings that can enhance your heart containers and grant you more strength.\n\nHowever, you realize you don't have a heart container to give to him.", false);
            TextEngine.printWithDelays("Would you like to leave this sacred space? Type " + yellowColor + "yes" + resetColor + " to exit or " + yellowColor + "no" + resetColor + " to stay", true);
            while (true) {

                command = console.readLine();
                switch (command.toLowerCase().trim()) {
                    case "yes" -> {
                        GameEngine.loadSave();
                    }
                    case "no" -> {
                        TextEngine.printWithDelays("There isn't anyhting here for you, try going to a dungeon.", false);
                        TextEngine.printWithDelays("Would you like to leave " + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor, true);

                        command = console.readLine();
                        switch (command.toLowerCase()) {
                            case "yes" -> {
                                GameEngine.loadSave();
                            }
                            case "no" -> {
                                church();
                            }
                            default ->
                                GameEngine.inGameDefaultTextHandling(command);
                        }
                    }
                    default ->
                        GameEngine.inGameDefaultTextHandling(command);
                }
            }
        }
    }

    /**
     * The `hotel` function allows the player to rest and regain health at a
     * cost in a text-based game, with options to either rest or leave the
     * hotel.
     */
    public static void hotel() throws InterruptedException { //hotel
        //hotel implementation

        GameEngine.screenRefresh();
        int cost = (int) ((1 / 4.0) * (Player.getMaxHealth() - Player.getHealth()));
        if (cost < 1) {
            cost = 1;
        }
        TextEngine.printNoDelay("Gold: " + Player.getGold(), false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("You step into the hotel,\n      greeted by the cozy ambiance and the soft murmur of guests enjoying their stay.\nThe warm glow of the lights invites you to relax.", false);
        TextEngine.printWithDelays("        Here, you can rest to regain your health and rejuvenate your spirit.\n", false);
        if (Player.getHealth() == Player.getMaxHealth()) {
            TextEngine.printWithDelays("You are already at full health, there is no need to rest.", false);
            TextEngine.enterToNext();
            GameEngine.loadSave();
        }
        TextEngine.printWithDelays("What will you do? Type " + yellowColor + "rest" + resetColor + " to restore your health (cost:" + cost + " gold) or " + yellowColor + "leave" + resetColor + " to exit the hotel", true);
        while (true) {
            command = console.readLine();
            switch (command.toLowerCase().trim()) {
                case "rest" -> {
                    if (Player.getGold() < cost) {
                        TextEngine.printWithDelays("You do not have enough gold to rest", false);
                        TextEngine.enterToNext();
                        GameEngine.loadSave();
                    } else {
                        TextEngine.printWithDelays("You have rested and restored your health", false);
                        Player.changeGold(-cost);
                        Player.changeHealth(Player.getMaxHealth() - Player.getHealth());
                        GameEngine.loadSave();
                    }
                }
                case "leave" -> {
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    /**
     * The shop1 function in Java allows players to buy items with gold,
     * displaying a message with bold and bright text options.
     */
    public static void shop1() throws InterruptedException { //shop1
        String brightBoldEnd = "\033[0m"; // end color
        String brightYellowStart = "\033[1;33m"; // Bright Yellow

        GameEngine.screenRefresh();
        // Shop implementation
        TextEngine.printNoDelay("Gold: " + Player.getGold(), false);
        TextEngine.printNoDelay("Inventory: " + Player.getTotalNumberOfItemsInInventory() + "/" + Player.getInventorySize(), false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("You step into the bustling shop,\nwhere the scent of various herbs and spices fills the air.\n      Shelves are lined with a variety of items, each glimmering under the soft light.\n\n", false);
        TextEngine.printWithDelays("Here, you can purchase useful items to aid you on your journey.\n\n", false);

        // Creating the message with bold and bright items
        String shopMessage
                = "What would you like to buy?\n"
                + brightYellowStart + " health potion" + brightBoldEnd + "~15 gold\n"
                + brightYellowStart + " shield" + brightBoldEnd + "~20 gold\n"
                + "Or type " + brightYellowStart + "leave" + brightBoldEnd + " to exit the shop";

        // Use the modified message with bold items
        TextEngine.printNoDelay(shopMessage, true);

        while (true) {
            command = console.readLine();

            switch (command.toLowerCase().trim()) {
                case "health potion" -> {
                    buyMultiple("health potion", 15);
                }
                case "shield" -> {
                    if (Player.getGold() >= 20) {
                        if (Player.hasRoomInInventory(1)) {
                            Player.changeGold(-20);
                            Player.putItem("shield", 1);
                            keepShopping();
                        } else {
                            TextEngine.printWithDelays("You do not have enough space in your inventory to buy a shield", false);
                            TextEngine.enterToNext();
                            keepShopping();
                        }
                    } else {
                        TextEngine.printWithDelays("You do not have enough gold to buy a shield", false);
                        TextEngine.enterToNext();
                        keepShopping();
                    }
                }
                case "heart container" -> {
                    buyMultiple("heart container", 50);
                }
                case "leave" -> {
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    /**
     * The shop2 function in Java allows the player to buy items with gold,
     * displaying options in a formatted message and handling user input
     * accordingly.
     */
    public static void shop2() throws InterruptedException { //shop2
        String brightBoldEnd = "\033[0m"; // end color
        String brightYellowStart = "\033[1;33m"; // Bright Yellow

        GameEngine.screenRefresh();
        // Shop implementation
        TextEngine.printNoDelay("Gold: " + Player.getGold(), false);
        TextEngine.printNoDelay("Inventory: " + Player.getTotalNumberOfItemsInInventory() + "/" + Player.getInventorySize(), false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("You enter the shop.", false);
        TextEngine.printWithDelays("You can buy items here", false);

        // Creating the message with bold and bright items
        String shopMessage
                = "What would you like to buy:\n"
                + brightYellowStart + " greater health potion" + brightBoldEnd + "~30 gold\n"
                + brightYellowStart + " ninja armor" + brightBoldEnd + "~100 gold\n"
                + "or " + brightYellowStart + "leave" + brightBoldEnd;

        // Use the modified message with bold items
        TextEngine.printNoDelay(shopMessage, true);

        while (true) {
            command = console.readLine();

            switch (command.toLowerCase().trim()) {
                case "greater health potion" -> {
                    buyMultiple("greater health potion", 30);
                }
                case "ninja armor" -> {
                    if (Player.getGold() >= 100) {
                        if (Player.hasRoomInInventory(1)) {
                            Player.changeGold(-100);
                            Player.putItem("ninja armor", 1);
                            keepShopping();
                        } else {
                            TextEngine.printWithDelays("You do not have enough space in your inventory to buy a shield", false);
                            TextEngine.enterToNext();
                            keepShopping();
                        }
                    } else {
                        TextEngine.printWithDelays("You do not have enough gold to buy ninja armor", false);
                        TextEngine.enterToNext();
                        keepShopping();
                    }
                }
                case "leave" -> {
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    /**
     * The shop3 function in Java allows the player to buy items with gold,
     * displaying a message with bold and bright text options and handling
     * different cases based on user input.
     */
    public static void shop3() throws InterruptedException {
        String brightBoldEnd = "\033[0m"; // end color
        String brightYellowStart = "\033[1;33m"; // Bright Yellow

        GameEngine.screenRefresh();
        // Shop implementation
        TextEngine.printNoDelay("Gold: " + Player.getGold(), false);
        TextEngine.printNoDelay("Inventory: " + Player.getTotalNumberOfItemsInInventory() + "/" + Player.getInventorySize(), false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("You enter the shop.", false);
        TextEngine.printWithDelays("You can buy items here", false);

        // Creating the message with bold and bright items
        String shopMessage
                = "What would you like to buy:\n"
                + brightYellowStart + "super health potion" + brightBoldEnd + "~50 gold\n"
                + brightYellowStart + "demon armor" + brightBoldEnd + "~200 gold\n"
                + "or " + brightYellowStart + "leave" + brightBoldEnd;

        // Use the modified message with bold items
        TextEngine.printNoDelay(shopMessage, true);

        while (true) {
            command = console.readLine();

            switch (command.toLowerCase().trim()) {
                case "super health potion" -> {
                    buyMultiple("super health potion", 50);
                }
                case "demon armor" -> {
                    if (Player.getGold() >= 200) {
                        if (Player.hasRoomInInventory(1)) {
                            Player.changeGold(-200);
                            Player.putItem("demon armor", 1);
                            keepShopping();
                        } else {
                            TextEngine.printWithDelays("You do not have enough space in your inventory to buy a shield", false);
                            TextEngine.enterToNext();
                            keepShopping();
                        }
                    } else {
                        TextEngine.printWithDelays("You do not have enough gold to buy demon armor", false);
                        TextEngine.enterToNext();
                        keepShopping();
                    }
                }
                case "leave" -> {
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    /**
     * The function `buyMultiple` allows the player to buy multiple items of a
     * certain type from the village shop, handling input validation, cost
     * calculation, inventory space check, and gold availability.
     *
     * @param type The `type` parameter in the `buyMultiple` method represents
     * the type of item that the player wants to buy in the village shop. It
     * could be any item available for purchase in the shop, such as potions,
     * weapons, armor, or any other item that the player can acquire. The method
     * @param cost The `cost` parameter in the `buyMultiple` method represents
     * the price of each item that the player wants to buy from the village
     * shop. This cost is multiplied by the quantity of items the player wishes
     * to purchase to calculate the total cost of the transaction.
     */
    private static void buyMultiple(String type, int cost) throws InterruptedException { //buy multiple clause for certain items in village shop
        TextEngine.printWithDelays("How many would you like to buy?", true);
        while (true) {
            command = console.readLine();
            if (TextEngine.checkValidInput(command)) {
                try {
                    Integer.valueOf(command);
                } catch (NumberFormatException e) {
                    GameEngine.invalidCommand();
                    TextEngine.enterToNext();
                    buyMultiple(type, cost);
                }
                int totalCost = cost * Integer.parseInt(command);
                if (Player.getGold() >= totalCost && !command.equals("0")) {
                    if (Player.hasRoomInInventory(Integer.parseInt(command))) {
                        Player.changeGold(-totalCost);
                        Player.putItem(type, Integer.parseInt(command));
                        keepShopping();
                    } else {
                        TextEngine.printWithDelays("You do not have enough space in your inventory to buy " + command + " " + type + "s", false);
                        TextEngine.enterToNext();
                        keepShopping();
                    }
                } else {
                    switch (command) {
                        case "0" -> {
                            TextEngine.printWithDelays("You did not buy any " + type + "s.", false);
                            TextEngine.enterToNext();
                            keepShopping();
                        }
                        case "1" -> {
                            TextEngine.printWithDelays("You do not have enough gold to buy a " + command, false);
                            TextEngine.enterToNext();
                            keepShopping();
                        }
                        default -> {
                            TextEngine.printWithDelays("You do not have enough gold to buy " + command + " potions", false);
                            TextEngine.enterToNext();
                            keepShopping();
                        }
                    }
                }
            } else {
                GameEngine.invalidCommand();
                keepShopping();
            }
        }
    }

    /**
     * The `keepShopping` function prompts the user to continue shopping or not,
     * and directs them to different shop options based on the number of
     * completed dungeons.
     */
    private static void keepShopping() throws InterruptedException { //keep shopping
        TextEngine.printWithDelays("Would you like to keep shopping? " + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor, true);
        while (true) {
            command = console.readLine();
            switch (command.toLowerCase().trim()) {
                case "yes" -> {
                    if (Dungeon.completedDungeons < 3) { //0,1,2
                        shop1();
                    } else if (Dungeon.completedDungeons > 2 && Dungeon.completedDungeons < 6) { //3,4,5
                        shop2();
                    } else { //6,7,8
                        shop3();
                    }
                }
                case "no" -> {
                    GameEngine.loadSave();
                }
                default ->
                    GameEngine.inGameDefaultTextHandling(command);
            }
        }
    }

    /**
     * The `leave()` function clears the screen, saves the previous room, and
     * starts the initial room in a text-based adventure game.
     */
    private static void leave() throws InterruptedException { //leave the village
        TextEngine.clearScreen();
        OpenWorld.previousRoomSave = OpenWorld.roomSave;
        OpenWorld.startRoom();
    }
}
