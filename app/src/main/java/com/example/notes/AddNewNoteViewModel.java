package com.example.notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class AddNewNoteViewModel extends AndroidViewModel {

    private NotesRepository mRepository;



    public AddNewNoteViewModel(@NonNull Application application) {
        super(application);
        mRepository = new NotesRepository(application);

    }


    public void insert(Notes note) {
        mRepository.insert(note);
    }

    public void update(Notes note) {
        mRepository.update(note);
    }

}
