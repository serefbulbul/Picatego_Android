package co.twinkly.picatego.utils.services.network;

import co.twinkly.picatego.utils.constants.WebServiceAddresses;
import co.twinkly.picatego.utils.services.network.models.PhotoDetailResponseModel;
import co.twinkly.picatego.utils.services.network.models.PhotoSearchResponseModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by serefbulbul on 19.12.2017.
 */

public interface NetworkService {

    @GET(WebServiceAddresses.SEARCH_WITH_TAG_URL)
    Observable<PhotoSearchResponseModel> getPhotosForTag(@Query("tags") String tag);
    @GET(WebServiceAddresses.DETAIL_WITH_ID_URL)
    Observable<PhotoDetailResponseModel> getPhotoDetailForId(@Query("photo_id") String id);

}