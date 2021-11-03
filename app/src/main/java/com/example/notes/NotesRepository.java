package com.example.notes;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesRepository {

    private NotesDao mNotesDao;
    private LiveData<List<Notes>> getAllNotes;
    public NotesRepository (Application app)
    {
        NoteRoomDb dp= NoteRoomDb.getInstance(app);
        mNotesDao =dp.notesDao();
        getAllNotes = mNotesDao.getAllNotes();
    }
    public void insert(Notes note)
    {
        new InsertAsyncTask(mNotesDao).execute(note);
    }

    public void delete(Notes note)
    {
        new DeleteAsyncTask(mNotesDao).execute(note);
    }
    public void update(Notes note)
    {
        new UpdateAsyncTask(mNotesDao).execute(note);
    }
    public LiveData<List<Notes>> getAllNotes()
    {
        return getAllNotes;
    }
    public void deleteAllNotes()
    {
        new DeleteAllWordsAsyncTask(mNotesDao).execute();
    }


    private static class InsertAsyncTask extends AsyncTask<Notes,Void,Void>{
        private NotesDao mNotesDao;

        public InsertAsyncTask(NotesDao notesDao) {
            mNotesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            mNotesDao.insert(notes[0]);
            return null;
        }


    }
    private static class UpdateAsyncTask extends AsyncTask<Notes,Void,Void>{
        private NotesDao mNotesDao;

        public UpdateAsyncTask(NotesDao notesDao) {
            mNotesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            mNotesDao.update(notes[0]);
            return null;
        }
    }


    private static class DeleteAsyncTask extends AsyncTask<Notes,Void,Void>{
        private NotesDao mNotesDao;

        public DeleteAsyncTask(NotesDao notesDao) {
            mNotesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            mNotesDao.delete(notes[0]);
            return null;
        }
    }


    private static class DeleteAllWordsAsyncTask extends AsyncTask<Void,Void,Void>{
        private NotesDao mNotesDao;

        public DeleteAllWordsAsyncTask(NotesDao notesDao) {
            mNotesDao = notesDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mNotesDao.deleteAllNotes();
            return null;
        }
    }
}