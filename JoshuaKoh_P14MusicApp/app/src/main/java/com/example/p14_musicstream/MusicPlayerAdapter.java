package com.example.p14_musicstream;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

public class MusicPlayerAdapter extends ArrayAdapter<DisplayInfoConstructor>
{
    private static final String TAG = "musicplayerAdapter";
    private Context mcontext;
    private int mresource;
    private int lastPosition = -1;
    DisplayInfoStorage displayInfoStorage = new DisplayInfoStorage();
    SearchSongActivity searchSongActivity = new SearchSongActivity();

    static class ViewHolder
    {
        TextView id;
        TextView title;
        TextView artist;
        ImageButton drawable;
    }

    public MusicPlayerAdapter(Context context, int resource, ArrayList<DisplayInfoConstructor> objects)
    {
        super(context, resource, objects);
        mcontext = context;
        mresource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        setupimageloader();

        String id = getItem(position).getId();
        String title = getItem(position).getTitle();
        String artist = getItem(position).getArtist();
        String drawable = getItem(position).getDrawable();
        ImageButton bCover;

        DisplayInfoConstructor DisplayInfoStorage = new DisplayInfoConstructor(id, title, artist, drawable);
        final View result;
        ViewHolder holder;

        if(convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(mcontext);
            convertView = inflater.inflate(mresource, parent, false);

            holder = new ViewHolder();
            holder.drawable = (ImageButton) convertView.findViewById(R.id.bCover);
            holder.title = (TextView) convertView.findViewById(R.id.tv1);
            holder.id = (TextView) convertView.findViewById(R.id.tv3);
            holder.artist = (TextView) convertView.findViewById(R.id.tv2);
            bCover = convertView.findViewById(R.id.bCover);
            bCover.setTag(position);
            bCover.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int position = (Integer) v.getTag();
                    System.out.println(holder.id.getText());
                    int currentarrayindex = displayInfoStorage.searchbyid(holder.id.getText().toString());
                    System.out.println(currentarrayindex);
                    Intent i = new Intent(getContext(), MusicPlayerActivity.class);
                    i.putExtra("Index", currentarrayindex);
                    getContext().startActivity(i);
                    searchSongActivity.getArrayIndex(currentarrayindex);
                }
            });
            result = convertView;
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mcontext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
                // ^ if position is greater than last position = true then load_down_anim, if not then load_up_anim
        result.startAnimation(animation);
        lastPosition = position;

        ImageLoader imageLoader = ImageLoader.getInstance();

        int defaultimage = mcontext.getResources().getIdentifier("@drawable/image_failed", null, mcontext.getPackageName());

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultimage)
                .showImageOnFail(defaultimage)
                .showImageOnLoading(defaultimage).build();

        imageLoader.displayImage(drawable, holder.drawable, options);

        holder.title.setText(title);
        holder.id.setText(id);
        holder.artist.setText(artist);

        return convertView;
    }

    private void setupimageloader()
    {
        // Universal Image Loader
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mcontext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
    }
}
