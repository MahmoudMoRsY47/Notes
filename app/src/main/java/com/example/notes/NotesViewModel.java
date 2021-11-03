package com.example.notes;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    private NotesRepository mRepository;

    private LiveData<List<Notes>> mAllNotes;

    public NotesViewModel(@NonNull Application application) {

        super(application);
        mRepository = new NotesRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }



    public void insert(Notes note) {
        mRepository.insert(note);
    }

    public void delete(Notes note) {
        mRepository.delete(note);
    }

    public void update(Notes note) {
        mRepository.update(note);
    }

    public void deleteAllNotes() {
        mRepository.deleteAllNotes();
    }



    public LiveData<List<Notes>> getAllNotes() {
        return mAllNotes;
    }
}
