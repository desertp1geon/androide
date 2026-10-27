package com.example.iscip.data.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FruitDAO {

    @Query("SELECT * FROM fruits ORDER BY id ASC")
    List<FruitEntity> getAll();

    @Insert
    long insert(FruitEntity fruit);
}