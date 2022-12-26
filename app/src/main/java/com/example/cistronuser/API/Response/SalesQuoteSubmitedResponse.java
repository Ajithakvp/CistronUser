package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.SalesQuoteMailSendModel;
import com.google.gson.annotations.SerializedName;

public class SalesQuoteSubmitedResponse {


//    "action": "generateSalesQuote",
//            "success": 2,
//            "message": "Sales Quote Created Successfully",
//            "quote": "C:/xampp/htdocs/coms/merge_quote/24162_e367_BST05.pdf",
//            "qr": "C:/xampp/htdocs/beta1/qr/temp_qr/KMP_4290e4cfeccd8f9ca48566b27dd8f7ec.png"


    @SerializedName("action")
    private String action;

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    @SerializedName("quote")
    private String quote;

    @SerializedName("qr")
    private String qr;

    @SerializedName("mail")
    private SalesQuoteMailSendModel salesQuoteMailSendModel;


    public SalesQuoteMailSendModel getSalesQuoteMailSendModel() {
        return salesQuoteMailSendModel;
    }

    public void setSalesQuoteMailSendModel(SalesQuoteMailSendModel salesQuoteMailSendModel) {
        this.salesQuoteMailSendModel = salesQuoteMailSendModel;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
}
