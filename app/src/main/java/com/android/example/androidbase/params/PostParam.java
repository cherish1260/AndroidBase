package com.android.example.androidbase.params;

import java.util.HashMap;

public class PostParam {
    private String doctype;
    private String jsonversion;
    private String type;
    private String keyfrom;
    private String model;
    private String mid;
    private String imei;
    private String vendor;
    private String screen;
    private String ssid;
    private String network;
    private String abtest;
    private String i;

    public PostParam() {

    }

    public PostParam(String doctype, String jsonversion, String type, String keyfrom, String model, String mid, String imei, String vendor, String screen, String ssid, String network, String abtest, String i) {
        this.doctype = doctype;
        this.jsonversion = jsonversion;
        this.type = type;
        this.keyfrom = keyfrom;
        this.model = model;
        this.mid = mid;
        this.imei = imei;
        this.vendor = vendor;
        this.screen = screen;
        this.ssid = ssid;
        this.network = network;
        this.abtest = abtest;
        this.i = i;
    }

    public HashMap<String, Object> toMap() {
        HashMap map = new HashMap();
        map.put("doctype", this.doctype);
        map.put("jsonversion", this.jsonversion);
        map.put("type", this.type);
        map.put("keyfrom", this.keyfrom);
        map.put("model", this.model);
        map.put("mid", this.mid);
        map.put("imei", this.imei);
        map.put("vendor", this.vendor);
        map.put("screen", this.screen);
        map.put("ssid", this.ssid);
        map.put("network", this.network);
        map.put("abtest", this.abtest);
        map.put("i", this.i);
        return map;
    }

}
