package com.example.fox.projtest.ui.main;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fox.projtest.R;
import com.example.fox.projtest.entity.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    public MainAdapter(List<Item> items, Listener listener) {
        this.items = items;
        this.listener = listener;
    }

    interface Listener {
        void onItemClicked(String url, String title, String message);
    }

    private List<Item> items;
    private Listener listener;

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_view, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        final Item item = items.get(position);

        holder.itemTextTitle.setText(item.getTitle());
        holder.itemTextMessage.setText(item.getMessage());
        String url = item.getImageUrl();

        try {
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.ic_hourglass)
                    .into(holder.itemImageView);
        } catch (Exception e) {
            Log.d("MainAdapter", "onBindViewHolder: " + e.getMessage());
        }
        holder.constraintLayout.setOnClickListener(v -> listener.onItemClicked(url, item.getTitle(), item.getMessage()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {
        TextView itemTextTitle;
        TextView itemTextMessage;
        ImageView itemImageView;
        ConstraintLayout constraintLayout;

        MainViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImageView = itemView.findViewById(R.id.item_imageview);
            constraintLayout = itemView.findViewById(R.id.item_row_layout);
            itemTextTitle = itemView.findViewById(R.id.item_title);
            itemTextMessage = itemView.findViewById(R.id.item_message);
        }
    }
}
