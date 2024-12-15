package duque.rosa.greeneye;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Especie {
    @SerializedName("scientificNameWithoutAuthor")
    private String nomeCientifico;

    @SerializedName("score")
    private double acerto;

    @SerializedName("commonNames")
    private List<String> nomeConhecido;

    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public List<String> getNomeConhecido() {
        return nomeConhecido;
    }

    public void setNomeConhecido(List<String> nomeConhecido) {
        this.nomeConhecido = nomeConhecido;
    }

    public double getAcerto() {
        return acerto;
    }

    public void setAcerto(double acerto) {
        this.acerto = acerto;
    }
}
