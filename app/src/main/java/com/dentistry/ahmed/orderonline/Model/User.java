package com.dentistry.ahmed.orderonline.Model;

public class User {

    private String id;
    private String userName;
    private String ImageURL;
    private String Email;

    public User() {}

    public User(String id, String userName, String imageURL, String email) {
        this.id = id;
        this.userName = userName;
        ImageURL = imageURL;
        Email = email;
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

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
