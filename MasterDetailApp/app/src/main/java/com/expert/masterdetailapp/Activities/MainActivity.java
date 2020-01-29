package com.expert.masterdetailapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.expert.masterdetailapp.Adapters.AlbumListAdapter;
import com.expert.masterdetailapp.Models.Tracks;
import com.expert.masterdetailapp.Persistence.TrackPrefference;
import com.expert.masterdetailapp.R;
import com.expert.masterdetailapp.Services.PageRequests;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private AlbumListAdapter adapter;
    private PageRequests requests;
    private List<Tracks> tracksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        tracksList = new ArrayList<>();
        if( new TrackPrefference(MainActivity.this).getTrackName().equals(""))
        {
            initViews();
            getData();
            initListeners();
        }
        else{
            Intent intent = new Intent(MainActivity.this,DetailActivity.class);
            startActivity(intent);
        }

    }

    private void initListeners() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tracks tracks = tracksList.get(i);
                new TrackPrefference(MainActivity.this).saveTrack(tracks.getTrackName(),tracks.getCollectionCensoredName(),tracks.getArtworkUrl100());
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        });
    }

    public void getData() {
        tracksList =   requests.requestForTracks();
        if(tracksList !=null)
        {
            adapter = new AlbumListAdapter(MainActivity.this,tracksList);
            lv.setAdapter(adapter);
        }
        else{
            getData();
        }
    }

    private void initViews() {
        lv = findViewById(R.id.lv);
        requests = new PageRequests(MainActivity.this,"https://itunes.apple.com/search?term=star&amp;country=au&amp;media=movie&amp;all");
    }


}
