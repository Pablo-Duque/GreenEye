package duque.rosa.greeneye;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Resultado extends AppCompatActivity {
    Bundle dados;
    String nomeCientifico;
    String nomeConhecido;
    Double acerto;
    TextView viewNomeCientifico;
    TextView viewAcerto;
    TextView viewNomeConhecido;
    String textoAcerto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);

        dados = getIntent().getExtras();

        nomeCientifico = dados.getString("nomeCientifico");
        acerto = dados.getDouble("acerto");
        nomeConhecido = dados.getString("nomeConhecido");

        viewAcerto = findViewById(R.id.acerto);
        viewNomeCientifico = findViewById(R.id.nomeCientifico);
        viewNomeConhecido = findViewById(R.id.nomeComum);

        textoAcerto = String.format("%.0f%%", (acerto * 100));

        viewAcerto.setText(textoAcerto);
        viewNomeCientifico.setText(nomeCientifico);
        viewNomeConhecido.setText(nomeConhecido);
    }

    public void voltar(View view){
        finish();
    }
}