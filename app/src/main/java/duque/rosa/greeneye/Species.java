package duque.rosa.greeneye;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Species {
    @SerializedName("scientificNameWithoutAuthor")
    private String scientificName;

    @SerializedName("commonNames")
    private List<String> commonNames;

    // Getters and setters
    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public List<String> getCommonNames() {
        return commonNames;
    }

    public void setCommonNames(List<String> commonNames) {
        this.commonNames = commonNames;
    }
}