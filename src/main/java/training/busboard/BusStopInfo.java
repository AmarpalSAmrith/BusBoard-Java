package training.busboard;

import java.util.List;

public class BusStopInfo {

    private BusStop busStop;
    private List<ArrivalPrediction> arrivals;

    public BusStopInfo(BusStop busStop, List<ArrivalPrediction> arrivals) {
        this.busStop = busStop;
        this.arrivals = arrivals;
    }

    public BusStop getBusStop() {
        return busStop;
    }

    public List<ArrivalPrediction> getArrivals() {
        return arrivals;
    }
}
