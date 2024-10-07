
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

// Written by Caden Finley ACU 2024
// September 30, 2024
// 8 grueling hours but totally worth this is the coolest thing ive ever build
public class DungeonGenerator {

    public static boolean testing = false;
    public static int[][] matrix;
    public static int fails = 0;
    public static int runs = 0;

    public static void wipe() {
        matrix = null;
    }

    /**
     * Starts the dungeon generation process.
     *
     * @param pass The size of the matrix to generate.
     */
    public static void start(int pass) {
        runs++;
        if (pass < 5) {
            System.out.println("-------------------------------");
            System.out.println("Matrix size too small, retrying...");
            start(5);
            return;
        }
        int size = pass;
        if (size % 2 == 0) {
            size++;
        }
        if (size > 15) {
            size = 15;
        }

        float changeRatio = 1 + (((size * size) / 1) / 12.5f);

        if (3 + size < changeRatio) {
            changeRatio = size;
        }

        matrix = new int[size][size];
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

        //determines how many random rooms are added
        // Randomly add at least size+size/2 more 1's ensuring they are connected to the main path
        addRandom(matrix, rand, size + (int) changeRatio, 1);

        float itemRoomRatio = ((2 * size) - 5.5f) - (size / 2);

        if (itemRoomRatio >= size - 5) {
            itemRoomRatio = size / 2;
        }
        if (itemRoomRatio < 1) {
            itemRoomRatio = 1;
        }

        // Randomly add item rooms (2-5) ensuring they are connected to the main path 2-5 are item rooms
        if (size > 7) {
            addRandom(matrix, rand, 2, 2);
        } else {
            addRandom(matrix, rand, (int) itemRoomRatio, 2);
        }

        // Randomly add 1 rare item (3) ensuring it is connected to the main path
        addRandom(matrix, rand, 1, 3);

        // Randomly add 1 trap room (6) ensuring it is connected to the main path
        addRandom(matrix, rand, 1, 6);

        // Randomly add mini boss rooms (4) ensuring it is connected to the main path
        addRandom(matrix, rand, 1, 4);

        // Randomly add shop rooms (4) ensuring it is connected to the main path
        //addRandom(matrix, rand, 1, 7);
        // Ensure only one 1 value is adjacent to the 8
        ensureSingleAdjacent(matrix, coord8[0], coord8[1]);

        // Place 8 and 9 back in their original positions
        matrix[coord9[0]][coord9[1]] = 9;
        matrix[coord8[0]][coord8[1]] = 8;
        matrix = trimUnreachableParts(matrix, findValue(matrix, 9));
        if (testArrays(matrix)) {
            if (testing) {
                printMap(matrix);
                System.out.println("^^^^^^^^^^^^" + size + "^^^^^^^^^^^^");
                System.out.println("Matrix connected successfully!");
                System.out.println("Item Rooms: " + numberOfRooms(matrix, 2) + " Total Rooms: " + (numberOfRooms(matrix, 1) + numberOfRooms(matrix, 2) + numberOfRooms(matrix, 3) + numberOfRooms(matrix, 8) + numberOfRooms(matrix, 9) + numberOfRooms(matrix, 4)));
                System.out.println("-------------------------------");
            }
            return;
        }
        if (testing) {
            printMap(matrix);
            System.out.println("^^^^^^^^^^^^" + size + "^^^^^^^^^^^^");
            System.out.println("Matrix not connected, retrying...");
            System.out.println("-------------------------------");
        }
        fails++;
        start(size);
    }

