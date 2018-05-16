package com.gstmadeeasy.Model;

/**
 * Created by Dharam on 12/28/2017.
 */

        public class Business {

    // public String BusinessID,OrganizationName,OwnerName,EmailID,ContactNo,Address,City, StateName, StateID, Country, StatusID, FullAddress, GSTCategoryID,  GSTNumber,  Status, ClientCode, uname,password,btnVerify,btnActivate,btnSuspend, btnTerminate;
    private int BusinessID;
    private String OrganizationName;
    private String OwnerName;
    private String ContactNo;
    private String EmailID;
    private String Address;
    private int CityID = 1737;
    private String City = "Delhi";
    private String StateName;
    private String Country = "India";
    private int StateID;
    private int StatusID;
    private String FullAddress;
    private int GSTCategoryID;
    private String GSTNumber;
    private String GSTCategory;
    private String Status;
    private String ClientCode;
    private String uname;
    private String password;
    private int btnVerify;
    private int btnActivate;
    private int btnSuspend;
    private int btnTerminate;
    private String Pincode = "221304";


    public byte[] imageBytes;

    public int getBusinessID() {
        return BusinessID;
    }

    public void setBusinessID(int businessID) {
        BusinessID = businessID;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getCityID() {
        return CityID;
    }

    public void setCityID(int cityID) {
        CityID = cityID;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }


    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getStateID() {
        return StateID;
    }

    public void setStateID(int stateID) {
        StateID = stateID;
    }

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int statusID) {
        StatusID = statusID;
    }

    public String getFullAddress() {
        return FullAddress;
    }

    public void setFullAddress(String fullAddress) {
        FullAddress = fullAddress;
    }

    public int getGSTCategoryID() {
        return GSTCategoryID;
    }

    public void setGSTCategoryID(int GSTCategoryID) {
        this.GSTCategoryID = GSTCategoryID;
    }

    public String getGSTNumber() {
        return GSTNumber;
    }

    public void setGSTNumber(String GSTNumber) {
        this.GSTNumber = GSTNumber;
    }

    public String getGSTCategory() {
        return GSTCategory;
    }

    public void setGSTCategory(String GSTCategory) {
        this.GSTCategory = GSTCategory;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getClientCode() {
        return ClientCode;
    }

    public void setClientCode(String clientCode) {
        ClientCode = clientCode;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBtnVerify() {
        return btnVerify;
    }

    public void setBtnVerify(int btnVerify) {
        this.btnVerify = btnVerify;
    }

    public int getBtnActivate() {
        return btnActivate;
    }

    public void setBtnActivate(int btnActivate) {
        this.btnActivate = btnActivate;
    }

    public int getBtnSuspend() {
        return btnSuspend;
    }

    public void setBtnSuspend(int btnSuspend) {
        this.btnSuspend = btnSuspend;
    }

    public int getBtnTerminate() {
        return btnTerminate;
    }

    public void setBtnTerminate(int btnTerminate) {
        this.btnTerminate = btnTerminate;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }
}
