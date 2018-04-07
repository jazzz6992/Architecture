package com.vsevolodvisnevskij.presentation.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

public abstract class BaseAdapter<Model, ViewModel extends BaseItemViewModel<Model>> extends RecyclerView.Adapter<BaseItemViewHolder<Model, ViewModel, ?>> {
    protected List<Model> items = new ArrayList<>();

    private PublishSubject<ItemEntity> clickSubject = PublishSubject.create();

    @Override
    public void onBindViewHolder(BaseItemViewHolder<Model, ViewModel, ?> holder, int position) {
        Model item = items.get(position);
        holder.bind(item, position);
    }

    public PublishSubject<ItemEntity> observeClick() {
        return clickSubject;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Model> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public static class ItemEntity<Model> {
        public Model model;
        public int position;

        public ItemEntity(Model model, int position) {
            this.model = model;
            this.position = position;
        }
    }

    @Override
    public void onViewAttachedToWindow(BaseItemViewHolder<Model, ViewModel, ?> holder) {
        super.onViewAttachedToWindow(holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                clickSubject.onNext(new ItemEntity(items.get(pos), pos));
            }
        });
    }

    @Override
    public void onViewDetachedFromWindow(BaseItemViewHolder<Model, ViewModel, ?> holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.setOnClickListener(null);
    }
}

