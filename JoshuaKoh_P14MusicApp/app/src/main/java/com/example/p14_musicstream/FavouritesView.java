package com.example.p14_musicstream;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavouritesView extends RecyclerView.ViewHolder
{
    public TextView tTitle, tArtist;
    public ImageButton bCover, bRemove;

    public FavouritesView(@NonNull View itemView)
    {
        super(itemView);

        tTitle = itemView.findViewById(R.id.title_tv_favourite);
        tArtist = itemView.findViewById(R.id.artist_tv_favourite);
        bCover = itemView.findViewById(R.id.cover_b_favourite);
        bRemove = itemView.findViewById(R.id.remove_b_favourite);
    }
}
