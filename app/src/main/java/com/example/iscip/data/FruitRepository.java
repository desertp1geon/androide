package com.example.iscip.data;

import com.example.iscip.basic.concurency.concurency.TaskCallback;

import java.util.List;

public interface FruitRepository {

    void getAll(TaskCallback<List<Fruit>> callback);
    void add(Fruit fruit, TaskCallback<Void> callback);
}