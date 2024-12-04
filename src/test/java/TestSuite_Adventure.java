
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.junit.After;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * ASCIIADVENTURER TEST SUITE Caden Finley Albert Tucker Grijesh Shrestha
 *
 * @author ASCIIADVENTURERS
 * @version 1.0
 */
public class TestSuite_Adventure {

    private static int testsrun = 0;
    private ClockEngine timer;
    private ClockEngine stopwatch;
    private DungeonInstance dungeonInstance;
    private List<String> enemies;
    private List<String> items;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

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
        Random rand = new Random();
        int y = rand.nextInt(11) + 6;
        int[][] map = generator.generateDungeon(y);
        int[][] created = DungeonGenerator.createRoomsBeenTo(map.length);
        int[] x = DungeonGenerator.findValue(map, 9);
        DungeonGenerator.drawRoom(map, created, x[0], x[1], 0, false);
        DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(map, created, x, true);
        boolean connected = generator.testArrays();
        assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    }

    @Test
    public void stupidStress() {
        for (int i = 0; i < 1000; i++) {
            DungeonGenerator generator = new DungeonGenerator();
            testsrun++;
            Random rand = new Random();
            int y = rand.nextInt(11) + 6;
            int[][] map = generator.generateDungeon(y);
            int[][] created = DungeonGenerator.createRoomsBeenTo(map.length);
            int[] x = DungeonGenerator.findValue(map, 9);
            DungeonGenerator.drawRoom(map, created, x[0], x[1], 0, false);
            DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(map, created, x, true);
            boolean connected = generator.testArrays();
            assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
        }
    }

    @Test
    public void testRoomGeneration() {
        DungeonGenerator generator = new DungeonGenerator();
        testsrun++;
        int[][] map = generator.generateDungeon(10);
        int[][] created = DungeonGenerator.createRoomsBeenTo(map.length);
        int[] x = DungeonGenerator.findValue(map, 9);
        DungeonGenerator.drawRoom(map, created, x[0], x[1], 0, false);
        DungeonGenerator.printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(map, created, x, true);
        assertNotNull(map);
        assertNotNull(created);
        assertNotNull(x);
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
        assertEquals(7, Player.getDefense());
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
        assertEquals(7, Player.getDefense());
        Player.putItem("sword", -1);
        Player.putItem("shield", -1);
        assertEquals(5, Player.getDamage());
        assertEquals(7, Player.getDefense());
        Player.putItem("sword", 1);
        Player.putItem("shield", 1);
        assertEquals(5, Player.getDamage());
        assertEquals(7, Player.getDefense());
        Player.putItem("chainmail set", -1);
        assertEquals(2, Player.getDefense());
    }

    @Test
    public void testStartClockTimer() throws InterruptedException {
        System.out.println("Starting testStartClockTimer");
        timer = new ClockEngine("timer");
        timer.startClock(2);
        Thread.sleep(3000); // Wait for the timer to finish
        assertFalse(timer.isRunning());
        assertEquals(0, timer.getRemainingTimeInSeconds());
    }

    @Test
    public void testStartClockStopwatch() throws InterruptedException {
        System.out.println("Starting testStartClockStopwatch");
        stopwatch = new ClockEngine("stopwatch");
        stopwatch.startClock(1);
        Thread.sleep(3000); // Wait for the stopwatch to run
        assertTrue(stopwatch.isRunning());
        stopwatch.stopClock();
        assertFalse(stopwatch.isRunning());
        assertTrue(stopwatch.getTimeElapsedInSeconds() >= 2);
    }

    @Test
    public void testStopClock() {
        System.out.println("Starting testStopClock");
        stopwatch = new ClockEngine("stopwatch");
        stopwatch.startClock(1);
        stopwatch.stopClock();
        assertFalse(stopwatch.isRunning());
    }

    @Test
    public void testReturnTime() {
        stopwatch = new ClockEngine("stopwatch");
        stopwatch.debugTime(3661); // 1 hour, 1 minute, 1 second
        assertEquals("01:01:01", stopwatch.returnTime());
    }

    @Test
    public void testGetTimeElapsedInSeconds() {
        stopwatch = new ClockEngine("stopwatch");
        stopwatch.debugTime(3661);
        assertEquals(3661, stopwatch.getTimeElapsedInSeconds());
    }

    @Test
    public void testGetRemainingTimeInSeconds() {
        timer = new ClockEngine("timer");
        timer.startClock(10);
        timer.addTimeToTimerInSeconds(5);
        assertTrue(timer.getRemainingTimeInSeconds() > 0);
    }

    @Test
    public void testSetSavedTimeInSeconds() {
        stopwatch = new ClockEngine("stopwatch");
        stopwatch.setSavedTimeInSeconds(500);
        assertEquals(500, stopwatch.getTimeElapsedInSeconds());
    }

    @Test
    public void testDebugTime() {
        stopwatch = new ClockEngine("stopwatch");
        stopwatch.debugTime(1000);
        assertEquals(1000, stopwatch.getTimeElapsedInSeconds());
    }

    @Test
    public void testAddTimeToTimerInSeconds() {
        timer = new ClockEngine("timer");
        timer.startClock(10);
        timer.addTimeToTimerInSeconds(100);
        assertTrue(timer.getRemainingTimeInSeconds() > 10);
    }

    @Test
    public void testDungeonInitialization() {
        enemies = new ArrayList<>();
        items = new ArrayList<>();
        enemies.add("Goblin");
        items.add("Sword");
        dungeonInstance = new DungeonInstance(enemies, items, false, false, false, "Dungeon1", "Save1", "MiniBoss1", "Boss1", 10);
        assertNotNull(dungeonInstance);
        assertEquals("Dungeon1", dungeonInstance.dungeonName);
        assertEquals("Save1", dungeonInstance.dungeonSaveName);
        assertEquals("MiniBoss1", dungeonInstance.miniBossType);
        assertEquals("Boss1", dungeonInstance.bossType);
        assertEquals(10, dungeonInstance.potentialEnemies);
        assertFalse(dungeonInstance.completed);
        assertFalse(dungeonInstance.visited);
        assertFalse(dungeonInstance.mapRevealed);
        assertEquals(enemies, dungeonInstance.enemies);
        assertEquals(items, dungeonInstance.items);
    }

    @Test
    public void testSetValues() {
        enemies = new ArrayList<>();
        items = new ArrayList<>();
        enemies.add("Goblin");
        items.add("Sword");
        dungeonInstance = new DungeonInstance(enemies, items, false, false, false, "Dungeon1", "Save1", "MiniBoss1", "Boss1", 10);
        dungeonInstance.map = new int[][]{{0, 9}, {1, 2}};
        dungeonInstance.setValues();
        assertArrayEquals(new int[]{0, 1}, dungeonInstance.spawnPosition);
    }

    @Test
    public void testFresh() {
        enemies = new ArrayList<>();
        items = new ArrayList<>();
        enemies.add("Goblin");
        items.add("Sword");
        dungeonInstance = new DungeonInstance(enemies, items, false, false, false, "Dungeon1", "Save1", "MiniBoss1", "Boss1", 10);
        dungeonInstance.map = new int[][]{{0, 9}, {1, 2}};
        dungeonInstance.fresh();
        assertFalse(dungeonInstance.mapRevealed);
        assertFalse(dungeonInstance.visited);
        assertFalse(dungeonInstance.completed);
        assertNotNull(dungeonInstance.spawnPosition);
        assertNotNull(dungeonInstance.roomsBeenTo);
    }

    @Test
    public void testSetWidth() {
        System.setOut(new PrintStream(outContent));
        TextEngine.setWidth();
        assertTrue(TextEngine.MAX_LINE_WIDTH > 0);
        System.setOut(originalOut);
    }

    @Test
    public void testClearScreen() {
        System.setOut(new PrintStream(outContent));
        TextEngine.clearScreen();
        assertEquals("\033[H\033[2J", outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    public void testCheckValidInput() {
        System.setOut(new PrintStream(outContent));
        assertTrue(TextEngine.checkValidInput("command"));
        assertFalse(TextEngine.checkValidInput(""));
        assertFalse(TextEngine.checkValidInput(null));
        System.setOut(originalOut);
    }

    @Test
    public void testParseCommand() {
        System.setOut(new PrintStream(outContent));
        String[] possibleCommands = {"look", "take", "open"};
        assertEquals("look", TextEngine.parseCommand("look", possibleCommands));
        assertEquals("take", TextEngine.parseCommand("take", possibleCommands));
        assertEquals("open", TextEngine.parseCommand("open", possibleCommands));
        assertEquals("unknown", TextEngine.parseCommand("unknown", possibleCommands));
        System.setOut(originalOut);
    }

    @Test
    public void testGetMatchLength() {
        System.setOut(new PrintStream(outContent));
        assertEquals(3, TextEngine.getMatchLength("look", "loo"));
        assertEquals(0, TextEngine.getMatchLength("look", "take"));
        System.setOut(originalOut);
    }

    @Test
    public void testHas() {
        System.setOut(new PrintStream(outContent));
        String[] possibleCommands = {"look", "take", "open"};
        assertTrue(TextEngine.has(possibleCommands, "look"));
        assertFalse(TextEngine.has(possibleCommands, "unknown"));
        System.setOut(originalOut);
    }

    @Test
    public void testParseCommand0() {
        System.setOut(new PrintStream(outContent));
        String[] possibleCommands = {"look", "take", "open"};
        assertEquals("look", TextEngine.parseCommand("look", possibleCommands));
        assertEquals("take", TextEngine.parseCommand("take", possibleCommands));
        assertEquals("open", TextEngine.parseCommand("open", possibleCommands));
        assertEquals("unknown", TextEngine.parseCommand("unknown", possibleCommands));
        assertEquals("look", TextEngine.parseCommand("LOOK", possibleCommands)); // Case sensitivity test
        assertEquals("look", TextEngine.parseCommand("lo", possibleCommands)); // Partial match test
        assertEquals("", TextEngine.parseCommand("", possibleCommands)); // Empty string test
        System.setOut(originalOut);
    }

    @Test
    public void testGetMatchLength0() {
        System.setOut(new PrintStream(outContent));
        assertEquals(3, TextEngine.getMatchLength("look", "loo"));
        assertEquals(0, TextEngine.getMatchLength("look", "take"));
        assertEquals(4, TextEngine.getMatchLength("look", "look"));
        assertEquals(0, TextEngine.getMatchLength("look", ""));
        assertEquals(0, TextEngine.getMatchLength("", "look"));
        assertEquals(0, TextEngine.getMatchLength("look", null));
        assertEquals(0, TextEngine.getMatchLength(null, "look"));
        System.setOut(originalOut);
    }

    @Test
    public void testHas0() {
        System.setOut(new PrintStream(outContent));
        String[] possibleCommands = {"look", "take", "open"};
        assertTrue(TextEngine.has(possibleCommands, "look"));
        assertFalse(TextEngine.has(possibleCommands, "unknown"));
        assertTrue(TextEngine.has(possibleCommands, "take"));
        assertTrue(TextEngine.has(possibleCommands, "open"));
        assertFalse(TextEngine.has(possibleCommands, "LOOK")); // Case sensitivity test
        assertFalse(TextEngine.has(possibleCommands, "")); // Empty string test
        assertFalse(TextEngine.has(possibleCommands, null)); // Null test
        System.setOut(originalOut);
    }
}
