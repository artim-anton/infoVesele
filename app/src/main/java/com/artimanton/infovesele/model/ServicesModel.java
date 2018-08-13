package com.artimanton.infovesele.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ServicesModel implements Serializable{

    public String instagram;
    public String facebook;
    public String more_info_services;
    public String info_services;
    public String phone;
    public String key;

    public ServicesModel(){

    }

    public ServicesModel(String instagram, String facebook, String more_info_services, String info_services, String phone, String key) {
        this.instagram = instagram;
        this.facebook = facebook;
        this.more_info_services = more_info_services;
        this.info_services = info_services;
        this.phone = phone;
        this.key = key;
    }

    @Override
    public String toString() {
        return "ServicesModel{" +
                "instagram='" + instagram + '\'' +
                ", facebook='" + facebook + '\'' +
                ", more_info_services='" + more_info_services + '\'' +
                ", info_services='" + info_services + '\'' +
                ", phone='" + phone + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("instagram",instagram);
        result.put("facebook",facebook);
        result.put("more_info_services", more_info_services);
        result.put("info_services", info_services);
        result.put("phone", phone);
        result.put("key", key);
        return result;
    }
}