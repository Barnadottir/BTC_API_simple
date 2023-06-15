package com.btcapi.app;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import java.time.Instant;
public class DataHandler {
    //convert entity to jsonArray
    public static JsonArray parseToJsonArray(HttpEntity entity) throws IOException,IllegalStateException {
        //handling IO Exeception in the HTTPClient, so no need to add try/catch
        //has to be string for us to parser with "parseString" method
        //converting entity to string
        String responseString = EntityUtils.toString(entity);
        JsonArray responseArray = JsonParser.parseString(responseString).getAsJsonArray();
        return responseArray;
    }
    
    //test

    public Boolean testMethod(){
        return null;
    }

    public static void VWAPCalc(JsonArray array) {

        double sum_volume_2 = 0.0;
        double VWMP_num_2 = 0.0;
        double sum_volume_10 = 0.0;
        double VWMP_num_10 = 0.0;

        long time = Instant.now().getEpochSecond();
        for (JsonElement element : array) {
            JsonObject jsonObject = element.getAsJsonObject();
            if (jsonObject.has("timestamp") && jsonObject.has("price") && jsonObject.has("amount")) {
                long timestamp = jsonObject.get("timestamp").getAsLong();
                if (time - timestamp <= 600) {
                    double price = jsonObject.get("price").getAsDouble();
                    double volume = jsonObject.get("amount").getAsDouble();

                    //if within 600 seconds of current time
                    //add data to volume and overall VWMP for 10 min interval data
                    VWMP_num_10 = VWMP_num_10 + (price * volume);
                    sum_volume_10 = sum_volume_10 + volume;

                    if (time - timestamp <= 120) {
                        //if within 600 seconds of current time
                        //add data to volume and overall VWMP for 10 min interval data
                        VWMP_num_2 = VWMP_num_2 + (price * volume);
                        sum_volume_2 = sum_volume_2 + volume;
                    }
                }
            }
            else {
                System.out.println("given memeber is not present in JsonObject");
            }
        }
        double VWMP_2 = VWMP_num_2/sum_volume_2;
        double VWMP_10 = VWMP_num_10/sum_volume_10;
        System.out.println("\nVolume 2 min: " + VWMP_2 + "| Volume 10 min: " + VWMP_10);
        System.out.println("VWMP 2 min: " + VWMP_2 + "| VWMP 10 min: " + VWMP_10);
        if(VWMP_2 < VWMP_10) {
            System.out.println("Price is going down!");
        } else {
            System.out.println("Price is going up!");
        }
    }
}
