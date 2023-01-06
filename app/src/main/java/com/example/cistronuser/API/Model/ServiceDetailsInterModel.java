package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class ServiceDetailsInterModel {

//     "sr": "22081108",
//             "engineer": "Tempyugan (Tempyugan)",
//             "assigned": "11-01-2023 ; 11:00:00",
//             "attended": "11-08-2022 ; 09:00:00 to 20:00:00",
//             "problem": "",
//             "workDone": "Preventive maintenance\nChecked hating coil of amps 28.9\nWork done \nChecked gas cuts and boiler \nNo leakage found from the boiler\nRun all cycles machine is working good ",
//             "engAdvice": "",
//             "feedback": "",
//             "status": "Not yet to attend",
//             "pendingReason": "",
//             "Report": "",
//             "Warranty card": "",
//             "Installation image1": "",
//             "Installation image2": "",
//             "Installation image3": "",
//             "Eway bill": "",
//             "lr": ""


    @SerializedName("sr")
    private String sr;

    @SerializedName("engineer")
    private String engineer;

    @SerializedName("assigned")
    private String assigned;

    @SerializedName("attended")
    private String attended;

    @SerializedName("problem")
    private String problem;

    @SerializedName("workDone")
    private String workDone;

    @SerializedName("engAdvice")
    private String engAdvice;

    @SerializedName("feedback")
    private String feedback;

    @SerializedName("status")
    private String status;

    @SerializedName("pendingReason")
    private String pendingReason;

    @SerializedName("Warranty card")
    private String Warrantycard;

    @SerializedName("Report")
    private String Report;


    @SerializedName("Installation image1")
    private String Installationimage1;

    @SerializedName("Installation image2")
    private String Installationimage2;

    @SerializedName("Installation image3")
    private String Installationimage3;

    @SerializedName("Eway bill")
    private String Ewaybill;

    @SerializedName("lr")
    private String lr;

    @SerializedName("paidInvoice")
    private String paidInvoice;

    @SerializedName("spareInvoice")
    private String spareInvoice;


    public String getPaidInvoice() {
        return paidInvoice;
    }

    public void setPaidInvoice(String paidInvoice) {
        this.paidInvoice = paidInvoice;
    }

    public String getSpareInvoice() {
        return spareInvoice;
    }

    public void setSpareInvoice(String spareInvoice) {
        this.spareInvoice = spareInvoice;
    }

    public String getReport() {
        return Report;
    }

    public void setReport(String report) {
        Report = report;
    }

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public String getEngineer() {
        return engineer;
    }

    public void setEngineer(String engineer) {
        this.engineer = engineer;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public String getAttended() {
        return attended;
    }

    public void setAttended(String attended) {
        this.attended = attended;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getWorkDone() {
        return workDone;
    }

    public void setWorkDone(String workDone) {
        this.workDone = workDone;
    }

    public String getEngAdvice() {
        return engAdvice;
    }

    public void setEngAdvice(String engAdvice) {
        this.engAdvice = engAdvice;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPendingReason() {
        return pendingReason;
    }

    public void setPendingReason(String pendingReason) {
        this.pendingReason = pendingReason;
    }

    public String getWarrantycard() {
        return Warrantycard;
    }

    public void setWarrantycard(String warrantycard) {
        Warrantycard = warrantycard;
    }

    public String getInstallationimage1() {
        return Installationimage1;
    }

    public void setInstallationimage1(String installationimage1) {
        Installationimage1 = installationimage1;
    }

    public String getInstallationimage2() {
        return Installationimage2;
    }

    public void setInstallationimage2(String installationimage2) {
        Installationimage2 = installationimage2;
    }

    public String getInstallationimage3() {
        return Installationimage3;
    }

    public void setInstallationimage3(String installationimage3) {
        Installationimage3 = installationimage3;
    }

    public String getEwaybill() {
        return Ewaybill;
    }

    public void setEwaybill(String ewaybill) {
        Ewaybill = ewaybill;
    }

    public String getLr() {
        return lr;
    }

    public void setLr(String lr) {
        this.lr = lr;
    }
}
