package com.example.cistronuser.Common;

import android.app.Notification;

import androidx.core.app.NotificationManagerCompat;

import com.example.cistronuser.Activity.DashboardActivity;
import com.example.cistronuser.R;

public class Utilites {



    // ********** Toggle eye view visible or invisible  ********** //
    // password visible
    // edPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
    // password invisible
    // edPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
    // ********** Toggle eye view visible or invisible end  ********** //

    // *********** Screen onPause close Tab *********** //
    //            android:excludeFromRecents="true"
    //            android:exported="true"
    //            android:launchMode="singleTask"
    //            android:taskAffinity=""
    // *********** Screen onPause close Tab end *********** //

    // final Calendar c = Calendar.getInstance();
    //        c.set(Calendar.HOUR, c.get(Calendar.HOUR) - 1);
    //        int mHour = c.get(Calendar.HOUR);
    //        int mMinute = c.get(Calendar.MINUTE);
    //        String time= mHour+":"+mMinute ;


    // ********** Distance Notication Meter  ********* //

//                        Location locationA = new Location("Location A");
//                        Location locationB = new Location("Location B");
//                        locationA.setLatitude(Double.parseDouble(String.valueOf(10.80427879999)));
//                        locationA.setLongitude(Double.parseDouble(String.valueOf(78.7351327)));
//                        locationB.setLatitude(Double.parseDouble(String.valueOf(10.80427879999)));
//                        locationB.setLongitude(Double.parseDouble(String.valueOf(78.7351327)));
//                        // Log.e(TAG, "onResponse: " + "Distance : "+ locationA.distanceTo(locationB)/1000);
//
//                        Double str= Double.valueOf(locationA.distanceTo(locationB)/1000);
//                        if (str<=1.0){
//                            Log.e(TAG, "onResponse: " + "Distance in : "+str);
//                        }else {
//                            Log.e(TAG, "onResponse: " + "Distance out : "+str);
//                        }


    // ********** Distance Notication Meter End  ********* //


    // ******************* Top Notfication ***************** //
//    Notification newMessageNotification = null;
//                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//        newMessageNotification = new Notification.Builder(context, "Cistron")
//                .setSmallIcon(R.drawable.cis_logo_login)
//                .setContentTitle("Call Reporting Alert !")
//                .setContentText("You have a scheduled appointment at " + Hosp[0] + "\n" + locationTrackerCallReportModels.get(i).getAddress() + "\n"
//                        + "for " + locationTrackerCallReportModels.get(i).getDate())
//                .setAutoCancel(true)
//                .build();
//    }
//
//    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(DashboardActivity.this);
//                                notificationManager.notify(1, newMessageNotification);

    // ******************* Top Notfication End ***************** //

}
