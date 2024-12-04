
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * ASCIIADVENTURER Caden Finley Albert Tucker Grijesh Shrestha
 *
 * The PromptEngine class generates prompts for a text adventure game using an
 * OpenAI API key.
 *
 * @author ASCIIADVENTURERS
 * @version 1.0
 */
public class PromptEngine {

    private static final String yellowColor = "\u001B[33m";
    private static final String resetColor = "\u001B[0m";

    public static final String USER_API_KEY = null;
    public static boolean aiGenerationEnabled = true;
    public static int promptLength = 30;
    private static String prompt = null;
    /**
     * List of keywords to highlight in the generated prompt.
     */
    private static final List<String> keywords = Arrays.asList(
            "village", "meadow", "dungeon", "dark forest", "mountain cave", "mountain top",
            "desert pyramid", "desert oasis", "desert plains", "ocean kingdom", "next dungeon",
            "north", "south", "east", "west", "northeast", "northwest", "southeast", "southwest",
            "Village", "Meadow", "Dungeon", "Dark Forest", "Mountain Cave", "Mountain Top",
            "Desert Pyramid", "Desert Oasis", "Desert Plains", "Ocean Kingdom", "Next dungeon",
            "North", "South", "East", "West", "Northeast", "Northwest", "Southeast", "Southwest"
    );

    /**
     * The `buildPrompt` function generates a prompt for a text adventure game,
     * providing information about the player's location, nearby structures, and
     * distances in a descriptive manner.
     */
    public static void buildPrompt() {
        if (aiGenerationEnabled && (OpenWorld.checkChangeInRoom() || prompt == null || prompt.isEmpty())) {
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
            // "add more" / "make more complex"
            prompt = chatGPT("Generate a me a prompt for a text adventure game designed for highschoolers. Always state the direction of the structure if it is given and the distance if it is given. When giving direction do not abbreviate the direction. Do this in around " + promptLength + " words or less using this info: The player headed " + OpenWorld.holdCommand + " and is in a " + setting + " " + villagePrompt + dungeonPrompt + ".") + "\n";
            GameEngine.screenRefresh();
        }
    }

    /**
     * The function `buildHelpPrompt` generates a help prompt for a text
     * adventure game with available commands using chatGPT if AI generation is
     * enabled.
     *
     * @param availableCommands It looks like the `buildHelpPrompt` method is
     * designed to generate a help prompt for a text adventure game. The method
     * takes an array of available commands as a parameter. These commands are
     * the actions that the player can use in the game.
     */
    public static void buildHelpPrompt(String[] availableCommands) {
        if (aiGenerationEnabled) {
            prompt = chatGPT("Generate a help prompt for a text adventure game designed for highschoolers. The player can use the following commands: " + String.join(", ", availableCommands) + ".") + "\n";
        }
    }

    /**
     * The function `returnPrompt` checks if AI generation is enabled,
     * highlights keywords in the prompt, and returns the modified prompt or a
     * message if AI generation is disabled.
     *
     * @return The method `returnPrompt()` returns the generated prompt with
     * highlighted keywords if AI generation is enabled. If AI generation is
     * disabled, it returns the message "AI generation is disabled. Please
     * enable it in settings."
     */
    public static String returnPrompt() {
        if (aiGenerationEnabled) {
            if (prompt == null || prompt.isEmpty()) {
                buildPrompt();
            }
            // Highlight keywords
            for (String keyword : keywords) {
                prompt = prompt.replaceAll("\\b" + keyword + "\\b", yellowColor + keyword + resetColor);
            }
            return prompt;
        }
        return "AI generation is disabled. Please enable it in settings.";
    }

    /**
     * The function `chatGPT` sends a message to the OpenAI API for chat
     * completions using a specified model and API key, and returns the
     * extracted content from the response.
     *
     * @param message The `chatGPT` method you provided is a Java method that
     * interacts with the OpenAI GPT-3.5 API to generate chat completions based
     * on the input message.
     * @return The `chatGPT` method returns the extracted contents of the
     * response from the OpenAI API after processing the input message through
     * the GPT-3.5 model.
     */
    private static String chatGPT(String message) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = USER_API_KEY; // API key goes here
        String model = "gpt-3.5-turbo";
        //System.out.println(message);
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
            TextEngine.printNoDelay("OpenAI API connection failed. Please check your internet connection and try again later.", false);
            TextEngine.printNoDelay("AI generation has been disabled. You can renable it in settings.", false);
            TextEngine.enterToNext();
            aiGenerationEnabled = false;
            return " ";
            //throw new RuntimeException(e);
        }
    }

    /**
     * This function extracts content from a response string based on specific
     * markers.
     *
     * @param response The `extractContentFromResponse` method takes a `String`
     * parameter named `response`, which is the input string from which we want
     * to extract the content. The method then finds the starting and ending
     * markers within the response string to extract the content between them.
     * @return The method `extractContentFromResponse` returns a substring
     * containing the content extracted from the input response string.
     */
    private static String extractContentFromResponse(String response) {
        int startMarker = response.indexOf("content") + 11; // Marker for where the content starts.
        int endMarker = response.indexOf("\"", startMarker); // Marker for where the content ends.
        //System.out.println(response);
        return response.substring(startMarker, endMarker); // Returns the substring containing only the response.
    }

    /**
     * The function `testAPIKey` sends a test message to an API endpoint using
     * the provided API key and checks if a valid response is received.
     *
     * @param apiKey The `testAPIKey` method you provided is used to test the
     * validity of an API key by making a request to the OpenAI API endpoint.
     * The `apiKey` parameter is the API key that is passed to the method for
     * testing.
     * @return The `testAPIKey` method returns a boolean value. If the API key
     * is valid and the response from the API contains expected content, it
     * returns `true`. If there is an IOException (e.g., internet connection
     * issue) or the response does not contain the expected content, it returns
     * `false`.
     */
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
            TextEngine.printNoDelay("API Key failed. Please check your internet connection", false);
            aiGenerationEnabled = false;
            return false;
        }
    }

    /**
     * The function `setPromptLength` sets the length of a prompt based on the
     * input string "short", "medium", or "long".
     *
     * @param length The `length` parameter in the `setPromptLength` method is a
     * String that specifies the desired length of the prompt. It can have three
     * possible values: "short", "medium", or "long". The method sets the
     * `promptLength` variable based on the value of the `length`
     */
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
