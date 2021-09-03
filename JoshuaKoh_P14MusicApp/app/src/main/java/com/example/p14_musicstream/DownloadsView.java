package com.example.p14_musicstream;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DownloadsView extends RecyclerView.ViewHolder
{
    public TextView tTitle, tArtist;
    public ImageButton bCover, bRemove;

    //creates view in downloads library

    public DownloadsView(@NonNull View itemView)
    {
        super(itemView);

        tTitle = itemView.findViewById(R.id.title_tv_download);
        tArtist = itemView.findViewById(R.id.artist_tv_download);
        bCover = itemView.findViewById(R.id.cover_b_download);
        bRemove = itemView.findViewById(R.id.remove_b_download);
    }
}
