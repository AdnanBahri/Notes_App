package com.example.net.movies.flex.school.notesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.net.movies.flex.school.notesapp.callbacks.NotesClickListener;
import com.example.net.movies.flex.school.notesapp.databinding.NotesListBinding;
import com.example.net.movies.flex.school.notesapp.models.Note;

import java.util.List;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder> {
    private List<Note> notes;
    private NotesClickListener listener;

    public NotesRecyclerAdapter(List<Note> notes, NotesClickListener listener) {
        this.notes = notes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NotesListBinding binding = NotesListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fillUI(notes.get(position));
//        holder.setupOnClick(holder.getAdapterPosition())
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        NotesListBinding binding;

        public ViewHolder(@NonNull NotesListBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        void fillUI(Note note) {
            binding.textTitle.setText(note.getTitle());
            binding.textTitle.setSelected(true);
            binding.textNotes.setText(note.getNotes());
            binding.textDate.setText(note.getDate());
            binding.textDate.setSelected(true);
            if (note.isPinned())
                binding.imgPin.setVisibility(View.VISIBLE);
            else binding.imgPin.setVisibility(View.GONE);

            binding.notesContainer.setOnClickListener(v -> listener.onClick(note));
            binding.notesContainer.setOnLongClickListener(view -> {
                listener.onLongClick(note, binding.notesContainer);
                return true;
            });
        }
    }
}
