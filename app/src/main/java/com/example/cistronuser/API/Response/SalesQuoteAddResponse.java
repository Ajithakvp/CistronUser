package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class SalesQuoteAddResponse {


//    "action": "salesQuotePreview",
//            "success": "1",
//            "message": "",
//            "previewUrl": "http://192.168.29.173/coms/merge_quote/preview.pdf"

    @SerializedName("action")
    private String action;

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    @SerializedName("previewUrl")
    private String previewUrl;

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

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }
}
