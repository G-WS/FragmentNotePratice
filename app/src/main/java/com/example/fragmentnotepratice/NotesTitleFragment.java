package com.example.fragmentnotepratice;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesTitleFragment extends Fragment {
    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notes_titile_frag, container, false);
        RecyclerView notesTitleRecyclerView = (RecyclerView) view.findViewById(R.id.notes_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        notesTitleRecyclerView.setLayoutManager(layoutManager);
        NotesAdapter adapter = new NotesAdapter(getNotes());
        notesTitleRecyclerView.setAdapter(adapter);
        return view;
    }

    private List<Notes> getNotes() {
        List<Notes> notesList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            Notes notes = new Notes();
            notes.setTitle("this is title" + i);
            notes.setContent(getRandomLengthContent("this is notes content" + i + "."));
            notesList.add(notes);
        }
        return notesList;
    }

    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(content);

        }
        return stringBuilder.toString();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        requireActivity().getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                if (getActivity().findViewById(R.id.notes_content_layout) != null) {
                    isTwoPane = true;
                } else {
                    isTwoPane = false;
                }
            }
        });
    }

    class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
        private List<Notes> mNotesList;

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView notesTitleText;

            public ViewHolder(View view) {
                super(view);
                notesTitleText = (TextView) view.findViewById(R.id.notes_title);
            }
        }

        public NotesAdapter(List<Notes> notesList) {
            mNotesList = notesList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Notes notes = mNotesList.get(holder.getLayoutPosition());
                    if (isTwoPane) {
                        NotesContentFragment notesContentFragment = (NotesContentFragment) getChildFragmentManager().findFragmentById(R.id.notes_content_fragment);
                        notesContentFragment.refresh(notes.getTitle(), notes.getContent());
                    } else {
                        NotesContentActivity.actionStart(getActivity(), notes.getTitle(), notes.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Notes notes = mNotesList.get(position);
            holder.notesTitleText.setText(notes.getTitle());
        }


        @Override
        public int getItemCount() {
            return mNotesList.size();
        }
    }
}
