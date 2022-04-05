package com.example.net.movies.flex.school.notesapp.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.net.movies.flex.school.notesapp.db.RoomDB;
import com.example.net.movies.flex.school.notesapp.models.Note;
import com.example.net.movies.flex.school.notesapp.utils.Constants;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    MutableLiveData<Boolean> isLoading;
    private RoomDB database;
    private MutableLiveData<List<Note>> notes;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = RoomDB.getInstance(application.getApplicationContext());
        notes = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public Flowable<List<Note>> getAllNotes() {
        return database
                .mainDao()
                .getNotes();
    }

    public void deleteNote(Note note) {
        isLoading.postValue(true);
        Completable.fromAction(() -> database.mainDao().delete(note)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        Log.d(Constants.TAG_MAIN_VIEW_MODEL, "Delete Note : onSubscribe Called");
                    }

                    @Override
                    public void onComplete() {
                        isLoading.postValue(false);
                        Log.d(Constants.TAG_MAIN_VIEW_MODEL, "Delete Note : onComplete Called");
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d(Constants.TAG_MAIN_VIEW_MODEL, "Delete Note : onError Called");
                        Log.d(Constants.TAG_MAIN_VIEW_MODEL, "" + e.getMessage());
                    }
                });
    }

    public void insertNote(Note note) {
        isLoading.postValue(true);
        Completable
                .fromAction(
                        () -> database.mainDao().insert(note)
                ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        Log.d(Constants.TAG_MAIN_VIEW_MODEL, "Insert Note : onSubscribe Called");
                    }

                    @Override
                    public void onComplete() {
                        isLoading.postValue(false);
                        Log.d(Constants.TAG_MAIN_VIEW_MODEL, "Insert Note : onComplete Called");
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d(Constants.TAG_MAIN_VIEW_MODEL, "Insert Note : onError Called");
                        Log.d(Constants.TAG_MAIN_VIEW_MODEL, "" + e.getMessage());
                    }
                });
    }
}
