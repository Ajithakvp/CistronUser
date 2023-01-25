package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class CustomerPoResponseModel {

    //"poNo": "Cistron/Service/Est/2022/11/1944",
    //          "poRef": "1066_Kandukuri Hospitals_QUOTE.pdf",
    //          "poRefLink": "http://192.168.29.173/coms/servicequote/1066_Kandukuri%20Hospitals_QUOTE.pdf",
    //          "poDt": "2022-11-09",
    //          "id": "1066",
    //          "status": "5"

    @SerializedName("poNo")
    private String poNo;

    @SerializedName("poRef")
    private String poRef;

    @SerializedName("poRefLink")
    private String poRefLink;

    @SerializedName("poDt")
    private String poDt;

    @SerializedName("id")
    private String id;

    @SerializedName("status")
    private String status;


    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public String getPoRef() {
        return poRef;
    }

    public void setPoRef(String poRef) {
        this.poRef = poRef;
    }

    public String getPoRefLink() {
        return poRefLink;
    }

    public void setPoRefLink(String poRefLink) {
        this.poRefLink = poRefLink;
    }

    public String getPoDt() {
        return poDt;
    }

    public void setPoDt(String poDt) {
        this.poDt = poDt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
