


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
public class Village extends Room {

    static int roomSave = 0;

    public static void startRoom() throws InterruptedException {
    }
    public static void church() throws InterruptedException{
        //church implementation
        TextEngine.printWithDelays("You enter the village church.\nThere a a preist here who can bless your heart containers to gain more hearts", false);
        if(Player.inventory.containsKey("Heart Container")){
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
                    default -> Main.inGameDefaultTextHandling(command);
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
                    default -> Main.inGameDefaultTextHandling(command);
                }
            }
        }
    }
    public static void hotel(){
        //hotel implementation
    }
    public static void shop(){
        //shop implementation
    }
    
}