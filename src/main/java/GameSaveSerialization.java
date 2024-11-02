//2 4 5 7 9 10 

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
        writeMatrix(Main.MeadowDungeon.getMap(), filePath);
        writeSeparator(filePath);
        writeMatrix(Main.DarkForestDungeon.getMap(), filePath);
        writeSeparator(filePath);
        writeMatrix(Main.MountainCaveDungeon.getMap(), filePath);
        writeSeparator(filePath);
        writeMatrix(Main.MountainTopDungeon.getMap(), filePath);
        writeSeparator(filePath);
        writeMatrix(Main.DesertOasisDungeon.getMap(), filePath);
        writeSeparator(filePath);
        writeMatrix(Main.DesertPlainsDungeon.getMap(), filePath);
        writeSeparator(filePath);
        writeMatrix(Main.DesertPyramidDungeon.getMap(), filePath);
        writeSeparator(filePath);
        writeMatrix(Main.OceanKingdomDungeon.getMap(), filePath);
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
        writeValue(String.valueOf(Main.MeadowDungeon.getCompleted()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.MeadowDungeon.getVisited()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.DarkForestDungeon.getCompleted()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.DarkForestDungeon.getVisited()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.MountainCaveDungeon.getCompleted()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.MountainCaveDungeon.getVisited()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.MountainTopDungeon.getCompleted()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.MountainTopDungeon.getVisited()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.DesertOasisDungeon.getCompleted()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.DesertOasisDungeon.getVisited()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.DesertPlainsDungeon.getCompleted()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.DesertPlainsDungeon.getVisited()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.DesertPyramidDungeon.getCompleted()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.DesertPyramidDungeon.getVisited()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.OceanKingdomDungeon.getCompleted()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.OceanKingdomDungeon.getVisited()), filePath);
        writeSeparator(filePath);
        writeMatrix(Main.MeadowDungeon.getRoomsBeenTo(), filePath);
        writeSeparator(filePath);
        writeMatrix(Main.DarkForestDungeon.getRoomsBeenTo(), filePath);
        writeSeparator(filePath);
        writeMatrix(Main.MountainCaveDungeon.getRoomsBeenTo(), filePath);
        writeSeparator(filePath);
        writeMatrix(Main.MountainTopDungeon.getRoomsBeenTo(), filePath);
        writeSeparator(filePath);
        writeMatrix(Main.DesertOasisDungeon.getRoomsBeenTo(), filePath);
        writeSeparator(filePath);
        writeMatrix(Main.DesertPlainsDungeon.getRoomsBeenTo(), filePath);
        writeSeparator(filePath);
        writeMatrix(Main.DesertPyramidDungeon.getRoomsBeenTo(), filePath);
        writeSeparator(filePath);
        writeMatrix(Main.OceanKingdomDungeon.getRoomsBeenTo(), filePath);
        writeSeparator(filePath);
        writeList(Main.MeadowDungeon.getItems(), filePath);
        writeSeparator(filePath);
        writeList(Main.DarkForestDungeon.getItems(), filePath);
        writeSeparator(filePath);
        writeList(Main.MountainCaveDungeon.getItems(), filePath);
        writeSeparator(filePath);
        writeList(Main.MountainTopDungeon.getItems(), filePath);
        writeSeparator(filePath);
        writeList(Main.DesertOasisDungeon.getItems(), filePath);
        writeSeparator(filePath);
        writeList(Main.DesertPlainsDungeon.getItems(), filePath);
        writeSeparator(filePath);
        writeList(Main.DesertPyramidDungeon.getItems(), filePath);
        writeSeparator(filePath);
        writeList(Main.OceanKingdomDungeon.getItems(), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.MeadowDungeon.getMapRevealed()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.DarkForestDungeon.getMapRevealed()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.MountainCaveDungeon.getMapRevealed()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.MountainTopDungeon.getMapRevealed()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.DesertOasisDungeon.getMapRevealed()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.DesertPlainsDungeon.getMapRevealed()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.DesertPyramidDungeon.getMapRevealed()), filePath);
        writeSeparator(filePath);
        writeValue(String.valueOf(Main.OceanKingdomDungeon.getMapRevealed()), filePath);
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
                Main.MeadowDungeon.setMap(readMatrix(reader));
                buffer = reader.readLine();
                Main.DarkForestDungeon.setMap(readMatrix(reader));
                buffer = reader.readLine();
                Main.MountainCaveDungeon.setMap() = readMatrix(reader);
                buffer = reader.readLine();
                Main.MountainTopDungeon.setMap() = readMatrix(reader);
                buffer = reader.readLine();
                Main.DesertOasisDungeon.setMap() = readMatrix(reader);
                buffer = reader.readLine();
                Main.DesertPlainsDungeon.setMap() = readMatrix(reader);
                buffer = reader.readLine();
                Main.DesertPyramidDungeon.setMap() = readMatrix(reader);
                buffer = reader.readLine();
                Main.OceanKingdomDungeon.setMap() = readMatrix(reader);
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
                Main.MeadowDungeon.completed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.MeadowDungeon.visited = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.DarkForestDungeon.completed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.DarkForestDungeon.visited = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.MountainCaveDungeon.completed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.MountainCaveDungeon.visited = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.MountainTopDungeon.completed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.MountainTopDungeon.visited = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.DesertOasisDungeon.completed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.DesertOasisDungeon.visited = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.DesertPlainsDungeon.completed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.DesertPlainsDungeon.visited = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.DesertPyramidDungeon.completed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.DesertPyramidDungeon.visited = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.OceanKingdomDungeon.completed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.OceanKingdomDungeon.visited = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.MeadowDungeon.roomsBeenTo = readMatrix(reader);
                buffer = reader.readLine();
                Main.DarkForestDungeon.roomsBeenTo = readMatrix(reader);
                buffer = reader.readLine();
                Main.MountainCaveDungeon.roomsBeenTo = readMatrix(reader);
                buffer = reader.readLine();
                Main.MountainTopDungeon.roomsBeenTo = readMatrix(reader);
                buffer = reader.readLine();
                Main.DesertOasisDungeon.roomsBeenTo = readMatrix(reader);
                buffer = reader.readLine();
                Main.DesertPlainsDungeon.roomsBeenTo = readMatrix(reader);
                buffer = reader.readLine();
                Main.DesertPyramidDungeon.roomsBeenTo = readMatrix(reader);
                buffer = reader.readLine();
                Main.OceanKingdomDungeon.roomsBeenTo = readMatrix(reader);
                buffer = reader.readLine();
                Main.MeadowDungeon.items = readList(reader);
                buffer = reader.readLine();
                Main.DarkForestDungeon.items = readList(reader);
                buffer = reader.readLine();
                Main.MountainCaveDungeon.items = readList(reader);
                buffer = reader.readLine();
                Main.MountainTopDungeon.items = readList(reader);
                buffer = reader.readLine();
                Main.DesertOasisDungeon.items = readList(reader);
                buffer = reader.readLine();
                Main.DesertPlainsDungeon.items = readList(reader);
                buffer = reader.readLine();
                Main.DesertPyramidDungeon.items = readList(reader);
                buffer = reader.readLine();
                Main.OceanKingdomDungeon.items = readList(reader);
                buffer = reader.readLine();
                Main.MeadowDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.DarkForestDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.MountainCaveDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.MountainTopDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.DesertOasisDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.DesertPlainsDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.DesertPyramidDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
                buffer = reader.readLine();
                Main.OceanKingdomDungeon.mapRevealed = Boolean.parseBoolean(reader.readLine());
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
