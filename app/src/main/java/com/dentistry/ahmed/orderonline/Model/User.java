package com.dentistry.ahmed.orderonline.Model;

public class User {

    private String id;
    private String userName;
    private String ImageURL;
    private String Email;
    private String birthday;

    public User() {}

    public User(String id, String userName, String ImageURL,String birthday) {
        this.id = id;
        this.userName = userName;
        this.ImageURL = ImageURL;
        this.birthday = birthday;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
