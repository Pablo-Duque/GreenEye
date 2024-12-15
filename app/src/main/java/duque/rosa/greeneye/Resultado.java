package duque.rosa.greeneye;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Resultado extends AppCompatActivity {
    Bundle dados;
    String nomeCientifico;
    String nomeConhecido;
    Double acerto;
    TextView viewNomeCientifico;
    TextView viewAcerto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);

        dados = getIntent().getExtras();
        nomeCientifico = dados.getString("nomeCientifico");
        acerto = dados.getDouble("acerto");
        viewAcerto = findViewById(R.id.acerto);
        viewNomeCientifico = findViewById(R.id.nomeCientifico);
        viewAcerto.setText(acerto.toString());
        viewNomeCientifico.setText(nomeCientifico);
    }
}