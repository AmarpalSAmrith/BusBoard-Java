package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class GetArrivals {

    public static List<ArrivalPrediction> apiTFLBusses(String busStopCode) {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        return client.target("https://api.tfl.gov.uk/StopPoint")
                .path(busStopCode)
                .path("Arrivals")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<ArrivalPrediction>>() {
                });
    }
}
