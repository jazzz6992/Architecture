package com.vsevolodvisnevskij.presentation.screens.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;

import com.vsevolodvisnevskij.app.App;
import com.vsevolodvisnevskij.domain.entity.UserEntity;
import com.vsevolodvisnevskij.domain.interactors.GetUserByIdUseCase;
import com.vsevolodvisnevskij.presentation.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by vsevolodvisnevskij on 12.03.2018.
 */

public class UserViewModel extends BaseViewModel {

    @Inject
    public GetUserByIdUseCase getUserByIdUseCase;

    @Inject
    public Context context;

    private ObservableField<String> userName = new ObservableField<>();
    private ObservableField<String> profileUrl = new ObservableField<>();
    private ObservableInt age = new ObservableInt();
    private ObservableBoolean progressVisible = new ObservableBoolean(false);

    private String id;

    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }

    private void start() {
        progressVisible.set(true);
        getUserByIdUseCase.get(id).subscribe(new Observer<UserEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
                Log.d("myTag", "onSubscribe");
            }

            @Override
            public void onNext(UserEntity userEntity) {
                userName.set(userEntity.getUserName());
                profileUrl.set(userEntity.getUrl());
                age.set(userEntity.getAge());
                Log.d("myTag", "onNext2222 !" + userEntity.getUrl());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("myTag", "onError");
            }

            @Override
            public void onComplete() {
                progressVisible.set(false);
                Log.d("myTag", "onComplete");
            }
        });
    }

    public ObservableField<String> getUserName() {
        return userName;
    }

    public void setUserName(ObservableField<String> userName) {
        this.userName = userName;
    }

    public ObservableField<String> getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(ObservableField<String> profileUrl) {
        this.profileUrl = profileUrl;
    }

    public ObservableInt getAge() {
        return age;
    }

    public void setAge(ObservableInt age) {
        this.age = age;
    }

    public ObservableBoolean getProgressVisible() {
        return progressVisible;
    }

    public void setProgressVisible(ObservableBoolean progressVisible) {
        this.progressVisible = progressVisible;
    }

    public void setId(String id) {
        this.id = id;
        start();
    }
}