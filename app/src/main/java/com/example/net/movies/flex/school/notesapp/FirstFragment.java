package com.example.net.movies.flex.school.notesapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FirstFragment extends Fragment implements NotesClickListener {

    private final List<Note> data = new ArrayList<>();
    private FragmentFirstBinding binding;
    private NotesRecyclerAdapter adapter;
    private RoomDB database;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = RoomDB.getInstance(getContext());
        database
                .mainDao()
                .getNotes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Note>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<Note> notes) {
                        data.clear();
                        data.addAll(notes);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.e("TAG", "Catch OnError " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        updateRecycler();
                    }
                });
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
        Toast.makeText(getContext(), "" + note.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(Note note, MaterialCardView card) {
        Toast.makeText(getContext(), "" + note.getDate(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG", "Resume");
    }

}