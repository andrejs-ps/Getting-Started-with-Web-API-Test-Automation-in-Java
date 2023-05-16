package ps.webapi.automation;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static ps.webapi.automation.entities.User.ID;
import static ps.webapi.automation.entities.User.LOGIN;


public class BodyTestWithSimpleMap extends BaseClass {

    @Test
    public void returnsCorrectLogin() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");

        String jsonBody =  client.execute(get, response -> EntityUtils.toString(response.getEntity()));

        JSONObject jsonObject = new JSONObject(jsonBody);

        String loginValue = (String) getValueFor(jsonObject, LOGIN);

        Assert.assertEquals(loginValue, "andrejss88");
    }

    @Test
    public void returnsCorrectId() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");

        String jsonBody =  client.execute(get, response -> EntityUtils.toString(response.getEntity()));

        JSONObject jsonObject = new JSONObject(jsonBody);

        Integer loginValue = (Integer) getValueFor(jsonObject, ID);

        Assert.assertEquals(loginValue, Integer.valueOf(11834443));
    }

    private Object getValueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }
}
