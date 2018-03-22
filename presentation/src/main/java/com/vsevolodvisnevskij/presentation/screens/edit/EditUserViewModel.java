package com.vsevolodvisnevskij.presentation.screens.edit;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.vsevolodvisnevskij.app.App;
import com.vsevolodvisnevskij.presentation.base.BaseViewModel;

/**
 * Created by vsevolodvisnevskij on 22.03.2018.
 */

public class EditUserViewModel extends BaseViewModel {
    private ObservableField<String> name;
    private ObservableField<String> profileUrl;
    private ObservableInt age;
    private ObservableField<String> objectId;

    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }
}
