package com.vsevolodvisnevskij.presentation.screens.edit;

import android.databinding.InverseMethod;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.vsevolodvisnevskij.app.App;
import com.vsevolodvisnevskij.presentation.base.BaseViewModel;

/**
 * Created by vsevolodvisnevskij on 22.03.2018.
 */

public class EditUserViewModel extends BaseViewModel {
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
}
