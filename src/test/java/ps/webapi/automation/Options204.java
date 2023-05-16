package ps.webapi.automation;

import org.apache.hc.client5.http.classic.methods.HttpOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class Options204 extends BaseClass {

    @Test
    public void optionsReturnsCorrectMethodsList() throws IOException {

        String header = "Access-Control-Allow-Methods";
        String expectedReply = "GET, POST, PATCH, PUT, DELETE";

        HttpOptions request = new HttpOptions(BASE_ENDPOINT);
        String actualValue = client.execute(request, response -> ResponseUtils.getHeader(response, header));

        Assert.assertEquals(actualValue, expectedReply);
    }
}
