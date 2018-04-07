package com.vsevolodvisnevskij.presentation.screens.user;

import android.app.Activity;
import android.content.Intent;

import com.vsevolodvisnevskij.presentation.base.Router;
import com.vsevolodvisnevskij.presentation.screens.edit.EditUserActivity;

public class UserRouter extends Router {
    public UserRouter(Activity activity) {
        super(activity);
    }

    public void navigateToEditUser(String name, String url, int age, String id) {
        Intent intent = EditUserActivity.getEditIntent(getActivity(), name, url, age, id);
        getActivity().startActivity(intent);
    }
}
