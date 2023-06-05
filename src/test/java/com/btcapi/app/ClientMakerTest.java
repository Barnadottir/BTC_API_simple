package com.btcapi.app;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ClientMakerTest {

    ClientMaker clientMaker;
    
    @Test
    public void testMakeRequestNull(){
        clientMaker = new ClientMaker("wrongURL");//IOException should be thrown
        assertNull(clientMaker.makeRequest());
        
    }
    
    @Test
    public void testMakeRequestNotNull() {
        clientMaker = new ClientMaker();//IOException should be thrown
        assertNotNull(clientMaker.makeRequest());
    }

    
}
