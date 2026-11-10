
package com.example.iscip.data;

import android.content.Context;

import androidx.room.Room;

import com.example.iscip.basic.concurency.concurency.Fault;
import com.example.iscip.basic.concurency.concurency.TaskCallback;
import com.example.iscip.data.mappers.FruitMappers;
import com.example.iscip.data.room.AppDatabase;
import com.example.iscip.data.room.FruitEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FruitDatabase implements FruitRepository {

    private final AppDatabase appDatabase;
    private final ExecutorService executorService;

    public FruitDatabase(Context context) {
        Context appContext = context.getApplicationContext();

        appDatabase = Room.databaseBuilder(
                appContext,
                AppDatabase.class,
                "fruitify_database"
        ).build();

        executorService = Executors.newSingleThreadExecutor();
    }

    // for test
    public FruitDatabase(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void getAll(TaskCallback<List<Fruit>> callback) {
        executorService.execute(() -> {
            try {
                List<FruitEntity> entities =
                        appDatabase.fruitDAO().getAll();

                List<Fruit> fruits =
                        FruitMappers.toDomainList(entities);

                callback.onComplete(fruits);

            } catch (Exception exception) {
                callback.onError(
                        new Fault(exception.getMessage())
                );
            }
        });
    }

    @Override
    public void add(Fruit fruit, TaskCallback<Boolean> callback) {
        executorService.execute(() -> {
            try {
                FruitEntity entity = FruitMappers.toEntity(fruit);

                long insertedId =
                        appDatabase.fruitDAO().insert(entity);

                callback.onComplete(insertedId > 0);

            } catch (Exception exception) {
                callback.onError(
                        new Fault(exception.getMessage())
                );
            }
        });
    }
    public void close() {
        executorService.shutdown();
        appDatabase.close();
    }
}
