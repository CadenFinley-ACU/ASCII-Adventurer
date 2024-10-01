
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class DungeonGenerator {
    public static int[][] matrix;

    public static void start(int size) {
        matrix = new int[size][size];
        Random rand = new Random();

        // Place 9 at the bottom row in the middle position
        int x1 = size - 1;
        int y1 = size / 2;
        matrix[x1][y1] = 9;

        // Place 8 at a random position at least size steps away from (x1, y1)
        int x2, y2;
        do {
            x2 = rand.nextInt(size);
            y2 = rand.nextInt(size);
        } while (Math.abs(x1 - x2) + Math.abs(y1 - y2) < size);
        matrix[x2][y2] = 8;

        // Draw path of 1's to connect 9 and 8
        drawPath(matrix, x1, y1, x2, y2);

        // Save coordinates of 8 and 9
        int[] coord9 = {x1, y1};
        int[] coord8 = {x2, y2};

        // Remove 8 and 9 temporarily
        matrix[coord9[0]][coord9[1]] = 0;
        matrix[coord8[0]][coord8[1]] = 0;

        // Randomly add at least size+size/2 more 1's ensuring they are connected to the main path
        addRandom(matrix, rand, size + size / 2, 1);

        // Randomly add at least size+size/2 more 2's ensuring they are connected to the main path
        addRandom(matrix, rand, 1, 2);

        // Randomly add at least size+size/2 more 1's ensuring they are connected to the main path
        addRandom(matrix, rand, 1, 3);

        // Ensure only one 1 value is adjacent to the 8
        ensureSingleAdjacentOne(matrix, coord8[0], coord8[1]);

        // Place 8 and 9 back in their original positions
        matrix[coord9[0]][coord9[1]] = 9;
        matrix[coord8[0]][coord8[1]] = 8;
        if (testArrays(matrix)) {
            //Print the matrix
            //printMap(size);
            return;
        }
        System.out.println("Matrix not connected, retrying...");
        printMap(size);
        start(size);
    }

    private static void drawPath(int[][] matrix, int x1, int y1, int x2, int y2) {
        while (x1 != x2 || y1 != y2) {
            if (x1 < x2) {
                x1++; 
            }else if (x1 > x2) {
                x1--; 
            }else if (y1 < y2) {
                y1++; 
            }else if (y1 > y2) {
                y1--;
            }

            if (matrix[x1][y1] == 0) {
                matrix[x1][y1] = 1;
            }
        }
    }

    private static void addRandom(int[][] matrix, Random rand, int minOnes, int num) {
        int addedOnes = 0;
        while (addedOnes < minOnes) {
            int x, y;
            do {
                x = rand.nextInt(matrix.length);
                y = rand.nextInt(matrix.length);
            } while (matrix[x][y] != 0 || !isConnected(matrix, x, y));
            matrix[x][y] = num;
            addedOnes++;
        }
    }

    private static boolean isConnected(int[][] matrix, int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix.length && matrix[nx][ny] == 1) {
                return true;
            }
        }
        return false;
    }

    private static void ensureSingleAdjacentOne(int[][] matrix, int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int adjacentOnes = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix.length && matrix[nx][ny] == 1) {
                adjacentOnes++;
                if (adjacentOnes > 1) {
                    matrix[nx][ny] = 0;
                }
            }
        }
    }

    public static boolean isPathConnected(int[][] matrix, int x1, int y1, int x2, int y2) {
        int size = matrix.length;
        boolean[][] visited = new boolean[size][size];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x1, y1});
        visited[x1][y1] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            if (cx == x2 && cy == y2) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < size && ny >= 0 && ny < size && !visited[nx][ny] && matrix[nx][ny] > 0) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        return false;
    }

    public static boolean testArrays(int[][] arrays) {
        int[][] localMatrix = arrays;
        int[] pos9 = findValue(localMatrix, 9);
        int[] pos8 = findValue(localMatrix, 8);

        if (pos9 == null || pos8 == null) {
            return false;
        }
        return isPathConnected(localMatrix, pos9[0], pos9[1], pos8[0], pos8[1]);
    }

    public static int[] findValue(int[][] matrix, int value) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == value) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[][] returnMatrix() {
        // Method implementation or comment explaining why it's empty
        return matrix;
    }

    public static int[][] generateValidMatrix(int size) {
        int[][] matrix;
        Random random = new Random();
        do {
            matrix = new int[size][size];
            // Fill the matrix with random values greater than 0
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    matrix[i][j] = random.nextInt(10) + 1; // Values between 1 and 10
                }
            }
            // Place 8 and 9 at random positions
            matrix[random.nextInt(size)][random.nextInt(size)] = 8;
            matrix[random.nextInt(size)][random.nextInt(size)] = 9;
        } while (!isPathConnected(matrix, findValue(matrix, 9)[0], findValue(matrix, 9)[1], findValue(matrix, 8)[0], findValue(matrix, 8)[1]));
        return matrix;
    }
    public static void printMap(int size){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
