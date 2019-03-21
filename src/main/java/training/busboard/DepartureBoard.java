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
            busStopInfos.add(new BusStopInfo(nearestBusStopsToPostCode.get(i), arrivalPredictionList.subList(0, Math.min(5, arrivalPredictionList.size()))));
        }

        return busStopInfos;
    }
}
