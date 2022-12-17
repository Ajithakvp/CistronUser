package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class SalesQuoteContactPersonResponse {


//     "quoteId": "1",
//             "productId": "458",
//             "product": "(DST05) B-sterile : Automatic Steam Sterilizer - 16x24 Double Door",
//             "quote_pdf": "http://192.168.29.173/merge_quote/11315_e0007_DST05.pdf",
//             "con_prefix": "Mr. ",
//             "con_person": "Test Doctor",
//             "mobile": "1234567890",
//             "address": "Test Hospital Pvm\nHospital Road\nVenbavoor\nPerambalur-621116\nTamil Nadu"

    @SerializedName("quoteId")
    private String quoteId;

    @SerializedName("productId")
    private String productId;

    @SerializedName("product")
    private String product;

    @SerializedName("quote_pdf")
    private String quote_pdf;

    @SerializedName("con_prefix")
    private String con_prefix;

    @SerializedName("con_person")
    private String con_person;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("address")
    private String address;

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuote_pdf() {
        return quote_pdf;
    }

    public void setQuote_pdf(String quote_pdf) {
        this.quote_pdf = quote_pdf;
    }

    public String getCon_prefix() {
        return con_prefix;
    }

    public void setCon_prefix(String con_prefix) {
        this.con_prefix = con_prefix;
    }

    public String getCon_person() {
        return con_person;
    }

    public void setCon_person(String con_person) {
        this.con_person = con_person;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
