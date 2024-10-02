

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OpenWorldGenerator {

    private static final int SIZE = 11;
    private static int[][] matrix;
    private List<int[]> positions;
    private List<int[]> fivePositions;
    private int[] ninePosition;
    private Random random = new Random();
    public static boolean testing = false;

    public OpenWorldGenerator() {
        matrix = new int[SIZE][SIZE];
        positions = new ArrayList<>();
        fivePositions = new ArrayList<>();
    }

    public void placeNineAtCenter() {
        int centerRow = SIZE / 2;
        int centerCol = SIZE / 2;
        matrix[centerRow][centerCol] = 9;
        ninePosition = new int[]{centerRow, centerCol};
    }

    public void placeEightsWithDistance() {
        random = new Random();
        int count = 0;
        while (count < 8) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            if (isValidPosition(row, col, positions)) {
                matrix[row][col] = 8;
                positions.add(new int[]{row, col});
                count++;
            }
        }
    }

    public void placeFivesWithDistance() {
        random = new Random();
        int count = 0;
        while (count < 4) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            if (isValidPosition(row, col, fivePositions)) {
                matrix[row][col] = 5;
                fivePositions.add(new int[]{row, col});
                count++;
            }
        }
    }

    private boolean isValidPosition(int row, int col, List<int[]> positions) {
        int minDistance = Integer.MAX_VALUE;
        int distanceOffset = SIZE / 2;
    
        for (int[] pos : positions) {
            int distance = Math.abs(pos[0] - row) + Math.abs(pos[1] - col);
            
            // Check if the position is adjacent to any 9 values
            if (pos[0] == 9 && (Math.abs(pos[0] - row) <= 1 && Math.abs(pos[1] - col) <= 1)) {
                return false;
            }
    
            if (distance < minDistance) {
                minDistance = distance;
            }
        }
        return minDistance >= distanceOffset;
    }

    public void drawLinesFromNineToEights() {
        for (int[] pos : positions) {
            drawRandomLine(ninePosition[0], ninePosition[1], pos[0], pos[1]);
        }
    }

    public void drawLinesFromNineToFives() {
        for (int[] pos : fivePositions) {
            drawRandomLine(ninePosition[0], ninePosition[1], pos[0], pos[1]);
        }
    }

    private void drawRandomLine(int x1, int y1, int x2, int y2) {
        random = new Random();
        while (x1 != x2 || y1 != y2) {
            if (matrix[x1][y1] == 0) {
                matrix[x1][y1] = 1;
            }
            if (x1 != x2 && y1 != y2) {
                if (random.nextBoolean()) {
                    x1 += (x1 < x2) ? 1 : -1;
                } else {
                    y1 += (y1 < y2) ? 1 : -1;
                }
            } else if (x1 != x2) {
                x1 += (x1 < x2) ? 1 : -1;
            } else if (y1 != y2) {
                y1 += (y1 < y2) ? 1 : -1;
            }
        }
        // Mark the final position
        if (matrix[x1][y1] == 0) {
            matrix[x1][y1] = 1;
        }
    }
    public void drawCircleFromNine() {
        int centerX = -1, centerY = -1;

        // Find the center point where 9 is located
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (matrix[i][j] == 9) {
                    centerX = i;
                    centerY = j;
                    break;
                }
            }
            if (centerX != -1) break;
        }

        if (centerX == -1 || centerY == -1) {
            System.out.println("Center point with value 9 not found.");
            return;
        }

        // Calculate the maximum radius with reduced randomness
        int baseRadius = Math.min(Math.min(centerX, SIZE - centerX - 1), Math.min(centerY, SIZE - centerY - 1));
        int radius = baseRadius+1; // Randomly reduce the radius by up to 1 unit

        // Draw the circle using the midpoint circle algorithm with reduced randomness
        int x = radius;
        int y = 0;
        int decisionOver2 = 1 - x; // Decision criterion divided by 2 evaluated at x=r, y=0

        while (y <= x) {
            // Set the points in all octants with reduced randomness
            if (random.nextInt(10) < 8) setPoint(centerX + x, centerY + y);
            if (random.nextInt(10) < 8) setPoint(centerX + y, centerY + x);
            if (random.nextInt(10) < 8) setPoint(centerX - x, centerY + y);
            if (random.nextInt(10) < 8) setPoint(centerX - y, centerY + x);
            if (random.nextInt(10) < 8) setPoint(centerX - x, centerY - y);
            if (random.nextInt(10) < 8) setPoint(centerX - y, centerY - x);
            if (random.nextInt(10) < 8) setPoint(centerX + x, centerY - y);
            if (random.nextInt(10) < 8) setPoint(centerX + y, centerY - x);
            y++;
            if (decisionOver2 <= 0) {
                decisionOver2 += 2 * y + 1; // Change in decision criterion for y -> y+1
            } else {
                x--;
                decisionOver2 += 2 * (y - x) + 1; // Change for y -> y+1, x -> x-1
            }
        }

        // Fill the inside of the circle with 1s
        fillInsideCircle(centerX, centerY, radius);
    }

    private void setPoint(int x, int y) {
        if (x >= 0 && x < SIZE && y >= 0 && y < SIZE && matrix[x][y] == 0) {
            matrix[x][y] = 1; // Use 1 to represent the circle, only if the cell is empty
        }
    }

    private void fillInsideCircle(int centerX, int centerY, int radius) {
        for (int i = centerX - radius; i <= centerX + radius; i++) {
            for (int j = centerY - radius; j <= centerY + radius; j++) {
                if (i >= 0 && i < SIZE && j >= 0 && j < SIZE) {
                    int dx = centerX - i;
                    int dy = centerY - j;
                    if (dx * dx + dy * dy <= radius * radius && matrix[i][j] == 0) {
                        matrix[i][j] = 1;   
                    }
                }
            }
        }
    }
    private static int countRooms(){
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(matrix[i][j] != 0){
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean validateMatrix() {
        int count9 = 0, count8 = 0, count5 = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (matrix[i][j] == 9) count9++;
                if (matrix[i][j] == 8) count8++;
                if (matrix[i][j] == 5) count5++;
            }
        }
        return count9 == 1 && count8 == 8 && count5 == 4;
    }

    public void generateMatrix() {
        do {
            // Clear the matrix
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    matrix[i][j] = 0;
                }
            }

            // Place the values
            placeNineAtCenter();
            placeEightsWithDistance();
            placeFivesWithDistance();
            drawLinesFromNineToEights();
            drawLinesFromNineToFives();
            drawCircleFromNine();
        } while (!validateMatrix());
    }

    public static void printMatrix() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                switch (matrix[i][j]) {
                    case 9 -> System.out.print("X");
                    case 8 -> System.out.print("D");
                    case 5 -> System.out.print("V");
                    case 1 -> System.out.print(".");
                    default -> System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    public static int[][] getMatrix() {
        OpenWorldGenerator modifier = new OpenWorldGenerator();
        modifier.generateMatrix();
        System.out.println(countRooms());
        return matrix;
    }

}