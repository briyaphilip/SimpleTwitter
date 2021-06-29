package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweeter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{

    Context context;
    List<Tweeter> tweeterList;
    // pass in context and list of tweets
    public TweetsAdapter(Context context, List<Tweeter> tweeters){
        this.context = context;
        this.tweeterList = tweeters;
    }


    // for each row, inflate a layout for tweet
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    // bind values based on position of the element
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        // get data at position
        Tweeter tweeter = tweeterList.get(position);
        // bind the tweet with view holder
        holder.bind(tweeter);
    }

    @Override
    public int getItemCount() {
        return tweeterList.size();
    }


    // define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
        }

        public void bind(Tweeter tweeter) {
            tvBody.setText(tweeter.body);
            tvScreenName.setText(tweeter.user.screenName);
            Glide.with(context).load(tweeter.user.profileImageUrl).into(ivProfileImage);
        }
    }
}
