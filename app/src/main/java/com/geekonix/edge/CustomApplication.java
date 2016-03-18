package com.geekonix.edge;

import android.app.Application;

import com.urbanairship.UAirship;

/**
 * Created by sucho on 10/3/16.
 */
public class CustomApplication extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();

        UAirship.takeOff(this, new UAirship.OnReadyCallback() {
            @Override
            public void onAirshipReady(UAirship airship) {

                // Enable user notifications
                airship.getPushManager().setUserNotificationsEnabled(true);
            }
        });

    }
}
