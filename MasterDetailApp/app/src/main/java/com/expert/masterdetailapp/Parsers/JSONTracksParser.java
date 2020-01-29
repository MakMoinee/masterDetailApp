package com.expert.masterdetailapp.Parsers;

import com.expert.masterdetailapp.Models.Tracks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONTracksParser {
    public static List<Tracks> parseFeed(String content){
        try {
            JSONObject object = new JSONObject(content);
            JSONArray array = object.getJSONArray("results");

                List<Tracks> tracksList = new ArrayList<>();

                for(int i=0;i<array.length();i++)
                {
                    JSONObject obj = array.getJSONObject(i);
                    Tracks tracks = new Tracks();
                    tracks.setArtistId(obj.getInt("artistId"));
                    tracks.setTrackName(obj.getString("trackName"));
                    tracks.setPrimaryGenreName(obj.getString("primaryGenreName"));
                    tracks.setCollectionPrice(obj.getDouble("collectionPrice"));
                    tracks.setArtworkUrl100(obj.getString("artworkUrl100"));
                    tracksList.add(tracks);
                }


            return tracksList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
