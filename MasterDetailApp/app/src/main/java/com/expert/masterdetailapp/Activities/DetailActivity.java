package com.expert.masterdetailapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.pool.GlideTrace;
import com.expert.masterdetailapp.Persistence.TrackPrefference;
import com.expert.masterdetailapp.R;

public class DetailActivity extends AppCompatActivity {

    private TextView txtTrackName,txtDescription;
    private ImageView imgAlbum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        initViews();
        setDataIntoView();
    }

    private void setDataIntoView() {
        txtTrackName.setText(new TrackPrefference(DetailActivity.this).getTrackName());
        txtDescription.setText(new TrackPrefference(DetailActivity.this).getDescription());
        Glide.with(DetailActivity.this).load(new TrackPrefference(DetailActivity.this).getArtWork()).into(imgAlbum);
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

    private void initViews() {
        txtTrackName = findViewById(R.id.txtTrackName);
        txtDescription = findViewById(R.id.txtDescription);
        imgAlbum = findViewById(R.id.imgAlbum);
        GlideTrace.endSection();
        Glide.tearDown();
    }

    @Override
    public void onBackPressed() {

        new TrackPrefference(DetailActivity.this).saveTrack("","","");
        Intent intent = new Intent(DetailActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();

    }
}
