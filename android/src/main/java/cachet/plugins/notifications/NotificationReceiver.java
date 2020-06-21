package cachet.plugins.notifications;

import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.flutter.plugin.common.EventChannel.EventSink;

/** Android-specific */
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;


public class NotificationReceiver extends BroadcastReceiver{
    private EventSink eventSink=NotificationsPlugin.eventSink;
    final static String TAG = "NOTIFICATION_RECEIVER";

    /** Called whenever the event channel is subscribed to in Flutter */


    @Override
    public void onReceive(Context context, Intent intent) {
        String packageName = intent.getStringExtra(NotificationListener.NOTIFICATION_PACKAGE_NAME);
        String title = intent.getStringExtra(NotificationListener.NOTIFICATION_TITLE);
        String text = intent.getStringExtra(NotificationListener.NOTIFICATION_TEXT);
        String response= packageName+"<br>"+title+"<br>"+text;

        eventSink.success(response);
    }
}