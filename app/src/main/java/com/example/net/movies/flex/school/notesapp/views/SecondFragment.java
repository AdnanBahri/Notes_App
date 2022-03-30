package com.example.net.movies.flex.school.notesapp.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.net.movies.flex.school.notesapp.R;
import com.example.net.movies.flex.school.notesapp.databinding.FragmentSecondBinding;
import com.example.net.movies.flex.school.notesapp.models.Note;
import com.example.net.movies.flex.school.notesapp.utils.Constants;
import com.example.net.movies.flex.school.notesapp.viewmodels.MainViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private NavController controller;
    private MainViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        controller = Navigation.findNavController(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.save)
            saveNote();
        return true;
    }

    private void saveNote() {
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
    }
}