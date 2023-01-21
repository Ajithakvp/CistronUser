package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class CallInfoModel {


    // "custDetail": "<b>20AP251749-</b> Shine speciality hospital / Ramalingapuram - Nellore - AP",
    //            "prodDetail": "HHS Steam Sterilizer (18 Kw) - SD - 20x48 - 236 Litres (HHS03)",
    //            "proSerial": "10580820",
    //            "createdBy": "(e000) System Generated",
    //            "reportBy": "Dr. sujay sada - 9848522663"
    //"logistics_bp_install": "68440",
    //      "logistics_bp_installr": "0"


    @SerializedName("custDetail")
    private String custDetail;

    @SerializedName("prodDetail")
    private String prodDetail;

    @SerializedName("proSerial")
    private String proSerial;

    @SerializedName("createdBy")
    private String createdBy;

    @SerializedName("reportBy")
    private String reportBy;

    @SerializedName("logistics_bp_install")
    private String logistics_bp_install;

    @SerializedName("logistics_bp_installr")
    private String logistics_bp_installr;


    public String getLogistics_bp_install() {
        return logistics_bp_install;
    }

    public void setLogistics_bp_install(String logistics_bp_install) {
        this.logistics_bp_install = logistics_bp_install;
    }

    public String getLogistics_bp_installr() {
        return logistics_bp_installr;
    }

    public void setLogistics_bp_installr(String logistics_bp_installr) {
        this.logistics_bp_installr = logistics_bp_installr;
    }

    public String getCustDetail() {
        return custDetail;
    }

    public void setCustDetail(String custDetail) {
        this.custDetail = custDetail;
    }

    public String getProdDetail() {
        return prodDetail;
    }

    public void setProdDetail(String prodDetail) {
        this.prodDetail = prodDetail;
    }

    public String getProSerial() {
        return proSerial;
    }

    public void setProSerial(String proSerial) {
        this.proSerial = proSerial;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getReportBy() {
        return reportBy;
    }

    public void setReportBy(String reportBy) {
        this.reportBy = reportBy;
    }
}
