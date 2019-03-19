package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true)

public class BusStop {
    private String naptanId;
    private double distance;

    private BusStop() {}

    public String getNaptanId() {
        return naptanId;
    }

    public double getDistance() {
        return distance;
    }
}
