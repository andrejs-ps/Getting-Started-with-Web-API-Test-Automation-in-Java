package ps.webapi.automation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import ps.webapi.automation.entities.User;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ResponseUtils {

    public static String getHeader(ClassicHttpResponse response, String headerName) {

        // Get All headers
        Header[] headers = response.getHeaders();
        List<Header> httpHeaders = Arrays.asList(headers);
        String returnHeader = "";

        // Loop over the headers list
        for(Header header : httpHeaders){
            if(headerName.equalsIgnoreCase(header.getName())){
                returnHeader = header.getValue();
            }
        }
        // If no header found - throw an exception
        if(returnHeader.isEmpty()){
            throw new RuntimeException("Didn't find the header: " + headerName);
        }
        // Return the header
        return returnHeader;
    }


    public static String getHeaderJava8Way(ClassicHttpResponse response, String headerName) {

        List<Header> httpHeaders = Arrays.asList(response.getHeaders());

        Header matchedHeader = httpHeaders.stream()
                                .filter(header -> headerName.equalsIgnoreCase(header.getName()))
                                .findFirst().orElseThrow(() -> new RuntimeException("Didn't find the header"));

        return matchedHeader.getValue();
    }

    public static boolean headerIsPresent(ClassicHttpResponse response, String headerName) {
        List<Header> httpHeaders = Arrays.asList(response.getHeaders());

        return httpHeaders.stream()
                .anyMatch(header -> header.getName().equalsIgnoreCase(headerName));
    }

    public static User unmarshall(CloseableHttpResponse response, Class<User> clazz) throws IOException, ParseException {

        String jsonBody = EntityUtils.toString(response.getEntity());

        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonBody, clazz);
    }

    public static <T> T unmarshallGeneric(ClassicHttpResponse response, Class<T> clazz) throws IOException, ParseException {

        String jsonBody = EntityUtils.toString(response.getEntity());

        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonBody, clazz);
    }
}
