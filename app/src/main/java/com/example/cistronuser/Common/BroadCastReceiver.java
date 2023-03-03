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
        CallCheckLocation(context);
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



    private void CallCheckLocation(Context context) {
        UpcomingCallListInterface upcomingCallListInterface = APIClient.getClient().create(UpcomingCallListInterface.class);
        upcomingCallListInterface.CallUpcomingCallReport("getCallsRecords", PreferenceManager.getEmpID(context), "today").enqueue(new Callback<UpcomingCallListResponse>() {
            @Override
            public void onResponse(Call<UpcomingCallListResponse> call, Response<UpcomingCallListResponse> response) {
                try {
                    locationTrackerCallReportModels = response.body().getUpcomingCallListModels();


                    for (int i = 0; i < locationTrackerCallReportModels.size(); i++) {




                        Location locationA = new Location("Location A");
                        Location locationB = new Location("Location B");
                        locationA.setLatitude(Double.parseDouble(String.valueOf(lat)));
                        locationA.setLongitude(Double.parseDouble(String.valueOf(longg)));
                        locationB.setLatitude(Double.parseDouble(String.valueOf(locationTrackerCallReportModels.get(i).getLat())));
                        locationB.setLongitude(Double.parseDouble(String.valueOf(locationTrackerCallReportModels.get(i).getLng())));

                        str = Double.valueOf(locationA.distanceTo(locationB) / 1000);
                        // Log.e(TAG, "onResponse: " + str);

                        if (str <= 1.0) {




                            // ************** Time Check ************** //

                            // String TimeCheck = response.body().getUpcomingCallListModels().get(i).getDate();
                            // TimeCheck = TimeCheck.substring(11); 28-02-2023 10:00:00
                            // Log.e(TAG, "onResponse: " + TimeCheck);
                            String TimeCheck = "02-03-2023 18:00:00";

                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());
                            //Log.e(TAG, "onResponse: "+currentDateandTime );


                            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());

                            Date TimeDate;
                            Date CurrentTimeDate;
                            TimeDate = format.parse(TimeCheck);
                            CurrentTimeDate = format.parse(currentDateandTime);


                            long diff = TimeDate.getTime() - CurrentTimeDate.getTime();
                            long diffSeconds = diff / 1000;
                            long diffMinutes = diff / (60 * 1000);
                            long diffHours = diff / (60 * 60 * 1000);
                            Log.e(TAG, "onResponse: check " + diffHours +"---"+diffMinutes);

                            if (diffMinutes <= 60 ) {
                                Log.e(TAG, "TimeCheck: in " + diffMinutes);

                                scheduleNotification(getNotification("text"));

                            }else {
                                Log.e(TAG, "TimeCheck: " + diffMinutes);
                            }
                            // ************** Time Check End ************** //


                        } else {

                            Log.e(TAG, "onResponse: not reached");
                        }







                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<UpcomingCallListResponse> call, Throwable t) {

            }
        });

    }

    private void scheduleNotification(Notification notification) {
        Intent notificationIntent = new Intent(activity, BroadCastReceiver.class);
        notificationIntent.putExtra(BroadCastReceiver.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(BroadCastReceiver.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, 1, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
        long futureInMillis = SystemClock.elapsedRealtime();
        AlarmManager alarmManager = (AlarmManager)activity.getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 1000, pendingIntent);
    }

    private Notification getNotification(String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity, default_notification_channel_id);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setAutoCancel(true);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        return builder.build();
    }
}
