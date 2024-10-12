
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameSaveSerialization {

    private static final String NULL_MARKER = "NULL";

    public static String saveGame() {
        return (writeValue(Player.getName()) + writeValue(String.valueOf(Player.getHealth())) + writeValue(String.valueOf(Player.getMaxHealth()))
                + writeValue(String.valueOf(Player.getGold())) + writeValue(Player.getInventory()) + writeValue(String.valueOf(Player.getInventorySize()))
                + writeValue(Main.savedPlace) + writeValue(String.valueOf(Main.playerCreated)) + writeValue(TextEngine.speedSetting) + writeValue(Dungeon.currentDungeon))
                + writeValue(String.valueOf(Dungeon.completedDungeons) + writeValue(String.valueOf(Dungeon.currentPlayerPosition)) + writeValue(String.valueOf(Dungeon.lastPosition))
                        + writeValue(String.valueOf(Dungeon.meadowDungeon)) + writeValue(String.valueOf(Dungeon.darkForestDungeon)) + writeValue(String.valueOf(Dungeon.mountainCaveDungeon))
                        + writeValue(String.valueOf(Dungeon.mountainTopDungeon)) + writeValue(String.valueOf(Dungeon.mountainCaveDungeon)) + writeValue(String.valueOf(Dungeon.desertOasisDungeon))
                        + writeValue(String.valueOf(Dungeon.desertPlainsDungeon)) + writeValue(String.valueOf(Dungeon.desertPyramidDungeon)) + writeValue(String.valueOf(Dungeon.oceanKingdomDungeon))
                        + writeValue(String.valueOf(Dungeon.missedItems)) + writeValue(String.valueOf(SpawnRoom.roomSave)) + writeValue(String.valueOf(OpenWorld.roomSave)) + writeValue(String.valueOf(OpenWorld.roomNumber))
                        + writeValue(OpenWorld.holdCommand) + writeValue(Room.room) + writeValue(String.valueOf(MeadowDungeon.completed)) + writeValue(String.valueOf(MeadowDungeon.visited))
                        + writeValue(String.valueOf(DarkForestDungeon.completed)) + writeValue(String.valueOf(DarkForestDungeon.visited)) + writeValue(String.valueOf(MountainCaveDungeon.completed)) + writeValue(String.valueOf(MountainCaveDungeon.visited))
                        + writeValue(String.valueOf(MountainTopDungeon.completed)) + writeValue(String.valueOf(MountainTopDungeon.visited)) + writeValue(String.valueOf(DesertOasisDungeon.completed)) + writeValue(String.valueOf(DesertOasisDungeon.visited))
                        + writeValue(String.valueOf(DesertPlainsDungeon.completed)) + writeValue(String.valueOf(DesertPlainsDungeon.visited)) + writeValue(String.valueOf(DesertPyramidDungeon.completed)) + writeValue(String.valueOf(DesertPyramidDungeon.visited))
                        + writeValue(String.valueOf(OceanKingdomDungeon.completed)) + writeValue(String.valueOf(OceanKingdomDungeon.visited))
                );
    }

    private static String writeValue(String value) {
        // if (value == null) {
        //     return NULL_MARKER + "|";
        // }
        // StringBuilder hexString = new StringBuilder();
        // for (char ch : value.toCharArray()) {
        //     hexString.append(Integer.toHexString((int) ch));
        // }
        // // Append the separator
        // hexString.append("|");
        // return hexString.toString();
        return value + "|";
    }

    public static String readSave(String hexString) {
        // if (hexString == null || hexString.isEmpty()) {
        //     throw new IllegalArgumentException("Input hex string is null or empty");
        // }
        // StringBuilder value = new StringBuilder();
        // String[] segments = hexString.split("\\|");
        // for (String segment : segments) {
        //     if (NULL_MARKER.equals(segment)) {
        //         value.append("null");
        //     } else {
        //         for (int i = 0; i + 2 <= segment.length(); i += 2) {
        //             String hexByte = segment.substring(i, i + 2);
        //             char c = (char) Integer.parseInt(hexByte, 16);
        //             value.append(c);
        //         }
        //     }
        //     value.append('|');
        // }
        // // // Remove the last appended '|'
        // // if (value.length() > 0) {
        // //     value.setLength(value.length() - 1);
        // // }
        // return value.toString();
        return null;
    }

    public static void parseSave(String saveData) {
        String[] values = saveData.split("\\|");
        int index = 0;

        String playerName = values[index++];
        System.out.println("Player Name: " + playerName);
        int playerHealth = Integer.parseInt(values[index++]);
        System.out.println("Player Health: " + playerHealth);
        int playerMaxHealth = Integer.parseInt(values[index++]);
        System.out.println("Player Max Health: " + playerMaxHealth);
        int playerGold = Integer.parseInt(values[index++]);
        System.out.println("Player Gold: " + playerGold);
        Map<String, Integer> playerInventory = deserializeInventory(values[index++]);
        System.out.println("Player Inventory: " + playerInventory);
        int playerInventorySize = Integer.parseInt(values[index++]);
        System.out.println("Player Inventory Size: " + playerInventorySize);
        Player.playerSetSave(playerName, playerHealth, playerMaxHealth, playerGold, playerInventory, playerInventorySize);

        Main.savedPlace = values[index++];
        System.out.println("Saved Place: " + Main.savedPlace);
        Main.playerCreated = Boolean.parseBoolean(values[index++]);
        System.out.println("Player Created: " + Main.playerCreated);

        TextEngine.speedSetting = values[index++];
        System.out.println("Text Speed Setting: " + TextEngine.speedSetting);

        Dungeon.currentDungeon = values[index++];
        System.out.println("Current Dungeon: " + Dungeon.currentDungeon);
        Dungeon.completedDungeons = Integer.parseInt(values[index++]);
        System.out.println("Completed Dungeons: " + Dungeon.completedDungeons);

        String[] positionStrings = values[index++].split(",");
        Dungeon.currentPlayerPosition = new int[positionStrings.length];
        for (int i = 0; i < positionStrings.length; i++) {
            Dungeon.currentPlayerPosition[i] = Integer.parseInt(positionStrings[i]);
        }
        System.out.println("Current Player Position: " + Dungeon.currentPlayerPosition);
        String[] lastPositionStrings = values[index++].split(",");
        Dungeon.lastPosition = new int[lastPositionStrings.length];
        for (int i = 0; i < lastPositionStrings.length; i++) {
            Dungeon.lastPosition[i] = Integer.parseInt(lastPositionStrings[i]);
        }
        System.out.println("Last Position: " + Dungeon.lastPosition);

        Dungeon.meadowDungeon = deserializeIntArray(values[index++]);
        DungeonGenerator.printMap(Dungeon.meadowDungeon);
        Dungeon.darkForestDungeon = deserializeIntArray(values[index++]);
        DungeonGenerator.printMap(Dungeon.darkForestDungeon);
        Dungeon.mountainCaveDungeon = deserializeIntArray(values[index++]);
        DungeonGenerator.printMap(Dungeon.mountainCaveDungeon);
        Dungeon.mountainTopDungeon = deserializeIntArray(values[index++]);
        DungeonGenerator.printMap(Dungeon.mountainTopDungeon);
        Dungeon.desertOasisDungeon = deserializeIntArray(values[index++]);
        DungeonGenerator.printMap(Dungeon.desertOasisDungeon);
        Dungeon.desertPlainsDungeon = deserializeIntArray(values[index++]);
        DungeonGenerator.printMap(Dungeon.desertPlainsDungeon);
        Dungeon.desertPyramidDungeon = deserializeIntArray(values[index++]);
        DungeonGenerator.printMap(Dungeon.desertPyramidDungeon);
        Dungeon.oceanKingdomDungeon = deserializeIntArray(values[index++]);
        DungeonGenerator.printMap(Dungeon.oceanKingdomDungeon);

        Dungeon.missedItems = deserializeList(values[index++]);
        System.out.println("Missed Items: " + Dungeon.missedItems);

        SpawnRoom.roomSave = Integer.parseInt(values[index++]);
        System.out.println("Spawn Room Save: " + SpawnRoom.roomSave);

        OpenWorld.roomSave = Integer.parseInt(values[index++]);
        System.out.println("Open World Room Save: " + OpenWorld.roomSave);
        OpenWorld.roomNumber = Integer.parseInt(values[index++]);
        System.out.println("Open World Room Number: " + OpenWorld.roomNumber);
        OpenWorld.holdCommand = values[index++];
        System.out.println("Open World Hold Command: " + OpenWorld.holdCommand);

        Room.room = values[index++];
        System.out.println("Room: " + Room.room);

        MeadowDungeon.completed = Boolean.parseBoolean(values[index++]);
        MeadowDungeon.visited = Boolean.parseBoolean(values[index++]);
        DarkForestDungeon.completed = Boolean.parseBoolean(values[index++]);
        DarkForestDungeon.visited = Boolean.parseBoolean(values[index++]);
        MountainCaveDungeon.completed = Boolean.parseBoolean(values[index++]);
        MountainCaveDungeon.visited = Boolean.parseBoolean(values[index++]);
        MountainTopDungeon.completed = Boolean.parseBoolean(values[index++]);
        MountainTopDungeon.visited = Boolean.parseBoolean(values[index++]);
        DesertOasisDungeon.completed = Boolean.parseBoolean(values[index++]);
        DesertOasisDungeon.visited = Boolean.parseBoolean(values[index++]);
        DesertPlainsDungeon.completed = Boolean.parseBoolean(values[index++]);
        DesertPlainsDungeon.visited = Boolean.parseBoolean(values[index++]);
        DesertPyramidDungeon.completed = Boolean.parseBoolean(values[index++]);
        DesertPyramidDungeon.visited = Boolean.parseBoolean(values[index++]);
        OceanKingdomDungeon.completed = Boolean.parseBoolean(values[index++]);
        OceanKingdomDungeon.visited = Boolean.parseBoolean(values[index++]);
        System.out.println("Dungeon Completion Statuses: Meadow(" + MeadowDungeon.completed + "), Dark Forest(" + DarkForestDungeon.completed + "), Mountain Cave(" + MountainCaveDungeon.completed + "), Mountain Top(" + MountainTopDungeon.completed + "), Desert Oasis(" + DesertOasisDungeon.completed + "), Desert Plains(" + DesertPlainsDungeon.completed + "), Desert Pyramid(" + DesertPyramidDungeon.completed + "), Ocean Kingdom(" + OceanKingdomDungeon.completed + ")");
    }

    private static int[][] deserializeIntArray(String arrayString) {
        if (arrayString == null || arrayString.isEmpty() || NULL_MARKER.equals(arrayString)) {
            return new int[0][0];
        }
        String[] rows = arrayString.split(";");
        int[][] array = new int[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            String[] cols = rows[i].split(",");
            array[i] = new int[cols.length];
            for (int j = 0; j < cols.length; j++) {
                array[i][j] = Integer.parseInt(cols[j]);
            }
        }
        return array;
    }

    private static Map<String, Integer> deserializeInventory(String inventoryString) {
        Map<String, Integer> inventory = new HashMap<>();
        if (inventoryString == null || inventoryString.isEmpty() || NULL_MARKER.equals(inventoryString)) {
            return inventory;
        }
        String[] items = inventoryString.split(",");
        for (String item : items) {
            String[] parts = item.split(":");
            if (parts.length == 2) {
                String itemName = parts[0];
                int itemQuantity = Integer.parseInt(parts[1]);
                inventory.put(itemName, itemQuantity);
            }
        }
        return inventory;
    }

    private static List<String> deserializeList(String listString) {
        List<String> list = new ArrayList<>();
        if (listString == null || listString.isEmpty() || NULL_MARKER.equals(listString)) {
            return list;
        }
        String[] parts = listString.split(",");
        Collections.addAll(list, parts);
        return list;
    }
}
