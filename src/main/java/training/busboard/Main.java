package training.busboard;

import apple.laf.JRSUIConstants;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        List<ArrivalPrediction> name = client.target("https://api.tfl.gov.uk/StopPoint/490008660N/Arrivals")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<ArrivalPrediction>>() {});
        System.out.println(name);
    }
}	
