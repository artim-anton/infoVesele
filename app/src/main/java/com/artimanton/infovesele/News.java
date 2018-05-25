package com.artimanton.infovesele;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class News implements Parcelable, Serializable {
    private String linkImageNews = "1";
    private String nameNews = "2";
    private String linkPageNews = "3";

    public News(){

    }

    public News(String linkImageNews, String nameNews, String linkPageNews) {
        this.linkImageNews = linkImageNews;
        this.nameNews = nameNews;
        this.linkPageNews = linkPageNews;
    }

    protected News(Parcel in) {
        String [] data = new String[3];
        in.readStringArray(data);
        linkImageNews = data[0];
        nameNews = data[1];
        linkPageNews = data[2];
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
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
