package com.example.fragmentnotepratice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NotesContentFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.notes_content_flag,container,false);
        return view;
    }
    public void refresh(String notesTitle,String notesContent){
        View visiblityLayout = view.findViewById(R.id.visibility_layout);
        visiblityLayout.setVisibility(View.VISIBLE);
        TextView notesTitleText = (TextView) view.findViewById(R.id.notes_title);
        TextView notesContentText = (TextView) view.findViewById(R.id.notes_content);
        notesTitleText.setText(notesTitle);
        notesContentText.setText(notesContent);
    }
}
