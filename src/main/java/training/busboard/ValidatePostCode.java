package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class ValidatePostCode {

    public static boolean isPostCodeValid (String postcode) {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        return client.target("https://api.postcodes.io/postcodes")
                .path(postcode)
                .path("validate")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(CheckLocation.class)
                .isResult();

    }

}
