package com.vsevolodvisnevskij.presentation.base;

import android.databinding.BaseObservable;

public abstract class BaseItemViewModel<Model> extends BaseObservable {
    public abstract void setItem(Model model, int position);

    public void init() {

    }

    public void release() {

    }
}
