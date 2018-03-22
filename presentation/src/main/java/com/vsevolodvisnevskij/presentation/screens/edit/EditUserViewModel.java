package com.vsevolodvisnevskij.presentation.screens.edit;

import android.content.Context;
import android.databinding.InverseMethod;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.widget.Toast;

import com.vsevolodvisnevskij.app.App;
import com.vsevolodvisnevskij.domain.entity.UserEntity;
import com.vsevolodvisnevskij.domain.interactors.SaveUserUseCase;
import com.vsevolodvisnevskij.presentation.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by vsevolodvisnevskij on 22.03.2018.
 */

public class EditUserViewModel extends BaseViewModel {
    private String MYTAG = "MYTAG";

    @Inject
    public SaveUserUseCase saveUserUseCase;
    @Inject
    public Context context;

    private ObservableField<String> name = new ObservableField<>();
    private ObservableField<String> profileUrl = new ObservableField<>();
    private ObservableInt age = new ObservableInt();
    private ObservableField<String> objectId = new ObservableField<>();

    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> name) {
        this.name = name;
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

    public ObservableField<String> getObjectId() {
        return objectId;
    }


    @InverseMethod("convertToAge")
    public String convertFromAge(int age) {
        return String.valueOf(age);
    }

    public int convertToAge(String s) {
        return Integer.valueOf(s);
    }

    public void save() {
        UserEntity userEntity = new UserEntity(name.get(), profileUrl.get(), age.get(), objectId.get());
        saveUserUseCase.save(userEntity).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Toast.makeText(context, "SUBSCRIBED", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onComplete() {
                Toast.makeText(context, "DONE", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(context, "FAIL", Toast.LENGTH_LONG).show();
                Log.d(MYTAG, e.getLocalizedMessage());
            }
        });
    }
}
