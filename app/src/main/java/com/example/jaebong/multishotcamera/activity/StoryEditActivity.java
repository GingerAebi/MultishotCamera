package com.example.jaebong.multishotcamera.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jaebong.multishotcamera.R;
import com.example.jaebong.multishotcamera.adapter.ImageListAdapter;
import com.example.jaebong.multishotcamera.model.FilePath;
import com.example.jaebong.multishotcamera.model.Story;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class StoryEditActivity extends AppCompatActivity {
    private Realm realm;

    @BindView(R.id.recyclerViewList)
    RecyclerView recyclerView;

    @BindView(R.id.imageCountText)
    TextView imageCountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_edit);
        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();

        RealmResults<FilePath> filePaths = realm.where(FilePath.class).findAll();
        if (filePaths != null) {
            imageCountText.setText(filePaths.size() + "개의 이미지");
        }
        ImageListAdapter imageListAdapter = new ImageListAdapter(this, filePaths);
        recyclerView.setAdapter(imageListAdapter);
    }

    @BindView(R.id.titleEdit)
    EditText titleEdit;

    @BindView(R.id.memoEdit)
    EditText memoEdit;

    private RealmList<FilePath> filePaths = new RealmList<>();

    @OnClick(R.id.completeText)
    public void completeStory() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Calendar calendar = Calendar.getInstance();
                Story story = realm.createObject(Story.class);
                story.setDate(calendar.getTime());
                story.setTitle(titleEdit.getText().toString());
                story.setMemo(memoEdit.getText().toString());
                story.setPicPaths(filePaths);
            }
        });

        finish();
    }
}
