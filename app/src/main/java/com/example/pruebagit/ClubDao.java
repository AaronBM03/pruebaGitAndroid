package com.example.pruebagit;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ClubDao {
    @Insert
    void insertOne(Club club);

    @Delete
    void deleteOne(Club club);

    @Query("SELECT * FROM club")
    List<Club> getAll();

    @Query("SELECT * FROM club WHERE id = :id")
    Club getOneById(int id);
}
