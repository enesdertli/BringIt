package com.example.bringit;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Products implements Parcelable {
    private String description;
    private int cost;   //string olacak
    //private String food_name;
    //private String section;
    private List<String> foods = new ArrayList<>();

    public Products(String description, int cost, List<String> foods) {
        this.description = description;
        this.cost = cost;
        this.foods = foods;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<String> getFoods() {
        return foods;
    }

    public void setFoods(List<String> foods) {
        this.foods = foods;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeInt(this.cost);
        dest.writeStringList(this.foods);
    }

    public void readFromParcel(Parcel source) {
        this.description = source.readString();
        this.cost = source.readInt();
        this.foods = source.createStringArrayList();
    }

    protected Products(Parcel in) {
        this.description = in.readString();
        this.cost = in.readInt();
        this.foods = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Products> CREATOR = new Parcelable.Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel source) {
            return new Products(source);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };
}
