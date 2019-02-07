package com.dentistry.ahmed.orderonline.Model;

public class Item {

    private String image;
    private String color;
    private String company;
    private String price;
    private String name;
    private String description;


    public Item() {
    }

    public Item(String image, String color, String company, String price, String name, String description) {
        this.image = image;
        this.color = color;
        this.company = company;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
