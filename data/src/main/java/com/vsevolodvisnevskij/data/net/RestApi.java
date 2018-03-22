package com.vsevolodvisnevskij.data.net;

import com.vsevolodvisnevskij.data.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by vsevolodvisnevskij on 19.03.2018.
 */

public interface RestApi {
    @GET("data/User")
    Observable<List<User>> loadUsers();

    @GET("data/User/{id}")
    Observable<User> loadUserById(@Path("id") String id);

}
