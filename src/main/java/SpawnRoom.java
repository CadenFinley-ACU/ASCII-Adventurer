
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
        GameSaveSerialization.saveGame();
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
        String resetColor = "\033[0m"; // reset to default color
        String yellowColor = "\033[1;33m"; // yellow color

        // Print the initial text with delays
        TextEngine.printWithDelays("You awaken in a dim, damp cave, the air thick with the scent of moss and stone. \nThe only thing you have is the clothes you're wearing. To the north, you spot a faint glimmer of light.", false);

        // Print the command text with 'north' and 'help' highlighted in yellow
        TextEngine.printWithDelays("What will you do? Type " + yellowColor + "north" + resetColor + " to move or " + yellowColor + "help" + resetColor + " for assistance", true);

        // Command handling loop
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase().trim()) {
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
        TextEngine.printWithDelays("As you approach the light, your eyes catch the glint of a sword lying on the ground.", false);
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
        String resetColor = "\033[0m"; // reset to default color
        String yellowColor = "\033[1;33m"; // yellow color
        TextEngine.printWithDelays("You enter a cool, dimly lit room by a few torches", false);
        TextEngine.printWithDelays("There is a petestal in the middle where a mighty sword once lay", false);
        TextEngine.printWithDelays("Where to?\n Deeper in the Cave or Back out in the Wilderness?", false);
        TextEngine.printWithDelays("What is your command: " + yellowColor + "cave" + resetColor + " or " + yellowColor + "wilderness" + resetColor, true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase().trim()) {
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
