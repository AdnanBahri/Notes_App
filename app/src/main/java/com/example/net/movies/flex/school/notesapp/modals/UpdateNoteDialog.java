package com.example.net.movies.flex.school.notesapp.modals;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.net.movies.flex.school.notesapp.callbacks.UpdateClickListener;
import com.example.net.movies.flex.school.notesapp.databinding.BottomSheetNoteBinding;
import com.example.net.movies.flex.school.notesapp.models.Note;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class UpdateNoteDialog extends AppCompatDialogFragment {

    private BottomSheetNoteBinding binding;
    private UpdateClickListener listener;
    private Note note;

    public UpdateNoteDialog(UpdateClickListener listener, Note note) {
        this.listener = listener;
        this.note = note;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        binding = BottomSheetNoteBinding.inflate(getActivity().getLayoutInflater());
        builder.setView(binding.getRoot());
        builder.setCancelable(true);
        builder.setTitle(null);

        binding.textTitle.setText(note.getTitle());
        binding.textNotes.setText(note.getNotes());
        binding.btnCancel.setOnClickListener(v -> dismiss());
        binding.btnUpdate.setOnClickListener(v -> {
            this.listener.onUpdateClick(note);
            dismiss();
        });

        return builder.create();
    }
}
