package com.example.storeunited;

public class Sneakers {

    private String img;
    private String ID;
    private String size;
    private String userID;

    public Sneakers(String img, String ID, String size, String userID) {
        this.img = img;
        this.ID = ID;
        this.size = size;
        this.userID = userID;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Sneakers(){}

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
