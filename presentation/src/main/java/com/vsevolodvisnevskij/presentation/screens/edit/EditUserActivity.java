package com.vsevolodvisnevskij.presentation.screens.edit;

import android.content.Context;
import android.content.Intent;

import com.vsevolodvisnevskij.presentation.R;
import com.vsevolodvisnevskij.presentation.base.BaseMVVMActivity;
import com.vsevolodvisnevskij.presentation.base.BaseViewModel;

public class EditUserActivity extends BaseMVVMActivity {

    private static final String EXTRA_NAME = "com.vsevolodvisnevskij.presentation.NAME";
    private static final String EXTRA_URL = "com.vsevolodvisnevskij.presentation.URL";
    private static final String EXTRA_AGE = "com.vsevolodvisnevskij.presentation.AGE";
    private static final String EXTRA_ID = "com.vsevolodvisnevskij.presentation.ID";


    @Override
    public int provideLayoutId() {
        return R.layout.activity_edit;
    }

    @Override
    public BaseViewModel provideViewModel() {
        EditUserViewModel editUserViewModel = new EditUserViewModel();
        Intent intent = getIntent();
        editUserViewModel.getName().set(intent.getStringExtra(EXTRA_NAME));
        editUserViewModel.getProfileUrl().set(intent.getStringExtra(EXTRA_URL));
        editUserViewModel.getAge().set(intent.getIntExtra(EXTRA_AGE, 0));
        editUserViewModel.getObjectId().set(intent.getStringExtra(EXTRA_ID));
        return editUserViewModel;
    }

    public static Intent getIntent(Context context, String name, String url, int age, String id) {
        Intent intent = new Intent(context, EditUserActivity.class);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_AGE, age);
        intent.putExtra(EXTRA_ID, id);
        return intent;
    }
}
