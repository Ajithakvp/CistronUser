package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.UserListModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserListResponse {

    //"action": "user_list",
    //  "res": [

    @SerializedName("action")
    private String action;

    @SerializedName("res")
    private ArrayList<UserListModel>userListModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<UserListModel> getUserListModels() {
        return userListModels;
    }

    public void setUserListModels(ArrayList<UserListModel> userListModels) {
        this.userListModels = userListModels;
    }
}
