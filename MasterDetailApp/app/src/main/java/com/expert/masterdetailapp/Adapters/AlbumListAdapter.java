package com.expert.masterdetailapp.Adapters;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.pool.GlideTrace;
import com.expert.masterdetailapp.Models.Tracks;
import com.expert.masterdetailapp.R;

import java.util.List;

public class AlbumListAdapter extends BaseAdapter {
    private TextView txtPrice,txtGenre,txtTrackName;
    private ImageView imgAlbum;
    Context mContext;
    List<Tracks> tracksList;
    public AlbumListAdapter(Context sContext, List<Tracks> tracks)
    {
        this.mContext=sContext;
        this.tracksList=tracks;
        GlideTrace.endSection();
        Glide.tearDown();
    }

    @Override
    public int getCount() {
        return tracksList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View mView  = LayoutInflater.from(mContext).inflate(R.layout.list_main,null,false);
        initViews(mView);
        Tracks tracks = tracksList.get(i);
        setViews(tracks);


        return mView;
    }

    private void setViews(Tracks tracks) {
        txtTrackName.setText(tracks.getTrackName());
        txtGenre.setText(tracks.getPrimaryGenreName());
        txtPrice.setText(Double.toString(tracks.getCollectionPrice()));
        Glide.with(mContext.getApplicationContext()).load(tracks.getArtworkUrl100()).into(imgAlbum);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (imgAlbum.getDrawable() == null){
                    //Image doesn´t exist.
                   imgAlbum.setImageResource(R.drawable.ic_error);
                }else{
                    //Image Exists!.
                }
            }
        },500);
    }

    private void initViews(View mView) {
        txtTrackName =mView.findViewById(R.id.txtTrackName);
        txtPrice = mView.findViewById(R.id.txtPrice);
        txtGenre = mView.findViewById(R.id.txtGenre);
        imgAlbum = mView.findViewById(R.id.imgAlbum);
    }
}
