package com.gstmadeeasy.Model;

/**
 * Created by Dharam on 4/10/2018.
 */

    public class GSTCategory {

    int ID;
    String CategoryName;

    public GSTCategory() {
    }

    public GSTCategory(String categoryName) {
        CategoryName = categoryName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    @Override
    public String toString() {
        return getCategoryName();
    }
}
