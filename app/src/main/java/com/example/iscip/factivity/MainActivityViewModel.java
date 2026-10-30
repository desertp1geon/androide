
package com.example.iscip.factivity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.iscip.data.Fruit;
import com.example.iscip.data.FruitRepository;
import com.example.iscip.basic.concurency.concurency.TaskCallback;
import com.example.iscip.basic.concurency.concurency.Fault;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final FruitRepository fruitRepository;

    private final MutableLiveData<List<Fruit>> fruits =
            new MutableLiveData<>(new ArrayList<>());

    private final MutableLiveData<String> errorMessage =
            new MutableLiveData<>();

    public MainActivityViewModel(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public MutableLiveData<List<Fruit>> getFruits() {
        return fruits;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void loadFruits() {

        fruitRepository.getAll(new TaskCallback<List<Fruit>>() {

            @Override
            public void onComplete(List<Fruit> result) {
                fruits.postValue(result);
            }

            @Override
            public void onError(Fault fault) {
                errorMessage.postValue(
                        "Не вдалося завантажити колекцію"
                );
            }
        });
    }
}
