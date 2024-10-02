
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class TestSuite_Adventure {

    @Before
    public void setUp() {
        DungeonGenerator.testing = true;
        DungeonGenerator.wipe();
        OpenWorldGenerator.testing = true;
    }

    // @Test
    // public void testDungeonGeneratorConnection1() {
    //     DungeonGenerator.start(8);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }

    // @Test
    // public void testDungeonGeneratorConnection2() {
    //     DungeonGenerator.start(15);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }

    // @Test
    // public void testDungeonGeneratorConnection3() {
    //     DungeonGenerator.start(11);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }

    // @Test
    // public void testDungeonGeneratorConnection5() {
    //     DungeonGenerator.start(8);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }

    // @Test
    // public void testDungeonGeneratorConnection6() {
    //     DungeonGenerator.start(5);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }

    // @Test
    // public void testDungeonGeneratorConnection7() {
    //     DungeonGenerator.start(8);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }

    // @Test
    // public void testDungeonGeneratorConnection8() {
    //     DungeonGenerator.start(8);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }

    // @Test
    // public void testDungeonGeneratorConnection9() {
    //     DungeonGenerator.start(5);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }

    // @Test
    // public void testDungeonGeneratorConnection10() {
    //     DungeonGenerator.start(5);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }

    // @Test
    // public void testDungeonGeneratorConnection11() {
    //     DungeonGenerator.start(5);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }

    // @Test
    // public void testDungeonGeneratorConnection12() {
    //     DungeonGenerator.start(5);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }

    // @Test
    // public void testDungeonGeneratorConnection13() {
    //     DungeonGenerator.start(5);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }

    // @Test
    // public void testDungeonGeneratorConnection14() {
    //     DungeonGenerator.start(5);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }

    // @Test
    // //SCARY
    // public void testDungeonGeneratorConnection15() {
    //     DungeonGenerator.start(7);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }

    // @Test
    // //SCARY
    // public void testDungeonGeneratorConnection16() {
    //     DungeonGenerator.start(7);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }

    // @Test
    // public void testDungeonGeneratorConnection17() {
    //     DungeonGenerator.start(9);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }

    // @Test
    // public void testDungeonGeneratorConnection18() {
    //     DungeonGenerator.start(9);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }

    // @Test
    // public void testDungeonGeneratorConnection19() {
    //     DungeonGenerator.start(11);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }

    // @Test
    // public void testDungeonGeneratorConnection20() {
    //     DungeonGenerator.start(13);
    //     int[][] matrix = DungeonGenerator.returnMatrix();
    //     int[] pos9 = DungeonGenerator.findValue(matrix, 9);
    //     int[] pos8 = DungeonGenerator.findValue(matrix, 8);
    //     int[] pos3 = DungeonGenerator.findValue(matrix, 3);
    //     int[] pos2 = DungeonGenerator.findValue(matrix, 2);
    //     int[] pos4 = DungeonGenerator.findValue(matrix, 4);
    //     assertNotNull(pos9);
    //     assertNotNull(pos8);
    //     assertNotNull(pos3);
    //     assertNotNull(pos2);
    //     assertNotNull(pos4);
    //     boolean connected = DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos8[0], pos8[1]) && DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos3[0], pos3[1]) && DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos2[0], pos2[1]) && DungeonGenerator.isPathConnected(matrix, pos9[0], pos9[1], pos4[0], pos4[1]);
    //     assertTrue("There should be a path connecting 9 and 8 with values higher than 0", connected);
    // }
    @Test   
    public void testOpenWorldGen21(){
        int[][] matrix = OpenWorldGenerator.getMatrix();
        boolean valid = OpenWorldGenerator.validateMatrix();
        if(OpenWorldGenerator.testing) {
            OpenWorldGenerator.printMatrix();
        }
        assertTrue("The matrix should be valid", valid);
    }
    @Test   
    public void testOpenWorldGen22(){
        int[][] matrix = OpenWorldGenerator.getMatrix();
        boolean valid = OpenWorldGenerator.validateMatrix();
        if(OpenWorldGenerator.testing) {
            OpenWorldGenerator.printMatrix();
        }
        assertTrue("The matrix should be valid", valid);
    }
    @Test   
    public void testOpenWorldGen23(){
        int[][] matrix = OpenWorldGenerator.getMatrix();
        boolean valid = OpenWorldGenerator.validateMatrix();
        if(OpenWorldGenerator.testing) {
            OpenWorldGenerator.printMatrix();
        }
        assertTrue("The matrix should be valid", valid);
    }
    @Test   
    public void testOpenWorldGen24(){
        int[][] matrix = OpenWorldGenerator.getMatrix();
        boolean valid = OpenWorldGenerator.validateMatrix();
        if(OpenWorldGenerator.testing) {
            OpenWorldGenerator.printMatrix();
        }
        assertTrue("The matrix should be valid", valid);
    }
    @Test   
    public void testOpenWorldGen25(){
        int[][] matrix = OpenWorldGenerator.getMatrix();
        boolean valid = OpenWorldGenerator.validateMatrix();
        if(OpenWorldGenerator.testing) {
            OpenWorldGenerator.printMatrix();
        }
        assertTrue("The matrix should be valid", valid);
    }
    @Test   
    public void testOpenWorldGen26(){
        int[][] matrix = OpenWorldGenerator.getMatrix();
        boolean valid = OpenWorldGenerator.validateMatrix();
        if(OpenWorldGenerator.testing) {
            OpenWorldGenerator.printMatrix();
        }
        assertTrue("The matrix should be valid", valid);
    }
    @Test   
    public void testOpenWorldGen27(){
        int[][] matrix = OpenWorldGenerator.getMatrix();
        boolean valid = OpenWorldGenerator.validateMatrix();
        if(OpenWorldGenerator.testing) {
            OpenWorldGenerator.printMatrix();
        }
        assertTrue("The matrix should be valid", valid);
    }
    @Test   
    public void testOpenWorldGen28(){
        int[][] matrix = OpenWorldGenerator.getMatrix();
        boolean valid = OpenWorldGenerator.validateMatrix();
        if(OpenWorldGenerator.testing) {
            OpenWorldGenerator.printMatrix();
        }
        assertTrue("The matrix should be valid", valid);
    }
    @Test   
    public void testOpenWorldGen29(){
        int[][] matrix = OpenWorldGenerator.getMatrix();
        boolean valid = OpenWorldGenerator.validateMatrix();
        if(OpenWorldGenerator.testing) {
            OpenWorldGenerator.printMatrix();
        }
        assertTrue("The matrix should be valid", valid);
    }
    @Test   
    public void testOpenWorldGen30(){
        int[][] matrix = OpenWorldGenerator.getMatrix();
        boolean valid = OpenWorldGenerator.validateMatrix();
        if(OpenWorldGenerator.testing) {
            OpenWorldGenerator.printMatrix();
        }
        assertTrue("The matrix should be valid", valid);
    }

}
