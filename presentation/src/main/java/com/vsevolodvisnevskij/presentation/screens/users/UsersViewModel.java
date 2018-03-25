package com.vsevolodvisnevskij.presentation.screens.users;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vsevolodvisnevskij.app.App;
import com.vsevolodvisnevskij.domain.entity.UserEntity;
import com.vsevolodvisnevskij.domain.interactors.GetUsersUseCase;
import com.vsevolodvisnevskij.presentation.R;
import com.vsevolodvisnevskij.presentation.base.BaseViewModel;
import com.vsevolodvisnevskij.presentation.databinding.UserItemBinding;
import com.vsevolodvisnevskij.presentation.screens.user.UserActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by vsevolodvisnevskij on 22.03.2018.
 */

public class UsersViewModel extends BaseViewModel {
    @Inject
    public GetUsersUseCase getUsersUseCase;

    @Inject
    public Context context;

    private UserAdapter adapter = new UserAdapter();
    private boolean activityStarted = false;

    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }

    public UsersViewModel() {
        loadNextPage("0");
    }

    class UserHolder extends RecyclerView.ViewHolder {
        UserItemBinding binding;

        public UserHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.setViewModel(new ItemUserViewModel());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = UserActivity.getIntent(context, binding.getViewModel().getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
        }
    }

    public class UserAdapter extends RecyclerView.Adapter<UserHolder> {
        private List<UserEntity> userEntities = new ArrayList<>();

        public void addUserEntities(List<UserEntity> userEntities) {
            this.userEntities.addAll(userEntities);
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            UserItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.user_item, parent, false);
            return new UserHolder(binding.getRoot());
        }

        @Override
        public void onBindViewHolder(UserHolder holder, int position) {
            holder.binding.getViewModel().setEntity(userEntities.get(position));
            if (position == userEntities.size() - 1) {
                loadNextPage(String.valueOf(position + 1));
            }
        }

        @Override
        public int getItemCount() {
            return userEntities.size();
        }

        public void clear() {
            userEntities.clear();
        }
    }

    //    public LinearLayoutManager getLinearLayoutManager() {
    //        return linearLayoutManager;
    //    }

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
                System.out.println();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void startActivity(Intent intent) {
        activityStarted = true;
        context.startActivity(intent);
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
