package com.vsevolodvisnevskij.presentation.screens.edit;

import android.databinding.InverseMethod;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

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

    @Inject
    public SaveUserUseCase saveUserUseCase;

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
        if (s.length() == 0) {
            return 0;
        }
        return Integer.valueOf(s);
    }

    public void save() {
        UserEntity userEntity = new UserEntity(name.get(), profileUrl.get(), age.get(), objectId.get());
        saveUserUseCase.save(userEntity).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onComplete() {
                if (router != null) {
                    router.back();
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
