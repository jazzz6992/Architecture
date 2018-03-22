package com.vsevolodvisnevskij.domain.entity;

/**
 * Created by vsevolodvisnevskij on 12.03.2018.
 */

public class UserEntity {
    private String userName;
    private String url;
    private int age;
    private String id;

    public UserEntity(String userName, String url, int age, String id) {
        this.userName = userName;
        this.url = url;
        this.age = age;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUrl() {
        return url;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }
}
