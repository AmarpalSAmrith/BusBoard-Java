package training.busboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class DepartureBoard {

    public static List<BusStopInfo> getBusDepartures (String postcode) {
        Location longAndLat = GetPostCode.getLongAndLatFromPostcode(postcode);
        List<BusStop> nearestBusStopsToPostCode = GetBusStop.apiTFLStopPointsList(longAndLat.getLongitude(), longAndLat.getLatitude());
        nearestBusStopsToPostCode.sort(Comparator.comparing(BusStop::getDistance));

        List<BusStopInfo> busStopInfos = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<ArrivalPrediction> arrivalPredictionList = GetArrivals.apiTFLBusses(nearestBusStopsToPostCode.get(i).getNaptanId());
            arrivalPredictionList.sort(Comparator.comparing(ArrivalPrediction::getTimeToStation));
            List <ArrivalPrediction> max5ArrivalPredictionList = new ArrayList<>();

            if (arrivalPredictionList.size() > 5) {
                for (int j = 0; j < 5; j++) {
                    max5ArrivalPredictionList.add(arrivalPredictionList.get(j));
                }
            }
            
            busStopInfos.add(new BusStopInfo(nearestBusStopsToPostCode.get(i),max5ArrivalPredictionList));
        }

        return busStopInfos;
    }
}
