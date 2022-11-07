package com.example.cistronuser.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import static java.text.DateFormat.DEFAULT;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.LinkMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.LoginInterFace;
import com.example.cistronuser.API.Interface.ProfileUserDataInterface;
import com.example.cistronuser.API.Model.LoginuserModel;
import com.example.cistronuser.API.Response.LoginResponse;
import com.example.cistronuser.API.Response.LoginUserResponse;
import com.example.cistronuser.Adapter.ProfileAdapter;
import com.example.cistronuser.Common.ConnectionRecevier;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.LoginActivity;
import com.example.cistronuser.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Blob;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends Activity {



    private static final String TAG="DashBoard";
    //Internet

    BroadcastReceiver broadcastReceiver;

    ImageView ivBack, ivProfilePhoto;
    RelativeLayout rlProfile;
    CardView cvAttendance, cvExpense, cvLeave;


    //Bottom
    TextView tvName, tvEmpId, tvDesignation, tvBranch, tvTeamLeader, tvMobile, tvEmail, tvDob, tvDoj, lWebview,tvProfilename;


    LottieAnimationView lottieAnimationView, ivprofile;
    //Gps


    String strPass,Profile;
    String Name,EmpiD,Des,DOB,DOJ,TL,Mob,Branch,Email,strPhoto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        PreferenceManager.setLoggedStatus(this,true);
        cvLeave = findViewById(R.id.cvLeave);
        cvExpense = findViewById(R.id.cvExpense);
        cvAttendance = findViewById(R.id.cvAttendance);
        rlProfile = findViewById(R.id.rlProfile);
        lottieAnimationView = findViewById(R.id.ivLogout);
        ivprofile = findViewById(R.id.ivprofile);
        lWebview = findViewById(R.id.lWebview);
        tvProfilename=findViewById(R.id.tvProfilename);

        Profile=getIntent().getStringExtra("Name");
        tvProfilename.setText(Profile);

        lWebview.setMovementMethod(LinkMovementMethod.getInstance());
        lWebview.setLinkTextColor(getResources().getColor(R.color.white));



        //internet
        broadcastReceiver = new ConnectionRecevier();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
                builder.setMessage("Do you want to LogOut");
                builder.setTitle("Log Out!");
                builder.setIcon(R.drawable.logout);
                builder.setCancelable(false);
                builder.setPositiveButton("yes", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       PreferenceManager.setLoggedStatus(DashboardActivity.this, false);
                        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }));
                builder.setNegativeButton("No", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }));
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });


        cvAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent attend = new Intent(DashboardActivity.this, AttendanceActivity.class);
                startActivity(attend);
            }
        });
        cvExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent expense = new Intent(DashboardActivity.this, ExpensesActivity.class);
                startActivity(expense);
            }
        });

        cvLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, LeaveActivity.class);
                startActivity(intent);
            }
        });


        ivprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileView();
            }
        });


    }





    private void ProfileView() {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.profileview);
        bottomSheetDialog.show();

        tvDoj = bottomSheetDialog.findViewById(R.id.tvDoj);
        tvName = bottomSheetDialog.findViewById(R.id.tvName);
        tvEmpId = bottomSheetDialog.findViewById(R.id.tvEmpId);
        tvDesignation = bottomSheetDialog.findViewById(R.id.tvDesignation);
        tvBranch = bottomSheetDialog.findViewById(R.id.tvBranch);
        tvTeamLeader = bottomSheetDialog.findViewById(R.id.tvTeamLeader);
        tvMobile = bottomSheetDialog.findViewById(R.id.tvMobile);
        tvEmail = bottomSheetDialog.findViewById(R.id.tvEmail);
        tvDob = bottomSheetDialog.findViewById(R.id.tvDob);
        ivBack = bottomSheetDialog.findViewById(R.id.ivBack);
        ivProfilePhoto = bottomSheetDialog.findViewById(R.id.ivProfilePhoto);

        strPass=getIntent().getStringExtra("Pass");


        Name=getIntent().getStringExtra("Name");
        EmpiD=getIntent().getStringExtra("EmpID");
        Des=getIntent().getStringExtra("Des");
        DOB=getIntent().getStringExtra("DOB");
        DOJ=getIntent().getStringExtra("DOJ");
        Branch=getIntent().getStringExtra("Branch");
        Email=getIntent().getStringExtra("Email");
        Mob=getIntent().getStringExtra("Mob");
        TL=getIntent().getStringExtra("TL");
        strPhoto=getIntent().getStringExtra("Photo");


        tvName.setText(Name);
        tvEmpId.setText(EmpiD);
        tvBranch.setText(Branch);
        tvDesignation.setText(Des);
        tvDob.setText(DOB);
        tvDoj.setText(DOJ);
        tvEmail.setText(Email);
        tvMobile.setText(Mob);
        tvTeamLeader.setText(TL);


        try {
            byte[] decodedString = Base64.decode(strPhoto, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            ivProfilePhoto.setImageBitmap(decodedByte);
        } catch (Exception e) {
            e.printStackTrace();
        }



       // Callprofile();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
    }

    private void Callprofile() {
      ProfileUserDataInterface profileUserDataInterface=APIClient.getClient().create(ProfileUserDataInterface.class);
      profileUserDataInterface.CalluserData(PreferenceManager.getEmpID(this),strPass,"","").enqueue(new Callback<LoginuserModel>() {
          @Override
          public void onResponse(Call<LoginuserModel> call, Response<LoginuserModel> response) {
             try{
                 if (response.isSuccessful()){
                     tvName.setText(response.body().getName());
                     tvEmpId.setText(response.body().getEmpid());
                     tvBranch.setText(response.body().getBranch());
                     tvDesignation.setText(response.body().getDesignation());
                     tvDob.setText(response.body().getDob());
                     tvDoj.setText(response.body().getDoj());
                     tvMobile.setText(response.body().getMobile());
                     tvEmail.setText(response.body().getEmail());
                     tvTeamLeader.setText(response.body().getTeamleader());
                     tvProfilename.setText(response.body().getName());
                 }else {
                     tvName.setText(response.body().getName());
                     tvEmpId.setText(response.body().getEmpid());
                     tvBranch.setText(response.body().getBranch());
                     tvDesignation.setText(response.body().getDesignation());
                     tvDob.setText(response.body().getDob());
                     tvDoj.setText(response.body().getDoj());
                     tvMobile.setText(response.body().getMobile());
                     tvEmail.setText(response.body().getEmail());
                     tvTeamLeader.setText(response.body().getTeamleader());
                     tvProfilename.setText(response.body().getName());
                 }

             }catch (Exception e){}
          }


          @Override
          public void onFailure(Call<LoginuserModel> call, Throwable t) {

          }
      });
    }

    protected void unregBroadcast() {
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {


        super.onDestroy();
        unregBroadcast();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 &&requestCode==RESULT_OK && data!=null){
            Uri uri=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                byte[] bytes=byteArrayOutputStream.toByteArray();
                strPhoto= Base64.encodeToString(bytes,Base64.DEFAULT);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}