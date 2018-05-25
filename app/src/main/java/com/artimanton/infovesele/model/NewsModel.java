package com.artimanton.infovesele.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class NewsModel implements Parcelable, Serializable {
    private String linkImageNews = "1";
    private String nameNews = "2";
    private String linkPageNews = "3";

    public NewsModel(){

    }

    public NewsModel(String linkImageNews, String nameNews, String linkPageNews) {
        this.linkImageNews = linkImageNews;
        this.nameNews = nameNews;
        this.linkPageNews = linkPageNews;
    }

    protected NewsModel(Parcel in) {
        String [] data = new String[3];
        in.readStringArray(data);
        linkImageNews = data[0];
        nameNews = data[1];
        linkPageNews = data[2];
    }

    public static final Creator<NewsModel> CREATOR = new Creator<NewsModel>() {
        @Override
        public NewsModel createFromParcel(Parcel in) {
            return new NewsModel(in);
        }

        @Override
        public NewsModel[] newArray(int size) {
            return new NewsModel[size];
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
