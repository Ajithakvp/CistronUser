package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SaleQuoteExistingUpdateModel {


//       "date": "2022-12-15",
//               "status": "2",
//               "remark": "No Requirement",
//               "updatedBy": "VELMURUGAN P (E367)"


    @SerializedName("date")
    private String date;


    @SerializedName("status")
    private String status;


    @SerializedName("remark")
    private String remark;


    @SerializedName("updatedBy")
    private String updatedBy;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
