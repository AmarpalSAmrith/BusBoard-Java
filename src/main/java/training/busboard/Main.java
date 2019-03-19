package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

        Location longAndLat = getLongAndLatFromPostcode();

        List<BusStop> nearestBusStopsToPostCode = apiTFLStopPointsList(longAndLat.getLongitude(), longAndLat.getLatitude());
        nearestBusStopsToPostCode.sort(Comparator.comparing(BusStop::getDistance));




        // TODO Rename busStopCode
        String busStopCode = "";
        List<ArrivalPrediction> arrivalPredictionList = apiTFLBusses(busStopCode);
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

    private static List<ArrivalPrediction> apiTFLBusses(String busStopCode) {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        return client.target("https://api.tfl.gov.uk/StopPoint/" + busStopCode + "/Arrivals")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<ArrivalPrediction>>() {
                });
    }


    // TODO Potentially add radius variable
    private static List<BusStop> apiTFLStopPointsList(double lon, double lat) {

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


    private static Location getLongAndLatFromPostcode() {
        String postcode = Utilities.requestString("Please enter a postcode").toUpperCase();
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
