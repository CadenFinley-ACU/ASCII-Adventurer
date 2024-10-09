
import java.util.Random;

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
        System.out.println("For every test run, " + (float) DungeonGenerator.runs / testsrun + " dungeons were generated");
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
    public void testDungeonGeneratorConnectionRandomSize() {
        testsrun++;
        Random rand = new Random();
        int size = rand.nextInt(11) + 5; // Generates a random size between 5 and 15
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(size);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize1() {
        testsrun++;
        Random rand = new Random();
        int size = rand.nextInt(11) + 5; // Generates a random size between 5 and 15
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(size);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize2() {
        testsrun++;
        Random rand = new Random();
        int size = rand.nextInt(11) + 5; // Generates a random size between 5 and 15
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(size);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize3() {
        testsrun++;
        Random rand = new Random();
        int size = rand.nextInt(11) + 5; // Generates a random size between 5 and 15
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(size);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize4() {
        testsrun++;
        Random rand = new Random();
        int size = rand.nextInt(11) + 5; // Generates a random size between 5 and 15
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(size);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize5() {
        testsrun++;
        Random rand = new Random();
        int size = rand.nextInt(11) + 5; // Generates a random size between 5 and 15
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(size);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize6() {
        testsrun++;
        Random rand = new Random();
        int size = rand.nextInt(11) + 5; // Generates a random size between 5 and 15
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(size);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize7() {
        testsrun++;
        Random rand = new Random();
        int size = rand.nextInt(11) + 5; // Generates a random size between 5 and 15
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(size);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize8() {
        testsrun++;
        Random rand = new Random();
        int size = rand.nextInt(11) + 5; // Generates a random size between 5 and 15
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(size);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize9() {
        testsrun++;
        Random rand = new Random();
        int size = rand.nextInt(11) + 5; // Generates a random size between 5 and 15
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(size);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize10() {
        testsrun++;
        Random rand = new Random();
        int size = rand.nextInt(11) + 5; // Generates a random size between 5 and 15
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(size);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorActualSizes1() {
        testsrun++;
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(5);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorActualSizes2() {
        testsrun++;
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(6);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorActualSizes3() {
        testsrun++;
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(7);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorActualSizes4() {
        testsrun++;
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(7);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorActualSizes5() {
        testsrun++;
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(8);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorActualSizes6() {
        testsrun++;
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(8);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorActualSizes7() {
        testsrun++;
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(9);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorActualSizes8() {
        testsrun++;
        int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(11);
        boolean connected = DungeonGenerator.testArrays(testMatrix);
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }
}
