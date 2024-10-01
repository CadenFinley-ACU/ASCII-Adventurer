
/**
 * Open World Class
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
//maybe procurally generated dungeons using birary states to matrix arrrys ti create rooms in  path
public class OpenWorld extends Room {

    static int roomSave = 1;
    static int[] completedRooms = {
        0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
    };
    public static void startRoom() throws InterruptedException { //start room
        room = "OpenWorld";
        Main.checkSave(room);
        Main.screenRefresh();
        switch (roomSave) {
            case 0:
                Village.startRoom();
            case 1:
                room1(); //start room
            case 2:
                room2(); //start room
            case 3:
                room3(); //start room
            case 4:
                room4(); //start room
            case 5:
                room5(); //start room
            case 6:
                room6(); //start room
            case 7:
                room7(); //start room
            case 8:
                room8(); //start room
            case 9:
                room9(); //start room
            case 10:
                room10(); //start room
            case 11:
                room11(); //start room
            case 12:
                room12(); //start room
            case 13:
                room13(); //start room
            case 14:
                room14(); //start room
            case 15:
                room15(); //start room
            case 16:
                room16(); //start room
            case 17:
                room17(); //start room
            case 18:
                room18(); //start room
            case 19:
                room19(); //start room
            case 20:
                room20(); //start room
            default:
                Main.startMenu();
        }
    }
    public static void resetAll(){ //reset all
        roomSave = 0;
    }
    private static void room1() throws InterruptedException { //0
        TextEngine.printWithDelays("You are in the open world,\nthe sunlight blinds your eyes as they have not adjusted from the dark cave.", false);
        TextEngine.printWithDelays("You see a long winding paths in all four directions.\nHow overwhelming...", false);
        TextEngine.printWithDelays("Which path will you take:\nnorth, east, village, west, Go Back, or help", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 4;
                    Main.loadSave();
                }
                case "go back" -> {
                    SpawnRoom.startRoom();
                }
                case "west" -> {
                    roomSave = 2;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 2;
                    Main.loadSave();
                }
                case "village" -> {
                    Main.saveSpace("Village");
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room2() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north or east", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 3;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 1;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room3() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north, south, east, or Meadow Dungeon", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 8;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 2;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 4;
                    Main.loadSave();
                }
                case "dungeon" -> {
                    MeadowDungeon.fresh();
                    Main.saveSpace("Meadow Dungeon");
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room4() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north, south, east, or west", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 7;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 1;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 5;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 3;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room5() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north, Mountain Cave Dungeon, or west", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 6;
                    Main.loadSave();
                }
                case "dungeon" -> {
                    if(Dungeon.completedDungeons>1){
                    MountainCaveDungeon.fresh();
                    Main.saveSpace("Mountain Cave Dungeon");
                    Main.loadSave();
                    }
                    else{
                        TextEngine.printWithDelays("You must complete the other dungeons first.", true);
                    }
                }
                case "west" -> {
                    roomSave = 4;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room6() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north, south, Mountain Top Dungeon, or west", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 10;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 5;
                    Main.loadSave();
                }
                case "dungeon" -> {
                    if(Dungeon.completedDungeons>2){
                        MountainTopDungeon.fresh();
                        Main.saveSpace("Mountain Top Dungeon");
                        Main.loadSave();
                        }
                        else{
                            TextEngine.printWithDelays("You must complete the other dungeons first.", true);
                        }
                }
                case "west" -> {
                    roomSave = 7;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room7() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north, south, east, or west", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 9;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 4;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 6;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 8;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room8() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: south, east, or Dark Forest Dungeon", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "south" -> {
                    roomSave = 3;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 7;
                    Main.loadSave();
                }
                case "dungeon" -> {
                    if(Dungeon.completedDungeons>0){
                        DarkForestDungeon.fresh();
                    Main.saveSpace("Dark Forest Dungeon");
                    Main.loadSave();
                    } else {
                        TextEngine.printWithDelays("You must complete the other dungeons first.", true);
                    }   
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room9() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north, south, east, or village", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 12;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 7;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 10;
                    Main.loadSave();
                }
                case "village" -> {
                    Village.startRoom();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room10() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north, south, dungeon, or west", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 11;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 6;
                    Main.loadSave();
                }
                case "dungeon" -> {
                    //to dungeon
                }
                case "west" -> {
                    roomSave = 9;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room11() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: south, dungeon, or west", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "south" -> {
                    roomSave = 10;
                    Main.loadSave();
                }
                case "dungeon" -> {
                    //to dungeon
                }
                case "west" -> {
                    roomSave = 12;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room12() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north, south, east, or west", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 17;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 9;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 11;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 13;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room13() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north, east, or west", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 16;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 12;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 14;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room14() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north or east", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 15;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 13;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room15() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: village, south, or east", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "village" -> {
                    Village.startRoom();
                }
                case "south" -> {
                    roomSave = 14;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 16;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room16() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: south or west", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "south" -> {
                    roomSave = 13;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 15;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room17() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north, south, or village", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "south" -> {
                    roomSave = 12;
                    Main.loadSave();
                }
                case "village" -> {
                    Village.startRoom();
                }
                case "north" -> {
                    roomSave = 19;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room18() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: west or dungeon", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "west" -> {
                    roomSave = 19;
                    Main.loadSave();
                }
                case "dungeon" -> {
                    //to dungeon
                } 
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room19() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north, south, or east", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 20;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 17;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 18;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room20() throws InterruptedException{
        TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: south or dungeon", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "south" -> {
                    roomSave = 19;
                    Main.loadSave();
                }
                case "dungeon" -> {
                    //to final dungeon
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
}
    