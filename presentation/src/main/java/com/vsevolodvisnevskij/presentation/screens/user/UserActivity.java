package com.vsevolodvisnevskij.presentation.screens.user;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vsevolodvisnevskij.app.App;
import com.vsevolodvisnevskij.presentation.R;
import com.vsevolodvisnevskij.presentation.base.BaseMVVMActivity;
import com.vsevolodvisnevskij.presentation.databinding.ActivityUserBinding;
import com.vsevolodvisnevskij.presentation.utils.ImageChooser;

/**
 * Created by vsevolodvisnevskij on 12.03.2018.
 */

public class UserActivity extends BaseMVVMActivity<ActivityUserBinding, UserViewModel, UserRouter> {

    private static final String EXTRA_ID = "com.vsevolodvisnevskij.presentation.ID";
    private static final String INTRO = "intro";
    private SharedPreferences sharedPreferences;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_burger_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //можно, но не охота
            }
        });
        toolbar.inflateMenu(R.menu.activity_user_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset_intro:
                getSharedPreferences(App.APP_PREFERENCES, Context.MODE_PRIVATE).edit().putInt(INTRO, 0).apply();
                binding.constraintContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startIntro();
                    }
                });
                startIntro();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences(App.APP_PREFERENCES, Context.MODE_PRIVATE);
        binding.backgrownd.setVisibility(View.GONE);
        binding.avatarDescTextView.setVisibility(View.GONE);
        binding.nameDescTextView.setVisibility(View.GONE);
        binding.ageDescTextView.setVisibility(View.GONE);
        int introCount = (sharedPreferences.getInt(INTRO, 0));
        if (introCount < 3) {
            binding.constraintContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startIntro();
                }
            });
            startIntro();
        }
    }

    private void startIntro() {
        int count = sharedPreferences.getInt(INTRO, 0);
        switch (count) {
            case 0:
                binding.backgrownd.bringToFront();
                binding.backgrownd.setVisibility(View.VISIBLE);
                binding.imageView.bringToFront();
                binding.avatarDescTextView.setVisibility(View.VISIBLE);
                binding.avatarDescTextView.bringToFront();
                binding.nameDescTextView.setVisibility(View.GONE);
                binding.ageDescTextView.setVisibility(View.GONE);
                break;
            case 1:
                binding.backgrownd.bringToFront();
                binding.backgrownd.setVisibility(View.VISIBLE);
                binding.userName.bringToFront();
                binding.nameDescTextView.setVisibility(View.VISIBLE);
                binding.nameDescTextView.bringToFront();
                binding.avatarDescTextView.setVisibility(View.GONE);
                binding.ageDescTextView.setVisibility(View.GONE);
                break;
            case 2:
                binding.backgrownd.bringToFront();
                binding.backgrownd.setVisibility(View.VISIBLE);
                binding.age.bringToFront();
                binding.ageDescTextView.setVisibility(View.VISIBLE);
                binding.ageDescTextView.bringToFront();
                binding.nameDescTextView.setVisibility(View.GONE);
                binding.avatarDescTextView.setVisibility(View.GONE);
                break;
            default:
                binding.backgrownd.setVisibility(View.GONE);
                binding.avatarDescTextView.setVisibility(View.GONE);
                binding.nameDescTextView.setVisibility(View.GONE);
                binding.ageDescTextView.setVisibility(View.GONE);
                binding.constraintContainer.setOnClickListener(null);
                break;
        }
        sharedPreferences.edit().putInt(INTRO, count + 1).apply();
    }
}
