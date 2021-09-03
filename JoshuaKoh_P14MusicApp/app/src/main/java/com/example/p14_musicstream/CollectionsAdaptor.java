package com.example.p14_musicstream;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CollectionsAdaptor extends RecyclerView.Adapter<CollectionsConstructor>
{
    public CollectionsAdaptor(List<MusicConstructor> collectionsongs)
    {
        this.collectionsongs = collectionsongs;
    }

    List<MusicConstructor> collectionsongs;
    Context context;

    @NonNull
    @Override
    public CollectionsConstructor onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View colleView = inflater.inflate(R.layout.collection_list_layout, parent, false);
        CollectionsConstructor viewHolder = new CollectionsConstructor(colleView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionsConstructor holder, int position)
    {
        MusicConstructor collemusic = collectionsongs.get(position);

        TextView colleartist = holder.tArtist;
        colleartist.setText(collemusic.getArtist());

        TextView colletitle = holder.tTitle;
        colletitle.setText(collemusic.getTitle());

        ImageButton collecover = holder.bCover;
        collecover.setImageResource(collemusic.getDrawable());

        holder.bRemove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MusicPlayerActivity.colleList.remove(position); //removes song from collection list
                notifyDataSetChanged();
            }
        });
    }

    public void filterList(ArrayList<MusicConstructor> filteredList)
    {
        collectionsongs = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        return collectionsongs.size();
    }
}