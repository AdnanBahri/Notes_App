package com.example.net.movies.flex.school.notesapp.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.net.movies.flex.school.notesapp.databinding.FragmentSecondBinding;
import com.example.net.movies.flex.school.notesapp.models.Note;
import com.example.net.movies.flex.school.notesapp.viewmodels.MainViewModel;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private NavController controller;
    private MainViewModel viewModel;
    private Note note;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        note = (Note) getArguments().getSerializable("note");
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        binding.title.setText(note.getTitle());
        binding.notes.setText(note.getNotes());

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        controller = Navigation.findNavController(view);
        binding.save.setOnClickListener(v -> Toast.makeText(getContext(), "" + note.getTitle(), Toast.LENGTH_SHORT).show());
        binding.back.setOnClickListener(v -> controller.popBackStack());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /*private void saveNote() {
        String title = binding.title.getText().toString();
        String notes = binding.notes.getText().toString();
        Note note = new Note();
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm a");
        Date date = new Date();
        note.setTitle(title);
        note.setNotes(notes);
        note.setDate(formatter.format(date));
        viewModel.insertNote(note);
        viewModel
                .getIsLoading()
                .observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        Log.d(Constants.TAG_SECOND_FRAGMENT, "isLoading is " + aBoolean);
                        if (!aBoolean) {
                            controller.popBackStack();
                        }
                    }
                });
    }*/
}