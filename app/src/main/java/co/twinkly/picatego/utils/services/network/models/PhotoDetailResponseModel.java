
package co.twinkly.picatego.utils.services.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoDetailResponseModel {

    @SerializedName("photo")
    @Expose
    private PhotoDetail photoDetail;
    @SerializedName("stat")
    @Expose
    private String stat;

    public PhotoDetail getPhotoDetail() {
        return photoDetail;
    }

    public void setPhotoDetail(PhotoDetail photoDetail) {
        this.photoDetail = photoDetail;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

}
