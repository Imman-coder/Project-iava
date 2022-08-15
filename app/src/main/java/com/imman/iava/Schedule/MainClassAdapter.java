package com.imman.iava.Schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imman.iava.R;
//import com.squareup.picasso.Picasso;

public class MainClassAdapter extends RecyclerView.Adapter<MainClassAdapter.ViewHolder> {

    @NonNull
    @Override
    public MainClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cards,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainClassAdapter.ViewHolder holder, int position) {
//        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
