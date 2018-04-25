package com.vsevolodvisnevskij.presentation.screens.users;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vsevolodvisnevskij.app.App;
import com.vsevolodvisnevskij.domain.entity.UserEntity;
import com.vsevolodvisnevskij.domain.interactors.GetUsersUseCase;
import com.vsevolodvisnevskij.presentation.R;
import com.vsevolodvisnevskij.presentation.base.BaseAdapter;
import com.vsevolodvisnevskij.presentation.base.BaseItemViewHolder;
import com.vsevolodvisnevskij.presentation.base.BaseViewModel;
import com.vsevolodvisnevskij.presentation.databinding.UserItemBinding;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by vsevolodvisnevskij on 22.03.2018.
 */

public class UsersViewModel extends BaseViewModel<UsersRouter> {
    @Inject
    public GetUsersUseCase getUsersUseCase;

    private UserAdapter adapter = new UserAdapter();
    private boolean activityStarted = false;

    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }

    public UsersViewModel() {
        compositeDisposable.add(adapter.observeClick().subscribe(m -> {
            if (router != null) {
                startActivity();
                router.navigateToUser(((UserEntity) m.model).getId());
            }
        }));
        loadNextPage("0");
    }

    class UserHolder extends BaseItemViewHolder<UserEntity, ItemUserViewModel, UserItemBinding> {

        public UserHolder(UserItemBinding binding, ItemUserViewModel viewModel) {
            super(binding, viewModel);
        }
    }

    public class UserAdapter extends BaseAdapter<UserEntity, ItemUserViewModel> {

        public void addUserEntities(List<UserEntity> userEntities) {
            this.items.addAll(userEntities);
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            UserItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.user_item, parent, false);
            ItemUserViewModel viewModel = new ItemUserViewModel();
            return new UserHolder(binding, viewModel);
        }

        @Override
        public void onBindViewHolder(BaseItemViewHolder<UserEntity, ItemUserViewModel, ?> holder, int position) {
            super.onBindViewHolder(holder, position);
            if (position == items.size() - 1) {
                loadNextPage(String.valueOf(position + 1));
            }
        }


        public void clear() {
            items.clear();
        }
    }

    public UserAdapter gerUserAdapter() {
        return adapter;
    }

    private void loadNextPage(String offset) {
        getUsersUseCase.get(offset).subscribe(new Observer<List<UserEntity>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<UserEntity> userEntities) {
                if (userEntities.size() != 0) {
                    adapter.addUserEntities(userEntities);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void startActivity() {
        activityStarted = true;
    }

    @Override
    public void onResume() {
        if (activityStarted) {
            activityStarted = false;
            adapter.clear();
            loadNextPage("0");
        }
    }
}
