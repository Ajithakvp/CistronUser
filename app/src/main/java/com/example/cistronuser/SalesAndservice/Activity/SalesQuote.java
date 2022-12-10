package com.example.cistronuser.SalesAndservice.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.SalesQuoteAddInterface;
import com.example.cistronuser.API.Interface.SalesQuoteCategoryInterface;
import com.example.cistronuser.API.Interface.SalesQuoteProductsInterFace;
import com.example.cistronuser.API.Interface.VisitEntryDoctorInterface;
import com.example.cistronuser.API.Interface.VisitEntryGetDistrictInterface;
import com.example.cistronuser.API.Interface.VisitEntryHospitalInterface;
import com.example.cistronuser.API.Interface.VisitEntryStateInterface;
import com.example.cistronuser.API.Model.SalesQuoteCategoryModel;
import com.example.cistronuser.API.Model.SalesQuoteProductsAddonModel;
import com.example.cistronuser.API.Model.SalesQuoteProductsModel;
import com.example.cistronuser.API.Model.VisitEntryDoctorModel;
import com.example.cistronuser.API.Model.VisitEntryGetDistrictModel;
import com.example.cistronuser.API.Model.VisitEntryHospitalModel;
import com.example.cistronuser.API.Model.VisitEntryStateModel;
import com.example.cistronuser.API.Response.SalesQuoteAddResponse;
import com.example.cistronuser.API.Response.SalesQuoteCategoryResponse;
import com.example.cistronuser.API.Response.SalesQuoteProductsResponse;
import com.example.cistronuser.API.Response.VisitEntryDoctorResponse;
import com.example.cistronuser.API.Response.VisitEntryGetDistrictResponse;
import com.example.cistronuser.API.Response.VisitEntryModelResponse;
import com.example.cistronuser.API.Response.VisityEntryStateResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.SalesAndservice.Adapter.SalesQuoteAddonAdapter;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesQuote extends AppCompatActivity {

    TextView tvDate, tvAdd, tvSubmit;
    ImageView ivBack, ivList;
    Spinner spDistrict, spState, spDoctor, spProduct, sphospital, spCategory;


    ArrayList<VisitEntryGetDistrictModel> visitEntryGetDistrictModels = new ArrayList<>();
    ArrayList<String> strDistrict = new ArrayList<>();
    ArrayAdapter districtAdapter;
    String District;

    //State
    ArrayList<VisitEntryStateModel> visitEntryStateModels = new ArrayList<>();
    ArrayList<String> strState = new ArrayList<>();
    ArrayAdapter stateAdapter;
    String StateId;

    //Hospital
    ArrayList<VisitEntryHospitalModel> visitEntryHospitalModels = new ArrayList<>();
    ArrayList<String> strHospital = new ArrayList<>();
    ArrayAdapter hospitalAdapter;
    String HospitalID;


    //ChefDoctor
    ArrayList<VisitEntryDoctorModel> visitEntryDoctorModels = new ArrayList<>();
    ArrayList<String> strDoctor = new ArrayList<>();
    ArrayAdapter chefDocAdapter;
    EditText edMail, edAnotherMail;
    String ChefDocID;


    //Category
    ArrayList<SalesQuoteCategoryModel> salesQuoteCategoryModels = new ArrayList<>();
    ArrayList<String> strCategory = new ArrayList<>();
    ArrayAdapter categoryAdapter;
    String CategoryID;

    //Product
    ArrayList<SalesQuoteProductsModel> salesQuoteProductsModels = new ArrayList<>();
    ArrayList<SalesQuoteProductsAddonModel> salesQuoteProductsAddonModels = new ArrayList<>();
    ArrayList<String> strAddon = new ArrayList<>();
    SalesQuoteAddonAdapter salesQuoteAddonAdapter;
    TextView tvPrice;
    ArrayList<String> strProduct = new ArrayList<>();
    ArrayAdapter ProductAdapter;
    RecyclerView rvProducts;
    String ProductID;
    String AddOnID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_quote);

        spDistrict = findViewById(R.id.spDistrict);
        tvDate = findViewById(R.id.tvDate);
        ivBack = findViewById(R.id.ivBack);
        spProduct = findViewById(R.id.spProduct);
        spState = findViewById(R.id.spState);
        spDoctor = findViewById(R.id.spDoctor);
        sphospital = findViewById(R.id.sphospital);
        tvAdd = findViewById(R.id.tvAdd);
        ivList = findViewById(R.id.ivList);
        spCategory = findViewById(R.id.spCategory);
        rvProducts = findViewById(R.id.rvProducts);
        tvPrice = findViewById(R.id.tvPrice);
        edAnotherMail = findViewById(R.id.edAnotherMail);
        edMail = findViewById(R.id.edMail);


        //State

        CallState();
        stateAdapter = new ArrayAdapter(this, R.layout.spinner_item, strState);
        stateAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spState.setAdapter(stateAdapter);
        spState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                callDistrict(spState.getSelectedItem().toString());
                callHospital(spState.getSelectedItem().toString(), "District");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //District

        districtAdapter = new ArrayAdapter(this, R.layout.spinner_item, strDistrict);
        districtAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spDistrict.setAdapter(districtAdapter);
        spDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                callHospital(spState.getSelectedItem().toString(), spDistrict.getSelectedItem().toString());


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Hospital

        hospitalAdapter = new ArrayAdapter(this, R.layout.spinner_item, strHospital);
        hospitalAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        sphospital.setAdapter(hospitalAdapter);
        sphospital.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                HospitalID = visitEntryHospitalModels.get(position).getId();
                CallChefDoc(HospitalID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //ChefDoctor
        chefDocAdapter = new ArrayAdapter(this, R.layout.spinner_item, strDoctor);
        chefDocAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spDoctor.setAdapter(chefDocAdapter);
        spDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ChefDocID = visitEntryDoctorModels.get(position).getId();
                edMail.setText(visitEntryDoctorModels.get(position).getMail());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Category
        CallCategory();
        categoryAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, strCategory);
        categoryAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spCategory.setAdapter(categoryAdapter);
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CategoryID = salesQuoteCategoryModels.get(position).getSeriesId();
                rvProducts.setVisibility(View.GONE);
                CallProduct(CategoryID);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Product'

        salesQuoteAddonAdapter = new SalesQuoteAddonAdapter(this, salesQuoteProductsAddonModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvProducts.setLayoutManager(linearLayoutManager);
        rvProducts.setAdapter(salesQuoteAddonAdapter);

        ProductAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, strProduct);
        ProductAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spProduct.setAdapter(ProductAdapter);
        spProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProductID = salesQuoteProductsModels.get(position).getProdId();

                String Price;
                Double Product = Double.valueOf(salesQuoteProductsModels.get(position).getPrice());
                Double AddOnprice = Double.valueOf(salesQuoteProductsModels.get(position).getSalesQuoteProductsAddonModels().get(0).getPrice());
                Price = String.valueOf(Product + AddOnprice);
                tvPrice.setText(Price);




                if (salesQuoteProductsModels.get(position).getSalesQuoteProductsAddonModels().size() > 0) {
                    rvProducts.setVisibility(View.VISIBLE);
                    salesQuoteAddonAdapter.salesQuoteProductsAddonModels = salesQuoteProductsModels.get(position).getSalesQuoteProductsAddonModels();
                    salesQuoteProductsAddonModels = salesQuoteProductsModels.get(position).getSalesQuoteProductsAddonModels();
                    for (int i=0;i<salesQuoteProductsAddonModels.size();i++){
                        SalesQuoteProductsAddonModel salesQuoteProductsAddonModel=salesQuoteProductsAddonModels.get(i);
                        AddOnID=salesQuoteProductsAddonModel.getAddonId();
                        Log.e(TAG, "onItemSelected: "+AddOnID );
                    }

                    salesQuoteAddonAdapter.notifyDataSetChanged();
                } else {
                    rvProducts.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDate.setError(null);

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                calendar.add(Calendar.DATE, -1);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SalesQuote.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String moth, dt;

                        moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                        dt = (day > 9) ? "" + day : ("0" + day);


                        String strDate = year + "-" + moth + "-" + dt;
                        tvDate.setText(strDate);


                    }

                }, year, month, dayOfMonth);

                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);


                datePickerDialog.show();


            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallPreview();
            }
        });

    }

    private void CallPreview() {


        SalesQuoteAddInterface salesQuoteAddInterface = APIClient.getClient().create(SalesQuoteAddInterface.class);
        salesQuoteAddInterface.CallPreview("salesQuotePreview", CategoryID, ProductID, HospitalID, AddOnID).enqueue(new Callback<SalesQuoteAddResponse>() {
            @Override
            public void onResponse(Call<SalesQuoteAddResponse> call, Response<SalesQuoteAddResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        String Preview = response.body().getPreviewUrl();
                        Uri uri = Uri.parse(Preview);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<SalesQuoteAddResponse> call, Throwable t) {

            }
        });
    }

    private void CallProduct(String categoryID) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SalesQuoteProductsInterFace salesQuoteProductsInterFace = APIClient.getClient().create(SalesQuoteProductsInterFace.class);
        salesQuoteProductsInterFace.CallProduct("getProductOfTheSeries", categoryID).enqueue(new Callback<SalesQuoteProductsResponse>() {
            @Override
            public void onResponse(Call<SalesQuoteProductsResponse> call, Response<SalesQuoteProductsResponse> response) {

                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();

                        if (response.body().getSalesQuoteProductsModels().size() > 0) {
                            salesQuoteProductsModels = response.body().getSalesQuoteProductsModels();
                            strProduct.clear();
                            for (int i = 0; i < salesQuoteProductsModels.size(); i++) {
                                strProduct.add(salesQuoteProductsModels.get(i).getSeriesName());


//
//                                if (salesQuoteProductsModels.get(i).getSalesQuoteProductsAddonModels().size()>0){
//                                    rvProducts.setVisibility(View.VISIBLE);
//                                    Log.e(TAG, "onResponse: "+salesQuoteProductsModels.get(i).getSalesQuoteProductsAddonModels().size());
//                                    salesQuoteAddonAdapter.salesQuoteProductsAddonModels =salesQuoteProductsModels.get(i).getSalesQuoteProductsAddonModels();
//
//                                    AddOnID=salesQuoteProductsModels.get(i).getSalesQuoteProductsAddonModels().get(i).getAddonId();
//                                    Log.e(TAG, "onResponse: ", );
//                                    salesQuoteAddonAdapter.notifyDataSetChanged();
//                                }else {
//                                    rvProducts.setVisibility(View.GONE);
//                                }

                            }


                            ProductAdapter.notifyDataSetChanged();
                        }


                    }

                } catch (Exception e) {

                }


            }

            @Override
            public void onFailure(Call<SalesQuoteProductsResponse> call, Throwable t) {

            }
        });

    }

    private void CallCategory() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SalesQuoteCategoryInterface salesQuoteCategoryInterface = APIClient.getClient().create(SalesQuoteCategoryInterface.class);
        salesQuoteCategoryInterface.CallCategory("getAvailableCategory").enqueue(new Callback<SalesQuoteCategoryResponse>() {
            @Override
            public void onResponse(Call<SalesQuoteCategoryResponse> call, Response<SalesQuoteCategoryResponse> response) {
                try {
                    if (response.body().getSalesQuoteCategoryModels().size() > 0) {
                        progressDialog.dismiss();
                        salesQuoteCategoryModels = response.body().getSalesQuoteCategoryModels();
                        strCategory.clear();
                        for (int i = 0; i < salesQuoteCategoryModels.size(); i++) {
                            strCategory.add(salesQuoteCategoryModels.get(i).getSeriesName());
                        }
                        categoryAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<SalesQuoteCategoryResponse> call, Throwable t) {

            }
        });
    }


    private void CallChefDoc(String hospitalID) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        VisitEntryDoctorInterface visitEntryDoctorInterface = APIClient.getClient().create(VisitEntryDoctorInterface.class);
        visitEntryDoctorInterface.callChefDoctor("getChiefDr", hospitalID).enqueue(new Callback<VisitEntryDoctorResponse>() {
            @Override
            public void onResponse(Call<VisitEntryDoctorResponse> call, Response<VisitEntryDoctorResponse> response) {
                try {
                    if (response.body().getVisitEntryDoctorModels().size() > 0) {
                        visitEntryDoctorModels = response.body().getVisitEntryDoctorModels();
                        strDoctor.clear();
                        for (int i = 0; i < visitEntryDoctorModels.size(); i++) {
                            strDoctor.add(visitEntryDoctorModels.get(i).getChiefDr());
                        }
                        chefDocAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    } else {
                        progressDialog.dismiss();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<VisitEntryDoctorResponse> call, Throwable t) {

            }
        });
    }

    private void callHospital(String state, String district) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        VisitEntryHospitalInterface visitEntryHospitalInterface = APIClient.getClient().create(VisitEntryHospitalInterface.class);
        visitEntryHospitalInterface.CallHospital("getStateHospitals", state, district).enqueue(new Callback<VisitEntryModelResponse>() {
            @Override
            public void onResponse(Call<VisitEntryModelResponse> call, Response<VisitEntryModelResponse> response) {

                try {
                    if (response.body().getVisitEntryHospitalModels().size() > 0) {
                        visitEntryHospitalModels = response.body().getVisitEntryHospitalModels();

                        strHospital.clear();
                        for (int i = 0; i < visitEntryHospitalModels.size(); i++) {
                            strHospital.add(visitEntryHospitalModels.get(i).getHospital());
                        }
                        hospitalAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    } else {
                        progressDialog.dismiss();
                    }

                } catch (Exception e) {

                }


            }

            @Override
            public void onFailure(Call<VisitEntryModelResponse> call, Throwable t) {

            }
        });
    }

    private void CallState() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        VisitEntryStateInterface visitEntryStateInterface = APIClient.getClient().create(VisitEntryStateInterface.class);
        visitEntryStateInterface.CallState("getUserStates", PreferenceManager.getEmpID(this)).enqueue(new Callback<VisityEntryStateResponse>() {
            @Override
            public void onResponse(Call<VisityEntryStateResponse> call, Response<VisityEntryStateResponse> response) {
                try {

                    if (response.body().getVisitEntryStateModels().size() > 0) {

                        Log.e(TAG, "onResponse: " + response.body().getVisitEntryStateModels());
                        visitEntryStateModels = response.body().getVisitEntryStateModels();

                        strState.clear();
                        for (int i = 0; i < visitEntryStateModels.size(); i++) {
                            strState.add(visitEntryStateModels.get(i).getState());
                        }
                        stateAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<VisityEntryStateResponse> call, Throwable t) {

            }
        });
    }

    private void callDistrict(String state) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        VisitEntryGetDistrictInterface visitEntryGetDistrictInterface = APIClient.getClient().create(VisitEntryGetDistrictInterface.class);
        visitEntryGetDistrictInterface.CallDistrict("getDistricts", state).enqueue(new Callback<VisitEntryGetDistrictResponse>() {
            @Override
            public void onResponse(Call<VisitEntryGetDistrictResponse> call, Response<VisitEntryGetDistrictResponse> response) {
                try {
                    if (response.body().getVisitEntryGetDistrictModels().size() > 0) {
                        visitEntryGetDistrictModels = response.body().getVisitEntryGetDistrictModels();
                        strDistrict.clear();
                        for (int i = 0; i < visitEntryGetDistrictModels.size(); i++) {
                            strDistrict.add(visitEntryGetDistrictModels.get(i).getDistrict());
                        }
                        districtAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<VisitEntryGetDistrictResponse> call, Throwable t) {

            }
        });
    }

}