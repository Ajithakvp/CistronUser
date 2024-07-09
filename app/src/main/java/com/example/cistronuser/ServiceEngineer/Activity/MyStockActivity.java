package com.example.cistronuser.ServiceEngineer.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.DeleteSwaplistInterface;
import com.example.cistronuser.API.Interface.MyStockSEInterface;
import com.example.cistronuser.API.Interface.MyStockSESearchInteface;
import com.example.cistronuser.API.Interface.ServiceEnguserInterface;
import com.example.cistronuser.API.Interface.SubmitswapuserInterface;
import com.example.cistronuser.API.Interface.SwapListengInterface;
import com.example.cistronuser.API.Interface.ViewSparesInwardDetailsInterface;
import com.example.cistronuser.API.Model.MyStockSEModel;
import com.example.cistronuser.API.Model.MyStockSESearchModel;
import com.example.cistronuser.API.Model.ServiceEnguserModel;
import com.example.cistronuser.API.Model.TransportModel;
import com.example.cistronuser.API.Response.DeleteSwaplistResponse;
import com.example.cistronuser.API.Response.MyStockSEResponse;
import com.example.cistronuser.API.Response.MyStockSESearchResponse;
import com.example.cistronuser.API.Response.ServiceEnguserResponse;
import com.example.cistronuser.API.Response.SubmitswapuserResponse;
import com.example.cistronuser.API.Response.SwapListengResponse;
import com.example.cistronuser.API.Response.ViewSparesInwardDetailsResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Adapter.MyStockAdapter;
import com.example.cistronuser.ServiceEngineer.Adapter.MyStockSearchAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyStockActivity extends AppCompatActivity {


    RecyclerView rvMystockList;
    ImageView ivBackl, ivlist;
    EditText edSearch;
    ArrayList<MyStockSEModel> myStockSEModels = new ArrayList<>();
    MyStockAdapter myStockAdapter;

    //Search List
    RecyclerView rvMystockSearchList;
    MyStockSearchAdapter myStockSearchAdapter;
    ArrayList<MyStockSESearchModel> myStockSESearchModels = new ArrayList<>();


    //FAB Button design
    ExtendedFloatingActionButton mAddFab;
    TextView tvswapview;
    FloatingActionButton fabswap;
    ConstraintLayout conmove;

    Boolean isAllFabsVisible;


    //Swap Eng
    ImageView ivBackd, ivdelete;
    TextView tvPartName, tvPartno, tvUnitprice, tvMyqty, tvDatetime, tvSubmit;
    EditText edQty2out, edRef;
    Spinner speng, spModeOfTransport;
    ArrayAdapter engAdapter;
    ArrayList<ServiceEnguserModel> serviceEnguserModels = new ArrayList<>();
    ArrayList<String> streng = new ArrayList<>();
    String Enguid;

    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

    ArrayAdapter spmodeadapter;
    ArrayList<String> strModelist = new ArrayList<>();
    String TransportID;
    ArrayList<TransportModel> transportModels = new ArrayList<>();


    //Swap eng End

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stock);

        rvMystockList = findViewById(R.id.rvMystockList);
        ivBackl = findViewById(R.id.ivBack);
        edSearch = findViewById(R.id.edSearch);
        rvMystockSearchList = findViewById(R.id.rvMystockSearchList);
        mAddFab = findViewById(R.id.add_fab);
        tvswapview = findViewById(R.id.tvswapview);
        fabswap = findViewById(R.id.fabswap);
        conmove = findViewById(R.id.conmove);

