package com.example.jaebong.multishotcamera.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jaebong.multishotcamera.R;
import com.example.jaebong.multishotcamera.model.Story;
import com.example.jaebong.multishotcamera.adapter.StoryBoardAdapter;
import com.example.jaebong.multishotcamera.model.StoryBoardItem;
import com.example.jaebong.multishotcamera.model.StoryHeader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class StoryBoardActivity extends AppCompatActivity {

    private Realm realm;

    @BindView(R.id.storyList)
    ListView storyListView;

    @BindView(R.id.searchEdit)
    EditText searchEdit;

    @BindView(R.id.allStoryCountText)
    TextView allStoryCountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_board);
        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();
        RealmResults<Story> allStories = realm.where(Story.class).findAllSorted("date");

        allStoryCountText.setText("(" + allStories.size() + ")");

        ArrayList<StoryBoardItem> storyBoardItems = new ArrayList<>();
        //add Headers
        //1.가장 처음의 데이터를 가져와 헤더에 년, 월 포함 추가 -> getShortTime()으로 가능할듯.
        Iterator<Story> it = allStories.iterator();
        Date fastestDate = null;
        Date nextMonthDate = null;
        int headerPosition = 0;

        if (it.hasNext()) {
            Story firstStory = it.next();
            fastestDate = getDayOnlyDate(firstStory.getDate());
            nextMonthDate = getNextMonthDate(fastestDate);


            storyBoardItems.add(new StoryHeader(firstStory.getShortTime(), 1));
            storyBoardItems.add(firstStory);

        }
        int itemIdx = 1;
        if (it.hasNext()) {
            Story story = it.next();
            Date storyDate = story.getDate();
            Date dayOnlyDate = getDayOnlyDate(storyDate);

            if (dayOnlyDate.equals(fastestDate) && dayOnlyDate.after(fastestDate) && story.getDate().before(nextMonthDate)) {
                storyBoardItems.add(story);
                updateItemCount(storyBoardItems, headerPosition);
                itemIdx++;
            } else {
                headerPosition = itemIdx;
                fastestDate = story.getDate();
                nextMonthDate = getNextMonthDate(story.getDate());
                storyBoardItems.add(new StoryHeader(story.getShortTime(), 1));
                storyBoardItems.add(story);
                itemIdx += 2;
            }
        }


        StoryBoardAdapter storyBoardAdapter = new StoryBoardAdapter(this, storyBoardItems);
        storyListView.setAdapter(storyBoardAdapter);

    }

    private Date getDayOnlyDate(Date storyDate) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(storyDate);
        calender.set(Calendar.HOUR_OF_DAY, 0);
        calender.set(Calendar.MINUTE, 0);
        calender.set(Calendar.SECOND, 0);
        calender.set(Calendar.MILLISECOND, 0);

        Date dayOnlyDate = calender.getTime();
        return dayOnlyDate;
    }

    private void updateItemCount(ArrayList<StoryBoardItem> storyBoardItems, int headerPosition) {
        StoryHeader storyHeader = ((StoryHeader) storyBoardItems.get(headerPosition));
        storyHeader.setStoryCount(storyHeader.getStoryCount() + 1);
        storyBoardItems.set(headerPosition, storyHeader);
    }

    private Date getNextMonthDate(Date fastestDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fastestDate);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        Date nextMonthDate = calendar.getTime();
        return nextMonthDate;
    }


    @OnClick(R.id.searchButton)
    public void search() {

    }

    @OnClick(R.id.addStoryText)
    public void addStory() {
        Intent intent = new Intent(this, StoryEditActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
