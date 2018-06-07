package com.artimanton.infovesele.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AdvertModel implements Serializable{

    public String price;
    public String more_info_advert;
    public String info_advert;
    public String phone;
    public String key;

    public AdvertModel(){

    }

    public AdvertModel(String price, String more_info_advert, String info_advert, String phone, String key) {
        this.price = price;
        this.more_info_advert = more_info_advert;
        this.info_advert = info_advert;
        this.phone = phone;
        this.key = key;
    }

    @Override
    public String toString() {
        return "AdvertModel{" +
                "price='" + price + '\'' +
                ", more_info_advert='" + more_info_advert + '\'' +
                ", info_advert='" + info_advert + '\'' +
                ", phone='" + phone + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("more_info_advert", more_info_advert);
        result.put("info_advert", info_advert);
        result.put("phone", phone);
        result.put("key", key);
        result.put("price", price);
        return result;
    }
}