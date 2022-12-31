package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SalesQuoteReportModel {




//       "quoteDt": "29-Dec-2022",
//               "quoteRef": "CIS/AS/22L/5262",
//               "quotePdf": "http://192.168.29.173/coms/merge_quote/1393666751_e155_DST01.pdf",
//               "quoteCust": "BIDAN ENTERPRISE - Kumarpara | ASSAM",
//               "quoteProd": "B-Sterile : Automatic Steam Sterilizer - 20x48 Double Door | DST01",
//               "user": "Kalaiselvi(E155)",
//               "quoteid": "8879",
//               "status": null

    @SerializedName("quoteDt")
    private String quoteDt;

    @SerializedName("quoteRef")
    private String quoteRef;

    @SerializedName("quotePdf")
    private String quotePdf;


    @SerializedName("quoteCust")
    private String quoteCust;

    @SerializedName("quoteProd")
    private String quoteProd;

    @SerializedName("user")
    private String user;

    @SerializedName("quoteid")
    private String quoteid;

    @SerializedName("status")
    private String status;

    @SerializedName("detail")
    private SalesQuoteReportDetails salesQuoteReportDetails;

    public SalesQuoteReportDetails getSalesQuoteReportDetails() {
        return salesQuoteReportDetails;
    }

    public void setSalesQuoteReportDetails(SalesQuoteReportDetails salesQuoteReportDetails) {
        this.salesQuoteReportDetails = salesQuoteReportDetails;
    }

    public String getQuoteDt() {
        return quoteDt;
    }

    public void setQuoteDt(String quoteDt) {
        this.quoteDt = quoteDt;
    }

    public String getQuoteRef() {
        return quoteRef;
    }

    public void setQuoteRef(String quoteRef) {
        this.quoteRef = quoteRef;
    }

    public String getQuotePdf() {
        return quotePdf;
    }

    public void setQuotePdf(String quotePdf) {
        this.quotePdf = quotePdf;
    }

    public String getQuoteCust() {
        return quoteCust;
    }

    public void setQuoteCust(String quoteCust) {
        this.quoteCust = quoteCust;
    }

    public String getQuoteProd() {
        return quoteProd;
    }

    public void setQuoteProd(String quoteProd) {
        this.quoteProd = quoteProd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getQuoteid() {
        return quoteid;
    }

    public void setQuoteid(String quoteid) {
        this.quoteid = quoteid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
