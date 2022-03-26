package com.example.net.movies.flex.school.notesapp.callbacks;

import androidx.cardview.widget.CardView;

import com.example.net.movies.flex.school.notesapp.models.Note;
import com.google.android.material.card.MaterialCardView;

public interface NotesClickListener {
    void onClick(Note note);
    void onLongClick(Note note, MaterialCardView card);
}
