package com.example.fragmentnotepratice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class NotesContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_content);
        String notesTitle = getIntent().getStringExtra("notes_title");
        String notesContent = getIntent().getStringExtra("notes_content");
        NotesContentFragment notesContentFragment = (NotesContentFragment) getSupportFragmentManager().findFragmentById(R.id.notes_content_fragment);
        notesContentFragment.refresh(notesTitle,notesContent);
    }
    public static void actionStart(Context context,String notesTitle,String notesContent){
        Intent intent = new Intent(context,NotesContentActivity.class);
        intent.putExtra("notes_title",notesTitle);
        intent.putExtra("notes_content",notesContent);
        context.startActivity(intent);
    }

}