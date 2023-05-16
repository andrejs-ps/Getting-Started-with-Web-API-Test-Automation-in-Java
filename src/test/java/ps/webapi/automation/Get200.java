package ps.webapi.automation;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Get200 extends BaseClass{

    @Test
    public void rateLimitReturns200() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");

        int actualStatus = client.execute(get, response -> response.getCode());

        assertEquals(actualStatus, 200);
    }

    @Test
    public void searchReposReturns200() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/search/repositories?q=java");

        int actualStatus = client.execute(get, response -> response.getCode());

        assertEquals(actualStatus, 200);
    }
}
