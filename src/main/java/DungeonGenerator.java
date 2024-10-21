
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
    public static String yellowColor = "\u001B[33m";
    public static String resetColor = "\u001B[0m";
    public static String redColor = "\u001B[31m";
    public static String greenColor = "\u001B[32m";
    public static String pinkColor = "\u001B[35m";

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

        // Randomly add 1 shop room (6) ensuring it is connected to the main path
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
        return (isPathConnected(localMatrix, pos9[0], pos9[1], pos8[0], pos8[1]) && isPathConnected(localMatrix, pos9[0], pos9[1], pos3[0], pos3[1]) && isPathConnected(localMatrix, pos9[0], pos9[1], pos2[0], pos2[1]) && isPathConnected(localMatrix, pos9[0], pos9[1], pos4[0], pos4[1]) && !isAdjacent(pos8[0], pos8[1], pos6) && !isAdjacent(pos8[0], pos8[1], pos4) && numberOfRooms(matrix, 2) >= 2);
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
                    System.out.print("[" + passedMatrix1[j] + "] ");
                } else {
                    System.out.print("    ");
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
    public static void printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(int[][] passedMatrix, int[][] unlocked, int[] passedPosition, boolean revealed) {
        if (!revealed) {
            for (int i = 0; i < passedMatrix.length; i++) {
                for (int j = 0; j < passedMatrix.length; j++) {
                    if (passedMatrix[i][j] != 0) {
                        if (i == passedPosition[0] && j == passedPosition[1]) {
                            System.out.print(yellowColor + "[P] " + resetColor);
                        } else if (isAdjacent(i, j, passedPosition)) {
                            switch (passedMatrix[i][j]) {
                                case 2, 5, 7 -> {
                                    if (unlocked[i][j] > 0) {
                                        System.out.print("[■] ");
                                    } else {
                                        System.out.print(greenColor + "[?] " + resetColor);
                                    }
                                }
                                case 8 ->
                                    System.out.print(redColor + "[B] " + resetColor);
                                case 9 ->
                                    System.out.print("[S] ");
                                case 6 ->
                                    System.out.print(greenColor + "[$] " + resetColor);
                                case 4 -> {
                                    if (unlocked[i][j] > 0) {
                                        System.out.print("[■] ");
                                    } else {
                                        System.out.print(redColor + "[!] " + resetColor);
                                    }
                                }
                                case 10 -> {
                                    if (unlocked[i][j] > 0) {
                                        System.out.print("[■] ");
                                    } else {
                                        System.out.print(pinkColor + "[F] " + resetColor);
                                    }
                                }
                                default ->
                                    System.out.print("[~] ");
                            }
                        } else if (unlocked[i][j] > 0) {
                            switch (unlocked[i][j]) { //icon for visited rooms
                                case 6 ->
                                    System.out.print(greenColor + "[$] " + resetColor); // Special marker for shop/trap room
                                case 8 ->
                                    System.out.print(redColor + "[B] " + resetColor); // Special marker for boss room
                                case 9 -> {
                                    if (isAdjacent(i, j, passedPosition)) {
                                        System.out.print("[~] ");
                                    } else {
                                        System.out.print("[S] ");
                                    }
                                }
                                default -> {
                                    if (isAdjacent(i, j, passedPosition)) {
                                        System.out.print("[~] ");
                                    } else {
                                        System.out.print("[■] "); // Default case for other values
                                    }
                                }
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
            System.out.println();
            System.out.println("Map Key: [P] Player,\n [?] Item Room,\n [B] Boss Room,\n [S] Spawn Room,\n [$] Shop Room,\n [F] Fairy Room");
            System.out.println(" [~] Available Moves,\n [ ] Unvisited Room,\n [■] Visited Room");
        } else {
            for (int i = 0; i < passedMatrix.length; i++) {
                for (int j = 0; j < passedMatrix.length; j++) {
                    if (passedMatrix[i][j] != 0) {
                        if (i == passedPosition[0] && j == passedPosition[1]) {
                            System.out.print(yellowColor + "[P] " + resetColor);
                        } else {
                            switch (passedMatrix[i][j]) {
                                case 2, 7 -> {
                                    if (unlocked[i][j] == 2 || unlocked[i][j] == 7) {
                                        if (isAdjacent(i, j, passedPosition)) {
                                            System.out.print("[~] ");
                                        } else {
                                            System.out.print("[■] ");
                                        }
                                    } else {
                                        System.out.print(greenColor + "[?] " + resetColor);
                                    }
                                }
                                case 3, 5 -> {
                                    if (unlocked[i][j] == 3 || unlocked[i][j] == 5) {
                                        if (isAdjacent(i, j, passedPosition)) {
                                            System.out.print("[~] ");
                                        } else {
                                            System.out.print("[■] ");
                                        }
                                    } else {
                                        System.out.print(greenColor + "[K] " + resetColor);
                                    }
                                }
                                case 8 ->
                                    System.out.print(redColor + "[B] " + resetColor);
                                case 4 -> {
                                    if (unlocked[i][j] == 4) {
                                        if (isAdjacent(i, j, passedPosition)) {
                                            System.out.print("[~] ");
                                        } else {
                                            System.out.print("[■] ");
                                        }
                                    } else {
                                        System.out.print(redColor + "[!] " + resetColor);
                                    }
                                }
                                case 9 -> {
                                    System.out.print("[S] ");
                                }
                                case 6 -> {
                                    System.out.print(greenColor + "[$] " + resetColor);
                                }
                                case 1 -> {
                                    if (unlocked[i][j] == 1) {
                                        if (isAdjacent(i, j, passedPosition)) {
                                            System.out.print("[~] ");
                                        } else {
                                            System.out.print("[■] ");
                                        }
                                    } else {
                                        System.out.print("[ ] ");
                                    }
                                }
                                case 10 -> {
                                    if (unlocked[i][j] == 10) {
                                        if (isAdjacent(i, j, passedPosition)) {
                                            System.out.print("[~] ");
                                        } else {
                                            System.out.print("[■] ");
                                        }
                                    } else {
                                        System.out.print(pinkColor + "[F] " + resetColor);
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.print("    ");
                    }
                }
                System.out.println();
            }
            System.out.println();
            System.out.println("Map Key: [P] Player, [?] Item Room, [B] Boss Room, [S] Spawn Room, [$] Shop Room, [F] Fairy Room");
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

    public static void drawRoom(int[][] localDungeon, int[][] visitedRoom, int x, int y, int numberofEnemies, boolean revealed) {
        int[] moves = getDirections(localDungeon, x, y);
        //default room layout
        String[][] room = {
            {"┌", "─", "─", "─", "─", "─", "─", "─", "─", "─", "─", "─", "─", "─", "┐"},
            {"|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"},
            {"|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"},
            {"|", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "|"},
            {"└", "─", "─", "─", "─", "─", "─", "─", "─", "─", "─", "─", "─", "─", "┘"}
        };
        //render room objects
        if (visitedRoom[x][y] > 0) {
            room[2][7] = "P"; // Player
        } else {
            switch (localDungeon[x][y]) {
                case 1, 3 -> {
                    room[2][7] = "P"; // Player
                    List<int[]> emptySpots = new ArrayList<>();
                    Random random = new Random();
                    // Collect all empty spots in the room
                    for (int row = 0; row < room.length; row++) {
                        for (int col = 0; col < room[row].length; col++) {
                            if (room[row][col].equals(" ")) { // Assuming ' ' represents an empty spot
                                emptySpots.add(new int[]{row, col});
                            }
                        }
                    }
                    // Place enemies at random empty spots
                    if (localDungeon[x][y] == 3) {
                        if (numberofEnemies < 1) {
                            numberofEnemies = 1;
                        }
                    }
                    for (int i = 0; i < numberofEnemies && !emptySpots.isEmpty(); i++) {
                        int randomIndex = random.nextInt(emptySpots.size());
                        int[] spot = emptySpots.remove(randomIndex);
                        String enemyRender;
                        switch (Dungeon.enemyType) {
                            case "Goblin" ->
                                enemyRender = "G";
                            case "Orc" ->
                                enemyRender = "O";
                            case "Troll" ->
                                enemyRender = "T";
                            case "Bandit" ->
                                enemyRender = "B";
                            case "Spider" ->
                                enemyRender = "S";
                            case "Giant Rat" ->
                                enemyRender = "R";
                            case "Skeleton" ->
                                enemyRender = "S";
                            case "Zombie" ->
                                enemyRender = "Z";
                            case "Ghost" ->
                                enemyRender = "G";
                            case "Demon" ->
                                enemyRender = "D";
                            case "Vampire" ->
                                enemyRender = "V";
                            case "Werewolf" ->
                                enemyRender = "W";
                            case "Witch" ->
                                enemyRender = "W";
                            case "Giant" ->
                                enemyRender = "G";
                            case "Mummy" ->
                                enemyRender = "M";
                            case "Slime" ->
                                enemyRender = "S";
                            case "Mimic" ->
                                enemyRender = "M";
                            case "Gargoyle" ->
                                enemyRender = "G";
                            case "Sea Serpent" ->
                                enemyRender = "S";
                            case "Sea Monster" ->
                                enemyRender = "M";
                            case "Sea Witch" ->
                                enemyRender = "W";
                            case "Sea Dragon" ->
                                enemyRender = "D";
                            case "Sea Giant" ->
                                enemyRender = "G";
                            case "Scorpion" ->
                                enemyRender = "S";
                            case "Mountain Lion" ->
                                enemyRender = "M";
                            case "Barbarian" ->
                                enemyRender = "B";
                            case "Shark" ->
                                enemyRender = "S";
                            case "Pirate" ->
                                enemyRender = "P";
                            default ->
                                enemyRender = "E";
                        }
                        room[spot[0]][spot[1]] = redColor + enemyRender + resetColor; // Enemy
                    }
                }
                case 2 -> {
                    room[2][5] = "P"; // Item Room
                    room[2][7] = greenColor + "C" + resetColor; // Item
                }
                case 5 -> {
                    room[2][5] = "P"; // key Room
                    room[2][7] = greenColor + "K" + resetColor; // key
                }
                case 7 -> {
                    room[2][5] = "P"; // heart container Room
                    room[2][7] = redColor + "H" + resetColor; // heart container
                }
                case 6 -> {
                    room[2][5] = "P"; // Shop Room
                    room[2][7] = greenColor + "$" + resetColor; // Shop
                }
                case 4 -> {
                    room[2][5] = "P"; // Mini Boss Room
                    String miniBossRender;
                    switch (Dungeon.currentMiniBoss) {
                        case "Golem" ->
                            miniBossRender = "G";
                        case "Forest Guardian" ->
                            miniBossRender = "F";
                        case "Elemental" ->
                            miniBossRender = "E";
                        case "Minotaur" ->
                            miniBossRender = "M";
                        case "Sphinx" ->
                            miniBossRender = "S";
                        case "Cyclops" ->
                            miniBossRender = "C";
                        case "Medusa" ->
                            miniBossRender = "M";
                        case "Leviathan" ->
                            miniBossRender = "L";
                        default ->
                            miniBossRender = "M";
                    }
                    room[2][7] = redColor + miniBossRender + resetColor; // Mini Boss
                }
                case 8 -> {
                    room[2][5] = "P"; // Boss Room
                    String bossRender;
                    switch (Dungeon.currentBoss) {
                        case "Forest Giant" ->
                            bossRender = "F";
                        case "Forest Spirit" ->
                            bossRender = "S";
                        case "Wyvern" ->
                            bossRender = "W";
                        case "Ice Dragon" ->
                            bossRender = "I";
                        case "Phoenix" ->
                            bossRender = "P";
                        case "Giant Scorpion" ->
                            bossRender = "S";
                        case "Giant Sand Worm" ->
                            bossRender = "W";
                        case "Kraken" ->
                            bossRender = "K";
                        default ->
                            bossRender = "B";
                    }
                    room[2][7] = redColor + bossRender + resetColor; // Boss
                }
                case 10 -> {
                    room[2][5] = "P"; // fairy Room
                    room[2][7] = pinkColor + "F" + resetColor; // fairy
                }
                default -> {
                    room[2][7] = "P"; // Default
                }
            }
        }
        //get the last postinon to render the last position icon
        if (Dungeon.lastPosition != null) {
            if (x - 1 == Dungeon.lastPosition[0] && y == Dungeon.lastPosition[1]) {
                moves[0] = 15;
            } else if (x + 1 == Dungeon.lastPosition[0] && y == Dungeon.lastPosition[1]) {
                moves[1] = 15;
            } else if (x == Dungeon.lastPosition[0] && y - 1 == Dungeon.lastPosition[1]) {
                moves[2] = 15;
            } else if (x == Dungeon.lastPosition[0] && y + 1 == Dungeon.lastPosition[1]) {
                moves[3] = 15;
            }
        }
        // render all 4 possible moves with ajacent rooms icon
        switch (moves[0]) {
            case 1, 3, 9 -> {
                if (moves[0] == 3) {
                    if (revealed) {
                        room[0][7] = greenColor + "K" + resetColor;
                    } else {
                        room[0][7] = " ";
                    }
                } else {
                    room[0][7] = " ";
                }
                room[0][6] = " ";
                room[0][8] = " ";
            }
            case 2, 5, 7 -> {
                if (visitedRoom[x - 1][y] > 0) {
                    room[0][7] = " ";
                } else {
                    if (revealed) {
                        if (moves[0] == 5) {
                            room[0][7] = greenColor + "K" + resetColor;
                        } else {
                            room[0][7] = greenColor + "I" + resetColor;
                        }
                    } else {
                        room[0][7] = greenColor + "?" + resetColor;
                    }
                }
                room[0][6] = " ";
                room[0][8] = " ";
            }
            case 4 -> {
                if (visitedRoom[x - 1][y] > 0) {
                    room[0][7] = " ";
                } else {
                    room[0][7] = redColor + "!" + resetColor;
                }
                room[0][6] = " ";
                room[0][8] = " ";
            }
            case 8 -> {
                if (visitedRoom[x - 1][y] > 0) {
                    room[0][7] = " ";
                } else {
                    room[0][7] = redColor + "B" + resetColor;
                }
                room[0][6] = " ";
                room[0][8] = " ";
            }
            case 15 -> {
                room[0][7] = yellowColor + "*" + resetColor;
                room[0][6] = " ";
                room[0][8] = " ";
            }
            case 6 -> {
                room[0][7] = greenColor + "$" + resetColor;
                room[0][6] = " ";
                room[0][8] = " ";
            }
            case 10 -> {
                if (visitedRoom[x - 1][y] > 0) {
                    room[0][7] = " ";
                } else {
                    room[0][7] = pinkColor + "F" + resetColor;
                }
                room[0][6] = " ";
                room[0][8] = " ";
            }
            default ->
                room[0][7] = "─";
        }
        switch (moves[1]) {
            case 1, 3, 9 -> {
                if (moves[1] == 3) {
                    if (revealed) {
                        room[4][7] = greenColor + "K" + resetColor;
                    } else {
                        room[4][7] = " ";
                    }
                } else {
                    room[4][7] = " ";
                }
                room[4][6] = " ";
                room[4][8] = " ";
            }
            case 2, 5, 7 -> {
                if (visitedRoom[x + 1][y] > 0) {
                    room[4][7] = " ";
                } else {
                    if (revealed) {
                        if (moves[1] == 5) {
                            room[4][7] = greenColor + "K" + resetColor;
                        } else {
                            room[4][7] = greenColor + "I" + resetColor;
                        }
                    } else {
                        room[4][7] = greenColor + "?" + resetColor;
                    }
                }
                room[4][6] = " ";
                room[4][8] = " ";
            }
            case 4 -> {
                if (visitedRoom[x + 1][y] > 0) {
                    room[4][7] = " ";
                } else {
                    room[4][7] = redColor + "!" + resetColor;
                }
                room[4][6] = " ";
                room[4][8] = " ";
            }
            case 8 -> {
                if (visitedRoom[x + 1][y] > 0) {
                    room[4][7] = " ";
                } else {
                    room[4][7] = redColor + "B" + resetColor;
                }
                room[4][6] = " ";
                room[4][8] = " ";
            }
            case 15 -> {
                room[4][7] = yellowColor + "*" + resetColor;
                room[4][6] = " ";
                room[4][8] = " ";
            }
            case 6 -> {
                room[4][7] = greenColor + "$" + resetColor;
                room[4][6] = " ";
                room[4][8] = " ";
            }
            case 10 -> {
                if (visitedRoom[x + 1][y] > 0) {
                    room[4][7] = " ";
                } else {
                    room[4][7] = pinkColor + "F" + resetColor;
                }
                room[4][6] = " ";
                room[4][8] = " ";
            }
            default ->
                room[4][7] = "─";
        }
        switch (moves[2]) {
            case 1, 3, 9 -> {
                if (moves[2] == 3) {
                    if (revealed) {
                        room[2][0] = greenColor + "K" + resetColor;
                    } else {
                        room[2][0] = " ";
                    }
                } else {
                    room[2][0] = " ";
                }
            }
            case 2, 5, 7 -> {
                if (visitedRoom[x][y - 1] > 0) {
                    room[2][0] = " ";
                } else {
                    if (revealed) {
                        if (moves[2] == 5) {
                            room[2][0] = greenColor + "K" + resetColor;
                        } else {
                            room[2][0] = greenColor + "I" + resetColor;
                        }
                    } else {
                        room[2][0] = greenColor + "?" + resetColor;
                    }
                }
            }
            case 4 -> {
                if (visitedRoom[x][y - 1] > 0) {
                    room[2][0] = " ";
                } else {
                    room[2][0] = redColor + "!" + resetColor;
                }
            }
            case 8 -> {
                if (visitedRoom[x][y - 1] > 0) {
                    room[2][0] = " ";
                } else {
                    room[2][0] = redColor + "B" + resetColor;
                }
            }
            case 15 -> {
                room[2][0] = yellowColor + "*" + resetColor;
            }
            case 6 -> {
                room[2][0] = greenColor + "$" + resetColor;
            }
            case 10 -> {
                if (visitedRoom[x][y - 1] > 0) {
                    room[2][0] = " ";
                } else {
                    room[2][0] = pinkColor + "F" + resetColor;
                }
            }
            default ->
                room[2][0] = "│";
        }
        switch (moves[3]) {
            case 1, 3, 9 -> {
                if (moves[3] == 3) {
                    if (revealed) {
                        room[2][14] = greenColor + "K" + resetColor;
                    } else {
                        room[2][14] = " ";
                    }
                } else {
                    room[2][14] = " ";
                }
            }
            case 2, 5, 7 -> {
                if (visitedRoom[x][y + 1] > 0) {
                    room[2][14] = " ";
                } else {
                    if (revealed) {
                        if (moves[3] == 5) {
                            room[2][14] = greenColor + "K" + resetColor;
                        } else {
                            room[2][14] = greenColor + "I" + resetColor;
                        }
                    } else {
                        room[2][14] = greenColor + "?" + resetColor;
                    }
                }
            }
            case 4 -> {
                if (visitedRoom[x][y + 1] > 0) {
                    room[2][14] = " ";
                } else {
                    room[2][14] = redColor + "!" + resetColor;
                }
            }
            case 8 -> {
                if (visitedRoom[x][y + 1] > 0) {
                    room[2][14] = " ";
                } else {
                    room[2][14] = redColor + "B" + resetColor;
                }
            }
            case 15 -> {
                room[2][14] = yellowColor + "*" + resetColor;
            }
            case 6 -> {
                room[2][14] = greenColor + "$" + resetColor;
            }
            case 10 -> {
                if (visitedRoom[x][y + 1] > 0) {
                    room[2][14] = " ";
                } else {
                    room[2][14] = pinkColor + "F" + resetColor;
                }
            }
            default ->
                room[2][14] = "│";
        }

        // Print the room to the console
        for (int i = 0; i < 5; i++) {
            System.out.print("    ");
            for (int j = 0; j < 15; j++) {
                System.out.print(room[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}

/*
 * key
 * 1 = enemy rooms
 * 2 - item rooms
 * 3 - enemy key rooms
 * 4 - mini boss rooms
 * 5 - key rooms
 * 6 - shop rooms
 * 7 - heart container rooms
 * 8 - boss rooms
 * 9 - spawn room
 * 10 - fairy rooms
 * 15 - last position
 */
