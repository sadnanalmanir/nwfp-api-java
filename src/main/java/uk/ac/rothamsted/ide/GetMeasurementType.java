package uk.ac.rothamsted.ide;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.logging.Logger;

public class GetMeasurementType {
    private static final Logger log = Logger.getLogger(GetMeasurementType.class.getName());

    public static void main(String[] args) {
        try {
            String endpoint = "https://nwfp.rothamsted.ac.uk:8443/getMeasurementTypes";
            URL obj = new URL(endpoint);
            long startTime = System.currentTimeMillis();
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");
            // set connection timeout to 5 seconds
            // conn.setConnectTimeout(5000);
            // set content reading timeout to 20 seconds
            // conn.setReadTimeout(20000);
            // conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            conn.addRequestProperty("User-Agent", "Mozilla");
            log.info("Request URL: " + obj);

            int status = conn.getResponseCode();
            log.info("Response Code: " + status);
            if (status == HttpURLConnection.HTTP_OK) {
                log.info("'GET' Request is Successful. Http Status Code: " + status);
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                String responseLine;
                StringBuilder response = new StringBuilder();
                log.info("Reading response...");
                while ((responseLine = in.readLine()) != null) {
                    response.append(responseLine);
                }
                in.close();
                log.info("Done.");
                conn.disconnect();
                log.info("URL Connection closed.");

                long endTime = System.currentTimeMillis();
                log.info("Round trip response time = " + (endTime - startTime) + " ms");

                log.info("API Response Data: " + response);

                JsonArray jsonArray = new Gson().fromJson(response.toString(), JsonArray.class);
                Iterator<JsonElement> elementIterator = jsonArray.iterator();
                JsonObject element;
                while (elementIterator.hasNext()) {
                    element = elementIterator.next().getAsJsonObject();
                    String id = getNullAsEmptyString(element.get("Id"));
                    String displayName = getNullAsEmptyString(element.get("DisplayName"));
                    String name = getNullAsEmptyString(element.get("Name"));
                    String unit = getNullAsEmptyString(element.get("Unit"));
                    String displayUnit = getNullAsEmptyString(element.get("DisplayUnit"));
                    String llo = getNullAsEmptyString(element.get("LLO"));
                    String ulo = getNullAsEmptyString(element.get("ULO"));
                    String group = getNullAsEmptyString(element.get("Group"));
                    String systemSetQuality = getNullAsEmptyString(element.get("SystemSetQuality"));
                }
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    private static String getNullAsEmptyString(JsonElement jsonElement) {
        return jsonElement.isJsonNull() ? "" : jsonElement.getAsString();
    }

}