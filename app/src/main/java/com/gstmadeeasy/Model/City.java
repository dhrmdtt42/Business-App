package com.gstmadeeasy.Model;

/**
 * Created by Dharam on 4/10/2018.
 */

public class City {

    int ID;
    String CityName;

    public City() {
    }

    public City(String cityName) {
        CityName = cityName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    @Override
    public String toString() {
        return getCityName();
    }
}
