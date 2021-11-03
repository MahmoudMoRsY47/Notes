package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class AddNewNoteActivity extends AppCompatActivity {

    private EditText titleedt;
    private EditText noteedt;

    private boolean editMode;

    private int mID;

    public static final String EXTRA_ID = "com.example.wordlist.extraid";
    public static final String EXTRA_TITLE = "com.example.wordlist.word";
    public static final String EXTRA_NOTE = "com.example.wordlist.meaning";

    //veiw Model fo add new word Activity
    private AddNewNoteViewModel mVeiwModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        titleedt = findViewById(R.id.edit_text_word);
        noteedt = findViewById(R.id.edit_text_meaning);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_exit);

        Intent i = getIntent();
        if(i.hasExtra(EXTRA_ID))
        {
            //Update word
            setTitle("Edit Note");
            editMode = true;
            mID = i.getIntExtra(EXTRA_ID, -1);
            titleedt.setText(i.getStringExtra(EXTRA_TITLE));
            noteedt.setText(i.getStringExtra(EXTRA_NOTE));
        }else{
            //insert new word
            setTitle("Add new Word");
            editMode = false;
        }


        mVeiwModel = ViewModelProviders.of(this).get(AddNewNoteViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_word:
                saveNote();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


    public void saveNote() {
        String word = titleedt.getText().toString().trim();
        String meaning = noteedt.getText().toString().trim();

        Notes wordObject = new Notes(word, meaning);

        if (word.isEmpty() ||  meaning.isEmpty()) {
            Toast.makeText(AddNewNoteActivity.this, "please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }

        if(editMode){
            wordObject.setId(mID);
            mVeiwModel.update(wordObject);
        }else{
            mVeiwModel.insert(wordObject);
        }
        finish();
    }

}







