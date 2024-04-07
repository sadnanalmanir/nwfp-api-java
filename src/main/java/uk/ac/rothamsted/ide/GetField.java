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

public class GetField {
    private static final Logger log = Logger.getLogger(GetField.class.getName());

    public static void main(String[] args) {
        try {
            String endpoint = "https://nwfp.rothamsted.ac.uk:8443/getFields";
            URL obj = new URL(endpoint);
            long startTime = System.currentTimeMillis();
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");
            // set connection timeout to 2 seconds
            // conn.setConnectTimeout(5000);
            // set content reading timeout to 5 seconds
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
                    //logger.info("element: " + element);
                    String id = getNullAsEmptyString(element.get("Id"));
                    String displayId = getNullAsEmptyString(element.get("DisplayId"));
                    String name = getNullAsEmptyString(element.get("Name"));
                    String validFrom = getNullAsEmptyString(element.get("ValidFrom"));
                    String validUntil = getNullAsEmptyString(element.get("ValidUntil"));
                    String cuttingArea = getNullAsEmptyString(element.get("CuttingArea"));
                    String fencedArea = getNullAsEmptyString(element.get("FencedArea"));
                    String organicSpreadingArea = getNullAsEmptyString(element.get("OrganicSpreadingArea"));
                    String inorganicSpreadingArea = getNullAsEmptyString(element.get("InorganicSpreadingArea"));
                    String catchment_id = getNullAsEmptyString(element.get("Catchment_Id"));
                    String hydrologicalArea = getNullAsEmptyString(element.get("HydrologicalArea"));
                    log.info("Id: " + id
                            + " DisplayId: " + displayId
                            + " Name: " + name
                            + " ValidFrom: " + validFrom
                            + " ValidUntil: " + validUntil
                            + " CuttingArea: " + cuttingArea
                            + " FencedArea: " + fencedArea
                            + " OrganicSpreadingArea: " + organicSpreadingArea
                            + " InorganicSpreadingArea: " + inorganicSpreadingArea
                            + " Catchment_Id: " + catchment_id
                            + " HydrologicalArea: " + hydrologicalArea);
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