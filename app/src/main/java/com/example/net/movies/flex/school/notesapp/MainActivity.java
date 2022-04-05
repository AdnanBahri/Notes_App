package com.example.net.movies.flex.school.notesapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.net.movies.flex.school.notesapp.callbacks.CreateNoteListener;
import com.example.net.movies.flex.school.notesapp.databinding.ActivityMainBinding;
import com.example.net.movies.flex.school.notesapp.modals.CreateNoteDialog;
import com.example.net.movies.flex.school.notesapp.models.Note;
import com.example.net.movies.flex.school.notesapp.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity implements CreateNoteListener {

    public ActivityMainBinding binding;
    private AppBarConfiguration appBarConfiguration;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        binding.fab.setOnClickListener(view -> {
            Log.d("Current Frag", "" + navController.getCurrentDestination().getLabel().equals("First Fragment"));
            CreateNoteDialog dialog = new CreateNoteDialog(this::onCreateClick);
            dialog.show(getSupportFragmentManager(), "MainActivity");
        });
    }

    @Override
    public void onCreateClick(Note note) {
        viewModel.insertNote(note);
    }
}