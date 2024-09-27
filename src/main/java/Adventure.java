
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
    private Console console = System.console();
    private String command;

    public static void main(String[] args) throws InterruptedException {
        TextEngine.printWithDelays("Adventure V1, by BarrettHall:: Albert Tucker, Caden Finley, and Grijesh Shrestha");
        Adventure adventure = new Adventure();
        adventure.inAmerica();
    }

    private void inAmerica() throws InterruptedException{
        while(true){
            TextEngine.printWithDelays("You are in a lucious, green area with trees, not Abilene.\nWhat is your command: north or exit?");
            command = console.readLine();
            if (command.equals("north")) {
                inCanada();
                break;
            } else if (command.equals("exit")) {
                TextEngine.printWithDelays("See ya next time!");
                System.exit(0);
        
            } else {
                TextEngine.printWithDelays("I'm sorry, I don't understand that command.");
            }
        }
    }

    private void inCanada() throws InterruptedException{
        while(true){
            TextEngine.printWithDelays("Eh, you are in Canada. Good luck with all the mooses.\nWhat is your command: north, south or exit?");
            command = console.readLine();
            if (command.equals("north")) {
                TextEngine.printWithDelays("Eh, you are on the North Pole. Good luck cold, hope you brought your coat. brrrrrrr...");
                System.exit(0);
            } else if (command.equals("south")) {
                inAmerica();
                break;
            } else if (command.equals("exit")) {
                TextEngine.printWithDelays("See ya next time!");
                System.exit(0);
            }else {
                TextEngine.printWithDelays("I'm sorry, I don't understand that command.");
            }
        }
    }
}