


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

    public static void startRoom() throws InterruptedException {
        room = "Village";
        Main.checkSave(room);
        OpenWorld.roomSave=4;
        Main.screenRefresh();
        //maybe something here in the futre that adds rondomness or more advanced feature to certain villages to make them all not the exact same
        TextEngine.printWithDelays("You walk into the village, there are multiple builings", false);
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
                    Village.shop();
                }
                case "leave village" -> {
                    leave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    public static void church() throws InterruptedException {
        Main.screenRefresh();
        //church implementation
        TextEngine.printWithDelays("You enter the village church.\nThere a a preist here who can bless your heart containers to gain more hearts", false);
        if (Player.inventory.containsKey("Heart Container")) {
            TextEngine.printWithDelays("You have a heart container to give to the priest", false);
            TextEngine.printWithDelays("What is your command: give it or leave it", true);
            while (true) {
                ignore = console.readLine();
                command = console.readLine();
                switch (command.toLowerCase()) {
                    case "give it" -> {
                        Player.putItem("Heart Container", -1);
                        Player.changeHealth(20);
                        TextEngine.printWithDelays("Your health has increased by 20 points", false);
                        TextEngine.printNoDelay("Press enter to continue.", false);
                        ignore = console.readLine();
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
                        church();
                    }
                    default ->
                        Main.inGameDefaultTextHandling(command);
                }
            }
        }
    }

    public static void hotel() throws InterruptedException {
        //hotel implementation
        Main.screenRefresh();
        TextEngine.printNoDelay("Gold: "+Player.getGold(), false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("You enter the hotel.", false);
        TextEngine.printWithDelays("You can rest here to regain health", false);
        TextEngine.printWithDelays("What is your command: rest(5 gold) or leave", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "rest" -> {
                    if(Player.getGold() < 5){
                        TextEngine.printWithDelays("You do not have enough gold to rest", false);
                        TextEngine.printWithDelays("Press enter to continue.", false);
                        ignore = console.readLine();
                        Main.loadSave();
                    }
                    else{
                        Player.changeGold(-5);
                        Player.changeHealth(Player.getMaxHealth()-Player.getHealth());
                        Main.screenRefresh();
                        TextEngine.printWithDelays("You have rested and restored your health", false);
                        TextEngine.printNoDelay("Press enter to continue.", false);
                        ignore = console.readLine();
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

    public static void shop() throws InterruptedException {
        Main.screenRefresh();
        //shop implementation
        TextEngine.printNoDelay("Gold: "+Player.getGold(), false);
        TextEngine.printNoDelay("Inventory: "+Player.inventory.size()+"/"+Player.inventorySize, false);
        TextEngine.printNoDelay("\n", false);
        TextEngine.printWithDelays("You enter the shop.", false);
        TextEngine.printWithDelays("You can buy items here", false);
                    TextEngine.printWithDelays("What would you like to buy: \npotion (10 gold), shield (20 gold), key (30 gold), or leave", true);
                    while (true) {
                        ignore = console.readLine();
                        command = console.readLine();
                        switch (command.toLowerCase()) {
                            case "potion" -> {
                                if (Player.getGold() >= 10) {
                                    Player.changeGold(-10);
                                    Player.putItem("Potion", 1);
                                    keepShopping();
                                } else {
                                    TextEngine.printWithDelays("You do not have enough gold to buy a potion", false);
                                    TextEngine.printWithDelays("Press enter to continue.", false);
                                    ignore = console.readLine();
                                    keepShopping();
                                }
                            }
                            case "shield" -> {
                                if (Player.getGold() >= 20) {
                                    Player.changeGold(-20);
                                    Player.putItem("Shield", 1);
                                    keepShopping();
                                } else {
                                    TextEngine.printWithDelays("You do not have enough gold to buy a shield", false);
                                    TextEngine.printWithDelays("Press enter to continue.", false);
                                    ignore = console.readLine();
                                    keepShopping();
                                }
                            }
                            case "axe" -> {
                                if (Player.getGold() >= 10) {
                                    Player.changeGold(-10);
                                    Player.putItem("Axe", 1);
                                    keepShopping();
                                } else {
                                    TextEngine.printWithDelays("You do not have enough gold to buy Axe", false);
                                    TextEngine.printWithDelays("Press enter to continue.", false);
                                    ignore = console.readLine();
                                    keepShopping();
                                }
                            }
                            case "chainmail" -> {
                                if (Player.getGold() >= 10) {
                                    Player.changeGold(-10);
                                    Player.putItem("Chainmail Set", 1);
                                    keepShopping();
                                } else {
                                    TextEngine.printWithDelays("You do not have enough gold to buy Chainmail Set", false);
                                    TextEngine.printWithDelays("Press enter to continue.", false);
                                    ignore = console.readLine();
                                    keepShopping();
                                }
                            }
                            case "key" -> {
                                if (Player.getGold() >= 30) {
                                    Player.changeGold(-30);
                                    Player.putItem("Key", 1);
                                    keepShopping();
                                } else {
                                    TextEngine.printWithDelays("You do not have enough gold to buy a key", false);
                                    TextEngine.printWithDelays("Press enter to continue.", false);
                                    ignore = console.readLine();
                                    keepShopping();
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

    private static void keepShopping() throws InterruptedException {
        TextEngine.printWithDelays("Would you like to keep shopping? yes or no", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "yes" -> {
                    shop();
                }
                case "no" -> {
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void leave() throws InterruptedException {
        TextEngine.clearScreen();
        OpenWorld.startRoom();
    }
}