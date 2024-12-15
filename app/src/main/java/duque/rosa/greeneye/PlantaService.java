package duque.rosa.greeneye;

import java.io.File;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface PlantaService {
    @Multipart
    @POST("v2/identify/{project}")
    Call<ConjuntoEspecies> identificarPlanta(
            @Path("project") String project,
            @Query("api-key") String apiKey,
            @Part MultipartBody.Part images
    );
    }