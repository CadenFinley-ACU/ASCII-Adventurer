
import java.io.Console;
/**
 * Room Engine???
 * 
 * Text Adventure Game
 * SE374 F24
 * Final Project
 * Caden Finley
 * Albert Tucker
 * Grijesh Shrestha
 */
 class Adventure {
        public static void main(String[] args) throws InterruptedException {
        TextEngine.printWithDelays("Adventure V1, by BarrettHall:: Albert Tucker, Caden Finley, and Grijesh Shrestha");

        TextEngine.printWithDelays("You are in a lucious, green area with trees, not Abilene.\nWhat is your command: north or exit?");

	    Console console = System.console();
        String command = console.readLine();

        if (command.equals("north")) {
            TextEngine.printWithDelays("Eh, you are in Canada. Good luck with all the mooses. Bye for now.");
            System.exit(0);
	    
        } else if (command.equals("exit")) {
            TextEngine.printWithDelays("See ya next time!");
            System.exit(0);
	    
        } else {
            // how did the user arrive at this branch?
            // TODO: be more useful to the user
            System.exit(0);
        }
    }
}