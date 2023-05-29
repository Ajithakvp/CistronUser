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


    // ********** Distance Notification Meter  ********* //

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


    // ********** Distance Notification Meter End  ********* //


    // ******************* Top Notification ***************** //
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

    // ******************* Top Notification End ***************** //





    // **************** BarCodeView **************** //

// SurfaceView surfaceView;
//
//    TextView txtBarcodeValue;
//
//    private BarcodeDetector barcodeDetector;
//    private CameraSource cameraSource;
//    private static final int REQUEST_CAMERA_PERMISSION = 201;
//    Button btnAction;
//    String intentData = "";
    //   surfaceView=findViewById(R.id.surfaceView);
    //        txtBarcodeValue=findViewById(R.id.txtBarcodeValue);
    //        btnAction = findViewById(R.id.btnAction);
    //
    //        btnAction.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                if (intentData.length() > 0) {
    //                    startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse(intentData)));
    //                }
    //            }
    //        });

//    private void initialiseDetectorsAndSources() {
//
//        // Toast.makeText(getApplicationContext() , "Barcode scanner started" , Toast.LENGTH_SHORT).show();
//        barcodeDetector = new BarcodeDetector.Builder(this)
//                .setBarcodeFormats(Barcode.ALL_FORMATS)
//                .build();
//
//        cameraSource = new CameraSource.Builder(this , barcodeDetector)
//                .setRequestedPreviewSize(1920 , 1080)
//                .setAutoFocusEnabled(true) //you should add this feature
//                .build();
//
//        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder holder) {
//                try {
//                    if (ActivityCompat.checkSelfPermission(PendicallActivity.this , Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                        cameraSource.start(surfaceView.getHolder());
//                    } else {
//                        ActivityCompat.requestPermissions(PendicallActivity.this , new
//                                String[]{Manifest.permission.CAMERA} , REQUEST_CAMERA_PERMISSION);
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder holder , int format , int width , int height) {
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder holder) {
//                cameraSource.stop();
//            }
//        });
//
//
//        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
//            @Override
//            public void release() {
//                //Toast.makeText(getApplicationContext() , "To prevent memory leaks barcode scanner has been stopped" , Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void receiveDetections(Detector.Detections<Barcode> detections) {
//                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
//                if (barcodes.size() != 0) {
//                    txtBarcodeValue.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            // btnAction.setText("LAUNCH URL");
//                            intentData = barcodes.valueAt(0).displayValue;
//
//                            Dialog dialog=new Dialog(PendicallActivity.this);
//                            dialog.setContentView(R.layout.activity_main);
//                            dialog.show();
//                            TextView tvCode=dialog.findViewById(R.id.tvCode);
//                            ImageView ivClose=dialog.findViewById(R.id.ivClose);
//
//                            tvCode.setText(intentData);
//
//
//                            ivClose.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    dialog.dismiss();
//                                }
//                            });
//                            // txtBarcodeValue.setText(intentData);
//                        }
//                    });
//                }
//            }
//        });
//    }
//
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        cameraSource.release();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        initialiseDetectorsAndSources();
//    }
    
    // **************** BarCodeView End**************** //

}
