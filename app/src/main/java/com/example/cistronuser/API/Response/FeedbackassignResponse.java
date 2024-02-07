package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.FeedbackassignModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FeedbackassignResponse {
    //  "response": [

    @SerializedName("response")
    private ArrayList<FeedbackassignModel>feedbackassignModels=new ArrayList<>();

    public ArrayList<FeedbackassignModel> getFeedbackassignModels() {
        return feedbackassignModels;
    }

    public void setFeedbackassignModels(ArrayList<FeedbackassignModel> feedbackassignModels) {
        this.feedbackassignModels = feedbackassignModels;
    }
}
