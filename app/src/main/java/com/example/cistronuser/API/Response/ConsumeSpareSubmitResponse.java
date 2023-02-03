package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class ConsumeSpareSubmitResponse {

    //"action": "onConsumeSpares",
    //  "result": "Spares Consumed",
    //  "error": 0

    @SerializedName("action")
    private String  action;

    @SerializedName("result")
    private String  result;

    @SerializedName("error")
    private String  error;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
