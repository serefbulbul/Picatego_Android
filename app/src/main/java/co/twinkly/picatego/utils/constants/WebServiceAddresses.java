package co.twinkly.picatego.utils.constants;

/**
 * Created by serefbulbul on 19.12.2017.
 */

public class WebServiceAddresses {

    private WebServiceAddresses() {

    }

    public static final String BASE_URL = "https://api.flickr.com/services/rest/";
    public static final String API_KEY_EXT = "?format=json&nojsoncallback=1&api_key=";
    public static final String API_KEY = "04a42d236e746206fbbf64245342dd2d";
    public static final String BASE_ADDRESS = BASE_URL + API_KEY_EXT + API_KEY;
    public static final String DEFAULT_PAGE_SIZE = "12";
    public static final String DEFAULT_PAGE = "12";
    public static final String SEARCH_WITH_TAG_URL = BASE_ADDRESS + "&method=flickr.photos.search&per_page=" + DEFAULT_PAGE_SIZE + "&page=" + DEFAULT_PAGE + "&extras=url_n";
    public static final String DETAIL_WITH_ID_URL = BASE_ADDRESS + "&method=flickr.photos.getInfo";
}

