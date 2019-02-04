package com.dentistry.ahmed.orderonline.Model;

public class Order {

    private String orderID;
    private String userName;
    private String orderTitle;
    private String orderDescription;
    private String imageUrl;


    public Order(String orderID) {}

    public Order(String orderID, String userName, String orderTitle, String orderDescription, String imageUrl) {
        this.orderID = orderID;
        this.userName = userName;
        this.orderTitle = orderTitle;
        this.orderDescription = orderDescription;
        this.imageUrl = imageUrl;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
