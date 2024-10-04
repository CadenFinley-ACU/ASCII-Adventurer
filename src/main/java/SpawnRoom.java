
/**
 * Spawn Room Class
 *
 * Text Adventure Game
 * SE374 F24
 * Final Project
 * Caden Finley
 * Albert Tucker
 * Grijesh Shrestha
 */
public class SpawnRoom extends Room {

    static int roomSave = 0;

    public static void startRoom() throws InterruptedException { //start room
        room = "SpawnRoom";
        Main.checkSave(room);
        Main.screenRefresh();
        switch (roomSave) {
            case 0 ->
                part0();
            case 1 ->
                part1();
            case 2 ->
                part2();
            default ->
                Main.startMenu();
        }
    }

    public static void resetAll() { //reset all
        roomSave = 0;
    }

    private static void part0() throws InterruptedException { //0
        TextEngine.printWithDelays("You wake up in a dark musty cave with nothing but the clothes on your back.", false);
        TextEngine.printWithDelays("You see a faint light to the north", false);
        TextEngine.printWithDelays("What is your command: north or help", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave++;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void part1() throws InterruptedException { //1
        if (Player.inventory.containsKey("sword")) {
            roomSave++;
            OpenWorld.startRoom();
        }
        TextEngine.printWithDelays("You enter a cool, dimly lit room by a few torches", false);
        while (true) {
            if (hasItemInRoom("sword", 1)) {
                roomSave++;
                OpenWorld.startRoom();
            } else {
                TextEngine.printWithDelays("You must take the sword!\nIt is too dangerous to go alone!", false);
                TextEngine.enterToNext();
            }
        }
    }

    private static void part2() throws InterruptedException { //2
        TextEngine.printWithDelays("You enter a cool, dimly lit room by a few torches", false);
        TextEngine.printWithDelays("There is a petestal in the middle where a mighty sword once lay", false);
        TextEngine.printWithDelays("Where to?\n Deeper in the Cave or Back out in the Wilderness?", false);
        TextEngine.printWithDelays("What is your command: cave or wilderness", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "cave" -> {
                    TextEngine.printWithDelays("Why would we go back there? That is where we came from.", true);
                    continue;
                }
                case "wilderness" -> {
                    OpenWorld.startRoom();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }

    }
}
