
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// Written by Caden Finley ACU 2024
// October 15, 2024

public class GameSaveSerialization {

    private static final String NULL_MARKER = "NULL";
    public static int versionID = 1;
    private static String command;
    private final static Console console = System.console();
    public static String filePath = ".game_save.txt";
    public static String runtimePath = ".runtime.txt";
    //change file path manually in main in functions that call this class

    public static void saveGame() {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write(""); // This will clear the file
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeValue(String.valueOf(versionID), filePath);
        writeSeparator(filePath);
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
        writeValue(Main.savedPlace, filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.playerCreated), filePath);
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
        writeValue(String.valueOf(Main.gameComplete), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Dungeon.resetedAfterWin), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(OpenWorld.previousRoomSave), filePath);
        writeSeparator(filePath);
        writeValue(PromptEngine.userAPIKey, filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(PromptEngine.aiGenerationEnabled), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(PromptEngine.promptLength), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.playTime.getElapsedTime() + Main.playTime.getSavedTime()), filePath);
        writeSeparator(filePath);
        //do this after all other data is saved
        serializeAllLines(filePath, filePath);
    }

    public static void loadGameSave() throws InterruptedException {
        Main.playTime.setSavedTime(Main.playTime.getSavedTime() + Main.playTime.getElapsedTime());
        String buffer = "";
        deserializeToFile(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(runtimePath))) {
            try {
                int version = Integer.parseInt(reader.readLine());
                //read decrypted data
                buffer = reader.readLine();
                String name = String.valueOf(reader.readLine());
                buffer = reader.readLine();
                int health = Integer.parseInt(reader.readLine());
                buffer = reader.readLine();
                int maxHealth = Integer.parseInt(reader.readLine());
                buffer = reader.readLine();
                int gold = Integer.parseInt(reader.readLine());
                buffer = reader.readLine();

                Map<String, Integer> inventory = readInventory(reader.readLine());
                buffer = reader.readLine();

                int inventorySize = Integer.parseInt(reader.readLine());
                buffer = reader.readLine();
                Player.playerSetSave(name, health, maxHealth, gold, inventory, inventorySize);
                Main.savedPlace = reader.readLine();
                buffer = reader.readLine();
                Main.playerCreated = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                TextEngine.speedSetting = reader.readLine();
                buffer = reader.readLine();
                Dungeon.currentDungeon = reader.readLine();
                buffer = reader.readLine();
                Dungeon.completedDungeons = Integer.parseInt(reader.readLine());
                buffer = reader.readLine();
                Dungeon.currentPlayerPosition = readArray(reader);
                buffer = reader.readLine();
                Dungeon.lastPosition = readArray(reader);
                buffer = reader.readLine();
                Dungeon.meadowDungeon = readMatrix(reader);
                buffer = reader.readLine();
                Dungeon.darkForestDungeon = readMatrix(reader);
                buffer = reader.readLine();
                Dungeon.mountainCaveDungeon = readMatrix(reader);
                buffer = reader.readLine();
                Dungeon.mountainTopDungeon = readMatrix(reader);
                buffer = reader.readLine();
                Dungeon.desertOasisDungeon = readMatrix(reader);
                buffer = reader.readLine();
                Dungeon.desertPlainsDungeon = readMatrix(reader);
                buffer = reader.readLine();
                Dungeon.desertPyramidDungeon = readMatrix(reader);
                buffer = reader.readLine();
                Dungeon.oceanKingdomDungeon = readMatrix(reader);
                buffer = reader.readLine();
                SpawnRoom.roomSave = Integer.parseInt(reader.readLine());
                buffer = reader.readLine();
                OpenWorld.roomSave = Integer.parseInt(reader.readLine());
                buffer = reader.readLine();
                OpenWorld.roomNumber = Integer.parseInt(reader.readLine());
                buffer = reader.readLine();
                OpenWorld.holdCommand = reader.readLine();
                buffer = reader.readLine();
                Room.room = reader.readLine();
                buffer = reader.readLine();
                Dungeon.MeadowDungeon.completed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.MeadowDungeon.visited = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.DarkForestDungeon.completed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.DarkForestDungeon.visited = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.MountainCaveDungeon.completed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.MountainCaveDungeon.visited = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.MountainTopDungeon.completed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.MountainTopDungeon.visited = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.DesertOasisDungeon.completed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.DesertOasisDungeon.visited = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.DesertPlainsDungeon.completed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.DesertPlainsDungeon.visited = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.DesertPyramidDungeon.completed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.DesertPyramidDungeon.visited = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.OceanKingdomDungeon.completed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.OceanKingdomDungeon.visited = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.MeadowDungeon.roomsBeenTo = readMatrix(reader);
                buffer = reader.readLine();
                Dungeon.DarkForestDungeon.roomsBeenTo = readMatrix(reader);
                buffer = reader.readLine();
                Dungeon.MountainCaveDungeon.roomsBeenTo = readMatrix(reader);
                buffer = reader.readLine();
                Dungeon.MountainTopDungeon.roomsBeenTo = readMatrix(reader);
                buffer = reader.readLine();
                Dungeon.DesertOasisDungeon.roomsBeenTo = readMatrix(reader);
                buffer = reader.readLine();
                Dungeon.DesertPlainsDungeon.roomsBeenTo = readMatrix(reader);
                buffer = reader.readLine();
                Dungeon.DesertPyramidDungeon.roomsBeenTo = readMatrix(reader);
                buffer = reader.readLine();
                Dungeon.OceanKingdomDungeon.roomsBeenTo = readMatrix(reader);
                buffer = reader.readLine();
                Dungeon.MeadowDungeon.items = readList(reader);
                buffer = reader.readLine();
                Dungeon.DarkForestDungeon.items = readList(reader);
                buffer = reader.readLine();
                Dungeon.MountainCaveDungeon.items = readList(reader);
                buffer = reader.readLine();
                Dungeon.MountainTopDungeon.items = readList(reader);
                buffer = reader.readLine();
                Dungeon.DesertOasisDungeon.items = readList(reader);
                buffer = reader.readLine();
                Dungeon.DesertPlainsDungeon.items = readList(reader);
                buffer = reader.readLine();
                Dungeon.DesertPyramidDungeon.items = readList(reader);
                buffer = reader.readLine();
                Dungeon.OceanKingdomDungeon.items = readList(reader);
                buffer = reader.readLine();
                Dungeon.MeadowDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.DarkForestDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.MountainCaveDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.MountainTopDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.DesertOasisDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.DesertPlainsDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.DesertPyramidDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.OceanKingdomDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.gameComplete = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Dungeon.resetedAfterWin = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                OpenWorld.previousRoomSave = Integer.parseInt(reader.readLine());
                buffer = reader.readLine();
                PromptEngine.userAPIKey = reader.readLine();
                buffer = reader.readLine();
                PromptEngine.aiGenerationEnabled = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                PromptEngine.promptLength = Integer.parseInt(reader.readLine());
                buffer = reader.readLine();
                Main.playTime.setSavedTime(Long.parseLong(reader.readLine()));
                buffer = reader.readLine();

            } catch (IOException | NumberFormatException e) {
                System.out.println("Save File Corrupt or Invalid... ");
                TextEngine.printWithDelays("Erasing Save File and Restarting...", false);
                TextEngine.enterToNext();
                TextEngine.clearScreen();
                Main.wipeSave();
                Main.startMenu();
            }
        } catch (Exception e) {
            System.out.println("Save File Not Found... ");
            TextEngine.printWithDelays("Restarting...", false);
            TextEngine.enterToNext();
            TextEngine.clearScreen();
            Main.startMenu();
        }
    }

    private static void writeSeparator(String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) { // true to append to the file
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeMatrix(int[][] matrix, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) { // true to append to the file
            for (int[] row : matrix) {
                for (int value : row) {
                    writer.write(value + " ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeValue(String value, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) { // true to append to the file
            if (value == null) {
                writer.write("null\n");
            } else {
                writer.write(value + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }
    }

    private static ArrayList<String> readList(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line.equals(NULL_MARKER) || line.equals("null") || line.equals("") || line.equals("Null") || line.equals("NULL")) {
            return null;
        }
        return new ArrayList<>(List.of(line.split(", ")));
    }

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
            e.printStackTrace();
        }
    }

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
}
