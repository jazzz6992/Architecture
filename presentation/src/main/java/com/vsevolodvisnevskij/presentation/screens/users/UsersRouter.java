package com.vsevolodvisnevskij.presentation.screens.users;

import android.app.Activity;
import android.content.Intent;

import com.vsevolodvisnevskij.presentation.base.Router;
import com.vsevolodvisnevskij.presentation.screens.edit.EditUserActivity;
import com.vsevolodvisnevskij.presentation.screens.user.UserActivity;

public class UsersRouter extends Router {


    public UsersRouter(Activity activity) {
        super(activity);
    }

    public void navigateToUser(String id) {
        Intent intent = UserActivity.getIntent(getActivity(), id);
        getActivity().startActivity(intent);
    }

    public void navigateToEditUser() {
        Intent intent = EditUserActivity.getAddIntent(getActivity());
        getActivity().startActivity(intent);
    }
}
