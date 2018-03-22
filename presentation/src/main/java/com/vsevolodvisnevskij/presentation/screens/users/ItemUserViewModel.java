package com.vsevolodvisnevskij.presentation.screens.users;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.vsevolodvisnevskij.domain.entity.UserEntity;

/**
 * Created by vsevolodvisnevskij on 22.03.2018.
 */

public class ItemUserViewModel extends BaseObservable {
    private UserEntity entity;

    public UserEntity getEntity() {
        return entity;
    }

    public void setEntity(UserEntity entity) {
        this.entity = entity;
        notifyChange();
    }

    @Bindable
    public String getName() {
        return entity.getUserName();
    }

    public String getId() {
        return entity.getId();
    }
}
