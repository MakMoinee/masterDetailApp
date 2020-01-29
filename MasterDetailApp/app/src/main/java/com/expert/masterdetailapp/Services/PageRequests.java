package com.expert.masterdetailapp.Services;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.expert.masterdetailapp.Models.Tracks;
import com.expert.masterdetailapp.Parsers.JSONParseResponse;
import com.expert.masterdetailapp.Parsers.JSONTracksParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PageRequests {
    Context mContext;
     String url="";
    List<Tracks> tracksList;

    public  PageRequests(Context sContext,String sUrl)
    {
        this.mContext=sContext;
        this.url=sUrl;
    }

    public  List<Tracks> requestForTracks(){
        tracksList = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("results");

                    for(int i=0;i<array.length();i++)
                    {

                        JSONObject obj = array.getJSONObject(i);
                        Tracks tracks = new Tracks();
                        tracks.setArtistId(obj.getInt("artistId"));
                        tracks.setTrackName(obj.getString("trackName"));
                        tracks.setPrimaryGenreName(obj.getString("primaryGenreName"));
                        tracks.setCollectionPrice(obj.getDouble("collectionPrice"));
                        tracks.setArtworkUrl100(obj.getString("artworkUrl100"));
                        tracks.setCollectionCensoredName(obj.getString("collectionCensoredName"));
                        tracksList.add(tracks);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(mContext,"hey: " + e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(stringRequest);
        return tracksList;
    }
}
