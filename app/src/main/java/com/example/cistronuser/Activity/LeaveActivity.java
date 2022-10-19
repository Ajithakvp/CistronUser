package com.example.cistronuser.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cistronuser.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class LeaveActivity extends Activity {


    //Bottom
    TextView tvDate;
    Spinner spReson,tvLeaveType,tvDayType;


    ImageView ivBack,ivAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);


        ivAdd=findViewById(R.id.ivMore);
        ivBack=findViewById(R.id.ivBack);

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallLeave();
            }
        });
    }

    private void CallLeave() {

        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.leave_from);
        bottomSheetDialog.show();
    }
}