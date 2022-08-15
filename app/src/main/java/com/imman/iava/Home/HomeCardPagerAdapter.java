package com.imman.iava.Home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.imman.iava.R;

public class HomeCardPagerAdapter extends RecyclerView.Adapter<HomeCardPagerAdapter.ViewHolder> {

    @NonNull
    @Override
    public HomeCardPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cards, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCardPagerAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView rootHomeCard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rootHomeCard = itemView.findViewById(R.id.root_home_card);
        }
    }
}
