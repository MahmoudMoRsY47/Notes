package com.example.notes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String note;



    public Notes(String title, String note) {
        this.title = title;
        this.note = note;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }


}