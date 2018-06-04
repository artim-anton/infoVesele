package com.artimanton.infovesele.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

import java.io.Serializable;

public class AfishaModel extends SugarRecord implements Parcelable, Serializable {
    private String linkImageNews;
    private String nameNews;
    private String linkPageNews;

    public AfishaModel(){

    }

    public AfishaModel(String linkImageNews, String nameNews, String linkPageNews) {
        this.linkImageNews = linkImageNews;
        this.nameNews = nameNews;
        this.linkPageNews = linkPageNews;
    }

    protected AfishaModel(Parcel in) {
        String [] data = new String[3];
        in.readStringArray(data);
        linkImageNews = data[0];
        nameNews = data[1];
        linkPageNews = data[2];
    }

    public static final Creator<AfishaModel> CREATOR = new Creator<AfishaModel>() {
        @Override
        public AfishaModel createFromParcel(Parcel in) {
            return new AfishaModel(in);
        }

        @Override
        public AfishaModel[] newArray(int size) {
            return new AfishaModel[size];
        }
    };

    public String getLinkImageNews() {
        return linkImageNews;
    }

    public void setLinkImageNews(String linkImageNews) {
        this.linkImageNews = linkImageNews;
    }

    public String getNameNews() {
        return nameNews;
    }

    public void setNameNews(String nameNews) {
        this.nameNews = nameNews;
    }

    public String getLinkPageNews() {
        return linkPageNews;
    }

    public void setLinkPageNews(String linkPageNews) {
        this.linkPageNews = linkPageNews;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{linkImageNews, nameNews, linkPageNews});
    }
}
