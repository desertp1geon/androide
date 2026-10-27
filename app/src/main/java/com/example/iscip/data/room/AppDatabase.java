package com.example.iscip.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.iscip.data.room.FruitDAO;
import com.example.iscip.data.room.FruitEntity;

@Database(
        entities = {FruitEntity.class},
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FruitDAO fruitDAO();
}