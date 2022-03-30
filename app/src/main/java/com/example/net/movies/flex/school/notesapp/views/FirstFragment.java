package com.example.net.movies.flex.school.notesapp.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.net.movies.flex.school.notesapp.MainActivity;
import com.example.net.movies.flex.school.notesapp.adapters.NotesRecyclerAdapter;
import com.example.net.movies.flex.school.notesapp.callbacks.NotesClickListener;
import com.example.net.movies.flex.school.notesapp.callbacks.UpdateClickListener;
import com.example.net.movies.flex.school.notesapp.databinding.FragmentFirstBinding;
import com.example.net.movies.flex.school.notesapp.modals.UpdateNoteDialog;
import com.example.net.movies.flex.school.notesapp.models.Note;
import com.example.net.movies.flex.school.notesapp.viewmodels.MainViewModel;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FirstFragment extends Fragment implements NotesClickListener, UpdateClickListener {

    private final List<Note> data = new ArrayList<>();
    private FragmentFirstBinding binding;
    private NotesRecyclerAdapter adapter;
    private MainViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel
                .getAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notes -> {
                    data.clear();
                    data.addAll(notes);
                    adapter.notifyDataSetChanged();
                });
        updateRecycler();
    }

    private void updateRecycler() {
        adapter = new NotesRecyclerAdapter(data, this);
        binding.notesRecycler.setHasFixedSize(true);
        binding.notesRecycler.setAdapter(adapter);
        binding.notesRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("TAG", "Destroy");
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TAG", "Start");
        MainActivity activity = (MainActivity) getActivity();
        activity.binding.fab.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(Note note) {
        //Todo : Add  Progress Bar
        /*Toast.makeText(getContext(), "" + note.getTitle(), Toast.LENGTH_SHORT).show();
        BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        dialog.setContentView(R.layout.bottom_sheet_note);
        TextView title = dialog.findViewById(R.id.text_title);
        TextView notes = dialog.findViewById(R.id.text_notes);
        MaterialButton cancel = dialog.findViewById(R.id.btn_cancel);
        MaterialButton update = dialog.findViewById(R.id.btn_update);

        title.setText(note.getTitle());
        notes.setText(note.getNotes());

        cancel.setOnClickListener(v -> dialog.cancel());
        update.setOnClickListener(v -> {
            Note newNote = new Note();
            String text_title = title.getText().toString();
            String text_notes = notes.getText().toString();
            SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm a");
            Date date = new Date();
            newNote.setDate(formatter.format(date));

        });
        dialog.show();*/
        UpdateNoteDialog dialog = new UpdateNoteDialog(this::onUpdateClick, note);
        dialog.show(getActivity().getSupportFragmentManager(), "Create Update Dialog");
    }

    @Override
    public void onLongClick(Note note, MaterialCardView card) {
        //Todo : Add Alert Dialog and Progress Bar

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG", "Resume");
    }

    @Override
    public void onUpdateClick(Note note) {
        Toast.makeText(getContext(), "Update " + note.getTitle() + " Working fine", Toast.LENGTH_SHORT).show();
    }
}