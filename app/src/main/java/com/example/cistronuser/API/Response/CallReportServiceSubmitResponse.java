package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class CallReportServiceSubmitResponse {
    //I/{"action":"uploadLR","lr":0,"uploadStatus":[{"fileName":"file_inlr","error":1}]}

    @SerializedName("uploadLR")
    private String uploadLR;

    @SerializedName("lr")
    private String lr;

    public String getUploadLR() {
        return uploadLR;
    }

    public void setUploadLR(String uploadLR) {
        this.uploadLR = uploadLR;
    }

    public String getLr() {
        return lr;
    }

    public void setLr(String lr) {
        this.lr = lr;
    }
}
