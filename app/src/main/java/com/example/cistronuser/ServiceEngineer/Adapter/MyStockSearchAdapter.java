package com.example.cistronuser.ServiceEngineer.Adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.DeleteSwaplistInterface;
import com.example.cistronuser.API.Interface.DeletedAPIInterface;
import com.example.cistronuser.API.Interface.ServiceEnguserInterface;
import com.example.cistronuser.API.Interface.SubmitswapuserInterface;
import com.example.cistronuser.API.Interface.SwapListInsertengInterface;
import com.example.cistronuser.API.Interface.ViewSparesInwardDetailsInterface;
import com.example.cistronuser.API.Model.MyStockSESearchModel;
import com.example.cistronuser.API.Model.ServiceEnguserModel;
import com.example.cistronuser.API.Model.TransportModel;
import com.example.cistronuser.API.Response.DeleteResponse;
import com.example.cistronuser.API.Response.DeleteSwaplistResponse;
import com.example.cistronuser.API.Response.ServiceEnguserResponse;
import com.example.cistronuser.API.Response.SubmitswapuserResponse;
import com.example.cistronuser.API.Response.SwapListengResponse;
import com.example.cistronuser.API.Response.ViewSparesInwardDetailsResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Activity.MyStockActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyStockSearchAdapter extends RecyclerView.Adapter<MyStockSearchAdapter.ViewHolder> {

    public ArrayList<MyStockSESearchModel> myStockListSEModels;
    Activity activity;
    //Search
    ArrayList<MyStockSESearchModel> tempMystockList = new ArrayList<>();
    ArrayList<String> strMyStockList = new ArrayList<>();
    public MyStockSearchAdapter(ArrayList<MyStockSESearchModel> myStockListSEModels, Activity activity) {
        this.myStockListSEModels = myStockListSEModels;
        this.activity = activity;
    }

    //Swap Eng
    ImageView ivBack,ivdelete;
    TextView tvPartName,tvPartno,tvUnitprice,tvMyqty,tvDatetime,tvSubmit;
    EditText edQty2out,edRef;
    Spinner speng,spModeOfTransport;
    ArrayAdapter engAdapter;
    ArrayList<ServiceEnguserModel>serviceEnguserModels=new ArrayList<>();
    ArrayList<String>streng=new ArrayList<>();
    String Enguid;

    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

    ArrayAdapter spmodeadapter;
    ArrayList<String>strModelist=new ArrayList<>();
    String TransportID;
    ArrayList<TransportModel>transportModels=new ArrayList<>();


    //Swap eng End

    @NonNull
    @Override
    public MyStockSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mystock_search_adapter, parent, false);
        return new MyStockSearchAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyStockSearchAdapter.ViewHolder holder, int position) {
        holder.tvpartName.setText(myStockListSEModels.get(position).getName());
        holder.tvMyQty.setText(myStockListSEModels.get(position).getQuantity());
        holder.tvPartId.setText(myStockListSEModels.get(position).getId());
        holder.tvPartNo.setText(myStockListSEModels.get(position).getPart_no());
        holder.tvUnitPrice.setText(myStockListSEModels.get(position).getPrice());

        if (myStockListSEModels.get(position).getLabel().trim().equals("cis")) {
            holder.tvPartcisIdName.setVisibility(View.VISIBLE);
            holder.tvPartotherIdName.setVisibility(View.GONE);
            holder.tvPartcsplIdName.setVisibility(View.GONE);

        } else if (myStockListSEModels.get(position).getLabel().trim().equals("cspl")) {
            holder.tvPartcisIdName.setVisibility(View.GONE);
            holder.tvPartotherIdName.setVisibility(View.GONE);
            holder.tvPartcsplIdName.setVisibility(View.VISIBLE);
        } else {
            holder.tvPartcisIdName.setVisibility(View.GONE);
            holder.tvPartotherIdName.setVisibility(View.VISIBLE);
            holder.tvPartcsplIdName.setVisibility(View.GONE);
            holder.tvPartotherIdName.setText(myStockListSEModels.get(position).getLabel());
        }

        String s=myStockListSEModels.get(position).getS();
        String partid=myStockListSEModels.get(position).getId();

        holder.ivswapspare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallswapVieweng(partid,s);


            }
        });
    }

    private void CallswapVieweng(String partid, String s) {
        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SwapListInsertengInterface swapListInsertengInterface= APIClient.getClient().create(SwapListInsertengInterface.class);
        swapListInsertengInterface.view(PreferenceManager.getEmpID(activity),partid,"1").enqueue(new Callback<SwapListengResponse>() {
            @Override
            public void onResponse(Call<SwapListengResponse> call, Response<SwapListengResponse> response) {
                try {

                    if (response.isSuccessful()) {

                        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(activity,R.style.AppBottomSheetDialogTheme);
                        bottomSheetDialog.setContentView(R.layout.swapuserdialogadapter);
                        bottomSheetDialog.setCancelable(false);
                        bottomSheetDialog.show();

                        ivBack=bottomSheetDialog.findViewById(R.id.ivBack);
                        ivdelete=bottomSheetDialog.findViewById(R.id.ivdelete);
                        tvPartName=bottomSheetDialog.findViewById(R.id.tvPartName);
                        tvPartno=bottomSheetDialog.findViewById(R.id.tvPartno);
                        tvUnitprice=bottomSheetDialog.findViewById(R.id.tvUnitprice);
                        tvMyqty=bottomSheetDialog.findViewById(R.id.tvMyqty);
                        edQty2out=bottomSheetDialog.findViewById(R.id.edQty2out);
                        speng=bottomSheetDialog.findViewById(R.id.speng);
                        spModeOfTransport=bottomSheetDialog.findViewById(R.id.spModeOfTransport);
                        tvDatetime=bottomSheetDialog.findViewById(R.id.tvDatetime);
                        tvSubmit=bottomSheetDialog.findViewById(R.id.tvSubmit);
                        edRef=bottomSheetDialog.findViewById(R.id.edRef);



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
                                AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.AlertDialogCustom);
                                builder.setMessage("Are you sure you want to Delete ?");
                                builder.setTitle("cancel!");
                                builder.setIcon(R.drawable.ic_baseline_cancel_24);
                                builder.setCancelable(false);
                                builder.setPositiveButton("yes", (new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        final ProgressDialog progressDialog = new ProgressDialog(activity,R.style.ProgressBarDialog);
                                        progressDialog.setMessage("Loading...");
                                        progressDialog.setCancelable(false);
                                        progressDialog.show();
                                        DeleteSwaplistInterface deleteSwaplistInterface=APIClient.getClient().create(DeleteSwaplistInterface.class);
                                        deleteSwaplistInterface.delete(response.body().getSwapListengModels().get(0).getId()).enqueue(new Callback<DeleteSwaplistResponse>() {
                                            @Override
                                            public void onResponse(Call<DeleteSwaplistResponse> call, Response<DeleteSwaplistResponse> response) {
                                                try {
                                                    if (response.isSuccessful()){
                                                        if (response.body().getResponsechk().trim().equals("1")){
                                                            progressDialog.dismiss();
                                                            Toast.makeText(activity, "Deleted", Toast.LENGTH_SHORT).show();
                                                            activity.overridePendingTransition(0, 0);
                                                            activity.startActivity(activity.getIntent());
                                                            activity.overridePendingTransition(0, 0);
                                                            activity.finish();
                                                        }
                                                    }

                                                }catch (Exception e){

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
                                DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                        String moth, dt;

                                        moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                                        dt = (day > 9) ? "" + day : ("0" + day);


                                        String strDate = year + "-" + moth + "-" + dt;
                                        String dateandtime=strDate + " " + currentTime;
                                        tvDatetime.setText(dateandtime);

                                    }

                                }, year, month, dayOfMonth);

                                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());


                                datePickerDialog.show();

                            }
                        });

                        Callenglist();
                        engAdapter=new ArrayAdapter(activity,R.layout.spinner_item,streng);
                        engAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
                        speng.setAdapter(engAdapter);

                        speng.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                Enguid=serviceEnguserModels.get(i).getUid();

                               //Toast.makeText(activity, Enguid, Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });


                        CallViewList(partid);
                        spmodeadapter=new ArrayAdapter(activity,R.layout.spinner_item,strModelist);
                        spmodeadapter.setDropDownViewResource(R.layout.spinner_dropdown);
                        spModeOfTransport.setAdapter(spmodeadapter);
                        spModeOfTransport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                TransportID=transportModels.get(i).getId();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });


                        ivBack.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                bottomSheetDialog.dismiss();
                            }
                        });

                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    if(tvDatetime.getText().toString().trim().equals("")){
                                        Toast.makeText(activity, "Please Select a date ", Toast.LENGTH_SHORT).show();
                                    }else if(edRef.getText().toString().trim().equals("")){
                                        Toast.makeText(activity, "Please enter the reference ", Toast.LENGTH_SHORT).show();

                                    }else{
                                        CallSubmit(TransportID,response.body().getSwapListengModels().get(0).getId(),response.body().getSwapListengModels().get(0).getPart_id(),bottomSheetDialog);
                                    }

                                }catch (Exception e){

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

                }catch (Exception e){
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<SwapListengResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });

    }

    private void CallSubmit(String transportID, String id, String partId, BottomSheetDialog bottomSheetDialog) {
        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SubmitswapuserInterface submitswapuserInterface=APIClient.getClient().create(SubmitswapuserInterface.class);
        submitswapuserInterface.submit("1",transportID,partId,edQty2out.getText().toString(),id,tvDatetime.getText().toString(),Enguid,edRef.getText().toString(),PreferenceManager.getEmpID(activity)).enqueue(new Callback<SubmitswapuserResponse>() {
            @Override
            public void onResponse(Call<SubmitswapuserResponse> call, Response<SubmitswapuserResponse> response) {
                try {
                    if (response.isSuccessful()){
                        if (response.body().getResponse().trim().equals("1")){
                            Toast.makeText(activity, "Submitted", Toast.LENGTH_SHORT).show();
                            bottomSheetDialog.dismiss();
                            progressDialog.dismiss();
                        }

                    }
                }catch (Exception e){
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
        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ServiceEnguserInterface serviceEnguserInterface=APIClient.getClient().create(ServiceEnguserInterface.class);
        serviceEnguserInterface.user(PreferenceManager.getEmpID(activity)).enqueue(new Callback<ServiceEnguserResponse>() {
            @Override
            public void onResponse(Call<ServiceEnguserResponse> call, Response<ServiceEnguserResponse> response) {
                try {
                    if (response.isSuccessful()){
                        streng.clear();
//                        streng.add(0,"Select a engineers");
                        serviceEnguserModels=response.body().getServiceEnguserModels();
                        for (int i=0 ; i<serviceEnguserModels.size();i++){
                            streng.add(serviceEnguserModels.get(i).getEngname());
                        }
                        engAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }

                }catch (Exception e){
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
        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
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

    @Override
    public int getItemCount() {
        return myStockListSEModels.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase();
        myStockListSEModels.clear();
        if (charText.length() == 0) {
            myStockListSEModels.addAll(tempMystockList);
        } else {
            for (String searchedValue : strMyStockList) {

                if (searchedValue.toLowerCase().contains(charText)) {
                    for (int i = 0; i < tempMystockList.size(); i++) {

                        if (tempMystockList.get(i).getName().equalsIgnoreCase(searchedValue)) {
                            myStockListSEModels.add(tempMystockList.get(i));
                            break;
                        }else if (tempMystockList.get(i).getPart_no().equalsIgnoreCase(searchedValue)) {
                            myStockListSEModels.add(tempMystockList.get(i));
                            break;
                        }
                    }

                }
            }


        }
        notifyDataSetChanged();
    }

    public void searchAdapter(ArrayList<MyStockSESearchModel> data) {
        tempMystockList.addAll(data);
        strMyStockList.clear();
        for (int i = 0; i < tempMystockList.size(); i++) {
            strMyStockList.add(tempMystockList.get(i).getName());
        }
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPartId, tvPartcisIdName, tvPartotherIdName, tvPartcsplIdName, tvPartNo, tvUnitPrice, tvMyQty, tvpartName;
        ImageView ivswapspare;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPartId = itemView.findViewById(R.id.tvPartId);
            tvPartcisIdName = itemView.findViewById(R.id.tvPartcisIdName);
            tvPartcsplIdName = itemView.findViewById(R.id.tvPartcsplIdName);
            tvPartotherIdName = itemView.findViewById(R.id.tvPartotherIdName);
            tvPartNo = itemView.findViewById(R.id.tvPartNo);
            tvUnitPrice = itemView.findViewById(R.id.tvUnitPrice);
            tvMyQty = itemView.findViewById(R.id.tvMyQty);
            tvpartName = itemView.findViewById(R.id.tvpartName);
            ivswapspare=itemView.findViewById(R.id.ivswapspare);
        }
    }
}
