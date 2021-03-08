package com.nirvik.monsterchat.models;

public class NameMessage {
    String name;
    String message;
    String userid;

    public NameMessage(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public NameMessage(String name, String message, String userid) {
        this.name = name;
        this.message = message;
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
