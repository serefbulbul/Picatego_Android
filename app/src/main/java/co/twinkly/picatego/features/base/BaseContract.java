package co.twinkly.picatego.features.base;

import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by serefbulbul on 19.12.2017.
 */

public interface BaseContract {

    interface View {
        android.view.View getRootView();

        void setTitle(String title);

        String getString(Object string);

        void showActionBar(Context context);

        void hideActionBar(Context context);

        void onBackPressed();

        void showProgressView();

        void hideProgressView();

        void showAlert(Object title, Object message, Object negativeTitle, DialogInterface.OnClickListener negativeAction, Object positiveTitle, DialogInterface.OnClickListener positiveAction);

        void showAlert(Object title, Object message, Object negativeTitle, DialogInterface.OnClickListener negativeAction);
    }

    interface Presenter {
        void subscribe();

        void unsubscribe();
    }

    interface Interactor {

    }
}