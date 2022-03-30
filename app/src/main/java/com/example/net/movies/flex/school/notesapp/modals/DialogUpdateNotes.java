package com.example.net.movies.flex.school.notesapp.modals;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.net.movies.flex.school.notesapp.databinding.NotesListBinding;
import com.example.net.movies.flex.school.notesapp.db.RoomDB;
import com.example.net.movies.flex.school.notesapp.models.Note;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DialogUpdateNotes extends BottomSheetDialog {

    public DialogUpdateNotes(@NonNull Context context) {
        super(context);
    }

/*
    private NotesListBinding binding;
    private Note note;
    private RoomDB instance;

    public DialogUpdateNotes(Note note, RoomDB instance) {
        this.note = note;
        this.instance = instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=NotesListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.textTitle.setText(note.getTitle());
        binding.textNotes.setText(note.getNotes());
        binding.textDate.setText(note.getDate());
    }*/
}
