package ps.webapi.automation;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.core5.http.ParseException;
import ps.webapi.automation.entities.NotFound;
import ps.webapi.automation.entities.RateLimit;
import ps.webapi.automation.entities.User;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class BodyTestWithJackson extends BaseClass {

    @Test
    public void returnsCorrectLogin() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");

        User user =  client.execute(get, response -> ResponseUtils.unmarshallGeneric(response, User.class));

        assertEquals(user.getLogin(), "andrejss88");
    }

    @Test
    public void returnsCorrectId() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");

        User user =  client.execute(get, response -> ResponseUtils.unmarshallGeneric(response, User.class));

        assertEquals(user.getId(), 11834443);
    }

    @Test
    public void notFoundMessageIsCorrect() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonexistingendpoint");

        NotFound notFoundMessage =  client.execute(get, response -> ResponseUtils.unmarshallGeneric(response, NotFound.class));

        assertEquals(notFoundMessage.getMessage(), "Not Found");
    }

    @Test
    public void correctRateLimitsAreSet() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");

        RateLimit rateLimits =  client.execute(get, response -> ResponseUtils.unmarshallGeneric(response, RateLimit.class));

        assertEquals(rateLimits.getCoreLimit(), 60);
        assertEquals(rateLimits.getSearchLimit(), "10");
    }
}
