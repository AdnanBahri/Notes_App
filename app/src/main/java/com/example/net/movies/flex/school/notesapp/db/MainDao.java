package com.example.net.movies.flex.school.notesapp.db;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.net.movies.flex.school.notesapp.models.Note;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface MainDao {
    @Insert(onConflict = REPLACE)
    void insert(Note note);

    @Query("SELECT * FROM notes ORDER BY id DESC")
    Observable<List<Note>> getNotes();

    @Query("UPDATE notes SET title = :title, notes = :notes WHERE ID = :id")
    Completable update(int id, String title, String notes);

    @Delete
    Completable delete(Note note);
}
