package ps.webapi.automation;


import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Get404 extends BaseClass{

    @Test
    public void nonExistingUrlReturns404() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonexistingurl");

        int actualStatus = client.execute(get, response -> response.getCode());

        assertEquals(actualStatus, 404);
    }
}
