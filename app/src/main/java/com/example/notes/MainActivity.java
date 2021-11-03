package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //veiw Model
    private NotesViewModel mNoteViewModel;

    //RecyclerView
    private RecyclerView mRecyclerView;
    private NoteAdapter mNoteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //floating button
        FloatingActionButton floatingActionButton = findViewById(R.id.button_add_word);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to the add activity
                Intent i = new Intent(MainActivity.this, AddNewNoteActivity.class);
                startActivityForResult(i, 1);
            }
        });

        //recycler view
        mRecyclerView = findViewById(R.id.words_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        //connect Recyclerview With adapter
        mNoteAdapter = new NoteAdapter();
        mRecyclerView.setAdapter(mNoteAdapter);

        //View Model
        mNoteViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        mNoteViewModel.getAllNotes().observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                //Update UI
                //RecyclerView
                mNoteAdapter.setWords(notes);
                // Toast.makeText(MainActivity.this, "on Changed worked", Toast.LENGTH_LONG).show();
            }

        });

        mNoteAdapter.OnItemClickListener(new NoteAdapter.OnItemCliclListener() {
            @Override
            public void onItemClick(Notes note) {
                Intent i =new Intent(MainActivity.this,AddNewNoteActivity.class);
                i.putExtra(AddNewNoteActivity.EXTRA_ID,note.getId());
                i.putExtra(AddNewNoteActivity.EXTRA_TITLE,note.getTitle());
                i.putExtra(AddNewNoteActivity.EXTRA_NOTE,note.getNote());


                startActivity(i);

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //delete item
                int position = viewHolder.getAdapterPosition();
                mNoteViewModel.delete(mNoteAdapter.getWordAt(position));
            }
        }).attachToRecyclerView(mRecyclerView);
    }




}