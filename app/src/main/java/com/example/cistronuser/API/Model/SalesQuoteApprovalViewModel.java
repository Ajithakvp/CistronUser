package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SalesQuoteApprovalViewModel {

//     "hospital": "Test Hospital Pvm",
//             "hosp_adrs": "Venbavoor / Veppanthattai / Perambalur",
//             "dealer": "0",
//             "enduser": "0",
//             "quote": "11",
//             "po_ref": "14",
//             "po_date": "2022-12-19",
//             "po": "20",
//             "po_pdf": "http://192.168.29.173/coms/po_uploads/sales-quote-11.jpg",
//             "quote_pdf": "http://192.168.29.173/coms//merge_quote/14729_E367_DST05.pdf",
//             "billing_opt": "1",
//             "delivery": "Test Hospital Pvm\nHospital Road\nVenbavoor\nPerambalur-621116\nTamil Nadu",
//             "billing": "Test Hospital Pvm\nHospital Road\nVenbavoor\nPerambalur-621116\nTamil Nadu",
//             "con_pre": "Dr",
//             "con_name": "Test Doctor",
//             "con_no": "8876543210",
//             "product": "458",
//             "pro_name": "(DST05) B-Sterile : Automatic Steam Sterilizer - 16x24 Double Door",
//             "pro_opt": "1",
//             "pro_spec": "As per Quotation",
//             "qty": "1",
//             "order_value": "100",
//             "advance_value": "58",
//             "deposit": "0",
//             "deposit_date": "0000-00-00",
//             "bp_dispatch": "788",
//             "ba_dispatch": "889",
//             "bp_install": "789",
//             "total_instal": "2",
//             "installments": [
//             "888",
//             "599"
//             ],
//             "bp_remarks": "",
//             "delivery_date": "2022-12-20",
//             "warranty": "12",
//             "spl_remarks": ""
//}

    @SerializedName("hospital")
    private String hospital;

    @SerializedName("hosp_adrs")
    private String hosp_adrs;

    @SerializedName("dealer")
    private String dealer;

    @SerializedName("enduser")
    private String enduser;

    @SerializedName("quote")
    private String quote;

    @SerializedName("po_ref")
    private String po_ref;

    @SerializedName("po_date")
    private String po_date;

    @SerializedName("po_pdf")
    private String po_pdf;

    @SerializedName("quote_pdf")
    private String quote_pdf;

    @SerializedName("billing_opt")
    private String billing_opt;

    @SerializedName("delivery")
    private String delivery;

    @SerializedName("billing")
    private String billing;

    @SerializedName("con_pre")
    private String con_pre;

    @SerializedName("con_name")
    private String con_name;

    @SerializedName("con_no")
    private String con_no;

    @SerializedName("product")
    private String product;

    @SerializedName("pro_name")
    private String pro_name;

    @SerializedName("pro_opt")
    private String pro_opt;

    @SerializedName("pro_spec")
    private String pro_spec;

    @SerializedName("qty")
    private String qty;

    @SerializedName("order_value")
    private String order_value;

    @SerializedName("advance_value")
    private String advance_value;

    @SerializedName("deposit")
    private String deposit;

    @SerializedName("deposit_date")
    private String deposit_date;

    @SerializedName("bp_dispatch")
    private String bp_dispatch;

    @SerializedName("ba_dispatch")
    private String ba_dispatch;

    @SerializedName("bp_install")
    private String bp_install;

    @SerializedName("total_instal")
    private String total_instal;

    @SerializedName("installments")
    private ArrayList<SalesQuoteApprovalViewInstallmentModel> salesQuoteApprovalViewInstallmentModels = new ArrayList<>();

    @SerializedName("bp_remarks")
    private String bp_remarks;

    @SerializedName("delivery_date")
    private String delivery_date;

    @SerializedName("warranty")
    private String warranty;

    @SerializedName("spl_remarks")
    private String spl_remarks;


    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getHosp_adrs() {
        return hosp_adrs;
    }

    public void setHosp_adrs(String hosp_adrs) {
        this.hosp_adrs = hosp_adrs;
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public String getEnduser() {
        return enduser;
    }

    public void setEnduser(String enduser) {
        this.enduser = enduser;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getPo_ref() {
        return po_ref;
    }

    public void setPo_ref(String po_ref) {
        this.po_ref = po_ref;
    }

    public String getPo_date() {
        return po_date;
    }

    public void setPo_date(String po_date) {
        this.po_date = po_date;
    }

    public String getPo_pdf() {
        return po_pdf;
    }

    public void setPo_pdf(String po_pdf) {
        this.po_pdf = po_pdf;
    }

    public String getQuote_pdf() {
        return quote_pdf;
    }

    public void setQuote_pdf(String quote_pdf) {
        this.quote_pdf = quote_pdf;
    }

    public String getBilling_opt() {
        return billing_opt;
    }

    public void setBilling_opt(String billing_opt) {
        this.billing_opt = billing_opt;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getBilling() {
        return billing;
    }

    public void setBilling(String billing) {
        this.billing = billing;
    }

    public String getCon_pre() {
        return con_pre;
    }

    public void setCon_pre(String con_pre) {
        this.con_pre = con_pre;
    }

    public String getCon_name() {
        return con_name;
    }

    public void setCon_name(String con_name) {
        this.con_name = con_name;
    }

    public String getCon_no() {
        return con_no;
    }

    public void setCon_no(String con_no) {
        this.con_no = con_no;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getPro_opt() {
        return pro_opt;
    }

    public void setPro_opt(String pro_opt) {
        this.pro_opt = pro_opt;
    }

    public String getPro_spec() {
        return pro_spec;
    }

    public void setPro_spec(String pro_spec) {
        this.pro_spec = pro_spec;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getOrder_value() {
        return order_value;
    }

    public void setOrder_value(String order_value) {
        this.order_value = order_value;
    }

    public String getAdvance_value() {
        return advance_value;
    }

    public void setAdvance_value(String advance_value) {
        this.advance_value = advance_value;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getDeposit_date() {
        return deposit_date;
    }

    public void setDeposit_date(String deposit_date) {
        this.deposit_date = deposit_date;
    }

    public String getBp_dispatch() {
        return bp_dispatch;
    }

    public void setBp_dispatch(String bp_dispatch) {
        this.bp_dispatch = bp_dispatch;
    }

    public String getBa_dispatch() {
        return ba_dispatch;
    }

    public void setBa_dispatch(String ba_dispatch) {
        this.ba_dispatch = ba_dispatch;
    }

    public String getBp_install() {
        return bp_install;
    }

    public void setBp_install(String bp_install) {
        this.bp_install = bp_install;
    }

    public String getTotal_instal() {
        return total_instal;
    }

    public void setTotal_instal(String total_instal) {
        this.total_instal = total_instal;
    }

    public ArrayList<SalesQuoteApprovalViewInstallmentModel> getSalesQuoteApprovalViewInstallmentModels() {
        return salesQuoteApprovalViewInstallmentModels;
    }

    public void setSalesQuoteApprovalViewInstallmentModels(ArrayList<SalesQuoteApprovalViewInstallmentModel> salesQuoteApprovalViewInstallmentModels) {
        this.salesQuoteApprovalViewInstallmentModels = salesQuoteApprovalViewInstallmentModels;
    }

    public String getBp_remarks() {
        return bp_remarks;
    }

    public void setBp_remarks(String bp_remarks) {
        this.bp_remarks = bp_remarks;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getSpl_remarks() {
        return spl_remarks;
    }

    public void setSpl_remarks(String spl_remarks) {
        this.spl_remarks = spl_remarks;
    }
}
