package com.vsevolodvisnevskij.presentation.screens.users;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.vsevolodvisnevskij.presentation.R;
import com.vsevolodvisnevskij.presentation.base.BaseMVVMActivity;
import com.vsevolodvisnevskij.presentation.databinding.ActivityUsersBinding;

/**
 * Created by vsevolodvisnevskij on 22.03.2018.
 */

public class UsersActivity extends BaseMVVMActivity<ActivityUsersBinding, UsersViewModel, UsersRouter> {

    @Override
    public int provideLayoutId() {
        return R.layout.activity_users;
    }

    @Override
    public UsersViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(UsersViewModel.class);
    }

    @Override
    public UsersRouter provideRouter() {
        return new UsersRouter(this);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_users_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_user:
                viewModel.startActivity();
                if (router != null) {
                    router.navigateToEditUser();
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
