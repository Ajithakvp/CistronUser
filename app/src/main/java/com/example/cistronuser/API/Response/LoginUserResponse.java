package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.LoginuserModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginUserResponse {
    private boolean status;
    public String Message;
    @SerializedName("data")
   // private LoginuserModel data;
    private ArrayList<LoginuserModel> data=new ArrayList<>();

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public ArrayList<LoginuserModel> getData() {
        return data;
    }

    public void setData(ArrayList<LoginuserModel> data) {
        this.data = data;
    }
}
