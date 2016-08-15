package com.example.jaebong.multishotcamera.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by gingeraebi on 2016. 8. 15..
 */
public class Story extends RealmObject implements StoryBoardItem {

    public static final int STORY_TYPE = 1;

    private RealmList<FilePath> picPaths;
    private String title;
    private String memo;
    private Date date;


    public void setPicPaths(RealmList<FilePath> picPaths) {
        this.picPaths = picPaths;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public RealmList<FilePath> getPicPaths() {
        return picPaths;
    }

    public String getTitle() {
        return title;
    }

    public String getMemo() {
        return memo;
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");
        return format.format(date);
    }

    public String getShortTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월");
        return format.format(date);
    }

    @Override
    public int getType() {
        return Story.STORY_TYPE;
    }

    @Override
    public String toString() {
        return "Story{" +
                "picPaths=" + picPaths +
                ", title='" + title + '\'' +
                ", memo='" + memo + '\'' +
                ", date=" + date +
                '}';
    }
}
