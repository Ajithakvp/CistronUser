package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class WeeklyUserWMModel {

//    rs_weekly_exp": {
//            "e032": {
//        "name": "S P Jain",
//                "sum": 2480,
//                "week1": 1780,
//                "week5": "350",
//                "week6": "350"


    @SerializedName("e032")
    private String e032;

    @SerializedName("name")
    private String name;

    @SerializedName("sum")
    private String sum;

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

    public String getE032() {
        return e032;
    }

    public void setE032(String e032) {
        this.e032 = e032;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

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
