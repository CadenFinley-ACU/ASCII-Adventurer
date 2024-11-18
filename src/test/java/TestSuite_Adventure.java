
import java.util.HashMap;
import java.util.Random;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class TestSuite_Adventure {

    private static int testsrun = 0;

    @Before
    public void setUp() {
        // Reset the inventory and other static fields before each test
        InventoryManager.inventory = new HashMap<>();
        InventoryManager.inventorySize = 10; // Assuming a default inventory size
        DungeonGenerator.testing = false;
        DungeonGenerator.wipe();
        Main.TESTING = true;
    }

    @After
    public void tearDown() {
        System.out.println("-----------------------------");
        System.out.println("Tests run: " + testsrun);
        System.out.println("Dungeons Generated: " + DungeonGenerator.runs + " Fails: " + DungeonGenerator.fails);
        System.out.println(((float) DungeonGenerator.fails / DungeonGenerator.runs) * 100 + "% failure rate");
        System.out.println("For every test run, " + (float) DungeonGenerator.runs / testsrun + " dungeons were generated");
        System.out.println("-----------------------------");
        DungeonGenerator.wipe();
        DungeonGenerator.testing = false;
        Main.TESTING = false;
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

    @Test
    public void stupidStress() {
        for (int i = 0; i < 1000; i++) {
            int randNum = new Random().nextInt(11) + 7;
            testsrun++;
            int[][] testMatrix = DungeonGenerator.generateAndReturnMatrix(randNum);
            boolean connected = DungeonGenerator.testArrays(testMatrix);
            assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
        }
    }

    @Test
    public void testInventoryHasRoom() throws InterruptedException {
        Player.inventory = new HashMap<>();
        Player.inventorySize = 10; // Assuming a default inventory size
        Player.inventory.put("item1", 5);
        assertTrue(InventoryManager.inventoryHasRoom(4));
        assertFalse(InventoryManager.inventoryHasRoom(6));
    }

    @Test
    public void testGiveItem() throws InterruptedException {
        Player.inventory = new HashMap<>();
        Player.inventorySize = 10; // Assuming a default inventory size
        Player.putItem("item1", 3);
        assertEquals(3, Player.inventory.get("item1").intValue());

        Player.putItem("item2", 2);
        assertEquals(2, Player.inventory.get("item2").intValue());

        Player.putItem("item3", 5);
        assertEquals(5, Player.inventory.get("item3").intValue());

        // Test adding item when there is no room
        Player.putItem("item4", 1);
        assertNull(Player.inventory.get("item4"));
    }

    @Test
    public void testGiveItemWithNegativeAmount() throws InterruptedException {
        Player.inventory = new HashMap<>();
        Player.putItem("item1", -1);
        assertNull(Player.inventory.get("item1"));
    }

    @Test
    public void testGiveItemWith0Amount() throws InterruptedException {
        Player.inventory = new HashMap<>();
        Player.putItem("item1", 0);
        assertNull(Player.inventory.get("item1"));
    }

    @Test
    public void testGiveItemWithZeroAmount() throws InterruptedException {
        Player.inventory = new HashMap<>();
        Player.putItem("item1", 0);
        assertNull(Player.inventory.get("item1"));
    }
}
