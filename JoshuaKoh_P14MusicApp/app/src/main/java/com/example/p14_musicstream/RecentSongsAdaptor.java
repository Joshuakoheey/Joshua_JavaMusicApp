package com.example.p14_musicstream;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecentSongsAdaptor extends RecyclerView.Adapter<RecentSongsView>
{
    public RecentSongsAdaptor(List<MusicConstructor> recentsongs)
    {
        this.recentsongs = recentsongs;
    }

    List<MusicConstructor> recentsongs;
    Context context;

    @NonNull
    @Override
    public RecentSongsView onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View recentView = inflater.inflate(R.layout.recent_list_layout, parent, false);
        RecentSongsView viewHolder = new RecentSongsView(recentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecentSongsView holder, int position)
    {
        MusicConstructor recentmusic = recentsongs.get(position);

        TextView recentartist = holder.tArtist;
        recentartist.setText(recentmusic.getArtist());

        TextView recenttitle = holder.tTitle;
        recenttitle.setText(recentmusic.getTitle());

        ImageButton recentcover = holder.bCover;
        recentcover.setImageResource(recentmusic.getDrawable());
    }

    @Override
    public int getItemCount()
    {
        return recentsongs.size();
    }
}
