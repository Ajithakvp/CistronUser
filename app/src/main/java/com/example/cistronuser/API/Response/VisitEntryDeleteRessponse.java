package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class VisitEntryDeleteRessponse {

//     "action": "deleteVisitEntry",
//             "message": "Visit Entry Not Deleted, Plz contact your system admin",
//             "status": "0"

    @SerializedName("action")
    private String action;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