    /**
     * Draws a path of 1's to connect two points in the matrix.
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
     * Adds random values to the matrix ensuring they are connected to the main
     * path.
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
            } while (matrix[x][y] != 0 || !isConnected(matrix, x, y) || matrix[x][y] > 2);
            matrix[x][y] = num;
            addedOnes++;
        }
    }

    /**
     * Checks if a position in the matrix is connected to the main path.
     *
     * Algorithm: Adjacency check.
     *
     * @param matrix The matrix to check.
     * @param x The x-coordinate of the position to check.
     * @param y The y-coordinate of the position to check.
     * @return True if the position is connected to the main path, false
     * otherwise.
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
     * Ensures that only one value greater than zero is adjacent to the value 8
     * in the matrix.
     *
     * @param matrix The matrix to modify.
     * @param x The x-coordinate of the position to check.
     * @param y The y-coordinate of the position to check.
     */
    private static void ensureSingleAdjacent(int[][] matrix, int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int adjacentNonZeroCount = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix[0].length && matrix[nx][ny] > 0) {
                adjacentNonZeroCount++;
                if (adjacentNonZeroCount > 1) {
                    matrix[nx][ny] = 0;
                }
            }
        }
    }

    /**
     * Checks if there is a path connecting two points in the matrix.
     *
     * Algorithm: Breadth-First Search (BFS). Time Complexity: O(n^2) where n is
     * the size of the matrix.
     *
     * @param matrix The matrix to check.
     * @param x1 The starting x-coordinate.
     * @param y1 The starting y-coordinate.
     * @param x2 The ending x-coordinate.
     * @param y2 The ending y-coordinate.
     * @return True if there is a path connecting the two points, false
     * otherwise.
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
     * @param arrays The matrix to test.
     * @return True if the matrix meets the criteria, false otherwise.
     */
    public static boolean testArrays(int[][] arrays) {
        int[][] localMatrix = arrays;
        int[] pos9 = findValue(localMatrix, 9);
        int[] pos8 = findValue(localMatrix, 8);
        int[] pos3 = findValue(localMatrix, 3);
        int[] pos2 = findValue(localMatrix, 2);
        int[] pos4 = findValue(localMatrix, 4);
        int[] pos6 = findValue(localMatrix, 6);

        if (pos9 == null || pos8 == null || pos3 == null || pos2 == null || pos4 == null || pos6 == null) {
            return false;
        }
        return (isPathConnected(localMatrix, pos9[0], pos9[1], pos8[0], pos8[1]) && isPathConnected(localMatrix, pos9[0], pos9[1], pos3[0], pos3[1]) && isPathConnected(localMatrix, pos9[0], pos9[1], pos2[0], pos2[1]) && isPathConnected(localMatrix, pos9[0], pos9[1], pos4[0], pos4[1]) && !isAdjacent(pos8[0], pos8[1], pos6) && numberOfRooms(matrix, 2) >= 2);
    }

    /**
     * Finds the coordinates of a value in the matrix.
     *
     * Algorithm: Linear search. Time Complexity: O(n^2) where n is the size of
     * the matrix.
     *
     * @param matrix The matrix to search.
     * @param value The value to find.
     * @return The coordinates of the value as an array [x, y], or null if not
     * found.
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
     * @param size The size of the matrix to generate.
     * @return The generated matrix.
     */
    public static int[][] generateValidMatrix(int size) {
        int[][] localMatrix;
        Random random = new Random();
        do {
            localMatrix = new int[size][size];
            // Fill the matrix with random values greater than 0
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    localMatrix[i][j] = random.nextInt(10) + 1; // Values between 1 and 10
                }
            }
            // Place 8 and 9 at random positions
            localMatrix[random.nextInt(size)][random.nextInt(size)] = 8;
            localMatrix[random.nextInt(size)][random.nextInt(size)] = 9;
        } while (!isPathConnected(localMatrix, findValue(localMatrix, 9)[0], findValue(localMatrix, 9)[1], findValue(localMatrix, 8)[0], findValue(localMatrix, 8)[1]));
        return localMatrix;
    }

    /**
     * Prints the generated matrix.
     *
     * @param size The size of the matrix to print.
     */
    public static void printMap(int[][] passedMatrix) {
        for (int[] passedMatrix1 : passedMatrix) {
            for (int j = 0; j < passedMatrix.length; j++) {
                if (passedMatrix1[j] != 0) {
                    System.out.print(passedMatrix1[j] + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Prints the adjacent rooms, the current room, and unlocked rooms in the
     * dungeon matrix.
     *
     * @param passedMatrix The matrix representing the dungeon layout.
     * @param unlocked The matrix indicating which rooms have been unlocked.
     * @param passedPosition The current position of the player in the dungeon.
     */
    public static void printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(int[][] passedMatrix, int[][] unlocked, int[] passedPosition) {
        for (int i = 0; i < passedMatrix.length; i++) {
            for (int j = 0; j < passedMatrix.length; j++) {
                if (passedMatrix[i][j] != 0) {
                    if (i == passedPosition[0] && j == passedPosition[1]) {
                        System.out.print("[P] ");
                    } else if (isAdjacent(i, j, passedPosition)) {
                        switch (passedMatrix[i][j]) { //icon for unvisited rooms
                            case 8 ->
                                System.out.print("[B] "); // Special marker for value 8
                            case 0 ->
                                System.out.print("[ ] "); // Empty room
                            case 4 -> {
                                System.out.print("[!] "); // Special marker for value 3
                            }
                            case 3 -> {
                                System.out.print("[I] "); // Special marker for value 3  
                            }
                            case 2 -> {
                                System.out.print("[I] "); // Special marker for value 2
                            }
                            default ->
                                System.out.print("[~] "); // Default case for other values
                        }
                    } else if (unlocked[i][j] > 0) {
                        switch (unlocked[i][j]) { //icon for visited rooms
                            case 9 ->
                                System.out.print("[*] "); // Special marker for value 9
                            case 2 ->
                                System.out.print("[I] "); // Special marker for value 
                            case 3 ->
                                System.out.print("[I] "); // Special marker for value 3
                            case 4 ->
                                System.out.print("[!] "); // Special marker for value 4
                            default ->
                                System.out.print("[.] "); // Default case for other values
                        }
                    } else {
                        System.out.print("[ ] "); // Print brackets around non-zero values
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

    /**
     * Checks if a given position is adjacent to the player's current position.
     *
     * @param x The x-coordinate of the position to check.
     * @param y The y-coordinate of the position to check.
     * @param playerPosition The current position of the player.
     * @return True if the position is adjacent to the player's position, false
     * otherwise.
     */
    private static boolean isAdjacent(int x, int y, int[] playerPosition) {
        int px = playerPosition[0];
        int py = playerPosition[1];
        return (Math.abs(px - x) == 1 && py == y) || (Math.abs(py - y) == 1 && px == x);
    }

    /**
     * Trims off the unreachable parts of the matrix.
     *
     * @param matrix The matrix to trim.
     * @param startPosition The starting position of the player.
     * @return The trimmed matrix.
     */
    public static int[][] trimUnreachableParts(int[][] matrix, int[] startPosition) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(startPosition);
        visited[startPosition[0]][startPosition[1]] = true;

        // Directions for moving up, down, left, right
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Perform BFS to mark all reachable cells
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && !visited[nx][ny] && matrix[nx][ny] != 0) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        // Set all unreachable cells to 0
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    matrix[i][j] = 0;
                }
            }
        }

        return matrix;
    }

    /**
     * Generates and returns a valid matrix with a path connecting the values 8
     * and 9.
     *
     * @param size The size of the matrix to generate.
     * @return The generated matrix.
     */
    public static int[][] generateAndReturnMatrix(int size) {
        start(size);
        return matrix;
    }

    /**
     * Returns an array of all adjacent values greater than 0 to a given point
     * on the passed matrix.
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

    /**
     * Creates a 2D array representing rooms that have been visited. Each
     * element in the array is initialized to 0.
     *
     * @param size the size of the 2D array (size x size)
     * @return a 2D array with all elements initialized to 0
     */
    public static int[][] createRoomsBeenTo(int size) {
        int[][] temp = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp[i][j] = 0;
            }
        }
        return temp;
    }

    /**
     * Counts the number of rooms in the matrix that match the specified value.
     *
     * This method is only used by TestSuite_Adventure.java and is not part of
     * the main program.
     *
     * @param matrix the 2D array representing the rooms
     * @param find the value to count in the matrix
     * @return the number of rooms that match the specified value
     */
    public static int numberOfRooms(int[][] matrix, int find) {
        int count = 0;
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                if (ints[j] == find) {
                    count++;
                }
            }
        }
        return count;
    }
}
