package com.codepath.apps.restclienttemplate.models;

import android.media.Image;
import android.net.ParseException;
import android.text.format.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Parcel
public class Tweeter {

    public String body;
    public String createdAt;
    public User user;
    public String image;
    public long id;

    public Tweeter() {}

    public static Tweeter fromJson(JSONObject jsonObject) throws JSONException {
        Tweeter tweeter = new Tweeter();
        tweeter.body = jsonObject.getString("text");
        tweeter.id = jsonObject.getLong("id");
        tweeter.createdAt = jsonObject.getString("created_at");
        tweeter.user = User.fromJson(jsonObject.getJSONObject("user"));

        if (jsonObject.getJSONObject("entities").has("media") == true){
            tweeter.image = jsonObject.getJSONObject("entities").getJSONArray("media").getJSONObject(0).getString("media_url_https");
        }


        return tweeter;
    }


    public static List<Tweeter> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweeter> tweeters = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            tweeters.add(fromJson(jsonArray.getJSONObject(i)));

        }
        return tweeters;
    }

    public String getRelativeTimeAgo(String createdAt) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(createdAt).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}
