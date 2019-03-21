package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties (ignoreUnknown = true)

public class BusStop {
    private String naptanId;
    private String commonName;
    private long distance;
    private double lat;
    private double lon;
    private List<AdditionalProperty> additionalProperties;

    private BusStop() {}

    public String getNaptanId() {
        return naptanId;
    }

    public String getCommonName() {
        return commonName;
    }

    public List<AdditionalProperty> getAdditionalProperties() {
        return additionalProperties;
    }

    public double getDistance() {
        return distance;
    }

    public String getDirection() {
        for (AdditionalProperty additionalProperty : additionalProperties ){
            if (additionalProperty.getCategory().equalsIgnoreCase("direction") && additionalProperty.getKey().equalsIgnoreCase("compasspoint")){
                return additionalProperty.getValue();
            }
        }
        return "Direction not known";
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
