
/**
 * ASCIIADVENTURER
 * Caden Finley
 *
 * @author Caden Finley
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameSaveSerialization {

    private static final String NULL_MARKER = "NULL";
    public static int versionID = 1;
    public static String filePath = ".game_save.txt";
    public static String runtimePath = ".runtime.txt";
    //change file path manually in main in functions that call this class

    /**
     * Saves the current game state to a file. This method writes various
     * game-related data to a specified file, including player information, game
     * engine states, dungeon states, and other relevant game data.
     *
     * The file is cleared before writing the new data.
     *
     * The following data is saved: - Player's name, health, max health, gold,
     * inventory, inventory size - Game engine's saved place, player creation
     * status, text engine speed setting - Dungeon states including current
     * dungeon, completed dungeons, player positions, dungeon matrices - Room
     * states including room saves, room numbers, hold commands - Dungeon
     * completion and visitation statuses - Dungeon room matrices and items -
     * Dungeon map reveal statuses - Game completion status, reset status after
     * win, previous room save - Prompt engine settings including AI generation
     * enabled status and prompt length - Game engine play time in seconds
     *
     * If the player's name is "Debug!", a stack trace is logged to the
     * terminal.
     *
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public static void saveGame() {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write(""); // This will clear the file
        } catch (IOException e) {
            System.out.println("Error clearing file: " + e.getMessage());
        }
        writeValue(Player.getName(), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Player.getHealth()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Player.getMaxHealth()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Player.getGold()), filePath);
        writeSeparator(filePath);
        writeValue(Player.getInventory(), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Player.getInventorySize()), filePath);
        writeSeparator(filePath);
        writeValue(GameEngine.savedPlace, filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(GameEngine.playerCreated), filePath);
        writeSeparator(filePath);
        writeValue(TextEngine.speedSetting, filePath);
        writeSeparator(filePath);
        writeValue(Dungeon.currentDungeon, filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.completedDungeons), filePath);
        writeSeparator(filePath);
        writeArray(Dungeon.currentPlayerPosition, filePath);
        writeSeparator(filePath);
        writeArray(Dungeon.lastPosition, filePath);
        writeSeparator(filePath);
        writeMatrix(Dungeon.meadowDungeon, filePath);
        writeSeparator(filePath);
        writeMatrix(Dungeon.darkForestDungeon, filePath);
        writeSeparator(filePath);
        writeMatrix(Dungeon.mountainCaveDungeon, filePath);
        writeSeparator(filePath);
        writeMatrix(Dungeon.mountainTopDungeon, filePath);
        writeSeparator(filePath);
        writeMatrix(Dungeon.desertOasisDungeon, filePath);
        writeSeparator(filePath);
        writeMatrix(Dungeon.desertPlainsDungeon, filePath);
        writeSeparator(filePath);
        writeMatrix(Dungeon.desertPyramidDungeon, filePath);
        writeSeparator(filePath);
        writeMatrix(Dungeon.oceanKingdomDungeon, filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(SpawnRoom.roomSave), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(OpenWorld.roomSave), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(OpenWorld.roomNumber), filePath);
        writeSeparator(filePath);
        writeValue(OpenWorld.holdCommand, filePath);
        writeSeparator(filePath);
        writeValue(Room.room, filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.MeadowDungeon.completed), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.MeadowDungeon.visited), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.DarkForestDungeon.completed), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.DarkForestDungeon.visited), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.MountainCaveDungeon.completed), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.MountainCaveDungeon.visited), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.MountainTopDungeon.completed), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.MountainTopDungeon.visited), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.DesertOasisDungeon.completed), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.DesertOasisDungeon.visited), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.DesertPlainsDungeon.completed), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.DesertPlainsDungeon.visited), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.DesertPyramidDungeon.completed), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.DesertPyramidDungeon.visited), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.OceanKingdomDungeon.completed), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.OceanKingdomDungeon.visited), filePath);
        writeSeparator(filePath);
        writeMatrix(Dungeon.MeadowDungeon.roomsBeenTo, filePath);
        writeSeparator(filePath);
        writeMatrix(Dungeon.DarkForestDungeon.roomsBeenTo, filePath);
        writeSeparator(filePath);
        writeMatrix(Dungeon.MountainCaveDungeon.roomsBeenTo, filePath);
        writeSeparator(filePath);
        writeMatrix(Dungeon.MountainTopDungeon.roomsBeenTo, filePath);
        writeSeparator(filePath);
        writeMatrix(Dungeon.DesertOasisDungeon.roomsBeenTo, filePath);
        writeSeparator(filePath);
        writeMatrix(Dungeon.DesertPlainsDungeon.roomsBeenTo, filePath);
        writeSeparator(filePath);
        writeMatrix(Dungeon.DesertPyramidDungeon.roomsBeenTo, filePath);
        writeSeparator(filePath);
        writeMatrix(Dungeon.OceanKingdomDungeon.roomsBeenTo, filePath);
        writeSeparator(filePath);
        writeList(Dungeon.MeadowDungeon.items, filePath);
        writeSeparator(filePath);
        writeList(Dungeon.DarkForestDungeon.items, filePath);
        writeSeparator(filePath);
        writeList(Dungeon.MountainCaveDungeon.items, filePath);
        writeSeparator(filePath);
        writeList(Dungeon.MountainTopDungeon.items, filePath);
        writeSeparator(filePath);
        writeList(Dungeon.DesertOasisDungeon.items, filePath);
        writeSeparator(filePath);
        writeList(Dungeon.DesertPlainsDungeon.items, filePath);
        writeSeparator(filePath);
        writeList(Dungeon.DesertPyramidDungeon.items, filePath);
        writeSeparator(filePath);
        writeList(Dungeon.OceanKingdomDungeon.items, filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.MeadowDungeon.mapRevealed), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.DarkForestDungeon.mapRevealed), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.MountainCaveDungeon.mapRevealed), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.MountainTopDungeon.mapRevealed), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.DesertOasisDungeon.mapRevealed), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.DesertPlainsDungeon.mapRevealed), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.DesertPyramidDungeon.mapRevealed), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.OceanKingdomDungeon.mapRevealed), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(GameEngine.gameComplete), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.resetedAfterWin), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(OpenWorld.previousRoomSave), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(PromptEngine.aiGenerationEnabled), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(PromptEngine.promptLength), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(GameEngine.playTime.getTimeElapsedInSeconds()), filePath);
        //do this after all other data is saved
        serializeAllLines(filePath, filePath);
        if (Player.getName().equals("Debug!")) {
            logStackTraceToTerminal(new IOException());
        }
    }

    /**
     * Loads the game save from the specified file paths and initializes the
     * game state.
     *
     * @throws InterruptedException if the thread is interrupted while loading
     * the game save.
     */
    public static void loadGameSave() throws InterruptedException {
        GameEngine.playTime.setSavedTimeInSeconds(GameEngine.playTime.getTimeElapsedInSeconds());
        deserializeToFile(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(runtimePath))) {
            try {
                String name = String.valueOf(reader.readLine());
                reader.readLine();
                int health = Integer.parseInt(reader.readLine());
                reader.readLine();
                int maxHealth = Integer.parseInt(reader.readLine());
                reader.readLine();
                int gold = Integer.parseInt(reader.readLine());
                reader.readLine();
                Map<String, Integer> inventory = readInventory(reader.readLine());
                reader.readLine();
                int inventorySize = Integer.parseInt(reader.readLine());
                reader.readLine();
                Player.playerSetSave(name, health, maxHealth, gold, inventory, inventorySize);
                GameEngine.savedPlace = reader.readLine();
                reader.readLine();
                GameEngine.playerCreated = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                TextEngine.speedSetting = reader.readLine();
                reader.readLine();
                Dungeon.currentDungeon = reader.readLine();
                reader.readLine();

                Dungeon.completedDungeons = Integer.parseInt(reader.readLine());
                reader.readLine();
                Dungeon.currentPlayerPosition = readArray(reader);
                reader.readLine();
                Dungeon.lastPosition = readArray(reader);
                reader.readLine();
                Dungeon.meadowDungeon = readMatrix(reader);
                reader.readLine();
                Dungeon.darkForestDungeon = readMatrix(reader);
                reader.readLine();
                Dungeon.mountainCaveDungeon = readMatrix(reader);
                reader.readLine();
                Dungeon.mountainTopDungeon = readMatrix(reader);
                reader.readLine();
                Dungeon.desertOasisDungeon = readMatrix(reader);
                reader.readLine();
                Dungeon.desertPlainsDungeon = readMatrix(reader);
                reader.readLine();
                Dungeon.desertPyramidDungeon = readMatrix(reader);
                reader.readLine();
                Dungeon.oceanKingdomDungeon = readMatrix(reader);
                reader.readLine();

                SpawnRoom.roomSave = Integer.parseInt(reader.readLine());
                reader.readLine();
                OpenWorld.roomSave = Integer.parseInt(reader.readLine());
                reader.readLine();
                OpenWorld.roomNumber = Integer.parseInt(reader.readLine());
                reader.readLine();
                OpenWorld.holdCommand = reader.readLine();
                reader.readLine();
                Room.room = reader.readLine();
                reader.readLine();

                Dungeon.MeadowDungeon.completed = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.MeadowDungeon.visited = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.DarkForestDungeon.completed = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.DarkForestDungeon.visited = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.MountainCaveDungeon.completed = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.MountainCaveDungeon.visited = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.MountainTopDungeon.completed = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.MountainTopDungeon.visited = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.DesertOasisDungeon.completed = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.DesertOasisDungeon.visited = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.DesertPlainsDungeon.completed = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.DesertPlainsDungeon.visited = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.DesertPyramidDungeon.completed = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.DesertPyramidDungeon.visited = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.OceanKingdomDungeon.completed = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.OceanKingdomDungeon.visited = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.MeadowDungeon.roomsBeenTo = readMatrix(reader);
                reader.readLine();
                Dungeon.DarkForestDungeon.roomsBeenTo = readMatrix(reader);
                reader.readLine();
                Dungeon.MountainCaveDungeon.roomsBeenTo = readMatrix(reader);
                reader.readLine();
                Dungeon.MountainTopDungeon.roomsBeenTo = readMatrix(reader);
                reader.readLine();
                Dungeon.DesertOasisDungeon.roomsBeenTo = readMatrix(reader);
                reader.readLine();
                Dungeon.DesertPlainsDungeon.roomsBeenTo = readMatrix(reader);
                reader.readLine();
                Dungeon.DesertPyramidDungeon.roomsBeenTo = readMatrix(reader);
                reader.readLine();
                Dungeon.OceanKingdomDungeon.roomsBeenTo = readMatrix(reader);
                reader.readLine();
                Dungeon.MeadowDungeon.items = readList(reader);
                reader.readLine();
                Dungeon.DarkForestDungeon.items = readList(reader);
                reader.readLine();
                Dungeon.MountainCaveDungeon.items = readList(reader);
                reader.readLine();
                Dungeon.MountainTopDungeon.items = readList(reader);
                reader.readLine();
                Dungeon.DesertOasisDungeon.items = readList(reader);
                reader.readLine();
                Dungeon.DesertPlainsDungeon.items = readList(reader);
                reader.readLine();
                Dungeon.DesertPyramidDungeon.items = readList(reader);
                reader.readLine();
                Dungeon.OceanKingdomDungeon.items = readList(reader);
                reader.readLine();
                Dungeon.MeadowDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.DarkForestDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.MountainCaveDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.MountainTopDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.DesertOasisDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.DesertPlainsDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.DesertPyramidDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.OceanKingdomDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                reader.readLine();

                GameEngine.gameComplete = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                Dungeon.resetedAfterWin = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                OpenWorld.previousRoomSave = Integer.parseInt(reader.readLine());
                reader.readLine();
                PromptEngine.aiGenerationEnabled = Boolean.parseBoolean(reader.readLine());
                reader.readLine();
                PromptEngine.promptLength = Integer.parseInt(reader.readLine());
                reader.readLine();
                GameEngine.playTime.setSavedTimeInSeconds(Long.parseLong(reader.readLine()));
            } catch (IOException | NumberFormatException e) {
                System.out.println("Save File Corrupt or Invalid... ");
                TextEngine.printWithDelays("Erasing Save File and Restarting...", false);
                TextEngine.enterToNext();
                GameEngine.wipeFile(".game_save.txt");
                GameEngine.wipeFile(".runtime.txt");
                TextEngine.clearScreen();
                GameEngine.gameStartGenDungeon();
                GameEngine.wipeSave();
                GameEngine.startMenu();
            }
        } catch (Exception e) {
            System.out.println("Save File Not Found... ");
            TextEngine.printWithDelays("Restarting...", false);
            TextEngine.enterToNext();
            TextEngine.clearScreen();
            GameEngine.startMenu();
        }
    }

    /**
     * Writes a separator (newline) to the specified file.
     *
     * @param filePath the path of the file to write to
     */
    private static void writeSeparator(String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write("\n");
        } catch (IOException e) {
            System.out.println("Error writing separator: " + e.getMessage());
        }
    }

    /**
     * Writes a string value to the specified file.
     *
     * @param value the value to write
     * @param filePath the path of the file to write to
     */
    private static void writeMatrix(int[][] matrix, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) { // true to append to the file
            for (int[] row : matrix) {
                for (int value : row) {
                    writer.write(value + " ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing matrix: " + e.getMessage());
        }
    }

    /**
     * Writes a given value to a specified file. If the value is null, it writes
     * "null" followed by a newline character. Otherwise, it writes the value
     * followed by a newline character. The value is appended to the file if it
     * already exists.
     *
     * @param value the value to be written to the file
     * @param filePath the path of the file to which the value will be written
     */
    private static void writeValue(String value, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) { // true to append to the file
            if (value == null) {
                writer.write("null\n");
            } else {
                writer.write(value + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing value: " + e.getMessage());
        }
    }

    /**
     * Writes the contents of an integer array to a specified file. If the array
     * is null, it writes "null" followed by a newline character. Otherwise, it
     * writes each integer in the array separated by a space, followed by a
     * newline character. The array is appended to the file if it already
     * exists.
     *
     * @param array the integer array to be written to the file
     * @param filePath the path of the file to which the array will be written
     */
    private static void writeArray(int[] array, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) { // true to append to the file
            if (array == null) {
                writer.write("null\n");
            } else {
                for (int value : array) {
                    writer.write(value + " ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing array: " + e.getMessage());
        }
    }

    /**
     * Writes a list of strings to a file at the specified file path. If the
     * list is null or empty, writes "null" followed by a newline. Otherwise,
     * writes each string in the list followed by a comma and a space, and ends
     * with a newline.
     *
     * @param list the list of strings to write to the file
     * @param filePath the path of the file to write to
     */
    private static void writeList(List<String> list, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) { // true to append to the file
            if (list == null || list.isEmpty()) {
                writer.write("null\n");
            } else {
                for (String value : list) {
                    writer.write(value + ", ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing list: " + e.getMessage());
        }
    }

    /**
     * Reads a line from the given BufferedReader and converts it into an
     * ArrayList of strings. If the line is equal to any of the null markers
     * ("null", "NULL", "Null", or an empty string), returns null. Otherwise,
     * splits the line by ", " and returns the resulting list.
     *
     * @param reader the BufferedReader to read from
     * @return an ArrayList of strings parsed from the line, or null if the line
     * is a null marker
     * @throws IOException if an I/O error occurs
     */
    private static ArrayList<String> readList(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line.equals(NULL_MARKER) || line.equals("null") || line.equals("") || line.equals("Null") || line.equals("NULL")) {
            return null;
        }
        return new ArrayList<>(List.of(line.split(", ")));
    }

    /**
     * Reads a line from the given BufferedReader and converts it into an array
     * of integers. If the line is equal to any of the null markers ("null",
     * "NULL", "Null", or an empty string), returns null. Otherwise, splits the
     * line by spaces and converts each value to an integer.
     *
     * @param reader the BufferedReader to read from
     * @return an array of integers parsed from the line, or null if the line is
     * a null marker
     * @throws IOException if an I/O error occurs
     */
    private static int[] readArray(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line.equals(NULL_MARKER) || line.equals("null") || line.equals("") || line.equals("Null") || line.equals("NULL")) {
            return null;
        }
        String[] values = line.trim().split(" ");
        int[] array = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = Integer.parseInt(values[i]);
        }
        return array;
    }

    /**
     * Reads a matrix from a BufferedReader. The matrix is expected to be in a
     * space-separated format. The first line of the input determines the size
     * of the matrix.
     *
     * @param reader the BufferedReader to read the matrix from
     * @return a 2D integer array representing the matrix
     * @throws IOException if an I/O error occurs
     */
    private static int[][] readMatrix(BufferedReader reader) throws IOException {
        // Read the first line to determine the size of the matrix
        String[] firstLine = reader.readLine().trim().split(" ");
        int size = firstLine.length;

        // Initialize the matrix with the determined size
        int[][] matrix = new int[size][size];

        // Fill the first row of the matrix
        for (int j = 0; j < size; j++) {
            matrix[0][j] = Integer.parseInt(firstLine[j]);
        }

        // Continue reading and filling the rest of the matrix
        for (int i = 1; i < size; i++) {
            String[] line = reader.readLine().trim().split(" ");
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }

        return matrix;
    }

    /**
     * Parses an inventory string and converts it into a map of item names to
     * their quantities. The inventory string is expected to be in the format
     * "{item1=quantity1, item2=quantity2, ...}".
     *
     * @param inventoryString the string representation of the inventory
     * @return a map where the keys are item names and the values are their
     * quantities
     * @throws NumberFormatException if the quantity is not a valid integer
     */
    private static Map<String, Integer> readInventory(String inventoryString) {
        Map<String, Integer> inventory = new HashMap<>();

        // Remove the curly braces
        inventoryString = inventoryString.substring(1, inventoryString.length() - 1);

        // Split the string by commas
        String[] items = inventoryString.split(", ");

        // Iterate over each item entry
        for (String item : items) {
            // Split the entry by the equals sign
            String[] parts = item.split("=");
            if (parts.length == 2) {
                String itemName = parts[0].trim();
                int itemQuantity = Integer.parseInt(parts[1].trim());
                inventory.put(itemName, itemQuantity);
            }
        }

        return inventory;
    }

    /**
     * Reads all lines from a specified input file and serializes them into a
     * single output file. The lines are stored in a list and then written as an
     * object to the output file.
     *
     * @param inputFilePath the path to the input file to read lines from
     * @param outputFilePath the path to the output file to serialize the lines
     * to
     */
    public static void serializeAllLines(String inputFilePath, String outputFilePath) {
        File inputFile = new File(inputFilePath);
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            // Serialize the list of lines to a single file
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFilePath))) {
                oos.writeObject(lines);
                //System.out.println("File serialized successfully to: " + outputFilePath);
            }

        } catch (IOException e) {
            System.out.println("Error serializing file: " + e.getMessage());
        }
    }

    /**
     * Deserializes a list of strings from a serialized file and writes them to
     * an output file.
     *
     * @param serializedFilePath the path to the serialized file containing the
     * list of strings
     * @throws IOException if an I/O error occurs while reading from the
     * serialized file or writing to the output file
     * @throws ClassNotFoundException if the class of a serialized object cannot
     * be found
     */
    @SuppressWarnings("unchecked")
    public static void deserializeToFile(String serializedFilePath) {
        File outputFilePath = new File(runtimePath);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serializedFilePath)); BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {

            // Deserialize the list of lines 
            List<String> lines = (List<String>) ois.readObject();

            // Write each line to the output file
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }

            //System.out.println("File deserialized and written to: " + outputFilePath);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing file: " + e.getMessage());
        }
    }

    /**
     * Logs the stack trace of a throwable to a log file and opens the log file
     * in the Terminal.
     *
     * @param throwable the throwable whose stack trace is to be logged
     */
    private static void logStackTraceToTerminal(Throwable throwable) {
        String log = ".stack_trace_log.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(log, true))) {
            throwable.printStackTrace(writer);
        } catch (IOException e) {
            System.err.println("Error writing stack trace to log file: " + e.getMessage());
        }
        try {
            String[] cmd = {"/bin/bash", "-c", "open -a Terminal " + log};
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            System.err.println("Error opening Terminal: " + e.getMessage());
        }
    }
}
