package co.twinkly.picatego.features.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import co.twinkly.picatego.utils.managers.AlertViewManager;
import co.twinkly.picatego.utils.managers.ProgressViewManager;

/**
 * Created by serefbulbul on 19.12.2017.
 */

public abstract class BaseView extends FrameLayout implements BaseContract.View {

    protected final Context context;
    private final ProgressViewManager progressViewManager;
    protected View rootView;

    public BaseView(@NonNull Context context) {
        super(context);

        this.context = context;

        progressViewManager = new ProgressViewManager(context);
    }

    @Override
    public void setTitle(String title) {
        ((Activity) context).setTitle(title);
    }

    @Override
    public String getString(Object string) {
        if (string instanceof String) {
            return (String) string;
        } else if (string instanceof Integer) {
            return context.getString((Integer) string);
        } else {
            throw new RuntimeException("Unexpected type for getString()");
        }
    }

    @Override
    public void showActionBar(Context context) {
        ((AppCompatActivity) context).getSupportActionBar().setShowHideAnimationEnabled(false);
        ((AppCompatActivity) context).getSupportActionBar().show();
    }

    @Override
    public void hideActionBar(Context context) {
        ((AppCompatActivity) context).getSupportActionBar().setShowHideAnimationEnabled(false);
        ((AppCompatActivity) context).getSupportActionBar().hide();
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void onBackPressed() {
        ((Activity) context).onBackPressed();
    }

    @Override
    public void showProgressView() {
        progressViewManager.show();
    }

    @Override
    public void hideProgressView() {
        progressViewManager.hide();
    }

    @Override
    public void showAlert(Object title, Object message, Object negativeTitle, DialogInterface.OnClickListener negativeAction) {
        AlertViewManager.getAlertDialog(context, title, message, negativeTitle, negativeAction).show();
    }

    @Override
    public void showAlert(Object title, Object message, Object negativeTitle, DialogInterface.OnClickListener negativeAction, Object positiveTitle, DialogInterface.OnClickListener positiveAction) {
        AlertViewManager.getAlertDialog(context, title, message, negativeTitle, negativeAction, positiveTitle, positiveAction).show();
    }
}
