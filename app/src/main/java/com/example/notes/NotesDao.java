package com.example.notes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {
    @Insert
    void insert(Notes note);
    @Update
    void update(Notes note);
    @Delete
    void delete(Notes note);
    @Query("Delete From note")
    void deleteAllNotes();
    @Query("Select * From note")
    LiveData<List<Notes>> getAllNotes();
}
