package com.example.jaebong.multishotcamera;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JaeBong on 2016-08-14.
 */
public class StoryBoardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEADER_VIEW = 1;

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailImage;
        TextView titleText;
        TextView timeText;

        public StoryViewHolder(View itemView) {
            super(itemView);

            thumbnailImage = (ImageView) itemView.findViewById(R.id.tumbnailImage);
            titleText = (TextView) itemView.findViewById(R.id.titleText);
            timeText = (TextView) itemView.findViewById(R.id.timeText);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
