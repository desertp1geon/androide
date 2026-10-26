
package com.example.iscip.factivity;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private final MutableLiveData<MainActivityFruitModel>
            fruitModel = new MutableLiveData<>();

    public MainActivityViewModel() {
        fruitModel.setValue(new MainActivityFruitModel());
    }

    public MutableLiveData<MainActivityFruitModel>
    getFruitModel() {
        return fruitModel;
    }

    public void onNextButtonClicked(View v) {
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
            model.setNavigateToNext(false);
            model.setShowError(true);
        } else {
            model.setShowError(false);
            model.setNavigateToNext(true);
        }

        fruitModel.setValue(model);
    }
}
