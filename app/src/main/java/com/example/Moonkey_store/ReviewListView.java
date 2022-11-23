package com.example.Moonkey_store;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ReviewListView extends LinearLayout {
    TextView text_nickname;
    TextView text_score;
    TextView text_contents;


    public ReviewListView(Context context) {
        super(context);
        init(context);
    }

    public ReviewListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_reviewlist, this, true);
        text_nickname = (TextView) findViewById(R.id.tv_nickname);
        text_score = (TextView) findViewById(R.id.tv_score);
        text_contents = (TextView) findViewById(R.id.tv_contents);
    }
    public void setNickname(String nickname) {
        text_nickname.setText(nickname);
    }
    public void setScore(String score) {
        text_score.setText(score);
    }
    public void setContents(String contents){ text_contents.setText(contents);}



}