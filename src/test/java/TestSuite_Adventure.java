
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
        DungeonGenerator.testing = true;
        DungeonGenerator.wipe();
        Main.TESTING = true;
        Player.hardSetInventorySize(20);
    }

    @After
    public void tearDown() {
        if (DungeonGenerator.testing) {
            System.out.println("-----------------------------");
            System.out.println("Tests run in dungeon gneration: " + testsrun);
            System.out.println("Dungeons Generated: " + DungeonGenerator.runs + " Fails: " + DungeonGenerator.fails);
            System.out.println(((float) DungeonGenerator.fails / DungeonGenerator.runs) * 100 + "% failure rate");
            System.out.println("For every test run, " + (float) DungeonGenerator.runs / testsrun + " dungeons were generated");
            System.out.println("-----------------------------");
            DungeonGenerator.wipe();
            DungeonGenerator.testing = false;
        }
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
        Player.hardSetInventorySize(20);
        Player.inventory = new HashMap<>();
        Player.inventory.put("heath potion", 15);
        assertTrue(InventoryManager.inventoryHasRoom(4));
        assertFalse(InventoryManager.inventoryHasRoom(6));
    }

    @Test
    public void testGiveItem() throws InterruptedException {
        Player.hardSetInventorySize(20);
        Player.inventory = new HashMap<>();
        Player.putItem("heath potion", 6);
        assertEquals(6, Player.inventory.get("heath potion").intValue());
        Player.putItem("greater health potion", 4);
        assertEquals(4, Player.inventory.get("greater health potion").intValue());
        Player.putItem("super health potion", 10);
        assertEquals(10, Player.inventory.get("super health potion").intValue());
        // Test adding item when there is no room
        Player.putItem("greater heath potion", 1);
        assertNull(Player.inventory.get("greater heath potion"));
    }

    @Test
    public void testGiveItemWithNegativeAmount() throws InterruptedException {
        Player.inventory = new HashMap<>();
        Player.hardSetInventorySize(20);
        Player.putItem("heath potion", -1);
        assertNull(Player.inventory.get("heath potion"));
    }

    @Test
    public void testGiveItemWith0Amount() throws InterruptedException {
        Player.inventory = new HashMap<>();
        Player.hardSetInventorySize(20);
        Player.putItem("heath potion", 0);
        assertNull(Player.inventory.get("heath potion"));
    }

    @Test
    public void testGiveItemWithZeroAmount() throws InterruptedException {
        Player.inventory = new HashMap<>();
        Player.hardSetInventorySize(20);
        Player.putItem("heath potion", 0);
        assertNull(Player.inventory.get("heath potion"));
    }

    @Test
    public void testChangeInventorySize() throws InterruptedException {
        Main.createGameItems();
        Player.hardSetInventorySize(20);
        Player.inventory = new HashMap<>();
        assertEquals(20, Player.getInventorySize());
        Player.putItem("Backpack", 1);
        assertEquals(35, Player.getInventorySize());
    }

    @Test
    public void testChangeInventorySize2() throws InterruptedException {
        Main.createGameItems();
        Player.hardSetInventorySize(20);
        Player.inventory = new HashMap<>();
        assertEquals(20, Player.getInventorySize());
        Player.putItem("Large Backpack", 1);
        assertEquals(50, Player.getInventorySize());
    }

    @Test
    public void testChangeInPlayerStats() throws InterruptedException {
        Player.inventory = new HashMap<>();
        Player.hardSetInventorySize(20);
        InventoryManager.setStatsToHighestInInventory();
        assertEquals(0, Player.getDamage());
        assertEquals(0, Player.getDefense());
        Player.putItem("sword", 1);
        Player.putItem("shield", 1);
        assertEquals(2, Player.getDamage());
        assertEquals(2, Player.getDefense());
    }

    @Test
    public void testChangeInPlayerStatsWithNegativeItems() throws InterruptedException {
        Player.inventory = new HashMap<>();
        Player.hardSetInventorySize(20);
        InventoryManager.setStatsToHighestInInventory();
        assertEquals(0, Player.getDamage());
        assertEquals(0, Player.getDefense());
        Player.putItem("sword", 1);
        Player.putItem("shield", 1);
        assertEquals(2, Player.getDamage());
        assertEquals(2, Player.getDefense());
        Player.putItem("sword", -1);
        assertEquals(0, Player.getDamage());
        assertEquals(2, Player.getDefense());
        Player.putItem("shield", -1);
        assertEquals(0, Player.getDamage());
        assertEquals(0, Player.getDefense());
    }

    @Test
    public void testMultipleItems() throws InterruptedException {
        Player.inventory = new HashMap<>();
        Player.hardSetInventorySize(20);
        InventoryManager.setStatsToHighestInInventory();
        assertEquals(0, Player.getDamage());
        assertEquals(0, Player.getDefense());
        Player.putItem("sword", 1);
        Player.putItem("shield", 1);
        assertEquals(2, Player.getDamage());
        assertEquals(2, Player.getDefense());
        Player.putItem("axe", 1);
        Player.putItem("chainmail set", 1);
        assertEquals(5, Player.getDamage());
        assertEquals(5, Player.getDefense());
    }

    @Test
    public void testGiveMultipleItems() throws InterruptedException {
        Player.inventory = new HashMap<>();
        Player.hardSetInventorySize(100);
        InventoryManager.setStatsToHighestInInventory();
        assertEquals(0, Player.getDamage());
        assertEquals(0, Player.getDefense());
        Player.putItem("sword", 50);
        Player.putItem("shield", 50);
        assertEquals(2, Player.getDamage());
        assertEquals(2, Player.getDefense());
    }

    @Test
    public void testChangeHealth() throws InterruptedException {
        int startHealth = Player.getHealth();
        Player.changeHealth(-10);
        assertEquals(startHealth - 10, Player.getHealth());
        Player.changeHealth(10);
        assertEquals(startHealth, Player.getHealth());
    }
}
