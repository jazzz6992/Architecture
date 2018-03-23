package com.vsevolodvisnevskij.data.net;

import com.vsevolodvisnevskij.data.entity.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by vsevolodvisnevskij on 19.03.2018.
 */

@Singleton
public class RestService {
    private RestApi restApi;

    @Inject
    public RestService(RestApi restApi) {
        this.restApi = restApi;
    }

    public Observable<List<User>> loadUsers() {
        return restApi.loadUsers();
    }

    public Observable<User> loadUserById(String id) {
        return restApi.loadUserById(id);
    }

    public Completable saveUser(User user) {
        return restApi.saveUser(user);
    }

    public Completable removeUser(String id) {
        return restApi.removeUser(id);
    }
}
