
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Timer Engine
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class TimerEngine {

    private boolean running;
    private long timeElapsedInSeconds = 0;

    public void startClock() {
        //System.out.println("Timer started");
        running = true;
        // Start a new thread to display the clock
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            if (running) {
                timeElapsedInSeconds++;
                System.out.println(returnTime());
            } else {
                executor.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public void stopClock() {
        //System.out.println("Timer stopped");
        running = false;
    }

    public String returnTime() {
        long minutes = (timeElapsedInSeconds / 60) % 60;
        long hours = (timeElapsedInSeconds / 3600);
        if (hours > 99) {
            hours = 99;
            if (minutes > 59) {
                minutes = 59;
                if (timeElapsedInSeconds % 60 > 59) {
                    timeElapsedInSeconds = 59;
                }
            }
        }
        return String.format("%02d:%02d:%02d", hours, minutes, timeElapsedInSeconds % 60);
    }

    public long getTime() {
        return timeElapsedInSeconds;
    }

    public void setSavedTime(long save) {
        this.timeElapsedInSeconds = save;
    }

    public void debugTime(long time) {
        this.timeElapsedInSeconds = time;
    }
}
