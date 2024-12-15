package duque.rosa.greeneye;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConjuntoEspecies {
    @SerializedName("results")
    private List<Especie> resultado;

    public List<Especie> getResultado() {
        return resultado;
    }

    public void setResultado(List<Especie> resultado) {
        this.resultado = resultado;
    }
}
