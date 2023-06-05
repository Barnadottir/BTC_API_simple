package com.btcapi.app;
import com.btcapi.app.ClientMaker;
import com.google.gson.JsonArray;
import org.apache.http.HttpEntity;
import java.util.TimerTask;
import java.io.IOException;
import java.util.Timer;
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Starting program!");
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run(){
                mainProgram();
            }
        }, 0, 120000);
    }

    public static void mainProgram() {
        ClientMaker client = new ClientMaker();
        try {
            HttpEntity entity = client.makeRequest();
            if (entity != null) {
                JsonArray responseArray = DataHandler.parseToJsonArray(entity);
                DataHandler.VWAPCalc(responseArray);
            }
        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
