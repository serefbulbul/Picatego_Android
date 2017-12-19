package co.twinkly.picatego.utils.services.network;

import com.google.gson.JsonElement;

import java.util.Map;

import co.twinkly.picatego.utils.constants.WebServiceAddresses;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by serefbulbul on 19.12.2017.
 */

public interface NetworkService {

    @POST(WebServiceAddresses.BASE_ADDRESS)
    Observable<JsonElement> createProspectCustomer(@Body Map<String, Object> body);
}