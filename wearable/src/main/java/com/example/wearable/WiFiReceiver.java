package com.example.wearable;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.example.wearable.MainActivity.CHANNEL_ID;

public class WiFiReceiver extends BroadcastReceiver {
    
    @Override
    public void onReceive(Context context, Intent intent) {
        int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);

        if (wifiStateExtra == WifiManager.WIFI_STATE_ENABLED) {
            showNotification(context, "Wifi Connected");

        } else if (wifiStateExtra == WifiManager.WIFI_STATE_DISABLED) {
            showNotification(context, "Wifi Disconnected");
        }

    }

    private void showNotification(Context context, String msg) {
        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_rss_feed_black_24dp)
                .setContentTitle("Wi-Fi")
                .setContentText(msg)
                .extend(wearableExtender);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0, notificationBuilder.build());

    }
    }
