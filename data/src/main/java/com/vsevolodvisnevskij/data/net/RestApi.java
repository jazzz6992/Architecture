package com.vsevolodvisnevskij.data.net;

import com.vsevolodvisnevskij.data.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by vsevolodvisnevskij on 19.03.2018.
 */

public interface RestApi {
    @GET("data/User")
    Observable<List<User>> loadUsers(@Query("offset") String offset);

    @GET("data/User/{id}")
    Observable<User> loadUserById(@Path("id") String id);

    @PUT("data/User")
    Completable saveUser(@Body User user);

    @DELETE("data/User/{id}")
    Completable removeUser(@Path("id") String id);

}
