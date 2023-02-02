package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class CallReportSpareConsumedSubmitResponse {

    //"action":"uploadDCForSparesConsumed","sr":1,"

    @SerializedName("uploadDCForSparesConsumed")
    private String uploadDCForSparesConsumed;

    @SerializedName("sr")
    private String sr;

    public String getUploadDCForSparesConsumed() {
        return uploadDCForSparesConsumed;
    }

    public void setUploadDCForSparesConsumed(String uploadDCForSparesConsumed) {
        this.uploadDCForSparesConsumed = uploadDCForSparesConsumed;
    }

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }
}
