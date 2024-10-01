import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

// Written by Caden Finley ACU 2024
// September 30, 2024

public class DungeonGenerator {
    public static boolean testing = false;
    public static int[][] matrix;

    /**
     * Starts the dungeon generation process.
     * This method generates a matrix with a path connecting the values 8 and 9,
     * and ensures the matrix meets certain criteria.
     * 
     * Algorithm: Randomized placement with pathfinding and connectivity checks.
     * Time Complexity: O(n^2) for matrix generation and pathfinding.
     * 
     * @param pass The size of the matrix to generate.
     */
    public static void start(int pass) {
        if (pass < 5) {
            System.out.println("-------------------------------");
            System.out.println("Matrix size too small, retrying...");
            start(5);
            return;
        }
        int sizeY = pass;
        int size = pass;
        matrix = new int[size][sizeY];
        Random rand = new Random();

        // Place 9 at a random position on the bottom row
        int x1 = size - 1;
        int y1 = rand.nextInt(size);
        matrix[x1][y1] = 9;

        // Place 8 at a random position at least size steps away from (x1, y1)
        int x2, y2;
        do {
            x2 = rand.nextInt(size);
            y2 = rand.nextInt(size);
        } while (Math.abs(x1 - x2) + Math.abs(y1 - y2) < size);
        matrix[x2][y2] = 8;

        // Draw path of 1's to connect 9 and 8
        drawPath(matrix, x1, y1, x2, y2, rand);

        // Save coordinates of 8 and 9
        int[] coord9 = {x1, y1};
        int[] coord8 = {x2, y2};

        // Remove 8 and 9 temporarily
        matrix[coord9[0]][coord9[1]] = 0;
        matrix[coord8[0]][coord8[1]] = 0;
        float changeRatio = ((size * size) / 1) / 25f;
        // Randomly add at least size+size/2 more 1's ensuring they are connected to the main path
        addRandom(matrix, rand, size + (int) changeRatio, 1);

        // Randomly add 2 item rooms (1) ensuring they are connected to the main path
        addRandom(matrix, rand, 1, 2);

        // Randomly add 1 rare item (3) ensuring it is connected to the main path
        addRandom(matrix, rand, 1, 3);

        // Ensure only one 1 value is adjacent to the 8
        ensureSingleAdjacentOne(matrix, coord8[0], coord8[1]);

        // Place 8 and 9 back in their original positions
        matrix[coord9[0]][coord9[1]] = 9;
        matrix[coord8[0]][coord8[1]] = 8;
        if (testArrays(matrix)) {
            if(testing){
                printMap(size);
                System.out.println("^^^^^^^^^^^^"+pass+"^^^^^^^^^^^^");
                System.out.println("Matrix connected successfully!");
                System.out.println("-------------------------------");
            }
            return;
        }
        if(testing){
            printMap(size);
            System.out.println("^^^^^^^^^^^^"+pass+"^^^^^^^^^^^^");
            System.out.println("Matrix not connected, retrying...");
            System.out.println("-------------------------------");
        }
        start(size);
    }

    /**
     * Draws a path of 1's to connect two points in the matrix.
     * 
     * Algorithm: Randomized path drawing with deviations.
     * Time Complexity: O(n) where n is the distance between the two points.
     * 
     * @param matrix The matrix to draw the path in.
     * @param x1 The starting x-coordinate.
     * @param y1 The starting y-coordinate.
     * @param x2 The ending x-coordinate.
     * @param y2 The ending y-coordinate.
     * @param rand The Random instance to use for generating random deviations.
     */
    private static void drawPath(int[][] matrix, int x1, int y1, int x2, int y2, Random rand) {
        while (x1 != x2 || y1 != y2) {
            if (rand.nextBoolean()) {
                // Randomly decide whether to move in the x or y direction
                if (x1 != x2 && rand.nextBoolean()) {
                    x1 += (x1 < x2) ? 1 : -1;
                } else if (y1 != y2) {
                    y1 += (y1 < y2) ? 1 : -1;
                }
            } else {
                // Move in the perpendicular direction
                if (y1 != y2 && rand.nextBoolean()) {
                    y1 += (y1 < y2) ? 1 : -1;
                } else if (x1 != x2) {
                    x1 += (x1 < x2) ? 1 : -1;
                }
            }

            if (matrix[x1][y1] == 0) {
                matrix[x1][y1] = 1;
            }
        }
    }

    /**
     * Adds random values to the matrix ensuring they are connected to the main path.
     * 
     * Algorithm: Randomized placement with connectivity checks.
     * Time Complexity: O(n^2) in the worst case for checking connectivity.
     * 
     * @param matrix The matrix to add values to.
     * @param rand The Random instance to use for generating random positions.
     * @param minOnes The minimum number of values to add.
     * @param num The value to add to the matrix.
     */
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

    /**
     * Checks if a position in the matrix is connected to the main path.
     * 
     * Algorithm: Adjacency check.
     * Time Complexity: O(1) for checking adjacent cells.
     * 
     * @param matrix The matrix to check.
     * @param x The x-coordinate of the position to check.
     * @param y The y-coordinate of the position to check.
     * @return True if the position is connected to the main path, false otherwise.
     */
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

