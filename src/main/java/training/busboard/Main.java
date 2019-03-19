package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        Utilities.welcomeMsg("Hi lets find some busses :)");

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        String busStopCode = Utilities.requestString("Please enter a Bus stop code");

        List<ArrivalPrediction> arrivalPredictionList = client.target("https://api.tfl.gov.uk/StopPoint/" + busStopCode + "/Arrivals")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<ArrivalPrediction>>() {
                });
        arrivalPredictionList.sort(Comparator.comparing(ArrivalPrediction::getTimeToStation));
        printNextBussesAtUserDefinedStop(arrivalPredictionList);
    }

    private static void printNextBussesAtUserDefinedStop(List<ArrivalPrediction> apList) {
        int requestNumberOfBusses = Utilities.requestInt("How many Buses do you want to return?");
        int numberOfBusses = requestNumberOfBusses < apList.size() ? requestNumberOfBusses : apList.size() - 1;
        System.out.println("I can only return: " + numberOfBusses);

        for (int i = 0; i < numberOfBusses; i++) {
            ArrivalPrediction eachBus = apList.get(i);
            System.out.println("Number Bus " + eachBus.getLineName() +
                    "; Buses Destination " + eachBus.getDestinationName() +
                    "; Due in: " + (eachBus.getTimeToStation() / 60) + "mins");
        }
    }
}	
