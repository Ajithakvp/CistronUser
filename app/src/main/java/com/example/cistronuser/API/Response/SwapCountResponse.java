package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class SwapCountResponse {

    @SerializedName("SwapInward_count")
    public String SwapInward_count;

    @SerializedName("SwapOutward_count")
    public String SwapOutward_count;


    public String getSwapInward_count() {
        return SwapInward_count;
    }

    public void setSwapInward_count(String swapInward_count) {
        SwapInward_count = swapInward_count;
    }

    public String getSwapOutward_count() {
        return SwapOutward_count;
    }

    public void setSwapOutward_count(String swapOutward_count) {
        SwapOutward_count = swapOutward_count;
    }
}
