package co.twinkly.picatego.features.itemdetail;

import co.twinkly.picatego.features.base.BaseContract;

public interface ItemDetailContract {

    interface View extends BaseContract.View {

        void setImageFromUrl(String url);

        void setText(String text);
    }

    interface Presenter extends BaseContract.Presenter {

    }

    interface Interactor extends BaseContract.Interactor {

    }
}
