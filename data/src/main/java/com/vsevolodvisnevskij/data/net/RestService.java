package com.vsevolodvisnevskij.data.net;

import com.vsevolodvisnevskij.data.entity.Error;
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
    private ErrorTransformer errorTransformer;

    @Inject
    public RestService(RestApi restApi, ErrorTransformer errorTransformer) {
        this.restApi = restApi;
        this.errorTransformer = errorTransformer;
    }

    public Observable<List<User>> loadUsers(String offset) {
        return restApi.loadUsers(offset).compose(errorTransformer.<List<User>, Error>parseHttpError());
    }

    public Observable<User> loadUserById(String id) {
        return restApi.loadUserById(id).compose(errorTransformer.<User, Error>parseHttpError());
    }

    public Completable saveUser(User user) {
        return restApi.saveUser(user).compose(errorTransformer.parseHttpErrorCompletable());
    }

    public Completable removeUser(String id) {
        return restApi.removeUser(id).compose(errorTransformer.parseHttpErrorCompletable());
    }
}
