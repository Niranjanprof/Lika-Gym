package com.example.gymapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Training implements Parcelable {
    private int id;
    private String name;
    private String shortDesc;
    private String LongDesc;
    private String ImageUrl;

    public Training(int id, String name, String shortDesc, String longDesc, String imageUrl) {
        this.id = id;
        this.name = name;
        this.shortDesc = shortDesc;
        LongDesc = longDesc;
        ImageUrl = imageUrl;
    }

    protected Training(Parcel in) {
        id = in.readInt();
        name = in.readString();
        shortDesc = in.readString();
        LongDesc = in.readString();
        ImageUrl = in.readString();
    }

    public static final Creator<Training> CREATOR = new Creator<Training>() {
        @Override
        public Training createFromParcel(Parcel in) {
            return new Training(in);
        }

        @Override
        public Training[] newArray(int size) {
            return new Training[size];
        }
    };

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", LongDesc='" + LongDesc + '\'' +
                ", ImageUrl='" + ImageUrl + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return LongDesc;
    }

    public void setLongDesc(String longDesc) {
        LongDesc = longDesc;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(shortDesc);
        dest.writeString(LongDesc);
        dest.writeString(ImageUrl);
    }
}
