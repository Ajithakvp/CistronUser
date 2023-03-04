package com.example.cistronuser.Common;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.SystemClock;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.UpcomingCallListInterface;
import com.example.cistronuser.API.Model.UpcomingCallListModel;
import com.example.cistronuser.API.Response.UpcomingCallListResponse;
import com.example.cistronuser.Activity.DashboardActivity;
import com.example.cistronuser.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BroadCastReceiver extends BroadcastReceiver {
    private static final String TAG = "DashBoard";
    ArrayList<UpcomingCallListModel> locationTrackerCallReportModels = new ArrayList<>();
    Double lat, longg, str;

    Activity activity;
    private final static String default_notification_channel_id = "cistron";


    public static String NOTIFICATION_ID = "notification-id" ;
    public static String NOTIFICATION = "notification" ;
    public static final String NOTIFICATION_CHANNEL_ID = "1" ;

    public void onReceive (Context context , Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context. NOTIFICATION_SERVICE ) ;
        Notification notification = intent.getParcelableExtra( NOTIFICATION ) ;
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            int importance = NotificationManager. IMPORTANCE_HIGH ;
            NotificationChannel notificationChannel = new NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel) ;
        }
        int id = intent.getIntExtra( NOTIFICATION_ID , 0 ) ;
        assert notificationManager != null;
        notificationManager.notify(id , notification) ;
    }

}
