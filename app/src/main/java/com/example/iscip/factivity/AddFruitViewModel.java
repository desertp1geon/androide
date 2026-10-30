
package com.example.iscip.factivity;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.iscip.data.Fruit;
import com.example.iscip.data.FruitRepository;
import com.example.iscip.basic.concurency.concurency.TaskCallback;
import com.example.iscip.basic.concurency.concurency.Fault;

public class AddFruitViewModel extends ViewModel {

    private final FruitRepository fruitRepository;

    private final MutableLiveData<MainActivityFruitModel>
            fruitModel = new MutableLiveData<>();

    private final MutableLiveData<Boolean> saveSuccess =
            new MutableLiveData<>(false);

    private final MutableLiveData<String> errorMessage =
            new MutableLiveData<>();

    public AddFruitViewModel(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
        fruitModel.setValue(new MainActivityFruitModel());
    }

    public MutableLiveData<MainActivityFruitModel> getFruitModel() {
        return fruitModel;
    }

    public MutableLiveData<Boolean> getSaveSuccess() {
        return saveSuccess;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void onSaveButtonClicked(View v) {

        MainActivityFruitModel model = fruitModel.getValue();

        if (model == null) {
            return;
        }

        boolean hasEmptyFields =
                model.getName().trim().isEmpty()
                        || model.getType().trim().isEmpty()
                        || model.getDescription().trim().isEmpty()
                        || model.getCalories().trim().isEmpty();

        if (hasEmptyFields) {
            model.setShowError(true);
            fruitModel.setValue(model);
            return;
        }

        Fruit fruit = new Fruit(
                0,
                model.getName().trim(),
                model.getType().trim(),
                model.getDescription().trim(),
                model.getCalories().trim()
        );

        fruitRepository.add(fruit, new TaskCallback<Void>() {

            @Override
            public void onComplete(Void result) {
                saveSuccess.postValue(true);
            }

            @Override
            public void onError(Fault fault) {
                errorMessage.postValue(
                        "Не вдалося зберегти фрукт"
                );
            }
        });
    }
}
