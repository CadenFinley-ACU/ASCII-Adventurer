
/**
 * Open World Class
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
//maybe procurally generated dungeons using birary states to matrix arrrys ti create rooms in  path
public class OpenWorld extends Room {

    static int roomSave = 1;
    public static String holdCommand = "";

    public static void startRoom() throws InterruptedException { //start room
        room = "OpenWorld";
        Main.checkSave(room);
        Main.screenRefresh();
        switch (roomSave) {
            case 1 -> {
                Player.playerX = 2;
                Player.playerY = 7;
                room1(); //start room   
            }
            case 2 -> {
                Player.playerX = 1;
                Player.playerY = 7;
                room2(); //start room
            }
            case 3 -> {
                Player.playerX = 1;
                Player.playerY = 6;
                room3(); //start room
            }
            case 4 -> {
                Player.playerX = 2;
                Player.playerY = 6;
                room4(); //start room
            }
            case 5 -> {
                Player.playerX = 3;
                Player.playerY = 6;
                room5(); //start room
            }
            case 6 -> {
                Player.playerX = 3;
                Player.playerY = 5;
                room6(); //start room
            }
            case 7 -> {
                Player.playerX = 2;
                Player.playerY = 5;
                room7(); //start room
            }
            case 8 -> {
                Player.playerX = 1;
                Player.playerY = 5;
                room8(); //start room
            }
            case 9 -> {
                Player.playerX = 2;
                Player.playerY = 4;
                room9(); //start room
            }
            case 10 -> {
                Player.playerX = 3;
                Player.playerY = 4;
                room10(); //start room
            }
            case 11 -> {
                Player.playerX = 3;
                Player.playerY = 3;
                room11(); //start room
            }
            case 12 -> {
                Player.playerX = 2;
                Player.playerY = 3;
                room12(); //start room
            }
            case 13 -> {
                Player.playerX = 1;
                Player.playerY = 3;
                room13(); //start room
            }
            case 14 -> {
                Player.playerX = 0;
                Player.playerY = 3;
                room14(); //start room
            }
            case 15 -> {
                Player.playerX = 0;
                Player.playerY = 2;
                room15(); //start room
            }
            case 16 -> {
                Player.playerX = 1;
                Player.playerY = 2;
                room16(); //start room
            }
            case 17 -> {
                Player.playerX = 2;
                Player.playerY = 2;
                room17(); //start room
            }
            case 18 -> {
                Player.playerX = 3;
                Player.playerY = 1;
                room18(); //start room
            }
            case 19 -> {
                Player.playerX = 2;
                Player.playerY = 1;
                room19(); //start room
            }
            case 20 -> {
                Player.playerX = 2;
                Player.playerY = 0;
                room20(); //start room
            }
            case 21 ->{
                Player.playerX = 1;
                Player.playerY = 4;
                room21();
            }
            default ->
                Main.startMenu();
        }
    }

    public static void resetAll() { //reset all
        roomSave = 1;
    }

    private static void room1() throws InterruptedException { //0
        TextEngine.printWithDelays("You are in the open world,\nthe sunlight blinds your eyes as they have not adjusted from the dark cave.", false);
        TextEngine.printWithDelays("As your eyes adjust you see long winding paths that go north, west, and a village to the east.\nHow overwhelming...", false);
        TextEngine.printWithDelays("Which path will you take: north, west, or The Village", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 4;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 2;
                    Main.loadSave();
                }
                case "the village" -> {
                    Main.saveSpace("Village");
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room2() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + " and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north or east", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim().trim()) {
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

    private static void room3() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + ", you are right by The Meadow dungeon now.", false);
        TextEngine.printWithDelays("Worrning: going into a dungeon you will trigger fights, but you might find something in the rooms\nIf you do not DIE", false);
        TextEngine.printWithDelays("What is your command: north, east, south, or The Meadow", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 8;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 4;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 2;
                    Main.loadSave();
                }
                case "the meadow" -> {
                    MeadowDungeon.fresh();
                    Main.saveSpace("Meadow Dungeon");
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room4() throws InterruptedException {
        TextEngine.printWithDelays("you walk "+ command + ", and you see a unlocked dungeion to the west",false);
        TextEngine.printWithDelays("What is your command: north, east, south, or west", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 7;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 5;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 1;
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

    private static void room5() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + ", you are right by The Mountain Cave", false);
        TextEngine.printWithDelays("Worrning: going into a dungeon you will trigger fights, but you might find something in the rooms\nIf you do not DIE", false);
        TextEngine.printWithDelays("What is your command: north, west, or The Mountain Cave", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 6;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 4;
                    Main.loadSave();
                }
                case "the mountain cave" -> {
                    if (Dungeon.completedDungeons > 1) {
                        MountainCaveDungeon.fresh();
                        Main.saveSpace("Mountain Cave Dungeon");
                        Main.loadSave();
                    } else {
                        TextEngine.printWithDelays("You must complete The Dark Forest first.\nTry going west // MaYbe", true);
                    }
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room6() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + ", you are right by The Mountain Top dungeon", false);
        TextEngine.printWithDelays("Worrning: going into a dungeon you will trigger fights, but you might find something in the rooms\nIf you do not DIE", false);
        TextEngine.printWithDelays("What is your command: north, south, west, or The Mountain Top", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 10;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 5;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 7;
                    Main.loadSave();
                }
                case "the mountain top" -> {
                    if (Dungeon.completedDungeons > 2) {
                        MountainTopDungeon.fresh();
                        Main.saveSpace("Mountain Top Dungeon");
                        Main.loadSave();
                    } else {
                        TextEngine.printWithDelays("You must complete The Mountain Cave dungeons first\nTry going south //Not sure if that is right~Albert:)", true);
                    }
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room7() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + " and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north, east, south, or west", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 9;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 6;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 4;
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

    private static void room8() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + ", you are right by The Dark Forest", false);
        TextEngine.printWithDelays("Worrning: going into a dungeon you will trigger fights, but you might find something in the rooms\nIf you do not DIE", false);
        TextEngine.printWithDelays("What is your command: north, east, south, or The Dark Forest", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 21;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 7;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 3;
                    Main.loadSave();
                }
                case "the dark forest" -> {
                    if (Dungeon.completedDungeons > 0) {
                        DarkForestDungeon.fresh();
                        Main.saveSpace("Dark Forest Dungeon");
                        Main.loadSave();
                    } else {
                        TextEngine.printWithDelays("You must complete The Meadow dungeon first.\nTry going south", true);
                    }
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room9() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + " and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north, east, south, or west", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 12;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 10;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 7;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 21;
                    Main.loadSave();                
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room10() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + ", you are right by The Desert Oasis", false);
        TextEngine.printWithDelays("Worrning: going into a dungeon you will trigger fights, but you might find something in the rooms\nIf you do not DIE", false);
        TextEngine.printWithDelays("What is your command: north, south, west, The Desert Oasis", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 11;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 6;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 9;
                    Main.loadSave();
                }
                case "the dark oasis" -> {
                    if (Dungeon.completedDungeons > 3) {
                        DesertOasisDungeon.fresh();
                        Main.saveSpace("Desert Oasis Dungeon");
                        Main.loadSave();
                    } else {
                        TextEngine.printWithDelays("You must complete The Mountain Top dungeon first.\n Try going to the south ~ maybe", true);
                    }
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room11() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + ", you are right by The Desert Plains", false);
        TextEngine.printWithDelays("Worrning: going into a dungeon you will trigger fights, but you might find something in the rooms\nIf you do not DIE", false);
        TextEngine.printWithDelays("What is your command: south, west, or The Desert Plains", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
                case "south" -> {
                    roomSave = 10;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 12;
                    Main.loadSave();
                }
                case "the desert plains" -> {
                    if (Dungeon.completedDungeons > 4) {
                        DesertPlainsDungeon.fresh();
                        Main.saveSpace("Desert Plains Dungeon");
                        Main.loadSave();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Oasis dungeons first.\nTry going to the south ~ maybe", true);
                    }
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room12() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + " and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north, east, south, or west", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 17;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 11;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 9;
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

    private static void room13() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + " and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north, east, south, or west", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 16;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 12;
                    Main.loadSave();
                }
                case "south" ->{
                    roomSave = 21;
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

    private static void room14() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + " and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north or east", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
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

    private static void room15() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + " and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: east, south, or The village", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
                case "east" -> {
                    roomSave = 16;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 14;
                    Main.loadSave();
                }
                case "the village" -> {
                    Village.startRoom();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room16() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + " and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: east, south or west", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
                case "east" -> {
                    roomSave = 17;
                    Main.loadSave();
                }
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

    private static void room17() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + " and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north, south, west, or The Village", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 19;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 12;
                    Main.loadSave();
                }
                case "west" -> {
                    roomSave = 16;
                    Main.loadSave();
                }
                case "the village" -> {
                    Village.startRoom();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room18() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + ", you are right by The Desert Pyramid", false);
        TextEngine.printWithDelays("Worrning: going into a dungeon you will trigger fights, but you might find something in the rooms\nIf you do not DIE", false);
        TextEngine.printWithDelays("What is your command: west or The Desert Pyramid", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
                case "west" -> {
                    roomSave = 19;
                    Main.loadSave();
                }
                case "the desert pyramid" -> {
                    if (Dungeon.completedDungeons > 5) {
                        DesertPyramidDungeon.fresh();
                        Main.saveSpace("Desert Pyramid Dungeon");
                        Main.loadSave();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Plains dungeons first.\nTry going to the south ~ maybe", true);
                    }
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room19() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + " and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north, east, or south", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
                case "north" -> {
                    roomSave = 20;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 18;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 17;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }

    private static void room20() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + ", you are right by The Ocean Kingdom", false);
        TextEngine.printWithDelays("Worrning: going into a dungeon you will trigger fights, but you might find something in the rooms\nIf you do not DIE", false);
        TextEngine.printWithDelays("What is your command: south or The Ocean Kingdom", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase().trim()) {
                case "south" -> {
                    roomSave = 19;
                    Main.loadSave();
                }
                case "the ocean kingdom" -> {
                    if (Dungeon.completedDungeons > 6) {
                        OceanKingdomDungeon.fresh();
                        Main.saveSpace("Ocean Kingdom Dungeon");
                        Main.loadSave();
                    } else {
                        TextEngine.printWithDelays("You must complete The Desert Pyramis dungeon first.\nTry going to the west ~ maybe", true);
                    }
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
    private static void room21() throws InterruptedException {
        TextEngine.printWithDelays("You walk " + holdCommand + " and see a small village in the distance", false);
        TextEngine.printWithDelays("What is your command: north, east, or south", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            holdCommand = command;
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 13;
                    Main.loadSave();
                }
                case "east" -> {
                    roomSave = 9;
                    Main.loadSave();
                }
                case "south" -> {
                    roomSave = 8;
                    Main.loadSave();
                }
                default ->
                    Main.inGameDefaultTextHandling(command);
            }
        }
    }
}
