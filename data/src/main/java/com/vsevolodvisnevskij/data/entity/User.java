package com.vsevolodvisnevskij.data.entity;

/**
 * Created by vsevolodvisnevskij on 19.03.2018.
 */

public class User {

    private String objectId;
    private int age;
    private String profileUrl;
    private String username;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
