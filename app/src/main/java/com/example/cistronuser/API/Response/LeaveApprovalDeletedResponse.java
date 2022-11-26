package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class LeaveApprovalDeletedResponse {

    @SerializedName("action")
    private String action;

    @SerializedName("message")
    private String message;

    @SerializedName("success")
    private String success;

    @SerializedName("exception")
    private String exception;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
