package com.example.jaebong.multishotcamera.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jaebong.multishotcamera.R;
import com.example.jaebong.multishotcamera.model.Story;
import com.example.jaebong.multishotcamera.model.StoryBoardItem;
import com.example.jaebong.multishotcamera.model.StoryHeader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by JaeBong on 2016-08-14.
 */
public class StoryBoardAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<StoryBoardItem> storyBoardItems;

    public StoryBoardAdapter(Context context, ArrayList<StoryBoardItem> storyBoardItems) {
        this.context = context;
        this.storyBoardItems = storyBoardItems;
    }

    @Override
    public int getCount() {
        return storyBoardItems.size();
    }

    @Override
    public Object getItem(int position) {
        return storyBoardItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StoryBoardItem storyBoardItem = storyBoardItems.get(position);

        switch (storyBoardItem.getType()) {
            case Story.STORY_TYPE:
                return setStoryView(parent, (Story) storyBoardItem);
            case StoryHeader.HEADER_TYPE:
                return setStoryHeaderView(parent, (StoryHeader) storyBoardItem);
            default:
                return null;
        }
    }

    private View setStoryHeaderView(ViewGroup parent, StoryHeader storyBoardItem) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_story_header, parent, false);
        StoryHeader storyHeader = storyBoardItem;

        TextView timeText = (TextView) view.findViewById(R.id.timeText);
        TextView storyCountText = (TextView) view.findViewById(R.id.storyCountText);

        timeText.setText(storyHeader.getTime());
        storyCountText.setText("(" + storyHeader.getStoryCount() + ")");

        return view;
    }

    private View setStoryView(ViewGroup parent, Story storyBoardItem) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_story, parent, false);

        Story story = storyBoardItem;

        ImageView thumbnailImage = (ImageView) view.findViewById(R.id.thumbnailImage);
        TextView titleText = (TextView) view.findViewById(R.id.titleText);
        TextView timeText = (TextView) view.findViewById(R.id.timeText);

        titleText.setText(story.getTitle());
        timeText.setText(story.getTime());

        Picasso.with(context).load(story.getPicPaths().get(0).filePath).into(thumbnailImage);

        return view;
    }

}
