package duque.rosa.greeneye;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    File imagem;
    PlantaService service;
    private File arquivoImagem;
    private static final String BASE_URL = "https://my-api.plantnet.org/";
    private static final String API_KEY = "2b10stuOqFgy1AUXtr2xdhFHf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(PlantaService.class);
    }

    public void tirarFoto(View view) {
        try {
            arquivoImagem = criarFile();
            Uri imageUri = FileProvider.getUriForFile(
                    this,
                    getApplicationContext().getPackageName() + ".fileprovider",
                    arquivoImagem
            );
            Intent intencaoFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intencaoFoto.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

            intencaoFoto.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intencaoFoto.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            startActivityForResult(intencaoFoto, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File criarFile() throws IOException {
        String nomeArquivo = "JPEG" + System.currentTimeMillis();
        File diretorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(nomeArquivo, ".jpg", diretorio);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 1 && resultCode == RESULT_OK) {
                if (arquivoImagem != null && arquivoImagem.exists()) {
                    imagem = new File(arquivoImagem.getAbsolutePath());

                    RequestBody imageBody = RequestBody.create(MediaType.parse("image/*"), imagem);
                    MultipartBody.Part imagePart = MultipartBody.Part.createFormData("images", imagem.getName(), imageBody);

                    Call<ConjuntoEspecies> call = service.identificarPlanta("all", API_KEY, imagePart);
                    call.enqueue(new Callback<ConjuntoEspecies>() {
                        @Override
                        public void onResponse(Call<ConjuntoEspecies> call, Response<ConjuntoEspecies> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                ConjuntoEspecies resultado = response.body();
                                Intent intencaoResultado = new Intent(getApplicationContext(), Resultado.class);
                                intencaoResultado.putExtra("acerto", resultado.getResultado().get(0).getAcerto());
                                intencaoResultado.putExtra("nomeCientifico", resultado.getResultado().get(0).getNomeCientifico());
                                startActivity(intencaoResultado);
                            } else {
                                Toast.makeText(MainActivity.this, "Erro na identificação da planta", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ConjuntoEspecies> call, Throwable t) {
                            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro ao processar imagem: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }}