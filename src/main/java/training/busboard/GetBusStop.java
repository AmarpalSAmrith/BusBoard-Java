package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class GetBusStop {

    // TODO Potentially add radius variable
    public static List<BusStop> apiTFLStopPointsList(double lon, double lat) {

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        return client.target("https://api.tfl.gov.uk/StopPoint")
                .queryParam("stopTypes", "NaptanPublicBusCoachTram")
                .queryParam("radius", 1000)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(BusStopStopPoints.class)
                .getStopPoints();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)

    private static class BusStopStopPoints {

        private List<BusStop> stopPoints;

        private BusStopStopPoints() {
        }

        public List<BusStop> getStopPoints() {
            return stopPoints;
        }
    }
}
