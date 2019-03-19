package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class GetArrivals {

    public static void printNextBussesAtUserDefinedStop(List<ArrivalPrediction> apList) {

        System.out.println("Bus Stop - " + apList.get(0).getStationName());
        for (int i = 0; i < 5; i++) {
            ArrivalPrediction eachBus = apList.get(i);
            System.out.println("Number Bus " + eachBus.getLineName() +
                    "; Buses Destination " + eachBus.getDestinationName() +
                    "; Due in: " + (eachBus.getTimeToStation() / 60) + "mins");
        }
    }

    public static List<ArrivalPrediction> apiTFLBusses(String busStopCode) {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        return client.target("https://api.tfl.gov.uk/StopPoint/" + busStopCode + "/Arrivals")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<ArrivalPrediction>>() {
                });
    }
}
