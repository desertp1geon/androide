package com.example.iscip.data.mappers;

import com.example.iscip.data.Fruit;
import com.example.iscip.data.room.FruitEntity;

import java.util.ArrayList;
import java.util.List;

public final class FruitMappers {

    private FruitMappers() {
    }

    public static Fruit toDomain(FruitEntity entity) {
        return new Fruit(
                entity.id,
                entity.name,
                entity.type,
                entity.description,
                entity.calories
        );
    }

    public static FruitEntity toEntity(Fruit fruit) {
        return new FruitEntity(
                fruit.getId(),
                fruit.getName(),
                fruit.getType(),
                fruit.getDescription(),
                fruit.getCalories()
        );
    }

    public static List<Fruit> toDomainList(
            List<FruitEntity> entities) {

        List<Fruit> fruits = new ArrayList<>();

        for (FruitEntity entity : entities) {
            fruits.add(toDomain(entity));
        }

        return fruits;
    }
}