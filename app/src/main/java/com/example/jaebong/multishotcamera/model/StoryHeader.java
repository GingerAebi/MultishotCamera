package com.example.jaebong.multishotcamera.model;

/**
 * Created by gingeraebi on 2016. 8. 15..
 */
public class StoryHeader implements StoryBoardItem {
    public static final int HEADER_TYPE = -100;

    private String time;
    private int storyCount;

    public StoryHeader(String time, int storyCount) {
        this.time = time;
        this.storyCount = storyCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStoryCount() {
        return storyCount;
    }

    public void setStoryCount(int storyCount) {
        this.storyCount = storyCount;
    }

    @Override
    public int getType() {
        return HEADER_TYPE;
    }
}
