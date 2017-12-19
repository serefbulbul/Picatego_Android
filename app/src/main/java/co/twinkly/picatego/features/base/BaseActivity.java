package co.twinkly.picatego.features.base;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import co.twinkly.picatego.app.App;

/**
 * Created by serefbulbul on 19.12.2017.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();

        App.isActivityActive = true;

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    @Override
    protected void onPause() {
        super.onPause();

        App.isActivityActive = false;
    }

    @Override
    protected void onStart() {
        super.onStart();

        App.activityCount++;
    }

    @Override
    protected void onStop() {
        super.onStop();

        App.activityCount--;
    }
}