
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
        //System.out.println("Timer started");
        startTime = System.currentTimeMillis();
        running = true;
        // Start a new thread to display the clock
        new Thread(() -> {
            while (running) {
                elapsedTime = System.currentTimeMillis() - startTime;
                seconds = (elapsedTime / 1000) % 60;
                minutes = (elapsedTime / 60000) % 60;
                hours = (elapsedTime / 360000) % 24;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void stopClock() {
        //System.out.println("Timer stopped");
        savedTime += elapsedTime;
        running = false;
        elapsedTime = 0;
    }

    public String returnTime() {
        savedTime += getElapsedTime();
        hours = ((savedTime) / 360000) % 24;
        minutes = ((savedTime) / 60000) % 60;
        seconds = ((savedTime) / 1000) % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
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
