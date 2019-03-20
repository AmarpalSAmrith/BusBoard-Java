package training.busboard;

import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String args[]) {
        Utilities.welcomeMsg("Hi lets find some busses :)");

        String postcode = Utilities.requestString("Please enter a postcode").toUpperCase().replaceAll(" ", "");

        Location longAndLat = GetPostCode.getLongAndLatFromPostcode(postcode);

        List<BusStop> nearestBusStopsToPostCode = GetBusStop.apiTFLStopPointsList(longAndLat.getLongitude(), longAndLat.getLatitude());
        nearestBusStopsToPostCode.sort(Comparator.comparing(BusStop::getDistance));

        for (int i = 0; i < 2; i++) {
            List<ArrivalPrediction> arrivalPredictionList = GetArrivals.apiTFLBusses(nearestBusStopsToPostCode.get(i).getNaptanId());
            arrivalPredictionList.sort(Comparator.comparing(ArrivalPrediction::getTimeToStation));
            printNextBussesAtUserDefinedStop(nearestBusStopsToPostCode.get(i).getCommonName(), arrivalPredictionList);
        }

    }

    public static void printNextBussesAtUserDefinedStop(String stopName, List<ArrivalPrediction> apList) {
        System.out.println("Bus Stop - " + stopName);
        for (int i = 0; i < Math.min(apList.size(), 5); i++) {
            ArrivalPrediction eachBus = apList.get(i);
            System.out.println("Number Bus " + eachBus.getLineName() +
                    "; Buses Destination " + eachBus.getDestinationName() +
                    "; Due in: " + (eachBus.getTimeToStation() / 60) + "mins");
        }
    }

}	
