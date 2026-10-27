package com.example.iscip.data.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fruits")
public class FruitEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "calories")
    public String calories;

    public FruitEntity(int id, String name, String type,
                       String description, String calories) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.calories = calories;
    }
}