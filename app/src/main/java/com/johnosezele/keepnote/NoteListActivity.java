package com.johnosezele.keepnote;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {
    private NoteRecyclerAdapter mNoteRecyclerAdapter;

//    private ArrayAdapter<NoteInfo> mAdapterNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity (new Intent(NoteListActivity.this, NoteActivity.class));
            }
        });

        initializeDisplayContent();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
//        mAdapterNotes.notifyDataSetChanged();
        mNoteRecyclerAdapter.notifyDataSetChanged(); //see initializeDisplayContent()mtd below for clarity.
        //this way anytime our NoteRecyclerAdapter is resumed we'll refresh our data-set.
    }

    private void initializeDisplayContent() {
//            final ListView listNotes = findViewById(R.id.list_notes);
//
//            List<NoteInfo> notes = DataManager.getInstance().getNotes();
//        mAdapterNotes = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, notes);
//
//        listNotes.setAdapter(mAdapterNotes);
//
//        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
////                NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(position);
//                intent.putExtra(NoteActivity.NOTE_POSITION, position);
//
//                startActivity(intent);
//            }
//        });

        //This is where we setup our RecyclerView

        //First declare variable of type RecyclerView named recyclerNotes and tie it to the layout
        // this is done to get reference to RecyclerView.
        final RecyclerView recyclerNotes = findViewById(R.id.list_notes);

        //Associate layout manager to RecyclerView: create local variable of type LinearLayoutManager called notesLayoutManager,
        // new up the var. type and pass in 'this' context reference to the LinearLayoutManager constructor.
        final LinearLayoutManager notesLayoutManager = new LinearLayoutManager(this);

        //Then tie recyclerNotes and notesLayoutManager together using setLayoutManager() method
        recyclerNotes.setLayoutManager(notesLayoutManager);

        //Get the note we want to display within the RecyclerView from the DataManager.
        List<NoteInfo> notes = DataManager.getInstance().getNotes();

        //Create instance of NoteRecyclerAdapter, new it up and pass in a context and our notes.
        mNoteRecyclerAdapter = new NoteRecyclerAdapter(this, notes);
//        final NoteRecyclerAdapter noteRecyclerAdapter = new NoteRecyclerAdapter(this, notes);
//        the noteRecyclerAdapter var. in the above commented code was promoted to a field.

        //Associate RecyclerView with our NoteRecyclerAdapter
        recyclerNotes.setAdapter(mNoteRecyclerAdapter);

        //we want our NoteRecyclerAdapter updated each time the data is changed,
        //promote the noteRecyclerAdapter var. to a field, see above.
        //call notifyDataSetChanged()mtd for the mNoteRecyclerAdapter field in the onResume()mtd above.
    }
}