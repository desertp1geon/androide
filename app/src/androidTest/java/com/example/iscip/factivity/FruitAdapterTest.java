package com.example.iscip.factivity;

import static org.junit.Assert.*;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import android.content.Context;

import com.example.iscip.data.Fruit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;

@RunWith(AndroidJUnit4.class)
public class FruitAdapterTest {

    private FruitAdapter adapter;

    @Before
    public void setUp() {
        adapter = new FruitAdapter(null);
    }

    @Test
    public void testAdapterInitialization() {
        assertNotNull(adapter);
        assertEquals(0, adapter.getItemCount());
    }

    @Test
    public void testSetFruits() {
        Fruit apple = new Fruit(
                1, "Apple", "Pome", "Red apple", "52"
        );

        Fruit banana = new Fruit(
                2, "Banana", "Tropical", "Yellow banana", "89"
        );

        adapter.setFruits(Arrays.asList(apple, banana));

        assertEquals(2, adapter.getItemCount());
    }

    @Test
    public void testSetEmptyFruits() {
        Fruit apple = new Fruit(
                1, "Apple", "Pome", "Red apple", "52"
        );

        adapter.setFruits(Collections.singletonList(apple));
        adapter.setFruits(Collections.emptyList());

        assertEquals(0, adapter.getItemCount());
    }

    @Test
    public void testSetNullFruits() {
        adapter.setFruits(null);

        assertEquals(0, adapter.getItemCount());
    }

    @Test
    public void testUpdateFruits() {
        Fruit apple = new Fruit(
                1, "Apple", "Pome", "Red apple", "52"
        );

        Fruit banana = new Fruit(
                2, "Banana", "Tropical", "Yellow banana", "89"
        );

        adapter.setFruits(Collections.singletonList(apple));
        assertEquals(1, adapter.getItemCount());

        adapter.setFruits(Arrays.asList(apple, banana));
        assertEquals(2, adapter.getItemCount());
    }
}