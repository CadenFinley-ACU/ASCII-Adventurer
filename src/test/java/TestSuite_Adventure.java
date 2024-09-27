import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
public class TestSuite_Adventure
{   
    TextEngine textEngine;
    @Before
    public void setUp(){
        textEngine = new TextEngine();
    }
    @Test
    public void testFirstOutput(){
        assertEquals("Adventure V1, by BarrettHall:: Albert Tucker, Caden Finley, and Grijesh Shrestha", textEngine.printWithDelays("Adventure V1, by BarrettHall:: Albert Tucker, Caden Finley, and Grijesh Shrestha"));
    }
}
