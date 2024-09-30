
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
        TextEngine.printWithDelays("Which path will you take:\nnorth, village, west, Go Back, or help", true);
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
                case "village" -> {
                    Village.startRoom();
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
        TextEngine.printWithDelays("What is your command: north, south, east, or dungeon", true);
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
                    //go meadow dungeon
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
        TextEngine.printWithDelays("What is your command: north, dungeon, or west", true);
        while (true) {
            ignore = console.readLine();
            command = console.readLine();
            switch (command.toLowerCase()) {
                case "north" -> {
                    roomSave = 6;
                    Main.loadSave();
                }
                case "dungeon" -> {
                    //to dungeon
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
        TextEngine.printWithDelays("What is your command: north, south, dungeon, or west", true);
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
                    //to dungeon
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
        TextEngine.printWithDelays("What is your command: south, east, or dungeon", true);
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
                    //to dungeon
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
    // private static void part2() throws InterruptedException { //2 came from 0
    //     TextEngine.printWithDelays("You walk forward and see a small village in the distance", false);
    //     TextEngine.printWithDelays("What is your command: go on or go back", true);
    //     while (true) {
    //         ignore = console.readLine();
    //         command = console.readLine();
    //         switch (command.toLowerCase()) {
    //             case "go on" -> {
    //                 roomSave = 4;
    //                 Main.loadSave();
    //             }
    //             case "go back" -> {
    //                 roomSave = 0;
    //                 Main.loadSave();
    //             }
    //             default ->
    //                 Main.inGameDefaultTextHandling(command);
    //         }
    //     }
    // }
    // private static void part4() throws InterruptedException { //4 came from 2 maybe create combat system for later fights as this is just example for class
    //     if (!completedPart4) {
    //         TextEngine.printWithDelays("As you are walking along the path to the village you are jumped by some bandits", false);
    //         TextEngine.printWithDelays("What is your command: fight, run, plead", true);
    //         while (true) {
    //             ignore = console.readLine();
    //             command = console.readLine();
    //             switch (command.toLowerCase()) {
    //                 case "fight" -> {
    //                     Player.changeHealth(Enemy.spawnEnemy("bandit",3));
    //                     TextEngine.enterToNext();
    //                     completedPart4 = true;
    //                     roomSave = 5; //tovillage
    //                     Main.loadSave();
    //                 }
    //                 case "run" -> {
    //                     TextEngine.printWithDelays("You managed to run away from the bandits.", false);
    //                     TextEngine.enterToNext();
    //                     roomSave = 2; //goes back
    //                     Main.loadSave();
    //                 }
    //                 case "plead" -> {
    //                     Player.changeGold(-Player.getGold());
    //                     TextEngine.printWithDelays("You plead deperately from them to let you go\nThe demand all of your gold.", false);
    //                     TextEngine.printWithDelays("They took everything you had.", false);
    //                     TextEngine.enterToNext();
    //                     roomSave = 5; //to village
    //                     completedPart4 = true;
    //                     Main.loadSave();
    //                 }
    //                 default ->
    //                     Main.inGameDefaultTextHandling(command);
    //             }
    //         }
    //     } else {
    //         TextEngine.printWithDelays("The path was serene and quiet.\n the bandits were nowhere to be seen.", false);
    //         TextEngine.printWithDelays("A path here has opened up through a meadow", false);
    //         TextEngine.printWithDelays("What is your command: meadow, village, or back to start", true);
    //         while (true) { 
    //             ignore = console.readLine();
    //             command=console.readLine();
    //             switch (command.toLowerCase()) {
    //                 case "meadow" -> {
    //                     roomSave = 3;
    //                     Main.loadSave();
    //                 }
    //                 case "village" -> {
    //                     roomSave = 5;
    //                     Main.loadSave();
    //                 }
    //                 case "back to start" -> {
    //                     roomSave = 2;
    //                     Main.loadSave();
    //                 }
    //                 default ->
    //                     Main.inGameDefaultTextHandling(command);
    //             }
    //             roomSave = 0;
    //         }
    //     }

    // }

    // private static void part1() throws InterruptedException { //1 came from 0 create a forest dungeon
    //     TextEngine.printWithDelays("You walk into a dark spooky forest, if you choose to go on you cannot go back.", false);
    //     TextEngine.printWithDelays("What is your command: go on or go back", true);
    //     while (true) {
    //         ignore = console.readLine();
    //         command = console.readLine();
    //         switch (command.toLowerCase()) {
    //             case "go on" -> {
    //                 roomSave =0;
    //                 //create forest dungeon
    //             }
    //             case "go back" -> {
    //                 roomSave = 0;
    //                 Main.loadSave();
    //             }
    //             default ->
    //                 Main.inGameDefaultTextHandling(command);
    //         }
    //     }
    // }
    // private static void part3() throws InterruptedException { //3 came from 4 create a meadow dungeon
    //     TextEngine.printWithDelays("You walk into the meadow, if you choose to go on you cannot go back.", false);
    //     TextEngine.printWithDelays("What is your command: go on or go back", true);
    //     while (true) {
    //         ignore = console.readLine();
    //         command = console.readLine();
    //         switch (command.toLowerCase()) {
    //             case "go on" -> {
    //                 roomSave=4;
    //                 //create meadow dungeon
    //                 Dungeon.initDungeon("Meadow");
    //             }
    //             case "go back" -> {
    //                 roomSave = 4;
    //                 Main.loadSave();
    //             }
    //             default ->
    //                 Main.inGameDefaultTextHandling(command);
    //         }
    //     }
    // }

