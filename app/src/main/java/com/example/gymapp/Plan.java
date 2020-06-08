package com.example.gymapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Plan implements Parcelable {
    private  Training training;
    private int minutes;
    private String day;
    private boolean isFinished;

    public Plan(Training training, int minutes, String day, boolean isFinished) {
        this.training = training;
        this.minutes = minutes;
        this.day = day;
        this.isFinished = isFinished;
    }

    protected Plan(Parcel in) {
        training = in.readParcelable(Training.class.getClassLoader());
        minutes = in.readInt();
        day = in.readString();
        isFinished = in.readByte() != 0;
    }

    public static final Creator<Plan> CREATOR = new Creator<Plan>() {
        @Override
        public Plan createFromParcel(Parcel in) {
            return new Plan(in);
        }

        @Override
        public Plan[] newArray(int size) {
            return new Plan[size];
        }
    };

    @Override
    public String toString() {
        return "Plan{" +
                "training=" + training +
                ", minutes=" + minutes +
                ", day='" + day + '\'' +
                ", isFinished=" + isFinished +
                '}';
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(training, flags);
        dest.writeInt(minutes);
        dest.writeString(day);
        dest.writeByte((byte) (isFinished ? 1 : 0));
    }
}
