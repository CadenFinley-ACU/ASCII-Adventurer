
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

// Written by Caden Finley ACU 2024
// https://www.youtube.com/watch?v=TkJ2dFtD0ho
// October 19, 2024
public class PromptEngine {

    private static final String yellowColor = "\u001B[33m";
    private static final String resetColor = "\u001B[0m";

    public static String userAPIKey = null;
    public static boolean aiGenerationEnabled = false;
    public static int promptLength = 30;
    private static String prompt = null;
    public static String lastGeneratedPrompt;

    private static final List<String> keywords = Arrays.asList(
            "village", "meadow", "dungeon", "dark forest", "mountain cave", "mountain top",
            "desert pyramid", "desert oasis", "desert plains", "ocean kingdom", "next dungeon",
            "north", "south", "east", "west", "northeast", "northwest", "southeast", "southwest",
            "Village", "Meadow", "Dungeon", "Dark Forest", "Mountain Cave", "Mountain Top",
            "Desert Pyramid", "Desert Oasis", "Desert Plains", "Ocean Kingdom", "Next dungeon",
            "North", "South", "East", "West", "Northeast", "Northwest", "Southeast", "Southwest"
    );

    public static void buildPrompt() throws InterruptedException {
        if ((aiGenerationEnabled && OpenWorld.roomChange()) || prompt == null || prompt.isEmpty()) {
            String villageDirection = Player.getCompassDirectionToClosestVillage();
            String nextDungeon = Player.getNextDungeon();
            String dungeonNextDirection = Player.getCompassDirectionToClosestDungeon();
            String setting = Player.getColorOfPlayerPostitionTile();
            int distanceToVillage = Player.distanceToVillage();
            int distanceToDungeon = Player.distanceToNextDungeon();
            String villageDistanceGauge;
            String dungeonDistanceGauge;
            String villagePrompt;
            String dungeonPrompt;
            TextEngine.printNoDelay("Loading...", false);
            if (villageDirection == null || villageDirection.isEmpty() || villageDirection.equals("No village found")) {
                villageDirection = "There is no village nearby";
            }
            if (nextDungeon == null || nextDungeon.isEmpty() || nextDungeon.equals("No dungeon found")) {
                nextDungeon = "There is no dungeon nearby";
            }
            if (setting == null || setting.isEmpty()) {
                setting = "grassland";
            }
            if (dungeonNextDirection == null || dungeonNextDirection.isEmpty() || dungeonNextDirection.equals("No dungeon found")) {
                dungeonNextDirection = "There is no dungeon nearby";
            }
            if (distanceToDungeon > 0) {
                if (distanceToDungeon == 0) {
                    dungeonDistanceGauge = "Right next to the player";
                } else {
                    dungeonDistanceGauge = distanceToDungeon + " tiles away";
                }
            } else {
                dungeonDistanceGauge = "There is no dungeon nearby.";
            }
            if (distanceToVillage > 0) {
                if (distanceToVillage == 0) {
                    villageDistanceGauge = "Right next to the player";
                } else {
                    villageDistanceGauge = distanceToVillage + " tiles away";
                }
            } else {
                villageDistanceGauge = "There is no village nearby";
            }
            if (distanceToVillage == 0) {
                villagePrompt = "The village is directly to the " + villageDirection + ".";
            } else {
                villagePrompt = "The village is to the " + villageDirection + " and is " + villageDistanceGauge + ".";
            }
            if (distanceToDungeon == 0) {
                dungeonPrompt = "The " + nextDungeon + " is directly to the " + dungeonNextDirection + ".";
            } else {
                dungeonPrompt = "The " + nextDungeon + " is to the " + dungeonNextDirection + " and is " + dungeonDistanceGauge + ".";
            }
            prompt = chatGPT("Generate a me a prompt for a text adventure game designed for highschoolers. Always state the direction of the structure if it is given and the distance if it is given. When giving direction do not abbreviate the direction. Do this in around " + promptLength + " words or less using this info: The player is in a " + setting + villagePrompt + dungeonPrompt + ".") + "\n";
            lastGeneratedPrompt = prompt;
            Main.screenRefresh();
        } else {
            prompt = lastGeneratedPrompt;
        }
    }

    public static void buildHelpPrompt(String[] availableCommands) throws InterruptedException {
        if (aiGenerationEnabled) {
            prompt = chatGPT("Generate a help prompt for a text adventure game designed for highschoolers. The player can use the following commands: " + String.join(", ", availableCommands) + ".") + "\n";
        }
    }

    public static String returnPrompt() throws InterruptedException {
        if (prompt == null || prompt.isEmpty()) {
            buildPrompt();
        }
        // Highlight keywords
        for (String keyword : keywords) {
            prompt = prompt.replaceAll("\\b" + keyword + "\\b", yellowColor + keyword + resetColor);
        }
        return prompt;
    }

    private static String chatGPT(String message) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = userAPIKey; // API key goes here
        String model = "gpt-3.5-turbo";

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");

            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
            con.setDoOutput(true);
            try (OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream())) {
                writer.write(body);
                writer.flush();
            }

            StringBuilder response;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            // returns the extracted contents of the response.
            return extractContentFromResponse(response.toString());

        } catch (IOException e) {
            TextEngine.printNoDelay("OpenAI API connection failed. Please check your API key and internet connection. Please try again later.", false);
            TextEngine.printNoDelay("AI generation has been disabled. You can renable it in settings.", false);
            TextEngine.enterToNext();
            aiGenerationEnabled = false;
            return " ";
            //throw new RuntimeException(e);
        }
    }

    // This method extracts the response expected from chatgpt and returns it.
    private static String extractContentFromResponse(String response) {
        int startMarker = response.indexOf("content") + 11; // Marker for where the content starts.
        int endMarker = response.indexOf("\"", startMarker); // Marker for where the content ends.
        return response.substring(startMarker, endMarker); // Returns the substring containing only the response.
    }

    public static boolean testAPIKey(String apiKey) {
        // userAPIKey = apiKey;
        // aiGenerationEnabled = true;
        // return true;
        String testMessage = "This is a test message to check if the API key is valid.";
        try {
            String url = "https://api.openai.com/v1/chat/completions";
            String model = "gpt-3.5-turbo";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + testMessage + "\"}]}";
            con.setDoOutput(true);
            try (OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream())) {
                writer.write(body);
                writer.flush();
            }
            StringBuilder response;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            // Check if the response contains the expected content
            String responseContent = extractContentFromResponse(response.toString());
            aiGenerationEnabled = responseContent != null && !responseContent.isEmpty();
            return responseContent != null && !responseContent.isEmpty();
        } catch (IOException e) {
            TextEngine.printNoDelay("API Key failed. Please check your API key and internet connection", false);
            aiGenerationEnabled = false;
            return false;
        }
    }

    public static void setPromptLength(String length) {
        promptLength = switch (length) {
            case "short" ->
                30;
            case "medium" ->
                50;
            case "long" ->
                75;
            default ->
                30;
        };
    }
}
