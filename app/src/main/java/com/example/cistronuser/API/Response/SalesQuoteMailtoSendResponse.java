package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Query;

public class SalesQuoteMailtoSendResponse {


//     "mail_status": 1,

    //{"mail_status":1,"mail_error":"","sms_status":1,"sms_error":""}

//             "sms_status": 1

    @SerializedName("mail_status")
    private String mail_status;

    @SerializedName("mail_error")
    private String mail_error;

    @SerializedName("sms_status")
    private String sms_status;

    @SerializedName("sms_error")
    private String sms_error;

    public String getMail_status() {
        return mail_status;
    }

    public void setMail_status(String mail_status) {
        this.mail_status = mail_status;
    }

    public String getMail_error() {
        return mail_error;
    }

    public void setMail_error(String mail_error) {
        this.mail_error = mail_error;
    }

    public String getSms_status() {
        return sms_status;
    }

    public void setSms_status(String sms_status) {
        this.sms_status = sms_status;
    }

    public String getSms_error() {
        return sms_error;
    }

    public void setSms_error(String sms_error) {
        this.sms_error = sms_error;
    }
}
