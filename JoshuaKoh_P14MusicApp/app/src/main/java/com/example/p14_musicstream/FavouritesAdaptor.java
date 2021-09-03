package com.example.p14_musicstream;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

public class FavouritesAdaptor extends RecyclerView.Adapter<FavouritesView>
{
    public FavouritesAdaptor(List<MusicConstructor> favouritesongs)
    {
        this.favouritesongs = favouritesongs;
    }

    List<MusicConstructor> favouritesongs;
    Context context;

    @NonNull
    @Override
    public FavouritesView onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context); // Instantiates a layout XML file into its corresponding View objects
        View favView = inflater.inflate(R.layout.favourites_list_layout, parent, false);
        FavouritesView viewHolder = new FavouritesView(favView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesView holder, int position)
    {
        MusicConstructor favmusic = favouritesongs.get(position);

        TextView favartist = holder.tArtist;
        favartist.setText(favmusic.getArtist());

        TextView favtitle = holder.tTitle;
        favtitle.setText(favmusic.getTitle());

        ImageButton favcover = holder.bCover;
        favcover.setImageResource(favmusic.getDrawable());

        holder.bRemove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MusicPlayerActivity.favList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return favouritesongs.size();
    }
}