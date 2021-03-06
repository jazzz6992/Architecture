package com.vsevolodvisnevskij.presentation.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.vsevolodvisnevskij.presentation.screens.users.UsersViewModel;

/**
 * Created by vsevolodvisnevskij on 22.03.2018.
 */

public class BindingAdapters {

    @BindingAdapter({"adapter"})
    public static void initRecyclerView(RecyclerView recyclerView, UsersViewModel.UserAdapter userAdapter) {
        recyclerView.setAdapter(userAdapter);
    }

    @BindingAdapter({"src"})
    public static void loadImage(ImageView view, String url) {
        if (url == null)
            return;
        Log.d("TAG", url);
        Glide.with(view.getContext()).load(url).into(view);
    }
}
