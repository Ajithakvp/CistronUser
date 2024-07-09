package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class MyStockListSEModel {

    //"label": "cspl",
    //      "id": "95",
    //      "part_no": "TMXSW104",
    //      "name": "24V-5 AMPS POWER SUPPLY",
    //      "price": "3201",
    //      "quantity": "1"
    //store_qty": "324.00",
    //      "coord_qty": "0",
    //      "eng_qty": "0"
    //series
    //   //      "s": "2"


    @SerializedName("label")
    private String label;

    @SerializedName("id")
    private String id;

    @SerializedName("part_no")
    private String part_no;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private String price;

    @SerializedName("quantity")
    private String quantity;

    @SerializedName("store_qty")
    private String store_qty;

    @SerializedName("coord_qty")
    private String coord_qty;

    @SerializedName("eng_qty")
    private String eng_qty;


    @SerializedName("series")
    private String series;

    @SerializedName("s")
    private String s;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getStore_qty() {
        return store_qty;
    }

    public void setStore_qty(String store_qty) {
        this.store_qty = store_qty;
    }

    public String getCoord_qty() {
        return coord_qty;
    }

    public void setCoord_qty(String coord_qty) {
        this.coord_qty = coord_qty;
    }

    public String getEng_qty() {
        return eng_qty;
    }

    public void setEng_qty(String eng_qty) {
        this.eng_qty = eng_qty;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPart_no() {
        return part_no;
    }

    public void setPart_no(String part_no) {
        this.part_no = part_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
