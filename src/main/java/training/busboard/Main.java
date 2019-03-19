package training.busboard;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        Utilities.welcomeMsg("Hi lets find some busses :)");

        Location longAndLat = GetPostCode.getLongAndLatFromPostcode();

        List<BusStop> nearestBusStopsToPostCode = GetBusStop.apiTFLStopPointsList(longAndLat.getLongitude(), longAndLat.getLatitude());
        nearestBusStopsToPostCode.sort(Comparator.comparing(BusStop::getDistance));

        for (int i = 0; i < 2; i++) {
            List<ArrivalPrediction> arrivalPredictionList = GetArrivals.apiTFLBusses(nearestBusStopsToPostCode.get(i).getNaptanId());
            arrivalPredictionList.sort(Comparator.comparing(ArrivalPrediction::getTimeToStation));
            GetArrivals.printNextBussesAtUserDefinedStop(arrivalPredictionList);
        }

    }

}	
