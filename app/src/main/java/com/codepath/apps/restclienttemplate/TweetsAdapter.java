package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.apps.restclienttemplate.models.Tweeter;
import com.google.android.material.shape.RoundedCornerTreatment;

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

    // Clean all elements of the recycler
    public void clear() {
        tweeterList.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweeter> list) {
        tweeterList.addAll(list);
        notifyDataSetChanged();
    }


    // define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        ImageView tvOptImg;
        TextView userAt;
        Button fav;
        Button retweet;
        TextView timestamp;


        public ViewHolder(@NonNull @NotNull final View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvOptImg = itemView.findViewById(R.id.tvOptImg);
            userAt = itemView.findViewById(R.id.userAt);
            fav = itemView.findViewById(R.id.fav);
            retweet = itemView.findViewById(R.id.retweet);
            timestamp = itemView.findViewById(R.id.timestamp);



            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fav.getBackground().getConstantState() == context.getResources().getDrawable(R.drawable.ic_vector_heart_stroke).getConstantState()){
                        v.setBackgroundResource(R.drawable.ic_vector_heart);
                    }
                    else{
                        v.setBackgroundResource(R.drawable.ic_vector_heart_stroke);
                    }

                }
            });

            retweet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (retweet.getBackground().getConstantState() == context.getResources().getDrawable(R.drawable.ic_vector_retweet_stroke).getConstantState()){
                        v.setBackgroundResource(R.drawable.ic_vector_retweet);
                    }
                    else{
                        v.setBackgroundResource(R.drawable.ic_vector_retweet_stroke);
                    }
                }
            });

        }



        public void bind(Tweeter tweeter) {
            tvBody.setText(tweeter.body);
            tvScreenName.setText(tweeter.user.screenName);
            userAt.setText("@" + tweeter.user.username);
            timestamp.setText(tweeter.getRelativeTimeAgo(tweeter.createdAt));


            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transform(new CenterCrop(), new RoundedCorners(30));
            //userAt.setText(tweeter.User.);
            Glide.with(context).load(tweeter.user.profileImageUrl).circleCrop().into(ivProfileImage);
            if (tweeter.image != null){
                Glide.with(context).load(tweeter.image).apply(requestOptions).into(tvOptImg);
            }
            else{
                tvOptImg.setVisibility(View.GONE);
            }
        }

    }
}
