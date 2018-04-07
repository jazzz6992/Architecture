package com.vsevolodvisnevskij.presentation.screens.user;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;

import com.vsevolodvisnevskij.presentation.R;
import com.vsevolodvisnevskij.presentation.base.BaseMVVMActivity;
import com.vsevolodvisnevskij.presentation.databinding.ActivityUserBinding;

/**
 * Created by vsevolodvisnevskij on 12.03.2018.
 */

public class UserActivity extends BaseMVVMActivity<ActivityUserBinding, UserViewModel, UserRouter> {

    private static final String EXTRA_ID = "com.vsevolodvisnevskij.presentation.ID";

    @Override
    public int provideLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    public UserViewModel provideViewModel() {
        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.setId(getIntent().getStringExtra(EXTRA_ID));
        return userViewModel;
    }

    @Override
    public UserRouter provideRouter() {
        return new UserRouter(this);
    }

    public static Intent getIntent(Context context, String id) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(EXTRA_ID, id);
        return intent;
    }
}
