package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class WeekRangeWmModel {


//     "week_range": {
//        "week1": "2022-09-25 to 2022-10-01",
//                "week2": "2022-10-02 to 2022-10-08",
//                "week3": "2022-10-09 to 2022-10-15",
//                "week4": "2022-10-16 to 2022-10-22",
//                "week5": "2022-10-23 to 2022-10-29",
//                "week6": "2022-10-30 to 2022-11-05"


    @SerializedName("week1")
    private String week1;

    @SerializedName("week2")
    private String week2;

    @SerializedName("week3")
    private String week3;

    @SerializedName("week4")
    private String week4;

    @SerializedName("week5")
    private String week5;

    @SerializedName("week6")
    private String week6;

    public String getWeek1() {
        return week1;
    }

    public void setWeek1(String week1) {
        this.week1 = week1;
    }

    public String getWeek2() {
        return week2;
    }

    public void setWeek2(String week2) {
        this.week2 = week2;
    }

    public String getWeek3() {
        return week3;
    }

    public void setWeek3(String week3) {
        this.week3 = week3;
    }

    public String getWeek4() {
        return week4;
    }

    public void setWeek4(String week4) {
        this.week4 = week4;
    }

    public String getWeek5() {
        return week5;
    }

    public void setWeek5(String week5) {
        this.week5 = week5;
    }

    public String getWeek6() {
        return week6;
    }

    public void setWeek6(String week6) {
        this.week6 = week6;
    }
}
