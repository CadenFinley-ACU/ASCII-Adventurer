import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
public class TestSuite_Adventure
{   
    @Before
    public void setUp(){
    }
    @Test
    public void printWithDelays() throws InterruptedException{
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TextEngine.printWithDelays("Adventure V1, by BarrettHall:: Albert Tucker, Caden Finley, and Grijesh Shrestha",false);
        assertEquals("Adventure V1, by BarrettHall:: Albert Tucker, Caden Finley, and Grijesh Shrestha", outContent.toString().trim());
    }
    @Test
    public void printNoDelay() throws InterruptedException{
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TextEngine.printNoDelay("Adventure V1, by BarrettHall:: Albert Tucker, Caden Finley, and Grijesh Shrestha",false);
        assertEquals("Adventure V1, by BarrettHall:: Albert Tucker, Caden Finley, and Grijesh Shrestha", outContent.toString().trim());
    }
    @Test
    public void printWithDelays2() throws InterruptedException{
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TextEngine.printWithDelays("You enter a dark forest with a small path leading to the north", false);
        assertEquals("You enter a dark forest with a small path leading to the north", outContent.toString().trim());
    }
    @Test
    public void printNoDelay2() throws InterruptedException{
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TextEngine.printNoDelay("You enter a dark forest with a small path leading to the north", false);
        assertEquals("You enter a dark forest with a small path leading to the north", outContent.toString().trim());
    }
}
