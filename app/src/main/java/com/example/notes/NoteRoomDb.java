package com.example.notes;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Notes.class, version = 1)
public abstract class NoteRoomDb extends RoomDatabase {

    private static NoteRoomDb instance;

    public abstract NotesDao notesDao();

    //Singlton
    public static synchronized NoteRoomDb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteRoomDb.class, "word3-database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDataAsyncTask(instance).execute();
        }

    };

    private static class PopulateDataAsyncTask extends AsyncTask<Void,Void, Void>
    {
        private NotesDao mNotesDao;

        PopulateDataAsyncTask(NoteRoomDb db)
        {
            mNotesDao = db.notesDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mNotesDao.insert(new Notes("book", "Book"));

            return null;
        }

    }

}

