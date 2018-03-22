package com.vsevolodvisnevskij.presentation.screens.users;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.widget.RecyclerView;

import com.vsevolodvisnevskij.presentation.R;
import com.vsevolodvisnevskij.presentation.base.BaseMVVMActivity;
import com.vsevolodvisnevskij.presentation.base.BaseViewModel;

/**
 * Created by vsevolodvisnevskij on 22.03.2018.
 */

public class UsersActivity extends BaseMVVMActivity {
    private RecyclerView recyclerView;

    @Override
    public int provideLayoutId() {
        return R.layout.activity_users;
    }

    @Override
    public BaseViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(UsersViewModel.class);
    }
}
