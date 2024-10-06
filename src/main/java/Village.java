


/**
 * Village Class
 *
 * Text Adventure Game
 * SE374 F24
 * Final Project
 * Caden Finley
 * Albert Tucker
 * Grijesh Shrestha
 */
public class Village extends Room {

    public static void startRoom() throws InterruptedException { //start room
        room = "Village";
        Main.checkSave(room);
        Main.screenRefresh();
        //maybe something here in the futre that adds rondomness or more advanced feature to certain villages to make them all not the exact same
        TextEngine.printWithDelays("You walk into the village, there are multiple builings", false);
        if (Dungeon.completedDungeons > 5) {
            TextEngine.printWithDelays("You can now warp to different areas using the 'portal'", false);
        }
        TextEngine.printWithDelays("What is your command: church, hotel, shop, leave village", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
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
                case "leave village" -> {
                    leave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    public static void church() throws InterruptedException { //church
        Main.screenRefresh();
        //church implementation
        TextEngine.printWithDelays("You enter the village church.\nThere a a preist here who can bless your heart containers to gain more hearts", false);
        if (Player.inventory.containsKey("heart container")) {
            TextEngine.printWithDelays("You have a heart container to give to the priest", false);
            TextEngine.printWithDelays("What is your command: give it or leave it", true);
            while (true) {
                ignore = console.readLine();
                command = console.readLine();
                switch (command.toLowerCase()) {
                    case "give it" -> {
                        Player.putItem("heart container", -1);
                        Player.changeMaxHealth(InventoryManager.Potions.get("heart container"));
                        TextEngine.printWithDelays("Your max health has increased by " + InventoryManager.Potions.get("heart container") + " points", false);
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
            TextEngine.printWithDelays("You do not have a heart container to give to the priest", false);
            TextEngine.printWithDelays("Would you like to leave? yes or no", true);
            while (true) {
                ignore = console.readLine();
                command = console.readLine();
                switch (command.toLowerCase()) {
                    case "yes" -> {
                        Main.loadSave();
                    }
                    case "no" -> {
                        TextEngine.printWithDelays("There isn't anyhting here for you, try going to a dungeon.", true);
                        TextEngine.printWithDelays("Would you like to leave yes or no?", true);
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
        TextEngine.printNoDelay("Gold: " + Player.getGold(), false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("You enter the hotel.", false);
        TextEngine.printWithDelays("You can rest here to regain health", false);
        TextEngine.printWithDelays("What is your command: rest(5 gold) or leave", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "rest" -> {
                    if (Player.getGold() < 5) {
                        TextEngine.printWithDelays("You do not have enough gold to rest", false);
                        TextEngine.enterToNext();
                        Main.loadSave();
                    } else {
                        Player.changeGold(-5);
                        Player.changeHealth(Player.getMaxHealth() - Player.getHealth());
                        Main.screenRefresh();
                        TextEngine.printWithDelays("You have rested and restored your health", false);
                        TextEngine.enterToNext();
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

    public static void shop1() throws InterruptedException { //shop 1
        Main.screenRefresh();
        //shop implementation
        TextEngine.printNoDelay("Gold: " + Player.getGold(), false);
        TextEngine.printNoDelay("Inventory: " + Player.inventory.size() + "/" + Player.inventorySize, false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("You have entered the shop.", false);
        TextEngine.printWithDelays("You can buy items here", false);
        TextEngine.printWithDelays("What would you like to buy: \nhealth potion (15 gold), shield (20 gold), key (30 gold), or leave", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "health potion" -> {
                    buyMultiple("health potion", 15);
                }
                case "shield" -> {
                    if (Player.getGold() >= 20) {
                        Player.changeGold(-20);
                        Player.putItem("shield", 1);
                        keepShopping();
                    } else {
                        TextEngine.printWithDelays("You do not have enough gold to buy a shield", false);
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

    public static void shop2() throws InterruptedException { //shop 2
        Main.screenRefresh();
        //shop implementation
        TextEngine.printNoDelay("Gold: " + Player.getGold(), false);
        TextEngine.printNoDelay("Inventory: " + Player.inventory.size() + "/" + Player.inventorySize, false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("You enter the shop.", false);
        TextEngine.printWithDelays("You can buy items here", false);
        TextEngine.printWithDelays("What would you like to buy: \n greater health potion (30 gold), ninja armor (100 gold), key (30 gold), or leave", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "greater health potion" -> {
                    buyMultiple("greater health potion", 30);
                }
                case "ninja armor" -> {
                    if (Player.getGold() >= 100) {
                        Player.changeGold(-100);
                        Player.putItem("ninja armor", 1);
                        keepShopping();
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

    public static void shop3() throws InterruptedException { //shop 3
        Main.screenRefresh();
        //shop implementation
        TextEngine.printNoDelay("Gold: " + Player.getGold(), false);
        TextEngine.printNoDelay("Inventory: " + Player.inventory.size() + "/" + Player.inventorySize, false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("You enter the shop.", false);
        TextEngine.printWithDelays("You can buy items here", false);
        TextEngine.printWithDelays("What would you like to buy: \nsuper health potion (50 gold), demon armor (200 gold), key (30 gold), or leave", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "super health potion" -> {
                    buyMultiple("super health potion", 15);
                }
                case "demon armor" -> {
                    if (Player.getGold() >= 20) {
                        Player.changeGold(-20);
                        Player.putItem("demon armor", 1);
                        keepShopping();
                    } else {
                        TextEngine.printWithDelays("You do not have enough gold to buy a demon armor", false);
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
                Player.changeGold(-totalCost);
                Player.putItem(type, Integer.parseInt(command));
                keepShopping();
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
        TextEngine.printWithDelays("Would you like to keep shopping? yes or no", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "yes" -> {
                    if (Dungeon.completedDungeons < 4) {
                        shop1();
                    } else if (Dungeon.completedDungeons > 3 && Dungeon.completedDungeons < 6) {
                        shop2();
                    } else {
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
