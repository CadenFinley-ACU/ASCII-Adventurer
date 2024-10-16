
//import javax.sound.sampled.AudioInputStream;
/**
 * Audio Engine Engine
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class AudioEngine {

    static boolean muted = false;

    public static void playSound() {
        if (!muted) {
            System.out.println("\007");
        }
    }
}
