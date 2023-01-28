package com.example.cistronuser.Common;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Activity.PendicallActivity;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class ConnectionRecevier extends BroadcastReceiver {

    Context mcontext;

    @Override
    public void onReceive(Context context, Intent intent) {




        if (isConnected(context)) {

        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View view = LayoutInflater.from(context).inflate(R.layout.network, null);
            builder.setView(view);
            AlertDialog alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.show();

        }
    }
    private boolean isConnected(Context context) {

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }

    }





    // BarCodeView

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

}
