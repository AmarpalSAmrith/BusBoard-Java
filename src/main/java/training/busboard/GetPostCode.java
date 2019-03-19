package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class GetPostCode {

    public static Location getLongAndLatFromPostcode(String postcode) {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        return client.target("https://api.postcodes.io/postcodes")
                .path(postcode)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(PostcodeResult.class)
                .getResult();
    }


    @JsonIgnoreProperties(ignoreUnknown = true)

    private static class PostcodeResult {

        private Location result;

        private PostcodeResult() {
        }

        public Location getResult() {
            return result;
        }
    }

}
