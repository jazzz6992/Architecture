package com.vsevolodvisnevskij.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.vsevolodvisnevskij.data.entity.User;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDao {
    @Insert
    void insert(List<User> users);

    @Query("SELECT * FROM user")
    Flowable<List<User>> getAll();

    @Query("SELECT * FROM user WHERE objectId=:id LIMIT 1")
    Flowable<User> getById(String id);

    @Query("DELETE FROM user")
    void deleteAll();

    @Query("DELETE FROM user WHERE objectId=:id")
    void delete(String id);

    @Query("UPDATE user SET age=:argAge, profileUrl=:argProfileUrl, username=:argUsername WHERE objectId=:argId")
    void update(int argAge, String argProfileUrl, String argUsername, String argId);
}
