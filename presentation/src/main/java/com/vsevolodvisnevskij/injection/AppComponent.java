package com.vsevolodvisnevskij.injection;

import com.vsevolodvisnevskij.app.App;
import com.vsevolodvisnevskij.presentation.screens.edit.EditUserViewModel;
import com.vsevolodvisnevskij.presentation.screens.user.UserViewModel;
import com.vsevolodvisnevskij.presentation.screens.users.UsersViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vsevolodvisnevskij on 19.03.2018.
 */

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(UserViewModel userViewModel);

    void inject(UsersViewModel usersViewModel);

    void inject(EditUserViewModel usersViewModel);

//    void inject(App app);
}
