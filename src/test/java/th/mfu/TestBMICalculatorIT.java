package th.mfu;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.hamcrest.CoreMatchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBMICalculatorIT {
    private static Client client;
    private static Logger _logger = LoggerFactory.getLogger(TestBMICalculatorIT.class);
    private static String WEB_URI = "http://localhost:8080/calbmi";

    @BeforeClass
    public static void createClient() {
        // Use ClientBuilder to create a new client that can be used to create
        // connections to the Web service.
        client = ClientBuilder.newClient();
    }

    @AfterClass
    public static void closeConnection() {
        client.close();
    }

    @Test
    public void testCaclulate1() {

        // Make a HTTP GET request to retrieve the last created Parolee.
        try (Response response = client.target(WEB_URI+"?weight=70&height=1.5").request().get()) {

            // Check that the HTTP response code is 200 OK.
            int responseCode = response.getStatus();
            assertEquals(200, responseCode);

            String jsonResponse = response.readEntity(String.class);
            assertThat(jsonResponse, CoreMatchers.containsString("Result is 31"));

            assertThat(jsonResponse, CoreMatchers.containsString("obese"));
            _logger.info("IT1 test passed");
        }
    }

    //TODO: add another test case for normal built such as weight=50 and height=1.5.
    @Test
    public void testCaclulate2() {

        // Make a HTTP GET request to retrieve the last created Parolee.
        try (Response response = client.target(WEB_URI+"?weight=50&height=1.5").request().get()) {

            // Check that the HTTP response code is 200 OK.
            int responseCode = response.getStatus();
            assertEquals(200, responseCode);

            String jsonResponse = response.readEntity(String.class);
            assertThat(jsonResponse, CoreMatchers.containsString("Result is 22"));

            assertThat(jsonResponse, CoreMatchers.containsString("normal"));
            _logger.info("IT1 test passed");
        }
    }

}
