import java.util.concurrent.TimeUnit;
/**
 * Text Engine
 * 
 * Text Adventure Game
 * SE374 F24
 * Final Project
 * Caden Finley
 * Albert Tucker
 * Grijesh Shrestha
 */
public class TextEngine {
     public static void printWithDelays(String data) throws InterruptedException{
        //TODO add setting to change speed add a button to speed up text generation while it is printing
        if(data.charAt(data.length()-1) != '\n'){
            data = data + "\n";
        }
        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);
            if (ch == ' ' || ch == '.' || ch == ',' || ch == '!' || ch == '?') {
                System.out.print(ch);
            }
            else {
                if (data.length() > 100) {
                    TimeUnit.MILLISECONDS.sleep(25);
                } else if (data.length() > 50) {
                    TimeUnit.MILLISECONDS.sleep(50);
                } else {
                    TimeUnit.MILLISECONDS.sleep(75);
                }
                System.out.print(ch);
            }
        }
    }
    public static void printNoDelay(String data){
        System.out.println(data);
    }
}
