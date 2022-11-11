package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LeaveFormAllocatedleave {
//    "emptype": "3",
//            "category": "leave form",
//            "allocated leave": {
//        "cl": "0",
//                "ml": "0",
//                "pl": "0",
//                "probl": "1"
//    },
//            "available leave": {
//        "cl": "0",
//                "ml": "0",
//                "pl": "0",
//                "probl": "0"
//    },
//            "leave_reason": {
//        "reasons": "Dependent Sick / Surgery/ Hospitalized;Family Occasions;Health Discomfort;Long travel or trip ( Visiting temple,Visiting relative house );Not Feasible to travel;Personal Emergency;Shifting to new house;Sick and Hospitalized;Sick and not Hospitalized;Surgery /Hospitalization;To attend Self / Friends/ Relative marriage;To get essential needs",
//                "leavetype": "ML;CL,PL;CL,PL;CL,PL;CL,PL;CL,PL;CL,PL;ML;ML;ML;CL,PL;CL,PL"
//    }

    @SerializedName("emptype")
    private String emptype;

    @SerializedName("category")
    private String category;

    @SerializedName("allocated_leave")
    private AllocatedleaveModel allocatedleaveModel;

    @SerializedName("available_leave")
    private AvailableLeaveModel availableLeaveModel;

    @SerializedName("leave_reason")
    private LeaveResons leaveResons;

    public String getEmptype() {
        return emptype;
    }

    public void setEmptype(String emptype) {
        this.emptype = emptype;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public AllocatedleaveModel getAllocatedleaveModel() {
        return allocatedleaveModel;
    }

    public void setAllocatedleaveModel(AllocatedleaveModel allocatedleaveModel) {
        this.allocatedleaveModel = allocatedleaveModel;
    }

    public AvailableLeaveModel getAvailableLeaveModel() {
        return availableLeaveModel;
    }

    public void setAvailableLeaveModel(AvailableLeaveModel availableLeaveModel) {
        this.availableLeaveModel = availableLeaveModel;
    }

    public LeaveResons getLeaveResons() {
        return leaveResons;
    }

    public void setLeaveResons(LeaveResons leaveResons) {
        this.leaveResons = leaveResons;
    }
}
