package com.vsevolodvisnevskij.presentation.screens.users;

import android.databinding.Bindable;

import com.vsevolodvisnevskij.domain.entity.UserEntity;
import com.vsevolodvisnevskij.presentation.base.BaseItemViewModel;

/**
 * Created by vsevolodvisnevskij on 22.03.2018.
 */

public class ItemUserViewModel extends BaseItemViewModel<UserEntity> {
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

    @Override
    public void setItem(UserEntity userEntity, int position) {
        this.entity = userEntity;
        notifyChange();
    }
}
