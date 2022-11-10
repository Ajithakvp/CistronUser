package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class CompOffModel {

//     "date_of_apply":"2022-11-08",
//             "date_of_approve":"2022-11-08 01:06:34 pm",
//             "status":"Rejected"


     @SerializedName("date_of_apply")
     private String date_of_apply;

     @SerializedName("date_of_approve")
     private String date_of_approve;

     @SerializedName("status")
     private String status;

     public String getDate_of_apply() {
          return date_of_apply;
     }

     public void setDate_of_apply(String date_of_apply) {
          this.date_of_apply = date_of_apply;
     }

     public String getDate_of_approve() {
          return date_of_approve;
     }

     public void setDate_of_approve(String date_of_approve) {
          this.date_of_approve = date_of_approve;
     }

     public String getStatus() {
          return status;
     }

     public void setStatus(String status) {
          this.status = status;
     }
}
