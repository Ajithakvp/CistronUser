package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.ConsumeCustSparesModel;
import com.example.cistronuser.API.Model.ConsumeSparesModel;
import com.example.cistronuser.API.Model.CustomerPospareModel;
import com.google.gson.annotations.SerializedName;

public class YesDoYouConsumeSpareResponse {

    //"action": "doYouConsumeSpares",
    //  "squote": {
    //consumeSpares": {
    //consumeCustSpares": {

    @SerializedName("action")
    private String action;

    @SerializedName("squote")
    private CustomerPospareModel customerPospareModel;

    @SerializedName("consumeSpares")
    private ConsumeSparesModel consumeSparesModel;

    @SerializedName("consumeCustSpares")
    private ConsumeCustSparesModel consumeCustSparesModel;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public CustomerPospareModel getCustomerPospareModel() {
        return customerPospareModel;
    }

    public void setCustomerPospareModel(CustomerPospareModel customerPospareModel) {
        this.customerPospareModel = customerPospareModel;
    }

    public ConsumeSparesModel getConsumeSparesModel() {
        return consumeSparesModel;
    }

    public void setConsumeSparesModel(ConsumeSparesModel consumeSparesModel) {
        this.consumeSparesModel = consumeSparesModel;
    }

    public ConsumeCustSparesModel getConsumeCustSparesModel() {
        return consumeCustSparesModel;
    }

    public void setConsumeCustSparesModel(ConsumeCustSparesModel consumeCustSparesModel) {
        this.consumeCustSparesModel = consumeCustSparesModel;
    }
}
