
package com.example.iscip.data;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.iscip.basic.concurency.concurency.Fault;
import com.example.iscip.basic.concurency.concurency.TaskCallback;
import com.example.iscip.data.room.AppDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@RunWith(AndroidJUnit4.class)
public class FruitDatabaseTest {

    private AppDatabase testDatabase;
    private FruitDatabase repository;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();

        testDatabase = Room.inMemoryDatabaseBuilder(
                context,
                AppDatabase.class
        ).build();

        repository = new FruitDatabase(testDatabase);
    }

    @After
    public void tearDown() {
        repository.close();
    }

    @Test
    public void testRepositoryInitialization() {
        assertNotNull(repository);
    }

    @Test
    public void testAddFruitSuccessfully() throws InterruptedException {
        Fruit fruit = new Fruit(
                0,
                "Apple",
                "Pome",
                "Red apple",
                "52"
        );

        CountDownLatch latch = new CountDownLatch(1);
        AtomicBoolean result = new AtomicBoolean(false);

        repository.add(fruit, new TaskCallback<Boolean>() {
            @Override
            public void onComplete(Boolean success) {
                result.set(Boolean.TRUE.equals(success));
                latch.countDown();
            }

            @Override
            public void onError(Fault fault) {
                latch.countDown();
            }
        });

        assertTrue(latch.await(5, TimeUnit.SECONDS));
        assertTrue(result.get());
    }

    @Test
    public void testGetAllFruitsSuccessfully() throws InterruptedException {
        Fruit fruit = new Fruit(
                0,
                "Banana",
                "Tropical",
                "Yellow banana",
                "89"
        );

        CountDownLatch addLatch = new CountDownLatch(1);
        AtomicBoolean addResult = new AtomicBoolean(false);

        repository.add(fruit, new TaskCallback<Boolean>() {
            @Override
            public void onComplete(Boolean result) {
                addResult.set(Boolean.TRUE.equals(result));
                addLatch.countDown();
            }

            @Override
            public void onError(Fault fault) {
                addLatch.countDown();
            }
        });

        assertTrue(addLatch.await(5, TimeUnit.SECONDS));
        assertTrue(addResult.get());

        CountDownLatch getLatch = new CountDownLatch(1);
        AtomicReference<List<Fruit>> result = new AtomicReference<>();

        repository.getAll(new TaskCallback<List<Fruit>>() {
            @Override
            public void onComplete(List<Fruit> fruits) {
                result.set(fruits);
                getLatch.countDown();
            }

            @Override
            public void onError(Fault fault) {
                getLatch.countDown();
            }
        });

        assertTrue(getLatch.await(5, TimeUnit.SECONDS));
        assertNotNull(result.get());
        assertEquals(1, result.get().size());
        assertEquals("Banana", result.get().get(0).getName());
    }

    @Test
    public void testAddNullFruit() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        AtomicBoolean errorOccurred = new AtomicBoolean(false);

        repository.add(null, new TaskCallback<Boolean>() {
            @Override
            public void onComplete(Boolean result) {
                latch.countDown();
            }

            @Override
            public void onError(Fault fault) {
                errorOccurred.set(true);
                latch.countDown();
            }
        });

        assertTrue(latch.await(5, TimeUnit.SECONDS));
        assertTrue(errorOccurred.get());
    }
}
