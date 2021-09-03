package com.example.p14_musicstream;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecentSongsView extends RecyclerView.ViewHolder
{
    public TextView tTitle, tArtist;
    public ImageButton bCover;

    public RecentSongsView(@NonNull View itemView)
    {
        super(itemView);

        tTitle = itemView.findViewById(R.id.title_tv_recentsongs);
        tArtist = itemView.findViewById(R.id.artist_tv_recentsongs);
        bCover = itemView.findViewById(R.id.cover_b_recentsongs);
    }
}
