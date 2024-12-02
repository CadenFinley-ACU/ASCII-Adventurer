
import java.util.HashMap;
import java.util.Random;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


/**
 * ASCIIADVENTURER TEST SUITE
 * Caden Finley
 * Albert Tucker
 * Grijesh Shrestha
 *
 * @author ASCIIADVENTURERS
 * @version 1.0
 */


public class TestSuite_Adventure {

    private static int testsrun = 0;

    @Before
    public void setUp() {
        // Reset the inventory and other static fields before each test
        InventoryManager.inventory = new HashMap<>();
        DungeonGenerator.testing = true;
        GameEngine.TESTING = true;
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
            DungeonGenerator.testing = false;
        }
        GameEngine.TESTING = false;
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection1() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(5);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection2() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(6);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection3() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(7);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection4() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(8);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection5() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(9);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection6() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(10);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection7() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(11);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection8() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(12);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection9() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(13);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    //merely a stress test on the algorithm
    public void testDungeonGeneratorConnection10() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(14);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(15);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize1() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(16);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize2() {

        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(17);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize3() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;

        generator.generateDungeon(18);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize4() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(19);
        boolean connected = generator.testArrays();

        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize5() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(20);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize6() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(1);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize7() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(6);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize8() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(7);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize9() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(8);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorConnectionRandomSize10() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(9);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorActualSizes1() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(10);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorActualSizes2() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(9);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorActualSizes3() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(5);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorActualSizes4() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(8);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorActualSizes5() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(10);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorActualSizes6() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(11);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorActualSizes7() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(9);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void testDungeonGeneratorActualSizes8() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        generator.generateDungeon(15);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void stupidStress() {
        for (int i = 0; i < 1000; i++) {
            DungeonGenerator generator = new DungeonGenerator();
            Random rand = new Random();
            testsrun++;
            generator.generateDungeon(rand.nextInt(11) + 6);
            boolean connected = generator.testArrays();
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
        GameEngine.createGameItems();
        Player.hardSetInventorySize(20);
        Player.inventory = new HashMap<>();
        assertEquals(20, Player.getInventorySize());
        Player.putItem("Backpack", 1);
        assertEquals(35, Player.getInventorySize());
    }

    @Test
    public void testChangeInventorySize2() throws InterruptedException {
        GameEngine.createGameItems();
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
        Player.inventory = new HashMap<>();
        Player.hardSetInventorySize(20);
        InventoryManager.setStatsToHighestInInventory();
        int startHealth = Player.getHealth();
        Player.changeHealth(-10);
        assertEquals(startHealth - 10, Player.getHealth());
        Player.changeHealth(10);
        assertEquals(startHealth, Player.getHealth());
    }

    @Test
    public void testItemModulation() throws InterruptedException {
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
        Player.putItem("sword", -1);
        Player.putItem("shield", -1);
        assertEquals(5, Player.getDamage());
        assertEquals(5, Player.getDefense());
        Player.putItem("sword", 1);
        Player.putItem("shield", 1);
        assertEquals(5, Player.getDamage());
        assertEquals(5, Player.getDefense());
    }
}
