
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
//Caden Finley
//ACU SE F24
//CS374

public class DungeonGenerator {

    public static boolean testing = false;
    public static int[][] matrix;
    public static int fails = 0;
    public static int runs = 0;

    public static void wipe() {
        matrix = null;
    }

    public static void start(int size) {
        runs++;
        if (size < 5) {
            size = 5;
        }
        if (size > 15) {
            size = 15;
        }

        matrix = new int[size][size];
        Random rand = new Random();

        int x1 = size - 1;
        int y1 = rand.nextInt(size);
        matrix[x1][y1] = 9;

        int x2, y2;
        do {
            x2 = rand.nextInt(size);
            y2 = rand.nextInt(size);
        } while (Math.abs(x1 - x2) + Math.abs(y1 - y2) < size / 2);
        matrix[x2][y2] = 8;

        drawPath(matrix, x1, y1, x2, y2, rand);

        int[] coord9 = {x1, y1};
        int[] coord8 = {x2, y2};

        matrix[coord9[0]][coord9[1]] = 0;
        matrix[coord8[0]][coord8[1]] = 0;
        if (size > 7) {
            addRandom(matrix, rand, size * 2, 1);
        } else {
            addRandom(matrix, rand, (size * 3) - (size / 2), 1);
        }

        float itemRoomRatio = Math.max(1, size / 4);
        addRandom(matrix, rand, (int) itemRoomRatio, 2);

        addRandom(matrix, rand, 1, 3);
        addRandom(matrix, rand, 1, 6);
        addRandom(matrix, rand, 1, 4);
        addRandom(matrix, rand, 1, 5);

        ensureSingleAdjacent(matrix, coord8[0], coord8[1]);

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

    private static void drawPath(int[][] matrix, int x1, int y1, int x2, int y2, Random rand) {
        while (x1 != x2 || y1 != y2) {
            if (rand.nextBoolean()) {
                if (x1 != x2 && rand.nextBoolean()) {
                    x1 += (x1 < x2) ? 1 : -1;
                } else if (y1 != y2) {
                    y1 += (y1 < y2) ? 1 : -1;
                }
            } else {
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
        int[] pos3 = findValue(localMatrix, 3);
        int[] pos2 = findValue(localMatrix, 2);
        int[] pos4 = findValue(localMatrix, 4);
        int[] pos6 = findValue(localMatrix, 6);
        int[] pos5 = findValue(localMatrix, 5);

        if (pos9 == null || pos8 == null || pos3 == null || pos2 == null || pos4 == null || pos6 == null || pos5 == null) {
            return false;
        }
        return (isPathConnected(localMatrix, pos9[0], pos9[1], pos8[0], pos8[1]) && isPathConnected(localMatrix, pos9[0], pos9[1], pos3[0], pos3[1]) && isPathConnected(localMatrix, pos9[0], pos9[1], pos2[0], pos2[1]) && isPathConnected(localMatrix, pos9[0], pos9[1], pos4[0], pos4[1]) && !isAdjacent(pos8[0], pos8[1], pos6));
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
        return matrix;
    }

    public static int[][] generateValidMatrix(int size) {
        int[][] localMatrix;
        Random random = new Random();
        do {
            localMatrix = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    localMatrix[i][j] = random.nextInt(10) + 1;
                }
            }
            localMatrix[random.nextInt(size)][random.nextInt(size)] = 8;
            localMatrix[random.nextInt(size)][random.nextInt(size)] = 9;
        } while (!isPathConnected(localMatrix, findValue(localMatrix, 9)[0], findValue(localMatrix, 9)[1], findValue(localMatrix, 8)[0], findValue(localMatrix, 8)[1]));
        return localMatrix;
    }

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

    public static void printAdjacentRoomsAndCurrentRoomAndUnlockedRooms(int[][] passedMatrix, int[][] unlocked, int[] passedPosition) {
        for (int i = 0; i < passedMatrix.length; i++) {
            for (int j = 0; j < passedMatrix.length; j++) {
                if (passedMatrix[i][j] != 0) {
                    if (i == passedPosition[0] && j == passedPosition[1]) {
                        System.out.print("[P] ");
                    } else if (isAdjacent(i, j, passedPosition)) {
                        switch (passedMatrix[i][j]) {
                            case 9 ->
                                System.out.print("[*] ");
                            case 8 ->
                                System.out.print("[B] ");
                            case 0 ->
                                System.out.print("[ ] ");
                            case 4 ->
                                System.out.print("[!] ");
                            case 3 ->
                                System.out.print("[?] ");
                            case 2 ->
                                System.out.print("[?] ");
                            default ->
                                System.out.print("[~] ");
                        }
                    } else if (unlocked[i][j] > 0) {
                        switch (unlocked[i][j]) {
                            case 9 ->
                                System.out.print("[*] ");
                            case 2 ->
                                System.out.print("[I] ");
                            case 3 ->
                                System.out.print("[I] ");
                            case 4 ->
                                System.out.print("[!] ");
                            default ->
                                System.out.print("[.] ");
                        }
                    } else {
                        System.out.print("[ ] ");
                    }
                } else if (unlocked[i][j] == 1) {
                    System.out.print("[ ] ");
                } else {
                    System.out.print("    ");
                }
            }
            System.out.println();
        }
    }

    private static boolean isAdjacent(int x, int y, int[] playerPosition) {
        int px = playerPosition[0];
        int py = playerPosition[1];
        return (Math.abs(px - x) == 1 && py == y) || (Math.abs(py - y) == 1 && px == x);
    }

    public static int[][] trimUnreachableParts(int[][] matrix, int[] startPosition) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(startPosition);
        visited[startPosition[0]][startPosition[1]] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

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

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    matrix[i][j] = 0;
                }
            }
        }

        return matrix;
    }

    public static int[][] generateAndReturnMatrix(int size) {
        start(size);
        return matrix;
    }

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
                directions.add(0);
            }
        }
        return directions.stream().mapToInt(i -> i).toArray();
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
