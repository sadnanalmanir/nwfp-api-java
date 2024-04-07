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

public class GetFieldEvent {
    private static final Logger log = Logger.getLogger(GetFieldEvent.class.getName());

    public static void main(String[] args) {
        try {
            String endpoint = "https://nwfp.rothamsted.ac.uk:8443/getFieldEvents";
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
                    String datasetVersionId = getNullAsEmptyString(element.get("DatasetVersionId"));
                    String catchName = getNullAsEmptyString(element.get("catch_name"));
                    String eventDate = getNullAsEmptyString(element.get("EventDate"));
                    String timeIn = getNullAsEmptyString(element.get("TimeIn"));
                    String timeOut = getNullAsEmptyString(element.get("TimeOut"));
                    String timeInField = getNullAsEmptyString(element.get("TimeInField"));
                    String applicationTypeName = getNullAsEmptyString(element.get("ApplicationTypeName"));
                    String fieldName = getNullAsEmptyString(element.get("Field_Name"));
                    String tempFieldName = getNullAsEmptyString(element.get("TempField_Name"));
                    String totalApplication = getNullAsEmptyString(element.get("TotalApplication"));
                    String applicationInfo = getNullAsEmptyString(element.get("ApplicationInfo"));
                    String applicationRate = getNullAsEmptyString(element.get("ApplicationRate"));
                    String fieldId = getNullAsEmptyString(element.get("FieldId"));
                    String tractorId = getNullAsEmptyString(element.get("TractorId"));
                    String startTractorHours = getNullAsEmptyString(element.get("StartTractorHours"));
                    String endTractorHours = getNullAsEmptyString(element.get("EndTractorHours"));
                    String totalTractorHours = getNullAsEmptyString(element.get("TotalTractorHours"));
                    String fieldOperationId = getNullAsEmptyString(element.get("FieldOperationId"));
                    String fieldApplicationId = getNullAsEmptyString(element.get("FieldApplicationId"));
                    String applicationBatchNumber = getNullAsEmptyString(element.get("ApplicationBatchNumber"));
                    String productName = getNullAsEmptyString(element.get("ProductName"));
                    String manufacturer = getNullAsEmptyString(element.get("Manufacturer"));
                    String temporaryFieldId = getNullAsEmptyString(element.get("TemporaryFieldId"));
                    String catchmentName = getNullAsEmptyString(element.get("Catchment_Name"));
                    String operationName = getNullAsEmptyString(element.get("Operation_name"));
                    String operationGroup = getNullAsEmptyString(element.get("OperationGroup"));
                    String applicationName = getNullAsEmptyString(element.get("ApplicationName"));
                    String applicationInfoApps = getNullAsEmptyString(element.get("ApplicationInfoApps"));
                    String formatName = getNullAsEmptyString(element.get("FormatName"));
                    String rangeEndDateTime = getNullAsEmptyString(element.get("RangeEndDateTime"));
                    String rangeStartDateTime = getNullAsEmptyString(element.get("RangeStartDateTime"));
                    String tempFieldId = getNullAsEmptyString(element.get("TempFieldId"));
                    String farmletNew = getNullAsEmptyString(element.get("Farmlet_new"));
                    String farmletOld = getNullAsEmptyString(element.get("Farmlet_old"));
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