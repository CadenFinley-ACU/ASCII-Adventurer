
/**
 * Timer Engine
 *
 * Text Adventure Game SE374 F24 Final Project Caden Finley Albert Tucker
 * Grijesh Shrestha
 */
public class TimerEngine {

    private long startTime;
    private boolean running;

    private long elapsedTime;
    private long seconds = 0;
    private long minutes = 0;
    private long hours = 0;

    private long savedTime;

    public void startClock() {
        startTime = System.currentTimeMillis();
        running = true;
        // Start a new thread to display the clock
        new Thread(() -> {
            while (running) {
                elapsedTime = System.currentTimeMillis() - startTime;
                seconds = (elapsedTime / 1000) % 60;
                minutes = (elapsedTime / (1000 * 60)) % 60;
                hours = (elapsedTime / (1000 * 60 * 60)) % 24;
                // Pause for 1 second
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Timer interrupted");
                }
            }
        }).start();
    }

    public void stopClock() {
        //System.out.printf("Time elapsed: %02d:%02d:%02d%n", hours, minutes, seconds);
        running = false;
    }

    public String returnTime() {
        hours = (savedTime / (1000 * 60 * 60)) % 24;
        minutes = (savedTime / (1000 * 60)) % 60;
        seconds = (savedTime / 1000) % 60;
        return String.format("%02d:%02d", hours, minutes);
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public long getSavedTime() {
        return savedTime;
    }

    public void setSavedTime(long save) {
        this.savedTime = save;
    }
}
