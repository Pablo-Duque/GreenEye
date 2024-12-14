package duque.rosa.greeneye;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlantNetResponse {
    @SerializedName("results")
    private List<PlantResult> results;

    // Getters and setters
    public List<PlantResult> getResults() {
        return results;
    }

    public void setResults(List<PlantResult> results) {
        this.results = results;
    }
}
