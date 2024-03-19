package com.example.whatsappfirebase.viewpager.data;

public class Calls {
    public String name, time;
    public int img_user, img_phone;

    public Calls(String name, String time, int img_user, int img_phone) {
        this.img_user = img_user;
        this.img_phone = img_phone;
        this.name = name;
        this.time = time;
    }
}
