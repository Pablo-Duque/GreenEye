package duque.rosa.greeneye;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView foto;
    private static final String BASE_URL = "https://my-api.plantnet.org/";
    private static final String API_KEY = "YOUR-API-KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foto = findViewById(R.id.foto);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlantaService service = retrofit.create(PlantaService.class);
    }

    public void tirarFoto(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            foto.setImageBitmap(bitmap);//substituir pelo uso de api

            Call<PlantNetResponse> call = service.identificarPlanta("all", API_KEY, images, organs);
            call.enqueue(new Callback<PlantNetResponse>() {
                @Override
                public void onResponse(Call<PlantNetResponse> call, Response<PlantNetResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        PlantNetResponse result = response.body();
                        // Processar o resultado
                    }
                }
        }
    }
}