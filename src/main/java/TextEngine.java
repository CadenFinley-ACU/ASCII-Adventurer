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
    public static String speedSetting = "Normal";
    public static void printWithDelays(String data,boolean buffer) throws InterruptedException{ //use buffer is you are accepting input after the text is printed
        if(buffer) {
        data = data + "(press enter to type)";
        }
        if(data.charAt(data.length()-1) != '\n'&& !buffer){
            data = data + "\n";
        }
        StringBuilder remainingData = new StringBuilder(data);
        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);
                switch (speedSetting) {
                    case "Slow" -> TimeUnit.MILLISECONDS.sleep(50);
                    case "Fast" -> TimeUnit.MILLISECONDS.sleep(25);
                    case "NoDelay" -> TimeUnit.MILLISECONDS.sleep(0);
                    default -> {
                    if (data.length() > 100) {
                        TimeUnit.MILLISECONDS.sleep(15);
                    } else if (data.length() > 50) {
                        TimeUnit.MILLISECONDS.sleep(35);
                    } else {
                        TimeUnit.MILLISECONDS.sleep(50);
                    }
                }
            }
            System.out.print(ch);
            remainingData.setCharAt(i, ' ');
        }
    }
    public static void printNoDelay(String data,boolean buffer){ //use buffer is you are accepting input after the text is printed
        if(buffer) {
            data = data + "(press enter to type)";
        }
        if(data.charAt(data.length()-1) != '\n'&&!buffer){
            data = data + "\n";
        }
        System.out.print(data);
    }
    @SuppressWarnings("deprecation")
    public static void clearScreen() { //clears the screen
        try
        {
        final String os = System.getProperty("os.name");
        if (os.contains("Windows"))
        {
            Runtime.getRuntime().exec("cls");
        }
        else
        {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            Runtime.getRuntime().exec("clear");
        }
        }   catch (final Exception e) {
        //  Handle any exceptions.
        }
    }

}
