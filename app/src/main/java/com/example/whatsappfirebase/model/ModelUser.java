package com.example.whatsappfirebase.model;

public class ModelUser {
    public ModelUser(String name, String email, String uId, String imgUrl) {
        this.name = name;
        this.email = email;
        this.uId = uId;
        this.imgUrl = imgUrl;
    }

    ModelUser() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getuId() {
        return uId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    private String name;
    private String email;
    private String uId;
    private String imgUrl;
}
