package com.vsevolodvisnevskij.data.repositories;

import android.content.Context;

import com.vsevolodvisnevskij.data.entity.User;
import com.vsevolodvisnevskij.data.net.RestService;
import com.vsevolodvisnevskij.domain.entity.UserEntity;
import com.vsevolodvisnevskij.domain.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by vsevolodvisnevskij on 16.03.2018.
 */

public class UserRepositoryImpl implements UserRepository {
    private Context context;
    private RestService restService;

    @Inject
    public UserRepositoryImpl(Context context, RestService restService) {
        this.context = context;
        this.restService = restService;
    }

    @Override
    public Observable<UserEntity> get(String id) {
        return restService.loadUserById(id).map(new Function<User, UserEntity>() {
            @Override
            public UserEntity apply(User user) throws Exception {
                return new UserEntity(user.getUsername(), user.getProfileUrl(), user.getAge(), user.getObjectId());
            }
        });
    }

    @Override
    public Observable<List<UserEntity>> get() {
        return restService.loadUsers().map(new Function<List<User>, List<UserEntity>>() {
            @Override
            public List<UserEntity> apply(List<User> users) throws Exception {
                List<UserEntity> list = new ArrayList<>();
                for (User u : users) {
                    list.add(new UserEntity(u.getUsername(), u.getProfileUrl(), u.getAge(), u.getObjectId()));
                }
                return list;
            }
        });
    }

    @Override
    public Completable save(UserEntity userEntity) {
        User user = new User();
        user.setUsername(userEntity.getUserName());
        user.setProfileUrl(userEntity.getUrl());
        user.setAge(userEntity.getAge());
        user.setObjectId(userEntity.getId());
        return restService.saveUser(user);
    }

    @Override
    public Completable remove() {
        return null;
    }
}
