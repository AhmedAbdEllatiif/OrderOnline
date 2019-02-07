package com.dentistry.ahmed.orderonline.Model;

public class Color {

    String URL;
    String name;


    public Color() {}

    public Color(String URL, String name) {
        this.URL = URL;
        this.name = name;
    }

    public String getURl() {
        return URL;
    }

    public void setURl(String URL) {
        this.URL = URL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


