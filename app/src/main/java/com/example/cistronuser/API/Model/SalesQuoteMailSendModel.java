package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SalesQuoteMailSendModel {


//     "empid": "E367",
//             "id": 53177,
//             "quote_id": "8910",
//             "doctor": "Mr.  TEST DOCTOR TEST DOCTOR MD",
//             "doc_email": "callmeasvelan@gmail.com",
//             "product": "Instrument Washer Cum Disinfector",
//             "m_status": 1,
//             "mailto": "callmeasvelan@gmail.com",
//             "mailcc": "ajithmaxwell3096@gmail.com",
//             "bro_name": "932858435_INW01.pdf",
//             "a": 1

    @SerializedName("empid")
    private String empid;

    @SerializedName("id")
    private String id;

    @SerializedName("quote_id")
    private String quote_id;

    @SerializedName("doctor")
    private String doctor;

    @SerializedName("doc_email")
    private String doc_email;

    @SerializedName("product")
    private String product;

    @SerializedName("m_status")
    private String m_status;

    @SerializedName("mailto")
    private String mailto;

    @SerializedName("mailcc")
    private String mailcc;

    @SerializedName("bro_name")
    private String bro_name;

    @SerializedName("a")
    private String a;


    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(String quote_id) {
        this.quote_id = quote_id;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDoc_email() {
        return doc_email;
    }

    public void setDoc_email(String doc_email) {
        this.doc_email = doc_email;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getM_status() {
        return m_status;
    }

    public void setM_status(String m_status) {
        this.m_status = m_status;
    }

    public String getMailto() {
        return mailto;
    }

    public void setMailto(String mailto) {
        this.mailto = mailto;
    }

    public String getMailcc() {
        return mailcc;
    }

    public void setMailcc(String mailcc) {
        this.mailcc = mailcc;
    }

    public String getBro_name() {
        return bro_name;
    }

    public void setBro_name(String bro_name) {
        this.bro_name = bro_name;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
