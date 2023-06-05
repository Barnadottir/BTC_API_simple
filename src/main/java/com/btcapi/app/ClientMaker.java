package com.btcapi.app;
import org.apache.http.impl.client.CloseableHttpClient;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.HttpGet;
import com.google.gson.JsonArray;
public class ClientMaker {

    private String BASE_URL = "https://api.gemini.com/";
    private final String btc_endpoint = "v1/trades/BTCUSD";


    //private final String btc_endpoint = "v1/trades/BTCUSD?since_tid=<since_tid_value>"
    //This could be used to get small payload from request
    //It would also not be necessary to iterate over all timestamps time - timestamp <= 600
    //as that would be given a the query paramter


    private String url = BASE_URL + btc_endpoint;
    CloseableHttpClient client; //must create new CloseableHttpClient with default config

    ClientMaker() {
        client = HttpClients.createDefault();
        //creating Closeable Http client with default config 
    }
    ClientMaker(String BASE_URL) {
        this.BASE_URL = BASE_URL;
        client = HttpClients.createDefault();
    }
    public HttpEntity makeRequest() throws IOException {

        
             //create get request from the given endpoint
            HttpGet getRequest = new HttpGet(url);

            //Could potentially add since_tid parameter instead
            //also the number of trades is currently maxed out at 50, should perhaps increase

            //use client to execute request
            //must handle potential errors
            CloseableHttpResponse getResponse = client.execute(getRequest);
            //extracting body of response;
            HttpEntity entity = getResponse.getEntity();

            return entity;
    }
}
