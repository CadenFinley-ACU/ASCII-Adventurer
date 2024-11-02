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
        writeValue(Player.getName(), filePath);
        writeValue(String.valueOf(Player.getHealth()), filePath);
        writeValue(String.valueOf(Player.getMaxHealth()), filePath);
        writeValue(String.valueOf(Player.getGold()), filePath);
        writeValue(Player.getInventory(), filePath);
        writeValue(String.valueOf(Player.getInventorySize()), filePath);
        writeValue(Main.savedPlace, filePath);
        writeValue(String.valueOf(Main.playerCreated), filePath);
        writeValue(TextEngine.speedSetting, filePath);
        writeValue(Dungeon.currentDungeon, filePath);
        writeValue(String.valueOf(Dungeon.completedDungeons), filePath);
        writeArray(Main.MeadowDungeon.getCurrentPosition(), filePath);
        writeArray(Main.MeadowDungeon.getLastPosition(), filePath);
        writeArray(Main.DarkForestDungeon.getCurrentPosition(), filePath);
        writeArray(Main.DarkForestDungeon.getLastPosition(), filePath);
        writeArray(Main.MountainCaveDungeon.getCurrentPosition(), filePath);
        writeArray(Main.MountainCaveDungeon.getLastPosition(), filePath);
        writeArray(Main.MountainTopDungeon.getCurrentPosition(), filePath);
        writeArray(Main.MountainTopDungeon.getLastPosition(), filePath);
        writeArray(Main.DesertOasisDungeon.getCurrentPosition(), filePath);
        writeArray(Main.DesertOasisDungeon.getLastPosition(), filePath);
        writeArray(Main.DesertPlainsDungeon.getCurrentPosition(), filePath);
        writeArray(Main.DesertPlainsDungeon.getLastPosition(), filePath);
        writeArray(Main.DesertPyramidDungeon.getCurrentPosition(), filePath);
        writeArray(Main.DesertPyramidDungeon.getLastPosition(), filePath);
        writeArray(Main.OceanKingdomDungeon.getCurrentPosition(), filePath);
        writeMatrix(Main.MeadowDungeon.getMap(), filePath);
        writeMatrix(Main.DarkForestDungeon.getMap(), filePath);
        writeMatrix(Main.MountainCaveDungeon.getMap(), filePath);
        writeMatrix(Main.MountainTopDungeon.getMap(), filePath);
        writeMatrix(Main.DesertOasisDungeon.getMap(), filePath);
        writeMatrix(Main.DesertPlainsDungeon.getMap(), filePath);
        writeMatrix(Main.DesertPyramidDungeon.getMap(), filePath);
        writeMatrix(Main.OceanKingdomDungeon.getMap(), filePath);
        writeValue(String.valueOf(SpawnRoom.roomSave), filePath);
        writeValue(String.valueOf(OpenWorld.roomSave), filePath);
        writeValue(String.valueOf(OpenWorld.roomNumber), filePath);
        writeValue(OpenWorld.holdCommand, filePath);
        writeValue(Room.room, filePath);
        writeValue(String.valueOf(Main.MeadowDungeon.getCompleted()), filePath);
        writeValue(String.valueOf(Main.MeadowDungeon.getVisited()), filePath);
        writeValue(String.valueOf(Main.DarkForestDungeon.getCompleted()), filePath);
        writeValue(String.valueOf(Main.DarkForestDungeon.getVisited()), filePath);
        writeValue(String.valueOf(Main.MountainCaveDungeon.getCompleted()), filePath);
        writeValue(String.valueOf(Main.MountainCaveDungeon.getVisited()), filePath);
        writeValue(String.valueOf(Main.MountainTopDungeon.getCompleted()), filePath);
        writeValue(String.valueOf(Main.MountainTopDungeon.getVisited()), filePath);
        writeValue(String.valueOf(Main.DesertOasisDungeon.getCompleted()), filePath);
        writeValue(String.valueOf(Main.DesertOasisDungeon.getVisited()), filePath);
        writeValue(String.valueOf(Main.DesertPlainsDungeon.getCompleted()), filePath);
        writeValue(String.valueOf(Main.DesertPlainsDungeon.getVisited()), filePath);
        writeValue(String.valueOf(Main.DesertPyramidDungeon.getCompleted()), filePath);
        writeValue(String.valueOf(Main.DesertPyramidDungeon.getVisited()), filePath);
        writeValue(String.valueOf(Main.OceanKingdomDungeon.getCompleted()), filePath);
        writeValue(String.valueOf(Main.OceanKingdomDungeon.getVisited()), filePath);
        writeMatrix(Main.MeadowDungeon.getRoomsBeenTo(), filePath);
        writeMatrix(Main.DarkForestDungeon.getRoomsBeenTo(), filePath);
        writeMatrix(Main.MountainCaveDungeon.getRoomsBeenTo(), filePath);
        writeMatrix(Main.MountainTopDungeon.getRoomsBeenTo(), filePath);
        writeMatrix(Main.DesertOasisDungeon.getRoomsBeenTo(), filePath);
        writeMatrix(Main.DesertPlainsDungeon.getRoomsBeenTo(), filePath);
        writeMatrix(Main.DesertPyramidDungeon.getRoomsBeenTo(), filePath);
        writeMatrix(Main.OceanKingdomDungeon.getRoomsBeenTo(), filePath);
        writeList(Main.MeadowDungeon.getItems(), filePath);
        writeList(Main.DarkForestDungeon.getItems(), filePath);
        writeList(Main.MountainCaveDungeon.getItems(), filePath);
        writeList(Main.MountainTopDungeon.getItems(), filePath);
        writeList(Main.DesertOasisDungeon.getItems(), filePath);
        writeList(Main.DesertPlainsDungeon.getItems(), filePath);
        writeList(Main.DesertPyramidDungeon.getItems(), filePath);
        writeList(Main.OceanKingdomDungeon.getItems(), filePath);
        writeValue(String.valueOf(Main.MeadowDungeon.getMapRevealed()), filePath);
        writeValue(String.valueOf(Main.DarkForestDungeon.getMapRevealed()), filePath);
        writeValue(String.valueOf(Main.MountainCaveDungeon.getMapRevealed()), filePath);
        writeValue(String.valueOf(Main.MountainTopDungeon.getMapRevealed()), filePath);
        writeValue(String.valueOf(Main.DesertOasisDungeon.getMapRevealed()), filePath);
        writeValue(String.valueOf(Main.DesertPlainsDungeon.getMapRevealed()), filePath);
        writeValue(String.valueOf(Main.DesertPyramidDungeon.getMapRevealed()), filePath);
        writeValue(String.valueOf(Main.OceanKingdomDungeon.getMapRevealed()), filePath);
        writeValue(String.valueOf(Main.gameComplete), filePath);
        writeValue(String.valueOf(Dungeon.resetedAfterWin), filePath);
        writeValue(String.valueOf(OpenWorld.previousRoomSave), filePath);
        writeValue(PromptEngine.userAPIKey, filePath);
        writeValue(String.valueOf(PromptEngine.aiGenerationEnabled), filePath);
        writeValue(String.valueOf(PromptEngine.promptLength), filePath);
        writeValue(String.valueOf(Main.playTime.getElapsedTime() + Main.playTime.getSavedTime()), filePath);
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
                String name = String.valueOf(reader.readLine());
                int health = Integer.parseInt(reader.readLine());
                int maxHealth = Integer.parseInt(reader.readLine());
                int gold = Integer.parseInt(reader.readLine());
                Map<String, Integer> inventory = readInventory(reader.readLine());
                int inventorySize = Integer.parseInt(reader.readLine());
                Player.playerSetSave(name, health, maxHealth, gold, inventory, inventorySize);
                Main.savedPlace = reader.readLine();
                Main.playerCreated = Boolean.parseBoolean(reader.readLine());
                TextEngine.speedSetting = reader.readLine();
                Dungeon.currentDungeon = reader.readLine();
                Dungeon.completedDungeons = Integer.parseInt(reader.readLine());
                Main.MeadowDungeon.setCurrentPosition(readArray(reader));
                Main.MeadowDungeon.setLastPosition(readArray(reader));
                Main.DarkForestDungeon.setCurrentPosition(readArray(reader));
                Main.DarkForestDungeon.setLastPosition(readArray(reader));
                Main.MountainCaveDungeon.setCurrentPosition(readArray(reader));
                Main.MountainCaveDungeon.setLastPosition(readArray(reader));
                Main.MountainTopDungeon.setCurrentPosition(readArray(reader));
                Main.MountainTopDungeon.setLastPosition(readArray(reader));
                Main.DesertOasisDungeon.setCurrentPosition(readArray(reader));
                Main.DesertOasisDungeon.setLastPosition(readArray(reader));
                Main.DesertPlainsDungeon.setCurrentPosition(readArray(reader));
                Main.DesertPlainsDungeon.setLastPosition(readArray(reader));
                Main.DesertPyramidDungeon.setCurrentPosition(readArray(reader));
                Main.DesertPyramidDungeon.setLastPosition(readArray(reader));
                Main.OceanKingdomDungeon.setCurrentPosition(readArray(reader));
                Main.MeadowDungeon.setMap(readMatrix(reader));
                Main.DarkForestDungeon.setMap(readMatrix(reader));
                Main.MountainCaveDungeon.setMap(readMatrix(reader));
                Main.MountainTopDungeon.setMap(readMatrix(reader));
                Main.DesertOasisDungeon.setMap(readMatrix(reader));
                Main.DesertPlainsDungeon.setMap(readMatrix(reader));
                Main.DesertPyramidDungeon.setMap(readMatrix(reader));
                Main.OceanKingdomDungeon.setMap(readMatrix(reader));
                SpawnRoom.roomSave = Integer.parseInt(reader.readLine());
                OpenWorld.roomSave = Integer.parseInt(reader.readLine());
                OpenWorld.roomNumber = Integer.parseInt(reader.readLine());
                OpenWorld.holdCommand = reader.readLine();
                Room.room = reader.readLine();
                Main.MeadowDungeon.setCompleted(Boolean.parseBoolean(reader.readLine()));
                Main.MeadowDungeon.setVisited(Boolean.parseBoolean(reader.readLine()));
                Main.DarkForestDungeon.setCompleted(Boolean.parseBoolean(reader.readLine()));
                Main.DarkForestDungeon.setVisited(Boolean.parseBoolean(reader.readLine()));
                Main.MountainCaveDungeon.setCompleted(Boolean.parseBoolean(reader.readLine()));
                Main.MountainCaveDungeon.setVisited(Boolean.parseBoolean(reader.readLine()));
                Main.MountainTopDungeon.setCompleted(Boolean.parseBoolean(reader.readLine()));
                Main.MountainTopDungeon.setVisited(Boolean.parseBoolean(reader.readLine()));
                Main.DesertOasisDungeon.setCompleted(Boolean.parseBoolean(reader.readLine()));
                Main.DesertOasisDungeon.setVisited(Boolean.parseBoolean(reader.readLine()));
                Main.DesertPlainsDungeon.setCompleted(Boolean.parseBoolean(reader.readLine()));
                Main.DesertPlainsDungeon.setVisited(Boolean.parseBoolean(reader.readLine()));
                Main.DesertPyramidDungeon.setCompleted(Boolean.parseBoolean(reader.readLine()));
                Main.DesertPyramidDungeon.setVisited(Boolean.parseBoolean(reader.readLine()));
                Main.OceanKingdomDungeon.setCompleted(Boolean.parseBoolean(reader.readLine()));
                Main.OceanKingdomDungeon.setVisited(Boolean.parseBoolean(reader.readLine()));
                Main.MeadowDungeon.setRoomsBeenTo(readMatrix(reader));
                Main.DarkForestDungeon.setRoomsBeenTo(readMatrix(reader));
                Main.MountainCaveDungeon.setRoomsBeenTo(readMatrix(reader));
                Main.MountainTopDungeon.setRoomsBeenTo(readMatrix(reader));
                Main.DesertOasisDungeon.setRoomsBeenTo(readMatrix(reader));
                Main.DesertPlainsDungeon.setRoomsBeenTo(readMatrix(reader));
                Main.DesertPyramidDungeon.setRoomsBeenTo(readMatrix(reader));
                Main.OceanKingdomDungeon.setRoomsBeenTo(readMatrix(reader));
                Main.MeadowDungeon.setItems(readList(reader));
                Main.DarkForestDungeon.setItems(readList(reader));
                Main.MountainCaveDungeon.setItems(readList(reader));
                Main.MountainTopDungeon.setItems(readList(reader));
                Main.DesertOasisDungeon.setItems(readList(reader));
                Main.DesertPlainsDungeon.setItems(readList(reader));
                Main.DesertPyramidDungeon.setItems(readList(reader));
                Main.OceanKingdomDungeon.setItems(readList(reader));
                Main.MeadowDungeon.setMapRevealed(Boolean.parseBoolean(reader.readLine()));
                Main.DarkForestDungeon.setMapRevealed(Boolean.parseBoolean(reader.readLine()));
                Main.MountainCaveDungeon.setMapRevealed(Boolean.parseBoolean(reader.readLine()));
                Main.MountainTopDungeon.setMapRevealed(Boolean.parseBoolean(reader.readLine()));
                Main.DesertOasisDungeon.setMapRevealed(Boolean.parseBoolean(reader.readLine()));
                Main.DesertPlainsDungeon.setMapRevealed(Boolean.parseBoolean(reader.readLine()));
                Main.DesertPyramidDungeon.setMapRevealed(Boolean.parseBoolean(reader.readLine()));
                Main.OceanKingdomDungeon.setMapRevealed(Boolean.parseBoolean(reader.readLine()));
                Main.gameComplete = Boolean.parseBoolean(reader.readLine());
                Dungeon.resetedAfterWin = Boolean.parseBoolean(reader.readLine());
                OpenWorld.previousRoomSave = Integer.parseInt(reader.readLine());
                PromptEngine.userAPIKey = reader.readLine();
                PromptEngine.aiGenerationEnabled = Boolean.parseBoolean(reader.readLine());
                PromptEngine.promptLength = Integer.parseInt(reader.readLine());
                Main.playTime.setSavedTime(Long.parseLong(reader.readLine()));

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
