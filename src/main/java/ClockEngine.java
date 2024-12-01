
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Timer Engine
 *
 * Text Adventure Game SE374 F24 Final Project 
 * Caden Finley, Albert Tucker, Grijesh Shrestha
 */
public class ClockEngine {

    private boolean running;
    private long timeElapsedInSeconds = 0;
    private long remainingTimeInSeconds = 0;
    private final String whatAmI;

    public ClockEngine(String type) {
        this.running = false;
        this.whatAmI = type;
    }

    public void startClock(int periodInSeconds) {
        //System.out.println("Timer started");
        this.running = true;
        if (this.whatAmI.equals("timer")) {
            startTimer(periodInSeconds);
        }
        if (this.whatAmI.equals("stopwatch")) {
            startStopWatch(periodInSeconds);
        }
    }

    private void startTimer(int lengthInSeconds) {
        // Start a new thread to start the timer
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            if (this.running) {
                this.timeElapsedInSeconds++;
                if (this.timeElapsedInSeconds >= lengthInSeconds) {
                    stopClock();
                    executor.shutdown();
                }
                this.remainingTimeInSeconds = lengthInSeconds - this.timeElapsedInSeconds;
            } else {
                this.stopClock();
                executor.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void startStopWatch(int periodInSeconds) {
        // Start a new thread to start the spotwatch
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            if (this.running) {
                this.timeElapsedInSeconds++;
            } else {
                this.stopClock();
                executor.shutdown();
            }
        }, 0, periodInSeconds, TimeUnit.SECONDS);
    }

    public void stopClock() {
        //System.out.println("Timer stopped");
        running = false;
    }

    public String returnTime() {
        long minutes = (this.timeElapsedInSeconds / 60) % 60;
        long hours = (this.timeElapsedInSeconds / 3600);
        if (hours > 99) {
            hours = 99;
            if (minutes >= 59) {
                minutes = 59;
                if (this.timeElapsedInSeconds % 60 >= 59) {
                    this.timeElapsedInSeconds = 59;
                }
            }
        }
        return String.format("%02d:%02d:%02d", hours, minutes, this.timeElapsedInSeconds % 60);
    }

    public long getTimeElapsedInSeconds() {
        return this.timeElapsedInSeconds;
    }

    public long getRemainingTimeInSeconds() {
        return this.remainingTimeInSeconds;
    }

    public void setSavedTimeInSeconds(long save) {
        this.timeElapsedInSeconds = save;
    }

    public void debugTime(long time) {
        this.timeElapsedInSeconds = time;
    }
}