//        ivlist=findViewById(R.id.ivlist);


        fabswap.setVisibility(View.GONE);
        tvswapview.setVisibility(View.GONE);

        isAllFabsVisible = false;
        mAddFab.shrink();

        mAddFab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                v.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        switch (event.getActionMasked()) {
                            case MotionEvent.ACTION_MOVE:
                                view.setX(event.getRawX() - 120);
                                view.setY(event.getRawY() - 425);
                                break;
                            case MotionEvent.ACTION_UP:
                                view.setOnTouchListener(null);
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
                return true;
            }
        });
        fabswap.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                v.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        switch (event.getActionMasked()) {
                            case MotionEvent.ACTION_MOVE:
                                view.setX(event.getRawX() - 120);
                                view.setY(event.getRawY() - 425);
                                break;
                            case MotionEvent.ACTION_UP:
                                view.setOnTouchListener(null);
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
                return true;
            }
        });

        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAllFabsVisible) {
                    fabswap.show();
                    tvswapview.setVisibility(View.VISIBLE);
                    mAddFab.extend();
                    isAllFabsVisible = true;
                } else {
                    fabswap.hide();
                    tvswapview.setVisibility(View.GONE);
                    mAddFab.shrink();
                    isAllFabsVisible = false;
                }
            }
        });

        fabswap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallSwapList();

            }
        });

        ivBackl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        CallMystockList();
        myStockAdapter = new MyStockAdapter(this, myStockSEModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvMystockList.setLayoutManager(linearLayoutManager);
        rvMystockList.setAdapter(myStockAdapter);


        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (edSearch.getText().toString().length() > 0) {
                    rvMystockSearchList.setVisibility(View.VISIBLE);
                    rvMystockList.setVisibility(View.GONE);
                    CallMyStockSearch();
                    myStockSearchAdapter = new MyStockSearchAdapter(myStockSESearchModels, MyStockActivity.this);
                    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(MyStockActivity.this);
                    linearLayoutManager1.setOrientation(RecyclerView.VERTICAL);
                    rvMystockSearchList.setLayoutManager(linearLayoutManager1);
                    rvMystockSearchList.setAdapter(myStockSearchAdapter);
                } else {
                    rvMystockSearchList.setVisibility(View.GONE);
                    rvMystockList.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void CallSwapList() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SwapListengInterface swapListengInterface = APIClient.getClient().create(SwapListengInterface.class);
        swapListengInterface.view(PreferenceManager.getEmpID(this)).enqueue(new Callback<SwapListengResponse>() {
            @Override
            public void onResponse(Call<SwapListengResponse> call, Response<SwapListengResponse> response) {
                try {

                    if (response.isSuccessful()) {


                        if (response.body().getResponse().trim().equals("0")) {
                            progressDialog.dismiss();
                            Toast.makeText(MyStockActivity.this, "No swap Spare", Toast.LENGTH_SHORT).show();
                        } else {

                            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MyStockActivity.this, R.style.AppBottomSheetDialogTheme);
                            bottomSheetDialog.setContentView(R.layout.swapuserdialogadapter);
                            bottomSheetDialog.setCancelable(false);
                            bottomSheetDialog.show();

                            ivBackd = bottomSheetDialog.findViewById(R.id.ivBack);
                            ivdelete = bottomSheetDialog.findViewById(R.id.ivdelete);
                            tvPartName = bottomSheetDialog.findViewById(R.id.tvPartName);
                            tvPartno = bottomSheetDialog.findViewById(R.id.tvPartno);
                            tvUnitprice = bottomSheetDialog.findViewById(R.id.tvUnitprice);
                            tvMyqty = bottomSheetDialog.findViewById(R.id.tvMyqty);
                            edQty2out = bottomSheetDialog.findViewById(R.id.edQty2out);
                            speng = bottomSheetDialog.findViewById(R.id.speng);
                            spModeOfTransport = bottomSheetDialog.findViewById(R.id.spModeOfTransport);
                            tvDatetime = bottomSheetDialog.findViewById(R.id.tvDatetime);
                            tvSubmit = bottomSheetDialog.findViewById(R.id.tvSubmit);
                            edRef = bottomSheetDialog.findViewById(R.id.edRef);


                            edQty2out.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                }

                                @Override
                                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                    try {
                                        long myqty = Long.parseLong(response.body().getSwapListengModels().get(0).getQty());
                                        long qtyout = Long.parseLong(edQty2out.getText().toString().trim());


                                        if ((qtyout) > (myqty)) {
                                            tvSubmit.setVisibility(View.GONE);
                                            edQty2out.setError("Please Check  MyQty");

                                        } else {
                                            tvSubmit.setVisibility(View.VISIBLE);
                                            edQty2out.setError(null);


                                        }

                                    } catch (Exception e) {

                                    }
                                }

                                @Override
                                public void afterTextChanged(Editable editable) {


                                }
                            });


                            ivdelete.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MyStockActivity.this, R.style.AlertDialogCustom);
                                    builder.setMessage("Are you sure you want to Delete ?");
                                    builder.setTitle("cancel!");
                                    builder.setIcon(R.drawable.ic_baseline_cancel_24);
                                    builder.setCancelable(false);
                                    builder.setPositiveButton("yes", (new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            final ProgressDialog progressDialog = new ProgressDialog(MyStockActivity.this, R.style.ProgressBarDialog);
                                            progressDialog.setMessage("Loading...");
                                            progressDialog.setCancelable(false);
                                            progressDialog.show();
                                            DeleteSwaplistInterface deleteSwaplistInterface = APIClient.getClient().create(DeleteSwaplistInterface.class);
                                            deleteSwaplistInterface.delete(response.body().getSwapListengModels().get(0).getId()).enqueue(new Callback<DeleteSwaplistResponse>() {
                                                @Override
                                                public void onResponse(Call<DeleteSwaplistResponse> call, Response<DeleteSwaplistResponse> response) {
                                                    try {
                                                        if (response.isSuccessful()) {
                                                            if (response.body().getResponsechk().trim().equals("1")) {
                                                                progressDialog.dismiss();
                                                                Toast.makeText(MyStockActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                                                MyStockActivity.this.overridePendingTransition(0, 0);
                                                                MyStockActivity.this.startActivity(MyStockActivity.this.getIntent());
                                                                MyStockActivity.this.overridePendingTransition(0, 0);
                                                                MyStockActivity.this.finish();
                                                            }
                                                        }

                                                    } catch (Exception e) {

                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<DeleteSwaplistResponse> call, Throwable t) {

                                                }
                                            });


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


                            tvDatetime.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Calendar calendar = Calendar.getInstance();
                                    int year = calendar.get(Calendar.YEAR);
                                    int month = calendar.get(Calendar.MONTH);
                                    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                                    DatePickerDialog datePickerDialog = new DatePickerDialog(MyStockActivity.this, new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                            String moth, dt;

                                            moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                                            dt = (day > 9) ? "" + day : ("0" + day);


                                            String strDate = year + "-" + moth + "-" + dt;
                                            String dateandtime = strDate + " " + currentTime;
                                            tvDatetime.setText(dateandtime);

                                        }

                                    }, year, month, dayOfMonth);

                                    datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());


                                    datePickerDialog.show();

                                }
                            });

                            Callenglist();
                            engAdapter = new ArrayAdapter(MyStockActivity.this, R.layout.spinner_item, streng);
                            engAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
                            speng.setAdapter(engAdapter);

                            speng.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    Enguid = serviceEnguserModels.get(i).getUid();

                                    //  Toast.makeText(MyStockActivity.this, Enguid, Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });


                            CallViewList(response.body().getSwapListengModels().get(0).getPart_id());
                            spmodeadapter = new ArrayAdapter(MyStockActivity.this, R.layout.spinner_item, strModelist);
                            spmodeadapter.setDropDownViewResource(R.layout.spinner_dropdown);
                            spModeOfTransport.setAdapter(spmodeadapter);
                            spModeOfTransport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    TransportID = transportModels.get(i).getId();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });


                            ivBackd.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    bottomSheetDialog.dismiss();
                                }
                            });

                            tvSubmit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    try {
                                        if (tvDatetime.getText().toString().trim().equals("")) {
                                            Toast.makeText(MyStockActivity.this, "Please Select a date ", Toast.LENGTH_SHORT).show();
                                        } else if (edRef.getText().toString().trim().equals("")) {
                                            Toast.makeText(MyStockActivity.this, "Please enter the reference ", Toast.LENGTH_SHORT).show();

                                        } else {
                                            CallSubmit(TransportID, response.body().getSwapListengModels().get(0).getId(), response.body().getSwapListengModels().get(0).getPart_id(), bottomSheetDialog, response.body().getSwapListengModels().get(0).getOpt());
                                        }

                                    } catch (Exception e) {

                                    }
                                }
                            });

                            tvPartName.setText(response.body().getSwapListengModels().get(0).getName());
                            tvPartno.setText(response.body().getSwapListengModels().get(0).getPart_no());
                            tvMyqty.setText(response.body().getSwapListengModels().get(0).getQty());
                            tvUnitprice.setText(response.body().getSwapListengModels().get(0).getPrice());
                            tvPartName.setText(response.body().getSwapListengModels().get(0).getName());

                            progressDialog.dismiss();
                        }
                    }
                } catch (Exception e) {
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<SwapListengResponse> call, Throwable t) {

            }
        });
    }


    private void CallSubmit(String transportID, String id, String partId, BottomSheetDialog bottomSheetDialog, String opt) {
        final ProgressDialog progressDialog = new ProgressDialog(MyStockActivity.this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SubmitswapuserInterface submitswapuserInterface = APIClient.getClient().create(SubmitswapuserInterface.class);
        submitswapuserInterface.submit(opt, transportID, partId, edQty2out.getText().toString(), id, tvDatetime.getText().toString(), Enguid, edRef.getText().toString(), PreferenceManager.getEmpID(this)).enqueue(new Callback<SubmitswapuserResponse>() {
            @Override
            public void onResponse(Call<SubmitswapuserResponse> call, Response<SubmitswapuserResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getResponse().trim().equals("1")) {
                            Toast.makeText(MyStockActivity.this, "Submitted", Toast.LENGTH_SHORT).show();
                            bottomSheetDialog.dismiss();
                            progressDialog.dismiss();
                        }

                    }
                } catch (Exception e) {
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<SubmitswapuserResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }

    private void Callenglist() {
        final ProgressDialog progressDialog = new ProgressDialog(MyStockActivity.this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ServiceEnguserInterface serviceEnguserInterface = APIClient.getClient().create(ServiceEnguserInterface.class);
        serviceEnguserInterface.user(PreferenceManager.getEmpID(MyStockActivity.this)).enqueue(new Callback<ServiceEnguserResponse>() {
            @Override
            public void onResponse(Call<ServiceEnguserResponse> call, Response<ServiceEnguserResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        streng.clear();
//                        streng.add(0,"Select a engineers");
                        serviceEnguserModels = response.body().getServiceEnguserModels();
                        for (int i = 0; i < serviceEnguserModels.size(); i++) {
                            streng.add(serviceEnguserModels.get(i).getEngname());
                        }
                        engAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }

                } catch (Exception e) {
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<ServiceEnguserResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }

    private void CallViewList(String reqId) {
        final ProgressDialog progressDialog = new ProgressDialog(MyStockActivity.this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ViewSparesInwardDetailsInterface viewSparesInwardDetailsInterface = APIClient.getClient().create(ViewSparesInwardDetailsInterface.class);
        viewSparesInwardDetailsInterface.CallviewSparesInwardDetail("viewSparesInwardDetail", "1", reqId).enqueue(new Callback<ViewSparesInwardDetailsResponse>() {
            @Override
            public void onResponse(Call<ViewSparesInwardDetailsResponse> call, Response<ViewSparesInwardDetailsResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();


                        // ************** Transport SPinner ************* //
                        strModelist.clear();

                        transportModels = response.body().getTransportModels();
                        for (int i = 0; i < transportModels.size(); i++) {
                            strModelist.add(transportModels.get(i).getText());

                        }
                        spmodeadapter.notifyDataSetChanged();

                        // ************** Transport SPinner End ************* //


                    }

                } catch (Exception e) {
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ViewSparesInwardDetailsResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }


    private void CallMyStockSearch() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        MyStockSESearchInteface myStockSESearchInteface = APIClient.getClient().create(MyStockSESearchInteface.class);
        myStockSESearchInteface.CallMystock("myStock", PreferenceManager.getEmpID(this), edSearch.getText().toString()).enqueue(new Callback<MyStockSESearchResponse>() {
            @Override
            public void onResponse(Call<MyStockSESearchResponse> call, Response<MyStockSESearchResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        myStockSearchAdapter.myStockListSEModels = response.body().getMyStockSESearchModels();
                        myStockSearchAdapter.searchAdapter(response.body().getMyStockSESearchModels());
                        myStockSearchAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<MyStockSESearchResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void CallMystockList() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("My Stock List...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        MyStockSEInterface myStockSEInterface = APIClient.getClient().create(MyStockSEInterface.class);
        myStockSEInterface.CallMystock("getSeriesData", PreferenceManager.getEmpID(this)).enqueue(new Callback<MyStockSEResponse>() {
            @Override
            public void onResponse(Call<MyStockSEResponse> call, Response<MyStockSEResponse> response) {

                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        myStockAdapter.myStockSEModels = response.body().getMyStockSEModels();
                        myStockAdapter.notifyDataSetChanged();

                    }

                } catch (Exception e) {
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<MyStockSEResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }
}