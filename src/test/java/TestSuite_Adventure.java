
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class TestSuite_Adventure {

    private static int testsrun = 0;

    @Before
    public void setUp() {
        DungeonGenerator.testing = true;
        DungeonGenerator.wipe();
    }

    @After
    public void tearDown() {
        System.out.println("-----------------------------");
        System.out.println("Tests run: " + testsrun);
        System.out.println("Dungeons Generated: " + DungeonGenerator.runs + " Fails: " + DungeonGenerator.fails);
        System.out.println(((float) DungeonGenerator.fails / DungeonGenerator.runs) * 100 + "% failure rate");
        System.out.println("For every test run, " + DungeonGenerator.runs / testsrun + " dungeons were generated");
        System.out.println("-----------------------------");
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection1() {
        testsrun++;
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(5);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection2() {
        testsrun++;
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(7);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection3() {
        testsrun++;
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(9);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection4() {
        testsrun++;
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(11);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection5() {
        testsrun++;
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(13);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection6() {
        testsrun++;
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(5);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection7() {
        testsrun++;
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(7);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection8() {
        testsrun++;
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(9);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection9() {
        testsrun++;
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(11);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection10() {
        testsrun++;
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(13);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void stressTest() {
        for (int i = 5; i < 1000; i++) {
            testsrun++;
            int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(13);
            boolean connected = DungeonGenerator.testArrays(testMatrix);
            assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
        }
    }

}
