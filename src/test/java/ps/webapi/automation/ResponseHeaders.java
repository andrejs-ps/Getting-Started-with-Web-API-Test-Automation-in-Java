package ps.webapi.automation;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ResponseHeaders extends BaseClass {

    @Test
    public void contentTypeIsJson() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT);

        String contentType = client.execute(get, response -> response.getEntity().getContentType());

        assertEquals(contentType, "application/json; charset=utf-8");
    }

    @Test
    public void serverIsGithub() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT);

        String headerValue = client.execute(get, response -> ResponseUtils.getHeader(response, "Server"));

        assertEquals(headerValue, "GitHub.com");
    }

    @Test
    public void xRateLimitIsSixty() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT);

        String limitVal = client.execute(get, response -> ResponseUtils.getHeaderJava8Way(response, "X-RateLimit-Limit"));
        assertEquals(limitVal, "60");
    }

    @Test
    public void eTagIsPresent() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT);

        boolean tagIsPresent = client.execute(get, response -> ResponseUtils.headerIsPresent(response, "ETag"));

        assertTrue(tagIsPresent);
    }
}
