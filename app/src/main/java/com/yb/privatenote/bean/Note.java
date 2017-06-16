package com.yb.privatenote.bean;

/**
 * Created by yb on 2017/6/16 17:02.
 */

public class Note {
    private String content;
    private String time;

    public Note(String content, String time) {
        this.content = content;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
