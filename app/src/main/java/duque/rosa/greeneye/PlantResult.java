package duque.rosa.greeneye;

import com.google.gson.annotations.SerializedName;

public class PlantResult {
    @SerializedName("score")
    private double score;

    @SerializedName("species")
    private Species species;

    // Getters and setters
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }
}
