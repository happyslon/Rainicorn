package geek.example.rainicorn.data;

import geek.example.rainicorn.data.models.gallery.GalleryItem;
import geek.example.rainicorn.data.models.owner.Owner;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Endpoints {

    @GET("rest/")
    Flowable<GalleryItem> getResponse(@Query("method") String method, @Query("api_key") String apiKey,
                                         @Query("format") String format, @Query("nojsoncallback") String nojsoncallback,
                                         @Query("extras") String extras);
    @GET("rest/")
    Flowable<Owner> getOwnerGallery(@Query("method") String method, @Query("api_key") String apiKey,@Query("user_id") String user,
                                @Query("format") String format, @Query("nojsoncallback") String nojsoncallback);
}
