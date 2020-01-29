package com.expert.masterdetailapp.Persistence;

import android.content.Context;
import android.content.SharedPreferences;

public class TrackPrefference {
    Context mContext;
    public TrackPrefference(Context mContext){
        this.mContext=mContext;
    }

    public void saveTrack(String trackName, String description,String artWork)
    {
        SharedPreferences save = mContext.getSharedPreferences("saveTrack",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.putString("trackName",trackName);
        editor.putString("description",description);
        editor.putString("artWork",artWork);
        editor.apply();

    }

    public String getTrackName()
    {
        SharedPreferences save = mContext.getSharedPreferences("saveTrack",Context.MODE_PRIVATE);
         return  save.getString("trackName","");
    }
    public String getDescription(){
        SharedPreferences save = mContext.getSharedPreferences("saveTrack",Context.MODE_PRIVATE);
        return  save.getString("description","");
    }
    public String getArtWork(){
        SharedPreferences save = mContext.getSharedPreferences("saveTrack",Context.MODE_PRIVATE);
        return  save.getString("artWork","");
    }

}
