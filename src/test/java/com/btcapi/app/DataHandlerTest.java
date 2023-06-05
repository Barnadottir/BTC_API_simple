package com.btcapi.app;
import org.apache.http.HttpEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.btcapi.app.DataHandler;
import com.google.gson.JsonArray;

import java.io.IOException;
public class DataHandlerTest {

    ClientMaker clientMaker = new ClientMaker();
    //created with default paramters

    @Test
    public void testJsonArrayLength() throws IOException {
        HttpEntity entity = clientMaker.makeRequest();
        JsonArray array = DataHandler.parseToJsonArray(entity);
        assertTrue(array.size()<=50);
    }
    
}
