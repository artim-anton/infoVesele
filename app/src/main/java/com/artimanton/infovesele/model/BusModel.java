package com.artimanton.infovesele.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BusModel implements Serializable{

    public String time_go;
    public String time_finish;
    public String phone;
    public String key;

    public BusModel(){

    }

    public BusModel(String time_go, String time_finish, String phone, String key) {
        this.time_go = time_go;
        this.time_finish = time_finish;
        this.phone = phone;
        this.key = key;
    }

    @Override
    public String toString() {
        return "BusModel{" +
                "time_go='" + time_go + '\'' +
                ", time_finish='" + time_finish + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("time_go", time_go);
        result.put("time_finish", time_finish);
        result.put("phone", phone);
        result.put("key", key);
        return result;
    }
}
