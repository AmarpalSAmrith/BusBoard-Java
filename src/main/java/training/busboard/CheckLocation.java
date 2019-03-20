package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true)

public class CheckLocation {

    private boolean result;

    private CheckLocation () {}

    public boolean isResult() {
        return result;
    }
}
