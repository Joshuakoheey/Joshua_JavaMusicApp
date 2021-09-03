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

public class DownloadsAdaptor extends RecyclerView.Adapter<DownloadsView>
{
    public DownloadsAdaptor(List<MusicConstructor> downloadsongs)
    {
        this.downloadsongs = downloadsongs;
    }

    List<MusicConstructor> downloadsongs;
    Context context;

    @NonNull
    @Override
    public DownloadsView onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        context = parent.getContext(); // Takes info from the mainpage
        LayoutInflater inflater = LayoutInflater.from(context);
        View dlView = inflater.inflate(R.layout.downloads_list_layout, parent, false);
        DownloadsView viewHolder = new DownloadsView(dlView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DownloadsView holder, int position)
    {
        MusicConstructor dlmusic = downloadsongs.get(position);

        TextView dlartist = holder.tArtist;
        dlartist.setText(dlmusic.getArtist());

        TextView dltitle = holder.tTitle;
        dltitle.setText(dlmusic.getTitle());

        ImageButton dlcover = holder.bCover;
        dlcover.setImageResource(dlmusic.getDrawable());

        holder.bRemove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MusicPlayerActivity.dlList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return downloadsongs.size();
    }
}
