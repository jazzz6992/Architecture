package com.vsevolodvisnevskij.presentation.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.vsevolodvisnevskij.presentation.BR;

public abstract class BaseItemViewHolder<Model, ViewModel extends BaseItemViewModel, DataBinding extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private DataBinding binding;
    private ViewModel viewModel;

    public BaseItemViewHolder(DataBinding binding, ViewModel viewModel) {
        super(binding.getRoot());
        this.binding = binding;
        this.viewModel = viewModel;
        viewModel.init();
        initViewModel();
    }

    protected void initViewModel() {
        binding.setVariable(BR.viewModel, viewModel);
    }

    public void bind(Model model, int position) {
        viewModel.setItem(model, position);
        binding.executePendingBindings();
    }

    public void release() {
        viewModel.release();
    }

    public ViewModel getViewModel() {
        return viewModel;
    }
}
