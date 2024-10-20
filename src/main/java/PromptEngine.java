
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

// Written by Caden Finley ACU 2024
// October 19, 2024
public class PromptEngine {

    public static String userAPIKey = null;
    public static boolean aiGenerationEnabled = false;

    public static String buildAndReturnPrompt(String extraInfo) {
        String villageDirection = Player.getCompassDirectionToClosestVillage();
        String nextDungeon = Player.getNextDungeon();
        String dungeonNextDirection = Player.getCompassDirectionToClosestDungeon();
        String setting = Player.getColorOfPlayerPostitionTile();
        int distanceToVillage = Player.distanceToVillage();
        int distanceToDungeon = Player.distanceToNextDungeon();
        String villageDistanceGauge;
        String dungeonDistanceGauge;
        if (villageDirection == null || villageDirection.isEmpty() || villageDirection.equals("No village found")) {
            villageDirection = "There is no village nearby.";
        }
        if (nextDungeon == null || nextDungeon.isEmpty() || nextDungeon.equals("No dungeon found")) {
            nextDungeon = "There is no dungeon nearby.";
        }
        if (setting == null || setting.isEmpty()) {
            setting = "generic";
        }
        if (extraInfo == null || extraInfo.isEmpty()) {
            extraInfo = "no extra info";
        }
        if (dungeonNextDirection == null || dungeonNextDirection.isEmpty() || dungeonNextDirection.equals("No dungeon found")) {
            dungeonNextDirection = "There is no dungeon nearby.";
        }
        if (distanceToDungeon > 0) {
            dungeonDistanceGauge = "The next dungeon is " + distanceToDungeon + " tiles away.";
        } else {
            dungeonDistanceGauge = "There is no dungeon nearby.";
        }
        if (distanceToVillage > 0) {
            villageDistanceGauge = "The village is " + distanceToVillage + " tiles away.";
        } else {
            villageDistanceGauge = "There is no village nearby.";
        }
        //return chatGPT("Generate me a prompt for a text adventure game. The village is to the " + villageDirection + ". The next dungeon is to the " + dungeonNextDirection + ". The next dungeon is called " + nextDungeon + ". The setting is " + setting + ". (100 words or less)");
        //debug
        //System.out.println("villageDirection: " + villageDirection + " nextDungeon: " + nextDungeon + " dungeonNextDirection: " + dungeonNextDirection + " setting: " + setting + " extraInfo: " + extraInfo);
        return chatGPT("Generate a me a prompt for a text adventure game. The player is in a " + setting + " setting. The village is to the " + villageDirection + "and is " + villageDistanceGauge + ". The next dungeon is to the " + dungeonNextDirection + "and is " + dungeonDistanceGauge + ". The next dungeon is called " + nextDungeon + ". " + extraInfo + " (50 words or less)");
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
            throw new RuntimeException(e);
        }
    }

    // This method extracts the response expected from chatgpt and returns it.
    private static String extractContentFromResponse(String response) {
        int startMarker = response.indexOf("content") + 11; // Marker for where the content starts.
        int endMarker = response.indexOf("\"", startMarker); // Marker for where the content ends.
        return response.substring(startMarker, endMarker); // Returns the substring containing only the response.
    }

    public static boolean testAPIKey(String apiKey) {
        // For the sake of the demo, we will assume the API key is valid.
        // userAPIKey = apiKey;
        // aiGenerationEnabled = true;
        // return true;
        String testMessage = "This is a test message to validate the API key.";
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
            e.printStackTrace();
            TextEngine.printNoDelay("API Key failed. Please try again.", false);
            aiGenerationEnabled = false;
            return false;
        }
    }
}
