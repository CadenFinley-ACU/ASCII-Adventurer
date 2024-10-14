
/**
 * Village Class
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class Village extends Room {

    static String resetColor = "\033[0m"; // reset to default color
    static String yellowColor = "\033[1;33m"; // yellow color

    public static void startRoom() throws InterruptedException { //start room

        room = "Village";
        Main.checkSave(room);
        Main.screenRefresh();
        //maybe something here in the futre that adds rondomness or more advanced feature to certain villages to make them all not the exact same
        TextEngine.printWithDelays("You step into the village, surrounded by the hustle and bustle of life.\nVarious buildings dot the landscape, each offering something unique.", false);
        if (Dungeon.completedDungeons > 5) {
            TextEngine.printWithDelays("You can now warp to different areas using the " + yellowColor + "portal" + resetColor, false);
        }
        TextEngine.printNoDelay("What will you do? Type " + yellowColor + "church" + resetColor + ", " + yellowColor + "hotel" + resetColor + ", " + yellowColor + "shop" + resetColor + ", or " + yellowColor + "leave village" + resetColor + " to decide", true);
        while (true) {
            ignore = console.readLine();
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
                    leave();
                }
                case "portal" -> {
                    if (Dungeon.completedDungeons > 5) {
                        TextEngine.printWithDelays("Which area would you like to warp to?", false);
                        TextEngine.printWithDelays("Forest, Mountain, Desert, Ocean?", false);
                        ignore = console.readLine();
                        command = console.readLine();
                        switch (command.toLowerCase()) {
                            case "forest" -> {
                                OpenWorld.roomSave = 4;
                                OpenWorld.startRoom();
                            }
                            case "mountain" -> {
                                OpenWorld.roomSave = 5;
                                OpenWorld.startRoom();
                            }
                            case "desert" -> {
                                OpenWorld.roomSave = 10;
                                OpenWorld.startRoom();
                            }
                            case "ocean" -> {
                                OpenWorld.roomSave = 9;
                                OpenWorld.startRoom();
                            }
                            default ->
                                Main.inGameDefaultTextHandling(command);
                        }
                    } else {
                        Main.inGameDefaultTextHandling(command);
                    }
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    public static void church() throws InterruptedException { //church

        Main.screenRefresh();
        //church implementation
        TextEngine.printWithDelays("You step inside the village church, the air filled with the scent of incense and the soft glow of candlelight.\nThe serene atmosphere surrounds you as you approach the altar.\n\n", false);
        if (Player.inventory.containsKey("heart container")) {
            TextEngine.printWithDelays("A priest stands before you,\noffering blessings to those in need. You approach him, holding out your heart container.\n", false);
            TextEngine.printWithDelays("would you like to " + yellowColor + "give it" + resetColor + " or " + yellowColor + "leave it" + resetColor, true);
            while (true) {
                ignore = console.readLine();
                command = console.readLine();
                switch (command.toLowerCase().trim()) {
                    case "give it" -> {
                        TextEngine.printWithDelays("\n\"Ah, a heart container!\" he exclaims, a warm smile spreading across his face.\n        \"With this, I can bless your heart and grant you even greater strength.\"\n", false);
                        TextEngine.printWithDelays("As you hand over the heart container, you feel a surge of energy coursing through you.\n        The priest chants a blessing, and you sense your heart capacity increase.", false);
                        int amountofHeartContainers = Player.inventory.get("heart container");
                        Player.changeMaxHealth(InventoryManager.Potions.get("heart container") * amountofHeartContainers);
                        Player.putItem("heart container", -amountofHeartContainers);
                        TextEngine.enterToNext();
                        Main.loadSave();
                    }
                    case "leave it" -> {
                        Main.loadSave();
                    }
                    default ->
                        Main.inGameDefaultTextHandling(command);
                }
            }
        } else {
            TextEngine.printWithDelays("A priest stands before you,\nready to offer blessings that can enhance your heart containers and grant you more strength.\n\nHowever, you realize you don't have a heart container to give to him.", false);
            TextEngine.printWithDelays("Would you like to leave this sacred space? Type " + yellowColor + "yes" + resetColor + " to exit or " + yellowColor + "no" + resetColor + " to stay", true);
            while (true) {
                ignore = console.readLine();
                command = console.readLine();
                switch (command.toLowerCase().trim()) {
                    case "yes" -> {
                        Main.loadSave();
                    }
                    case "no" -> {
                        TextEngine.printWithDelays("There isn't anyhting here for you, try going to a dungeon.", false);
                        TextEngine.printWithDelays("Would you like to leave " + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor, true);
                        ignore = console.readLine();
                        command = console.readLine();
                        switch (command.toLowerCase()) {
                            case "yes" -> {
                                Main.loadSave();
                            }
                            case "no" -> {
                                church();
                            }
                            default ->
                                Main.inGameDefaultTextHandling(command);
                        }
                    }
                    default ->
                        Main.inGameDefaultTextHandling(command);
                }
            }
        }
    }

    public static void hotel() throws InterruptedException { //hotel
        //hotel implementation

        Main.screenRefresh();
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
            Main.loadSave();
        }
        TextEngine.printWithDelays("What will you do? Type " + yellowColor + "rest" + resetColor + " to restore your health (cost:" + cost + " gold) or " + yellowColor + "leave" + resetColor + " to exit the hotel", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase().trim()) {
                case "rest" -> {
                    if (Player.getGold() < cost) {
                        TextEngine.printWithDelays("You do not have enough gold to rest", false);
                        TextEngine.enterToNext();
                        Main.loadSave();
                    } else {
                        TextEngine.printWithDelays("You have rested and restored your health", false);
                        Player.changeGold(-cost);
                        Player.changeHealth(Player.getMaxHealth() - Player.getHealth());
                        Main.loadSave();
                    }
                }
                case "leave" -> {
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    public static void shop1() throws InterruptedException { //shop1
        String brightBoldEnd = "\033[0m"; // end color
        String brightYellowStart = "\033[1;33m"; // Bright Yellow

        Main.screenRefresh();
        // Shop implementation
        TextEngine.printNoDelay("Gold: " + Player.getGold(), false);
        TextEngine.printNoDelay("Inventory: " + Player.getTotalNumberOfItemsInInventory() + "/" + Player.inventorySize, false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("You step into the bustling shop,\nwhere the scent of various herbs and spices fills the air.\n      Shelves are lined with a variety of items, each glimmering under the soft light.\n\n", false);
        TextEngine.printWithDelays("Here, you can purchase useful items to aid you on your journey.\n\n", false);

        // Creating the message with bold and bright items
        String shopMessage
                = "What would you like to buy?\n"
                + brightYellowStart + " health potion" + brightBoldEnd + "~15 gold\n"
                + brightYellowStart + " shield" + brightBoldEnd + "~20 gold\n"
                + brightYellowStart + " key" + brightBoldEnd + "~30 gold\n"
                + "Or type " + brightYellowStart + "leave" + brightBoldEnd + " to exit the shop";

        // Use the modified message with bold items
        TextEngine.printNoDelay(shopMessage, true);

        while (true) {
            ignore = console.readLine(); // It's unclear what this line does, so it's kept.
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
                case "key" -> {
                    buyMultiple("key", 30);
                }
                case "heart container" -> {
                    buyMultiple("heart container", 50);
                }
                case "leave" -> {
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    public static void shop2() throws InterruptedException { //shop2
        String brightBoldEnd = "\033[0m"; // end color
        String brightYellowStart = "\033[1;33m"; // Bright Yellow

        Main.screenRefresh();
        // Shop implementation
        TextEngine.printNoDelay("Gold: " + Player.getGold(), false);
        TextEngine.printNoDelay("Inventory: " + Player.getTotalNumberOfItemsInInventory() + "/" + Player.inventorySize, false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("You enter the shop.", false);
        TextEngine.printWithDelays("You can buy items here", false);

        // Creating the message with bold and bright items
        String shopMessage
                = "What would you like to buy:\n"
                + brightYellowStart + " greater health potion" + brightBoldEnd + "~30 gold\n"
                + brightYellowStart + " ninja armor" + brightBoldEnd + "~100 gold\n"
                + brightYellowStart + " key" + brightBoldEnd + "~30 gold\n"
                + "or " + brightYellowStart + "leave" + brightBoldEnd;

        // Use the modified message with bold items
        TextEngine.printNoDelay(shopMessage, true);

        while (true) {
            ignore = console.readLine(); // It's unclear what this line does, so it's kept.
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
                case "key" -> {
                    buyMultiple("key", 30);
                }
                case "leave" -> {
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    public static void shop3() throws InterruptedException {
        String brightBoldEnd = "\033[0m"; // end color
        String brightYellowStart = "\033[1;33m"; // Bright Yellow

        Main.screenRefresh();
        // Shop implementation
        TextEngine.printNoDelay("Gold: " + Player.getGold(), false);
        TextEngine.printNoDelay("Inventory: " + Player.getTotalNumberOfItemsInInventory() + "/" + Player.inventorySize, false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("You enter the shop.", false);
        TextEngine.printWithDelays("You can buy items here", false);

        // Creating the message with bold and bright items
        String shopMessage
                = "What would you like to buy:\n"
                + brightYellowStart + "super health potion" + brightBoldEnd + "~50 gold\n"
                + brightYellowStart + "demon armor" + brightBoldEnd + "~200 gold\n"
                + brightYellowStart + "key" + brightBoldEnd + "~30 gold\n"
                + "or " + brightYellowStart + "leave" + brightBoldEnd;

        // Use the modified message with bold items
        TextEngine.printNoDelay(shopMessage, true);

        while (true) {
            ignore = console.readLine(); // It's unclear what this line does, so it's kept.
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
                case "key" -> {
                    buyMultiple("key", 30);
                }
                case "leave" -> {
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void buyMultiple(String type, int cost) throws InterruptedException { //buy multiple clause for certain items in village shop
        TextEngine.printWithDelays("How many would you like to buy?", true);
        ignore = console.readLine();
        command = console.readLine();
        if (TextEngine.checkValidInput(command)) {
            try {
                Integer.valueOf(command);
            } catch (NumberFormatException e) {
                Main.invalidCommand();
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
            Main.invalidCommand();
            keepShopping();
        }
    }

    private static void keepShopping() throws InterruptedException { //keep shopping
        TextEngine.printWithDelays("Would you like to keep shopping? " + yellowColor + "yes" + resetColor + " or " + yellowColor + "no" + resetColor, true);
        while (true) {
            ignore = console.readLine();
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
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void leave() throws InterruptedException { //leave the village
        TextEngine.clearScreen();
        OpenWorld.startRoom();
    }
}
