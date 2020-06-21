package cachet.plugins.notifications;

import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.content.BroadcastReceiver;
import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

/**
 * Notification listening service. Intercepts notifications if permission is given to do so.
 */
public class NotificationListener extends NotificationListenerService {

    public static String NOTIFICATION_INTENT = "notification_event";
    public static String NOTIFICATION_PACKAGE_NAME = "package_name";
    public static String NOTIFICATION_TEXT = "android_text";
    public static String NOTIFICATION_TITLE = "android_title";

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        String packageName = sbn.getPackageName();
        Intent intent = new Intent(getApplicationContext(),NotificationReceiver.class);
        intent.putExtra(NOTIFICATION_PACKAGE_NAME, packageName);

        Bundle extras = sbn.getNotification().extras;
        String title = extras.getString("android.title");
        String text = extras.getCharSequence("android.text").toString();

        intent.putExtra(NOTIFICATION_TEXT, text);
        intent.putExtra(NOTIFICATION_TITLE, title);

        sendBroadcast(intent);
    }
}