package com.vsevolodvisnevskij.presentation.screens.user;

import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.vsevolodvisnevskij.app.App;
import com.vsevolodvisnevskij.domain.entity.UserEntity;
import com.vsevolodvisnevskij.domain.interactors.DeleteUserUseCase;
import com.vsevolodvisnevskij.domain.interactors.GetUserByIdUseCase;
import com.vsevolodvisnevskij.presentation.base.BaseViewModel;
import com.vsevolodvisnevskij.presentation.screens.edit.EditUserActivity;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by vsevolodvisnevskij on 12.03.2018.
 */

public class UserViewModel extends BaseViewModel {

    @Inject
    public GetUserByIdUseCase getUserByIdUseCase;
    @Inject
    public DeleteUserUseCase deleteUserUseCase;


    private UserEntity userEntity;
    private ObservableField<String> userName = new ObservableField<>();
    private ObservableField<String> profileUrl = new ObservableField<>();
    private ObservableInt age = new ObservableInt();
    private ObservableBoolean progressVisible = new ObservableBoolean(false);

    private String id;
    private AppCompatActivity activity;

    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void onResume() {
        progressVisible.set(true);
        getUserByIdUseCase.get(id).subscribe(new Observer<UserEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(UserEntity userEntity) {
                UserViewModel.this.userEntity = userEntity;
                userName.set(userEntity.getUserName());
                profileUrl.set(userEntity.getUrl());
                age.set(userEntity.getAge());
            }

            @Override
            public void onError(Throwable e) {
                progressVisible.set(false);
            }

            @Override
            public void onComplete() {
                progressVisible.set(false);
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
    }

    public void startEditActivity() {
        Intent intent = EditUserActivity.getEditIntent(activity, userName.get(), profileUrl.get(), age.get(), id);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public void removeUser() {
        deleteUserUseCase.remove(id).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
                Log.d(MY_TEG, "onSubscribe for remove id " + id);
            }

            @Override
            public void onComplete() {
                Log.d(MY_TEG, "onComplete for remove id " + id);
                activity.finish();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(MY_TEG, "onError for remove id " + id + " message: " + e.getMessage());
            }
        });
    }

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }
}