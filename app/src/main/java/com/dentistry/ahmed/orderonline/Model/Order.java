package com.dentistry.ahmed.orderonline.Model;

public class Order {

    private String id;
    private String userName;
    private String title;
    private String Email;
    private String image_URL;
    private String price;
    private String description;
    private String color;
    private int quantity;

    public Order(String orderID) {}

    public Order(String id, String userName, String title, String email, String image_URL, String price, String description, String color, int quantity) {
        this.id = id;
        this.userName = userName;
        this.title = title;
        Email = email;
        this.image_URL = image_URL;
        this.price = price;
        this.description = description;
        this.color = color;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getImage_URL() {
        return image_URL;
    }

    public void setImage_URL(String image_URL) {
        this.image_URL = image_URL;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
