
package com.example.iscip.mappers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.example.iscip.data.Fruit;
import com.example.iscip.data.mappers.FruitMappers;
import com.example.iscip.data.room.FruitEntity;

public class FruitMappersTest {

    @Test
    public void testMapperInitialization() throws Exception {
        Constructor<FruitMappers> constructor =
                FruitMappers.class.getDeclaredConstructor();

        assertTrue(
                "Конструктор повинен бути приватним",
                Modifier.isPrivate(constructor.getModifiers())
        );

        assertNotNull(FruitMappers.class);
    }

    @Test
    public void testToDomainWithNull() {
        assertThrows(
                NullPointerException.class,
                () -> FruitMappers.toDomain(null)
        );
    }

    @Test
    public void testToEntityWithNull() {
        assertThrows(
                NullPointerException.class,
                () -> FruitMappers.toEntity(null)
        );
    }

    @Test
    public void testToDomainListWithNull() {
        assertThrows(
                NullPointerException.class,
                () -> FruitMappers.toDomainList(null)
        );
    }

    @Test
    public void testToDomainMapping() {
        FruitEntity entity = mock(FruitEntity.class);

        entity.id = 1;
        entity.name = "Apple";
        entity.type = "Pome";
        entity.description = "Red apple";
        entity.calories = "52";

        Fruit fruit = FruitMappers.toDomain(entity);

        assertNotNull(fruit);
        assertEquals(1, fruit.getId());
        assertEquals("Apple", fruit.getName());
        assertEquals("Pome", fruit.getType());
        assertEquals("Red apple", fruit.getDescription());
        assertEquals("52", fruit.getCalories());
    }

    @Test
    public void testToEntityMapping() {
        Fruit fruit = mock(Fruit.class);

        when(fruit.getId()).thenReturn(2);
        when(fruit.getName()).thenReturn("Banana");
        when(fruit.getType()).thenReturn("Tropical");
        when(fruit.getDescription()).thenReturn("Yellow banana");
        when(fruit.getCalories()).thenReturn("89");

        FruitEntity entity = FruitMappers.toEntity(fruit);

        assertNotNull(entity);
        assertEquals(2, entity.id);
        assertEquals("Banana", entity.name);
        assertEquals("Tropical", entity.type);
        assertEquals("Yellow banana", entity.description);
        assertEquals("89", entity.calories);
    }

    @Test
    public void testToDomainListMapping() {
        FruitEntity apple = new FruitEntity(
                1, "Apple", "Pome", "Red apple", "52"
        );

        FruitEntity banana = new FruitEntity(
                2, "Banana", "Tropical", "Yellow banana", "89"
        );

        List<FruitEntity> entities = Arrays.asList(apple, banana);

        List<Fruit> fruits = FruitMappers.toDomainList(entities);

        assertNotNull(fruits);
        assertEquals(2, fruits.size());

        assertEquals("Apple", fruits.get(0).getName());
        assertEquals("Banana", fruits.get(1).getName());
    }

    @Test
    public void testToDomainListWithEmptyList() {
        List<Fruit> fruits = FruitMappers.toDomainList(
                Collections.emptyList()
        );

        assertNotNull(fruits);
        assertTrue(fruits.isEmpty());
    }
}
