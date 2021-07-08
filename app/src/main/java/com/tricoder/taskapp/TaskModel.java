package com.tricoder.taskapp;

import java.util.Date;

public class TaskModel {

    String text;
    Date created;
    boolean checked;

    public TaskModel(String text) {
        this.text = text;
        created=new Date();
        checked=false;
    }




    public String getText() {
        return text;
    }


    public Date getCreated() {
        return created;
    }


    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
