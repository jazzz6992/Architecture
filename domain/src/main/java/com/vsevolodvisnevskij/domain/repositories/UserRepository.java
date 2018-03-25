package com.vsevolodvisnevskij.domain.repositories;

import com.vsevolodvisnevskij.domain.entity.UserEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by vsevolodvisnevskij on 16.03.2018.
 */

public interface UserRepository {
    Observable<UserEntity> getUser(String id);

    Observable<List<UserEntity>> getUsers(String offset);

    Completable save(UserEntity userEntity);

    Completable remove(String id);


}
