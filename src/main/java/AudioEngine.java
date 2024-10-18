
// import javax.sound.sampled.AudioSystem;
// import javax.sound.sampled.AudioInputStream;
// import javax.sound.sampled.AudioFormat;
// import javax.sound.sampled.DataLine;
// import javax.sound.sampled.LineUnavailableException;
// import javax.sound.sampled.Clip;
// import javax.sound.sampled.UnsupportedAudioFileException;

// import java.io.File;
// import java.io.IOException;

// /**
//  * Audio Engine Engine
//  *
//  * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
//  * Grijesh Shrestha
//  */
// public class AudioEngine {

//     static boolean muted = false;

//     public static void playSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//         if (!muted) {
//             System.out.println("\007");
//             // Load the audio file
//             File audioFile = new File("your_audio_file.wav");
//             AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

//             // Get the audio format
//             AudioFormat format = audioStream.getFormat();

//             // Create a DataLine.Info object for playback
//             DataLine.Info info = new DataLine.Info(Clip.class, format);

//             // Get a Clip object
//             Clip clip = (Clip) AudioSystem.getLine(info);

//             // Open the clip with the audio stream
//             clip.open(audioStream);

//             // Start playing the audio
//             clip.start();

//             // Close the clip
//             clip.close();

//         }
//     }
// }
