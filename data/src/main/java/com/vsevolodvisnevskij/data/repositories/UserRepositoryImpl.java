package com.vsevolodvisnevskij.data.repositories;

import android.content.Context;

import com.vsevolodvisnevskij.data.db.AppDatabase;
import com.vsevolodvisnevskij.data.db.UserDao;
import com.vsevolodvisnevskij.data.entity.User;
import com.vsevolodvisnevskij.data.net.RestService;
import com.vsevolodvisnevskij.domain.entity.UserEntity;
import com.vsevolodvisnevskij.domain.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by vsevolodvisnevskij on 16.03.2018.
 */

public class UserRepositoryImpl implements UserRepository {
    private Context context;
    private RestService restService;
    private UserDao userDao;

    @Inject
    public UserRepositoryImpl(Context context, RestService restService, AppDatabase database) {
        this.context = context;
        this.restService = restService;
        userDao = database.getUserDao();
    }

    @Override
    public Observable<UserEntity> getUser(String id) {
        return restService.loadUserById(id)
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends User>>() {
            @Override
            public ObservableSource<? extends User> apply(Throwable throwable) throws Exception {
                return userDao.getById(id).toObservable();
            }
        }).map(new Function<User, UserEntity>() {
            @Override
            public UserEntity apply(User user) throws Exception {
                return new UserEntity(user.getUsername(), user.getProfileUrl(), user.getAge(), user.getObjectId());
            }
        });
    }

    @Override
    public Observable<List<UserEntity>> getUsers(String offset) {
        return restService.loadUsers(offset).doOnNext(l -> {
            userDao.deleteAll();
            userDao.insert(l);
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends List<User>>>() {
            @Override
            public ObservableSource<? extends List<User>> apply(Throwable throwable) throws Exception {
                if (0 < Integer.valueOf(offset)) {
                    return Observable.fromArray();
                }
                return userDao.getAll().toObservable().take(1);
            }
        }).map(new Function<List<User>, List<UserEntity>>() {
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
        return restService.saveUser(user).onErrorResumeNext(new Function<Throwable, CompletableSource>() {
            @Override
            public CompletableSource apply(Throwable throwable) throws Exception {
                List<User> userList = new ArrayList<>();
                userList.add(user);
                userDao.insert(userList);
                return Completable.complete();
            }
        });
    }

    @Override
    public Completable remove(String id) {
        return restService.removeUser(id).onErrorResumeNext(new Function<Throwable, CompletableSource>() {
            @Override
            public CompletableSource apply(Throwable throwable) throws Exception {
                userDao.delete(id);
                return Completable.complete();
            }
        });
    }
}
