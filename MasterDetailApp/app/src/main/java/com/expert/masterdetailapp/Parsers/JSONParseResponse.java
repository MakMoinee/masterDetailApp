package com.expert.masterdetailapp.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParseResponse {
    public static boolean parseFeed(String content){
        try {
            JSONArray array = new JSONArray(content);

            JSONObject obj = array.getJSONObject(0);
            if(obj.getInt("resultCount")>0)
            {
                return true;
            }
            else{
                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
