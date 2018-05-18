package com.artimanton.infovesele.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TaxiModel implements Serializable{

    public String info_car;
    public String phone;
    public String key;

    public TaxiModel(){

    }

    public TaxiModel(String info_car, String phone, String key) {
        this.info_car = info_car;
        this.phone = phone;
        this.key = key;
    }

    @Override
    public String toString() {
        return "TaxiModel{" +
                "info_car='" + info_car + '\'' +
                ", phone='" + phone + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("info_car", info_car);
        result.put("phone", phone);
        result.put("key", key);
        return result;
    }
}