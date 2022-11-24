package com.example.Moonkey_store;

import java.io.Serializable;

public class ReviewItem implements Serializable {

    private String nickname;
    private String score;
    private String contents;

    public ReviewItem() {
    }

    public ReviewItem(String nickname, String score, String contents) {
        this.nickname = nickname;
        this.score = score;
        this.contents = contents;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}