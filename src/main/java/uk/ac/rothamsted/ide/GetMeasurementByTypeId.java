package uk.ac.rothamsted.ide;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;
public class GetMeasurementByTypeId {
    private static final Logger log = Logger.getLogger(GetMeasurementByDateRange.class.getName());

    public static void main(String[] args) {
        String body = "{\n" +
                "    \"numPerPage\": 50,\n" +
                "    \"page\": 4,\n" +
                "    \"typeId\": 25\n" +
                "}";
        try {
            String endpoint = "https://nwfp.rothamsted.ac.uk:8443/getMeasurementsByDateRange";
            URL obj = new URL(endpoint);
            long startTime = System.currentTimeMillis();
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setDoOutput(true);
            // set connection timeout to 5 seconds
            // conn.setConnectTimeout(5000);
            // set content reading timeout to 20 seconds
            // conn.setReadTimeout(20000);
            // conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            log.info("Request URL: " + obj);

            try(OutputStream os = conn.getOutputStream()) {
                byte[] inputToSend = body.getBytes(StandardCharsets.UTF_8);
                os.write(inputToSend, 0, inputToSend.length);
            }

            int status = conn.getResponseCode();
            log.info("Response Code: " + status);
            if (status == HttpURLConnection.HTTP_OK) {
                log.info("'POST' Request is Successful. Http Status Code: " + status);
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
                    Set<String> keyset = element.keySet();

                    for (String key : keyset) {
                        if (key.equals("totalPages")) {
                            int totalPages = element.get("totalPages").getAsInt();
                            log.info("Total number of pages: " + totalPages);
                        }
                        if (key.equals("queryResults")) {
                            JsonArray queryResultsArray = element.get("queryResults").getAsJsonArray();
                            log.info("Query Results: " + queryResultsArray);

                            Iterator<JsonElement> queryResultsIterator = queryResultsArray.iterator();
                            JsonObject queryResult;

                            while (queryResultsIterator.hasNext()) {
                                queryResult = queryResultsIterator.next().getAsJsonObject();
                                String dateTimeVal = getNullAsEmptyString(queryResult.get("DateTime"));
                                String valueVal = getNullAsEmptyString(queryResult.get("Value"));
                                String measurementTypeDisplayNameVal = getNullAsEmptyString(queryResult.get("MeasTypeDisplayName"));
                                String locationNameVal = getNullAsEmptyString(queryResult.get("LocationName"));
                                String catchmentDisplayNameVal = getNullAsEmptyString(queryResult.get("CatchDisplayName"));
                                String dataQualityVal = getNullAsEmptyString(queryResult.get("dataQuality"));
                            }
                        }
                    }
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