    /**
     * Ensures only one 1 value is adjacent to a given position in the matrix.
     * 
     * Algorithm: Adjacency check and modification.
     * Time Complexity: O(1) for checking and modifying adjacent cells.
     * honestly idrk the speed
     * 
     * @param matrix The matrix to modify.
     * @param x The x-coordinate of the position to check.
     * @param y The y-coordinate of the position to check.
     */
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

    /**
     * Checks if there is a path connecting two points in the matrix.
     * 
     * Algorithm: Breadth-First Search (BFS).
     * Time Complexity: O(n^2) where n is the size of the matrix.
     * 
     * @param matrix The matrix to check.
     * @param x1 The starting x-coordinate.
     * @param y1 The starting y-coordinate.
     * @param x2 The ending x-coordinate.
     * @param y2 The ending y-coordinate.
     * @return True if there is a path connecting the two points, false otherwise.
     */
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

    /**
     * Tests if the matrix meets certain criteria.
     * 
     * Algorithm: Pathfinding and connectivity checks.
     * Time Complexity: O(n^2) for pathfinding and connectivity checks.
     * 
     * @param arrays The matrix to test.
     * @return True if the matrix meets the criteria, false otherwise.
     */
    public static boolean testArrays(int[][] arrays) {
        int[][] localMatrix = arrays;
        int[] pos9 = findValue(localMatrix, 9);
        int[] pos8 = findValue(localMatrix, 8);
        int[] pos3 = findValue(localMatrix, 3);

        if (pos9 == null || pos8 == null || pos3 == null) {
            return false;
        }
        return isPathConnected(localMatrix, pos9[0], pos9[1], pos8[0], pos8[1]) && isPathConnected(localMatrix, pos3[0], pos3[1], pos9[0], pos9[1]);
    }

    /**
     * Finds the coordinates of a value in the matrix.
     * 
     * Algorithm: Linear search.
     * Time Complexity: O(n^2) where n is the size of the matrix.
     * 
     * @param matrix The matrix to search.
     * @param value The value to find.
     * @return The coordinates of the value as an array [x, y], or null if not found.
     */
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

    /**
     * Returns the generated matrix.
     * 
     * @return The generated matrix.
     */
    public static int[][] returnMatrix() {
        return matrix;
    }

    /**
     * Generates a valid matrix with a path connecting the values 8 and 9.
     * 
     * Algorithm: Randomized placement with pathfinding and connectivity checks.
     * Time Complexity: O(n^2) for matrix generation and pathfinding.
     * 
     * @param size The size of the matrix to generate.
     * @return The generated matrix.
     */
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

    /**
     * Prints the generated matrix.
     * 
     * @param size The size of the matrix to print.
     */
    public static void printMap(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] != 0) {
                    System.out.print(matrix[i][j] + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
    /**
     * Prints the generated matrix.
     * 
     * @param size The size of the matrix to print.
     */
    public static void printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(int[][] passedMatrix, int[][] unlocked, int[] passedPosition) {
        for (int i = 0; i < passedMatrix.length; i++) {
            for (int j = 0; j < passedMatrix.length; j++) {
                if (passedMatrix[i][j] != 0) {
                    if (i == passedPosition[0] && j == passedPosition[1]) {
                        System.out.print("[P] ");
                    } else if (isAdjacent(i, j, passedPosition)) {
                        switch (passedMatrix[i][j]){
                            case 9 -> System.out.print("[*] ");
                            case 8 -> System.out.print("[!] ");
                            case 0 -> System.out.print("[ ] ");
                            default -> System.out.print("[" + passedMatrix[i][j] + "] ");
                        }
                    } else if (unlocked[i][j] > 0) {
                        switch (unlocked[i][j]) {
                            case 9 -> System.out.print("[*] ");
                            case 8 -> System.out.print("[!] ");
                            default -> System.out.print("[" + passedMatrix[i][j] + "] ");
                        }
                    } else {
                        System.out.print("[ ] ");
                    }
                } else if (unlocked[i][j] == 1) {
                    System.out.print("[ ] "); // Print brackets around 0 values if unlocked
                } else {
                    System.out.print("    "); // No brackets around 0 values
                }
            }
            System.out.println();
        }
    }
    private static boolean isAdjacent(int i, int j, int[] passedPosition) {
        int x = passedPosition[0];
        int y = passedPosition[1];
        return (i == x && (j == y - 1 || j == y + 1)) || (j == y && (i == x - 1 || i == x + 1));
    }
    /**
     * Generates and returns a valid matrix with a path connecting the values 8 and 9.
     * 
     * @param size The size of the matrix to generate.
     * @return The generated matrix.
     */
    public static int[][] generateAndReturnMatrix(int size) {
        start(size);
        return matrix;
    }

     /**
     * Returns an array of all adjacent values greater than 0 to a given point on the passed matrix.
     * 
     * @param matrix The matrix to check.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @return An array of adjacent values greater than 0.
     */
    public static int[] getDirections(int[][] matrix, int x, int y) {
        List<Integer> directions = new ArrayList<>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix[0].length) {
                directions.add(matrix[nx][ny]);
            } else {
                directions.add(0); // Add 0 if out of bounds
            }
        }
        return directions.stream().mapToInt(i -> i).toArray();
    }
    public static void __init__() {
        //initialize the meadow dungeon
    }
    public static int[][] createRoomsBeenTo(int size) {
        int[][] temp = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp[i][j] = 0;
            }
        }
        return temp;
    }
}