package com.example.cistronuser.ServiceEngineer.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.FeedbackFileUploadInterface;
import com.example.cistronuser.API.Response.FeedbackFileUploadResponse;
import com.example.cistronuser.Common.FileUtli;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fileupload_fb extends AppCompatActivity {

    TextView tvfilename,tvfileSubmit;
    RelativeLayout rlUpload;
    WebView Webview;
    String strCusfbAttach;
    File filefb;
    String cid,hid,pid,serlno,sid,uid,currentDateandTime;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fileupload_fb);

        tvfileSubmit=findViewById(R.id.tvfileSubmit);
        tvfilename=findViewById(R.id.tvfilename);
        rlUpload=findViewById(R.id.rlUpload);
        ivBack=findViewById(R.id.ivBack);

        cid=getIntent().getStringExtra("cid");
        hid=getIntent().getStringExtra("hid");
        pid=getIntent().getStringExtra("pid");
        serlno=getIntent().getStringExtra("serlno");
        sid=getIntent().getStringExtra("sid");



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss", Locale.getDefault());
        currentDateandTime = sdf.format(new Date());
        uid= PreferenceManager.getEmpID(this);




        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        //Toast.makeText(this, "---cid-"+cid+"---pid-"+pid+"---hid-"+hid+"---serl-"+serlno+"---sid"+sid, Toast.LENGTH_SHORT).show();

        //Log.d("text", "onCreate: "+"---cid-"+cid+"---pid-"+pid+"---hid-"+hid+"---serl-"+serlno+"---sid"+sid);


        tvfileSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(Fileupload_fb.this,R.style.ProgressBarDialog);
                progressDialog.setMessage("Please Wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                FeedbackFileUploadInterface feedbackFileUploadInterface= APIClient.getClient().create(FeedbackFileUploadInterface.class);
                RequestBody action=RequestBody.create(MediaType.parse("text/plain"),"fileupload");
                RequestBody empid=RequestBody.create(MediaType.parse("text/plain"),PreferenceManager.getEmpID(Fileupload_fb.this));
                RequestBody hospid=RequestBody.create(MediaType.parse("text/plain"),hid.toString());
                RequestBody sidn=RequestBody.create(MediaType.parse("text/plain"),sid.toString());
                RequestBody pro_id=RequestBody.create(MediaType.parse("text/plain"),pid.toString());
                RequestBody catid=RequestBody.create(MediaType.parse("text/plain"),cid.toString());
                RequestBody crntdate=RequestBody.create(MediaType.parse("text/plain"),currentDateandTime);
                RequestBody fileupload=RequestBody.create(MediaType.parse("multipart/form-data"),filefb);
                MultipartBody.Part fileup=MultipartBody.Part.createFormData("fileName",filefb.getName(),fileupload);
                feedbackFileUploadInterface.call(action,hospid,pro_id,sidn,empid,catid,crntdate,fileup).enqueue(new Callback<FeedbackFileUploadResponse>() {
                    @Override
                    public void onResponse(Call<FeedbackFileUploadResponse> call, Response<FeedbackFileUploadResponse> response) {
                        if(response.isSuccessful()){
                            progressDialog.dismiss();

                            AlertDialog.Builder alert = new AlertDialog.Builder(Fileupload_fb.this,R.style.AlertDialogCustom);
                            alert.setMessage(response.body().getStatus());
                           alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            progressDialog.dismiss();
                            overridePendingTransition(0, 0);
                           onBackPressed();
                          overridePendingTransition(0, 0);
                           finish();

                           }
                      });

                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();

                    }
                        }





                    @Override
                    public void onFailure(Call<FeedbackFileUploadResponse> call, Throwable t) {
                        progressDialog.dismiss();

                    }
                });



            }
        });



        rlUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 1);
                } catch (Exception e) {

                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    Log.e("pdf", "onActivityResult: "+contentUri );
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strCusfbAttach = timeStamp + "." + getFileExt(contentUri);
                    //Toast.makeText(this, "File Name" + strCusfbAttach, Toast.LENGTH_SHORT).show();


                    try {
                        filefb = FileUtli.from(this, contentUri);
                        Log.e("pdf1", "onActivityResult: "+filefb );

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    long mb = filefb.length();
                    mb = mb / 1024;

                    try {
                        if (mb < 5120) {
                            String myStr = strCusfbAttach;
                            tvfilename.setError(null);
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            System.out.println(extension);
                            if (extension.equals(".pdf")) {
                                tvfilename.setText(strCusfbAttach);
                            }  else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage("Please Select Pdf File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }

                        } else {
                            tvfilename.setError("Sorry, your file is too large. Upload files up to 5 MB in size below.");
                            tvfilename.requestFocus();
                        }

                    } catch (Exception e) {

                    }


                }
                break;
        }
    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }
}