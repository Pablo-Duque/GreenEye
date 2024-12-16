package duque.rosa.greeneye;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class Especie {
    @SerializedName("species")
    private JsonObject species;

    @SerializedName("score")
    private double acerto;

    public String getNomeCientifico() {
        if (species != null && species.has("scientificNameWithoutAuthor")) {
            return species.get("scientificNameWithoutAuthor").getAsString();
        }
        return null;
    }

    public void setSpecies(JsonObject species) {
        this.species = species;
    }

    public String getNomeConhecido() {
        if (species != null && species.has("commonNames")) {
            JsonArray commonNames = species.getAsJsonArray("commonNames");
            if (commonNames != null && commonNames.size() > 0) {
                return commonNames.get(0).getAsString();
            }
        }
        return null;
    }





    public double getAcerto() {
        return acerto;
    }


}
