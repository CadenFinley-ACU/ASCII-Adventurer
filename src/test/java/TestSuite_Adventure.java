import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
public class TestSuite_Adventure
{   
    @Before
    public void setUp() {
        DungeonGenerator.testing = true;
    }
    // @Test
    // public void printWithDelays() throws InterruptedException{
    //     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    //     System.setOut(new PrintStream(outContent));
    //     TextEngine.printWithDelays("Adventure V1, by BarrettHall:: Albert Tucker, Caden Finley, and Grijesh Shrestha",false);
    //     assertEquals("Adventure V1, by BarrettHall:: Albert Tucker, Caden Finley, and Grijesh Shrestha", outContent.toString().trim());
    // }
    // @Test
    // public void printNoDelay() throws InterruptedException{
    //     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    //     System.setOut(new PrintStream(outContent));
    //     TextEngine.printNoDelay("Adventure V1, by BarrettHall:: Albert Tucker, Caden Finley, and Grijesh Shrestha",false);
    //     assertEquals("Adventure V1, by BarrettHall:: Albert Tucker, Caden Finley, and Grijesh Shrestha", outContent.toString().trim());
    // }
    // @Test
    // public void printWithDelays2() throws InterruptedException{
    //     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    //     System.setOut(new PrintStream(outContent));
    //     TextEngine.printWithDelays("You enter a dark forest with a small path leading to the north", false);
    //     assertEquals("You enter a dark forest with a small path leading to the north", outContent.toString().trim());
    // }
    // @Test
    // public void printNoDelay2() throws InterruptedException{
    //     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    //     System.setOut(new PrintStream(outContent));
    //     TextEngine.printNoDelay("You enter a dark forest with a small path leading to the north", false);
    //     assertEquals("You enter a dark forest with a small path leading to the north", outContent.toString().trim());
    // }
    @Test
    public void testDungeonGeneratorConnection1() {
        DungeonGenerator.start(8);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
    @Test
    public void testDungeonGeneratorConnection2() {
        DungeonGenerator.start(15);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
    @Test
    public void testDungeonGeneratorConnection3() {
        DungeonGenerator.start(11);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
    @Test
    public void testDungeonGeneratorConnection5() {
        DungeonGenerator.start(8);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
    @Test
    public void testDungeonGeneratorConnection6() {
        DungeonGenerator.start(5);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
    @Test
    public void testDungeonGeneratorConnection7() {
        DungeonGenerator.start(8);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
    @Test
    public void testDungeonGeneratorConnection8() {
        DungeonGenerator.start(8);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
    @Test
    public void testDungeonGeneratorConnection9() {
        DungeonGenerator.start(5);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
    @Test
    public void testDungeonGeneratorConnection10() {
        DungeonGenerator.start(5);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
    @Test
    public void testDungeonGeneratorConnection11() {
        DungeonGenerator.start(5);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
    @Test
    public void testDungeonGeneratorConnection12() {
        DungeonGenerator.start(5);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
    @Test
    public void testDungeonGeneratorConnection13() {
        DungeonGenerator.start(5);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
    @Test
    public void testDungeonGeneratorConnection14() {
        DungeonGenerator.start(5);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
    @Test
    //SCARY
    public void testDungeonGeneratorConnection15() {
        DungeonGenerator.start(7);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
    @Test
    //SCARY
    public void testDungeonGeneratorConnection16() {
        DungeonGenerator.start(7);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
    @Test
    public void testDungeonGeneratorConnection17() {
        DungeonGenerator.start(9);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
    @Test
    public void testDungeonGeneratorConnection18() {
        DungeonGenerator.start(9);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
    @Test
    public void testDungeonGeneratorConnection19() {
        DungeonGenerator.start(11);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
    @Test
    public void testDungeonGeneratorConnection20() {
        DungeonGenerator.start(13);
        int[][] matrix = DungeonGenerator.returnMatrix();
        int[] pos9 = DungeonGenerator.findValue(matrix, 9);
        int[] pos8 = DungeonGenerator.findValue(matrix, 8);
        assertNotNull(pos9);
        assertNotNull(pos8);
        boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

}
