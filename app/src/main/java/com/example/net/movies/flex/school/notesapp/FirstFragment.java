package com.example.net.movies.flex.school.notesapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.net.movies.flex.school.notesapp.adapters.NotesRecyclerAdapter;
import com.example.net.movies.flex.school.notesapp.callbacks.NotesClickListener;
import com.example.net.movies.flex.school.notesapp.databinding.FragmentFirstBinding;
import com.example.net.movies.flex.school.notesapp.db.RoomDB;
import com.example.net.movies.flex.school.notesapp.models.Note;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class FirstFragment extends Fragment implements NotesClickListener {

    private FragmentFirstBinding binding;
    private NotesRecyclerAdapter adapter;
    private List<Note> notes;
    private RoomDB database;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = RoomDB.getInstance(getContext());
        notes = database.mainDao().getNotes();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateRecycler();

    }

    private void updateRecycler() {
        adapter = new NotesRecyclerAdapter(notes, this);
        binding.notesRecycler.setHasFixedSize(true);
        binding.notesRecycler.setAdapter(adapter);
        binding.notesRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        MainActivity activity = (MainActivity) getActivity();
        activity.binding.fab.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(Note note) {

    }

    @Override
    public void onLongClick(Note note, MaterialCardView card) {

    }
}