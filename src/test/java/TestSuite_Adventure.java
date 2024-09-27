import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
public class TestSuite_Adventure
{   
    TextEngine textEngine;
    @Before
    public void setUp(){
        textEngine = new TextEngine();
    }
    @Test
    public void printWithDelays() throws InterruptedException{
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TextEngine.printWithDelays("Adventure V1, by BarrettHall:: Albert Tucker, Caden Finley, and Grijesh Shrestha");
        assertEquals("Adventure V1, by BarrettHall:: Albert Tucker, Caden Finley, and Grijesh Shrestha", outContent.toString().trim());
    }
    @Test
    public void printNoDelay() throws InterruptedException{
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TextEngine.printNoDelay("Adventure V1, by BarrettHall:: Albert Tucker, Caden Finley, and Grijesh Shrestha");
        assertEquals("Adventure V1, by BarrettHall:: Albert Tucker, Caden Finley, and Grijesh Shrestha", outContent.toString().trim());
    }

}
