package training.busboard.web;

import training.busboard.BusStopInfo;

import java.util.List;

public class BusInfo {
    private final String postcode;
    private List <BusStopInfo> departureBoard;

    public BusInfo(String postcode, List<BusStopInfo> departureBoard) {
        this.postcode = postcode;
        this.departureBoard = departureBoard;
    }

    public String getPostcode() {
        return postcode;
    }


    public List<BusStopInfo> getDepartureBoard() { return departureBoard; }
}
