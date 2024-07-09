package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.ViewSwapindialogModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ViewSwapindialogResponse {

    // "response": "View_Inward",
    //  "chk_transport1": "1",
    //  "transportmode1": "1",
    //  "return_date": "2024-04-18",
    //  "transport_ref": "test",
    //  "Data": [

    @SerializedName("response")
    private String response;

    @SerializedName("chk_transport1")
    private String chk_transport1;

    @SerializedName("transportmode1")
    private String transportmode1;

    @SerializedName("return_date")
    private String return_date;

    @SerializedName("transport_ref")
    private String transport_ref;

    @SerializedName("Data")
    private ArrayList<ViewSwapindialogModel>viewSwapindialogModels=new ArrayList<>();


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getChk_transport1() {
        return chk_transport1;
    }

    public void setChk_transport1(String chk_transport1) {
        this.chk_transport1 = chk_transport1;
    }

    public String getTransportmode1() {
        return transportmode1;
    }

    public void setTransportmode1(String transportmode1) {
        this.transportmode1 = transportmode1;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public String getTransport_ref() {
        return transport_ref;
    }

    public void setTransport_ref(String transport_ref) {
        this.transport_ref = transport_ref;
    }

    public ArrayList<ViewSwapindialogModel> getViewSwapindialogModels() {
        return viewSwapindialogModels;
    }

    public void setViewSwapindialogModels(ArrayList<ViewSwapindialogModel> viewSwapindialogModels) {
        this.viewSwapindialogModels = viewSwapindialogModels;
    }
}
